package cn.com.git.udmp.component.batch.event.alert.impl;

import org.springframework.scheduling.annotation.Async;

import cn.com.git.udmp.component.batch.event.alert.AlertMsg;

/**
 * 报警代理对象接口
 * @description 报警代理对象接口
 * @author bihb bihb_wb@newchinalife.com
 * @date 2015年4月13日 下午3:37:51  
 */
public interface IAlertProxy {
    /**
     * @Fields TIMES : 转化为毫秒的倍数
     */ 
    public final int TIMES = 1000;
    /**
     * @Fields TEMPLATE_VAR_HOLDER : velocity模板中的变量占位符名称
     */ 
    public final String TEMPLATE_VAR_HOLDER = "alert";
    /**
     * 报警操作，使用异步线程进行报警操作,对应异步线程池执行器的beanid=batchAlertProxyExecutor，
     * 该接口下所有实现类的都异步执行，不获取返回值
     * @description 报警操作
     * @version 1.0
     * @title   报警操作
     * @author bihb bihb_wb@newchinalife.com
     * @param msg 报警消息
     */
    @Async(value = "batchAlertProxyExecutor")
    public void alert(AlertMsg msg);

}
