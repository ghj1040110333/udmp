package cn.com.git.udmp.impl.batch.jobDepend.ucc;

import java.util.List;

import cn.com.git.udmp.component.batch.common.base.IBaseBatchUCC;
import cn.com.git.udmp.impl.batch.jobDepend.bo.BatchJobDependBO;
import cn.com.git.udmp.impl.batch.jobDepend.po.BatchJobDependPO;
import cn.com.git.udmp.impl.batch.jobDepend.vo.BatchJobDependVO;

/**
 * @description IBatchJobDependUCC接口
 * @author yangfeiit@newchinalife.com
 * @date 2015-02-04 11:07:43
 */
public interface IBatchJobDependUCC extends IBaseBatchUCC<BatchJobDependVO, BatchJobDependBO, BatchJobDependPO>{
   public List<BatchJobDependVO> findAllBatchJobDependPreCond(BatchJobDependVO batchAgentVO);
   public List<BatchJobDependVO> findAllBatchJobDepend(BatchJobDependVO batchAgentVO);
}
