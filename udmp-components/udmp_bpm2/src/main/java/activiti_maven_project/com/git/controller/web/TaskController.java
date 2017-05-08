package activiti_maven_project.com.git.controller.web;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import activiti_maven_project.com.git.common.WorkFlowContent;
import activiti_maven_project.com.git.common.exception.BpmException;
import activiti_maven_project.com.git.common.vo.ActFromVO;
import activiti_maven_project.com.git.common.vo.ActiveNode;
import activiti_maven_project.com.git.common.vo.SelectUserInfos;
import activiti_maven_project.com.git.service.TaskService_;
import activiti_maven_project.com.git.user.Users;

@Controller
@RequestMapping(value = "/task")
public class TaskController extends BaseController {

	@Autowired(required = true)
	private TaskService_ taskService_;

	@RequestMapping(value = "/handle/{taskId}")
	public ModelAndView handleTask(@PathVariable("taskId") String taskId, HttpServletRequest request,
			HttpServletResponse response, Model model)throws BpmException {
		logger.debug("taskId===={}", taskId);
		String message="";
		ModelAndView modelAndView = new ModelAndView();
		ActFromVO af = taskService_.findTaskFormInfo(taskId);
		SelectUserInfos users=null;
		try{
			users = taskService_.findNextActivityUserInfo(taskId);
		}catch(BpmException e){
			message=e.getMessage();
		}
		
		if (StringUtils.isBlank(af.getFromKey())) {
			model.addAttribute("actFromInfos", af.getInsideFroms());
			model.addAttribute("actActions", af.getActions());
			model.addAttribute("variables", af.getVariables());
			model.addAttribute("taskId", af.getTaskId());
			model.addAttribute("opinion", af.getOpinion());
			model.addAttribute("nextUsers", users);
			model.addAttribute("message", message);
			modelAndView.setViewName("/bootstrap3/task/submitForm.jsp");
			return modelAndView;
		} else {
			modelAndView.setViewName(af.getFromKey());
			return modelAndView;
		}
	}
	
	@RequestMapping(value = "/handleBatch/{taskIds}")
	public ModelAndView handleBatchTask(@PathVariable("taskIds") String taskIds, HttpServletRequest request,
			HttpServletResponse response, Model model)throws BpmException {
		logger.debug("taskIds===={}", taskIds);
		String[] tis=taskIds.split(",");
		String taskId=tis[0].substring(0,tis[0].indexOf("@"));
		String message="";
		
		ModelAndView modelAndView = new ModelAndView();
		ActFromVO af = taskService_.findTaskFormInfo(taskId);
		SelectUserInfos users=null;
		try{
			users = taskService_.findNextActivityUserInfo(taskId);
		}catch(BpmException e){
			message=e.getMessage();
		}
		
		if (StringUtils.isBlank(af.getFromKey())) {
			model.addAttribute("actFromInfos", af.getInsideFroms());
			model.addAttribute("actActions", af.getActions());
			model.addAttribute("variables", af.getVariables());
			model.addAttribute("taskId", af.getTaskId());
			model.addAttribute("opinion", af.getOpinion());
			model.addAttribute("nextUsers", users);
			model.addAttribute("message", message);
			modelAndView.setViewName("/bootstrap3/task/submitForm.jsp");
			return modelAndView;
		} else {
			modelAndView.setViewName(af.getFromKey());
			return modelAndView;
		}
	}
	
	@RequestMapping(value = "/retrieve/{taskId}")
	@ResponseBody
	public void retrieveTask(@PathVariable("taskId") String taskId,@ModelAttribute("user") Users user, HttpServletRequest request,
			HttpServletResponse response, Model model)throws BpmException {
		logger.debug("taskId===={}", taskId);
//		Map<String, Object> variables = new HashMap<String, Object>();
		taskService_.retrieveTask(taskId, user.getUserCd(), null);
	}
	
