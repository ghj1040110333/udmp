package cn.com.git.udmp.component.batch.common.constants;

/**
 * @description 批处理的常量类
 * @author liuliang liuliang_wb@newchina.live
 * @date 2015年7月8日 下午2:54:48
 */
public class BatchCommonConst {
    /**
     * @Fields DEFALUT_ID : 默认ID值
     */
    public static final String DEFALUT_ID = "0";

    /**
     * @Fields BATCH_AUDIT_LOG_SERVICE_Name : 审计服务类
     */
    public static final String BATCH_AUDIT_LOG_SERVICE_NAME = "batchAuditLogService";
    public static final String BATCH_QUARTZ_SCHEDULER_FACTORY_NAME = "quartzSchedulerFactory";

    /**
     * @Fields BATCH_MESSAGE_COMMAND_TYPE_REPORT : 通信报文中命令类型中的report模式
     */
    public static final String BATCH_MESSAGE_COMMAND_TYPE_REPORT = "REPORT";
    /**
     * @Fields BATCH_MESSAGE_COMMAND_TYPE_COMMAND : 通信报文中命令类型中的command模式
     */
    public static final String BATCH_MESSAGE_COMMAND_TYPE_COMMAND = "COMMAND";

    /**
     * @Fields MESSAGE_NAME_IN_CONTEXT : 上下文对象中message的存储名称
     */
    public static final String MESSAGE_NAME_IN_CONTEXT = "message";

    /**
     * @Fields BATCH_MESSAGE_COMMAND_FINISH : 指令模式中的FINISH指令-完成
     */
    public static final String BATCH_MESSAGE_COMMAND_FINISH = "FINISH";
    /**
     * @Fields BATCH_MESSAGE_COMMAND_EXCEPTION : 指令模式中的EXCEPTION指令-异常
     */
    public static final String BATCH_MESSAGE_COMMAND_EXCEPTION = "EXCEPTION";

    /**
     * @Fields BATCH_MESSAGE_COMMAND_STATUS : 指令模式中的STATUS指令-状态
     */
    public static final String BATCH_MESSAGE_COMMAND_STATUS = "STATUS";

    /**
     * @Fields BATCH_TRUE_FLAG : true对应的码值
     */
    public static final String BATCH_FLAG_TRUE = "1";

    /**
     * @Fields BATCH_FALSE_FLAG : false对应的码值
     */
    public static final String BATCH_FLAG_FALSE = "0";

    /**
     * @Fields BATCH_EMPTY_AGENT_ID :当可用代理为空时指向的空代理ID
     */
    public static final String BATCH_EMPTY_AGENT_ID = "-1";

    /**
     * @Fields BATCH_JOB_RELATION_PRE : 前置任务标志
     */
    public static final String BATCH_JOB_RELATION_PRE = "B";

    /**
     * @Fields BATCH_JOB_RELATION_AFT : 后置任务标志
     */
    public static final String BATCH_JOB_RELATION_AFT = "A";
    
    /** 
    * @Fields TIME_WINDOW_FORMAT : 时分秒时间转换的格式 
    */ 
    public static final String TIME_WINDOW_FORMAT="yyyy-MM-dd hh:mm:ss";
    
    /** 
     * @Fields BATCH_JOB_TYPE_QUARTZ : 定时任务
     */ 
    public static final String BATCH_JOB_TYPE_QUARTZ="S";
    
    /** 
     * @Fields BATCH_JOB_TYPE_MAN : 异步时任务
     */ 
    public static final String BATCH_JOB_TYPE_MAN="A";

    
    /** 
     * @Fields JOB_SESSION_CONTEXT_EXTENSIONS : 作业上下文对象中拓展属性的key值 
     */ 
     public static final String JOB_SESSION_CONTEXT_EXTENSIONS = "batch_extendsions";

}
