package cn.com.git.udmp.impl.batch.agent.ucc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.git.udmp.component.batch.common.base.BaseBatchUCC;
import cn.com.git.udmp.component.batch.common.base.IBaseBatchService;
import cn.com.git.udmp.impl.batch.agent.bo.BatchAgentBO;
import cn.com.git.udmp.impl.batch.agent.po.BatchAgentPO;
import cn.com.git.udmp.impl.batch.agent.service.IBatchAgentService;
import cn.com.git.udmp.impl.batch.agent.ucc.IBatchAgentUCC;
import cn.com.git.udmp.impl.batch.agent.vo.BatchAgentVO;

/**
 * @description BatchAgentUCC实现类
 * @author yangfeiit@newchinalife.com
 * @date 2015-02-02 17:07:21
 */
@Service
public class BatchAgentUCC extends BaseBatchUCC<BatchAgentVO, BatchAgentBO, BatchAgentPO> implements IBatchAgentUCC {
    /**
     * @Fields batchAgentService : 注入service
     */
    @Autowired
    private IBatchAgentService batchAgentService;

    @Override
    public IBatchAgentService getService() {
        return batchAgentService;
    }

    public void setBatchAgentService(IBatchAgentService batchAgentService) {
        this.batchAgentService = batchAgentService;
    }
   


}
