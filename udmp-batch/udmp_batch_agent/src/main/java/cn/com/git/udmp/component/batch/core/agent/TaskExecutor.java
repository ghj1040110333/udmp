package cn.com.git.udmp.component.batch.core.agent;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.commons.beanutils.BeanUtils;

import cn.com.git.udmp.common.exception.FrameworkException;
import cn.com.git.udmp.component.batch.common.constants.CommandEnum;
import cn.com.git.udmp.component.batch.common.constants.JobRunStatus;
import cn.com.git.udmp.component.batch.common.utils.BalanceUtil;
import cn.com.git.udmp.component.batch.common.utils.BalanceUtil.Section;
import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.core.agent.communicate.AgentCommunicator;
import cn.com.git.udmp.component.batch.core.agent.concurrent.WorkExecutorGroup;
import cn.com.git.udmp.component.batch.exception.BatchBizException;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @description 作业执行器
 * @author liuliang
 * @date 2015年2月4日 上午10:23:08
 */
public class TaskExecutor implements ITaskExecutor {
    private JobSessionContext context;
    private EventExecutorGroup es;
    private Map<Integer, Future<?>> workMap = new HashMap<Integer, Future<?>>();
    private TaskHandler handler;
    private long timeout = 600; // default timeout setting
    private String name;
    private boolean isFinished = false;
    // 线程的状态收集

    /**
     * <p>
     * Title:作业执行器
     * </p>
     * <p>
     * Description: 作业执行器
     * </p>
     * 
     * @param jsContext 上下文对象
     */
    public TaskExecutor(JobSessionContext jsContext) {
        init(jsContext);
    }

    public TaskExecutor(String name, JobSessionContext jsContext) {
        this.name = name;
        init(jsContext);
    }

    private void init(JobSessionContext jsContext) {
        this.context = jsContext;
        es = new WorkExecutorGroup(getPoolSize());
        handler = new TaskHandler(jsContext);
    }

