package cn.com.git.udmp.component.batch.event.alert.factory;

import cn.com.git.udmp.common.utils.SpringContextHolder;
import cn.com.git.udmp.component.batch.event.alert.impl.IAlertProxy;

/**
 * 报警聚合类型代理对象创建工厂，创建的报警代理对象响应报警动作，但不做任何操作
 * @description 报警聚合类型代理对象创建工厂，创建的报警代理对象响应报警动作，但不做任何操作
 * @author bihb bihb_wb@newchinalife.com
 * @date 2015年4月13日 下午4:06:00  
 */
public class CompAlertFactory extends BaseAlertFactory implements IAlertFactory {
    /**
     * @Fields alertProxyBeanId : 报警代理对象beanid
     */ 
    private static final String ALERT_PROXY_BEAN_ID = "compAlertProxy";
    /**
     * @Fields factory : 单例工厂对象
     */ 
    private static final CompAlertFactory factory = new CompAlertFactory();
    /**
     * 私有构造函数，禁止new
     * <p>Title: 私有构造函数，禁止new</p> 
     * <p>Description: 私有构造函数，禁止new</p>  
     */
    private CompAlertFactory() {
    }
    /**
     * @description 返回报警工厂
     * @version 1.0
     * @title 返回报警工厂
     * @author bihb bihb_wb@newchinalife.com
     * @return 报警工厂
     */
    public static CompAlertFactory getFactory() {
        return factory;
    }

    /**
     * 获取聚合类型报警代理对象
     * @description 获取聚合类型报警代理对象
     * @version 1.0
     * @title 获取聚合类型报警代理对象
     * @author bihb bihb_wb@newchinalife.com
     * @see cn.com.git.udmp.component.batch.event.alert.factory.IAlertFactory#getAlertProxy()
     * @return 聚合类型报警代理对象
     */
    @Override
    public IAlertProxy getAlertProxy() {
        //通过spring getbean获取，原型形态的代理对象，保证线程安全
        //同时通过spring获取，可以基于Async使用异步线程
        IAlertProxy alertProxy = (IAlertProxy) SpringContextHolder.getBean(ALERT_PROXY_BEAN_ID);
        return alertProxy;
    }

}
