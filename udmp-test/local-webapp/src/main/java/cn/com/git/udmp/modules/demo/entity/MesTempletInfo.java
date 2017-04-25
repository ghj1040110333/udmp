/**
 * GIT Confidential
 * Licensed Materials - Property of GIT
 * Copyright (c) 1998-2016 Global InfoTech Corp. All Rights Reserved.
 * Global InfoTech, Inc. owns the copyright and other intellectual
 * property rights in this software.
 *
 * The source code for this program is not published.
 * Duplication or use of the Software is not permitted
 * except as expressly provided in a written agreement
 * between your company and Global InfoTech, Inc.
 */

package cn.com.git.udmp.modules.demo.entity;

import org.hibernate.validator.constraints.Length;

import cn.com.git.udmp.common.persistence.DataEntity;

/**
 * demoEntity
 * @author chuzm
 * @version 2015-12-11
 */
public class MesTempletInfo extends DataEntity<MesTempletInfo> {
	
	private static final long serialVersionUID = 1L;
	private String templetNum;		// 模板编号
	private String templetType;		// 模板类型
	private String templetVer;		// 模板版本
	private String pushChannel;		// 模板推送渠道
	private String templetState;		// 模板状态
	private String templetContent;		// 模版内容
	
	public MesTempletInfo() {
		super();
	}

	public MesTempletInfo(String id){
		super(id);
	}

	@Length(min=1, max=20, message="模板编号长度必须介于 1 和 20 之间")
	public String getTempletNum() {
		return templetNum;
	}

	public void setTempletNum(String templetNum) {
		this.templetNum = templetNum;
	}
	
	@Length(min=1, max=3, message="模板类型长度必须介于 1 和 3 之间")
	public String getTempletType() {
		return templetType;
	}

	public void setTempletType(String templetType) {
		this.templetType = templetType;
	}
	
	@Length(min=1, max=6, message="模板版本长度必须介于 1 和 6 之间")
	public String getTempletVer() {
		return templetVer;
	}

	public void setTempletVer(String templetVer) {
		this.templetVer = templetVer;
	}
	
	@Length(min=1, max=2, message="模板推送渠道长度必须介于 1 和 2 之间")
	public String getPushChannel() {
		return pushChannel;
	}

	public void setPushChannel(String pushChannel) {
		this.pushChannel = pushChannel;
	}
	
	@Length(min=0, max=2, message="模板状态长度必须介于 0 和 2 之间")
	public String getTempletState() {
		return templetState;
	}

	public void setTempletState(String templetState) {
		this.templetState = templetState;
	}
	
	@Length(min=0, max=1000, message="模版内容长度必须介于 0 和 1000 之间")
	public String getTempletContent() {
		return templetContent;
	}

	public void setTempletContent(String templetContent) {
		this.templetContent = templetContent;
	}
	
}