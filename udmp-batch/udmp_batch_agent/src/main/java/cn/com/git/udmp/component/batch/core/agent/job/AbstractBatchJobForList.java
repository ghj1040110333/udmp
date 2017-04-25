package cn.com.git.udmp.component.batch.core.agent.job;

import java.util.List;

import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.core.component.activity.IActivity;
import cn.com.git.udmp.component.batch.exception.BatchBizException;

/**
 * @description 多条记录处理形式的作业模板抽象父类
 * @author liuliang liuliang_wb@newchina.live
 * @date 2015年1月21日 下午3:15:05
 */
public abstract class AbstractBatchJobForList extends AbstractBatchJob implements IActivity {
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
                    // 执行逻辑的事务由子类自行控制，此处与forSinge模板类相异
                    List<JobData> jobDatas = execute(getJobSessionContext(), resultDatas);
                    for (JobData key : jobDatas) {
                        write(getJobSessionContext(), key);
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
                    throw e;
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
     * @description 批处理任务的具体操作
     * @author liuliang liuliang@newchinalife.com
     * @param jobSessionContext 上下文参数
     * @param resultDatas 需要处理的结果集
     * @return 处理后的结果集
     */
    public abstract List<JobData> execute(JobSessionContext jobSessionContext, List<JobData> resultDatas);

    /**
     * @description 写入操作
     * @author liuliang liuliang@newchinalife.com
     * @param jobSessionContext 上下文对象
     * @param jobData 数据对象
     */
    public abstract void write(JobSessionContext jobSessionContext, JobData jobData);

}
