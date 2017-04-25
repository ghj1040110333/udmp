package cn.com.git.udmp.test.batch.event.alert;

import org.junit.Test;

import cn.com.git.udmp.common.utils.testsupport.AbstractTest;
import cn.com.git.udmp.component.batch.common.constants.AlertType;
import cn.com.git.udmp.component.batch.event.alert.AlertFactory;
import cn.com.git.udmp.component.batch.event.alert.impl.IAlertProxy;
import junit.framework.Assert;

/**
 * @description 测试报警抽象工厂
 * @author bihb bihb_wb@newchinalife.com
 * @date 2015年4月14日 上午11:18:48  
 */
public class AlertFactoryTest extends AbstractTest {
    /**
     * @Fields DELAY : 延迟停止，等待控制台输出
     */ 
    private static final int DELAY = 8 * 1000;

    /**
     * 测试获取报警代理对象
     * @description 测试获取报警代理对象
     * @version 1.0
     * @title 测试获取报警代理对象
     * @author bihb bihb_wb@newchinalife.com 
     */
    @Test
    public void testGetAlertProxy() {
        //测试创建各种类型的报警代理对象
        AlertFactory.setType(AlertType.COMP);
        IAlertProxy alertProxy = AlertFactory.getAlertProxy();
        //测试能够通过springbean获取代理对象
        Assert.assertNotNull(alertProxy);
        
        AlertFactory.setType(AlertType.JMS);
        alertProxy = AlertFactory.getAlertProxy();
        Assert.assertNotNull(alertProxy);
        
        AlertFactory.setType(AlertType.MAIL);
        alertProxy = AlertFactory.getAlertProxy();
        Assert.assertNotNull(alertProxy);
        
        AlertFactory.setType(AlertType.NOP);
        alertProxy = AlertFactory.getAlertProxy();
        Assert.assertNotNull(alertProxy);
        
        AlertFactory.setType(AlertType.SMS);
        alertProxy = AlertFactory.getAlertProxy();
        Assert.assertNotNull(alertProxy);
        
        //测试单例模式获取代理对象，两次的对象不同
        IAlertProxy alertProxyFirst = AlertFactory.getAlertProxy();
        IAlertProxy alertProxySecond = AlertFactory.getAlertProxy();
        Assert.assertNotSame(alertProxyFirst.hashCode(), alertProxySecond.hashCode());
        
        //测试默认报警操作
        AlertFactory.setType(AlertType.COMP);
        IAlertProxy alertProxyObj = AlertFactory.getAlertProxy();
        logger.info("聚合对象hash值：" + alertProxyObj.hashCode());
        alertProxyObj.alert(null);
        logger.info("调用完毕，用异步线程执行报警操作");
        //再次调用，测试原型聚合对象里的原型list对象
        AlertFactory.setType(AlertType.COMP);
        alertProxyObj = AlertFactory.getAlertProxy();
        logger.info("聚合对象hash值：" + alertProxyObj.hashCode());
        alertProxyObj.alert(null);
        logger.info("第二次调用完毕，用异步线程执行报警操作");
        try {
            //暂时不终止进程，等待控制台的其他输出
            Thread.sleep(DELAY * 2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }
    /**
     * 测试获取报警代理对象类型
     * @description 测试获取报警代理对象类型
     * @version 1.0
     * @title   测试获取报警代理对象类型
     * @author bihb bihb_wb@newchinalife.com 
     */
    @Test
    public void testGetType() {
        AlertFactory.setType(AlertType.COMP);
        Assert.assertEquals(AlertType.COMP, AlertFactory.getType());
    }
    
    /**
     * 测试报警代理对象类型设置
     * @description 测试报警代理对象类型设置
     * @version 1.0
     * @title   测试报警代理对象类型设置
     * @author bihb bihb_wb@newchinalife.com 
     */
    @Test
    public void testSetType() {
        AlertFactory.setType(AlertType.JMS);
        Assert.assertEquals(AlertType.JMS, AlertFactory.getType());
    }
}