	@RequestMapping(value = "/turn/{taskId}")
	public ModelAndView turn(@PathVariable("taskId") String taskId, HttpServletRequest request,
			HttpServletResponse response, Model model)throws BpmException {
		logger.debug("taskId===={}", taskId);
		ModelAndView modelAndView = new ModelAndView();
		ActFromVO af = taskService_.findTaskFormInfo(taskId);
		if (StringUtils.isBlank(af.getFromKey())) {
			model.addAttribute("actFromInfos", af.getInsideFroms());
			model.addAttribute("actActions", af.getActions());
			model.addAttribute("variables", af.getVariables());
			model.addAttribute("taskId", af.getTaskId());
			modelAndView.setViewName("/bootstrap3/task/turnForm.jsp");
			return modelAndView;
		} else {
			modelAndView.setViewName(af.getFromKey());
			return modelAndView;
		}
	}
	
	
	
	@RequestMapping(value = "/delegate/{taskId}")
	@ResponseBody
	public void delegateTask(@PathVariable("taskId") String taskId, @RequestBody String parms,
			@ModelAttribute("user") Users user, HttpServletRequest request, HttpServletResponse response)
					throws BpmException {
		try {
			parms = java.net.URLDecoder.decode(parms, "UTF-8");
			Map<String, Object> variables = new HashMap<String, Object>();
			if (StringUtils.isNotBlank(parms)) {
				variables = str2map(parms);
			}

			// variables.put("userId", user.getUserCd());
			logger.debug("variables:{}", variables);

			taskService_.delegateTask(taskId, variables);
		} catch (Exception e) {
			logger.error("任务保存失败：", e);
			throw new BpmException("任务保存失败：", e);
		}
	}
	
	@RequestMapping(value = "/replace/{taskId}")
	@ResponseBody
	public void replaceTask(@PathVariable("taskId") String taskId,@RequestBody String parms, HttpServletRequest request,
			HttpServletResponse response) throws BpmException{
		logger.debug("taskId===={}", taskId);
		try {
			parms = java.net.URLDecoder.decode(parms, "UTF-8");
			Map<String, Object> variables = new HashMap<String, Object>();
			if (StringUtils.isNotBlank(parms)) {
				variables = str2map(parms);
			}
			taskService_.replaceTask(taskId,variables);
			
		} catch (Exception e) {
			logger.error("任务提交失败：", e);
			throw new BpmException("任务提交失败：", e);
		}
	}
	
	@RequestMapping(value = "/batch/{taskIds}")
	@ResponseBody
	public void submitBatch(@PathVariable("taskIds") String taskIds, @RequestBody String parms,
			@ModelAttribute("user") Users user, HttpServletRequest request, HttpServletResponse response)
					throws BpmException {
		try {
			parms = java.net.URLDecoder.decode(parms, "UTF-8");
			Map<String, Object> variables = new HashMap<String, Object>();
			if (StringUtils.isNotBlank(parms)) {
				variables = str2map(parms);
			}
			variables.put("userId", user.getUserCd());
			logger.debug("variables:{}", variables);
			String[] temps=taskIds.split(",");
			List<String> taskIdList=new ArrayList<String>();
			
			String procDefId="";
			
			for(String str:temps){
				if(StringUtils.isBlank(procDefId)){
					procDefId=str.substring(str.indexOf("@")+1);
				}else if(!procDefId.equals(str.substring(str.indexOf("@")+1))){
					throw new BpmException("批量提交流程模板不一致,不能批量提交");
				}
				taskIdList.add(str.substring(0,str.indexOf("@")));
			}
			
			taskService_.submitBatch(taskIdList, variables);
		} catch (Exception e) {
			logger.error("任务提交失败", e);
			throw new BpmException(e.getMessage(), e);
		}
	}
	

	@RequestMapping(value = "/submit/{taskId}")
	@ResponseBody
	public void submitForForm(@PathVariable("taskId") String taskId, @RequestBody String parms,
			@ModelAttribute("user") Users user, HttpServletRequest request, HttpServletResponse response)
					throws BpmException {
		try {
			parms = java.net.URLDecoder.decode(parms, "UTF-8");
			Map<String, Object> variables = new HashMap<String, Object>();
			if (StringUtils.isNotBlank(parms)) {
				variables = str2map(parms);
			}
			String submitType=(String)variables.get("submitType");
			variables.remove("variables");
			variables.put("userId", user.getUserCd());
			logger.debug("variables:{}", variables);
			if(submitType.equals(WorkFlowContent.WF_TASK_SUBMIT_Type_COMPLETE)){
				taskService_.submitNomalTask(taskId, variables);
			}else if(submitType.equals(WorkFlowContent.WF_TASK_SUBMIT_Type_CANCEL)){
				taskService_.cancelProcessByTaskId(taskId);
			}
		} catch (Exception e) {
			logger.error("任务提交失败", e);
			throw new BpmException("任务提交失败", e);
		}
	}

