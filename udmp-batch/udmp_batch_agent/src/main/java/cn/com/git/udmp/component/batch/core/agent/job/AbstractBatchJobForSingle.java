package cn.com.git.udmp.component.batch.core.agent.job;

import java.util.List;

import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.exception.BatchBizException;

/**
 * @description 单条记录处理形式的作业模板抽象父类
 * @author liuliang liuliang_wb@newchina.live
 * @date 2015年1月21日 下午3:15:05
 */
// TODO
// bihb-007-当前抽象类保留，添加两个实现的template，一个是单条循环的，一个是批量处理的，类命名注意使用template等明显的术语，让客户端知道去找合适的模板

// TODO bihb-010-所有方法的javadoc补齐，让别人能看到方法的文档注释，减少用错或反复过来问的麻烦
public abstract class AbstractBatchJobForSingle extends AbstractBatchJob {

    /**
     * @description 执行作业
     * @author liuliang liuliang@newchinalife.com
     * @param resultDatas 需要处理的数据
     * @param transactionTemplate 事务模板
     * @param jobSessionContext session上下文数据
     */
    public void handle(final List<JobData> resultDatas, final TransactionTemplate transactionTemplate,
            final JobSessionContext jobSessionContext) {
        // 事务控制
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {

            protected void doInTransactionWithoutResult(TransactionStatus status) {
                DefaultTransactionDefinition def = new DefaultTransactionDefinition();
                def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
                // 如果status不重新赋值的话，就会报异常
                // 因为transactionManager是改变了status的状态的！
                status = transactionTemplate.getTransactionManager().getTransaction(def);
                // 设为false
                try {
                    //执行逻辑的事务由模板类自行控制，此处与forList模板类相异
                    for (JobData jobData : resultDatas) {
                        // 如果时间窗口已经达到，停止当前作业
                        if (checkJobEndWindowDone(getJobSessionContext())) {
                            throw new BatchBizException("时间窗口的结束时间已经到达，停止当前作业");
                        }
                        write(jobSessionContext, execute(jobSessionContext, jobData));
                    }
                    if (!status.isCompleted()) {
                        transactionTemplate.getTransactionManager().commit(status);
                    }
                    // 执行成功后，记录成功记录数，以批次的概念，全部成功后才进行计数累计
                    jobSessionContext.setSuccessCnt(jobSessionContext.getSuccessCnt() + resultDatas.size());
                } catch (BatchBizException e) {
                    e.printStackTrace();
                    status.setRollbackOnly();
                    // 执行失败，记录失败记录数，以批次的概念，全部计入失败，因为全部被回滚
                    jobSessionContext.setFailCnt(jobSessionContext.getFailCnt() + resultDatas.size());
                    // 捕捉到批处理应用异常
                    // TODO 不能跑出异常
//                    throw e;
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    status.setRollbackOnly();
                    // 执行失败，记录失败记录数，以批次的概念，全部计入失败，因为全部被回滚
                    jobSessionContext.setFailCnt(jobSessionContext.getFailCnt() + resultDatas.size());
                } finally {
                    // report一次
                    
                }
            }
        });
    }

    /**
     * @description 业务操作
     * @author liuliang liuliang@newchinalife.com
     * @param jobSessionContext 上下文对象
     * @param jobData 数据对象
     * @return 经过业务操作后的数据对象
     */
    public abstract JobData execute(JobSessionContext jobSessionContext, JobData jobData);

    /**
     * @description 写入操作
     * @author liuliang liuliang@newchinalife.com
     * @param jobSessionContext 上下文对象
     * @param jobData 数据对象
     */
    public abstract void write(JobSessionContext jobSessionContext, JobData jobData);

}
