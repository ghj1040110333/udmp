

package cn.com.git.udmp.modules.sys.entity;

import org.hibernate.validator.constraints.Length;

import cn.com.git.udmp.common.persistence.DataEntity;

/**
 * 客户初始化信息管理Entity
 * @author 孙毅
 * @version 2015-11-09
 */
public class SysInitUserInfo extends DataEntity<SysInitUserInfo> {
	
	private static final long serialVersionUID = 1L;
	private String initName;		// 显示名称
	private String initType;		// 分类1-个人信息  2-家庭信息 3-车辆信息 4-职业信息 5-财务信息 6-联络信息 7-银行信息
	private String initSeqno;		// 显示顺序
	private String initDict;		// 表字段当输入类型为 4-日期段输入时，开始日期用init_dict的值加&lsquo;_start&rsquo;表示，结束日期用init_dict的值加&lsquo;_end&rsquo;表示
	private String initSelflag;		// 选择标志0-未选择 1-已选择
	private String initLabel;		// 补充文字
	private String initRegex;		// 正则表达式当输入类型为1-文本输入 ：正则验证2-下拉输入 ：需要的字典类型3-单日期输入 ：无4-日期段输入 ：无
	private String initInpuType;		// 输入类型1-文本输入 2-下拉输入 3-单日期输入 4-日期段输入
	
	private String selStr; //非数据库字段，用来传递选择的id集合
	
	public SysInitUserInfo() {
		super();
	}

	public SysInitUserInfo(String id){
		super(id);
	}

	@Length(min=0, max=100, message="显示名称长度必须介于 0 和 100 之间")
	public String getInitName() {
		return initName;
	}

	public void setInitName(String initName) {
		this.initName = initName;
	}
	
	@Length(min=0, max=100, message="分类1-个人信息  2-家庭信息 3-车辆信息 4-职业信息 5-财务信息 6-联络信息 7-银行信息长度必须介于 0 和 100 之间")
	public String getInitType() {
		return initType;
	}

	public void setInitType(String initType) {
		this.initType = initType;
	}
	
	@Length(min=0, max=11, message="显示顺序长度必须介于 0 和 11 之间")
	public String getInitSeqno() {
		return initSeqno;
	}

	public void setInitSeqno(String initSeqno) {
		this.initSeqno = initSeqno;
	}
	
	@Length(min=0, max=100, message="表字段当输入类型为 4-日期段输入时，开始日期用init_dict的值加&lsquo;_start&rsquo;表示，结束日期用init_dict的值加&lsquo;_end&rsquo;表示长度必须介于 0 和 100 之间")
	public String getInitDict() {
		return initDict;
	}

	public void setInitDict(String initDict) {
		this.initDict = initDict;
	}
	
	@Length(min=0, max=2, message="选择标志0-未选择 1-已选择长度必须介于 0 和 2 之间")
	public String getInitSelflag() {
		return initSelflag;
	}

	public void setInitSelflag(String initSelflag) {
		this.initSelflag = initSelflag;
	}
	
	@Length(min=0, max=45, message="补充文字长度必须介于 0 和 45 之间")
	public String getInitLabel() {
		return initLabel;
	}

	public void setInitLabel(String initLabel) {
		this.initLabel = initLabel;
	}
	
	@Length(min=0, max=1000, message="正则表达式(当输入类型为1-文本输入 ：正则验证2-下拉输入 ：需要的字典类型3-单日期输入 ：无4-日期段输入 ：无)长度必须介于 0 和 1000 之间")
	public String getInitRegex() {
		return initRegex;
	}

	public void setInitRegex(String initRegex) {
		this.initRegex = initRegex;
	}
	
	@Length(min=0, max=100, message="输入类型(1-文本输入 2-下拉输入 3-单日期输入 4-日期段输入)长度必须介于 0 和 100 之间")
	public String getInitInpuType() {
		return initInpuType;
	}

	public void setInitInpuType(String initInpuType) {
		this.initInpuType = initInpuType;
	}

	public String getSelStr() {
		return selStr;
	}

	public void setSelStr(String selStr) {
		this.selStr = selStr;
	}
	
	
	
}