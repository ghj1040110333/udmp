package cn.com.git.udmp.impl.batch.jobRunLog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.git.udmp.component.batch.common.base.BaseBatchService;
import cn.com.git.udmp.impl.batch.jobRunLog.bo.BatchJobRunLogBO;
import cn.com.git.udmp.impl.batch.jobRunLog.dao.IBatchJobRunLogDao;
import cn.com.git.udmp.impl.batch.jobRunLog.po.BatchJobRunLogPO;
import cn.com.git.udmp.impl.batch.jobRunLog.service.IBatchJobRunLogService;
 
/** 
 * @description BatchJobRunLogServiceImpl实现类
 */
@Service
 public class BatchJobRunLogServiceImpl extends BaseBatchService<BatchJobRunLogBO,BatchJobRunLogPO> implements IBatchJobRunLogService{
    @Autowired
    private IBatchJobRunLogDao dao;
    @Override
    public IBatchJobRunLogDao getDao() {
        return dao;
    }
    public void setDao(IBatchJobRunLogDao dao) {
        this.dao = dao;
    }
    
}
 