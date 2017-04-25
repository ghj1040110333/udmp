package cn.com.git.udmp.batch.web.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;

import cn.com.git.udmp.batch.web.base.FrameworkBaseAction;
import cn.com.git.udmp.common.exception.FrameworkException;
import cn.com.git.udmp.component.batch.common.constants.JobRunStatus;
import cn.com.git.udmp.component.batch.common.constants.StatusEnum;
import cn.com.git.udmp.component.batch.common.utils.StatusUtil;
import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.core.server.job.IJobController;
import cn.com.git.udmp.component.batch.core.server.manage.impl.JobDependManagerInDB;
import cn.com.git.udmp.component.batch.event.alert.AlertMsg;
import cn.com.git.udmp.component.model.Constants;
import cn.com.git.udmp.impl.batch.jobRun.ucc.IBatchJobRunUCC;
import cn.com.git.udmp.impl.batch.jobRun.vo.BatchJobRunVO;

/**
 * 
 * @description 执行监控
 * @author Spring Cao
 * @date 2016年8月30日 上午11:46:54
 */
@Service
@Scope("prototype")
@Transactional
public class BatchMonitorAction extends FrameworkBaseAction {
    private static final long serialVersionUID = 1L;
    private static final long SECOND = 60;
    private static final long MILLISECOND = 1000;

    private String jobName;

    @Autowired
    @Qualifier("dispatchJobController")
    IJobController startJobController;
    @Autowired
    @Qualifier("stopJobController")
    IJobController stopJobController;
    @Autowired
    JobDependManagerInDB jobDependManager;

    private BatchJobRunVO batchJobRunVO = new BatchJobRunVO();
    /**
     * @Fields batchJobRunTVO : 查询VO
     */
    private BatchJobRunVO batchJobRunTVO = new BatchJobRunVO();

    /**
     * @Fields batchJobRunAVO : 查询详细信息用VO
     */
    private BatchJobRunVO batchJobRunAVO = new BatchJobRunVO();
    @Autowired
    private IBatchJobRunUCC batchJobRunUCC;

    public BatchJobRunVO getBatchJobRunTVO() {
        return batchJobRunTVO;
    }

    public void setBatchJobRunTVO(BatchJobRunVO batchJobRunTVO) {
        this.batchJobRunTVO = batchJobRunTVO;
    }

    public BatchJobRunVO getBatchJobRunAVO() {
        return batchJobRunAVO;
    }

    public void setBatchJobRunAVO(BatchJobRunVO batchJobRunAVO) {
        this.batchJobRunAVO = batchJobRunAVO;
    }

    public BatchJobRunVO getBatchJobRunVO() {
        return batchJobRunVO;
    }

    public void setBatchJobRunVO(BatchJobRunVO batchJobRunVO) {
        this.batchJobRunVO = batchJobRunVO;
    }

    public IBatchJobRunUCC getBatchJobRunUCC() {
        return batchJobRunUCC;
    }

    public void setBatchJobRunUCC(IBatchJobRunUCC batchJobRunUCC) {
        this.batchJobRunUCC = batchJobRunUCC;
    }

    @Override
    public String getBizId() {
        return null;
    }

    /**
     * @description 打开主查询页面
     * @version
     * @title
     * @return 查询页面
     */
    public String showBatchMonitorIndexPage() {
        return "showBatchMonitorIndexPage";
    }

    /**
     * @description 打开详细内容页面
     * @version
     * @title
     * @return 打开详细内容页面
     */
    public String showBatchMonitorDetailPage() {
        try {
            String batchJobRunId = this.getRequest().getParameter("batchJobRunVO.jobRunId");
            if (batchJobRunId == null) {
                this.errorMsg = "";
                return "error";
            }
            batchJobRunTVO = new BatchJobRunVO();
            batchJobRunTVO.setJobRunId(new BigDecimal(batchJobRunId));
            batchJobRunAVO = batchJobRunUCC.find(batchJobRunTVO);

        } catch (FrameworkException e) {
            outAjax(Constants.DWZ_STATUSCODE_300, "open update page failed!", "", "", "");
            logger.error("open update page failed!", e);
        }
        return "showBatchMonitorDetailPage";
    }

