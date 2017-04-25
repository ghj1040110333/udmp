package cn.com.git.udmp.component.batch.core.component.invoke;

import javax.jws.WebParam;
import javax.jws.WebService;

import cn.com.git.udmp.common.model.DataObject;
import cn.com.git.udmp.component.batch.core.component.invoke.message.ServiceMessage;
import cn.com.git.udmp.core.logging.ILog;

/**
 * @description 服务调用接口
 * @author liuliang  
 * @date 2015年1月26日 下午3:09:58
 */
@WebService
public interface IServiceInvoker extends ILog{
    /**
     * @description 服务调用
     * @author liuliang 
     * @return 
     */
    public <T extends DataObject> void invoke(T t);

    public ServiceMessage test(@WebParam(name = "name") String t);

}
