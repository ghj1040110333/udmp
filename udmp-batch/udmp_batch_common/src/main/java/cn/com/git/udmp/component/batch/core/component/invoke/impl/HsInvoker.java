package cn.com.git.udmp.component.batch.core.component.invoke.impl;

import cn.com.git.udmp.common.model.DataObject;
import cn.com.git.udmp.component.batch.core.component.invoke.AbstractServiceInvoker;
import cn.com.git.udmp.component.batch.core.component.invoke.message.ServiceMessage;

/**
 * @description hessian 的服务调用类
 * @author liuliang 
 * @date 2015年1月26日 下午3:12:40
 */
public class HsInvoker extends AbstractServiceInvoker {

    @Override
    public <T extends DataObject> void invoke(T t) {
        logger.debug("invoke成功呢");
        handle(t);
    }

    @Override
    public ServiceMessage test(String t) {
        logger.debug("hello:" + t);
        ServiceMessage message = new ServiceMessage();
        return message;
    }
}
