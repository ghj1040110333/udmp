package cn.com.git.udmp.component.model;

import java.util.ArrayList;
import java.util.List;


/**
 * @description 页面信息对象 
 * 
 * <pre>
 * 历史记录：v1.0 2012-8-14
 * @author LiAnDong
 * @createDate Aug 14, 2012 
 * </pre>
 */
public class PageInfo implements java.io.Serializable {

    /**
     * 2014-3-28 yuqiangqiang 栏位说明
     */
    private static final long serialVersionUID = 6495479857682518429L;
    private int totalCount; // 总条数
    private int numPerPage = 20; // 每页显示多少条
    private int pageNumShown; // 页标数字多少个
    private int currentPage = 1; // 当前是第几页
    private int offset = Constants.START_MESSAGE; // 起始条数
    private List valueList = new ArrayList(); // 存放返回值列表

    // 排序字段
    private String orderField;
    // 排序规则(升序/降序)
    private String orderDirection;

    /** 
     * <p>Title:构造方法 </p> 
     * <p>Description: 页面信息 </p>  
    */
    public PageInfo() {

    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getNumPerPage() {
        return numPerPage;
    }

    public void setNumPerPage(int numPerPage) {
        this.numPerPage = numPerPage;
    }

    public int getPageNumShown() {
        return pageNumShown;
    }

    public void setPageNumShown(int pageNumShown) {
        this.pageNumShown = pageNumShown;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List getValueList() {
        return valueList;
    }

    public void setValueList(List valueList) {
        this.valueList = valueList;
    }

    /**
     * @description 计算起始条数
     * @version
     * @title
     * @author tanzhiliang tanzl_wb@newchinalife.com
     * @return 起始条数
    */
    public int getOffset() {
        // 计算出起始条数
        offset = numPerPage * (currentPage - 1);
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public String getOrderDirection() {
        return orderDirection;
    }

    public void setOrderDirection(String orderDirection) {
        this.orderDirection = orderDirection;
    }

}
