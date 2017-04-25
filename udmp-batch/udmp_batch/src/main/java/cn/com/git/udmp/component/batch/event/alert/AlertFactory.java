package cn.com.git.udmp.component.batch.event.alert;

import cn.com.git.udmp.component.batch.common.constants.AlertType;
import cn.com.git.udmp.component.batch.event.alert.factory.CompAlertFactory;
import cn.com.git.udmp.component.batch.event.alert.factory.IAlertFactory;
import cn.com.git.udmp.component.batch.event.alert.factory.JmsAlertFactory;
import cn.com.git.udmp.component.batch.event.alert.factory.MailAlertFactory;
import cn.com.git.udmp.component.batch.event.alert.factory.NOPAlertFactory;
import cn.com.git.udmp.component.batch.event.alert.factory.SmsAlertFactory;
import cn.com.git.udmp.component.batch.event.alert.impl.IAlertProxy;

/**
 * 报警工厂
 * @description 报警工厂
 * @author bihb bihb_wb@newchinalife.com
 * @date 2015年4月13日 下午3:33:56  
 */
public class AlertFactory {
    /**
     * @Fields type : 报警工厂类型
     */ 
    private static String type = AlertType.JMS;
    /**
     * @Fields defaultType : 默认报警工厂类型，用于类型复原
     */ 
    private static final String DEFAULTTYPE = AlertType.JMS;
    /**
     * 私有构造函数，禁止new
     * <p>Title: 私有构造函数，禁止new</p> 
     * <p>Description: 私有构造函数，禁止new</p>  
     */
    private AlertFactory() {
    }

    /**
     * @description 获取报警代理对象
     * @version 1.0
     * @title 获取报警代理对象
     * @author bihb bihb_wb@newchinalife.com
     * @return 报警代理对象
     */
    public static IAlertProxy getAlertProxy() {
        return getFactory().getAlertProxy();
    }

    /**
     * @description 获取工厂对象,复杂的构建工厂过程由具体工厂类细化实现
     * @version 1.0
     * @title 获取工厂对象,复杂的构建工厂过程由具体工厂类细化实现
     * @author bihb bihb_wb@newchinalife.com
     * @return 工厂对象
     */
    private static IAlertFactory getFactory() {
        IAlertFactory factory = null;
        //根据报警代理对象类型，创建各类型的单例工厂
        if (AlertType.JMS.equalsIgnoreCase(AlertFactory.type)) {
            factory = JmsAlertFactory.getFactory();
        } else if (AlertType.MAIL.equalsIgnoreCase(AlertFactory.type))  {
            factory = MailAlertFactory.getFactory();
        } else if (AlertType.SMS.equalsIgnoreCase(AlertFactory.type))  {
            factory = SmsAlertFactory.getFactory();
        } else if (AlertType.COMP.equalsIgnoreCase(AlertFactory.type))  {
            factory = CompAlertFactory.getFactory();
        } else {
            factory = NOPAlertFactory.getFactory();
        }
        // 恢复默认工厂类型，否则factoryType静态共享对象被赋值改变，影响其他客户端下次获取默认工厂
        AlertFactory.type = DEFAULTTYPE;
        // 返回工厂对象
        return factory;
    }

    /**
     * 返回type的属性值
     * @description 返回type的属性值
     * @version 1.0
     * @title 返回type的属性值
     * @author bihb bihb_wb@newchinalife.com
     * @return 返回获取type的属性值
     */
    public static String getType() {
        return type;
    }

    /**
     * 给type属性赋值,cn.com.git.udmp.component.batch.common.constants.AlertType
     * @description 给type属性赋值,cn.com.git.udmp.component.batch.common.constants.AlertType
     * @version 1.0
     * @title 给type属性赋值
     * @author bihb bihb_wb@newchinalife.com
     * @param type type属性 
     */
    public static void setType(String type) {
        AlertFactory.type = type;
    }
}
