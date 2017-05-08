package activiti_maven_project.com.git.controller.web;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestParam;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import activiti_maven_project.com.git.common.Page;
import activiti_maven_project.com.git.common.exception.BpmException;
import activiti_maven_project.com.git.common.vo.ActDefinitionPage;
import activiti_maven_project.com.git.common.vo.ActFromVO;
import activiti_maven_project.com.git.common.vo.ActHisActInstListPage;
import activiti_maven_project.com.git.common.vo.ActHisProcessListPage;
import activiti_maven_project.com.git.common.vo.ActHisTaskListPage;
import activiti_maven_project.com.git.common.vo.ModelExportVO;
import activiti_maven_project.com.git.common.vo.condition.WorkListCondition;
import activiti_maven_project.com.git.service.ProcessDefinitionService;
import activiti_maven_project.com.git.service.QueryWorkListService;
import activiti_maven_project.com.git.user.Users;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
@Controller
@RequestMapping(value = "/hisProcess")
public class HisProcessController extends BaseController {
	
	@Autowired(required = true)
	private QueryWorkListService queryWorkListService;
	
	/**
	 * bpm model处理页面初始化
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws BpmException
	 */
	@RequestMapping(value = "/initList")
	public  ModelAndView  init(@ModelAttribute("user") Users user, HttpServletRequest request, HttpServletResponse response, Model model) throws BpmException{
		logger.debug("init");
		Map map=new HashMap();
		ActHisProcessListPage ahl=	queryWorkListService.queryHisList(user.getUserCd(), map, new Page());
		model.addAttribute("templateName", "");
		model.addAttribute("templates", ahl);
		model.addAttribute("page", ahl.getPage());
		ModelAndView modelAndView = new ModelAndView(); 
		modelAndView.setViewName("/bootstrap3/hisProcess/hisProList.jsp");
		return modelAndView;
	}
	
	
	

	/**
	 * bpm model页面列表查询
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws BpmException 
	 */
	@RequestMapping(value = "/showList", method=RequestMethod.POST)
	public ModelAndView showList(@ModelAttribute("user") Users user, WorkListCondition condition ,HttpServletRequest request, HttpServletResponse response, Model model) throws BpmException{
		Map<String,Object> map=object2map(condition);
		String pageNo=request.getParameter("pageNo");
		String templateName=request.getParameter("templateName");
		
		if(StringUtils.isNotBlank(templateName)){
			map.put("template", templateName);
		}
		
		Page page=new Page();
		if(!StringUtils.isBlank(pageNo)){
			page.setPageNo(new Integer(pageNo));
		}
		ActHisProcessListPage ahl=	queryWorkListService.queryHisList(user.getUserCd(), map, page);
		model.addAttribute("templateName", templateName);
		model.addAttribute("templates", ahl);
		model.addAttribute("page", ahl.getPage());
		ModelAndView modelAndView = new ModelAndView(); 
		modelAndView.setViewName("/bootstrap3/hisProcess/hisProList.jsp");
		return modelAndView;
	}
	
	/**
	 * 查询历史详细
	 * @param taskId
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/view/{processId}")
	public ModelAndView handleTask(@PathVariable("processId") String processId, HttpServletRequest request,
			HttpServletResponse response, Model model)throws BpmException {
		logger.debug("processId===={}" , processId);
		ModelAndView modelAndView = new ModelAndView();
		ActHisActInstListPage hts = queryWorkListService.queryHisTaskList(processId, null, null);
			model.addAttribute("page", hts.getPage());
			model.addAttribute("hisTasks", hts);
			modelAndView.setViewName("/bootstrap3/hisProcess/hisTasks.jsp");
			return modelAndView;
	}
	
	
	/**
	 * 查询最近的会议记录
	 * @param taskId
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/viewMeeting/{processId}")
	public ModelAndView handleMeetingTask(@PathVariable("processId") String processId, HttpServletRequest request,
			HttpServletResponse response, Model model)throws BpmException {
		logger.debug("processId===={}" , processId);
		ModelAndView modelAndView = new ModelAndView();
		ActHisTaskListPage hts = queryWorkListService.queryHisMeetingTaskList(processId, null, null);
			model.addAttribute("page", hts.getPage());
			model.addAttribute("hisTasks", hts);
			modelAndView.setViewName("/bootstrap3/hisProcess/hisTasks.jsp");
			return modelAndView;
	}
	
	
}
