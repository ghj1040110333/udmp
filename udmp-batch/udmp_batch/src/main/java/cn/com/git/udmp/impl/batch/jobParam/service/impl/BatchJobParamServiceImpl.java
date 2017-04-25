package cn.com.git.udmp.impl.batch.jobParam.service.impl;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.git.udmp.component.batch.common.base.BaseBatchService;
import cn.com.git.udmp.component.batch.common.base.IBaseDao;
import cn.com.git.udmp.impl.batch.jobParam.bo.BatchJobParamBO;
import cn.com.git.udmp.impl.batch.jobParam.dao.IBatchJobParamDao;
import cn.com.git.udmp.impl.batch.jobParam.po.BatchJobParamPO;
import cn.com.git.udmp.impl.batch.jobParam.service.IBatchJobParamService;
  
/** 
 * @description BatchJobParamServiceImpl实现类
 */
@Service
 public class BatchJobParamServiceImpl extends BaseBatchService<BatchJobParamBO,BatchJobParamPO> implements IBatchJobParamService{
    @Autowired
    private IBatchJobParamDao dao;
    @Override
    public IBatchJobParamDao getDao() {
        return dao;
    }
    public void setDao(IBatchJobParamDao dao) {
        this.dao = dao;
    }
    
}
 