package cn.com.git.udmp.batch.web.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;

import cn.com.git.udmp.batch.web.base.FrameworkBaseAction;
import cn.com.git.udmp.common.exception.FrameworkException;
import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.core.server.job.IJobController;
import cn.com.git.udmp.component.batch.core.server.job.exception.CancelException;
import cn.com.git.udmp.component.model.Constants;
import cn.com.git.udmp.impl.batch.job.ucc.IBatchJobUCC;
import cn.com.git.udmp.impl.batch.job.vo.BatchJobVO;

/**
 * 
 * @description 任务管理
 * @author Spring Cao
 * @date 2016年8月30日 上午11:42:56
 */
@Service
@Scope("prototype")
@Transactional
public class BatchAsyncJobAction extends FrameworkBaseAction {
    private static final long serialVersionUID = 1L;
    /** 
    * @Fields startJobController : 此处注入的是dispatchJobController，调用查询批处理分片的责任链 
    */ 
    @Autowired
    @Qualifier("dispatchJobController")
    IJobController startJobController;

    /** 异步任务任务类型 */
    private static String ASYNC_JOB_STYPE = "A";

    /** 任务ucc */
    @Autowired
    private IBatchJobUCC batchJobUCC;

    private BatchJobVO batchAsyncJobVO;

    /** 查询使用VO */
    private BatchJobVO batchAsyncJobTVO = new BatchJobVO();

    /** 增加修改删除使用VO */
    private BatchJobVO batchAsyncJobAVO = new BatchJobVO();
    /**
     * @Fields paramValue : 异步任务参数值
     */
    private String[] paramValue = new String[]{};

    @Override
    public String getBizId() {
        return null;
    }

    /**
     * @description 打开主查询页面
     * @version
     * @title
     * @author yangfeiit@newchinalife.com
     * @return 登录页面
     */
    public String showBatchAsyncJobIndexPage() {
        return "showBatchAsyncJobIndexPage";
    }

    /**
     * @description 打开详细页面
     * @version
     * @title
     * @author yangfeiit@newchinalife.com
     * @return String 修改页面
     */
    public String showBatchAsyncJobDetailPage() {
        try {
            String batchAsyncJobId = this.getRequest().getParameter("batchAsyncJobVO.batchAsyncJobId");
            if (batchAsyncJobId == null) {
                this.errorMsg = "";
                return "error";
            }
            batchAsyncJobVO = new BatchJobVO();
            batchAsyncJobVO.setJobId(new BigDecimal(batchAsyncJobId));
            batchAsyncJobAVO = batchJobUCC.find(batchAsyncJobVO);
        } catch (FrameworkException e) {
            e.printStackTrace();
            outAjax(Constants.DWZ_STATUSCODE_300, "open update page failed!", "", "", "");
            logger.error("open update page failed!", e);
        }
        return "showBatchAsyncJobDetailPage";
    }

    /**
     * @description 查询操作，根据查询条件查出数据
     * @version
     * @title
     * @author yangfeiit@newchinalife.com
     * @return String 主页面
     */
    public String showBatchAsyncJob() {
        try {
            if (null != batchAsyncJobTVO) {
                batchAsyncJobTVO.setJobType(ASYNC_JOB_STYPE);
				currentPage = getCurrentPage();
				currentPage.setTotal(batchJobUCC.findBatchAgentTotal(batchAsyncJobTVO));
				currentPage.setPageTotal(currentPage.getTotal()	/ currentPage.getPageSize() + 1);
                currentPage = batchJobUCC.queryForPage(batchAsyncJobTVO, currentPage);
            }
        } catch (FrameworkException e) {
            e.printStackTrace();
            this.errorMsg = "";
            return "error";
        }
        return "showBatchAsyncJobIndexPage";
    }

