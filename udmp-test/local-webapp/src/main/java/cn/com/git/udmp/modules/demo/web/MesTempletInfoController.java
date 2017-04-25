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

package cn.com.git.udmp.modules.demo.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.com.git.udmp.common.persistence.Page;
import cn.com.git.udmp.common.web.BaseController;
import cn.com.git.udmp.core.config.Global;
import cn.com.git.udmp.modules.demo.entity.MesTempletInfo;
import cn.com.git.udmp.modules.demo.service.MesTempletInfoService;

/**
 * demoController
 * @author chuzm
 * @version 2015-12-11
 */
@Controller
@RequestMapping(value = "${adminPath}/demo/mesTempletInfo")
public class MesTempletInfoController extends BaseController {

	@Autowired
	private MesTempletInfoService mesTempletInfoService;
	
	@ModelAttribute
	public MesTempletInfo get(@RequestParam(required=false) String id) {
		MesTempletInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mesTempletInfoService.get(id);
		}
		if (entity == null){
			entity = new MesTempletInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("demo:mesTempletInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(MesTempletInfo mesTempletInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MesTempletInfo> page = mesTempletInfoService.findPage(new Page<MesTempletInfo>(request, response), mesTempletInfo); 
		model.addAttribute("page", page);
		return "udmp/modules/demo/mesTempletInfoList";
	}

	@RequiresPermissions("demo:mesTempletInfo:view")
	@RequestMapping(value = "form")
	public String form(MesTempletInfo mesTempletInfo, Model model) {
		model.addAttribute("mesTempletInfo", mesTempletInfo);
		return "udmp/modules/demo/mesTempletInfoForm";
	}

	@RequiresPermissions("demo:mesTempletInfo:edit")
	@RequestMapping(value = "save")
	public String save(MesTempletInfo mesTempletInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, mesTempletInfo)){
			return form(mesTempletInfo, model);
		}
		mesTempletInfoService.save(mesTempletInfo);
		addMessage(redirectAttributes, "保存demo成功");
		return "redirect:"+Global.getAdminPath()+"/demo/mesTempletInfo/?repage";
	}
	
	@RequiresPermissions("demo:mesTempletInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(MesTempletInfo mesTempletInfo, RedirectAttributes redirectAttributes) {
		mesTempletInfoService.delete(mesTempletInfo);
		addMessage(redirectAttributes, "删除demo成功");
		return "redirect:"+Global.getAdminPath()+"/demo/mesTempletInfo/?repage";
	}

}