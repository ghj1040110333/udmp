package cn.com.git.udmp.common.web;

import cn.com.git.udmp.common.model.DataObject;

/**
 * 分页数据对象
 * @description 分页数据对象,用于前端Table UI的分页
 * @see Page
 * @author Spring Cao
 * @date 2016年8月23日 下午3:58:18
 */
public class Paginator extends DataObject{
	/**
	 * 设置Paginator对象的默认每页记录数为-1
	 */
	public static final int DEFAULT_RECORDS_PER_PAGE = -1;
	/**
	 * Paginator对象未设置recordsPerPage时，将recordsPerPage调整为20计算TotalPage、BeginIndex、EndIndex
	 */
	public static final int RECORDS_PER_PAGE = 20;
	private int recordsPerPage = RECORDS_PER_PAGE;// 每页显示记录数
	private int pageNo=1;// 页号，从1开始计数
	private int total=0;
    private boolean doPagination=true;// 是否执行分页（有些场景下是不需要分页的）：true表示分页，false表示不分页
    private boolean queryTotal=true;// 是否查询总记录数（有些场合不需要查询总记录数，比如只查询第1页）：true表示查询，false表示不查询
    private boolean queryLastIfPageNoExceed=true;// 当指定页编号超出最大值时查询最后一页
    private int beginIndex;
    private int endIndex;
    private int totalPage;
	public int getRecordsPerPage() {
		return recordsPerPage;
	}
	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public boolean isDoPagination() {
		return doPagination;
	}
	public void setDoPagination(boolean doPagination) {
		this.doPagination = doPagination;
	}
	public boolean isQueryTotal() {
		return queryTotal;
	}
	public void setQueryTotal(boolean queryTotal) {
		this.queryTotal = queryTotal;
	}
	public boolean isQueryLastIfPageNoExceed() {
		return queryLastIfPageNoExceed;
	}
	public void setQueryLastIfPageNoExceed(boolean queryLastIfPageNoExceed) {
		this.queryLastIfPageNoExceed = queryLastIfPageNoExceed;
	}
	public int getBeginIndex() {
		return beginIndex;
	}
	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}
	public int getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
    
    
    
    
}
