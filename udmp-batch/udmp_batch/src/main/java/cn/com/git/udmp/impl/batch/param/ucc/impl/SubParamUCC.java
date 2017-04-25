package cn.com.git.udmp.impl.batch.param.ucc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.git.udmp.component.batch.common.base.BaseBatchUCC;
import cn.com.git.udmp.impl.batch.param.bo.SubParamBO;
import cn.com.git.udmp.impl.batch.param.po.SubParamPO;
import cn.com.git.udmp.impl.batch.param.service.ISubParamService;
import cn.com.git.udmp.impl.batch.param.ucc.ISubParamUCC;
import cn.com.git.udmp.impl.batch.param.vo.SubParamVO;

/**
 * @description SubParamUCC实现类
 * @author yangfeiit@newchinalife.com
 * @date 2015-02-04 11:07:49
 */
@Service
public class SubParamUCC extends BaseBatchUCC<SubParamVO, SubParamBO, SubParamPO>implements ISubParamUCC {
    @Autowired
    private ISubParamService service;

    @Override
    public ISubParamService getService() {
        return service;
    }

    public void setService(ISubParamService service) {
        this.service = service;
    }

}
