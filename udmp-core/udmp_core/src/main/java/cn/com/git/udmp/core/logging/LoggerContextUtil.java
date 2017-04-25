package cn.com.git.udmp.core.logging;

import org.slf4j.MDC;

/** 
 * @description 日志上下文工具 MDC操作类
 * @author tanzl
 * @date 2014年10月21日 上午11:48:55  
*/
public class LoggerContextUtil {
    /**
     * @description 将流水号，交易号，系统编号，调用次序放进一个环境信息里面
     * @version
     * @title
     * @param bizSeq 流水号
     * @param transCode 交易号
     * @param systemNo 系统编号
     * @param invokeSeq 调用次序
    */
    public static void setContextInfo(String bizSeq, String transCode, String systemNo, String invokeSeq) {
        MDC.clear();
        String bizS = (bizSeq == null) ? " " : bizSeq;
        String tc = (transCode == null) ? " " : transCode;
        String sysno = (systemNo == null) ? " " : systemNo;
        String invSeq = (invokeSeq == null) ? " " : invokeSeq;
        MDC.put("bizSeq", "流水号：" + bizS);
        MDC.put("transCode", "交易号：" + tc);
        MDC.put("systemNo", "系统编号：" + sysno);
        MDC.put("invokeSeq", "调用次序：" + invSeq);
    }

    /**
     * @description 更改流水号
     * @version
     * @title
     * @param bizSeq 流水号
    */
    public static void setbizSeq(String bizSeq) {
        MDC.clear();
        MDC.put("bizSeq", bizSeq);
    }

    /**
     * @description 更改交易号
     * @version
     * @title
     * @param transCode  交易号
    */
    public static void settransCode(String transCode) {
        MDC.clear();
        MDC.put("transCode", transCode);
    }

    /**
     * @description 更改系统编号
     * @version
     * @title
     * @param systemNo  系统编号
    */
    public static void setSystemNo(String systemNo) {
        MDC.clear();
        MDC.put("systemNo", systemNo);
    }
    
    /**
     * @description 更改调用次序
     * @version
     * @title
     * @param invokeSeq 调用次序
    */
    public static void setinvokeSeq(String invokeSeq) {
        MDC.clear();
        MDC.put("invokeSeq", invokeSeq);
    }
}
