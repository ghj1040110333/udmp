package cn.com.git.udmp.impl.batch.task.ucc.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.git.udmp.component.model.CurrentPage;
import cn.com.git.udmp.impl.batch.task.bo.BatchTaskBO;
import cn.com.git.udmp.impl.batch.task.bo.BatchTaskParamBO;
import cn.com.git.udmp.impl.batch.task.service.IBatchTaskParamService;
import cn.com.git.udmp.impl.batch.task.service.IBatchTaskService;
import cn.com.git.udmp.impl.batch.task.ucc.IBatchTaskUCC;
import cn.com.git.udmp.impl.batch.task.vo.BatchTaskParamVO;
import cn.com.git.udmp.impl.batch.task.vo.BatchTaskVO;
import cn.com.git.udmp.utils.bean.BeanUtils;

/**
 * 
 * @description BatchTaskUCC实现类
 * @author Spring Cao
 * @date 2016年8月30日 上午11:52:06
 */
@Service
public class BatchTaskUCC implements IBatchTaskUCC {
    /**
     * @Fields iBatchTaskService : 注入service
     */
    @Autowired
    private IBatchTaskService iBatchTaskService;
    // 作业参数Service
    @Autowired
    private IBatchTaskParamService batchTaskParamService;

    /**
     * @description 增加数据
     * @version
     * @title
     * @param batchTaskVO 对象
     * @return BatchTaskVO 添加结果
     */
    public void add(BatchTaskVO batchTaskVO, List<BatchTaskParamVO> batchTaskParamVOList) {
        // 插入作业信息
        BatchTaskBO batchTaskBO = BeanUtils.copyProperties(BatchTaskBO.class, batchTaskVO);
        iBatchTaskService.add(batchTaskBO);
        BigDecimal taskId = batchTaskBO.getTaskId();
        // 插入作业参数信息
        List<BatchTaskParamBO> batchTaskParamBOList = BeanUtils.copyList(BatchTaskParamBO.class, batchTaskParamVOList);
        batchTaskParamBOList = fillBatchTaskParamBOList(batchTaskParamBOList, taskId);
        batchTaskParamService.batchSave(batchTaskParamBOList);
    }

    /**
     * @description 填充BatchTaskParamBOList
     * @version
     * @title
     * @return List<BatchTaskParamBO>
     */
    private List<BatchTaskParamBO> fillBatchTaskParamBOList(List<BatchTaskParamBO> batchTaskParamBOList,
            BigDecimal taskId) {
        List<BatchTaskParamBO> result = batchTaskParamBOList;
        for (int i = 0; i < result.size(); i++) {
            result.get(i).setTaskId(taskId);
            result.get(i).setIsDeleted("0");
            result.get(i).setVer("1.0.0");
        }
        return result;
    }

    /**
     * @description 修改数据
     * @version
     * @title
     * @param batchTaskVO 对象
     * @return BatchTaskVO 修改结果
     */
    public BatchTaskVO update(BatchTaskVO batchTaskVO, List<BatchTaskParamVO> batchTaskParamVOList) {
        logger.debug("<======BatchTaskUCC--updateBatchTask======>");
        // 参数执行全删全增操作，先删除后增加
        // 通过taskId，删除该taskId对应的全部作业参数
        List<BatchTaskParamVO> batchTaskParamVOListOrgin = new ArrayList<BatchTaskParamVO>();
        BatchTaskParamVO batchTaskParamVoOrgin = new BatchTaskParamVO();
        batchTaskParamVoOrgin.setTaskId(batchTaskVO.getTaskId());
        batchTaskParamVOListOrgin.add(batchTaskParamVoOrgin);
        batchTaskParamService.batchDelete(BeanUtils.copyList(BatchTaskParamBO.class, batchTaskParamVOListOrgin));
        // 批量新增作业参数
        if (batchTaskParamVOList != null&&batchTaskParamVOList.size()>0) {
            List<BatchTaskParamBO> batchTaskParamBOList = BeanUtils.copyList(BatchTaskParamBO.class,
                    batchTaskParamVOList);
            batchTaskParamBOList = fillBatchTaskParamBOList(batchTaskParamBOList, batchTaskVO.getTaskId());
            batchTaskParamService.batchSave(batchTaskParamBOList);
        }
        // 修改作业基本信息
        BatchTaskBO batchTaskBO = iBatchTaskService.update(BeanUtils.copyProperties(BatchTaskBO.class, batchTaskVO));
        return BeanUtils.copyProperties(BatchTaskVO.class, batchTaskBO);
    }

    /**
     * @description 查询单条数据
     * @version
     * @title
     * @param batchTaskVO 对象
     * @return BatchTaskVO 查询结果对象
     */
    public BatchTaskVO find(BatchTaskVO batchTaskVO) {
        logger.debug("<======BatchTaskUCC--findBatchTask======>");
        BatchTaskBO batchTaskBackBO = iBatchTaskService.find(BeanUtils.copyProperties(BatchTaskBO.class, batchTaskVO));
        // 根据taskId,获取作业参数的全部记录
        BatchTaskParamBO batchTaskParamBO = new BatchTaskParamBO();
        batchTaskParamBO.setTaskId(batchTaskVO.getTaskId());
        batchTaskParamBO.setIsDeleted("0");
        List<BatchTaskParamBO> batchTaskParamBOList = batchTaskParamService.findAll(batchTaskParamBO);
        // 根据taskId，获取作业基本信息
        BatchTaskVO batchTaskBackVO = BeanUtils.copyProperties(BatchTaskVO.class, batchTaskBackBO);
        List<BatchTaskParamVO> batchTaskParamVOList = BeanUtils.copyList(BatchTaskParamVO.class, batchTaskParamBOList);
        // 将作业参数信息列表set到作业基本信息VO中
        batchTaskBackVO.setBatchTaskParamVOList(batchTaskParamVOList);
        return batchTaskBackVO;
    }

