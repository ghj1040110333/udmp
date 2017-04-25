

package cn.com.git.udmp.modules.sys.entity;

import org.hibernate.validator.constraints.Length;

import cn.com.git.udmp.common.persistence.DataEntity;

/**
 * 罚息管理Entity
 * @author 赵明辉
 * @version 2015-11-18
 */
public class SysBreachFineSubsection extends DataEntity<SysBreachFineSubsection> {
	
	private static final long serialVersionUID = 1L;
	private SysProductFine sysProductFineId;		// 罚息编号 父类
	private String startNum;		// 分段下限
	private String endNum;		// 分段上限
	private String breachFineAmt;		// 违约收取固定金额
	
	public SysBreachFineSubsection() {
		super();
	}

	public SysBreachFineSubsection(String id){
		super(id);
	}

	public SysBreachFineSubsection(SysProductFine sysProductFineId){
		this.sysProductFineId = sysProductFineId;
	}

	@Length(min=1, max=64, message="罚息编号长度必须介于 1 和 64 之间")
	public SysProductFine getSysProductFineId() {
		return sysProductFineId;
	}

	public void setSysProductFineId(SysProductFine sysProductFineId) {
		this.sysProductFineId = sysProductFineId;
	}
	
	@Length(min=0, max=10, message="分段下限长度必须介于 0 和 10 之间")
	public String getStartNum() {
		return startNum;
	}

	public void setStartNum(String startNum) {
		this.startNum = startNum;
	}
	
	@Length(min=0, max=10, message="分段上限长度必须介于 0 和 10 之间")
	public String getEndNum() {
		return endNum;
	}

	public void setEndNum(String endNum) {
		this.endNum = endNum;
	}
	
	public String getBreachFineAmt() {
		return breachFineAmt;
	}

	public void setBreachFineAmt(String breachFineAmt) {
		this.breachFineAmt = breachFineAmt;
	}
	
}