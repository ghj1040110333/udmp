package cn.com.git.udmp.batch.web.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;

import cn.com.git.udmp.batch.web.base.FrameworkBaseAction;
import cn.com.git.udmp.common.exception.FrameworkException;
import cn.com.git.udmp.component.batch.common.constants.JobRunStatus;
import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.core.server.job.IJobController;
import cn.com.git.udmp.component.model.Constants;
import cn.com.git.udmp.impl.batch.job.ucc.IBatchJobUCC;
import cn.com.git.udmp.impl.batch.job.vo.BatchJobVO;
import cn.com.git.udmp.impl.batch.jobDepend.ucc.IBatchJobDependUCC;
import cn.com.git.udmp.impl.batch.jobDepend.vo.BatchJobDependVO;
import cn.com.git.udmp.impl.batch.jobParam.ucc.IBatchJobParamUCC;
import cn.com.git.udmp.impl.batch.jobParam.vo.BatchJobParamVO;
import cn.com.git.udmp.impl.batch.jobRun.ucc.IBatchJobRunUCC;
import cn.com.git.udmp.impl.batch.jobRun.vo.BatchJobRunVO;
import cn.com.git.udmp.impl.batch.param.ucc.IParamManageUCC;
import cn.com.git.udmp.impl.batch.param.ucc.ISubParamUCC;
import cn.com.git.udmp.impl.batch.param.vo.ParamManageVO;
import cn.com.git.udmp.impl.batch.task.ucc.IBatchTaskUCC;
import cn.com.git.udmp.impl.batch.task.vo.BatchTaskParamVO;
import cn.com.git.udmp.impl.batch.task.vo.BatchTaskVO;

/**
 * 
 * @description 任务管理
 * @author Spring Cao
 * @date 2016年8月30日 上午11:43:22
 */
@Service
@Scope("prototype")
public class BatchJobAction extends FrameworkBaseAction {
    private static final long serialVersionUID = 1L;
    @Autowired
    @Qualifier("dispatchJobController")
    IJobController startJobController;

    private static final String PRO_DEPEND_TYPE = "B"; // 前置依赖
    private static final String POST_DEPEND_TYPE = "A"; // 前置依赖
    /**
     * @Fields IS_GROUP : "0"表示是不是任务组
     */
    private static final String IS_NOT_GROUP = "0";
    /**
     * @Fields IS_GROUP : "1"表示是任务组
     */
    private static final String IS_GROUP = "1";

    private String[] preDependJobId; // 存前置任务id
    private String[] preDependState; // 存前置任务状态
    private String[] preDependId; // 存前置任务记录id
    private String[] postDependJobId; // 存后置任务id
    private String[] postDependState; // 存后置任务状态
    private String[] postDependId; // 存后置任务记录id

    /**
     * 存放任务組
     */
    private List<BatchJobVO> jobGroupList;

    /**
     * 存放作业組
     */
    private List<BatchTaskVO> taskList;

    /**
     * 存放任务组
     */
    private List<BatchJobVO> jobList;

    // 任务ucc
    @Autowired
    private IBatchJobUCC batchJobUCC;
    // 作业ucc
    @Autowired
    private IBatchTaskUCC batchTaskUCC;
    // 子参数ucc
    @Autowired
    private ISubParamUCC subParamUCC;

    @Autowired
    private IParamManageUCC paramManageUCC;

    @Autowired
    private IBatchJobRunUCC batchJobRunUCC;

    @Autowired
    private IBatchJobDependUCC batchJobDependUCC;

    @Autowired
    private IBatchJobParamUCC batchJobParamUCC;
    /** 执行作业id */
    private String taskId;

    private BatchJobVO batchJobVO;

    /** 查询使用VO */
    private BatchJobVO batchJobTVO = new BatchJobVO();

    /** 增加修改删除使用VO */
    private BatchJobVO batchJobAVO = new BatchJobVO();

    private List<BatchJobVO> batchJobList;

    /**
     * @Fields taskParamName : 作业参数名称
     */
    private String[] taskParamName = new String[] {};

    /**
     * @Fields paramValue : 参数值
     */
    private String[] paramValue;

    /**
     * @Fields taskParamId : 作业参数ID
     */
    private String[] taskParamId;

    /**
     * @Fields isArray : 是否数组
     */
    private String[] isArray;

    /**
     * @Fields jobParamId : 任务参数ID
     */
    private String[] jobParamId;

    public String[] getJobParamId() {
        return jobParamId;
    }

    public void setJobParamId(String[] jobParamId) {
        this.jobParamId = jobParamId;
    }

    public String[] getIsArray() {
        return isArray;
    }

