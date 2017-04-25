package cn.com.git.udmp.impl.batch.agent.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.git.udmp.component.batch.common.base.BaseBatchService;
import cn.com.git.udmp.impl.batch.agent.bo.BatchAgentBO;
import cn.com.git.udmp.impl.batch.agent.dao.IBatchAgentDao;
import cn.com.git.udmp.impl.batch.agent.po.BatchAgentPO;
import cn.com.git.udmp.impl.batch.agent.service.IBatchAgentService;

/**
 * @description BatchAgentServiceImpl实现类
 */
@Service
public class BatchAgentServiceImpl extends BaseBatchService<BatchAgentBO, BatchAgentPO> implements IBatchAgentService {
    @Autowired
    private IBatchAgentDao batchAgentDao;

    @Override
    public IBatchAgentDao getDao() {
        return batchAgentDao;
    }

    public void setBatchAgentDao(IBatchAgentDao batchAgentDao) {
        this.batchAgentDao = batchAgentDao;
    }


}
