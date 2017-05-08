package activiti_maven_project.com.git.common.vo;

import java.util.List;

import activiti_maven_project.com.git.common.Page;

public class ActHisTaskListPage {
	
	private List<ActHisTaskListVO> actHisTaskListVOs;
	
	private Page page;





	public List<ActHisTaskListVO> getActHisTaskListVOs() {
		return actHisTaskListVOs;
	}

	public void setActHisTaskListVOs(List<ActHisTaskListVO> actHisTaskListVOs) {
		this.actHisTaskListVOs = actHisTaskListVOs;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	
	
}
