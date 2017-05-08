package activiti_maven_project.com.git.common.vo;

import java.util.List;

import activiti_maven_project.com.git.common.Page;

public class ActHisActInstListPage {
	
	private List<ActHisActInstListVO> actHisActInstListVOs;
	
	private String parentProcessId;
	
	private Page page;

	
	
	public String getParentProcessId() {
		return parentProcessId;
	}

	public void setParentProcessId(String parentProcessId) {
		this.parentProcessId = parentProcessId;
	}

	public List<ActHisActInstListVO> getActHisActInstListVOs() {
		return actHisActInstListVOs;
	}

	public void setActHisActInstListVOs(List<ActHisActInstListVO> actHisActInstListVOs) {
		this.actHisActInstListVOs = actHisActInstListVOs;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	
	
}
