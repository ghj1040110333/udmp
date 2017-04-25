package cn.com.git.udmp.impl.batch.param.ucc.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.git.udmp.component.batch.common.base.BaseBatchUCC;
import cn.com.git.udmp.impl.batch.param.bo.ParamManageBO;
import cn.com.git.udmp.impl.batch.param.bo.SubParamBO;
import cn.com.git.udmp.impl.batch.param.po.ParamManagePO;
import cn.com.git.udmp.impl.batch.param.service.IParamManageService;
import cn.com.git.udmp.impl.batch.param.service.ISubParamService;
import cn.com.git.udmp.impl.batch.param.ucc.IParamManageUCC;
import cn.com.git.udmp.impl.batch.param.vo.ParamManageVO;
import cn.com.git.udmp.impl.batch.param.vo.SubParamVO;
import cn.com.git.udmp.utils.bean.BeanUtils;
/**
 * @description ParamManageUCC实现类
 * @author yangfeiit@newchinalife.com
 * @date 2015-02-04 11:07:49
 */
@Service
public class ParamManageUCC extends BaseBatchUCC<ParamManageVO, ParamManageBO, ParamManagePO>
        implements IParamManageUCC {
    @Autowired
    private IParamManageService service;
    @Autowired
    private IParamManageService paramService;
    @Autowired
    private ISubParamService subParamService;

    @Override
    public IParamManageService getService() {
        return service;
    }

    public void setService(IParamManageService service) {
        this.service = service;
    }
    
    @Override
    public ParamManageVO find(ParamManageVO paramManageVo) {
        logger.debug("<======paramManageUCC--findParam======>");
        ParamManageBO paramManageBo = BeanUtils.copyProperties(ParamManageBO.class, paramManageVo);
        ParamManageBO paramBackBo = paramService.find(paramManageBo);
        SubParamBO subParamBo = new SubParamBO();
        subParamBo.setParamId(paramBackBo.getParamId());
        List<SubParamBO> subParamList = subParamService.findAll(subParamBo);        
        ParamManageVO paramBackVo = BeanUtils.copyProperties(ParamManageVO.class, paramBackBo);
        paramBackVo.setSubParamList(BeanUtils.copyList(SubParamVO.class, subParamList));
        return paramBackVo;
        
    }

}

