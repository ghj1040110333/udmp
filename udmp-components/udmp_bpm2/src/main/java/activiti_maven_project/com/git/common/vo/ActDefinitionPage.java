package activiti_maven_project.com.git.common.vo;

import java.util.List;

import activiti_maven_project.com.git.common.Page;

public class ActDefinitionPage {
	
	private List<ActDefinitionVO> actDefinitionVOs;
	
	private Page page;

	public List<ActDefinitionVO> getActDefinitionVOs() {
		return actDefinitionVOs;
	}

	public void setActDefinitionVOs(List<ActDefinitionVO> actDefinitionVOs) {
		this.actDefinitionVOs = actDefinitionVOs;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	
	
}
