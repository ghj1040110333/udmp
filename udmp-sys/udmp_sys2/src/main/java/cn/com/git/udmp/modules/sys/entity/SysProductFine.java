

package cn.com.git.udmp.modules.sys.entity;

import org.hibernate.validator.constraints.Length;
import java.util.List;
import com.google.common.collect.Lists;

import cn.com.git.udmp.common.persistence.DataEntity;

/**
 * 罚息管理Entity
 * @author 赵明辉
 * @version 2015-11-18
 */
public class SysProductFine extends DataEntity<SysProductFine> {
	
	private static final long serialVersionUID = 1L;
	private String description;//罚息标准描述
	private String fineWay;		// 罚息收取方式
	private String fineSubject;		// 罚息计息标准
	private String fineRate;		// 罚息执行利率
	private int gracePeriod;//违约宽限期
	private List<SysBreachFineSubsection> sysBreachFineSubsectionList = Lists.newArrayList();		// 子表列表
	
	public SysProductFine() {
		super();
	}

	public SysProductFine(String id){
		super(id);
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@Length(min=0, max=2, message="罚息收取方式长度必须介于 0 和 2 之间")
	public String getFineWay() {
		return fineWay;
	}

	public void setFineWay(String fineWay) {
		this.fineWay = fineWay;
	}
	
	@Length(min=0, max=5, message="罚息计息标准长度必须介于 0 和 5 之间")
	public String getFineSubject() {
		return fineSubject;
	}

	public void setFineSubject(String fineSubject) {
		this.fineSubject = fineSubject;
	}
	
	public String getFineRate() {
		return fineRate;
	}

	public void setFineRate(String fineRate) {
		this.fineRate = fineRate;
	}
	
	public int getGracePeriod() {
		return gracePeriod;
	}

	public void setGracePeriod(int gracePeriod) {
		this.gracePeriod = gracePeriod;
	}

	public List<SysBreachFineSubsection> getSysBreachFineSubsectionList() {
		return sysBreachFineSubsectionList;
	}

	public void setSysBreachFineSubsectionList(List<SysBreachFineSubsection> sysBreachFineSubsectionList) {
		this.sysBreachFineSubsectionList = sysBreachFineSubsectionList;
	}
}