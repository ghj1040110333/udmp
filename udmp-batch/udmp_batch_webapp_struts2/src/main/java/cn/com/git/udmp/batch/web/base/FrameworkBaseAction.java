package cn.com.git.udmp.batch.web.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import cn.com.git.udmp.batch.web.base.entity.AjaxReturnMsg;
import cn.com.git.udmp.common.mapper.JsonMapper;
import cn.com.git.udmp.component.model.Constants;
import cn.com.git.udmp.component.model.CurrentPage;
import cn.com.git.udmp.component.model.PageInfo;
import cn.com.git.udmp.core.logging.ILog;

/**
 * @description Action抽象基类 前端所有Action必须继承此基类
 * @author wulei_wb 
 * @date 2014年11月28日 上午11:29:48
 */
public abstract class FrameworkBaseAction extends ActionSupport implements  RequestAware, SessionAware, ILog {
    /**
     * @Fields serialVersionUID : UUID
     */
    private static final long serialVersionUID = 2082066641165327119L;
    /**
     * @Fields userflag : 标记用户是否首次进入菜单
     */
    public String userflag;
    /**
     * @Fields requestMap : request Map
     */
    protected Map requestMap;
    /**
     * @Fields sessionMap : session Map
     */
    protected Map sessionMap;
    /**
     * @Fields nciHeadVo : 交易管理公共头文件vo，为保证struts自动封装表单赋值
     *         对象名同/udmp-web/src/main/resources
     *         /META-INF/resources/udmp/pages/common/nciHeader.jsp对象前缀
     */

    /**
     * @Fields pageInfo : 分页信息
     */
    protected PageInfo pageInfo = new PageInfo();

    /**
     * @Fields currentPage : 分页信息
     */
    protected CurrentPage currentPage = new CurrentPage();

    /**
     * @Fields pageNum : 当前页
     */
    protected int pageNum = Constants.PAGE_NUM;
    /**
     * @Fields numPerPage : 每页显示多少条
     */
    protected int numPerPage = Constants.NUM_PER_PAGE;
    /**
     * @Fields ajaxMsg : Ajax提交返回值
     */
    protected AjaxReturnMsg ajaxMsg = new AjaxReturnMsg();

    /**
     * @Fields errorMsg : 异常返回信息
     */
    protected String errorMsg = "";
    /**
     * @Fields pid : pid
     */
    protected String pid;
    /**
     * @Fields userid : 用户代码
     */
    protected String userid;
    /**
     * @Fields message : 返回信息
     */
    protected String message;
    /**
     * @Fields menuId : 菜单ID
     */
    protected String menuId;
    
    /**
     * @Fields orderField : 排序字段
     */
    private String orderField;
    /**
     * @Fields orderDirection : 排序规则(升序/降序)
     */
    private String orderDirection;
    /**
     * @Fields messageStatusMap : 码表map
     */
    private Map<String, String> messageStatusMap = new HashMap<String, String>();


    /**
     * @description 获取当前页面内容
     * @version
     * @title
     * @author tanzhiliang tanzl_wb@newchinalife.com
     * @return 页对象
    */
    public CurrentPage getCurrentPage() {
        currentPage.setPageNo(pageNum);
        currentPage.setPageSize(numPerPage);
        return currentPage;
    }

    public void setCurrentPage(CurrentPage currentPage) {
        this.currentPage = currentPage;
    }

    public Map<String, String> getMessageStatusMap() {
        return messageStatusMap;
    }

