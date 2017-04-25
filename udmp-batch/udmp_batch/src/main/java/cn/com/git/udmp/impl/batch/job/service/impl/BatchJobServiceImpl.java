package cn.com.git.udmp.impl.batch.job.service.impl;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.git.udmp.component.batch.common.base.BaseBatchService;
import cn.com.git.udmp.impl.batch.job.bo.BatchJobBO;
import cn.com.git.udmp.impl.batch.job.dao.IBatchJobDao;
import cn.com.git.udmp.impl.batch.job.po.BatchJobPO;
import cn.com.git.udmp.impl.batch.job.service.IBatchJobService;
 
 
/** 
 * @description BatchJobServiceImpl实现类
 * @author yangfeiit@newchinalife.com 
 * @date 2015-02-04 09:54:54  
 */
@Service
public class BatchJobServiceImpl extends BaseBatchService<BatchJobBO, BatchJobPO> implements IBatchJobService{
        
        @Autowired
        private IBatchJobDao dao;
    
        @Override
        public IBatchJobDao getDao() {
            return dao;
        }

        public void setDao(IBatchJobDao dao) {
            this.dao = dao;
        }

}