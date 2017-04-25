package cn.com.git.udmp.component.batch.event.alert;

import java.io.Serializable;

/**
 * 报警消息
 * 
 * @description 报警消息
 * @author bihb bihb_wb@newchinalife.com
 * @date 2015年4月13日 下午3:33:17
 */
public class AlertMsg implements Serializable {
    /**
     * @Fields serialVersionUID : serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    /**
     * @Fields serverName : 服务工作结点名,char-30,赋值说明：处理此服务请求的ESB工作结点名
     */
    private String serverName;
    /**
     * @Fields msgId : 前端流水号,char-36,赋值说明：标识唯一一笔业务，用于记录、跟踪消息处理过程
     */
    private String msgId;
    /**
     * @Fields msgDate :
     *         前端系统系统日期,char-10,赋值说明：前端系统填写，ESB原值返回，前端系统的服务器日期，数据格式：YYYY-MM-DD
     */
    private String msgDate;
    /**
     * @Fields msgTime :
     *         前端系统系统时间,char-12,赋值说明：前端系统填写，ESB原值返回，前端系统的服务器时间，数据格式：HH:mm:ss.SSS
     */
    private String msgTime;
    /**
     * @Fields servCd : 发布服务编码,char-12,赋值说明：前端系统填写，ESB原值返回，前置系统发布的服务编码
     */
    private String servCd;
    /**
     * @Fields sysCd : 前端系统系统编码，char-12,赋值说明：前端系统填写，ESB原值返回
     */
    private String sysCd;
    /**
     * @Fields orgCd : 交易机构码,char-8,赋值说明：前端系统填写，ESB原值返回
     */
    private String orgCd;
    /**
     * @Fields resCd : 技术响应代码,char-6,赋值说明：返回给服务提供方系统的响应码信息
     */
    private String resCd;
    /**
     * @Fields resText : 技术响应信息描述,char-128,赋值说明：返回给服务消费方系统的响应信息描述
     */
    private String resText;
    /**
     * @Fields childSRVCode : 后端服务码,char-12,赋值说明：无（预留字段？）
     */
    private String childSRVCode;
    /**
     * @Fields childSRVOwner : 后端服务系统,char-12,赋值说明：无（预留字段？）
     */
    private String childSRVOwner;
    /**
     * @Fields requestTime :
     *         前端系统调用服务的前置时间,char-23,赋值说明：数据格式：2013-11-04T18:42:07.015
     */
    private String requestTime;
    /**
     * @Fields responseTime :
     *         前端系统得到响应的前置时间,char-23,赋值说明：数据格式：2013-11-04T18:42:07.015
     */
    private String responseTime;
    /**
     * @Fields invokeTime :
     *         前置调用后台服务的前置时间,char-23,赋值说明：数据格式：2013-11-04T18:42:07.015
     */
    private String invokeTime;
    /**
     * @Fields replyTime :
     *         前置得到后台响应的前置时间,char-23,赋值说明：数据格式：2013-11-04T18:42:07.015
     */
    private String replyTime;
    /**
     * @Fields bizId : 业务主键,char-20,赋值说明：业务场景编码参见服务管理平台
     */
    private String bizId;
    /**
     * @Fields earlyWarning : 是否预警标识,char-1,赋值说明：0：不预警,1：预警
     */
    private String earlyWarning;

    /**
     * 返回earlyWarning的属性值
     * 
     * @description 返回earlyWarning的属性值
     * @version 1.0
     * @title 返回serverName的属性值
     * @author wulei_wb wulei_wb@newchinalife.com
     * @return 返回earlyWarning的属性值
     */
    public String getEarlyWarning() {
        return earlyWarning;
    }

    /**
     * 是否预警标识,char-1,赋值说明：0：不预警,1：预警
     * 
     * @description 给earlyWarning属性赋值
     * @version 1.0
     * @title 给earlyWarning属性赋值
     * @author wulei_wb wulei_wb@newchinalife.com
     * @param earlyWarning earlyWarning属性
     */
    public void setEarlyWarning(String earlyWarning) {
        this.earlyWarning = earlyWarning;
    }

