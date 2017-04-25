package cn.com.git.udmp.impl.batch.task.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.git.udmp.component.batch.common.base.BaseBatchService;
import cn.com.git.udmp.component.batch.common.base.IBaseDao;
import cn.com.git.udmp.impl.batch.task.bo.BatchTaskBO;
import cn.com.git.udmp.impl.batch.task.dao.IBatchTaskDAO;
import cn.com.git.udmp.impl.batch.task.po.BatchTaskPO;
import cn.com.git.udmp.impl.batch.task.service.IBatchTaskService;

/**
 * @description BatchTaskServiceImpl实现类
 * @author anthorName@newchinalife.com
 * @date 2015-02-10 10:52:13
 */
/** 
 * @description BatchTaskServiceImpl实现类
 */
@Service
 public class BatchTaskServiceImpl extends BaseBatchService<BatchTaskBO,BatchTaskPO> implements IBatchTaskService{
    @Autowired
    private IBatchTaskDAO dao;
    @Override
    public IBatchTaskDAO getDao() {
        return dao;
    }
    public void setDao(IBatchTaskDAO dao) {
        this.dao = dao;
    }
    
}
