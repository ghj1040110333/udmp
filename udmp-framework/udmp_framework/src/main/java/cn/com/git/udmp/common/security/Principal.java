package cn.com.git.udmp.common.security;

import cn.com.git.udmp.common.model.DataObject;
import cn.com.git.udmp.common.persistence.entity.UserEntity;
import cn.com.git.udmp.common.utils.SpringContextHolder;
import cn.com.git.udmp.common.web.IUDMPContext;
import cn.com.git.udmp.core.config.Global;


/**
 * 授权用户信息
 */
public class Principal extends DataObject{

	private static final long serialVersionUID = 1L;
	
	private String id; // 编号
	private String loginName; // 登录名
	private String name; // 姓名
	private boolean mobileLogin; // 是否手机登录
	
	IUDMPContext udmpContext = SpringContextHolder.getBean(Global.UDMP_CONTEXT_NAME);
	
//	private Map<String, Object> cacheMap;

	public Principal(UserEntity user, boolean mobileLogin) {
		this.id = user.getId();
		this.loginName = user.getLoginName();
		this.name = user.getName();
		this.mobileLogin = mobileLogin;
	}

	public String getId() {
		return id;
	}

	public String getLoginName() {
		return loginName;
	}

	public String getName() {
		return name;
	}

	public boolean isMobileLogin() {
		return mobileLogin;
	}

//	@JsonIgnore
//	public Map<String, Object> getCacheMap() {
//		if (cacheMap==null){
//			cacheMap = new HashMap<String, Object>();
//		}
//		return cacheMap;
//	}

	/**
	 * 获取SESSIONID
	 */
	public String getSessionid() {
		try{
			return (String) udmpContext.getSessionEntity().getId();
		}catch (Exception e) {
			return "";
		}
	}
	
	@Override
	public String toString() {
		return id;
	}

}
