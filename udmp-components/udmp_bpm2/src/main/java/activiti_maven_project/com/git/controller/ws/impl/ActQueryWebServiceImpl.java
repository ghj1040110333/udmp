package activiti_maven_project.com.git.controller.ws.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import activiti_maven_project.com.git.common.Page;
import activiti_maven_project.com.git.common.exception.BpmException;
import activiti_maven_project.com.git.common.vo.ActHisActInstListPage;
import activiti_maven_project.com.git.common.vo.ActHisProcessListPage;
import activiti_maven_project.com.git.common.vo.ActHisTaskListPage;
import activiti_maven_project.com.git.common.vo.ActWorkListPage;
import activiti_maven_project.com.git.controller.ws.ActQueryWebService;
import activiti_maven_project.com.git.service.QueryWorkListService;

@Component("actQueryWebServiceImpl")
public class ActQueryWebServiceImpl extends BaseWebService implements ActQueryWebService {

	@Autowired(required = true)
	private QueryWorkListService queryWorkListService;
	@Override
	public String queryWorkList(String _variablesJson) throws BpmException {
		String requiredStr="userId,page_index,pageSize";
		Map<String, Object> map=getInputDate(_variablesJson,requiredStr);
		String userId=(String)map.get("userId");
		map.remove("userId");
		int page_index=(Integer)map.get("page_index");
		map.remove("page_index");
		int pageSize=(Integer)map.get("pageSize");
		map.remove("pageSize");
		Page page=new Page();
		page.setStart_index(page_index);
		page.setPageSize(pageSize);
		ActWorkListPage awp=queryWorkListService.queryWorkList(userId, map, page);
		return object2Jackson(awp);
	}

	@Override
	public String conutWorkList(String _variablesJson) throws BpmException {
		String requiredStr="userId,page_index,pageSize";
		Map<String, Object> map=getInputDate(_variablesJson,requiredStr);
		String userId=(String)map.get("userId");
		map.remove("userId");
		int page_index=(Integer)map.get("page_index");
		map.remove("page_index");
		int pageSize=(Integer)map.get("pageSize");
		map.remove("pageSize");
		Page page=new Page();
		page.setStart_index(page_index);
		page.setPageSize(pageSize);
		Long count=queryWorkListService.conutWorkList(userId, null, null, map);
		return "{\"count\":\""+count+"\"}";
	}

	@Override
	public String queryCandiWorkList(String _variablesJson) throws BpmException {
		String requiredStr="userId,page_index,pageSize";
		Map<String, Object> map=getInputDate(_variablesJson,requiredStr);
		String userId=(String)map.get("userId");
		map.remove("userId");
		int page_index=(Integer)map.get("page_index");
		map.remove("page_index");
		int pageSize=(Integer)map.get("pageSize");
		map.remove("pageSize");
		Page page=new Page();
		page.setStart_index(page_index);
		page.setPageSize(pageSize);
		ActWorkListPage awp=queryWorkListService.queryCandiWorkList(userId, map,page);
		return object2Jackson(awp);
	}

	@Override
	public String conutCandiWorkList(String _variablesJson) throws BpmException {
		String requiredStr="userId,page_index,pageSize";
		Map<String, Object> map=getInputDate(_variablesJson,requiredStr);
		String userId=(String)map.get("userId");
		map.remove("userId");
		int page_index=(Integer)map.get("page_index");
		map.remove("page_index");
		int pageSize=(Integer)map.get("pageSize");
		map.remove("pageSize");
		Page page=new Page();
		page.setStart_index(page_index);
		page.setPageSize(pageSize);
		Long count=queryWorkListService.conutCandiWorkList(userId, map);
		return "{\"count\":\""+count+"\"}";
	}

	@Override
	public String querySuspendList(String _variablesJson) throws BpmException {
		String requiredStr="userId,page_index,pageSize";
		Map<String, Object> map=getInputDate(_variablesJson,requiredStr);
		String userId=(String)map.get("userId");
		map.remove("userId");
		int page_index=(Integer)map.get("page_index");
		map.remove("page_index");
		int pageSize=(Integer)map.get("pageSize");
		map.remove("pageSize");
		Page page=new Page();
		page.setStart_index(page_index);
		page.setPageSize(pageSize);
		ActWorkListPage awp=queryWorkListService.querySuspendList(userId, map,page);
		return object2Jackson(awp);
	}

	@Override
	public String conutSuspendList(String _variablesJson) throws BpmException {
		String requiredStr="userId,page_index,pageSize";
		Map<String, Object> map=getInputDate(_variablesJson,requiredStr);
		String userId=(String)map.get("userId");
		map.remove("userId");
		int page_index=(Integer)map.get("page_index");
		map.remove("page_index");
		int pageSize=(Integer)map.get("pageSize");
		map.remove("pageSize");
		Page page=new Page();
		page.setStart_index(page_index);
		page.setPageSize(pageSize);
		Long count=queryWorkListService.conutSuspendList(userId, map);
		return "{\"count\":\""+count+"\"}";
	}