    @Override
    public void startTask() {
        logger.debug("线程池{}正在启动作业", this.getContext().getJobId());
        // TODO 1.获取区间，根据区间分区,
        long startNum = getStartNum();
        long endNum = getEndNum();
        long counts = endNum - startNum + 1;
        logger.debug("线程池大小是:{}", getPoolSize());
        logger.debug("需要执行的区间是{}到{}", startNum, endNum);
        /**
         * sharding算法
         */
        List<Section> balanceArray = BalanceUtil.balanceArray(getPoolSize(), startNum, endNum);
        logger.debug("sharding后的区间集为:{}", balanceArray);

        handler.setSize(balanceArray.size());

        int i = 0;
        for (Section section : balanceArray) {
            i++;
            JobSessionContext jsContext = new JobSessionContext();
            // 2.然后新建任务，将分批信息和参数放到jobSessionContext中
            try {
                // TODO bihb-005-使用cglib的beancopier，全系统统一，而且cglib的字节码操作效率最高。
                BeanUtils.copyProperties(jsContext, context);
                jsContext.setExceptionList(new ArrayList<BatchBizException>());
                logger.info(
                        "--当前线程：" + Thread.currentThread() + "--**异常列表引用地址：" + jsContext.getExceptionList().toString());
                logger.info("------------线程:" + i + "的session-id:" + jsContext.hashCode());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            jsContext.setStartNum(section.getStartNum());
            jsContext.setEndNum(section.getEndNum());
            jsContext.setData(context.getData()); // 参数传递
            jsContext.setExtension(context.getExtension());
            // 对每个执行线程都默认设置上总记录数条件，用于线程回执更新时使用
            // report中记录total
            jsContext.setTotalCnt(counts);
            logger.debug("即将启动一个线程执行区间{}到{}", section.getStartNum(), section.getEndNum());

            IWork work = new Work(i, jsContext, handler);
            // 将线程控制类future记录下来
            final int workId = i;
            // 这里使用的是netty的线程池,方便为线程添加监听事件
            Future<Boolean> future = es.submit(work);
            addListener(future, workId);
            // TODO 超时处理
            // setTimeoutEvent(submit,jsContext,i);
            workMap.put(i, future);
        }
        logger.info("---------------------开启的线程数量：" + workMap.size());
        logger.debug("任务{}的任务实例{}启动完成", context.getJobId(), context.getJobRunId());
    }

    /**
     * @title
     * @description 为每个线程添加监听器，监听器负责返回处理和异常处理等
     * 
     * @param future
     * @param workId
     */
    private void addListener(Future<Boolean> future, final int workId) {
        future.addListener(new GenericFutureListener<Future<Boolean>>() {
            @Override
            public void operationComplete(Future<Boolean> future) throws Exception {
                try {
                    Boolean result = future.get();
                    // TODO 回执线程池告知此线程已完成
                    logger.debug("线程{}执行作业结束", Thread.currentThread().getName());
                    if (result != null && result.booleanValue()) {
                        handler.log(true, workId, null);
                        recycleTaskExecutor();
                    }
                } catch (Exception e) {
                    if (e instanceof CancellationException) {

                    } else if (e instanceof ExecutionException) {
                        Throwable cause = e.getCause();
                        if (cause instanceof BatchBizException) {
                            // 捕捉到批处理应用异常
                            logger.info("thread-id:" + Thread.currentThread()
                                    + "---------------------------------------------------------BatchBizException:{}" + e);
                            handler.log(false, workId, (BatchBizException) cause);
                        } else if (cause instanceof Exception) {
                            logger.info("thread-id:" + Thread.currentThread()
                                    + "---------------------------------------------------------Exception:{}" + e);
                            // TODO 将异常抛出给线程池
//                            e.printStackTrace();
                            handler.log(false, workId, new BatchBizException(e.getMessage()));
                        }
                    }
                }
            }
        });
    }

    /**
     * @title
     * @description 回收执行线程池
     * 
     */
    private void recycleTaskExecutor() {
        if (isAllDone()) {
            logger.debug("当前线程池完成工作,即将回收");
            this.shutdown();
        }
    }

    /**
     * @title
     * @description 判断是否任务都已经完成
     * 
     * @return
     */
    private boolean isAllDone() {
        boolean flag = true;
        //当且仅当线程池数量和分配的线程数量一致时才判断是否都完成，防止只初始化并且只运行完第一个线程的时候就执行了recycleTaskExecutor方法
        if (workMap.size() == handler.getSize()) {
            for (Integer key: workMap.keySet()) {
                Future f = workMap.get(key);
                //但凡有一个线程没完成，也不会执行recycleTaskExecutor方法
                if(f.isDone()||f.isCancelled()||f.isSuccess()){
                    continue;
                }else{
                    flag = false;
                    logger.debug("线程{}没完成",key);
                    System.out.println(f.isDone());
                    System.out.println(f.isCancelled());
                    System.out.println(f.isSuccess());
                    break;
                }
            }
        }else{
            logger.debug("workMap size与handler不匹配");
            return false;
        }
        return flag;
    }

    /**
     * @description 设置线程超时处理
     * 
     * @param submit
     * @param jsContext
     * @param threadId
     */
    private void setTimeoutEvent(Future<?> submit, JobSessionContext jsContext, int threadId) {
        try {
            // updated by liang on 2016/11/28 对线程进行超时控制，默认600s
            submit.get(timeout, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            logger.error("任务{}的线程{}执行异常:{}", jsContext.getJobId(), threadId, e);
        }catch ( ExecutionException e) {
            logger.error("任务{}的线程{}执行异常:{}", jsContext.getJobId(), threadId, e);
        } catch (TimeoutException e) {
            logger.error("任务{}的线程{}执行超时,即将停止当前线程", jsContext.getJobId(), threadId);
            submit.cancel(true);
        }
    }

    private long getEndNum() {
        return context.getEndNum();
    }

    private long getStartNum() {
        return context.getStartNum();
    }

    @Override
    public void stopTask() {
        // TODO batch--停止线程，但是也需要同时记录日志,还需要实时与服务器端报告停止进度
        logger.debug("停止线程..");
        for (int key : workMap.keySet()) {
            logger.debug("正在停止线程{}", key);
            workMap.get(key).cancel(true);
            logger.debug("停止作业...");
        }
        es.shutdownGracefully();
        logger.debug("停止线程完成");
        reportStopStatus(context);
    }

    /**
     * @description 将停止结束的信息返回给server端
     * @param context 任务的上下文参数
     */
    private void reportStopStatus(JobSessionContext context) {
        logger.debug("返回任务的执行结果");
        AgentCommunicator communicator = new AgentCommunicator(context);
        // TODO batch--已经处理的结果需要收集
        communicator.reportStatus(context.getJobRunId(), JobRunStatus.ABORTED);
    }

    @Override
    public void pauseTask() {
        // TODO Auto-generated method stub

    }

    /**
     * @description 获取线程池大小
     * @author liuliang liuliang@newchinalife.com
     * @return 线程池大小
     */
    private int getPoolSize() {
        return context.getThreadSize();
    }

    public JobSessionContext getContext() {
        return context;
    }

    public void setContext(JobSessionContext context) {
        this.context = context;
    }

    @Override
    public void executeTask() {
        logger.debug("当前任务处理的作业ID为{}", context.getJobId());
        logger.debug("当前任务处理的作业名称为{}", context.getJobName());
        logger.debug("当前任务处理的作业类为{}", context.getJobClazzName());
        if (context.getCommand() == CommandEnum.START) {
            this.startTask();
        } else if (context.getCommand() == CommandEnum.ABORT) {
            this.stopTask();
        } else if (context.getCommand() == CommandEnum.PAUSE) {
            this.pauseTask();
        } else {
            // TODO batch--异常信息需要使用静态变量统一化
            logger.error("执行作业时没有找到对应指令{}", context.getCommand());
            throw new FrameworkException("执行作业时没有找到对应指令");
        }
    }

    /**
     * @description 获取可控线程集合
     * @return 可控线程集合
     */
    public Map<Integer, Future<?>> getWorkMap() {
        return workMap;
    }

    @Override
    public String toString() {
        return context.toString();
    }

    @Override
    public boolean isTerminated() {
        return es.isTerminated();
    }

    @Override
    public void shutdown() {
        if (es.isTerminated() || es.isShutdown()) {
            logger.debug("线程池{}已关闭", getName());
            return;
        }
        es.shutdownGracefully();
        if (es.isShuttingDown()) {
            logger.debug("线程池{}正在关闭", getName());
        }
        if (es.isTerminated() || es.isShutdown()) {
            logger.debug("线程池{}已关闭", getName());
        }
        isFinished = true;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
