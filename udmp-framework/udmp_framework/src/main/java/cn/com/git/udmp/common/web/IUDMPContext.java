

package cn.com.git.udmp.common.web;

import cn.com.git.udmp.common.persistence.entity.UserEntity;
import cn.com.git.udmp.common.security.Principal;
import cn.com.git.udmp.core.base.model.SessionEntity;

/** 
 * UDMP上下文接口
 * @description 
 *  主要是为UDMPContext提供操作行为的定义，实现类UDMPContext除了实现这个借口外，还要继承ENVContext类来获得更多的系统级上下文
 * @see ENVContext,UDMPContext,IUDMPContext,IUDMPSysContext
 * @author Spring Cao
 * @date 2016年8月23日 上午10:52:16  
*/
public interface IUDMPContext{
    
    /**
     * 
     * @title 获得用户
     * @description 获得用户对象，是UserEntity的实现类
     * 
     * @return 取不到返回 new User()
     */
    public UserEntity getUser();
    
    /**
     * 
     * @title 获得用户Session
     * @description 获取用户Session对象，需要使用已经封装好的ISession,其默认实现类ReadOnlySession
     * 
     * @return Session 
     */
    public SessionEntity getSessionEntity(); //返回值用ISession，udmp_sys中提供一个UdmpReadOnlySession来集成ReadOnlySessino，并通过一个Service来返回这个对象
    
    /**
     * 
     * @title
     * @description
     * 
     * @return
     */
    public Principal getPrincipal();
}
