package cn.com.git.udmp.component.batch.core.component.invoke;

import cn.com.git.udmp.common.model.DataObject;

/**
 * @description 服务调用的抽象父类
 * @author liuliang 
 * @date 2015年1月26日 下午3:22:52
 */
public abstract class AbstractServiceInvoker implements IServiceInvoker {
    IService service;

    public <T extends DataObject> void handle(T t) {
        // TODO 逻辑大概如下
        String status = t.getString("status");
        if (status.equals("start")) {
            logger.debug("the batch service is starting");
            service.start();
        } else if (status.equals("stop")) {
            logger.debug("the batch service is stoping..");
            service.stop();
        } else if (status.equals("run")) {
            logger.debug("the batch service is running");
            service.run(t);
        } else {
            logger.debug("the status is not defined");
        }
    }

    public IService getService() {
        return service;
    }

    public void setService(IService service) {
        this.service = service;
    }

}