    public void setIsArray(String[] isArray) {
        this.isArray = isArray;
    }

    public String[] getTaskParamId() {
        return taskParamId;
    }

    public void setTaskParamId(String[] taskParamId) {
        this.taskParamId = taskParamId;
    }

    public String[] getParamValue() {
        return paramValue;
    }

    public void setParamValue(String[] paramValue) {
        this.paramValue = paramValue;
    }

    public String[] getTaskParamName() {
        return taskParamName;
    }

    public void setTaskParamName(String[] taskParamName) {
        this.taskParamName = taskParamName;
    }

    public String[] getPreDependJobId() {
        return preDependJobId;
    }

    public void setPreDependJobId(String[] preDependJobId) {
        this.preDependJobId = preDependJobId;
    }

    public String[] getPreDependState() {
        return preDependState;
    }

    public void setPreDependState(String[] preDependState) {
        this.preDependState = preDependState;
    }

    public String[] getPostDependJobId() {
        return postDependJobId;
    }

    public void setPostDependJobId(String[] postDependJobId) {
        this.postDependJobId = postDependJobId;
    }

    public String[] getPostDependState() {
        return postDependState;
    }

    public void setPostDependState(String[] postDependState) {
        this.postDependState = postDependState;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public BatchJobVO getBatchJobTVO() {
        return batchJobTVO;
    }

    public void setBatchJobTVO(BatchJobVO batchJobTVO) {
        this.batchJobTVO = batchJobTVO;
    }

    public BatchJobVO getBatchJobAVO() {
        return batchJobAVO;
    }

    public void setBatchJobAVO(BatchJobVO batchJobAVO) {
        this.batchJobAVO = batchJobAVO;
    }

    public BatchJobVO getBatchJobVO() {
        return batchJobVO;
    }

    public void setBatchJobVO(BatchJobVO batchJobVO) {
        this.batchJobVO = batchJobVO;
    }

    public IBatchJobUCC getBatchJobUCC() {
        return batchJobUCC;
    }

    public void setBatchJobUCC(IBatchJobUCC batchJobUCC) {
        this.batchJobUCC = batchJobUCC;
    }

    public IBatchTaskUCC getBatchTaskUCC() {
        return batchTaskUCC;
    }

    public void setBatchTaskUCC(IBatchTaskUCC batchTaskUCC) {
        this.batchTaskUCC = batchTaskUCC;
    }

    public ISubParamUCC getSubParamUCC() {
        return subParamUCC;
    }

    public void setSubParamUCC(ISubParamUCC subParamUCC) {
        this.subParamUCC = subParamUCC;
    }

    public IBatchJobRunUCC getBatchJobRunUCC() {
        return batchJobRunUCC;
    }

    public void setBatchJobRunUCC(IBatchJobRunUCC batchJobRunUCC) {
        this.batchJobRunUCC = batchJobRunUCC;
    }

    public List<BatchJobVO> getBatchJobList() {
        return batchJobList;
    }

    public void setBatchJobList(List<BatchJobVO> batchJobList) {
        this.batchJobList = batchJobList;
    }

    public List<BatchJobVO> getJobGroupList() {
        return jobGroupList;
    }

    public void setJobGroupList(List<BatchJobVO> jobGroupList) {
        this.jobGroupList = jobGroupList;
    }

    public List<BatchTaskVO> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<BatchTaskVO> taskList) {
        this.taskList = taskList;
    }

    public List<BatchJobVO> getJobList() {
        return jobList;
    }

    public void setJobList(List<BatchJobVO> jobList) {
        this.jobList = jobList;
    }

    @Override
    public String getBizId() {
        return null;
    }

    /**
     * @description 打开主查询页面
     * @version
     * @title
     * @return 登录页面
     */
    public String showBatchJobIndexPage() {
        return "showBatchJobIndexPage";
    }

    /**
     * @description 打开新增页面
     * @version
     * @title
     * @return String 新增页面
     */

    public String showInsertBatchJobPage() {
        batchJobAVO = new BatchJobVO();
        batchJobVO = new BatchJobVO();
        // 查询全部任务，不包含任务组
        batchJobVO.setIsGroup(IS_NOT_GROUP);
        jobList = batchJobUCC.findAll(batchJobVO);
        // 查询全部任务组
        batchJobVO.setIsGroup(IS_GROUP);

        jobGroupList = batchJobUCC.findAll(batchJobVO);

        BatchTaskVO batchTaskVO = new BatchTaskVO();
        batchTaskVO.setIsDeleted("0");
        taskList = batchTaskUCC.findAll(batchTaskVO);

        return "showInsertBatchJobPage";
    }