    /**
     * @description 查询所有数据
     * @version
     * @title
     * @param batchTaskVO 对象
     * @return List<BatchTaskVO> 查询结果List
     */
    public List<BatchTaskVO> findAll(BatchTaskVO batchTaskVO) {
        logger.debug("<======BatchTaskUCC--findAllBatchTask======>");
        BatchTaskBO batchTaskBO = BeanUtils.copyProperties(BatchTaskBO.class, batchTaskVO);
        return BeanUtils.copyList(BatchTaskVO.class, iBatchTaskService.findAll(batchTaskBO));
    }

    /**
     * @description 查询数据条数
     * @version
     * @title
     * @param batchTaskVO 对象
     * @return int 查询结果条数
     */
    public int findTotal(BatchTaskVO batchTaskVO) {
        logger.debug("<======BatchTaskUCC--findTotal======>");
        BatchTaskBO batchTaskBO = BeanUtils.copyProperties(BatchTaskBO.class, batchTaskVO);
        return iBatchTaskService.findTotal(batchTaskBO);
    }

    /**
     * @description 分页查询数据
     * @version
     * @title
     * @param batchTaskVO 对象
     * @param currentPage 当前页对象
     * @return CurrentPage<BatchTaskVO> 查询结果的当前页对象
     */
    public CurrentPage<BatchTaskVO> queryForPage(BatchTaskVO batchTaskVO, CurrentPage<BatchTaskVO> currentPage) {
        logger.debug("<======BatchTaskUCC--queryForPage======>");
        BatchTaskBO batchTaskBO = BeanUtils.copyProperties(BatchTaskBO.class, batchTaskVO);
        return BeanUtils.copyCurrentPage(BatchTaskVO.class,
                iBatchTaskService.queryForPage(batchTaskBO, BeanUtils.copyCurrentPage(BatchTaskBO.class, currentPage)));
    }

    /**
     * @description 批量增加数据
     * @version
     * @title
     * @param batchTaskVOList 对象列表
     * @return boolean 批量添加是否成功
     */
    public boolean batchSave(List<BatchTaskVO> batchTaskVOList) {
        logger.debug("<======BatchTaskUCC--batchSaveBatchTask======>");
        boolean result = false;

        result = iBatchTaskService.batchSave(BeanUtils.copyList(BatchTaskBO.class, batchTaskVOList));

        return result;
    }

    /**
     * @description 批量修改数据
     * @version
     * @title
     * @param batchTaskVOList 对象列表
     * @return boolean 批量修改是否成功
     */
    public boolean batchUpdate(List<BatchTaskVO> batchTaskVOList) {
        logger.debug("<======BatchTaskUCC--batchUpdateBatchTask======>");
        boolean result = false;

        result = iBatchTaskService.batchUpdate(BeanUtils.copyList(BatchTaskBO.class, batchTaskVOList));
        return result;
    }

    /**
     * @description 批量删除数据
     * @version
     * @title
     * @param batchTaskVOList 对象列表
     * @return boolean 批量删除是否成功
     */
    public boolean batchDelete(List<BatchTaskVO> batchTaskVOList, List<BatchTaskParamVO> batchTaskParamVOList) {
        logger.debug("<======BatchTaskUCC--batchDeleteBatchTask======>");
        // 删除作业参数表数据
        batchTaskParamService.batchDelete(BeanUtils.copyList(BatchTaskParamBO.class, batchTaskParamVOList));
        // 删除作业表数据
        iBatchTaskService.batchDelete(BeanUtils.copyList(BatchTaskBO.class, batchTaskVOList));
        return true;
    }

    /**
     * @description 查询所有数据 ，重新组装为map
     * @version
     * @title
     * @param batchTaskVO 对象
     * @return List<Map<String, Object>> 查询结果存放到map中
     */
    public List<Map<String, Object>> findAllMap(BatchTaskVO batchTaskVO) {
        logger.debug("<======BatchTaskUCC--findAllMapBatchTask======>");
        BatchTaskBO batchTaskBO = BeanUtils.copyProperties(BatchTaskBO.class, batchTaskVO);
        return iBatchTaskService.findAllMap(batchTaskBO);
    }

    private BigDecimal[] getTaskIdArray(List<BatchTaskVO> batchTaskVOList) {
        BigDecimal[] result = new BigDecimal[batchTaskVOList.size()];
        for (int i = 0; i < batchTaskVOList.size(); i++) {
            result[i] = batchTaskVOList.get(i).getTaskId();
        }
        return result;
    }

    public void setiBatchTaskService(IBatchTaskService iBatchTaskService) {
        this.iBatchTaskService = iBatchTaskService;
    }

    public void setBatchTaskParamService(IBatchTaskParamService batchTaskParamService) {
        this.batchTaskParamService = batchTaskParamService;
    }

    @Override
    public void delete(BatchTaskVO batchTaskVO) {
        BatchTaskBO batchTaskBO = BeanUtils.copyProperties(BatchTaskBO.class, batchTaskVO);
        iBatchTaskService.delete(batchTaskBO);
    }
}
