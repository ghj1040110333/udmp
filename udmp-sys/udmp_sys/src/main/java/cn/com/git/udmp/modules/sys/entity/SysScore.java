

package cn.com.git.udmp.modules.sys.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.com.git.udmp.common.persistence.DataEntity;

/**
 * 积分参数管理Entity
 * @author 孙毅
 * @version 2015-11-06
 */
public class SysScore extends DataEntity<SysScore> {
	
	private static final long serialVersionUID = 1L;
	private String scoreId;		// 积分计划号
	private String scoreName;		// 积分计划名称
	private String scoreStatus;		// 积分计划状态1-正常 2-作废
	private String scoreMethod;		// 积分计算方式1-按笔数 2-按金额
	private String scoreStandard;		// 积分计划标准
	private String scoreValidity;		// 积分有效期方式1-统一到期 2-单笔计期 3-尾笔展期
	private Date scoreEnddate;		// 积分到期日
	private String scoreCycle;		// 积分有效周期
	private String scoreCycleUnit;		// 积分有效周期单位1-天 2-周 3-月 4-年
	
	public SysScore() {
		super();
	}

	public SysScore(String id){
		super(id);
	}

	@Length(min=0, max=45, message="积分计划号长度必须介于 0 和 45 之间")
	public String getScoreId() {
		return scoreId;
	}

	public void setScoreId(String scoreId) {
		this.scoreId = scoreId;
	}
	
	@Length(min=0, max=45, message="积分计划名称长度必须介于 0 和 45 之间")
	public String getScoreName() {
		return scoreName;
	}

	public void setScoreName(String scoreName) {
		this.scoreName = scoreName;
	}
	
	@Length(min=0, max=100, message="积分计划状态1-正常 2-作废长度必须介于 0 和 100 之间")
	public String getScoreStatus() {
		return scoreStatus;
	}

	public void setScoreStatus(String scoreStatus) {
		this.scoreStatus = scoreStatus;
	}
	
	@Length(min=0, max=100, message="积分计算方式1-按笔数 2-按金额长度必须介于 0 和 100 之间")
	public String getScoreMethod() {
		return scoreMethod;
	}

	public void setScoreMethod(String scoreMethod) {
		this.scoreMethod = scoreMethod;
	}
	
	@Length(min=0, max=11, message="积分计划标准长度必须介于 0 和 11 之间")
	public String getScoreStandard() {
		return scoreStandard;
	}

	public void setScoreStandard(String scoreStandard) {
		if(scoreStandard != null && "".equalsIgnoreCase(scoreStandard)){
			scoreStandard = null;
		}
		this.scoreStandard = scoreStandard;
	}
	
	@Length(min=0, max=100, message="积分有效期方式1-统一到期 2-单笔计期 3-尾笔展期长度必须介于 0 和 100 之间")
	public String getScoreValidity() {
		return scoreValidity;
	}

	public void setScoreValidity(String scoreValidity) {
		this.scoreValidity = scoreValidity;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getScoreEnddate() {
		return scoreEnddate;
	}

	public void setScoreEnddate(Date scoreEnddate) {
		this.scoreEnddate = scoreEnddate;
	}
	
	@Length(min=0, max=11, message="积分有效周期长度必须介于 0 和 11 之间")
	public String getScoreCycle() {
		return scoreCycle;
	}

	public void setScoreCycle(String scoreCycle) {
		if(scoreCycle != null && "".equalsIgnoreCase(scoreCycle)){
			scoreCycle = null;
		}
		this.scoreCycle = scoreCycle;
	}
	
	@Length(min=0, max=45, message="积分有效周期单位1-天 2-周 3-月 4-年长度必须介于 0 和 45 之间")
	public String getScoreCycleUnit() {
		return scoreCycleUnit;
	}

	public void setScoreCycleUnit(String scoreCycleUnit) {
		this.scoreCycleUnit = scoreCycleUnit;
	}
	
}