    /**
     * 返回serverName的属性值
     * 
     * @description 返回serverName的属性值
     * @version 1.0
     * @title 返回serverName的属性值
     * @author bihb bihb_wb@newchinalife.com
     * @return 返回获取serverName的属性值
     */
    public String getServerName() {
        return serverName;
    }

    /**
     * 服务工作结点名,char-30,赋值说明：处理此服务请求的ESB工作结点名
     * 
     * @description 给serverName属性赋值
     * @version 1.0
     * @title 给serverName属性赋值
     * @author bihb bihb_wb@newchinalife.com
     * @param serverName serverName属性
     */
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    /**
     * 返回msgId的属性值
     * 
     * @description 返回msgId的属性值
     * @version 1.0
     * @title 返回msgId的属性值
     * @author bihb bihb_wb@newchinalife.com
     * @return 返回获取msgId的属性值
     */
    public String getMsgId() {
        return msgId;
    }

    /**
     * 前端流水号,char-36,赋值说明：标识唯一一笔业务，用于记录、跟踪消息处理过程
     * 
     * @description 给msgId属性赋值
     * @version 1.0
     * @title 给msgId属性赋值
     * @author bihb bihb_wb@newchinalife.com
     * @param msgId msgId属性
     */
    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    /**
     * 返回msgDate的属性值
     * 
     * @description 返回msgDate的属性值
     * @version 1.0
     * @title 返回msgDate的属性值
     * @author bihb bihb_wb@newchinalife.com
     * @return 返回获取msgDate的属性值
     */
    public String getMsgDate() {
        return msgDate;
    }

    /**
     * 前端系统系统日期,char-10,赋值说明：前端系统填写，ESB原值返回，前端系统的服务器日期，数据格式：YYYY-MM-DD
     * 
     * @description 给msgDate属性赋值
     * @version 1.0
     * @title 给msgDate属性赋值
     * @author bihb bihb_wb@newchinalife.com
     * @param msgDate msgDate属性
     */
    public void setMsgDate(String msgDate) {
        this.msgDate = msgDate;
    }

    /**
     * 返回msgTime的属性值
     * 
     * @description 返回msgTime的属性值
     * @version 1.0
     * @title 返回msgTime的属性值
     * @author bihb bihb_wb@newchinalife.com
     * @return 返回获取msgTime的属性值
     */
    public String getMsgTime() {
        return msgTime;
    }

    /**
     * 前端系统系统时间,char-12,赋值说明：前端系统填写，ESB原值返回，前端系统的服务器时间，数据格式：HH:mm:ss.SSS
     * 
     * @description 给msgTime属性赋值
     * @version 1.0
     * @title 给msgTime属性赋值
     * @author bihb bihb_wb@newchinalife.com
     * @param msgTime msgTime属性
     */
    public void setMsgTime(String msgTime) {
        this.msgTime = msgTime;
    }

    /**
     * 返回servCd的属性值
     * 
     * @description 返回servCd的属性值
     * @version 1.0
     * @title 返回servCd的属性值
     * @author bihb bihb_wb@newchinalife.com
     * @return 返回获取servCd的属性值
     */
    public String getServCd() {
        return servCd;
    }

    /**
     * 发布服务编码,char-12,赋值说明：前端系统填写，ESB原值返回，前置系统发布的服务编码
     * 
     * @description 给servCd属性赋值
     * @version 1.0
     * @title 给servCd属性赋值
     * @author bihb bihb_wb@newchinalife.com
     * @param servCd servCd属性
     */
    public void setServCd(String servCd) {
        this.servCd = servCd;
    }

