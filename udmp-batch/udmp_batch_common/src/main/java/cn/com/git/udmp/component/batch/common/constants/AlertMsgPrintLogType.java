package cn.com.git.udmp.component.batch.common.constants;

/**
 * 报警日志打印位置常量
 * 值域：
 * P-Req：渠道请求日志
 * P-Res：ESB响应渠道日志
 * R-Req：ESB请求后端日志
 * R-Res：后端响应ESB日志
 * OSB-COMM：平台异常日志
 * @description 报警日志打印位置常量
 * @author bihb bihb_wb@newchinalife.com
 * @date 2015年4月15日 下午2:54:50  
 */
public final class AlertMsgPrintLogType {
    /**
     * @Fields P_REQ : P-Req：渠道请求日志
     */ 
    public static final String P_REQ = "P-Req";
    /**
     * @Fields P_RES : P-Res：ESB响应渠道日志
     */ 
    public static final String P_RES = "P-Res";
    /**
     * @Fields R_REQ : R-Req：ESB请求后端日志
     */ 
    public static final String R_REQ = "R-Req";
    /**
     * @Fields R_RES : R-Res：后端响应ESB日志
     */ 
    public static final String R_RES = "R-Res";
    /**
     * @Fields OSB_COMM : OSB-COMM：平台异常日志
     */ 
    public static final String OSB_COMM = "OSB-COMM";
}
