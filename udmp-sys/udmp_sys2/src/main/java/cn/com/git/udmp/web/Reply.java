package cn.com.git.udmp.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.git.udmp.common.web.CacheData;
import cn.com.git.udmp.common.web.Paginator;

public class Reply {
	private Paginator page = null;
	private ReturnCode returnCode = new ReturnCode();
	private Map<String, Integer> showModel = new HashMap<String, Integer>();
	private Object biz;
	private Object menuTree;
	private CacheData cacheData;//缓存数据
	private String userOrgId;
	private String userOrgName;
	private String userId;//用户ID
	private String userName;
	private String userRealName;
	private List<?> pageItems ;
	private List<?> bizList;
	
	private List<Org> orgList;//机构数据
	
	
	public String getUserOrgName() {
		return userOrgName;
	}
	public void setUserOrgName(String userOrgName) {
		this.userOrgName = userOrgName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserRealName() {
		return userRealName;
	}
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	public List<?> getBizList() {
		return bizList;
	}
	public void setBizList(List<?> bizList) {
		this.bizList = bizList;
	}
	public List<Org> getOrgList() {
		return orgList;
	}
	public void setOrgList(List<Org> orgList) {
		this.orgList = orgList;
	}
	public CacheData getCacheData() {
		return cacheData;
	}
	public void setCacheData(CacheData cacheData) {
		this.cacheData = cacheData;
	}
	
	public Object getMenuTree() {
		return menuTree;
	}
	public void setMenuTree(Object menuTree) {
		this.menuTree = menuTree;
	}
	public String getUserOrgId() {
		return userOrgId;
	}
	public void setUserOrgId(String userOrgId) {
		this.userOrgId = userOrgId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Paginator getPage() {
		return page;
	}
	public void setPage(Paginator page) {
		this.page = page;
	}
	public ReturnCode getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(ReturnCode returnCode) {
		this.returnCode = returnCode;
	}
	public Map<String, Integer> getShowModel() {
		return showModel;
	}
	public void setShowModel(Map<String, Integer> showModel) {
		this.showModel = showModel;
	}
	public Object getBiz() {
		return biz;
	}
	public void setBiz(Object biz) {
		this.biz = biz;
	}
	public List<?> getPageItems() {
		return pageItems;
	}
	public void setPageItems(List<?> pageItems) {
		this.pageItems = pageItems;
	}
	
}
