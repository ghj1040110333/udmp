package cn.com.git.udmp.batch.web.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.git.udmp.batch.web.base.FrameworkBaseAction;
import cn.com.git.udmp.common.exception.FrameworkException;
import cn.com.git.udmp.component.model.Constants;
import cn.com.git.udmp.impl.batch.jobRunLog.ucc.IBatchJobRunLogUCC;
import cn.com.git.udmp.impl.batch.jobRunLog.vo.BatchJobRunLogVO;

/**
 * 
 * @description 批处理LogAction 
 * @author Spring Cao
 * @date 2016年8月30日 上午11:46:20
 */
@Service
@Scope("prototype")
@Transactional
public class BatchJobRunLogAction extends FrameworkBaseAction {
    private static final long serialVersionUID = 1L;
    @Autowired
    private IBatchJobRunLogUCC batchJobRunLogUCC;
    private BatchJobRunLogVO batchJobRunLogVO;

    /** 查询使用VO */
    private BatchJobRunLogVO batchJobRunLogTVO = new BatchJobRunLogVO();

    /** 增加修改删除使用VO */
    private BatchJobRunLogVO batchJobRunLogAVO = new BatchJobRunLogVO();

    private List<BatchJobRunLogVO> batchJobRunLogList;

    public BatchJobRunLogVO getBatchJobRunLogAVO() {
        return batchJobRunLogAVO;
    }

    public void setBatchJobRunLogAVO(BatchJobRunLogVO batchJobRunLogAVO) {
        this.batchJobRunLogAVO = batchJobRunLogAVO;
    }

    public BatchJobRunLogVO getBatchJobRunLogTVO() {
        return batchJobRunLogTVO;
    }

    public void setBatchJobRunLogTVO(BatchJobRunLogVO batchJobRunLogTVO) {
        this.batchJobRunLogTVO = batchJobRunLogTVO;
    }

    public BatchJobRunLogVO getBatchJobRunLogVO() {
        return batchJobRunLogVO;
    }

    public void setBatchJobRunLogVO(BatchJobRunLogVO batchJobRunLogVO) {
        this.batchJobRunLogVO = batchJobRunLogVO;
    }

    public IBatchJobRunLogUCC getBatchJobRunLogUCC() {
        return batchJobRunLogUCC;
    }

    public void setBatchJobRunLogUCC(IBatchJobRunLogUCC batchJobRunLogUCC) {
        this.batchJobRunLogUCC = batchJobRunLogUCC;
    }

    public List<BatchJobRunLogVO> getBatchJobRunLogList() {
        return batchJobRunLogList;
    }

    public void setBatchJobRunLogList(List<BatchJobRunLogVO> batchJobRunLogList) {
        this.batchJobRunLogList = batchJobRunLogList;
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
    public String showBatchJobRunLogIndexPage() {
        return "showBatchJobRunLogIndexPage";
    }

    /**
     * @description 打开详细页面
     * @version
     * @title
     * @return 详细页面
     */
    public String showBatchJobRunLogDetailPage() {
        try {
            String batchJobRunLogId = this.getRequest().getParameter("batchJobRunLogVO.batchJobRunLogId");
            if (batchJobRunLogId == null) {
                this.errorMsg = "";
                return "error";
            }
            batchJobRunLogVO = new BatchJobRunLogVO();
            batchJobRunLogVO.setJobRunLogId(new BigDecimal(batchJobRunLogId));
            batchJobRunLogAVO = batchJobRunLogUCC.find(batchJobRunLogVO);
        } catch (FrameworkException e) {
            e.printStackTrace();
            outAjax(Constants.DWZ_STATUSCODE_300, "open update page failed!", "", "", "");
            logger.error("open update page failed!", e);
        }
        return "showBatchJobRunLogDetailPage";
    }

    /**
     * @description 查询操作，根据查询条件查出数据
     * @version
     * @title
     * @return 登录页面
     */
    public String showBatchJobRunLog() {
        try {
            if (null != batchJobRunLogTVO) {
                currentPage = batchJobRunLogUCC.queryForPage(batchJobRunLogTVO, getCurrentPage());
            }
        } catch (FrameworkException e) {
            e.printStackTrace();
            this.errorMsg = "";
            return "error";
        }
        return "showBatchJobRunLogIndexPage";
    }
}