    public void setMessageStatusMap(Map<String, String> messageStatusMap) {
        this.messageStatusMap = messageStatusMap;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getUserflag() {
        return userflag;
    }

    public void setUserflag(String userflag) {
        this.userflag = userflag;
    }

    public String getOrderField() {
        return orderField;
    }

    /**
     * @description 设置排序字段
     * @version
     * @title
     * @author tanzhiliang tanzl_wb@newchinalife.com
     * @param orderField 排序字段
    */
    public void setOrderField(String orderField) {
        pageInfo.setOrderField(orderField);
    }

    public String getOrderDirection() {
        return orderDirection;
    }

    /**
     * @description 设置排序规则
     * @version
     * @title
     * @author tanzhiliang tanzl_wb@newchinalife.com
     * @param orderDirection 排序规则
    */
    public void setOrderDirection(String orderDirection) {
        pageInfo.setOrderDirection(orderDirection);
    }

    public HttpSession getSession() {
        return getRequest().getSession();
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getNumPerPage() {
        return numPerPage;
    }

    public void setNumPerPage(int numPerPage) {
        this.numPerPage = numPerPage;
    }

    /**
     * @description 获取页面信息
     * @version
     * @title
     * @author tanzhiliang tanzl_wb@newchinalife.com
     * @return 页面信息
    */
    public PageInfo getPageInfo() {
        pageInfo.setCurrentPage(pageNum);
        pageInfo.setNumPerPage(numPerPage);
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public AjaxReturnMsg getAjaxMsg() {
        return ajaxMsg;
    }

    public void setAjaxMsg(AjaxReturnMsg ajaxMsg) {
        this.ajaxMsg = ajaxMsg;
    }

    /**
     * @description 得到BizId
     * @version
     * @title
     * @author wulei_wb wulei_wb@newchinalife.com
     * @return String BizId
     */
    public abstract String getBizId();

    @Override
    public void setRequest(Map request) {
        this.requestMap = request;
    }

    @Override
    public void setSession(Map session) {
        this.sessionMap = session;
    }

    /**
     * @description 获取HttpServletRequest
     * @version
     * @title
     * @author wulei_wb wulei_wb@newchinalife.com
     * @return HttpServletRequest
     */
    public HttpServletRequest getRequest() {
        return ServletActionContext.getRequest();
    }

    /**
     * @description 获取HttpServletResponse
     * @version
     * @title
     * @author wulei_wb wulei_wb@newchinalife.com
     * @return HttpServletResponse
     */
    public HttpServletResponse getResponse() {
        return ServletActionContext.getResponse();
    }

    /**
     * @description 得到web应用服务器的ServletContext
     * @version
     * @title
     * @author wulei_wb wulei_wb@newchinalife.com
     * @return ServletContext
     */
    public ServletContext getServletContext() {
        // 得到一个ServletContext对象
        return ServletActionContext.getServletContext();
    }

    /**
     * @description 得到当前session内对象 当前Session数据在Session数据结构中内存储的名字是currentSession
     * @version
     * @title
     * @author wulei_wb wulei_wb@newchinalife.com
     * @return Object
     */
    protected Object getCurrentSession() {
        HttpSession session = getRequest().getSession();
        if (session != null) {
            return session.getAttribute(Constants.CURRENT_SESSION);
        } else {
            return null;
        }
    }

    /**
     * @description 获得URL
     * @version
     * @title
     * @author wulei_wb wulei_wb@newchinalife.com
     * @return String URL
     */
    protected String getUrl() {
        return getRequest().getRequestURI() + "?" + getRequest().getQueryString();
    }

    /**
     * @description 通过session临时传递参数
     * @version
     * @title
     * @author wulei_wb wulei_wb@newchinalife.com
     * @param parmaMap 临时参数
     */
    protected void putParams(Map<String, String> parmaMap) {
        getRequest().getSession().setAttribute("params", parmaMap);
    }

    /**
     * @description 获取session临时传递的参数，获取参数后直接从session中删除该参数 临时数据属性名称 params
     * @version
     * @title
     * @author wulei_wb wulei_wb@newchinalife.com
     * @return Object 临时传递的参数
     */
    protected Object getParams() {
        Object obj = getRequest().getSession().getAttribute(Constants.CURRENT_SESSION_TMP_PARAM);
        getRequest().getSession().removeAttribute(Constants.CURRENT_SESSION_TMP_PARAM);
        return obj;
    }

    /**
     * @description 获取通过URL传递的参数对象
     * @version
     * @title
     * @author wulei_wb wulei_wb@newchinalife.com
     * @return Map 参数对象
     */
    protected Map getParamsMap() {
        Map params = getRequest().getParameterMap();
        Map retMap = new HashMap();
        Set<String> keySet = params.keySet();
        for (String key : keySet) {
            String param = "";
            Object paramObject = params.get(key);
            if (paramObject instanceof String) {
                param = (String) paramObject;
            } else if (paramObject instanceof String[]) {
                param = (Arrays.toString((String[]) paramObject)).substring(1,
                        (Arrays.toString((String[]) paramObject)).length() - 1);
            } else {
                logger.warn("参数[" + key + "]的类型为[" + paramObject.getClass().getCanonicalName() + "]无法继续传递！");
            }
            retMap.put(key, param);
        }
        logger.debug("解析后页面参数为：" + retMap);
        return retMap;
    }


    /**
     * @description 将单个对象转换json输出，非集合类
     * @version
     * @title
     * @author wulei_wb wulei_wb@newchinalife.com
     * @param obj Object 单个对象
     */
    public void outJson(Object obj) {
        String str = "";
        try {
            this.getResponse().setCharacterEncoding("UTF-8");
            PrintWriter out = getResponse().getWriter();
            str = JsonMapper.toJsonString(obj);
            logger.debug("return JSONObject:" + str);
            out.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @description 将集合转化成json并输出
     * @version
     * @title
     * @author wulei_wb wulei_wb@newchinalife.com
     * @param obj Object 集合
     */
    public void outJsonCon(Object obj) {
        String str = "";
        try {
            this.getResponse().setCharacterEncoding("UTF-8");
            PrintWriter out = getResponse().getWriter();
            str = JsonMapper.toJsonString(obj);
            logger.debug("return JSONObject:...." + str);
            out.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @description DWZ 公共的ajax返回
     * @version
     * @title
     * @author wulei_wb wulei_wb@newchinalife.com
     * @param statusCode 状态码
     * @param returnMsg 返回信息
     * @param navTabId String
     * @param forwardUrl 提交后跳转的地址 当callbackType设置为forward
     * @param callbackType 回调类型 closeCurrent forward
     */
    public void outAjax(String statusCode, String returnMsg, String navTabId, String forwardUrl, String callbackType) {
        ajaxMsg.setStatusCode(statusCode);
        ajaxMsg.setMessage(returnMsg);
        ajaxMsg.setNavTabId(navTabId); // 返回界面需要
        ajaxMsg.setForwardUrl(forwardUrl);
        ajaxMsg.setCallbackType(callbackType); // closeCurrent
        ajaxMsg.setRel(""); // 返回界面需要
        outJson(ajaxMsg);
    }

    /**
     * @description DWZ 公共的ajax返回 有 rel参数
     * @version
     * @title
     * @author wulei_wb wulei_wb@newchinalife.com
     * @param statusCode 状态码
     * @param returnMsg 返回信息
     * @param navTabId String
     * @param forwardUrl 提交后跳转的地址 当callbackType设置为forward
     * @param callbackType 回调类型 closeCurrent forward
     * @param rel String
     */
    public void outAjaxRel(String statusCode, String returnMsg, String navTabId, String forwardUrl, String callbackType,
            String rel) {

        ajaxMsg.setStatusCode(statusCode);
        ajaxMsg.setMessage(returnMsg);
        ajaxMsg.setNavTabId(navTabId); // 返回界面需要
        ajaxMsg.setForwardUrl(forwardUrl);
        ajaxMsg.setCallbackType(callbackType); // closeCurrent
        ajaxMsg.setRel(rel); // 返回界面需要
        outJson(ajaxMsg);
    }
}