    /**
     * @description 打开修改页面，重新查询数据显示在页面上
     * @version
     * @title
     * @return String 修改页面
     */
    public String showUpdateBatchJobPage() {
        try {
            String batchJobId = this.getRequest().getParameter("batchJobVO.batchJobId");
            if (batchJobId == null) {
                this.errorMsg = "";
                return "error";
            }
            batchJobVO = new BatchJobVO();
            batchJobVO.setJobId(new BigDecimal(batchJobId));
            batchJobAVO = batchJobUCC.find(batchJobVO);

            BatchJobDependVO batchJobDependVO = new BatchJobDependVO();
            batchJobDependVO.setActionJobId(new BigDecimal(batchJobId));
            batchJobDependVO.setDependType("B");
            batchJobDependVO.setIsDeleted("0");
            batchJobAVO.setBatchJobPreDependList(batchJobDependUCC.findAllBatchJobDependPreCond(batchJobDependVO));

            batchJobDependVO = new BatchJobDependVO();
            batchJobDependVO.setActionJobId(new BigDecimal(batchJobId));
            batchJobDependVO.setDependType("A");
            batchJobDependVO.setIsDeleted("0");
            batchJobAVO.setBatchJobPostDependList(batchJobDependUCC.findAllBatchJobDepend(batchJobDependVO));

            BatchJobParamVO batchJobParamVO = new BatchJobParamVO();
            batchJobParamVO.setJobId(new BigDecimal(batchJobId));
            batchJobParamVO.setIsDeleted("0");
            List<BatchJobParamVO> batchJobParamVOList = batchJobParamUCC.findAll(batchJobParamVO);
            int i;
            if ((batchJobParamVOList == null) || (batchJobParamVOList.isEmpty()))
                i = -1;
            else
                i = 0;

            BatchTaskVO batchTaskVO = new BatchTaskVO();
            batchTaskVO.setTaskId(batchJobAVO.getTaskId());
            batchTaskVO = batchTaskUCC.find(batchTaskVO);

            if ((i == 0) && (batchJobParamVOList.size() != batchTaskVO.getBatchTaskParamVOList().size()))
                i = -1;

            List<BatchJobParamVO> batchJobParamList = new ArrayList<BatchJobParamVO>();
            BatchJobParamVO batchJobParamVo;
            // SubParamVO subParamVo;
            ParamManageVO paramManageVo;
            for (BatchTaskParamVO batchTaskParamVo : batchTaskVO.getBatchTaskParamVOList()) {
                batchJobParamVo = new BatchJobParamVO();
                batchJobParamVo.setParamName(batchTaskParamVo.getTaskParamName()); // 参数名
                batchJobParamVo.setTaskParamId(batchTaskParamVo.getTaskParamId()); // 对应作业参数Id
                batchJobParamVo.setIsManu(batchTaskParamVo.getIsManu()); // 是否手工输入
                batchJobParamVo.setParamType(batchTaskParamVo.getTaskParamDataType()); // 数据类型
                batchJobParamVo.setIsArray(batchTaskParamVo.getIsArray()); // 是否数组
                batchJobParamVo.setIsRequired(batchTaskParamVo.getIsRequired()); // 是否必须
                if (i != -1) {
                    batchJobParamVo.setParamValue(batchJobParamVOList.get(i).getParamValue());
                    batchJobParamVo.setJobParamId(batchJobParamVOList.get(i).getJobParamId());
                }

                // 查询参数信息
                if (batchJobParamVo.getIsManu().toString().equals("0")) {
                    paramManageVo = new ParamManageVO();
                    paramManageVo.setParamId(batchTaskParamVo.getParamId());
                    paramManageVo.setIsDeleted("0");
                    paramManageVo = paramManageUCC.find(paramManageVo);
                    // subParamVo = new SubParamVO();
                    // subParamVo.setParamId(batchTaskParamVo.getParamId()); //
                    // 参数id，用于查询子参数
                    // subParamVo.setIsDeleted("0"); // 查找未标记删除的数据
                    batchJobParamVo.setParamManageVo(paramManageVo);
                    // batchJobParamVo.setSubParamList(paramManageVo.getSubParamList());
                }

                batchJobParamList.add(batchJobParamVo);
                if (i != -1)
                    i++;
            }
            batchJobAVO.setBatchJobParamList(batchJobParamList);

            batchJobVO = new BatchJobVO();
            // 查询全部任务，不包含任务组
            batchJobVO.setIsGroup(IS_NOT_GROUP);
            jobList = batchJobUCC.findAll(batchJobVO);
            // 查询全部任务组
            batchJobVO.setIsGroup(IS_GROUP);
            jobGroupList = batchJobUCC.findAll(batchJobVO);
            batchTaskVO = new BatchTaskVO();
            batchTaskVO.setIsDeleted("0");
            taskList = batchTaskUCC.findAll(batchTaskVO);
        } catch (FrameworkException e) {
            e.printStackTrace();
            outAjax(Constants.DWZ_STATUSCODE_300, "open update page failed!", "", "", "");
            logger.error("open update page failed!", e);
        }
        return "showUpdateBatchJobPage";
    }

