

package cn.com.git.udmp.modules.sys.security;

import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.git.udmp.common.security.Principal;
import cn.com.git.udmp.common.servlet.ValidateCodeServlet;
import cn.com.git.udmp.common.utils.Encodes;
import cn.com.git.udmp.common.utils.SpringContextHolder;
import cn.com.git.udmp.common.web.Servlets;
import cn.com.git.udmp.core.config.Global;
import cn.com.git.udmp.modules.sys.entity.Menu;
import cn.com.git.udmp.modules.sys.entity.Role;
import cn.com.git.udmp.modules.sys.entity.User;
import cn.com.git.udmp.modules.sys.service.SystemService;
import cn.com.git.udmp.modules.sys.utils.LogUtils;
import cn.com.git.udmp.modules.sys.utils.UserUtils;
import cn.com.git.udmp.modules.sys.web.LoginController;
import cn.com.git.udmp.web.context.IUDMPSysContext;

/**
 * 系统安全认证实现类
 * @description 提供对于Shiro认证的扩展
 * @author Spring Cao 
 * @date 2016年8月25日 下午1:44:17
 */
@Service
//@DependsOn({"userDao","roleDao","menuDao"})
public class SystemAuthorizingRealm extends AuthorizingRealm {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private SystemService systemService;
	
	@Autowired
	IUDMPSysContext udmpSysContext;
	
	/**
     * 
     * @title 获取授权信息
     * @description 如果缓存中存在，则直接从缓存中获取，佛足额就重新获取，登录成功后才会调用此方法
     *              2017-1-16 新增
     *
     * @see org.apache.shiro.realm.AuthorizingRealm#getAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo getAuthorizationInfo(PrincipalCollection principals){
        if(principals == null){
            return null;
        }
        AuthorizationInfo info = null;
        info = (AuthorizationInfo)UserUtils.getCache(UserUtils.CACHE_AUTH_INFO);
        if(info == null){
            info = doGetAuthorizationInfo(principals);
            if(info != null){
                UserUtils.putCache(UserUtils.CACHE_AUTH_INFO, info);
            }
        }
        return info;
    }

	/**
	 * 
	 * @title 认证回调函数, 登录时调用
	 * @description
	 *
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 * @param authcToken
	 * @return
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		int activeSessionSize = getSystemService().getSessionDao().getActiveSessions(false).size();
		if (logger.isDebugEnabled()){
			logger.debug("login submit, active session size: {}, username: {}", activeSessionSize, token.getUsername());
		}
		
		// 校验登录验证码
		if (LoginController.isValidateCodeLogin(token.getUsername(), false, false)){
			Session session = udmpSysContext.getSession();
			String code = (String)session.getAttribute(ValidateCodeServlet.VALIDATE_CODE);
			if (token.getCaptcha() == null || !token.getCaptcha().toUpperCase().equals(code)){
				throw new AuthenticationException("msg:验证码错误, 请重试.");
			}
		}
		
		// 校验用户名密码
		User user = getSystemService().getUserByLoginName(token.getUsername());
		if (user != null) {
			if (Global.NO.equals(user.getLoginFlag())){
				throw new AuthenticationException("msg:该帐号已禁止登录.");
			}
			byte[] salt = Encodes.decodeHex(user.getPassword().substring(0,16));
			return new SimpleAuthenticationInfo(new Principal(user, token.isMobileLogin()), 
					user.getPassword().substring(16), ByteSource.Util.bytes(salt), getName());
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @title 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
	 * @description
	 *
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 * @param principals
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Principal principal = (Principal) getAvailablePrincipal(principals);
		// 获取当前已登录的用户
		if (!Global.TRUE.equals(Global.getConfig("user.multiAccountLogin"))){
			Collection<Session> sessions = getSystemService().getSessionDao().getActiveSessions(true, principal, udmpSysContext.getSession());
			if (sessions.size() > 0){
				// 如果是登录进来的，则踢出已在线用户
				if (UserUtils.getSubject().isAuthenticated()){
					for (Session session : sessions){
						getSystemService().getSessionDao().delete(session);
					}
				}
				// 记住我进来的，并且当前用户已登录，则退出当前用户提示信息。
				else{
					UserUtils.getSubject().logout();
					throw new AuthenticationException("msg:账号已在其它地方登录，请重新登录。");
				}
			}
		}
		User user = getSystemService().getUserByLoginName(principal.getLoginName());
		if (user != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			List<Menu> list = UserUtils.getMenuList();
			for (Menu menu : list){
				if (StringUtils.isNotBlank(menu.getPermission())){
					// 添加基于Permission的权限信息
					for (String permission : StringUtils.split(menu.getPermission(),",")){
						info.addStringPermission(permission);
					}
				}
			}
			// 添加用户权限
			info.addStringPermission("user");
			// 添加用户角色信息
			for (Role role : user.getRoleList()){
				info.addRole(role.getEnname());
			}
			// 更新登录IP和时间
			getSystemService().updateUserLoginInfo(user);
			// 记录登录日志
			LogUtils.saveLog(Servlets.getRequest(), "系统登录");
			return info;
		} else {
			return null;
		}
	}
	
	/**
	 * 
	 * @title 校验权限
	 * @description
	 *
	 * @see org.apache.shiro.realm.AuthorizingRealm#checkPermission(org.apache.shiro.authz.Permission, org.apache.shiro.authz.AuthorizationInfo)
	 * @param permission
	 * @param info
	 */
	@Override
	protected void checkPermission(Permission permission, AuthorizationInfo info) {
		authorizationValidate(permission);
		super.checkPermission(permission, info);
	}
	
