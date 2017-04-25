package cn.com.git.udmp.impl.batch.job.ucc;

import java.util.List;

import cn.com.git.udmp.component.batch.common.base.IBaseBatchUCC;
import cn.com.git.udmp.impl.batch.job.bo.BatchJobBO;
import cn.com.git.udmp.impl.batch.job.po.BatchJobPO;
import cn.com.git.udmp.impl.batch.job.vo.BatchJobVO;


 /** 
 * @description IBatchJobUCC接口
 * @author yangfeiit@newchinalife.com 
 * @date 2015-02-04 09:58:12  
 */
public interface IBatchJobUCC extends IBaseBatchUCC<BatchJobVO, BatchJobBO, BatchJobPO>{
    
    List<BatchJobVO> findExcuteJobAll(BatchJobVO batchJobVO);

    void addWhoelJob(BatchJobVO batchJobVO);
    
}