    /**
     * @description 打开Cron表达式页面
     * @version
     * @title
     * @return String Cron表达式页面
     */
    public String showBatchJobCronPage() {
        return "showBatchJobCronPage";
    }

    /**
     * @description 查询操作，根据查询条件查出数据
     * @version
     * @title
     * @return String 主页面
     */
    public String showBatchJob() {
        try {
            if (null != batchJobTVO) {
                batchJobTVO.setIsGroup(IS_NOT_GROUP);
                currentPage = getCurrentPage();
                currentPage.setTotal(batchJobUCC.findBatchAgentTotal(batchJobTVO));
                currentPage.setPageTotal(currentPage.getTotal() / currentPage.getPageSize() + 1);
                currentPage = batchJobUCC.queryForPage(batchJobTVO, currentPage);
            }
        } catch (FrameworkException e) {
            e.printStackTrace();
            this.errorMsg = "";
            return "error";
        }
        return "showBatchJobIndexPage";
    }

    /**
     * @description 新增操作，将新增的数据持久化
     * @version
     * @title
     * @return
     */
    public void insertBatchJob() {
        try {
            // 保存job基本信息，依赖信息以及参数信息
            batchJobUCC.add(batchJobAVO);
            // 调用依赖List填充方法
            fillJobDependList(batchJobAVO.getJobId());
            List<BatchJobDependVO> batchJobDependVOList = batchJobAVO.getBatchJobPreDependList();
            if ((batchJobDependVOList != null) && (batchJobDependVOList.size() > 0))
                batchJobDependUCC.batchSave(batchJobAVO.getBatchJobPreDependList());
            List<BatchJobDependVO> batchJobPostDependVOList = batchJobAVO.getBatchJobPostDependList();
            if ((batchJobPostDependVOList != null) && (batchJobPostDependVOList.size() > 0))
                batchJobDependUCC.batchSave(batchJobAVO.getBatchJobPostDependList());
            List<BatchJobParamVO> batchJobParamVOList = batchJobAVO.getBatchJobParamList();
            if ((batchJobParamVOList != null) && (batchJobParamVOList.size() > 0))
                batchJobParamUCC.batchSave(batchJobAVO.getBatchJobParamList());

            outAjax(Constants.DWZ_STATUSCODE_200, Constants.DWZ_MESSAGE_200, "", "", Constants.DWZ_CALLBACKTYPE_CLOSE);

            logger.info("insert data success");
        } catch (FrameworkException e) {
            e.printStackTrace();
            outAjax(Constants.DWZ_STATUSCODE_300, "insert data failed", "", "", "");
            logger.error("insert data failed", e);
        }
    }

    /**
     * @description 删除操作，将选中的一条数据删除
     * @version
     * @title
     * @return
     */
    public void delete() {
        try {
            String batchJobId = this.getRequest().getParameter("batchJobVO.batchJobId");
            batchJobVO = new BatchJobVO();
            batchJobVO.setJobId(new BigDecimal(batchJobId));
            BatchJobVO batchJobBackVO = batchJobUCC.find(batchJobVO);
            batchJobUCC.delete(batchJobBackVO);
            outAjax(Constants.DWZ_STATUSCODE_200, Constants.DWZ_MESSAGE_200, "", "", "");
            logger.info("delete data success");
        } catch (FrameworkException e) {
            e.printStackTrace();
            outAjax(Constants.DWZ_STATUSCODE_300, "delete data failed", "", "", "");
            logger.error("delete data failed", e);
        }
    }

    public void deleteBatchJob() {
        try {
            String batchJobId = this.getRequest().getParameter("batchJobVO.batchJobId");

            BatchJobDependVO batchJobDependVO = new BatchJobDependVO();
            batchJobDependVO.setActionJobId(new BigDecimal(batchJobId));
            batchJobDependVO.setIsDeleted("0");
            batchJobDependUCC.delete(batchJobDependVO);

            BatchJobParamVO batchJobParamVO = new BatchJobParamVO();
            batchJobParamVO.setJobId(new BigDecimal(batchJobId));
            batchJobParamVO.setIsDeleted("0");
            batchJobParamUCC.delete(batchJobParamVO);

            batchJobVO = new BatchJobVO();
            batchJobVO.setJobId(new BigDecimal(batchJobId));
            batchJobUCC.delete(batchJobVO);
            outAjax(Constants.DWZ_STATUSCODE_200, Constants.DWZ_MESSAGE_200, "", "", "");
            logger.info("delete data success");
        } catch (FrameworkException e) {
            e.printStackTrace();
            outAjax(Constants.DWZ_STATUSCODE_300, "delete data failed", "", "", "");
            logger.error("delete data failed", e);
        }
    }

