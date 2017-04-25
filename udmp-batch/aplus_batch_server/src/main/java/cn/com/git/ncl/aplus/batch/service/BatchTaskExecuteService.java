package cn.com.git.ncl.aplus.batch.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.git.ncl.aplus.batch.dao.BatchTaskExecuteMapper;
import cn.com.git.ncl.aplus.batch.entity.BatchTaskExecuteData;
import cn.com.git.udmp.common.persistence.Page;
import cn.com.git.udmp.common.service.CrudService;

@Service
@Transactional(readOnly = true)
public class BatchTaskExecuteService  extends CrudService<BatchTaskExecuteMapper, BatchTaskExecuteData> {

    
    @Override
    public Page<BatchTaskExecuteData> findPage(Page<BatchTaskExecuteData> page, BatchTaskExecuteData batchTaskExecuteData) {
        
        return super.findPage(page, batchTaskExecuteData);
    }

}