    /**
     * 返回sysCd的属性值
     * 
     * @description 返回sysCd的属性值
     * @version 1.0
     * @title 返回sysCd的属性值
     * @author bihb bihb_wb@newchinalife.com
     * @return 返回获取sysCd的属性值
     */
    public String getSysCd() {
        return sysCd;
    }

    /**
     * 前端系统系统编码，char-12,赋值说明：前端系统填写，ESB原值返回
     * 
     * @description 给sysCd属性赋值
     * @version 1.0
     * @title 给sysCd属性赋值
     * @author bihb bihb_wb@newchinalife.com
     * @param sysCd sysCd属性
     */
    public void setSysCd(String sysCd) {
        this.sysCd = sysCd;
    }

    /**
     * 返回orgCd的属性值
     * 
     * @description 返回orgCd的属性值
     * @version 1.0
     * @title 返回orgCd的属性值
     * @author bihb bihb_wb@newchinalife.com
     * @return 返回获取orgCd的属性值
     */
    public String getOrgCd() {
        return orgCd;
    }

    /**
     * 交易机构码,char-8,赋值说明：前端系统填写，ESB原值返回
     * 
     * @description 给orgCd属性赋值
     * @version 1.0
     * @title 给orgCd属性赋值
     * @author bihb bihb_wb@newchinalife.com
     * @param orgCd orgCd属性
     */
    public void setOrgCd(String orgCd) {
        this.orgCd = orgCd;
    }

    /**
     * 返回resCd的属性值
     * 
     * @description 返回resCd的属性值
     * @version 1.0
     * @title 返回resCd的属性值
     * @author bihb bihb_wb@newchinalife.com
     * @return 返回获取resCd的属性值
     */
    public String getResCd() {
        return resCd;
    }

    /**
     * 技术响应代码,char-6,赋值说明：返回给服务提供方系统的响应码信息
     * 
     * @description 给resCd属性赋值
     * @version 1.0
     * @title 给resCd属性赋值
     * @author bihb bihb_wb@newchinalife.com
     * @param resCd resCd属性
     */
    public void setResCd(String resCd) {
        this.resCd = resCd;
    }

    /**
     * 返回resText的属性值
     * 
     * @description 返回resText的属性值
     * @version 1.0
     * @title 返回resText的属性值
     * @author bihb bihb_wb@newchinalife.com
     * @return 返回获取resText的属性值
     */
    public String getResText() {
        return resText;
    }

    /**
     * 技术响应信息描述,char-128,赋值说明：返回给服务消费方系统的响应信息描述
     * 
     * @description 给resText属性赋值
     * @version 1.0
     * @title 给resText属性赋值
     * @author bihb bihb_wb@newchinalife.com
     * @param resText resText属性
     */
    public void setResText(String resText) {
        this.resText = resText;
    }

    /**
     * 返回childSRVCode的属性值
     * 
     * @description 返回childSRVCode的属性值
     * @version 1.0
     * @title 返回childSRVCode的属性值
     * @author bihb bihb_wb@newchinalife.com
     * @return 返回获取childSRVCode的属性值
     */
    public String getChildSRVCode() {
        return childSRVCode;
    }

    /**
     * 后端服务码,char-12,赋值说明：无（预留字段？）
     * 
     * @description 给childSRVCode属性赋值
     * @version 1.0
     * @title 给childSRVCode属性赋值
     * @author bihb bihb_wb@newchinalife.com
     * @param childSRVCode childSRVCode属性
     */
    public void setChildSRVCode(String childSRVCode) {
        this.childSRVCode = childSRVCode;
    }

    /**
     * 返回childSRVOwner的属性值
     * 
     * @description 返回childSRVOwner的属性值
     * @version 1.0
     * @title 返回childSRVOwner的属性值
     * @author bihb bihb_wb@newchinalife.com
     * @return 返回获取childSRVOwner的属性值
     */
    public String getChildSRVOwner() {
        return childSRVOwner;
    }

