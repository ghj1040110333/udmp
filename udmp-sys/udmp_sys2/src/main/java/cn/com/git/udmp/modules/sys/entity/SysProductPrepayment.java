

package cn.com.git.udmp.modules.sys.entity;

import org.hibernate.validator.constraints.Length;

import cn.com.git.udmp.common.persistence.DataEntity;

/**
 * 提前还款信息Entity
 * @author 赵明辉
 * @version 2015-11-18
 */
public class SysProductPrepayment extends DataEntity<SysProductPrepayment> {
	
	private static final long serialVersionUID = 1L;
	private String description;         //提前还款描述
	private String earlyRepaymentWay;		// 提前还款方式
	private String earlyRepaymentSubject;		// 提前还款计息标准
	private String earlyRepaymentRate;		// 提前还款执行利率
	private String earlyRepaymentAmt;		// 提前还款固定金额
	
	public SysProductPrepayment() {
		super();
	}

	public SysProductPrepayment(String id){
		super(id);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Length(min=0, max=2, message="提前还款方式长度必须介于 0 和 2 之间")
	public String getEarlyRepaymentWay() {
		return earlyRepaymentWay;
	}

	public void setEarlyRepaymentWay(String earlyRepaymentWay) {
		this.earlyRepaymentWay = earlyRepaymentWay;
	}
	
	@Length(min=0, max=5, message="提前还款计息标准长度必须介于 0 和 5 之间")
	public String getEarlyRepaymentSubject() {
		return earlyRepaymentSubject;
	}

	public void setEarlyRepaymentSubject(String earlyRepaymentSubject) {
		this.earlyRepaymentSubject = earlyRepaymentSubject;
	}
	
	public String getEarlyRepaymentRate() {
		return earlyRepaymentRate;
	}

	public void setEarlyRepaymentRate(String earlyRepaymentRate) {
		this.earlyRepaymentRate = earlyRepaymentRate;
	}
	
	public String getEarlyRepaymentAmt() {
		return earlyRepaymentAmt;
	}

	public void setEarlyRepaymentAmt(String earlyRepaymentAmt) {
		this.earlyRepaymentAmt = earlyRepaymentAmt;
	}
	
}