	/**
	 * 
	 * @title 是否具备权限
	 * @description
	 *
	 * @see org.apache.shiro.realm.AuthorizingRealm#isPermitted(java.util.List, org.apache.shiro.authz.AuthorizationInfo)
	 * @param permissions
	 * @param info
	 * @return
	 */
	@Override
	protected boolean[] isPermitted(List<Permission> permissions, AuthorizationInfo info) {
		if (permissions != null && !permissions.isEmpty()) {
            for (Permission permission : permissions) {
        		authorizationValidate(permission);
            }
        }
		return super.isPermitted(permissions, info);
	}
	
	/**
	 * 
	 * @title 是否具备权限
	 * @description
	 *
	 * @see org.apache.shiro.realm.AuthorizingRealm#isPermitted(org.apache.shiro.subject.PrincipalCollection, org.apache.shiro.authz.Permission)
	 * @param principals
	 * @param permission
	 * @return
	 */
	@Override
	public boolean isPermitted(PrincipalCollection principals, Permission permission) {
		authorizationValidate(permission);
		return super.isPermitted(principals, permission);
	}
	
	/**
	 * 
	 * @title 是否具备权限
	 * @description
	 *
	 * @see org.apache.shiro.realm.AuthorizingRealm#isPermittedAll(java.util.Collection, org.apache.shiro.authz.AuthorizationInfo)
	 * @param permissions
	 * @param info
	 * @return
	 */
	@Override
	protected boolean isPermittedAll(Collection<Permission> permissions, AuthorizationInfo info) {
		if (permissions != null && !permissions.isEmpty()) {
            for (Permission permission : permissions) {
            	authorizationValidate(permission);
            }
        }
		return super.isPermittedAll(permissions, info);
	}
	
	/**
	 * 
	 * @title 授权验证方法
	 * @deprecated
	 * @description
	 * 
	 * @param permission
	 */
	private void authorizationValidate(Permission permission){
		// 模块授权预留接口
	}
	
	/**
	 * 
	 * @title 设定密码校验的Hash算法与迭代次数
	 * @description
	 *
	 */
	@PostConstruct
	public void initCredentialsMatcher() {
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(SystemService.HASH_ALGORITHM);
		matcher.setHashIterations(SystemService.HASH_INTERATIONS);
		setCredentialsMatcher(matcher);
	}
	
	
//	/**
//	 * 清空用户关联权限认证，待下次使用时重新加载
//	 */
//	public void clearCachedAuthorizationInfo(Principal principal) {
//		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
//		clearCachedAuthorizationInfo(principals);
//	}

	
	/**
	 * 
	 * @title 清空用户关联权限认证，待下次使用时重新加载
	 * @description
	 * 
	 * @param principal
	 */
    public void clearCachedAuthorizationInfo(String principal) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
        clearCachedAuthorizationInfo(principals);
    }
	
    /**
	 * 
	 * @Deprecated 不需要清空，授权缓存保存到session中
	 */
	@Deprecated
	public void clearAllCachedAuthorizationInfo() {
//		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
//		if (cache != null) {
//			for (Object key : cache.keys()) {
//				cache.remove(key);
//			}
//		}
	}

	/**
	 * 
	 * @title 获取系统业务对象
	 * @description
	 * 
	 * @return
	 */
	public SystemService getSystemService() {
		if (systemService == null){
			systemService = SpringContextHolder.getBean(SystemService.class);
		}
		return systemService;
	}
}
