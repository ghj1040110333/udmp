package cn.com.git.udmp.common.utils.biz;

import org.springframework.beans.factory.annotation.Value;

/**
 * 业务流水号配置信息
 * @author guosg
 *
 */
public class BusinessSeqConfigInfo {
	@Value("${business.sys_no}")
	private String sysNo;				//渠道系统编号	3位
	@Value("${business.server_no}")
	private String serviceNo;				//服务器实例号	2位
	@Value("${business.area_id}")
	private String areaId;				//区域标识	1位
	@Value("${business.sub_sys_no}")
	private String subSysNo;		//调用服务子系统的系统编号 2位
	
	public String getSysNo() {
		return sysNo;
	}
	public void setSysNo(String sysNo) {
		this.sysNo = sysNo;
	}
	public String getServiceNo() {
		return serviceNo;
	}
	public void setServiceNo(String serviceNo) {
		this.serviceNo = serviceNo;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getSubSysNo() {
		return subSysNo;
	}
	public void setSubSysNo(String subSysNo) {
		this.subSysNo = subSysNo;
	}
	
}
