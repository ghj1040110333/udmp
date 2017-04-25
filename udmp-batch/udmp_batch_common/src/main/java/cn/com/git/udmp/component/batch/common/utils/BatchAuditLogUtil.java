package cn.com.git.udmp.component.batch.common.utils;
//package cn.com.git.udmp.component.batch.common.utils;
//
//import java.math.BigDecimal;
//import java.net.Inet4Address;
//import java.net.UnknownHostException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.slf4j.Logger;
//
//import cn.com.git.udmp.component.batch.common.constants.BatchCommonConst;
//import cn.com.git.udmp.modules.batch.auditLog.bo.BatchAuditLogBO;
//import cn.com.git.udmp.modules.batch.auditLog.service.IBatchAuditLogService;
//
//import cn.com.git.udmp.common.logging.LoggerFactory;
//import cn.com.git.udmp.common.utils.SpringContextHolder;
//
///** 
// * @description 批处理审计日志工具类
// * @author liuliang liuliang_wb@newchina.live 
// * @date 2015年7月7日 下午3:32:49  
//*/
//public class BatchAuditLogUtil {
//
//    private static Logger logger = LoggerFactory.getLogger();
//
//    private static IBatchAuditLogService getBatchAuditLogService() {
//        return (IBatchAuditLogService) SpringContextHolder.getBean(BatchCommonConst.BATCH_AUDIT_LOG_SERVICE_NAME);
//    }
//
//    /**
//     * -------------------------------任务相关服务方法----------------------------------
//     */
//
//    /**
//     * 增加任务新增审计信息
//     * 
//     * @version
//     * @title
//     * @author majn@newchinalife.com
//     * @param jobId 任务ID
//     * @return 无返回值
//     */
//    public static void auditJobAdd(BigDecimal jobId) {
//        BatchAuditLogBO batchAuditLogBO = new BatchAuditLogBO();
//        batchAuditLogBO = fillAuditInfoByOperation("job", "add", jobId);
//        batchAuditLogBO = fillAuditInfoByUser(batchAuditLogBO);
//        getBatchAuditLogService().addBatchAuditLog(batchAuditLogBO);
//    }
//
//    /**
//     * 批量增加任务新增审计信息
//     * 
//     * @version
//     * @title
//     * @author majn@newchinalife.com
//     * @param jobId 任务ID数组
//     * @return 无返回值
//     */
//    public static void auditBatchJobAdd(BigDecimal[] jobId) {
//        List<BatchAuditLogBO> batchAuditLogBOList = fillBatchAuditLogBOList("job", "add", jobId);
//        getBatchAuditLogService().batchSaveBatchAuditLog(batchAuditLogBOList);
//    }
//
//    /**
//     * 增加任务删除审计信息
//     * 
//     * @version
//     * @title
//     * @author majn@newchinalife.com
//     * @param jobId 任务ID
//     * @return 无返回值
//     */
//    public static void auditJobDel(BigDecimal jobId) {
//        BatchAuditLogBO batchAuditLogBO = new BatchAuditLogBO();
//        batchAuditLogBO = fillAuditInfoByOperation("job", "del", jobId);
//        batchAuditLogBO = fillAuditInfoByUser(batchAuditLogBO);
//        getBatchAuditLogService().addBatchAuditLog(batchAuditLogBO);
//    }
//
//    /**
//     * 批量增加任务删除审计信息
//     * 
//     * @version
//     * @title
//     * @author majn@newchinalife.com
//     * @param jobId 任务ID数组
//     * @return 无返回值
//     */
//    public static void auditBatchJobDel(BigDecimal[] jobId) {
//        List<BatchAuditLogBO> batchAuditLogBOList = fillBatchAuditLogBOList("job", "del", jobId);
//        getBatchAuditLogService().batchSaveBatchAuditLog(batchAuditLogBOList);
//    }
//
//    /**
//     * 增加任务修改审计信息
//     * 
//     * @version
//     * @title
//     * @author majn@newchinalife.com
//     * @param jobId 任务ID
//     * @return 无返回值
//     */
//    public static void auditJobUpdate(BigDecimal jobId) {
//        BatchAuditLogBO batchAuditLogBO = new BatchAuditLogBO();
//        batchAuditLogBO = fillAuditInfoByOperation("job", "update", jobId);
//        batchAuditLogBO = fillAuditInfoByUser(batchAuditLogBO);
//        getBatchAuditLogService().addBatchAuditLog(batchAuditLogBO);
//    }
//
//    /**
//     * 批量增加任务修改审计信息
//     * 
//     * @version
//     * @title
//     * @author majn@newchinalife.com
//     * @param jobId 任务ID数组
//     * @return 无返回值
//     */
//    public static void auditBatchJobUpdate(BigDecimal[] jobId) {
//        List<BatchAuditLogBO> batchAuditLogBOList = fillBatchAuditLogBOList("job", "update", jobId);
//        getBatchAuditLogService().batchSaveBatchAuditLog(batchAuditLogBOList);
//    }
//
//    /**
//     * -------------------------------作业相关服务方法----------------------------------
//     */
//
//    /**
//     * 增加作业新增审计信息
//     * 
//     * @version
//     * @title
//     * @author majn@newchinalife.com
//     * @param taskId 作业ID
//     * @return 无返回值
//     */
//    public static void auditTaskAdd(BigDecimal taskId) {
//        BatchAuditLogBO batchAuditLogBO = new BatchAuditLogBO();
//        batchAuditLogBO = fillAuditInfoByOperation("task", "add", taskId);
//        batchAuditLogBO = fillAuditInfoByUser(batchAuditLogBO);
//        getBatchAuditLogService().addBatchAuditLog(batchAuditLogBO);
//    }
//
//    /**
//     * 批量增加作业新增审计信息
//     * 
//     * @version
//     * @title
//     * @author majn@newchinalife.com
//     * @param taskId 作业ID数组
//     * @return 无返回值
//     */
//    public static void auditBatchTaskAdd(BigDecimal[] taskId) {
//        List<BatchAuditLogBO> batchAuditLogBOList = fillBatchAuditLogBOList("task", "add", taskId);
//        getBatchAuditLogService().batchSaveBatchAuditLog(batchAuditLogBOList);
//    }
//
//    /**
//     * 增加作业删除审计信息
//     * 
//     * @version
//     * @title
//     * @author majn@newchinalife.com
//     * @param taskId 作业ID
//     * @return 无返回值
//     */
//    public static void auditTaskDel(BigDecimal taskId) {
//        BatchAuditLogBO batchAuditLogBO = new BatchAuditLogBO();
//        batchAuditLogBO = fillAuditInfoByOperation("task", "del", taskId);
//        batchAuditLogBO = fillAuditInfoByUser(batchAuditLogBO);
//        getBatchAuditLogService().addBatchAuditLog(batchAuditLogBO);
//    }
//
//    /**
//     * 批量增加作业删除审计信息
//     * 
//     * @version
//     * @title
//     * @author majn@newchinalife.com
//     * @param taskId 作业ID数组
//     * @return 无返回值
//     */
//    public static void auditBatchTaskDel(BigDecimal[] taskId) {
//        List<BatchAuditLogBO> batchAuditLogBOList = fillBatchAuditLogBOList("task", "del", taskId);
//        getBatchAuditLogService().batchSaveBatchAuditLog(batchAuditLogBOList);
//    }
//
//    /**
//     * 增加作业修改审计信息
//     * 
//     * @version
//     * @title
//     * @author majn@newchinalife.com
//     * @param taskId 作业ID
//     * @return 无返回值
//     */
//    public static void auditTaskUpdate(BigDecimal taskId) {
//        BatchAuditLogBO batchAuditLogBO = new BatchAuditLogBO();
//        batchAuditLogBO = fillAuditInfoByOperation("task", "update", taskId);
//        batchAuditLogBO = fillAuditInfoByUser(batchAuditLogBO);
//        getBatchAuditLogService().addBatchAuditLog(batchAuditLogBO);
//    }
//
//    /**
//     * 批量增加作业修改审计信息
//     * 
//     * @version
//     * @title
//     * @author majn@newchinalife.com
//     * @param taskId 作业ID数组
//     * @return 无返回值
//     */
//    public static void auditBatchTaskUpdate(BigDecimal[] taskId) {
//        List<BatchAuditLogBO> batchAuditLogBOList = fillBatchAuditLogBOList("task", "update", taskId);
//        getBatchAuditLogService().batchSaveBatchAuditLog(batchAuditLogBOList);
//    }
//
//    /**
//     * -------------------------------代理相关服务方法----------------------------------
//     */
//
//    /**
//     * 增加代理新增审计信息
//     * 
//     * @version
//     * @title
//     * @author majn@newchinalife.com
//     * @param agentId 代理ID
//     * @return 无返回值
//     */
//    public static void auditAgentAdd(BigDecimal agentId) {
//        BatchAuditLogBO batchAuditLogBO = new BatchAuditLogBO();
//        batchAuditLogBO = fillAuditInfoByOperation("agent", "add", agentId);
//        batchAuditLogBO = fillAuditInfoByUser(batchAuditLogBO);
//        getBatchAuditLogService().addBatchAuditLog(batchAuditLogBO);
//    }
//
//    /**
//     * 批量增加代理新增审计信息
//     * 
//     * @version
//     * @title
//     * @author majn@newchinalife.com
//     * @param agentId 代理ID数组
//     * @return 无返回值
//     */
//    public static void auditBatchAgentAdd(BigDecimal[] agentId) {
//        List<BatchAuditLogBO> batchAuditLogBOList = fillBatchAuditLogBOList("agent", "add", agentId);
//        getBatchAuditLogService().batchSaveBatchAuditLog(batchAuditLogBOList);
//    }
//
//    /**
//     * 增加代理删除审计信息
//     * 
//     * @version
//     * @title
//     * @author majn@newchinalife.com
//     * @param agentId 代理ID
//     * @return 无返回值
//     */
//    public static void auditAgentDel(BigDecimal agentId) {
//        BatchAuditLogBO batchAuditLogBO = new BatchAuditLogBO();
//        batchAuditLogBO = fillAuditInfoByOperation("agent", "del", agentId);
//        batchAuditLogBO = fillAuditInfoByUser(batchAuditLogBO);
//        getBatchAuditLogService().addBatchAuditLog(batchAuditLogBO);
//    }
//
//    /**
//     * 批量增加代理删除审计信息
//     * 
//     * @version
//     * @title
//     * @author majn@newchinalife.com
//     * @param agentId 代理ID数组
//     * @return 无返回值
//     */
//    public static void auditBatchAgentDel(BigDecimal[] agentId) {
//        List<BatchAuditLogBO> batchAuditLogBOList = fillBatchAuditLogBOList("agent", "del", agentId);
//        getBatchAuditLogService().batchSaveBatchAuditLog(batchAuditLogBOList);
//    }
//
//    /**
//     * 增加代理修改审计信息
//     * 
//     * @version
//     * @title
//     * @author majn@newchinalife.com
//     * @param agentId 代理ID
//     * @return 无返回值
//     */
//    public static void auditAgentUpdate(BigDecimal agentId) {
//        BatchAuditLogBO batchAuditLogBO = new BatchAuditLogBO();
//        batchAuditLogBO = fillAuditInfoByOperation("agent", "update", agentId);
//        batchAuditLogBO = fillAuditInfoByUser(batchAuditLogBO);
//        getBatchAuditLogService().addBatchAuditLog(batchAuditLogBO);
//    }
//
//    /**
//     * 批量增加代理修改审计信息
//     * 
//     * @version
//     * @title
//     * @author majn@newchinalife.com
//     * @param agentId 代理ID数组
//     * @return 无返回值
//     */
//    public static void auditBatchAgentUpdate(BigDecimal[] agentId) {
//        List<BatchAuditLogBO> batchAuditLogBOList = fillBatchAuditLogBOList("agent", "update", agentId);
//        getBatchAuditLogService().batchSaveBatchAuditLog(batchAuditLogBOList);
//    }
//
//    /**
//     * -------------------------------系统参数相关服务方法--------------------------------
//     * --
//     */
//
//    /**
//     * 增加系统参数新增审计信息
//     * 
//     * @version
//     * @title
//     * @author majn@newchinalife.com
//     * @param paramId 系统参数ID
//     * @return 无返回值
//     */
//    public static void auditParamAdd(BigDecimal paramId) {
//        BatchAuditLogBO batchAuditLogBO = new BatchAuditLogBO();
//        batchAuditLogBO = fillAuditInfoByOperation("param", "add", paramId);
//        batchAuditLogBO = fillAuditInfoByUser(batchAuditLogBO);
//        getBatchAuditLogService().addBatchAuditLog(batchAuditLogBO);
//    }
//
//    /**
//     * 批量增加系统参数新增审计信息
//     * 
//     * @version
//     * @title
//     * @author majn@newchinalife.com
//     * @param paramId 系统参数ID数组
//     * @return 无返回值
//     */
//    public static void auditBatchParamAdd(BigDecimal[] paramId) {
//        List<BatchAuditLogBO> batchAuditLogBOList = fillBatchAuditLogBOList("param", "add", paramId);
//        getBatchAuditLogService().batchSaveBatchAuditLog(batchAuditLogBOList);
//    }
//
//    /**
//     * 增加系统参数删除审计信息
//     * 
//     * @version
//     * @title
//     * @author majn@newchinalife.com
//     * @param paramId 系统参数ID
//     * @return 无返回值
//     */
//    public static void auditParamDel(BigDecimal paramId) {
//        BatchAuditLogBO batchAuditLogBO = new BatchAuditLogBO();
//        batchAuditLogBO = fillAuditInfoByOperation("param", "del", paramId);
//        batchAuditLogBO = fillAuditInfoByUser(batchAuditLogBO);
//        getBatchAuditLogService().addBatchAuditLog(batchAuditLogBO);
//    }
//
//    /**
//     * 批量增加系统参数删除审计信息
//     * 
//     * @version
//     * @title
//     * @author majn@newchinalife.com
//     * @param paramId 系统参数ID数组
//     * @return 无返回值
//     */
//    public static void auditBatchParamDel(BigDecimal[] paramId) {
//        List<BatchAuditLogBO> batchAuditLogBOList = fillBatchAuditLogBOList("param", "del", paramId);
//        getBatchAuditLogService().batchSaveBatchAuditLog(batchAuditLogBOList);
//    }
//
//    /**
//     * 增加系统参数修改审计信息
//     * 
//     * @version
//     * @title
//     * @author majn@newchinalife.com
//     * @param paramId 系统参数ID
//     * @return 无返回值
//     */
//    public static void auditParamUpdate(BigDecimal paramId) {
//        BatchAuditLogBO batchAuditLogBO = new BatchAuditLogBO();
//        batchAuditLogBO = fillAuditInfoByOperation("param", "update", paramId);
//        batchAuditLogBO = fillAuditInfoByUser(batchAuditLogBO);
//        getBatchAuditLogService().addBatchAuditLog(batchAuditLogBO);
//    }
//
//    /**
//     * 批量增加系统参数修改审计信息
//     * 
//     * @version
//     * @title
//     * @author majn@newchinalife.com
//     * @param paramId 系统参数ID数组
//     * @return 无返回值
//     */
//    public static void auditBatchParamUpdate(BigDecimal[] paramId) {
//        List<BatchAuditLogBO> batchAuditLogBOList = fillBatchAuditLogBOList("param", "update", paramId);
//        getBatchAuditLogService().batchSaveBatchAuditLog(batchAuditLogBOList);
//    }
//
//    /**
//     * /** 新建批处理审计对象List，并填充操作信息和用户信息
//     * 
//     * @version
//     * @title
//     * @author majn@newchinalife.com
//     * @param auditType 审计对象类型
//     * @param operationType 操作类型
//     * @param objectId 对象主键id数组
//     * @return batchAuditLogBOList 审计对象List
//     */
//    private static List<BatchAuditLogBO> fillBatchAuditLogBOList(String auditType, String operationType,
//            BigDecimal[] objectId) {
//        List<BatchAuditLogBO> batchAuditLogBOList = new ArrayList<BatchAuditLogBO>();
//        BatchAuditLogBO batchAuditLogBO = new BatchAuditLogBO();
//        for (int i = 0; i < objectId.length; i++) {
//            batchAuditLogBO = fillAuditInfoByOperation(auditType, operationType, objectId[i]);
//            batchAuditLogBO = fillAuditInfoByUser(batchAuditLogBO);
//            batchAuditLogBOList.add(batchAuditLogBO);
//        }
//        return batchAuditLogBOList;
//    }
//
//    /**
//     * -------------------------------公共方法----------------------------------
//     */
//
//    /**
//     * 新建批处理审计对象，并填充操作信息
//     * 
//     * @version
//     * @title
//     * @author majn@newchinalife.com
//     * @param auditType 审计对象类型
//     * @param operationType 操作类型
//     * @param objectId 对象主键id
//     * @return 无返回值
//     */
//    private static BatchAuditLogBO fillAuditInfoByOperation(String auditType, String operationType, BigDecimal objectId) {
//        BatchAuditLogBO result = new BatchAuditLogBO();
//        result.setAuditType(auditType);
//        result.setObjectId(objectId);
//        result.setOperationType(operationType);
//        result.setAuditContext(auditType + "Id: " + objectId + " " + operationType);
//        return result;
//
//    }
//
//    /**
//     * 填充用户id、用户ip信息
//     * 
//     * @version
//     * @title
//     * @author majn@newchinalife.com
//     * @param result 审计BO对象
//     * @return 无返回值
//     */
//    private static BatchAuditLogBO fillAuditInfoByUser(BatchAuditLogBO result) {
////        AppUser currentUser = AppUserContext.getCurrentUser();
//        // TODO 配置userId,userIp
//        result.setUserId(new BigDecimal(10000));
//        try {
//            result.setUserIp(Inet4Address.getLocalHost().getHostAddress());
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//            logger.error("获取本地IP失败");
//            result.setUserIp("127.0.0.1");
//        }
//        return result;
//    }
//
//}
