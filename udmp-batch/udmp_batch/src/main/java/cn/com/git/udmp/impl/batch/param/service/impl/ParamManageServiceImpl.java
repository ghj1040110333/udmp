package cn.com.git.udmp.impl.batch.param.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.git.udmp.component.batch.common.base.BaseBatchService;
import cn.com.git.udmp.impl.batch.param.bo.ParamManageBO;
import cn.com.git.udmp.impl.batch.param.dao.IParamManageDao;
import cn.com.git.udmp.impl.batch.param.po.ParamManagePO;
import cn.com.git.udmp.impl.batch.param.service.IParamManageService;
/** 
 * @description ParamManageServiceImpl实现类
 */
@Service
 public class ParamManageServiceImpl extends BaseBatchService<ParamManageBO,ParamManagePO> implements IParamManageService{
    @Autowired
    private IParamManageDao dao;
    @Override
    public IParamManageDao getDao() {
        return dao;
    }
    public void setDao(IParamManageDao dao) {
        this.dao = dao;
    }

}
