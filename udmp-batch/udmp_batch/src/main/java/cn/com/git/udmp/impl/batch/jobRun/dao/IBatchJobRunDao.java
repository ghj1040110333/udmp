package cn.com.git.udmp.impl.batch.jobRun.dao;

import java.util.List;
import java.util.Map;

import cn.com.git.udmp.common.persistence.annotation.MyBatisDao;
import cn.com.git.udmp.component.batch.common.base.IBaseDao;
import cn.com.git.udmp.impl.batch.jobRun.po.BatchJobRunPO;

/** 
 * @description IBatchJobRunDao接口
 * @author yangfeiit@newchinalife.com 
 * @date 2015-02-04 14:34:17  
 */
@MyBatisDao
 public interface IBatchJobRunDao extends IBaseDao<BatchJobRunPO>{
    public int addWithId(Map t);
    
    //查询任务的执行ID
    public List<String> queryJobChainRunIds(Map t);
}