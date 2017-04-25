package cn.com.git.udmp.modules.sys.entity;

import java.io.Serializable;

/**
 * 登录请求对象
 * 
 * @author tangyz
 *
 */
public class LoginRO implements Serializable {

	private static final long serialVersionUID = -1875509109946183736L;

	public static final String REGISTER_RO = "LoginRO";
	
	private String phone; // 手机号码
	
	private String passwd;	// 用户密码 
	
	private String jpushRegistrationID; //极光注册id
	
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getJpushRegistrationID() {
		return jpushRegistrationID;
	}

	public void setJpushRegistrationID(String jpushRegistrationID) {
		this.jpushRegistrationID = jpushRegistrationID;
	}


}
