package cn.com.git.ncl.aplus.batch.dao;

import java.util.List;

import cn.com.git.ncl.aplus.batch.entity.BatchTaskExecuteData;
import cn.com.git.udmp.common.persistence.CrudDao;
import cn.com.git.udmp.common.persistence.annotation.MyBatisDao;

@MyBatisDao
public interface BatchTaskExecuteMapper extends CrudDao<BatchTaskExecuteData> { 
    
    public List<BatchTaskExecuteData> findList(BatchTaskExecuteData data);
    
    /**
     * @title 按分页查询数据
     * @description
     * 
     * @param data
     * @return 
    */
    public List<BatchTaskExecuteData> query(BatchTaskExecuteData data);
    
    
}
