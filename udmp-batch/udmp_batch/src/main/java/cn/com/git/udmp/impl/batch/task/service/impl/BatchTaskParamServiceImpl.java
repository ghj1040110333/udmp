package cn.com.git.udmp.impl.batch.task.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.git.udmp.component.batch.common.base.BaseBatchService;
import cn.com.git.udmp.impl.batch.task.bo.BatchTaskParamBO;
import cn.com.git.udmp.impl.batch.task.dao.IBatchTaskParamDAO;
import cn.com.git.udmp.impl.batch.task.po.BatchTaskParamPO;
import cn.com.git.udmp.impl.batch.task.service.IBatchTaskParamService;

/**
 * @description BatchTaskParamServiceImpl实现类
 * @author anthorName@newchinalife.com
 * @date 2015-02-10 10:52:51
 */
/**
 * @description BatchTaskParamServiceImpl实现类
 */
@Service
public class BatchTaskParamServiceImpl extends BaseBatchService<BatchTaskParamBO, BatchTaskParamPO> implements IBatchTaskParamService{
    @Autowired
    private IBatchTaskParamDAO dao;

    @Override
    public IBatchTaskParamDAO getDao() {
        return dao;
    }

    public void setDao(IBatchTaskParamDAO dao) {
        this.dao = dao;
    }

}
