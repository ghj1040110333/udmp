package cn.com.git.udmp.component.batch.common.constants;

/**
 * 告警机制常量 NOP-空操作；SMS-短信；MAIL-邮件；JMS-监控平台；COMP-聚合操作；
 * @description 告警机制常量
 * @author bihb bihb_wb@newchina.live
 * @date 2015年4月13日 下午3:25:46
 */
public final class AlertType {
    /**
     * @Fields NOP : 空操作
     */
    public static final String NOP = "NOP";
    /** 
     * @Fields SMS : 短信
     */ 
    public static final String SMS = "SMS";
    /** 
     * @Fields MAIL : 邮件
     */ 
    public static final String MAIL = "MAIL";
    /** 
     * @Fields JMS : 监控平台
     */ 
    public static final String JMS = "JMS";
    /** 
     * @Fields COMP : 聚合操作
     */ 
    public static final String COMP = "COMP";

}