    /**
     * 后端服务系统,char-12,赋值说明：无（预留字段？）
     * 
     * @description 给childSRVOwner属性赋值
     * @version 1.0
     * @title 给childSRVOwner属性赋值
     * @author bihb bihb_wb@newchinalife.com
     * @param childSRVOwner childSRVOwner属性
     */
    public void setChildSRVOwner(String childSRVOwner) {
        this.childSRVOwner = childSRVOwner;
    }

    /**
     * 返回requestTime的属性值
     * 
     * @description 返回requestTime的属性值
     * @version 1.0
     * @title 返回requestTime的属性值
     * @author bihb bihb_wb@newchinalife.com
     * @return 返回获取requestTime的属性值
     */
    public String getRequestTime() {
        return requestTime;
    }

    /**
     * 前端系统调用服务的前置时间,char-23,赋值说明：数据格式：2013-11-04T18:42:07.015
     * 
     * @description 给requestTime属性赋值
     * @version 1.0
     * @title 给requestTime属性赋值
     * @author bihb bihb_wb@newchinalife.com
     * @param requestTime requestTime属性
     */
    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    /**
     * 返回responseTime的属性值
     * 
     * @description 返回responseTime的属性值
     * @version 1.0
     * @title 返回responseTime的属性值
     * @author bihb bihb_wb@newchinalife.com
     * @return 返回获取responseTime的属性值
     */
    public String getResponseTime() {
        return responseTime;
    }

    /**
     * 前端系统得到响应的前置时间,char-23,赋值说明：数据格式：2013-11-04T18:42:07.015
     * 
     * @description 给responseTime属性赋值
     * @version 1.0
     * @title 给responseTime属性赋值
     * @author bihb bihb_wb@newchinalife.com
     * @param responseTime responseTime属性
     */
    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

    /**
     * 返回invokeTime的属性值
     * 
     * @description 返回invokeTime的属性值
     * @version 1.0
     * @title 返回invokeTime的属性值
     * @author bihb bihb_wb@newchinalife.com
     * @return 返回获取invokeTime的属性值
     */
    public String getInvokeTime() {
        return invokeTime;
    }

    /**
     * 前置调用后台服务的前置时间,char-23,赋值说明：数据格式：2013-11-04T18:42:07.015
     * 
     * @description 给invokeTime属性赋值
     * @version 1.0
     * @title 给invokeTime属性赋值
     * @author bihb bihb_wb@newchinalife.com
     * @param invokeTime invokeTime属性
     */
    public void setInvokeTime(String invokeTime) {
        this.invokeTime = invokeTime;
    }

    /**
     * 返回replyTime的属性值
     * 
     * @description 返回replyTime的属性值
     * @version 1.0
     * @title 返回replyTime的属性值
     * @author bihb bihb_wb@newchinalife.com
     * @return 返回获取replyTime的属性值
     */
    public String getReplyTime() {
        return replyTime;
    }

    /**
     * 前置得到后台响应的前置时间,char-23,赋值说明：数据格式：2013-11-04T18:42:07.015
     * 
     * @description 给replyTime属性赋值
     * @version 1.0
     * @title 给replyTime属性赋值
     * @author bihb bihb_wb@newchinalife.com
     * @param replyTime replyTime属性
     */
    public void setReplyTime(String replyTime) {
        this.replyTime = replyTime;
    }

    /**
     * 返回bizId的属性值
     * 
     * @description 返回bizId的属性值
     * @version 1.0
     * @title 返回bizId的属性值
     * @author bihb bihb_wb@newchinalife.com
     * @return 返回获取bizId的属性值
     */
    public String getBizId() {
        return bizId;
    }

    /**
     * 业务主键,char-20,赋值说明：业务场景编码参见服务管理平台
     * 
     * @description 给bizId属性赋值
     * @version 1.0
     * @title 给bizId属性赋值
     * @author bihb bihb_wb@newchinalife.com
     * @param bizId bizId属性
     */
    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

}
