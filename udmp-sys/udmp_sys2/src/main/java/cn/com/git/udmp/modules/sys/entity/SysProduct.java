

package cn.com.git.udmp.modules.sys.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.List;
import com.google.common.collect.Lists;

import cn.com.git.udmp.common.persistence.DataEntity;

/**
 * 产品信息Entity
 * @author 赵明辉
 * @version 2015-11-17
 */
public class SysProduct extends DataEntity<SysProduct> {
	
	private static final long serialVersionUID = 1L;
	private String productNum;		// 产品编号
	private String productName;		// 产品名称
	private String productAbstract; //产品简介
	private String productContent;		// 产品描述
	private String appProductContent;//App产品描述
	private String productState;		// 产品状态
	private Date enableTime;		// 上线时间
	private Date disableTime;		// 下线时间
	private String salesArea;		// 销售区域
	private String salesChannel;		// 销售渠道
	private String salesCustomBase;		// 销售客户群组
	private String salesCustomLevel;		// 销售客户级别
	private String repaymentWay;		// 还款方式
	private String earlyRepayment;		// 提前还款标识
	private String sysProductPrepaymentId;		// 提前还款规则编号
	private String guarantyStyle;	//担保方式
	private String sysProductFineId;//罚息计息标准
	private String cardType;		// 允许入账银行卡类型
	private String contractInfoNo;//合同文本版本号
	private String loyaltyPlanNum;		// 积分计划号
	private String recommendedProductFlag;//是否首推产品
	private List<SysProductDetail> sysProductDetailList = Lists.newArrayList();		// 子表列表
	
	public SysProduct() {
		super();
	}

	public SysProduct(String id){
		super(id);
	}

	public String getAppProductContent() {
		return appProductContent;
	}

	public void setAppProductContent(String appProductContent) {
		this.appProductContent = appProductContent;
	}

	public String getRecommendedProductFlag() {
		return recommendedProductFlag;
	}

	public void setRecommendedProductFlag(String recommendedProductFlag) {
		this.recommendedProductFlag = recommendedProductFlag;
	}

	@Length(min=1, max=40, message="产品编号长度必须介于 1 和 40 之间")
	public String getProductNum() {
		return productNum;
	}

	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}
	
	@Length(min=0, max=40, message="产品名称长度必须介于 0 和 40 之间")
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	@Length(min=0, max=2, message="产品状态长度必须介于 0 和 2 之间")
	public String getProductState() {
		return productState;
	}

	public void setProductState(String productState) {
		this.productState = productState;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEnableTime() {
		return enableTime;
	}

	public void setEnableTime(Date enableTime) {
		this.enableTime = enableTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDisableTime() {
		return disableTime;
	}

	public void setDisableTime(Date disableTime) {
		this.disableTime = disableTime;
	}
	
	@Length(min=0, max=500, message="销售区域长度必须介于 0 和 500 之间")
	public String getSalesArea() {
		return salesArea;
	}

	public void setSalesArea(String salesArea) {
		this.salesArea = salesArea;
	}
	
	@Length(min=0, max=8, message="销售渠道长度必须介于 0 和 8 之间")
	public String getSalesChannel() {
		return salesChannel;
	}

	public void setSalesChannel(String salesChannel) {
		this.salesChannel = salesChannel;
	}
	
	@Length(min=0, max=8, message="销售客户群组长度必须介于 0 和 8 之间")
	public String getSalesCustomBase() {
		return salesCustomBase;
	}

	public void setSalesCustomBase(String salesCustomBase) {
		this.salesCustomBase = salesCustomBase;
	}
	
	@Length(min=0, max=20, message="销售客户级别长度必须介于 0 和 20 之间")
	public String getSalesCustomLevel() {
		return salesCustomLevel;
	}

	public void setSalesCustomLevel(String salesCustomLevel) {
		this.salesCustomLevel = salesCustomLevel;
	}
	
	
	@Length(min=0, max=10, message="还款方式长度必须介于 0 和 10之间")
	public String getRepaymentWay() {
		return repaymentWay;
	}

	public void setRepaymentWay(String repaymentWay) {
		this.repaymentWay = repaymentWay;
	}
	
	@Length(min=0, max=2, message="提前还款标识长度必须介于 0 和 2 之间")
	public String getEarlyRepayment() {
		return earlyRepayment;
	}

	public void setEarlyRepayment(String earlyRepayment) {
		this.earlyRepayment = earlyRepayment;
	}
	
	
	@Length(min=0, max=10, message="允许入账银行卡类型长度必须介于 0 和 10 之间")
	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	
	@Length(min=0, max=40, message="积分计划号长度必须介于 0 和 40 之间")
	public String getLoyaltyPlanNum() {
		return loyaltyPlanNum;
	}

	public void setLoyaltyPlanNum(String loyaltyPlanNum) {
		this.loyaltyPlanNum = loyaltyPlanNum;
	}
	
	public List<SysProductDetail> getSysProductDetailList() {
		return sysProductDetailList;
	}

	public void setSysProductDetailList(List<SysProductDetail> sysProductDetailList) {
		this.sysProductDetailList = sysProductDetailList;
	}

	public String getSysProductPrepaymentId() {
		return sysProductPrepaymentId;
	}

	public void setSysProductPrepaymentId(String sysProductPrepaymentId) {
		this.sysProductPrepaymentId = sysProductPrepaymentId;
	}

	public String getGuarantyStyle() {
		return guarantyStyle;
	}

	public void setGuarantyStyle(String guarantyStyle) {
		this.guarantyStyle = guarantyStyle;
	}

	public String getContractInfoNo() {
		return contractInfoNo;
	}

	public void setContractInfoNo(String contractInfoNo) {
		this.contractInfoNo = contractInfoNo;
	}

	public String getSysProductFineId() {
		return sysProductFineId;
	}

	public void setSysProductFineId(String sysProductFineId) {
		this.sysProductFineId = sysProductFineId;
	}

	public String getProductContent() {
		return productContent;
	}

	public void setProductContent(String productContent) {
		this.productContent = productContent;
	}

	public String getProductAbstract() {
		return productAbstract;
	}

	public void setProductAbstract(String productAbstract) {
		this.productAbstract = productAbstract;
	}
	
}