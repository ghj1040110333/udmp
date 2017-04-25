package cn.com.git.udmp.component.batch.core;

import cn.com.git.udmp.component.batch.context.SessionContext;
import cn.com.git.udmp.core.logging.ILog;

/**
 * @description 责任接口
 * @author liuliang 
 * @date 2015年4月8日
 * @version 1.0
 */
public interface IDuty extends ILog,ICloser {
    /**
     * @description 是否执行当前责任点的条件判断
     * @author liuliang 
     * @param context 上下文参数
     * @return 是否执行当前判断的布尔值
     */
    public boolean isDuty(SessionContext context);

    /**
     * @description 当前责任点的执行逻辑
     * @author liuliang 
     * @param context 上下文参数
     * @return 处理后的上下文参数
     */
    public SessionContext executeDuty(SessionContext context);
}
