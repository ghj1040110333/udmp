package cn.com.git.udmp.batch.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;

import cn.com.git.udmp.batch.controller.base.BaseBatchController;
import cn.com.git.udmp.common.exception.FrameworkException;
import cn.com.git.udmp.common.persistence.Page;
import cn.com.git.udmp.common.persistence.Table;
import cn.com.git.udmp.component.model.CurrentPage;
import cn.com.git.udmp.impl.batch.jobRun.ucc.IBatchJobRunUCC;
import cn.com.git.udmp.impl.batch.jobRun.vo.BatchJobRunVO;

/** 
 * @description 
 * @author Liang liuliang1@git.com.cn 
 * @date 2017年3月28日 下午5:43:27  
*/
@Controller
@RequestMapping("${adminPath}/batch/jobRun")
public class BatchJobRunController extends BaseBatchController {

    @Autowired
    private IBatchJobRunUCC batchJobRunUCC;

    /**
     * @title
     * @description
     * @testUrl "http://localhost:8181/udmp_batch_webapp/a/batch/JobRun/list?pageNo=1&&pageSize=10"
     * @param pageNo
     * @param pageSize
     * @param JobRunVO
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Table list(int pageNo, int pageSize, BatchJobRunVO JobRunVO) {
        CurrentPage<BatchJobRunVO> currentPage = new CurrentPage<BatchJobRunVO>();
        currentPage.setPageNo(pageNo);
        currentPage.setPageSize(pageSize);
        CurrentPage<BatchJobRunVO> result = batchJobRunUCC.queryForPage(JobRunVO, currentPage);
        Page<BatchJobRunVO> page = currentPageToPage(pageNo, pageSize, result);
        return new Table(page);
    }

    @RequestMapping("/save")
    @ResponseBody
    public HashMap<Object, Object> save(BatchJobRunVO batchJobRunAVO) {
        HashMap<Object, Object> result = Maps.newHashMap();
        try {
            if (batchJobRunAVO.getJobRunId() != null) {
                batchJobRunUCC.update(batchJobRunAVO);
                logger.info("更新代理成功");
            } else {
                batchJobRunUCC.add(batchJobRunAVO);
                logger.info("新增代理成功");
            }
            result.put("success", true);
        } catch (FrameworkException e) {
            // outAjax(Constants.DWZ_STATUSCODE_300, "新增代理失败", "", "", "");
            logger.error("操作失败", e);
            result.put("success", false);
            result.put("message", e.getMessage());
        }
        return result;
    }
    
    @RequestMapping("/delete")
    @ResponseBody
    public HashMap<Object, Object> delete(BatchJobRunVO batchJobRunAVO) {
        HashMap<Object, Object> result = Maps.newHashMap();
        Preconditions.checkNotNull(batchJobRunAVO.getJobRunId());
        try {
            if (batchJobRunAVO.getJobRunId() != null) {
                batchJobRunUCC.delete(batchJobRunAVO);
                logger.info("删除代理成功");
            } 
            result.put("success", true);
        } catch (FrameworkException e) {
            logger.error("操作失败", e);
            result.put("success", false);
            result.put("message", e.getMessage());
        }
        return result;
    }

    /**
     * @title 根据AgentId查询Agent信息
     * @description
     * 
     * @param batchJobRunAVO
     * @return
     */
    @RequestMapping("/queryById")
    @ResponseBody
    public Map<String, Object> queryById(BatchJobRunVO batchJobRunAVO) {
        Preconditions.checkNotNull(batchJobRunAVO.getJobRunId());
        Map<String, Object> result = Maps.newHashMap();
        result.put("success", false);
        BatchJobRunVO resultVO = batchJobRunUCC.find(batchJobRunAVO);
        if (resultVO != null) {
            result.put("success", true);
            result.put("data", resultVO);
        }
        return result;
    }

    /**
     * @description 首页跳转
     * @return
     */
    @RequestMapping("/form")
    public String toForm(BatchJobRunVO batchJobRunAVO) {
        return "batch/batchJobRunForm";
    }

    /**
     * @description 首页跳转
     * @return
     */
    @RequestMapping("/index")
    public String toIndex() {
        return "batch/batchJobRunIndex";
    }

}

