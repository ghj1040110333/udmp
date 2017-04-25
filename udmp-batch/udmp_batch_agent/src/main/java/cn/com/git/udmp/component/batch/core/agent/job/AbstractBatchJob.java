package cn.com.git.udmp.component.batch.core.agent.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.transaction.support.TransactionTemplate;

import cn.com.git.udmp.common.exception.FrameworkException;
import cn.com.git.udmp.component.batch.common.constants.BatchCommonConst;
import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.core.agent.TaskHandler;
import cn.com.git.udmp.component.batch.core.agent.Work;
import cn.com.git.udmp.component.batch.core.agent.communicate.AgentCommunicator;
import cn.com.git.udmp.component.batch.core.component.activity.EActivityCmpType;
import cn.com.git.udmp.component.batch.core.component.activity.IActivity;
import cn.com.git.udmp.component.batch.core.transaction.TransactionUtil;
import cn.com.git.udmp.component.batch.exception.BatchBizException;
import cn.com.git.udmp.component.batch.model.JobParam;
import cn.com.git.udmp.component.batch.template.job.AbstractPrePostJobTemplate;
import cn.com.git.udmp.utils.lang.UtilDate;

/**
 * @description 基于作业模板的抽象父类
 * @author liuliang 
 * @date 2015年6月23日 下午3:39:52
 */
public abstract class AbstractBatchJob extends AbstractPrePostJobTemplate implements IActivity {
    private TaskHandler taskHandler;
    private EActivityCmpType type;
    private List<Object> numList = new ArrayList<Object>();
    protected long loopTime = 60000;

    @Override
    public void cmpType() {
        this.type = EActivityCmpType.JOB;
    }

    @Override
    public void cmpAct() {
        // TODO 根据type类型执行不同的操作
        // doExecute(getJobSessionContext());
    }

    /**
     * @description 校验时间窗口的结束时间是否达到
     * @author liuliang liuliang@newchinalife.com
     * @param jsContext 上下文对象
     * @return true or false
     */
    public final boolean checkJobEndWindowDone(JobSessionContext jsContext) {
        if (jsContext.getJobEndWindow() != null) {
            logger.debug("校验任务{}的时间窗口结束时间", jsContext.getJobName());
            logger.debug("jobSessionContext:{}", jsContext);
            Date now = new Date();
            long diff = now.getTime() - jsContext.getJobEndWindow().getTime();
            if (diff >= 0) {
                return true;
            }
            logger.debug("任务{}未到达时间窗口结束时间{}，继续执行任务",jsContext.getJobName(),UtilDate.format(jsContext.getJobEndWindow(),BatchCommonConst.TIME_WINDOW_FORMAT));
        }
        return false;
    }

