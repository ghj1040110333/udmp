package activiti_maven_project.com.git.common.vo;

import java.util.ArrayList;
import java.util.List;

public class SelectUserInfos  extends BaseVO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<SelectUserInfo> selectUserInfos=new ArrayList<SelectUserInfo>();
	
	public List<SelectUserInfo> getSelectUserInfos() {
		return selectUserInfos;
	}

	public void setSelectUserInfos(List<SelectUserInfo> selectUserInfos) {
		this.selectUserInfos = selectUserInfos;
	}

	@Override
	public String toString() {
		return "SelectUserInfos [selectUserInfos=" + selectUserInfos + "]";
	}
	
	
	
	
}
