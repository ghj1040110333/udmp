package activiti_maven_project.com.git.controller.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.ActivitiException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import activiti_maven_project.com.git.common.Page;
import activiti_maven_project.com.git.common.exception.BpmException;
import activiti_maven_project.com.git.common.vo.ActWorkListPage;
import activiti_maven_project.com.git.common.vo.condition.WorkListCondition;
import activiti_maven_project.com.git.service.QueryWorkListService;
import activiti_maven_project.com.git.service.TaskService_;
import activiti_maven_project.com.git.user.Users; 

@Controller
@RequestMapping(value = "/worklist")
public class WorkListController extends BaseController{

	@Autowired(required = true)
	private QueryWorkListService queryWorkListService;
	
	@Autowired(required = true)
	private TaskService_ taskService_;
	
	
	/**
	 * 初始化待分配列表
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws BpmException
	 */
	@RequestMapping(value="/initCandiWorkList")
	@ResponseBody
	public ModelAndView initCandiWorkList (HttpServletRequest request, HttpServletResponse response, Model model) throws BpmException{
		Map map=new HashMap();
		
		Users user=(Users)request.getSession().getAttribute("user");
		
		ActWorkListPage alp=queryWorkListService.queryCandiWorkList(user.getUserCd(), map, new Page());
		
		model.addAttribute("workListCondition", new WorkListCondition());
		model.addAttribute("workList", alp);
		model.addAttribute("page", alp.getPage());
		
		ModelAndView modelAndView = new ModelAndView(); 
		modelAndView.setViewName("/bootstrap3/workList/candiWorkList.jsp");
		return modelAndView;
		
	}
	
	
	/**
	 * 待分配列表过滤查询
	 * @param condition
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws BpmException
	 */
	@RequestMapping(value="/queryCandiWorkList" ,headers = "Accept=application/json" ,method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView queryCandiWorkList ( WorkListCondition condition ,HttpServletRequest request, HttpServletResponse response, Model model) throws BpmException{
		Map<String,Object> map=object2map(condition);
		String pageNo=request.getParameter("pageNo");
		Page page=new Page();
		if(!StringUtils.isBlank(pageNo)){
			page.setPageNo(new Integer(pageNo));
		}
		Users user=(Users)request.getSession().getAttribute("user");
		
		ActWorkListPage alp=queryWorkListService.queryCandiWorkList(user.getUserCd(), map, page);
		
		model.addAttribute("WorkListCondition", condition);
		model.addAttribute("workList", alp);
		model.addAttribute("page", alp.getPage());
		
		ModelAndView modelAndView = new ModelAndView(); 
		modelAndView.setViewName("/bootstrap3/workList/candiWorkList.jsp");
		return modelAndView;
		
	}
	

	/**
	 * 待分配列表领取任务
	 * @param taskId
	 * @param request
	 * @param response
	 * @param model
	 * @throws BpmException
	 */
	@RequestMapping(value="/take/{taskId}")
	@ResponseBody
	public void take ( @PathVariable String taskId ,HttpServletRequest request, HttpServletResponse response, Model model) throws ActivitiException{
		Map map=new HashMap();
		
		Users user=(Users)request.getSession().getAttribute("user");
		
		taskService_.takeTask(taskId,user.getUserCd());
	}
	
	
	
	/**
	 * 待办任务列表初始化
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws BpmException
	 */
	@RequestMapping(value="/initWorkList")
	@ResponseBody
	public ModelAndView initWorkList (HttpServletRequest request, HttpServletResponse response, Model model) throws BpmException{
		Map map=new HashMap();
		
		Users user=(Users)request.getSession().getAttribute("user");
		
		ActWorkListPage alp=queryWorkListService.queryWorkList(user.getUserCd(), map, new Page());
		
		model.addAttribute("workListCondition", new WorkListCondition());
		model.addAttribute("workList", alp);
		model.addAttribute("page", alp.getPage());
		
		ModelAndView modelAndView = new ModelAndView(); 
		modelAndView.setViewName("/bootstrap3/workList/workList.jsp");
		return modelAndView;
		
	}
	
	/**
	 * 待办任务列表过滤查询
	 * @param condition
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws BpmException
	 */
	@RequestMapping(value="/queryWorkList" ,headers = "Accept=application/json" ,method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView queryWorkList ( WorkListCondition condition ,HttpServletRequest request, HttpServletResponse response, Model model) throws BpmException{
		Map<String,Object> map=object2map(condition);
		String pageNo=request.getParameter("pageNo");
		Page page=new Page();
		if(!StringUtils.isBlank(pageNo)){
			page.setPageNo(new Integer(pageNo));
		}
		Users user=(Users)request.getSession().getAttribute("user");
		
		ActWorkListPage alp=queryWorkListService.queryWorkList(user.getUserCd(), map, page);
		
		model.addAttribute("WorkListCondition", condition);
		model.addAttribute("workList", alp);
		model.addAttribute("page", alp.getPage());
		
		ModelAndView modelAndView = new ModelAndView(); 
		modelAndView.setViewName("/bootstrap3/workList/workList.jsp");
		return modelAndView;
		
	}
	
	
	/**
	 * 初始化挂起 任务列表
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws BpmException
	 */
	@RequestMapping(value="/initSuspendList")
	@ResponseBody
	public ModelAndView initSuspendList (HttpServletRequest request, HttpServletResponse response, Model model) throws BpmException{
		Map map=new HashMap();
		
		Users user=(Users)request.getSession().getAttribute("user");
		
		ActWorkListPage alp=queryWorkListService.querySuspendList(user.getUserCd(), map, new Page());
		
		model.addAttribute("workListCondition", new WorkListCondition());
		model.addAttribute("workList", alp);
		model.addAttribute("page", alp.getPage());
		
		ModelAndView modelAndView = new ModelAndView(); 
		modelAndView.setViewName("/operation/worklist/suspendList.jsp");
		return modelAndView;
		
	}
	
	
	
	
	
	
	
}