    @Override
    public final JobSessionContext doExecute(JobSessionContext jobSessionContext, TaskHandler handler) {
        this.taskHandler = handler;
        jobAudit(jobSessionContext);
        if (isCanBeRun()) {
            logger.debug("作业前置条件满足，开始执行作业");
            if (checkIsLoopTask(jobSessionContext)) {
                while (isActiveFlag()) {
                    logger.debug("开始轮询作业{}", jobSessionContext.getJobName());
                    jobStart(jobSessionContext, handler);
                    try {
                        Thread.sleep(getLoopTime());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }else{
                logger.debug("开始普通作业{}", jobSessionContext.getJobName());
                jobStart(jobSessionContext, handler);
            }
        } else {
            // TODO 条件不满足的情况是否需要回执对应失败信息
            logger.debug("作业前置条件不满足，跳过当前作业");
        }
        jobAudit(jobSessionContext);
        return jobSessionContext;
    }

    /**
     * @description 判断是否是轮询作业
     * @author liuliang liuliang@newchinalife.com
     * @param jobSessionContext
     * @return true or false
     */
    private boolean checkIsLoopTask(JobSessionContext jobSessionContext) {
        return jobSessionContext.isLoop();
    }

    /**
     * @description 作业运行逻辑
     * @author liuliang liuliang@newchinalife.com
     * @param jobSessionContext 执行上下文
     * @param handler 任务处理器
     */
    public final void jobStart(JobSessionContext jobSessionContext, TaskHandler handler) {

        TransactionTemplate transactionTemplate = TransactionUtil.getTransactionTemplate();

        // 一个批次数量
        final int counts = jobSessionContext.getBatchSize();
        // 开始记录数
        final long startNum = jobSessionContext.getStartNum();
        // 结束记录数
        final long endNum = jobSessionContext.getEndNum();

        // 总记录数 2015-5-7 总记录数在这不准确，是拆分到线程中的
        // jobSessionContext.setTotalCnt(endNum);
        logger.debug("批处理操作执行区间{}到{}", startNum, endNum);
        // 启动作业的流程
        // 1.查询逻辑
        long start = startNum;

        List<JobData> resultDatas = new ArrayList<JobData>();

        int i = 0;
        while (start < endNum) {
            // 数据清零，开始记录成功失败条数
            // 不记录本线程成功记失败条数，只记录当前线程每次循环的成功失败条数，并向handler中累加
            jobSessionContext.setSuccessCnt(0);
            jobSessionContext.setFailCnt(0);

            logger.info("开始执行" + (++i) + "次...");
            if ((start + counts) > endNum) {
                resultDatas = query(jobSessionContext, start, (int) (endNum - start));
                start = endNum;
            } else {
                resultDatas = query(jobSessionContext, start, counts);
                start += counts;
            }
            if (resultDatas == null) {
                // 如果查询不到记录，继续执行下一批
                continue;
            }
            // start += resultDatas.size();
            if(StringUtils.isEmpty(getIdName())){
                // 处理数据的过程(+事务)
                handle(resultDatas, transactionTemplate, jobSessionContext);
            }else{
                // 过滤掉已经处理的数据
                resultDatas = filtrateResult(resultDatas, getNumList());
                // 处理数据的过程(+事务)
                handle(resultDatas, transactionTemplate, jobSessionContext);
                // 记录已经处理的数据
                addNumList(resultDatas);
            }
            // 执行完毕后report一次

            // 将线程自己的session的成功失败条数累加到handler中的共享session
            handler.sumSuccessCnt(jobSessionContext.getSuccessCnt());
            handler.sumFailCnt(jobSessionContext.getFailCnt());

            handler.reportStatusCnt(jobSessionContext.getJobRunId(), jobSessionContext.getTotalCnt(), handler
                    .getJsContext().getSuccessCnt(), handler.getJsContext().getFailCnt());

            // TODO 应该批量一次写回去，不应该循环处理
            logger.info("----进行info数据回写-记录数：" + jobSessionContext.getExceptionList().size());
            for (Iterator<BatchBizException> iter = jobSessionContext.getExceptionList().iterator(); iter.hasNext();) {
                BatchBizException e = iter.next();
                handler.reportException(e);
            }
            // 清空输出日志列表
            jobSessionContext.setExceptionList(new ArrayList<BatchBizException>());

            // logger.debug("执行开始于{},结束于{}", start, endNum);
            if (resultDatas.size() < counts && start < endNum) {
                break;
            }
            // 如果时间窗口已经达到，停止当前作业
            if (checkJobEndWindowDone(getJobSessionContext())) {
                break;
            }
        }

    }

    /**
     * @description 新增处理完的结果集的序号
     * @author liuliang liuliang@newchinalife.com
     * @param resultDatas 结果集
     */
    private void addNumList(List<JobData> resultDatas) {
        logger.debug("新增处理完的结果集的序号");
        for (JobData data : resultDatas) {
            numList.add(data.getLong(getIdName()));
        }
    }

    /**
     * @description 过滤掉已经处理的数据
     * @author liuliang liuliang@newchinalife.com
     * @param resultDatas 结果集
     * @param numList 序号列表
     */
    private List<JobData> filtrateResult(List<JobData> resultDatas, List<Object> numList) {
        List<JobData> filtratedList = new ArrayList<JobData>();
        logger.debug("开始过滤结果集中已经处理的数据");
        for (JobData data : resultDatas) {
            Object num = data.get(getIdName());
            if (!numList.contains(num)) {
                filtratedList.add(data);
            }
        }
        return filtratedList;
    }

    /**
     * @description 获取索引记录使用的ID名称
     * @author liuliang liuliang@newchinalife.com
     * @return ID名称
     */
    public abstract String getIdName();

    /**
     * @description 执行作业
     * @author liuliang liuliang@newchinalife.com
     * @param resultDatas 需要处理的数据
     * @param transactionTemplate 事务模板
     * @param jobSessionContext session上下文数据
     */
    public abstract void handle(List<JobData> resultDatas, TransactionTemplate transactionTemplate,
            JobSessionContext jobSessionContext);

    public abstract boolean isCanBeRun();

    /**
     * @description 添加需要回执的一些系统参数
     * @author liuliang liuliang@newchinalife.com
     * @param jobParam 系统参数
     */
    public final void addSysParams(JobParam... jobParam) {
        if (taskHandler != null && jobParam != null && jobParam.length != 0) {
            AgentCommunicator communicator = taskHandler.getCommunicator();
            JobSessionContext jsContext = communicator.getJsContext();
            List<JobParam> params = jsContext.getParams();
            // 回执参数添加
            for (JobParam key : jobParam) {
                params.add(key);
            }
            jsContext.setParams(params);
            communicator.setJsContext(jsContext);
        }
    }

    /**
     * @description 执行查询总数
     * @author liuliang liuliang@newchinalife.com
     * @param jobSessionContext 上下文对象
     * @return 上下文对象
     */
    public final JobSessionContext executeQueryCounts(JobSessionContext jobSessionContext) {
        // 重置上下文中的起始和终止值
        jobSessionContext.setStartNum(-1);
        jobSessionContext.setEndNum(-1);
        // 执行Agent的查询待处理记录集操作,并赋值到上下文参数中
        JobSessionContext context = queryCounts(jobSessionContext);
        if (context.getStartNum() < 0 || context.getEndNum() < 0) {
            // TODO batch--异常信息常量化
            throw new FrameworkException("作业类未实现查询总数的方法并给与全局变量赋值");
        }
        return context;
    }

    /**
     * @description 属于批处理流程中的查询待处理记录集的操作，目的是为了查询需要处理的数据集(起始值和终止值)
     * @destination <li>
     *              需要根据jobSessionContext中的参数执行计数操作并且为起始值和终止值赋值,(
     *              即startNum和endNum值 )</li>
     * @author liuliang liuliang@newchinalife.com
     * @param jobSessionContext 上下文对象
     * @return 包含startNum和endNum值的上下文对象
     */
    public abstract JobSessionContext queryCounts(JobSessionContext jobSessionContext);

    /**
     * @description 查询操作
     * @author liuliang liuliang@newchinalife.com
     * @param jobSessionContext 全局变量
     * @param start 起始值
     * @param counts 查询数量
     * @return 记录集
     */
    public abstract List<JobData> query(JobSessionContext jobSessionContext, long start, int counts);

    /**
     * @description 作业审计
     * @author liuliang liuliang@newchinalife.com
     * @param jobSessionContext 上下文参数
     */
    public final void jobAudit(JobSessionContext jobSessionContext) {
        // TODO 审计
    }

    /**
     * @description 添加远程日志信息
     * @version 1.0
     * @title 添加远程日志信息
     * @author bihb bihb_wb@newchinalife.com
     * @param jobSessionContext 执行上下文对象，hash表
     * @param code 信息类型编码（客户端自定义字符串-vchar50以内）
     * @param msg 提示信息（用于定位代码行及异常或问题-vchar3000以内）
     * @param level 信息级别（值域参见：cn.com.git.udmp.component.batch.common.constants.
     *            BatchJobRunLogLevel）
     */
    public final void addLogInfo(JobSessionContext jobSessionContext, String code, String msg, String level) {
        jobSessionContext.addExceptionList(new BatchBizException(code, msg, level));
    }

    @Override
    public final JobSessionContext preExecute(JobSessionContext jobSessionContext) {
        return jobSessionContext;
    }

    @Override
    public final JobSessionContext postExecute(JobSessionContext jobSessionContext) {
        return jobSessionContext;
    }

    /**
     * @description 作业停止逻辑
     * @author liuliang liuliang@newchinalife.com
     */
    public abstract void jobStop();


    public long getLoopTime() {
        return loopTime;
    }

    public void setLoopTime(long loopTime) {
        this.loopTime = loopTime;
    }

    public List<Object> getNumList() {
        return numList;
    }

    public void setNumList(List<Object> numList) {
        this.numList = numList;
    }

    
    @Override
    public  boolean isActiveFlag() {
        return Work.isCurrentJobStoped();
    }
}
