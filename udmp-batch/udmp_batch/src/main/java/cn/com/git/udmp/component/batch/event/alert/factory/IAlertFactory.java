package cn.com.git.udmp.component.batch.event.alert.factory;

import cn.com.git.udmp.component.batch.event.alert.impl.IAlertProxy;

/**
 * 报警工厂接口
 * @description 报警工厂接口
 * @author bihb bihb_wb@newchinalife.com
 * @date 2015年4月13日 下午3:35:18
 */
public interface IAlertFactory {
    /**
     * @description 获取报警代理对象
     * @version 1.0
     * @title 获取报警代理对象
     * @author bihb bihb_wb@newchinalife.com
     * @return 报警代理对象
     */
    public IAlertProxy getAlertProxy();

}