	@RequestMapping(value = "/submitByBack/{type}/{taskId}")
	@ResponseBody
	public void submitByBack(@PathVariable("type") String type, @PathVariable("taskId") String taskId,
			@RequestBody String parms, @ModelAttribute("user") Users user, HttpServletRequest request,
			HttpServletResponse response) throws BpmException {
		try {
			parms = java.net.URLDecoder.decode(parms, "UTF-8");
			Map<String, Object> variables = new HashMap<String, Object>();
			if (StringUtils.isNotBlank(parms)) {
				variables = str2map(parms);
			}

			variables.put("userId", user.getUserCd());
			logger.debug("variables:{}", variables);

			taskService_.submitByBack(taskId, type, variables);
		} catch (Exception e) {
			logger.error("任务提交失败：", e);
			throw new BpmException("任务提交失败：", e);
		}
	}

	@RequestMapping(value = "/save/{taskId}")
	@ResponseBody
	public void save(@PathVariable("taskId") String taskId, @RequestBody String parms,
			@ModelAttribute("user") Users user, HttpServletRequest request, HttpServletResponse response)
					throws BpmException {
		try {
			parms = java.net.URLDecoder.decode(parms, "UTF-8");
			Map<String, Object> variables = new HashMap<String, Object>();
			if (StringUtils.isNotBlank(parms)) {
				variables = str2map(parms);
			}

			// variables.put("userId", user.getUserCd());
			logger.debug("variables:{}", variables);

			taskService_.saveTask(taskId, variables);
		} catch (Exception e) {
			logger.error("任务保存失败：", e);
			throw new BpmException("任务保存失败：", e);
		}
	}

	@RequestMapping(value = "/findNextActivityUserInfo/{taskId}")
	public ModelAndView findNextActivityUserInfo(@PathVariable("taskId") String taskId, HttpServletRequest request,
			HttpServletResponse response, Model model) throws BpmException {
		logger.debug("taskId===={}", taskId);
		ModelAndView modelAndView = new ModelAndView();
		try {
			SelectUserInfos users = taskService_.findNextActivityUserInfo(taskId);

			model.addAttribute("nextUsers", users);
//			modelAndView.setViewName("/bootstrap3/task/selectOneUser.jsp");
		} catch (Exception e) {
			logger.error("创建模型失败：", e);
			throw new BpmException("创建模型失败：", e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "/readActity/{taskId}")
	public ModelAndView readActity(@PathVariable("taskId") String taskId, HttpServletResponse response, Model model)
			throws Exception {
		List<ActiveNode> list4 = new ArrayList<>();
		// List<ActiveNode> list1= taskService.readActity(taskId);
		List<ActiveNode> list2 = taskService_.readHisActity(taskId);

		// List<ActivityImpl> list3= taskService.readAllActity(taskId);
		ModelAndView modelAndView = new ModelAndView();
		// model.addAttribute("actitys", list1);
		model.addAttribute("actitysHis", list2);
		model.addAttribute("actitysService", list4);
		// model.addAttribute("actitysAll", list3);
		model.addAttribute("taskId", taskId);
		modelAndView.setViewName("/operation/worklist/show.jsp");
		return modelAndView;
	}

	@RequestMapping(value = "/readResource/{taskId}")
	public void readResource(@PathVariable("taskId") String taskId, HttpServletResponse response) throws Exception {

		InputStream imageStream = taskService_.readRunResource(taskId);

		// 输出资源内容到相应对象
		byte[] b = new byte[1024];
		int len;
		while ((len = imageStream.read(b, 0, 1024)) != -1) {
			response.getOutputStream().write(b, 0, len);
		}
	}
}
