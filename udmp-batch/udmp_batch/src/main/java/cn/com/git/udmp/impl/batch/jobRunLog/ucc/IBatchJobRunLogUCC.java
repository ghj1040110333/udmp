package cn.com.git.udmp.impl.batch.jobRunLog.ucc;

import java.util.List;
import java.util.Map;

import cn.com.git.udmp.component.batch.common.base.IBaseBatchUCC;
import cn.com.git.udmp.component.model.CurrentPage;
import cn.com.git.udmp.impl.batch.jobRunLog.bo.BatchJobRunLogBO;
import cn.com.git.udmp.impl.batch.jobRunLog.po.BatchJobRunLogPO;
import cn.com.git.udmp.impl.batch.jobRunLog.vo.BatchJobRunLogVO;


 /** 
 * @description IBatchJobRunLogUCC接口
 * @author yangfeiit@newchinalife.com 
 * @date 2015-02-04 15:06:12  
 */
public interface IBatchJobRunLogUCC extends IBaseBatchUCC<BatchJobRunLogVO, BatchJobRunLogBO, BatchJobRunLogPO> {}