    /**
     * @description 查询操作，根据查询条件查出数据
     * @version
     * @title
     * @return 查询页面
     */
    public String showBatchMonitor() {
        try {
            if (null != batchJobRunTVO) {
                currentPage = getCurrentPage();
                currentPage.setTotal(batchJobRunUCC.findBatchAgentTotal(batchJobRunTVO));
                currentPage.setPageTotal(currentPage.getTotal() / currentPage.getPageSize() + 1);
                currentPage = batchJobRunUCC.queryForPage(batchJobRunTVO, currentPage);
            }
            // timeoutWarning();
        } catch (FrameworkException e) {
            e.printStackTrace();
            this.errorMsg = "";
            return "error";
        }
        return "showBatchMonitorIndexPage";
    }

    /**
     * @title
     * @description 重跑
     * 
     * @return
     */
    public String reboot() {
        return jobName;
    }

    /**
     * @title
     * @description 重跑
     * 
     * @return
     */
    public void failReboot() {
        String jobRunId = this.getRequest().getParameter("batchJobRunVO.jobRunId");
        // System.out.println(jobRunId);
        Preconditions.checkNotNull(jobRunId);

        // 若当前任务实例状态为成功、则往下找失败任务；若当前任务实例状态为失败，则往上找第一个失败任务
        BatchJobRunVO vo = new BatchJobRunVO();
        vo.setJobRunId(new BigDecimal(jobRunId));
        List<BatchJobRunVO> resultList = batchJobRunUCC.findAll(vo);
        if (resultList != null && resultList.size() != 0) {
            try {
                BatchJobRunVO result = resultList.get(0);
                if (result != null) {
                    String jobId = result.getJobId().toPlainString();
                    String jobChainRunId = result.getJobChainRunId();
                    Preconditions.checkNotNull(jobChainRunId);
                    if (!JobRunStatus.SUCCESS.equals(result.getStatus())) {
                        activePre(jobId, JobDependManagerInDB.getJobStatusFromResult(resultList), jobChainRunId);
                    } else {
                        activeAfter(jobId, JobDependManagerInDB.getJobStatusFromResult(resultList), jobChainRunId);
                    }
                } else {
                    logger.error("{}任务实例不存在", jobRunId);
                }
            } catch (Exception e) {
                outAjax(Constants.DWZ_STATUSCODE_300, "重启失败任务失败!"+e.getMessage(), "", "", "");
                logger.error(" 重启失败任务失败  !,{}", e);
                return ;
            }
            outAjax(Constants.DWZ_STATUSCODE_200, Constants.DWZ_MESSAGE_200, "", "", "");
        }
    }

    /**
     * @title 激活后续失败的任务
     * @description 若当前任务{jobId}的
     * 
     * @param jobId
     * @param jobChainRunId
     */
    private void activeAfter(String jobId, StatusEnum status, String jobChainRunId) {
        int lastBatch = batchJobRunUCC.findLastBatch(jobChainRunId);
        if (status.equals(StatusEnum.SUCCESS)) {
            // 若当前任务为成功状态，查找以当前任务为成功状态作为前置的任务，然后往下激活后续任务
            List<String> jobIds = jobDependManager.findJobsAfter(jobId, status);
            logger.info("当前任务{}在任务链{}下的后续任务有:{}", jobId, jobChainRunId, jobIds);
            for (String key : jobIds) {
                BatchJobRunVO batchJobRunVO = new BatchJobRunVO();
                batchJobRunVO.setJobId(new BigDecimal(key));
                batchJobRunVO.setJobChainRunId(jobChainRunId);
                batchJobRunVO.setJobChainBatch(new BigDecimal(lastBatch));// 设置批次号
                List<BatchJobRunVO> resultList = batchJobRunUCC.findAll(batchJobRunVO);
                if (resultList != null && resultList.size() != 0) {
                    try {
                        activeAfter(key, JobDependManagerInDB.getJobStatusFromResult(resultList), jobChainRunId);
                    } catch (RuntimeException e) {
                        // logger.error("任务链{}激活后续任务{}发生异常:{}", jobChainRunId,
                        // key, e);
                        throw e;
                    }
                }
            }
        } else {
            // 若当前任务不成功，则直接启动当前任务
            JobSessionContext jobSessionContext = new JobSessionContext();
            jobSessionContext.setJobId(jobId);
            jobSessionContext.setJobChainRunId(jobChainRunId);
            logger.info("任务{}当前状态任务链实例{}状态为不成功，开始执行失败重跑", jobId, jobChainRunId);
            // add by L.liang on 2017/1/12 初始化时获取当前任务实例执行批次，将批次号传入到执行链上下文中
            jobSessionContext.setJobChainBatch(lastBatch + 1);
            logger.debug("启动任务链实例{}下的任务{}的第{}批次", jobChainRunId, jobId, jobSessionContext.getJobChainBatch());
            startJobController.control(jobSessionContext);
        }
    }

