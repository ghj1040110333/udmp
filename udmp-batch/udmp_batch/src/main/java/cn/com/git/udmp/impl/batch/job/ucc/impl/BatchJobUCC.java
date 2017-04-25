package cn.com.git.udmp.impl.batch.job.ucc.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.git.udmp.component.batch.common.base.BaseBatchUCC;
import cn.com.git.udmp.impl.batch.job.bo.BatchJobBO;
import cn.com.git.udmp.impl.batch.job.po.BatchJobPO;
import cn.com.git.udmp.impl.batch.job.service.IBatchJobService;
import cn.com.git.udmp.impl.batch.job.ucc.IBatchJobUCC;
import cn.com.git.udmp.impl.batch.job.vo.BatchJobVO;
import cn.com.git.udmp.impl.batch.jobDepend.bo.BatchJobDependBO;
import cn.com.git.udmp.impl.batch.jobDepend.service.IBatchJobDependService;
import cn.com.git.udmp.impl.batch.jobDepend.vo.BatchJobDependVO;
import cn.com.git.udmp.impl.batch.jobParam.bo.BatchJobParamBO;
import cn.com.git.udmp.impl.batch.jobParam.service.IBatchJobParamService;
import cn.com.git.udmp.impl.batch.jobParam.vo.BatchJobParamVO;
import cn.com.git.udmp.impl.batch.param.bo.ParamManageBO;
import cn.com.git.udmp.impl.batch.param.bo.SubParamBO;
import cn.com.git.udmp.impl.batch.param.service.IParamManageService;
import cn.com.git.udmp.impl.batch.param.service.ISubParamService;
import cn.com.git.udmp.impl.batch.param.vo.ParamManageVO;
import cn.com.git.udmp.impl.batch.param.vo.SubParamVO;
import cn.com.git.udmp.impl.batch.task.bo.BatchTaskParamBO;
import cn.com.git.udmp.impl.batch.task.service.IBatchTaskParamService;
import cn.com.git.udmp.utils.bean.BeanUtils;

/**
 * @description BatchJobUCC实现类
 */
@Service
public class BatchJobUCC extends BaseBatchUCC<BatchJobVO, BatchJobBO, BatchJobPO> implements IBatchJobUCC{

    @Autowired
    private IBatchJobService service;
    @Autowired
    private IBatchJobDependService batchJobDependService;
    @Autowired
    private IBatchJobParamService batchJobParamService;
    @Autowired
    private IBatchTaskParamService batchTaskParamService;
    @Autowired
    private IParamManageService paramService;
    @Autowired
    private ISubParamService subParamService;
    
    
    @Override
    public IBatchJobService getService() {
        return service;
    }
    public void setService(IBatchJobService service) {
        this.service = service;
    }
    
    
    @Override
    public BatchJobVO find(BatchJobVO batchVO) {
        logger.debug("<======BatchJobUCC--findBatchJob======>");
        BatchJobBO batchJobBackBO = service.find(BeanUtils.copyProperties(BatchJobBO.class, batchVO));
        BatchJobDependBO batchJobDependBO = new BatchJobDependBO();
        batchJobDependBO.setActionJobId(batchJobBackBO.getJobId());
        // 组装任务参数BO
        BatchJobParamBO batchJobParamBO = new BatchJobParamBO();
        batchJobParamBO.setJobId(batchJobBackBO.getJobId());
        // TODO 是：1；否：0。后续待统一
        batchJobParamBO.setIsDeleted("0");
        // 查询前置依赖
        List<BatchJobDependBO> batchJobPreDependBOList = batchJobDependService
                .findAllBatchJobDependPreCond(batchJobDependBO);

        // 查询后置依赖
        List<BatchJobDependBO> batchJobPostDependBOList = batchJobDependService
                .findAllBatchJobDependPostCond(batchJobDependBO);

        // 查询任务参数信息
        // 1.查询任务参数信息，获取任务参数值
        List<BatchJobParamBO> batchJobParamList1 = batchJobParamService.findAll(batchJobParamBO);
        List<BatchJobParamVO> batchJobParamList2 = BeanUtils.copyList(BatchJobParamVO.class, batchJobParamList1);
        List<BatchJobParamVO> batchJobParamList3 = new ArrayList<BatchJobParamVO>();

        // 2.查询任务对应的作业参数信息，获取参数定义信息，如是否必选，是否数组等
        BatchTaskParamBO batchTaskParamBO = new BatchTaskParamBO();
        batchTaskParamBO.setTaskId(batchJobBackBO.getTaskId());
        // TODO 是：1；否：0。后续待统一
        batchTaskParamBO.setIsDeleted("0");
        List<BatchTaskParamBO> batchTaskParamBOList = batchTaskParamService.findAll(batchTaskParamBO);

        // 3.将查询出的任务参数值列表与任务参数定义信息列表合并
        ParamManageVO paramManageVo;
        if (batchJobParamList2 != null && batchJobParamList2.size() > 0) {
            for (BatchJobParamVO batchJobParamVo : batchJobParamList2) {
                for (BatchTaskParamBO batchTaskParamBo : batchTaskParamBOList) {
                    if (batchJobParamVo.getTaskParamId().equals(batchTaskParamBo.getTaskParamId())) {
                        batchJobParamVo.setParamName(batchTaskParamBo.getTaskParamName()); // 参数名
                        batchJobParamVo.setIsManu(batchTaskParamBo.getIsManu()); // 是否手工输入
                        batchJobParamVo.setParamType(batchTaskParamBo.getTaskParamDataType()); // 数据类型
                        batchJobParamVo.setIsArray(batchTaskParamBo.getIsArray()); // 是否数组
                        batchJobParamVo.setIsRequired(batchTaskParamBo.getIsRequired()); // 是否必须

                        if (batchJobParamVo.getIsManu().toString().equals("0")) {
                            paramManageVo = new ParamManageVO();
                            paramManageVo.setParamId(batchTaskParamBo.getParamId());
                            paramManageVo.setIsDeleted("0");
                            paramManageVo = findParam(paramManageVo);
                            batchJobParamVo.setParamManageVo(paramManageVo);
                        }

                        batchJobParamList3.add(batchJobParamVo);
                    }
                }
            }
        }

        BatchJobVO batchJobBackVO = BeanUtils.copyProperties(BatchJobVO.class, batchJobBackBO);
        batchJobBackVO.setBatchJobPreDependList(BeanUtils.copyList(BatchJobDependVO.class, batchJobPreDependBOList));
        batchJobBackVO.setBatchJobPostDependList(BeanUtils.copyList(BatchJobDependVO.class, batchJobPostDependBOList));
        batchJobBackVO.setBatchJobParamList(batchJobParamList3);
        return batchJobBackVO;
        
    }
    
