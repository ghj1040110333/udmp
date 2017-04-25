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
import cn.com.git.udmp.impl.batch.agent.ucc.IBatchAgentUCC;
import cn.com.git.udmp.impl.batch.agent.vo.BatchAgentVO;

@Controller
@RequestMapping("${adminPath}/batch/agent")
public class BatchAgentController extends BaseBatchController {

    @Autowired
    private IBatchAgentUCC batchAgentUCC;

    /**
     * @title
     * @description
     * @testUrl "http://localhost:8181/udmp_batch_webapp/a/batch/agent/list?pageNo=1&&pageSize=10"
     * @param pageNo
     * @param pageSize
     * @param agentVO
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Table list(int pageNo, int pageSize, BatchAgentVO agentVO) {
        CurrentPage<BatchAgentVO> currentPage = new CurrentPage<BatchAgentVO>();
        currentPage.setPageNo(pageNo);
        currentPage.setPageSize(pageSize);
        CurrentPage<BatchAgentVO> result = batchAgentUCC.queryForPage(agentVO, currentPage);
        Page<BatchAgentVO> page = currentPageToPage(pageNo, pageSize, result);
        return new Table(page);
    }

    @RequestMapping("/save")
    @ResponseBody
    public HashMap<Object, Object> save(BatchAgentVO batchAgentAVO) {
        HashMap<Object, Object> result = Maps.newHashMap();
        batchAgentAVO.setIsDeleted("0");
        try {
            if (batchAgentAVO.getAgentId() != null) {
                batchAgentUCC.update(batchAgentAVO);
                logger.info("更新代理成功");
            } else {
                batchAgentUCC.add(batchAgentAVO);
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
    public HashMap<Object, Object> delete(BatchAgentVO batchAgentAVO) {
        HashMap<Object, Object> result = Maps.newHashMap();
        Preconditions.checkNotNull(batchAgentAVO.getAgentId());
        try {
            if (batchAgentAVO.getAgentId() != null) {
                batchAgentUCC.delete(batchAgentAVO);
                logger.info("删除代理成功");
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

    /**
     * @title 根据AgentId查询Agent信息
     * @description
     * 
     * @param batchAgentAVO
     * @return
     */
    @RequestMapping("/queryById")
    @ResponseBody
    public Map<String, Object> queryById(BatchAgentVO batchAgentAVO) {
        Preconditions.checkNotNull(batchAgentAVO.getAgentId());
        Map<String, Object> result = Maps.newHashMap();
        result.put("success", false);
        BatchAgentVO resultVO = batchAgentUCC.find(batchAgentAVO);
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
    public String toForm(BatchAgentVO batchAgentAVO) {
        return "batch/batchAgentForm";
    }

    /**
     * @description 首页跳转
     * @return
     */
    @RequestMapping("/index")
    public String toIndex() {
        return "batch/batchAgentIndex";
    }

}