    /**
     * @description 更新操作，将修改过的数据持久化
     * @version
     * @title
     * @return
     */
    public void update() {
        try {
            doUpadate();
            outAjax(Constants.DWZ_STATUSCODE_200, Constants.DWZ_MESSAGE_200, "", "", Constants.DWZ_CALLBACKTYPE_CLOSE);
            logger.info("update data success");
        } catch (FrameworkException e) {
            e.printStackTrace();
            outAjax(Constants.DWZ_STATUSCODE_300, "update data failed", "", "", "");
            logger.error("update data failed", e);
        }
    }

    @Transactional(readOnly = true,propagation=Propagation.REQUIRES_NEW)
    private void doUpadate() {
        // 调用依赖List填充方法
        batchJobUCC.update(batchJobAVO);
        fillJobDependList(batchJobAVO.getJobId());
        /*
         * List<BatchJobDependVO> batchJobPostDependList =
         * batchJobAVO.getBatchJobPostDependList();
         * if(!batchJobPostDependList.isEmpty())
         * batchJobDependUCC.batchUpdate(batchJobPostDependList);
         * List<BatchJobDependVO> batchJobProDependList =
         * batchJobAVO.getBatchJobPreDependList();
         * if(!batchJobProDependList.isEmpty())
         * batchJobDependUCC.batchUpdate(batchJobProDependList);
         */

        BatchJobDependVO batchJobDependVO = new BatchJobDependVO();
        batchJobDependVO.setActionJobId(batchJobAVO.getJobId());
        batchJobDependVO.setIsDeleted("0");
        batchJobDependUCC.delete(batchJobDependVO);

        List<BatchJobDependVO> batchJobPostDependList = batchJobAVO.getBatchJobPostDependList();
        if (!batchJobPostDependList.isEmpty())
            batchJobDependUCC.batchSave(batchJobPostDependList);
        List<BatchJobDependVO> batchJobPreDependList = batchJobAVO.getBatchJobPreDependList();
        if (!batchJobPreDependList.isEmpty())
            batchJobDependUCC.batchSave(batchJobPreDependList);
        // TODO 事务控制，目前在ucc导致事务没法统一控制
        // 将批量更新修改为先删后增任务参数（更新会存在问题，还是先删后增靠谱）
        /**
         * 1.先删除任务相关参数
         */
        BatchJobParamVO batchJobParamVO = new BatchJobParamVO();
        batchJobParamVO.setIsDeleted("0");
        batchJobParamVO.setJobId(batchJobAVO.getJobId());
        batchJobParamUCC.delete(batchJobParamVO);
        /**
         * 2.新增新的参数信息
         */
        List<BatchJobParamVO> batchJobParamList = batchJobAVO.getBatchJobParamList();
        if (batchJobParamList != null && !batchJobParamList.isEmpty()) {
             batchJobParamUCC.batchSave(batchJobParamList);
        }


    }

