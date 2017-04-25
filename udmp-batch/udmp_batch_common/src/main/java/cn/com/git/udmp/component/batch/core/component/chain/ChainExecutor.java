package cn.com.git.udmp.component.batch.core.component.chain;

import cn.com.git.udmp.common.exception.FrameworkException;
import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.core.IDuty;

/**
 * @description 责任链执行类
 * @author liuliang
 * @date 2015年5月13日 下午5:44:47
 */
public class ChainExecutor implements IChain {
    protected IDuty[] dutys;

    @Override
    public void executeChain(JobSessionContext jobSessionContext) {
        try {
            if (doChainOrNot(jobSessionContext)) {
                if (dutys != null) {
                    for (IDuty duty : dutys) {
                        duty.executeDuty(jobSessionContext);
                    }
                }
            }
        } catch (DutyEndException e) {
            // 如果是一个责任链终止异常，则责任链停止执行(可以通过此机制来终止整个任务链的执行)，但不影响后续程序执行
            logger.info("发现责任链终止信号");
            return;
        } catch (FrameworkException e) {
            logger.error("任务链执行运行时异常:errorCode:{},errorMessage:{}",e.getErrCode(),e);
            throw e;
        } catch (RuntimeException e) {
            logger.error("任务链执行异常:{}", e.getMessage());
            if (logger.isDebugEnabled()) {
//                e.printStackTrace();
            }
            throw new RuntimeException(e);
        }
    }

    public void setDutys(IDuty[] dutys) {
        this.dutys = dutys;
    }

    @Override
    public void close() {
        try {
            if (dutys != null) {
                for (IDuty duty : dutys) {
                    duty.close();
                }
            }
        } catch (DutyEndException e) {
            // 如果是一个责任链终止异常，则责任链执行(可以通过此机制来终止整个任务链的执行)
            logger.info("发现责任链终止信号");
            return;
        } catch (FrameworkException e) {
            logger.error("任务链执行运行时异常:errorCode:{},errorMessage:{}",e.getErrCode(),e);
            throw e;
        } catch (RuntimeException e) {
            logger.error("任务链执行异常:{}", e);
            // e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    protected boolean doChainOrNot(JobSessionContext jobSessionContext) {
        return true;
    }
}
