package cn.com.git.udmp.component.batch.common.constants;

/**
 * @description 任务指令枚举类
 * @author liuliang liuliang_wb@newchina.live
 * @date 2015年1月29日 上午11:33:50
 */
/** 
 * @description 
 * @author liuliang liuliang_wb@newchina.live 
 * @date 2015年7月7日 下午3:31:30  
*/
public enum CommandEnum {
    /**
     *REGISTER-在server上注册agent后连接确认；
     *HEARTBEAT-心跳检测，代理反馈自己的负载信息；
     *DISPATCH-分发检测、请求负载信息及总记录数；
     *EXECUTE-首次执行发出执行请求；
     *STATUS-执行过程中的信息反馈；
     *ABORT-执行中的任务发出停止请求；
     *START-被停止的任务再次发出执行请求；
     *REEXECUTE-重跑；
     *FINISH-执行完毕；
     */
    
    /**
     * @Fields START : 开始命令
     */
    START,
    /**
     * @Fields ABORT : 停止命令
     */
    ABORT,
    /** 
    * @Fields REGISTER : 在server上注册agent后连接确认 
    */ 
    REGISTER,
    /** 
    * @Fields HEARTBEAT : 心跳检测，代理反馈自己的负载信息 
    */ 
    HEARTBEAT,
    /** 
    * @Fields DISPATCH : 分发检测、请求负载信息及总记录数 
    */ 
    DISPATCH,
    /** 
    * @Fields EXECUTE : 首次执行发出执行请求 
    */ 
    EXECUTE,
    /** 
    * @Fields STATUS : 执行过程中的信息反馈 
    */ 
    STATUS,
    EXCEPTION,
    /** 
    * @Fields REEXECUTE : 重跑 
    */ 
    REEXECUTE,
    /** 
    * @Fields FINISH : 执行完毕 
    */ 
    FINISH,
    /**
     * @Fields PAUSE : 暂停命令
     */
    PAUSE;
}
