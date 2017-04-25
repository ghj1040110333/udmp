package cn.com.git.udmp.impl.batch.param.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.git.udmp.component.batch.common.base.BaseBatchService;
import cn.com.git.udmp.impl.batch.param.bo.SubParamBO;
import cn.com.git.udmp.impl.batch.param.dao.ISubParamDao;
import cn.com.git.udmp.impl.batch.param.po.SubParamPO;
import cn.com.git.udmp.impl.batch.param.service.ISubParamService;
/** 
 * @description SubParamServiceImpl实现类
 */
@Service
 public class SubParamServiceImpl extends BaseBatchService<SubParamBO,SubParamPO> implements ISubParamService{
    @Autowired
    private ISubParamDao dao;
    @Override
    public ISubParamDao getDao() {
        return dao;
    }
    public void setDao(ISubParamDao dao) {
        this.dao = dao;
    }
    
}
