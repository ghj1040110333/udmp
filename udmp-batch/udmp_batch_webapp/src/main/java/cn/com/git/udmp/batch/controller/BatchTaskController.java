package cn.com.git.udmp.batch.controller;

import java.util.HashMap;
import java.util.List;
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
import cn.com.git.udmp.impl.batch.task.ucc.IBatchTaskUCC;
import cn.com.git.udmp.impl.batch.task.vo.BatchTaskParamVO;
import cn.com.git.udmp.impl.batch.task.vo.BatchTaskVO;

/** 
 * @description 
 * @author Liang liuliang1@git.com.cn 
 * @date 2017年3月28日 下午5:43:27  
*/
@Controller
@RequestMapping("${adminPath}/batch/task")
public class BatchTaskController extends BaseBatchController {

    @Autowired
    private IBatchTaskUCC batchTaskUCC;
    
    
    /**
     * @title
     * @description
     * @testUrl "http://localhost:8181/udmp_batch_webapp/a/batch/Task/list?pageNo=1&&pageSize=10"
     * @param pageNo
     * @param pageSize
     * @param TaskVO
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Table list(int pageNo, int pageSize, BatchTaskVO TaskVO) {
        CurrentPage<BatchTaskVO> currentPage = new CurrentPage<BatchTaskVO>();
        currentPage.setPageNo(pageNo);
        currentPage.setPageSize(pageSize);
        CurrentPage<BatchTaskVO> result = batchTaskUCC.queryForPage(TaskVO, currentPage);
        Page<BatchTaskVO> page = currentPageToPage(pageNo, pageSize, result);
        return new Table(page);
    }

    @RequestMapping("/save")
    @ResponseBody
    public HashMap<Object, Object> save(BatchTaskVO batchTaskAVO) {
        HashMap<Object, Object> result = Maps.newHashMap();
        batchTaskAVO.setIsDeleted("0");
        try {
            if (batchTaskAVO.getTaskId() != null) {
                //更新作业及其参数信息
                batchTaskUCC.update(batchTaskAVO,batchTaskAVO.getBatchTaskParamVOList());
                logger.info("更新作业成功");
            } else {
                //新增作业及其参数信息
                batchTaskUCC.add(batchTaskAVO,batchTaskAVO.getBatchTaskParamVOList());
                logger.info("新增作业成功");
            }
            result.put("success", true);
        } catch (FrameworkException e) {
            // outAjax(Constants.DWZ_STATUSCODE_300, "新增作业失败", "", "", "");
            logger.error("操作失败", e);
            result.put("success", false);
            result.put("message", e.getMessage());
        }
        return result;
    }
    
    @RequestMapping("/delete")
    @ResponseBody
    public HashMap<Object, Object> delete(BatchTaskVO batchTaskAVO) {
        HashMap<Object, Object> result = Maps.newHashMap();
        Preconditions.checkNotNull(batchTaskAVO.getTaskId());
        try {
            if (batchTaskAVO.getTaskId() != null) {
                batchTaskUCC.delete(batchTaskAVO);
                logger.info("删除作业成功");
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
     * @param batchTaskAVO
     * @return
     */
    @RequestMapping("/queryById")
    @ResponseBody
    public Map<String, Object> queryById(BatchTaskVO batchTaskAVO) {
        Preconditions.checkNotNull(batchTaskAVO.getTaskId());
        Map<String, Object> result = Maps.newHashMap();
        result.put("success", false);
        //查询作业及其参数信息
        BatchTaskVO resultVO = batchTaskUCC.find(batchTaskAVO);
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
    public String toForm(BatchTaskVO batchTaskAVO) {
        return "batch/batchTaskForm";
    }

    /**
     * @description 首页跳转
     * @return
     */
    @RequestMapping("/index")
    public String toIndex() {
        return "batch/batchTaskIndex";
    }

}

