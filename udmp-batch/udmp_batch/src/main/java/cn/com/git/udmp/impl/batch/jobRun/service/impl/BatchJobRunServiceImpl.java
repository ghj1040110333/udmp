package cn.com.git.udmp.impl.batch.jobRun.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.git.udmp.common.persistence.Page;
import cn.com.git.udmp.component.batch.common.base.BaseBatchService;
import cn.com.git.udmp.component.model.CurrentPage;
import cn.com.git.udmp.impl.batch.jobRun.bo.BatchJobRunBO;
import cn.com.git.udmp.impl.batch.jobRun.dao.IBatchJobRunDao;
import cn.com.git.udmp.impl.batch.jobRun.po.BatchJobRunPO;
import cn.com.git.udmp.impl.batch.jobRun.service.IBatchJobRunService;
import cn.com.git.udmp.utils.bean.BeanUtils;

/** 
 * @description BatchJobRunServiceImpl实现类
 */
@Service
 public class BatchJobRunServiceImpl extends BaseBatchService<BatchJobRunBO,BatchJobRunPO> implements IBatchJobRunService{
    @Autowired
    private IBatchJobRunDao dao;
    
    @Override
    public IBatchJobRunDao getDao() {
        return dao;
    }
    public void setDao(IBatchJobRunDao dao) {
        this.dao = dao;
    }
    
    @Override
    public int findTotal(BatchJobRunBO batchAgentBO) {
        
        logger.debug("<======{}--findTotal======>",getClass().getSimpleName());
        BatchJobRunPO po = BeanUtils.copyProperties(BatchJobRunPO.class, batchAgentBO);
        setPO(po);
        Map<String, Object> data = po.getData();
        List<String> resultIds = getDao().queryJobChainRunIds(data);
        //添加jobChainRunId List 对象
        data.put("resultIds", resultIds);
        logger.debug("查询批处理总数的条件，任务执行批次IDs:{}",resultIds);
        if(resultIds.size() == 0 && po.getJobType().equals("S"))
        {
            return 0;
        }else
        {
            return getDao().findTotal(data);
        }
    }
    
    @Override
    public void addWithId(BatchJobRunBO bo) {
        BatchJobRunPO data = BeanUtils.copyProperties(BatchJobRunPO.class, bo);
        setPO(data);
        getDao().addWithId(data.getData());
        bo = cpPoToBo(data.getData(), bo);
    }
    
    @Override
    public CurrentPage<BatchJobRunBO> queryForPage(BatchJobRunBO bo, CurrentPage<BatchJobRunBO> currentPage) {
        logger.debug("<======{}--queryForPage======>",getClass().getSimpleName());
        BatchJobRunPO batchJobPO = BeanUtils.copyProperties(BatchJobRunPO.class, bo);
        setPO(batchJobPO);
        // TODO 添加currentPage的转换工作
        //查询总数
        int total = getDao().findTotal(batchJobPO.getData());
        Map<String, Object> data = batchJobPO.getData();
//      System.out.println(data);
        int pageNo;
        int pageSize = currentPage.getPageSize();

//      if (currentPage.getPageNo() == 1) { // 如果当前页码是第一页
//          pageNo = 0;
//      } else {
            pageNo = currentPage.getPageNo();
//      }
//        int greaterNum = pageNo * currentPage.getPageSize();
//        int lessNum = (pageNo + 1) * currentPage.getPageSize();

//        data.put("GREATER_NUM", greaterNum);
//        data.put("LESS_NUM", lessNum);
        // GREATER_NUM,LESS_NUM
        // Updated by liang -- 为集成jeesite的方言翻页做配置 
        Page<Object> page = new Page<Object>(pageNo, pageSize, currentPage.getTotal());
        
        List<String> resultIds = getDao().queryJobChainRunIds(data);
        
        //添加jobChainRunId List 对象
        data.put("page", page);
        data.put("resultIds", resultIds);
        logger.debug("查询批处理数据的条件，任务执行批次IDs:{}",resultIds);
        List<Map> results = null;
        if(resultIds.size() == 0 && batchJobPO.getJobType().equals("S"))
        {
            results = new ArrayList<Map>();
        }else
        {
            results = getDao().queryForPage(data);
        }
        logger.debug("查询批处理数据的条件，任务执行批次data.IDs:{}",data.get("resultIds"));
        currentPage.setPageItems(changeListMap2Bo(results));
        currentPage.setTotal(total);
        return currentPage;
    }
    
}
