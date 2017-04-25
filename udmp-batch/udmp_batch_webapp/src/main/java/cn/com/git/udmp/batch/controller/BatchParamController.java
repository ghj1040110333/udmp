package cn.com.git.udmp.batch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.git.udmp.batch.controller.base.BaseBatchController;

/** 
 * @description 
 * @author Liang liuliang1@git.com.cn 
 * @date 2017年3月28日 下午5:43:27  
*/
@Controller
@RequestMapping("${adminPath}/batch/param")
public class BatchParamController extends BaseBatchController {
//
//    @Autowired
//    private IBatchParamUCC batchParamUCC;
//
//    /**
//     * @title
//     * @description
//     * @testUrl "http://localhost:8181/udmp_batch_webapp/a/batch/Param/list?pageNo=1&&pageSize=10"
//     * @param pageNo
//     * @param pageSize
//     * @param ParamVO
//     * @return
//     */
//    @RequestMapping("/list")
//    @ResponseBody
//    public Table list(int pageNo, int pageSize, BatchParamVO ParamVO) {
//        CurrentPage<BatchParamVO> currentPage = new CurrentPage<>();
//        currentPage.setPageNo(pageNo);
//        currentPage.setPageSize(pageSize);
//        CurrentPage<BatchParamVO> result = batchParamUCC.queryForPage(ParamVO, currentPage);
//        Page<BatchParamVO> page = currentPageToPage(pageNo, pageSize, result);
//        return new Table(page);
//    }
//
//    @RequestMapping("/save")
//    @ResponseBody
//    public HashMap<Object, Object> save(BatchParamVO batchParamAVO) {
//        HashMap<Object, Object> result = Maps.newHashMap();
//        batchParamAVO.setIsDeleted("0");
//        try {
//            if (batchParamAVO.getParamId() != null) {
//                batchParamUCC.update(batchParamAVO);
//                logger.info("更新代理成功");
//            } else {
//                batchParamUCC.add(batchParamAVO);
//                logger.info("新增代理成功");
//            }
//            result.put("success", true);
//        } catch (FrameworkException e) {
//            // outAjax(Constants.DWZ_STATUSCODE_300, "新增代理失败", "", "", "");
//            logger.error("操作失败", e);
//            result.put("success", false);
//            result.put("message", e.getMessage());
//        }
//        return result;
//    }
//    
//    @RequestMapping("/delete")
//    @ResponseBody
//    public HashMap<Object, Object> delete(BatchParamVO batchParamAVO) {
//        HashMap<Object, Object> result = Maps.newHashMap();
//        Preconditions.checkNotNull(batchParamAVO.getParamId());
//        try {
//            if (batchParamAVO.getParamId() != null) {
//                batchParamUCC.delete(batchParamAVO);
//                logger.info("删除代理成功");
//            } 
//            result.put("success", true);
//        } catch (FrameworkException e) {
//            logger.error("操作失败", e);
//            result.put("success", false);
//            result.put("message", e.getMessage());
//        }
//        return result;
//    }
//
//    /**
//     * @title 根据AgentId查询Agent信息
//     * @description
//     * 
//     * @param batchParamAVO
//     * @return
//     */
//    @RequestMapping("/queryById")
//    @ResponseBody
//    public Map<String, Object> queryById(BatchParamVO batchParamAVO) {
//        Preconditions.checkNotNull(batchParamAVO.getParamId());
//        Map<String, Object> result = Maps.newHashMap();
//        result.put("success", false);
//        BatchParamVO resultVO = batchParamUCC.find(batchParamAVO);
//        if (resultVO != null) {
//            result.put("success", true);
//            result.put("data", resultVO);
//        }
//        return result;
//    }
//
//    /**
//     * @description 首页跳转
//     * @return
//     */
//    @RequestMapping("/form")
//    public String toForm(BatchParamVO batchParamAVO) {
//        return "batch/batchParamForm";
//    }
//
//    /**
//     * @description 首页跳转
//     * @return
//     */
//    @RequestMapping("/index")
//    public String toIndex() {
//        return "batch/batchParamIndex";
//    }

}

