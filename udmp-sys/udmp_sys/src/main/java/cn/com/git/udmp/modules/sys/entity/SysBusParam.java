

package cn.com.git.udmp.modules.sys.entity;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import com.google.common.collect.Lists;

import cn.com.git.udmp.common.persistence.DataEntity;

/**
 * 业务参数管理Entity
 * @author xwj
 * @version 2015-11-10
 */
public class SysBusParam extends DataEntity<SysBusParam> {
	
	private static final long serialVersionUID = 1L;
	private String userPasswordStrength;		// 用户密码强度
	private String certificateType;		// 证件类型
	private String custPasswordStrength;  // 客户密码强度
	private Integer custPasschangeIntDay;  // 客户密码更换间隔天数
	private Integer userPasschangeIntDay;  // 用户密码更换间隔天数
	private Integer userPasswordMaxrWrongTime;  // 用户密码最大错误次数
	private Integer custPasswordMaxrWrongTime;  // 客户密码最大错误次数
	private Integer cycleCreditProIongestPeriod;  // 循环信用产品最长有效期（年）
	private Integer cycleCreditProDefaultPeriod;  // 循环信用产品默认有效期（年）
	private String regAgreOnlineVnum;  // 在用注册协议版本号
	private Double onlyoneCreditLimit;   // 单一客户最高授信限额
	private Double whitelistLimit;       //     白名单客户最高授信限额
	
	private List<String> certificateTypes;//证件类型链表
	
	private String agreementVersionId;//注册协议版本ID 取自表“sys_reg_agreement”
	private String agreementVersionNo;//注册协议版本号 取自表“sys_reg_agreement”
	
	private String onlyoneCreditLimitStr;   // 单一客户最高授信限额
	private String whitelistLimitStr;       //     白名单客户最高授信限额
	
	public SysBusParam() {
		super();
	}

	public SysBusParam(String id){
		super(id);
	}

	@Length(min=1, max=2, message="密码强度长度必须介于 1 和 2 之间")
	public String getPasswordStrength() {
		return userPasswordStrength;
	}

	public void setPasswordStrength(String userPasswordStrength) {
		this.userPasswordStrength = userPasswordStrength;
	}
	
	@Length(min=1, max=2, message="证件类型长度必须介于 1 和 2 之间")
	public String getCertificateType() {
		certificateType = StringUtils.join(certificateTypes, ",");
		return certificateType;
	}

	public void setCertificateType() {
		this.certificateType = StringUtils.join(certificateTypes, ",");
	}

	public List<String> getCertificateTypes() {
		if (certificateType != null){
			String[] cfs = StringUtils.split(certificateType, ",");
			certificateTypes = Lists.newArrayList(cfs);
		}
		return certificateTypes;
	}

	public void setCertificateTypes(List<String> certificateTypes) {
		this.certificateTypes = certificateTypes;
	}

	public String getUserPasswordStrength() {
		return userPasswordStrength;
	}

	public void setUserPasswordStrength(String userPasswordStrength) {
		this.userPasswordStrength = userPasswordStrength;
	}

	public String getCustPasswordStrength() {
		return custPasswordStrength;
	}

	public void setCustPasswordStrength(String custPasswordStrength) {
		this.custPasswordStrength = custPasswordStrength;
	}

	public Integer getCustPasschangeIntDay() {
		return custPasschangeIntDay;
	}

	public void setCustPasschangeIntDay(Integer custPasschangeIntDay) {
		this.custPasschangeIntDay = custPasschangeIntDay;
	}

	public Integer getUserPasschangeIntDay() {
		return userPasschangeIntDay;
	}

	public void setUserPasschangeIntDay(Integer userPasschangeIntDay) {
		this.userPasschangeIntDay = userPasschangeIntDay;
	}

	public Integer getUserPasswordMaxrWrongTime() {
		return userPasswordMaxrWrongTime;
	}

	public void setUserPasswordMaxrWrongTime(Integer userPasswordMaxrWrongTime) {
		this.userPasswordMaxrWrongTime = userPasswordMaxrWrongTime;
	}

	public Integer getCustPasswordMaxrWrongTime() {
		return custPasswordMaxrWrongTime;
	}

	public void setCustPasswordMaxrWrongTime(Integer custPasswordMaxrWrongTime) {
		this.custPasswordMaxrWrongTime = custPasswordMaxrWrongTime;
	}

	public Integer getCycleCreditProIongestPeriod() {
		return cycleCreditProIongestPeriod;
	}

	public void setCycleCreditProIongestPeriod(Integer cycleCreditProIongestPeriod) {
		this.cycleCreditProIongestPeriod = cycleCreditProIongestPeriod;
	}

	public Integer getCycleCreditProDefaultPeriod() {
		return cycleCreditProDefaultPeriod;
	}

	public void setCycleCreditProDefaultPeriod(Integer cycleCreditProDefaultPeriod) {
		this.cycleCreditProDefaultPeriod = cycleCreditProDefaultPeriod;
	}

	public String getRegAgreOnlineVnum() {
		return regAgreOnlineVnum;
	}

	public void setRegAgreOnlineVnum(String regAgreOnlineVnum) {
		this.regAgreOnlineVnum = regAgreOnlineVnum;
	}

	public String getAgreementVersionId() {
		return agreementVersionId;
	}

	public void setAgreementVersionId(String agreementVersionId) {
		this.agreementVersionId = agreementVersionId;
	}

	public String getAgreementVersionNo() {
		return agreementVersionNo;
	}

	public void setAgreementVersionNo(String agreementVersionNo) {
		this.agreementVersionNo = agreementVersionNo;
	}

	public String getOnlyoneCreditLimitStr() {
		return onlyoneCreditLimitStr;
	}

	public void setOnlyoneCreditLimitStr(String onlyoneCreditLimitStr) {
		this.onlyoneCreditLimitStr = onlyoneCreditLimitStr;
	}

	public Double getOnlyoneCreditLimit() {
		return onlyoneCreditLimit;
	}

	public void setOnlyoneCreditLimit(Double onlyoneCreditLimit) {
		this.onlyoneCreditLimit = onlyoneCreditLimit;
	}

	public Double getWhitelistLimit() {
		return whitelistLimit;
	}

	public void setWhitelistLimit(Double whitelistLimit) {
		 this.whitelistLimit = whitelistLimit;
	}
	
	public String getWhitelistLimitStr() {
		return whitelistLimitStr;
	}

	public void setWhitelistLimitStr(String whitelistLimitStr) {
		this.whitelistLimitStr = whitelistLimitStr;
	}
	
	
}