	@Override
	public String queryHisList(String _variablesJson) throws BpmException {
		String requiredStr="userId,page_index,pageSize";
		Map<String, Object> map=getInputDate(_variablesJson,requiredStr);
		String userId=(String)map.get("userId");
		map.remove("userId");
		int page_index=(Integer)map.get("page_index");
		map.remove("page_index");
		int pageSize=(Integer)map.get("pageSize");
		map.remove("pageSize");
		Page page=new Page();
		page.setStart_index(page_index);
		page.setPageSize(pageSize);
		ActHisProcessListPage app=queryWorkListService.queryHisList(userId, map,page);
		return object2Jackson(app);
	}

	@Override
	public String conutHisList(String _variablesJson) throws BpmException {
		String requiredStr="userId,page_index,pageSize";
		Map<String, Object> map=getInputDate(_variablesJson,requiredStr);
		String userId=(String)map.get("userId");
		map.remove("userId");
		int page_index=(Integer)map.get("page_index");
		map.remove("page_index");
		int pageSize=(Integer)map.get("pageSize");
		map.remove("pageSize");
		Page page=new Page();
		page.setStart_index(page_index);
		page.setPageSize(pageSize);
		Long count=queryWorkListService.conutHisList(userId, map);
		return "{\"count\":\""+count+"\"}";
	}

	@Override
	public String queryFinishHisList(String _variablesJson) throws BpmException {
		String requiredStr="userId,page_index,pageSize";
		Map<String, Object> map=getInputDate(_variablesJson,requiredStr);
		String userId=(String)map.get("userId");
		map.remove("userId");
		int page_index=(Integer)map.get("page_index");
		map.remove("page_index");
		int pageSize=(Integer)map.get("pageSize");
		map.remove("pageSize");
		Page page=new Page();
		page.setStart_index(page_index);
		page.setPageSize(pageSize);
		ActHisProcessListPage app=queryWorkListService.queryFinishHisList(userId, map,page);
		return object2Jackson(app);
	}

	@Override
	public String queryhisListAll(String _variablesJson) throws BpmException {
		String requiredStr="userId,page_index,pageSize";
		Map<String, Object> map=getInputDate(_variablesJson,requiredStr);
		String userId=(String)map.get("userId");
		map.remove("userId");
		int page_index=(Integer)map.get("page_index");
		map.remove("page_index");
		int pageSize=(Integer)map.get("pageSize");
		map.remove("pageSize");
		Page page=new Page();
		page.setStart_index(page_index);
		page.setPageSize(pageSize);
		ActHisProcessListPage app=queryWorkListService.queryhisListAll(map,page);
		return object2Jackson(app);
	}

	@Override
	public String queryHisTaskList(String _variablesJson) throws BpmException {
		String requiredStr="userId,processId,page_index,pageSize";
		Map<String, Object> map=getInputDate(_variablesJson,requiredStr);
		String userId=(String)map.get("userId");
		map.remove("userId");
		String processId=(String)map.get("processId");
		map.remove("processId");
		int page_index=(Integer)map.get("page_index");
		map.remove("page_index");
		int pageSize=(Integer)map.get("pageSize");
		map.remove("pageSize");
		Page page=new Page();
		page.setStart_index(page_index);
		page.setPageSize(pageSize);
		ActHisActInstListPage atp=queryWorkListService.queryHisTaskList(processId, map,page);
		return object2Jackson(atp);
	}

	@Override
	public String countWorkForTemplate(String _variablesJson) throws BpmException  {
		String requiredStr="userId,page_index,pageSize";
		Map<String, Object> map=getInputDate(_variablesJson,requiredStr);
		String userId=(String)map.get("userId");
		map.remove("userId");
		int page_index=(Integer)map.get("page_index");
		map.remove("page_index");
		int pageSize=(Integer)map.get("pageSize");
		map.remove("pageSize");
		Page page=new Page();
		page.setStart_index(page_index);
		page.setPageSize(pageSize);
		Map returnMap=queryWorkListService.countWorkForTemplate(userId);
		return map2Jackson(returnMap);
	}

	@Override
	public String countCandiWorkByTemplate(String _variablesJson) throws BpmException  {
		String requiredStr="userId,page_index,pageSize";
		Map<String, Object> map=getInputDate(_variablesJson,requiredStr);
		String userId=(String)map.get("userId");
		map.remove("userId");
		int page_index=(Integer)map.get("page_index");
		map.remove("page_index");
		int pageSize=(Integer)map.get("pageSize");
		map.remove("pageSize");
		Page page=new Page();
		page.setStart_index(page_index);
		page.setPageSize(pageSize);
		Map returnMap=queryWorkListService.countCandiWorkByTemplate(userId);
		return map2Jackson(returnMap);
	}

}
