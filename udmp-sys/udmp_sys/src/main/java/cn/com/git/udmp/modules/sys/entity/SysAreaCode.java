

package cn.com.git.udmp.modules.sys.entity;

import org.hibernate.validator.constraints.Length;

import cn.com.git.udmp.common.persistence.DataEntity;

/**
 * 区域代码管理Entity
 * @author 赵明辉
 * @version 2015-11-10
 */
public class SysAreaCode extends DataEntity<SysAreaCode> {
	
	private static final long serialVersionUID = 1L;
	private String areaIds;		// 区域地址的id
	private String areaNames;		// 地域名称
	private String areaCode;		// 地区代码
	
	public SysAreaCode() {
		super();
	}

	public SysAreaCode(String id){
		super(id);
	}

	@Length(min=1, max=500, message="区域地址的id长度必须介于 1 和 500 之间")
	public String getAreaIds() {
		return areaIds;
	}

	public void setAreaIds(String areaIds) {
		this.areaIds = areaIds;
	}
	
	@Length(min=1, max=500, message="地域名称长度必须介于 1 和 500 之间")
	public String getAreaNames() {
		return areaNames;
	}

	public void setAreaNames(String areaNames) {
		this.areaNames = areaNames;
	}
	
	@Length(min=1, max=100, message="地区代码长度必须介于 1 和 100 之间")
	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	
}