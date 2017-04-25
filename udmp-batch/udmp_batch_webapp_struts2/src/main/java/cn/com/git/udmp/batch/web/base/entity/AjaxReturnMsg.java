package cn.com.git.udmp.batch.web.base.entity;

import java.io.Serializable;

/** 
 * @description DWZ表单提交所需的返回信息，最终将以json的形式返回
 * @author LiAnDong
 * @date 2014年10月17日 下午3:43:38  
*/
public class AjaxReturnMsg implements Serializable {

    /** 
    * @Fields serialVersionUID :  序列号
    */ 
    private static final long serialVersionUID = 1732452786489251213L;

    /** 
    * @Fields statusCode :  状态码
    */ 
    private String statusCode; // 200 成功 300失败 301 超时*

    /** 
    * @Fields message : 提示信息  
    */ 
    private String message; // 提示信息

    /** 
    * @Fields navTabId :  标签页Id
    */ 
    private String navTabId;

    /** 
    * @Fields forwardUrl :  提交后跳转的地址
    */ 
    private String forwardUrl; // 提交后跳转的地址 当callbackType设置为forward

    /** 
    * @Fields callbackType :  回调类型
    */ 
    private String callbackType; // 回调类型 closeCurrent forward
    /** 
    * @Fields rel :  需要局部刷新的divId
    */ 
    private String rel;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNavTabId() {
        return navTabId;
    }

    public void setNavTabId(String navTabId) {
        this.navTabId = navTabId;
    }

    public String getForwardUrl() {
        return forwardUrl;
    }

    public void setForwardUrl(String forwardUrl) {
        this.forwardUrl = forwardUrl;
    }

    public String getCallbackType() {
        return callbackType;
    }

    public void setCallbackType(String callbackType) {
        this.callbackType = callbackType;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }
}
