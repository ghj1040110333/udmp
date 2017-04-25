//package cn.com.git.udmp.component.batch.core.component.invoke.impl;
//
//import javax.jws.WebService;
//
//import org.slf4j.Logger;
//
//import cn.com.git.udmp.common.logging.LoggerFactory;
//import cn.com.git.udmp.component.batch.core.component.invoke.AbstractServiceInvoker;
//import cn.com.git.udmp.component.batch.core.component.invoke.message.ServiceMessage;
//import cn.com.git.udmp.common.model.DataObject;
//
///**
// * @description webservice的服务调用类
// * @author liuliang liuliang_wb@newchina.live
// * @date 2015年1月26日 下午3:14:10
// */
//@WebService(endpointInterface = "cn.com.git.udmp.component.batch.core.component.invoke.IServiceInvoker")
//public class WsInvoker extends AbstractServiceInvoker {
//    private Logger logger = LoggerFactory.getLogger();
//
//    @Override
//    public <T extends DataObject> void invoke(T t) {
//    }
//
//    @Override
//    public ServiceMessage test(String t) {
//        // TODO Auto-generated method stub
//        logger.debug("hello:" + t);
//        DataObject data = new DataObject();
//        data.set("status", t);
//        handle(data);
//        ServiceMessage message = new ServiceMessage();
//        message.setName(t);
//        message.setAge(t.length());
//        message.setAddress(new String[] { t, t, t });
//        return message;
//    }
//}