    /**
     * @description 填写batchJobPreDependList和batchJobPostDependList
     * @version
     * @title
     */
    public void fillJobDependList(BigDecimal jobId) {
        List<BatchJobDependVO> batchJobPreDependList = new ArrayList<BatchJobDependVO>();
        List<BatchJobDependVO> batchJobPostDependList = new ArrayList<BatchJobDependVO>();
        List<BatchJobParamVO> batchJobParamList = new ArrayList<BatchJobParamVO>();
        BatchJobDependVO tempJobDependVo;
        BatchJobParamVO batchJobParamVo;
        // 添加前置依赖batchJobPreDependList
        for (int i = 0; i < preDependJobId.length; i++) {
            if (!preDependJobId[0].equals("0")) {
                tempJobDependVo = new BatchJobDependVO();
                tempJobDependVo.setDependJobId(new BigDecimal(preDependJobId[i]));
                tempJobDependVo.setDependType(PRO_DEPEND_TYPE);
                tempJobDependVo.setDependStatus(preDependState[i]);
                if ((preDependId != null) && (preDependId.length > i) && (!preDependId[i].equals("-1")))
                    tempJobDependVo.setDependId(new BigDecimal(preDependId[i]));
                if (jobId != null)
                    tempJobDependVo.setActionJobId(jobId);
                batchJobPreDependList.add(tempJobDependVo);
            }
        }

        // 添加后置依赖batchJobPostDependList
        for (int i = 0; i < postDependJobId.length; i++) {
            if (!postDependJobId[0].equals("0")) {
                tempJobDependVo = new BatchJobDependVO();
                tempJobDependVo.setDependJobId(new BigDecimal(postDependJobId[i]));
                tempJobDependVo.setDependType(POST_DEPEND_TYPE);
                tempJobDependVo.setDependStatus(postDependState[i]);
                if ((postDependId != null) && (postDependId.length > i) && (!postDependId[i].equals("-1")))
                    tempJobDependVo.setDependId(new BigDecimal(postDependId[i]));
                if (jobId != null)
                    tempJobDependVo.setActionJobId(jobId);
                batchJobPostDependList.add(tempJobDependVo);
            }
        }

        String paramName = "";
        int count = 1;
        // 添加任务参数batchJobParamList
        for (int i = 0; i < taskParamName.length; i++) {
            batchJobParamVo = new BatchJobParamVO();
            batchJobParamVo.setTaskParamId(new BigDecimal(taskParamId[i]));
            // TODO 是：1；否：0。后续待统一
            batchJobParamVo.setIsDeleted("0");
            batchJobParamVo.setVer("1.0.0");
            batchJobParamVo.setParamValue(paramValue[i]);
            if (jobParamId != null) {
                logger.debug("看看是第几个,{}",i);
                logger.debug("看看是参数大小,{}",jobParamId.length);
                logger.debug("看看是参数是啥,{}",jobParamId[i]);
                batchJobParamVo.setJobParamId(new BigDecimal(jobParamId[i]));
            }
            if (jobId != null)
                batchJobParamVo.setJobId(jobId);

            // 数组类型参数做下标排序
            if (isArray[i].equals("1")) {
                if (paramName.equals(taskParamName[i])) {
                    count++;
                    batchJobParamVo.setArrayParamOrder(new BigDecimal(count));
                } else {
                    batchJobParamVo.setArrayParamOrder(new BigDecimal(1));
                    paramName = taskParamName[i];
                    count = 1;
                }
            } else {
                batchJobParamVo.setArrayParamOrder(new BigDecimal(1));
            }
            batchJobParamList.add(batchJobParamVo);
        }
        batchJobAVO.setBatchJobPreDependList(batchJobPreDependList);
        batchJobAVO.setBatchJobPostDependList(batchJobPostDependList);
        batchJobAVO.setBatchJobParamList(batchJobParamList);
    }

    /**
     * @description 根据作业id，查询作业相关信息
     * @version
     * @title
     * @return
     */
    public void getTaskInfo() {
        try {
            // 通过taskId,获取作业信息BatchTaskVO,此VO中包含作业参数列表信息
            taskId = this.getRequest().getParameter("taskID");
            List<BatchJobParamVO> batchJobParamList = getJobParamById(taskId);

            // 将数据类型为Map的List放入一个map中
            Map<String, Object> obj = new HashMap<String, Object>();

            // 转换成json对象传递给前台页面
            obj.put("batchJobParamList", batchJobParamList);
            outJson(obj);

            // outAjax(Constants.DWZ_STATUSCODE_200, Constants.DWZ_MESSAGE_200,
            // "", "", "");
            logger.info("update data success");
        } catch (FrameworkException e) {
            e.printStackTrace();
            outAjax(Constants.DWZ_STATUSCODE_300, "作业信息查询失败", "", "", "");
            logger.error("update data failed", e);
        }
    }

