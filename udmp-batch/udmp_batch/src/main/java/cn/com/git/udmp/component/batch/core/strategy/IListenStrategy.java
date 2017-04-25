package cn.com.git.udmp.component.batch.core.strategy;

import cn.com.git.udmp.core.logging.ILog;

/**
 * @description 监听策略
 * @author liuliang 
 * @date 2015年5月21日 下午1:51:52
 */
public interface IListenStrategy extends ILog {
    /**
     * @description 监听
     * @author liuliang 
     */
    public void listen();

    /**
     * @description 停止监听
     * @author liuliang 
     */
    public void stoplisten();
}
