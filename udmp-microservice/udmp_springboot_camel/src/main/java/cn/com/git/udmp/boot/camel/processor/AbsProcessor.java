

package cn.com.git.udmp.boot.camel.processor;

import cn.com.git.udmp.boot.camel.common.UdmpCamelConstants;
import cn.com.git.udmp.boot.camel.exceptions.NormalTerminateException;
import cn.com.git.udmp.boot.camel.exceptions.UnSatisfiedCondition;
import cn.com.git.udmp.common.model.DataObject;

/**
 * 
 * @author liang 2016年8月3日
 */
public abstract class AbsProcessor implements IProcessor {

    /*
     * （非 Javadoc）
     * 
     * @see
     * cn.com.git.udmp.calc.camel.job.IExecJob#exec(cn.com.git.udmp.common.model
     * .DataObject)
     */

    @Override
    public final <T extends DataObject> T doProcess(T sourceObj) {
        try {
            if (Boolean.TRUE.equals(sourceObj.get(UdmpCamelConstants.TERMINATE_FLAG))) {
                logger.debug("发现终止条件,跳过当前processor");
                return sourceObj;
            }
            if (preconditions(sourceObj)) {
                T result = process(sourceObj);
                afterProcess(result);
                return result;
            } else {
                throw new UnSatisfiedCondition();
            }
        } catch (NormalTerminateException e) {
            logger.debug("发现终止条件,跳过后续processor");
            sourceObj.set(UdmpCamelConstants.TERMINATE_FLAG, true);
            return sourceObj;
        } catch (UnSatisfiedCondition e) {
            logger.error("不满足执行条件,跳过当前执行点");
            throw e;
        } catch (RuntimeException e) {
            logger.error("任务执行失败:{}", e.getMessage());
            throw e;
        } finally {

        }
    }

    public abstract <T extends DataObject> T process(T sourceObj);

    public abstract <T extends DataObject> void afterProcess(T sourceObj);

    public <T extends DataObject> boolean preconditions(T sourceObj) {
        // default
        return true;
    }

}