    private List<BatchJobParamVO> getJobParamById(String taskId) {
        BatchTaskVO batchTaskVO = new BatchTaskVO();
        batchTaskVO.setTaskId(new BigDecimal(taskId));
        batchTaskVO = batchTaskUCC.find(batchTaskVO);
        List<BatchJobParamVO> batchJobParamList = new ArrayList<BatchJobParamVO>();
        BatchJobParamVO batchJobParamVo;
        // SubParamVO subParamVo;
        ParamManageVO paramManageVo;
        for (BatchTaskParamVO batchTaskParamVo : batchTaskVO.getBatchTaskParamVOList()) {
            batchJobParamVo = new BatchJobParamVO();
            batchJobParamVo.setParamName(batchTaskParamVo.getTaskParamName()); // 参数名
            batchJobParamVo.setTaskParamId(batchTaskParamVo.getTaskParamId()); // 对应作业参数Id
            batchJobParamVo.setIsManu(batchTaskParamVo.getIsManu()); // 是否手工输入
            batchJobParamVo.setParamType(batchTaskParamVo.getTaskParamDataType()); // 数据类型
            batchJobParamVo.setIsArray(batchTaskParamVo.getIsArray()); // 是否数组
            batchJobParamVo.setIsRequired(batchTaskParamVo.getIsRequired()); // 是否必须

            // 查询参数信息
            if (batchJobParamVo.getIsManu().toString().equals("0")) {
                paramManageVo = new ParamManageVO();
                paramManageVo.setParamId(batchTaskParamVo.getParamId());
                paramManageVo.setIsDeleted("0");
                paramManageVo = paramManageUCC.find(paramManageVo);
                // subParamVo = new SubParamVO();
                // subParamVo.setParamId(batchTaskParamVo.getParamId()); //
                // 参数id，用于查询子参数
                // subParamVo.setIsDeleted("0"); // 查找未标记删除的数据
                batchJobParamVo.setParamManageVo(paramManageVo);
                // batchJobParamVo.setSubParamList(paramManageVo.getSubParamList());
            }

            batchJobParamList.add(batchJobParamVo);
        }
        return batchJobParamList;
    }

    /**
     * @description 打开任务执行管理页面
     * @version
     * @title
     * @author yangfeiit@newchinalife.com
     * @return 登录页面
     */
    public String showBatchJobExecutePage() {
        return "showBatchJobExecutePage";
    }

    /**
     * @description 查询任务，生成任务树
     * @version
     * @title
     * @return
     */
    public void getBatchJobTree() {
        BatchJobVO jobGroupVO = new BatchJobVO();
        List<BatchJobVO> jobTreeList = new ArrayList<BatchJobVO>();
        jobTreeList = batchJobUCC.findAll(jobGroupVO);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;
        // 遍历获取到的菜单集合将每个菜单的属性放入一个map中
        for (BatchJobVO key : jobTreeList) {
            map = new HashMap<String, Object>();
            // pId,id和name 是生成菜单树的必要条件
            map.put("id", key.getJobId());
            map.put("pId", key.getJobGroupId());
            map.put("name", key.getJobName());
            list.add(map);
        }
        // 将数据类型为Map的List放入一个map中
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("jobTreeList", list);
        outJson(obj);
    }

    /**
     * @description 显示任务组包含的任务
     * @version
     * @title
     */
    public void showJobGroupInfo() {
        try {
            String jobId = this.getRequest().getParameter("jobId");
            batchJobVO = new BatchJobVO();
            batchJobVO.setJobGroupId(new BigDecimal(jobId));
            batchJobVO.setIsGroup("0");
            batchJobList = new ArrayList<BatchJobVO>();
            batchJobList = batchJobUCC.findAll(batchJobVO);
            batchJobVO.setJobId(new BigDecimal(jobId));
            batchJobVO = batchJobUCC.find(batchJobVO);
            if (batchJobList.size() == 0 && !(batchJobVO.getJobType().equals("G"))) {
                batchJobList.add(batchJobVO);
            }
            // 将数据类型为Map的List放入一个map中
            Map<String, Object> obj = new HashMap<String, Object>();
            obj.put("batchJobList", batchJobList);
            outJson(obj);
        } catch (FrameworkException e) {
            outAjax(Constants.DWZ_STATUSCODE_300, Constants.DWZ_MESSAGE_300, "", "", "");
            throw new FrameworkException(e.getMessage());
        }
    }

    private void startBatchJob(BatchJobVO batchJobVO) {
        if (batchJobVO == null) {
            return;
        }
        String jobId = batchJobVO.getJobId().toPlainString();
        logger.info("————————————————开始启动任务{}————————————————", jobId);
        // 判断jobId是否为Group组任务，如果是则按任务组启动任务
        if (batchJobVO.getIsGroup().equals("1")) {

            logger.debug("任务{}为任务组,启动任务组{}", jobId, jobId);
            BatchJobVO job = new BatchJobVO();
            job.setJobGroupId(new BigDecimal(jobId));
            // 判断是group任务组的情况
            List<BatchJobVO> jobList = batchJobUCC.findExcuteJobAll(job);
            if (jobList.size() > 0) {
                // 循环启动任务(若为任务组不启动)
                logger.debug("启动任务组{}下的子任务", jobId);
                for(BatchJobVO vo:jobList){
                    if("0".equals(vo.getIsGroup())){
                        sendJob(startJobController, vo.getJobId().toPlainString());
                    }else if("1".equals(vo.getIsGroup())){
                        logger.debug("启动任务组{}下的子任务组", jobId);
                        startBatchJob(vo.getJobId().toPlainString());
                    }
                }
                /*
                 * --lamebda syntax
                jobList.stream().filter(x -> x.getIsGroup().equals("0"))
                        .forEach(x -> );
                logger.debug("启动任务组{}下的子任务组", jobId);
                jobList.stream().filter(x -> x.getIsGroup().equals("1"))
                        .forEach(x ->);*/
            } else {
                logger.debug("任务组{}无子任务", jobId);
            }
        } else {
            // 不是group任务组的情况
            logger.debug("任务{}为执行任务,启动任务{}", jobId, jobId);
            sendJob(startJobController, jobId);
        }
        logger.info("————————————————启动任务{}完成————————————————", jobId);
    }

