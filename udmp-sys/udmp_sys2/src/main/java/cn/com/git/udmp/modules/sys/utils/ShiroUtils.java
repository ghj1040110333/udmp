

package cn.com.git.udmp.modules.sys.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import cn.com.git.udmp.common.security.Principal;
import cn.com.git.udmp.core.logging.ILog;

/** 
 * Shiro工具类
 * @description  提供对于Shiro的Session等内容的获取
 * @author Spring Cao 
 * @date 2016年8月24日 下午5:35:15  
*/
public class ShiroUtils implements ILog{
  
    /**
     * 
     * @title 从 Shiro中获取Subject
     * @description
     * 
     * @return
     */
    public static Subject getSubject(){
        return SecurityUtils.getSubject();
    }
    
    /**
     * 
     * @title 从Shiro中获取会话
     * @description
     * 
     * @return
     */
    private static Session rtnSession(){
        return rtnSession(false);
    }
    
    /**
     * 
     * @title 从Shiro中获取会话
     * @description
     * 
     * @param isCreate
     * @return
     */
    private static Session rtnSession(boolean isCreate){
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession(false);
        return session;
    }
    
    /**
     * 
     * @title 获取Shiro中的Session
     * @description 若当前工程基于Shiro作为认证与授权框架，则可以通过此方法获取登陆后用户的Session
     *
     * @see cn.com.git.udmp.web.context.IUdmpSysContext#getShiroSession()
     * @return
     */
    public static Session getSession(){
        Session session = null;
        try{
            Subject subject = getSubject();
            session = rtnSession(false);
            if (session == null){
                session = subject.getSession();
            }
        }catch (InvalidSessionException e){
            logger.error(e.getMessage());
        }
        return session;
    }
    
    /**
     * 
     * @title 获取当前登录者对象
     * @description
     * 
     * @return
     */
    public static Principal getPrincipal(){
        Principal principal = null;
        try{
            Subject subject = getSubject();
            principal = (Principal)subject.getPrincipal();
        }catch (UnavailableSecurityManagerException e) {
            logger.error(e.getMessage());            
        }catch (InvalidSessionException ex){
            logger.error(ex.getMessage());
        }
        return principal;
    }
}