    public ParamManageVO findParam(ParamManageVO paramManageVo) {
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
    @Override
    public List<BatchJobVO> findExcuteJobAll(BatchJobVO batchJobVO) {
        List<BatchJobVO> batchJobVOList = new ArrayList<BatchJobVO>();
        logger.debug("<======BatchJobUCC--findExcuteJobAll======>");
        List<BatchJobBO> batchJobBOList = service.findAll(BeanUtils.copyProperties(BatchJobBO.class, batchJobVO));
        for(BatchJobBO batchJobBackBO:batchJobBOList)
        {
            BatchJobDependBO batchJobDependBO = new BatchJobDependBO();
            batchJobDependBO.setActionJobId(batchJobBackBO.getJobId());
            // 组装任务参数BO
            BatchJobParamBO batchJobParamBO = new BatchJobParamBO();
            batchJobParamBO.setJobId(batchJobBackBO.getJobId());
            // TODO 是：1；否：0。后续待统一
            batchJobParamBO.setIsDeleted("0");
            // 查询前置依赖
            List<BatchJobDependBO> batchJobPreDependBOList = batchJobDependService
                    .findAllBatchJobDependPreCond(batchJobDependBO);
            
            // 查询后置依赖
            List<BatchJobDependBO> batchJobPostDependBOList = batchJobDependService
                    .findAllBatchJobDependPostCond(batchJobDependBO);
            
            if(batchJobPreDependBOList.size() == 0 && batchJobPostDependBOList.size()==0)
            {
                BatchJobVO batchJobBackVO = BeanUtils.copyProperties(BatchJobVO.class, batchJobBackBO);
                batchJobVOList.add(batchJobBackVO);
            }
        }
        return batchJobVOList;
    }
    
    
    @Override
    public void addWhoelJob(BatchJobVO batchJobVO) {
        logger.debug("<======BatchUCC-{}--addBatchJob======>",getClass().getSimpleName());
        BatchJobBO batchBO = BeanUtils.copyProperties(BatchJobBO.class, batchJobVO);
        //新增任务依赖
        batchJobDependService.batchSave(BeanUtils.copyList(BatchJobDependBO.class, batchJobVO.getBatchJobPreDependList()));
        batchJobDependService.batchSave(BeanUtils.copyList(BatchJobDependBO.class, batchJobVO.getBatchJobPostDependList()));
        //新增任务参数信息
        batchJobParamService.batchSave(BeanUtils.copyList(BatchJobParamBO.class,batchJobVO.getBatchJobParamList()));
        //新增任务信息
        getService().add(batchBO);
        BeanUtils.copyProperties(batchJobVO, batchBO);
        
    }
    
}
