package cn.com.git.udmp.impl.batch.jobParam.ucc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.git.udmp.component.batch.common.base.BaseBatchUCC;
import cn.com.git.udmp.impl.batch.jobParam.bo.BatchJobParamBO;
import cn.com.git.udmp.impl.batch.jobParam.po.BatchJobParamPO;
import cn.com.git.udmp.impl.batch.jobParam.service.IBatchJobParamService;
import cn.com.git.udmp.impl.batch.jobParam.ucc.IBatchJobParamUCC;
import cn.com.git.udmp.impl.batch.jobParam.vo.BatchJobParamVO;


/**
 * @description BatchJobParamUCC实现类
 * @author yangfeiit@newchinalife.com
 * @date 2015-02-04 11:07:49
 */
@Service
public class BatchJobParamUCC extends BaseBatchUCC<BatchJobParamVO, BatchJobParamBO, BatchJobParamPO>
        implements IBatchJobParamUCC {
    @Autowired
    private IBatchJobParamService service;

    @Override
    public IBatchJobParamService getService() {
        return service;
    }

    public void setService(IBatchJobParamService service) {
        this.service = service;
    }

}

