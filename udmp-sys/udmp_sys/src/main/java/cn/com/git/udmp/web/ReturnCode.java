
package cn.com.git.udmp.web;

/**
 * 
 * @author guosg
 *
 */
public class ReturnCode {
	public final static String SUCCESS ="S";
	public final static String ERROR = "E";

	private String domain;//
	private String message;//
	private String type;//
	private String code;//
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
}
