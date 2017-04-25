

package cn.com.git.udmp.web.context;

import org.apache.shiro.session.Session;

import cn.com.git.udmp.common.web.IUDMPContext;

/**
 * UDMP后台管理相关上下文抽象接口
 * @description 主要是提供udmp_sys所用的Context，接口集成IUDMPContext，并提供获取Shiro Session的方法声明
 * @author Spring Cao
 * @date 2016年8月25日 下午1:41:52
 */
public abstract interface IUDMPSysContext extends IUDMPContext{
    /**
     * 
     * @title 获取Session
     * @description 获取Shiro Session
     * 
     * @return
     */
    public Session getSession();
}
