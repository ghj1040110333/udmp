package cn.com.git.udmp.common.web;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 
 * 手机端的请求返回结果VO
 * 
 * @author tangyz
 * 
 * @param <T>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MobileResultVO<T> extends ParentResultVO {

	private static final long serialVersionUID = -4026654833188890705L;

	private String redirect; 	// 手机应用要跳转的活动
	private T bizVO; 			// 业务查询结果VO

	public MobileResultVO() {
		super();
		this.setResult(SUCCESS);
	}

	public T getBizVO() {
		return bizVO;
	}

	public void setBizVO(T bizVO) {
		this.bizVO = bizVO;
	}

	public String getRedirect() {
		return redirect;
	}

	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}

	@JsonIgnore
	public boolean isSuccess() {
		return SUCCESS.equals(this.getResult());
	}

	@JsonIgnore
	public boolean isError() {
		return ERROR.equals(this.getResult());
	}
}
