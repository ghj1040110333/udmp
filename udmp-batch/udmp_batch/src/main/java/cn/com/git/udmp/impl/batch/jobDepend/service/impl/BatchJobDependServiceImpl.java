package cn.com.git.udmp.impl.batch.jobDepend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.git.udmp.component.batch.common.base.BaseBatchService;
import cn.com.git.udmp.impl.batch.jobDepend.bo.BatchJobDependBO;
import cn.com.git.udmp.impl.batch.jobDepend.dao.IBatchJobDependDao;
import cn.com.git.udmp.impl.batch.jobDepend.po.BatchJobDependPO;
import cn.com.git.udmp.impl.batch.jobDepend.service.IBatchJobDependService;
 
 
/** 
 * @description BatchJobDependServiceImpl实现类
 * @author yangfeiit@newchinalife.com 
 * @date 2015-02-04 11:31:33  
 */
@Service
 public class BatchJobDependServiceImpl extends BaseBatchService<BatchJobDependBO,BatchJobDependPO> implements IBatchJobDependService{
    @Autowired
    private IBatchJobDependDao dao;
    @Override
    public IBatchJobDependDao getDao() {
        return dao;
    }
    public void setDao(IBatchJobDependDao dao) {
        this.dao = dao;
    }
    @Override
    public List<BatchJobDependBO> findAllBatchJobDependPreCond(BatchJobDependBO batchJobDependBO) {
        batchJobDependBO.setDependType("B");
        batchJobDependBO.setIsDeleted("0");
        return this.findAll(batchJobDependBO);
    }
    @Override
    public List<BatchJobDependBO> findAllBatchJobDependPostCond(BatchJobDependBO batchJobDependBO) {
        batchJobDependBO.setDependType("A");
        batchJobDependBO.setIsDeleted("0");
        return this.findAll(batchJobDependBO);
    }
    
    
    
}