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
@RequestMapping("${adminPath}/batch/serverPort")
public class BatchServerPortController extends BaseBatchController {

//    @Autowired
//    private IBatchServerPortUCC batchServerPortUCC;
//
//    /**
//     * @title
//     * @description
//     * @testUrl "http://localhost:8181/udmp_batch_webapp/a/batch/ServerPort/list?pageNo=1&&pageSize=10"
//     * @param pageNo
//     * @param pageSize
//     * @param ServerPortVO
//     * @return
//     */
//    @RequestMapping("/list")
//    @ResponseBody
//    public Table list(int pageNo, int pageSize, BatchServerPortVO ServerPortVO) {
//        CurrentPage<BatchServerPortVO> currentPage = new CurrentPage<>();
//        currentPage.setPageNo(pageNo);
//        currentPage.setPageSize(pageSize);
//        CurrentPage<BatchServerPortVO> result = batchServerPortUCC.queryForPage(ServerPortVO, currentPage);
//        Page<BatchServerPortVO> page = currentPageToPage(pageNo, pageSize, result);
//        return new Table(page);
//    }
//
//    @RequestMapping("/save")
//    @ResponseBody
//    public HashMap<Object, Object> save(BatchServerPortVO batchServerPortAVO) {
//        HashMap<Object, Object> result = Maps.newHashMap();
//        batchServerPortAVO.setIsDeleted("0");
//        try {
//            if (batchServerPortAVO.getServerPortId() != null) {
//                batchServerPortUCC.update(batchServerPortAVO);
//                logger.info("更新代理成功");
//            } else {
//                batchServerPortUCC.add(batchServerPortAVO);
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
//    public HashMap<Object, Object> delete(BatchServerPortVO batchServerPortAVO) {
//        HashMap<Object, Object> result = Maps.newHashMap();
//        Preconditions.checkNotNull(batchServerPortAVO.getServerPortId());
//        try {
//            if (batchServerPortAVO.getServerPortId() != null) {
//                batchServerPortUCC.delete(batchServerPortAVO);
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
//     * @param batchServerPortAVO
//     * @return
//     */
//    @RequestMapping("/queryById")
//    @ResponseBody
//    public Map<String, Object> queryById(BatchServerPortVO batchServerPortAVO) {
//        Preconditions.checkNotNull(batchServerPortAVO.getServerPortId());
//        Map<String, Object> result = Maps.newHashMap();
//        result.put("success", false);
//        BatchServerPortVO resultVO = batchServerPortUCC.find(batchServerPortAVO);
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
//    public String toForm(BatchServerPortVO batchServerPortAVO) {
//        return "batch/batchServerPortForm";
//    }
//
//    /**
//     * @description 首页跳转
//     * @return
//     */
//    @RequestMapping("/index")
//    public String toIndex() {
//        return "batch/batchServerPortIndex";
//    }

}

