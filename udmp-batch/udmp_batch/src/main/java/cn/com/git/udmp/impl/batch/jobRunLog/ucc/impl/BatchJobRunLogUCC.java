package cn.com.git.udmp.impl.batch.jobRunLog.ucc.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.git.udmp.component.batch.common.base.BaseBatchUCC;
import cn.com.git.udmp.impl.batch.jobRunLog.bo.BatchJobRunLogBO;
import cn.com.git.udmp.impl.batch.jobRunLog.po.BatchJobRunLogPO;
import cn.com.git.udmp.impl.batch.jobRunLog.service.IBatchJobRunLogService;
import cn.com.git.udmp.impl.batch.jobRunLog.ucc.IBatchJobRunLogUCC;
import cn.com.git.udmp.impl.batch.jobRunLog.vo.BatchJobRunLogVO;


/**
 * @description BatchJobRunLogUCC实现类
 * @author yangfeiit@newchinalife.com
 * @date 2015-02-04 11:07:49
 */
@Service
public class BatchJobRunLogUCC extends BaseBatchUCC<BatchJobRunLogVO, BatchJobRunLogBO, BatchJobRunLogPO>
        implements IBatchJobRunLogUCC {
    @Autowired
    private IBatchJobRunLogService service;

    @Override
    public IBatchJobRunLogService getService() {
        return service;
    }

    public void setService(IBatchJobRunLogService service) {
        this.service = service;
    }

}
