package cn.com.git.udmp.test.builder;

import java.io.Serializable;

import cn.com.git.udmp.core.logging.ILog;

/**
 * 流式模式的数据绑定Builder
 * @description 
 * @author Spring Cao 
 * @date 2016年8月26日 下午2:50:18
 */
public interface DataBindBuilder extends ILog,Serializable{
    interface Builder{
        Builder name(String name);
        Builder age(int age);
        Builder address(String addr);
        DataBind build();
        String echo();
    }
}