    /**
     * @description 填写任务参数
     * @version
     * @title
     * @author yangfeiit@newchinalife.com
     * @return String 主页面
     */
    public void fillAsyncJobParamList() {
        if (null != batchAsyncJobAVO) {
            batchAsyncJobVO = new BatchJobVO();
            batchAsyncJobVO.setJobId(batchAsyncJobAVO.getJobId());
            batchAsyncJobAVO = batchJobUCC.find(batchAsyncJobVO);
            for (int i = 0; i < paramValue.length; i++) {
                batchAsyncJobAVO.getBatchJobParamList().get(i).setParamValue(paramValue[i]);
            }
        }
        logger.debug("异步任务参数值更新完成");

    }

    /**
     * @description 手动启动任务 <li>将配置的任务参数值入库并且启动当前异步任务</li>
     * @version
     * @title
     * @author yangfeiit@newchinalife.com
     * @param jobId
     * @return
     */
    public void startBatchAsyncJob() {
        BigDecimal jobId = batchAsyncJobAVO.getJobId();
        Preconditions.checkNotNull(jobId);
        try {
            // 将配置的任务参数值入库
            fillAsyncJobParamList();
            // 启动当前异步任务
            logger.debug("异步任务管理--开始启动异步任务{}", jobId);
            sendJob(startJobController, jobId.toString());
            // 返回任务启动成功的信息到前端
            outAjax(Constants.DWZ_STATUSCODE_200, "任务启动成功!", "", "", Constants.DWZ_CALLBACKTYPE_CLOSE);
        }catch (FrameworkException e) {
            logger.debug("异步任务管理-启动任务异常{}", e);
            outAjax(Constants.DWZ_STATUSCODE_300, e.getErrCode(), "", "", Constants.DWZ_CALLBACKTYPE_CLOSE);
        }catch (RuntimeException e) {
            logger.debug("异步任务管理-启动任务异常{}", e);
            outAjax(Constants.DWZ_STATUSCODE_300, e.getMessage(), "", "", Constants.DWZ_CALLBACKTYPE_CLOSE);
        }
        logger.info("————————————————启动任务————————————————");
    }

    /**
     * @description 通过任务ID控制任务（启动或者停止 ）
     * @version
     * @title
     * @author yangfeiit@newchinalife.com
     * @return
     */
    private void sendJob(IJobController jobController, String jobId) {
        JobSessionContext jsContext = new JobSessionContext();
        jsContext.setJobId(jobId);
        //modified by L.Liang 判断如果启动失败，返回启动异常信息
        boolean resultFlag = jobController.control(jsContext);
        if(!resultFlag){
            throw new RuntimeException("任务启动条件不满足，服务启动失败");
        }
    }

    public void setStartJobController(IJobController startJobController) {
        this.startJobController = startJobController;
    }

    public IBatchJobUCC getBatchJobUCC() {
        return batchJobUCC;
    }

    public void setBatchJobUCC(IBatchJobUCC batchJobUCC) {
        this.batchJobUCC = batchJobUCC;
    }

    public BatchJobVO getBatchAsyncJobVO() {
        return batchAsyncJobVO;
    }

    public void setBatchAsyncJobVO(BatchJobVO batchAsyncJobVO) {
        this.batchAsyncJobVO = batchAsyncJobVO;
    }

    public BatchJobVO getBatchAsyncJobTVO() {
        return batchAsyncJobTVO;
    }

    public void setBatchAsyncJobTVO(BatchJobVO batchAsyncJobTVO) {
        this.batchAsyncJobTVO = batchAsyncJobTVO;
    }

    public BatchJobVO getBatchAsyncJobAVO() {
        return batchAsyncJobAVO;
    }

    public void setBatchAsyncJobAVO(BatchJobVO batchAsyncJobAVO) {
        this.batchAsyncJobAVO = batchAsyncJobAVO;
    }

    public String[] getParamValue() {
        return paramValue;
    }

    public void setParamValue(String[] paramValue) {
        this.paramValue = paramValue;
    }

}