    /**
     * @description 激活之前失败的任务
     * 
     * @param jobId
     * @param jobChainRunId
     */
    private void activePre(String jobId, StatusEnum status, String jobChainRunId) {
        int jobChainBatch = batchJobRunUCC.findLastBatch(jobChainRunId);
        if (!jobDependManager.checkPreCondtion(jobId, jobChainRunId, jobChainBatch)) {
            // 1.若前置条件不满足，则向上查找前置条件有哪些
            // 2.递归执行前置条件的任务的active方法
            Map<String, StatusEnum> preCondtion = jobDependManager.findPreCondition(jobId);
            logger.info("任务链{}下的任务{}不满足启动条件,开始查找是否从之前的任务{}开始失败重跑", jobChainRunId, jobId, preCondtion.keySet());
            for (String key : preCondtion.keySet()) {
                BatchJobRunVO batchJobRunVO = new BatchJobRunVO();
                batchJobRunVO.setJobId(new BigDecimal(key));
                batchJobRunVO.setJobChainRunId(jobChainRunId);
                BatchJobRunVO result = batchJobRunUCC.find(batchJobRunVO);
                if (result != null) {
                    activePre(key, StatusUtil.getStatusEnum(result.getStatus()), jobChainRunId);
                }
            }
        } else {
            // 若前置条件满足，则直接启动当前任务
            // 1.若当前任务为失败状态则重跑
            if (!status.equals(StatusEnum.SUCCESS)) {
                JobSessionContext jobSessionContext = new JobSessionContext();
                jobSessionContext.setJobId(jobId);
                jobSessionContext.setJobChainRunId(jobChainRunId);
                logger.info("任务{}当前状态任务链实例{}状态为不成功，开始失败重跑", jobId, jobChainRunId);
                // add by L.liang on 2017/1/12 初始化时获取当前任务实例执行批次，将批次号传入到执行链上下文中
                jobSessionContext.setJobChainBatch(jobChainBatch + 1);
                logger.debug("启动任务链实例{}下的任务{}的第{}批次", jobChainRunId, jobId, jobSessionContext.getJobChainBatch());
                startJobController.control(jobSessionContext);
            }
        }
    }

