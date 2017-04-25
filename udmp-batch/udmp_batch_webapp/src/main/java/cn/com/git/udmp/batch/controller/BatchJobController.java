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
import cn.com.git.udmp.component.model.Constants;
import cn.com.git.udmp.component.model.CurrentPage;
import cn.com.git.udmp.impl.batch.job.ucc.IBatchJobUCC;
import cn.com.git.udmp.impl.batch.job.vo.BatchJobVO;

/** 
 * @description 
 * @author Liang liuliang1@git.com.cn 
 * @date 2017年3月28日 下午5:43:27  
*/
@Controller
@RequestMapping("${adminPath}/batch/job")
public class BatchJobController extends BaseBatchController {

    @Autowired
    private IBatchJobUCC batchJobUCC;

    /**
     * @title
     * @description
     * @testUrl "http://localhost:8181/udmp_batch_webapp/a/batch/job/list?pageNo=1&&pageSize=10"
     * @param pageNo
     * @param pageSize
     * @param jobVO
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Table list(int pageNo, int pageSize, BatchJobVO jobVO) {
        CurrentPage<BatchJobVO> currentPage = new CurrentPage<BatchJobVO>();
        currentPage.setPageNo(pageNo);
        currentPage.setPageSize(pageSize);
        //查询非任务组任务
        jobVO.setIsGroup(String.valueOf(Constants.IDEN_IS_NO));
        CurrentPage<BatchJobVO> result = batchJobUCC.queryForPage(jobVO, currentPage);
        Page<BatchJobVO> page = currentPageToPage(pageNo, pageSize, result);
        return new Table(page);
    }

    @RequestMapping("/save")
    @ResponseBody
    public HashMap<Object, Object> save(BatchJobVO batchJobAVO) {
        //由前端拼装参数信息、前后置依赖信息和任务的基本信息
        HashMap<Object, Object> result = Maps.newHashMap();
        batchJobAVO.setIsDeleted("0");
        try {
            if (batchJobAVO.getJobId() != null) {
                batchJobUCC.update(batchJobAVO);
                logger.info("更新代理成功");
            } else {
                batchJobUCC.addWhoelJob(batchJobAVO);
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
    public HashMap<Object, Object> delete(BatchJobVO batchJobAVO) {
        HashMap<Object, Object> result = Maps.newHashMap();
        Preconditions.checkNotNull(batchJobAVO.getJobId());
        try {
            if (batchJobAVO.getJobId() != null) {
                batchJobUCC.delete(batchJobAVO);
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
     * @param batchJobAVO
     * @return
     */
    @RequestMapping("/queryById")
    @ResponseBody
    public Map<String, Object> queryById(BatchJobVO batchJobAVO) {
        Preconditions.checkNotNull(batchJobAVO.getJobId());
        Map<String, Object> result = Maps.newHashMap();
        result.put("success", false);
        BatchJobVO resultVO = batchJobUCC.find(batchJobAVO);
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
    public String toForm(BatchJobVO batchJobAVO) {
        return "batch/batchJobForm";
    }

    /**
     * @description 首页跳转
     * @return
     */
    @RequestMapping("/index")
    public String toIndex() {
        return "batch/batchJobIndex";
    }

}

