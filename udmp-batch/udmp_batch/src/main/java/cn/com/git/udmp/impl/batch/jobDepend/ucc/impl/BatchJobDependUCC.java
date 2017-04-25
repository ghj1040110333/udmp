package cn.com.git.udmp.impl.batch.jobDepend.ucc.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.git.udmp.component.batch.common.base.BaseBatchUCC;
import cn.com.git.udmp.impl.batch.jobDepend.bo.BatchJobDependBO;
import cn.com.git.udmp.impl.batch.jobDepend.po.BatchJobDependPO;
import cn.com.git.udmp.impl.batch.jobDepend.service.IBatchJobDependService;
import cn.com.git.udmp.impl.batch.jobDepend.ucc.IBatchJobDependUCC;
import cn.com.git.udmp.impl.batch.jobDepend.vo.BatchJobDependVO;
import cn.com.git.udmp.utils.bean.BeanUtils;

/**
 * 
 * @description BatchJobDependUCC实现类
 * @author Spring Cao
 * @date 2016年8月30日 上午11:44:59
 */
@Service
public class BatchJobDependUCC extends BaseBatchUCC<BatchJobDependVO, BatchJobDependBO, BatchJobDependPO>
        implements IBatchJobDependUCC {
    @Autowired
    private IBatchJobDependService service;

    @SuppressWarnings("unchecked")
	@Override
    public IBatchJobDependService getService() {
        return service;
    }

    public void setService(IBatchJobDependService service) {
        this.service = service;
    }

    /**
     * @description 查询前置依赖
     * @see cn.com.git.udmp.impl.batch.jobDepend.ucc.IBatchJobDependUCC#findAllBatchJobDependPreCond(cn.com.git.udmp.impl.batch.jobDepend.vo.BatchJobDependVO)
     * @param batchAgentVO
     * @return 
    */
    @Override
    public List<BatchJobDependVO> findAllBatchJobDependPreCond(BatchJobDependVO batchAgentVO) {
		logger.debug("<======BatchJobDependUCC--findAllBatchJobDependPreCond======>");
		List<BatchJobDependBO> batchJobDependBOList = service.findAll(BeanUtils.copyProperties(BatchJobDependBO.class, batchAgentVO));
		List<BatchJobDependVO> batchJobDependVOList = BeanUtils.copyList(BatchJobDependVO.class, batchJobDependBOList);
		return batchJobDependVOList;
    }

    /**
     * @description 查询后置依赖
     * @see cn.com.git.udmp.impl.batch.jobDepend.ucc.IBatchJobDependUCC#findAllBatchJobDepend(cn.com.git.udmp.impl.batch.jobDepend.vo.BatchJobDependVO)
     * @param batchAgentVO
     * @return 
    */
    @Override
    public List<BatchJobDependVO> findAllBatchJobDepend(BatchJobDependVO batchAgentVO) {
		logger.debug("<======BatchJobDependUCC--findAllBatchJobDepend======>");
		List<BatchJobDependBO> batchJobDependBOList = service.findAll(BeanUtils.copyProperties(BatchJobDependBO.class, batchAgentVO));
		List<BatchJobDependVO> batchJobDependVOList = BeanUtils.copyList(BatchJobDependVO.class, batchJobDependBOList);
		return batchJobDependVOList;
    }
}
