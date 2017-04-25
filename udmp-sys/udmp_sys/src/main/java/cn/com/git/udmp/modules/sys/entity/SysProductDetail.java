

package cn.com.git.udmp.modules.sys.entity;

import org.hibernate.validator.constraints.Length;

import cn.com.git.udmp.common.persistence.DataEntity;

/**
 * 产品信息Entity
 * @author 赵明辉
 * @version 2015-11-17
 */
public class SysProductDetail extends DataEntity<SysProductDetail> {
	
	private static final long serialVersionUID = 1L;
	private SysProduct sysProductId;		// 主表id 父类
	private String singleSumLimit;		// 销售单笔金额下限
	private String singleSumCap;		// 销售单笔金额上限
	private String amountUnit;		// 销售金额最小单位
	private String periodsNumber;		// 分期期数
	private String periodsRate;		// 分期频度
	private String rateYear;		// 执行利率
	
	
	public SysProductDetail() {
		super();
	}

	public SysProductDetail(String id){
		super(id);
	}

	public SysProductDetail(SysProduct sysProductId){
		this.sysProductId = sysProductId;
	}

	@Length(min=1, max=64, message="主表id长度必须介于 1 和 64 之间")
	public SysProduct getSysProductId() {
		return sysProductId;
	}

	public void setSysProductId(SysProduct sysProductId) {
		this.sysProductId = sysProductId;
	}
	
	public String getSingleSumLimit() {
		return singleSumLimit;
	}

	public void setSingleSumLimit(String singleSumLimit) {
		this.singleSumLimit = singleSumLimit;
	}
	
	public String getSingleSumCap() {
		return singleSumCap;
	}

	public void setSingleSumCap(String singleSumCap) {
		this.singleSumCap = singleSumCap;
	}
	
	public String getAmountUnit() {
		return amountUnit;
	}

	public void setAmountUnit(String amountUnit) {
		this.amountUnit = amountUnit;
	}
	
	@Length(min=1, max=3, message="分期期数长度必须介于 1 和 3 之间")
	public String getPeriodsNumber() {
		return periodsNumber;
	}

	public void setPeriodsNumber(String periodsNumber) {
		this.periodsNumber = periodsNumber;
	}
	
	@Length(min=0, max=3, message="分期频度长度必须介于 0 和 3 之间")
	public String getPeriodsRate() {
		return periodsRate;
	}

	public void setPeriodsRate(String periodsRate) {
		this.periodsRate = periodsRate;
	}
	
	public String getRateYear() {
		return rateYear;
	}

	public void setRateYear(String rateYear) {
		this.rateYear = rateYear;
	}
	
}