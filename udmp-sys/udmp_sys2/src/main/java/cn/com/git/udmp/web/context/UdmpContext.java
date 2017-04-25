

package cn.com.git.udmp.web.context;

import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;

import cn.com.git.udmp.common.persistence.entity.DefaultSessionEntity;
import cn.com.git.udmp.common.persistence.entity.UserEntity;
import cn.com.git.udmp.common.security.Principal;
import cn.com.git.udmp.common.service.UserService;
import cn.com.git.udmp.common.utils.SpringContextHolder;
import cn.com.git.udmp.core.base.model.SessionEntity;
import cn.com.git.udmp.core.context.ENVContext;
import cn.com.git.udmp.modules.sys.utils.ShiroUtils;

/**
 * UDMP上下文对象
 * @description  UDMP上下文对象，是IUDMPContext的实现类，继承了ENVContext，Spring下的Bean名称为"udmpContext" 
 * @author Spring Cao
 * @date 2016年8月23日 上午11:16:17
 */
@Service
public class UdmpContext extends ENVContext implements IUDMPSysContext {

    /**
     * 
     * @title 获取当前用户
     * @description
     *
     * @see cn.com.git.udmp.common.web.IUDMPContext#getUser()
     * @return
     */
	public UserEntity getUser(){
		Principal principal = getPrincipal();
		if (principal != null){
			UserEntity user = getUserService().getUserById(principal.getId());
			return user;
		}
		// 如果没有登录，则返回实例化空的User对象。
		return getUserService().getUserById(principal.getId());
	}
	
	/**
	 * 
	 * @title 获取Shiro中的Session
	 * @description 若当前工程基于Shiro作为认证与授权框架，则可以通过此方法获取登陆后用户的Session
	 *
	 * @see cn.com.git.udmp.web.context.IUdmpSysContext#getShiroSession()
	 * @return
	 */
	public SessionEntity getSessionEntity(){
	    Session session = ShiroUtils.getSession();
	    SessionEntity entity = null;
	    if(session != null){
	        entity = new DefaultSessionEntity();
    	    entity.setId(session.getId() != null?(String)session.getId():"UDMPContext doesn't found Session ID");
    	    entity.setHost(session.getHost());
    	    entity.setTimeout(session.getTimeout());
    	    entity.setStartTimestamp(session.getStartTimestamp());
    	    entity.setLastAccessTime(session.getLastAccessTime());
	    }
	    return entity;
	}
	
	/**
     * 
     * @title 获取UDMP自定义的Session对象
     * @description 用于以后适配、扩展支持Shiro、Spring Security 等认证授权框架，并封装它们返回的Session对象
     *
     * @see cn.com.git.udmp.common.web.IUDMPContext#getSession()
     * @return
     */
	public Session getSession(){
        return ShiroUtils.getSession();
    }
	
	
	/**
	 * 
	 * @title 获取当前登录者对象
	 * @description
	 *
	 * @see cn.com.git.udmp.common.web.IUDMPContext#getPrincipal()
	 * @return
	 */
	public Principal getPrincipal(){
        return ShiroUtils.getPrincipal();
    }
//	public Principal getPrincipal(){
//        Principal principal = null;
//        try{
//            Subject subject = SecurityUtils.getSubject();
//            principal = (Principal)subject.getPrincipal();
//        }catch (UnavailableSecurityManagerException e) {
//            logger.error(e.getMessage());            
//        }catch (InvalidSessionException ex){
//            logger.error(ex.getMessage());
//        }
//        return principal;
//    }
	
	/** 用户系统服务 */
	private static UserService userService;
	
	/**
	 * 
	 * @title 获得用户系统服务对象
	 * @description 通过工具类获取Spring容器中的相关Bean
	 * 
	 * @return
	 */
	public static synchronized UserService getUserService(){
		if(userService == null){
			userService = SpringContextHolder.getBean(UserService.class);
		}
		logger.debug("+++++++++++++++userService {}+++++++++++++++",((userService == null)?" is Null" : "is Ready"));
		return userService;
	}
}
