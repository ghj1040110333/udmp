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

package cn.com.git.udmp.modules.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.git.udmp.common.persistence.Page;
import cn.com.git.udmp.common.service.CrudService;
import cn.com.git.udmp.modules.demo.dao.MesTempletInfoDao;
import cn.com.git.udmp.modules.demo.entity.MesTempletInfo;

/**
 * demoService
 * @author chuzm
 * @version 2015-12-11
 */
@Service
@Transactional(readOnly = true)
public class MesTempletInfoService extends CrudService<MesTempletInfoDao, MesTempletInfo> {

	public MesTempletInfo get(String id) {
		return super.get(id);
	}
	
	public List<MesTempletInfo> findList(MesTempletInfo mesTempletInfo) {
		return super.findList(mesTempletInfo);
	}
	
	public Page<MesTempletInfo> findPage(Page<MesTempletInfo> page, MesTempletInfo mesTempletInfo) {
		return super.findPage(page, mesTempletInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(MesTempletInfo mesTempletInfo) {
		super.save(mesTempletInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(MesTempletInfo mesTempletInfo) {
		super.delete(mesTempletInfo);
	}
	
}