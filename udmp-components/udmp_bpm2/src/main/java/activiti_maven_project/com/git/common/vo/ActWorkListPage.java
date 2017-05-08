package activiti_maven_project.com.git.common.vo;

import java.util.List;

import activiti_maven_project.com.git.common.Page;

public class ActWorkListPage {
	
	private List<ActWorkListVO> actWorkListVOs;
	
	private Page page;


	

	public List<ActWorkListVO> getActWorkListVOs() {
		return actWorkListVOs;
	}

	public void setActWorkListVOs(List<ActWorkListVO> actWorkListVOs) {
		this.actWorkListVOs = actWorkListVOs;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	
	
}