    /**
     * @description 初始化甘特图
     * @version
     * @title
     * @param list
     * @return
     */
    @SuppressWarnings({ "unchecked" })
    public void showGanttView2() {
        String pageNum = this.getRequest().getParameter("pageNum");
        String numPerPage = this.getRequest().getParameter("numPerPage");
        String status = this.getRequest().getParameter("status");
        String processDateStart = this.getRequest().getParameter("processDateStart");
        String processDateEnd = this.getRequest().getParameter("processDateEnd");

        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dataStart = null;
        Date dataEnd = null;

        try {
            if (processDateStart.length() != 0) {
                dataStart = sd.parse(processDateStart);
            }
            if (processDateEnd.length() != 0) {
                dataEnd = sd.parse(processDateEnd);
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        batchJobRunTVO = new BatchJobRunVO();
        batchJobRunTVO.setJobName(jobName);
        batchJobRunTVO.setStatus(status);
        batchJobRunTVO.setProcessDateStart(dataStart);
        batchJobRunTVO.setProcessDateEnd(dataEnd);
        currentPage.setPageNo(Integer.parseInt(pageNum));
        currentPage.setPageSize(Integer.parseInt(numPerPage));

        currentPage = batchJobRunUCC.queryForPage(batchJobRunTVO, currentPage);
        List<BatchJobRunVO> batchJobRunAVOList = currentPage.getPageItems();
        Map<String, Object> map;
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        // 转换开始时间格式
        for (BatchJobRunVO batchJobRunVo : batchJobRunAVOList) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String strStart = sdf.format(batchJobRunVo.getDueDate());

            map = new HashMap<String, Object>();
            map.put("id", batchJobRunVo.getJobRunId());
            map.put("text", batchJobRunVo.getJobName());
            map.put("start_date", strStart);
            map.put("duration", batchJobRunVo.getJobExpectDuration().intValue());

            if (batchJobRunVo.getStatus().equals("0")) {
                map.put("state", "成功");
                map.put("progress", "1");
            } else if (batchJobRunVo.getStatus().equals("1")) {
                map.put("state", "失败");
                map.put("progress", "0");
            } else if (batchJobRunVo.getStatus().equals("2")) {
                map.put("state", "部分成功");
                map.put("progress", "0.99");
            } else if (batchJobRunVo.getStatus().equals("3")) {
                map.put("state", "未启动");
                map.put("progress", "0");
            } else if (batchJobRunVo.getStatus().equals("4")) {
                Date currentDate = new Date();
                // 计算预期结束时间
                GregorianCalendar cal = new GregorianCalendar();
                cal.setTime(batchJobRunVo.getDueDate());
                cal.add(Calendar.MINUTE, batchJobRunVo.getJobExpectDuration().intValue());
                if ((currentDate.getTime() < cal.getTimeInMillis())) {
                    // 计算时差，转换成分钟
                    long diff = (currentDate.getTime() - batchJobRunVo.getDueDate().getTime()) / MILLISECOND / SECOND;
                    double procent = (double) diff / (batchJobRunVo.getJobExpectDuration().intValue());
                    map.put("state", "运行中");
                    map.put("progress", String.format("%.2f", procent)); // 保留两位小数
                } else {
                    map.put("state", "运行超时");
                    map.put("progress", "0.99");
                }
            } else if (batchJobRunVo.getStatus().equals("5")) {
                map.put("state", "手动停止");
                map.put("progress", "0");
            }
            map.put("open", "true");
            dataList.add(map);

        }

        // TODO links未使用，先不配置
        // List<Map<String, Object>> linkList = new ArrayList<Map<String,
        // Object>>();
        // map = new HashMap<String, Object>();
        // map.put("id", 1);
        // map.put("source", batchJobRunAVO.getJobId());
        // map.put("target", 999);
        // map.put("type", 1);
        // linkList.add(map);

        // 将数据类型为Map的List放入一个map中
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("data", dataList);
        Map<String, Object> tasks = new HashMap<String, Object>();
        tasks.put("tasks", data);
        // obj.put("links", linkList);
        outJson(tasks);
    }

    public void showGanttView() {
        String jobRunId = this.getRequest().getParameter("jobRunId");
        BatchJobRunVO batchJobRunVo = new BatchJobRunVO();
        batchJobRunVo.setJobRunId(new BigDecimal(jobRunId));
        batchJobRunVo = batchJobRunUCC.find(batchJobRunVo);
        // 生成json
        Map<String, Object> map;
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        // 转换开始时间格式

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String strStart = sdf.format(batchJobRunVo.getDueDate());

        map = new HashMap<String, Object>();
        map.put("id", batchJobRunVo.getJobRunId());
        map.put("text", batchJobRunVo.getJobName());
        map.put("start_date", strStart);
        map.put("duration", batchJobRunVo.getJobExpectDuration().intValue());

        if (batchJobRunVo.getStatus().equals("0")) {
            map.put("state", "成功");
            map.put("progress", "1");
        } else if (batchJobRunVo.getStatus().equals("1")) {
            map.put("state", "失败");
            map.put("progress", "0");
        } else if (batchJobRunVo.getStatus().equals("2")) {
            map.put("state", "部分成功");
            map.put("progress", "0.99");
        } else if (batchJobRunVo.getStatus().equals("3")) {
            map.put("state", "未启动");
            map.put("progress", "0");
        } else if (batchJobRunVo.getStatus().equals("4")) {
            Date currentDate = new Date();
            try {
                currentDate = sdf.parse("11-05-2015 15:40:32");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            // 计算预期结束时间
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(batchJobRunVo.getDueDate());
            cal.add(Calendar.MINUTE, batchJobRunVo.getJobExpectDuration().intValue());
            if ((currentDate.getTime() < cal.getTimeInMillis())) {
                // 计算时差，转换成分钟
                long diff = (currentDate.getTime() - batchJobRunVo.getDueDate().getTime()) / MILLISECOND / SECOND;
                double procent = (double) diff / (batchJobRunVo.getJobExpectDuration().intValue());
                map.put("state", "运行中");
                map.put("progress", String.format("%.2f", procent)); // 保留两位小数
            } else {
                map.put("state", "运行超时");
                map.put("progress", "0.99");
            }
        }
        map.put("open", "true");
        dataList.add(map);

        // TODO links未使用，先不配置
        // List<Map<String, Object>> linkList = new ArrayList<Map<String,
        // Object>>();
        // map = new HashMap<String, Object>();
        // map.put("id", 1);
        // map.put("source", batchJobRunAVO.getJobId());
        // map.put("target", 999);
        // map.put("type", 1);
        // linkList.add(map);

        // 将数据类型为Map的List放入一个map中
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("data", dataList);
        // obj.put("links", linkList);

        outJson(obj);
    }

    public String showBatchMonitor1() {
        try {
            if (null != batchJobRunTVO) {
                currentPage = batchJobRunUCC.queryForPage(batchJobRunTVO, getCurrentPage());
            }
            // timeoutWarning();
        } catch (FrameworkException e) {
            e.printStackTrace();
            this.errorMsg = "";
            return "error";
        }
        return "showBatchMonitorIndexPage1";
    }

    public String showBatchMonitorIndexPage1() {
        return "showBatchMonitorIndexPage1";
    }

    /**
     * @description 超时预警
     * @version
     * @title
     * @return
     */
    public void timeoutWarning() {
        batchJobRunVO = new BatchJobRunVO();
        batchJobRunVO.setStatus("4");
        List<BatchJobRunVO> warnList = new ArrayList<BatchJobRunVO>();
        warnList = batchJobRunUCC.findAll(batchJobRunVO);

        Date currentDate;
        Date processDate;
        BigDecimal jobAlertDuration;
        long diff;
        AlertMsg msg = new AlertMsg();
        // 计算时间差
        for (BatchJobRunVO jobRunVo : warnList) {
            // 使用udmp中的获取日期函数
            // currentDate = new Date();
            currentDate = new Date();
            processDate = jobRunVo.getProcessDate();
            // 计算时差
            diff = (currentDate.getTime() - processDate.getTime()) / MILLISECOND;
            // 转换成分钟
            jobAlertDuration = new BigDecimal(diff / SECOND);
            if (jobAlertDuration.compareTo(jobRunVo.getJobAlertDuration()) != -1) { // 时差大于等于报警时间情况
                // AlertFactory.setType(AlertType.MAIL);
                // IAlertProxy alertProxy = AlertFactory.getAlertProxy();
                // alertProxy.alert(msg);
            }
        }
    }

    /**
     * @description 手动停止任务
     * @version
     * @title
     * @return
     */
    public void endBatchJob() {
        String jobRunId = this.getRequest().getParameter("jobRunId");
        logger.info("手动停止任务{}", jobRunId);
        sendJob(stopJobController, jobRunId);
    }

    public void reRunBatchJob() {
        String jobRunId = this.getRequest().getParameter("jobRunId");
        logger.info("手动重跑任务{}", jobRunId);
        sendJob(startJobController, jobRunId);
    }

    private void sendJob(IJobController jobController, String jobRunId) {
        BatchJobRunVO batchJobRun = new BatchJobRunVO();
        batchJobRun.setJobRunId(new BigDecimal(jobRunId));
        BatchJobRunVO find = batchJobRunUCC.find(batchJobRun);
        JobSessionContext jsContext = new JobSessionContext();
        jsContext.setJobRunId(jobRunId);
        jsContext.setJobId(find.getJobId().toString());
        jobController.control(jsContext);
    }

    public void setStopJobController(IJobController stopJobController) {
        this.stopJobController = stopJobController;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public void setStartJobController(IJobController startJobController) {
        this.startJobController = startJobController;
    }

    public void setJobDependManager(JobDependManagerInDB jobDependManager) {
        this.jobDependManager = jobDependManager;
    }
}