    /**
     * @description 手动启动任务
     * @version
     * @title
     * @param jobId
     * @return
     */
    private void startBatchJob(String jobId) {
        Preconditions.checkNotNull(jobId);
        BatchJobVO batchJobVO = new BatchJobVO();
        batchJobVO.setJobId(new BigDecimal(jobId));
        batchJobVO = batchJobUCC.find(batchJobVO);
        startBatchJob(batchJobVO);
    }

    @Transactional(readOnly = false)
    public void startBatchJob() {
        String jobID = this.getRequest().getParameter("jobId");
        startBatchJob(jobID);
    }

    /**
     * @description 重复启动检查
     * @version
     * @title
     * @return
     */
    public void repeatCheck() {
        String jobId = this.getRequest().getParameter("jobId");
        BatchJobRunVO batchJobRunVO = new BatchJobRunVO();
        batchJobRunVO.setJobId(new BigDecimal(jobId));
        batchJobRunVO.setStatus(JobRunStatus.RUNNING);
        // 查询条件为运行中的任务JobId结果集
        List<BatchJobRunVO> jobRunBackVOList = batchJobRunUCC.findAll(batchJobRunVO);
        int resultType = 0;
        Map<String, Object> obj = new HashMap<String, Object>();
        if (jobRunBackVOList.size() == 0) {
            /**
             * 若不存在当前jobId正在运行，则启动当前任务
             */
            logger.debug("========不存在正在运行的当前任务，任务ID为：{}==========", jobId);
            startBatchJob(jobId);
            resultType = 1;
        } else {
            /**
             * 若存在当前正在运行的任务jobId，则返回一个resultType标志给前台，告诉前台页面已经有正在运行的任务
             */
            logger.debug("========已存在正在运行的当前任务，任务ID为：{}==========", jobId);
        }
        obj.put("resultType", resultType);
        outJson(obj);

    }

    /**
     * @description 任务起停管理
     * @version
     * @title
     * @return
     */
    private void sendJob(IJobController jobController, String jobId) {
        BatchJobRunVO batchJob = new BatchJobRunVO();
        batchJob.setJobId(new BigDecimal(jobId));
        BatchJobRunVO findRun = batchJobRunUCC.find(batchJob);
        JobSessionContext jsContext = new JobSessionContext();
        jsContext.setJobId(jobId);
        if (findRun != null && findRun.getJobRunId() != null) {
            jsContext.setJobRunId(findRun.getJobRunId().toString());
        }
        jobController.control(jsContext);
    }

    public void setStartJobController(IJobController startJobController) {
        this.startJobController = startJobController;
    }

    public void setParamManageUCC(IParamManageUCC paramManageUCC) {
        this.paramManageUCC = paramManageUCC;
    }

    /**
     * @return the batchJobDependUCC
     */
    public IBatchJobDependUCC getBatchJobDependUCC() {
        return batchJobDependUCC;
    }

    /**
     * @param batchJobDependUCC the batchJobDependUCC to set
     */
    public void setBatchJobDependUCC(IBatchJobDependUCC batchJobDependUCC) {
        this.batchJobDependUCC = batchJobDependUCC;
    }

    /**
     * @return the batchJobParamUCC
     */
    public IBatchJobParamUCC getBatchJobParamUCC() {
        return batchJobParamUCC;
    }

    /**
     * @param batchJobParamUCC the batchJobParamUCC to set
     */
    public void setBatchJobParamUCC(IBatchJobParamUCC batchJobParamUCC) {
        this.batchJobParamUCC = batchJobParamUCC;
    }

    /**
     * @return the preDependId
     */
    public String[] getPreDependId() {
        return preDependId;
    }

    /**
     * @param preDependId the preDependId to set
     */
    public void setPreDependId(String[] preDependId) {
        this.preDependId = preDependId;
    }

    /**
     * @return the postDependId
     */
    public String[] getPostDependId() {
        return postDependId;
    }

    /**
     * @param postDependId the postDependId to set
     */
    public void setPostDependId(String[] postDependId) {
        this.postDependId = postDependId;
    }
}
