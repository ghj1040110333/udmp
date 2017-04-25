package cn.com.git.udmp.test.batch.framework;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.git.udmp.common.utils.testsupport.AbstractTest;
import cn.com.git.udmp.component.batch.common.constants.BatchCommonConst;
import cn.com.git.udmp.component.model.CurrentPage;
import cn.com.git.udmp.impl.batch.job.ucc.IBatchJobUCC;
import cn.com.git.udmp.impl.batch.job.vo.BatchJobVO;

/** 
 * @description agent的UCC单元测试
 * @author liuliang liuliang1@git.com.cn 
 * @date 2016年1月4日 上午11:52:23  
*/
public class BatchAgentUCCTest extends AbstractTest {
    @Autowired
    private IBatchJobUCC batchJobUCC;

    @Test
    public void testFindAll() {
        BatchJobVO batchJobVO = new BatchJobVO();
        batchJobVO.setIsEnable(BatchCommonConst.BATCH_FLAG_TRUE);
        batchJobVO.setIsDeleted(BatchCommonConst.BATCH_FLAG_FALSE);
        batchJobVO.setIsGroup(BatchCommonConst.BATCH_FLAG_FALSE);
        List<BatchJobVO> batchJobVOResult = batchJobUCC.findAll(batchJobVO);
        System.out.println(batchJobVOResult);
    }
    
    @Test
    public void testFind(){
        
    }
    
    @Test
    public void testQueryForPage(){
        BatchJobVO batchJobVO = new BatchJobVO();
        batchJobVO.setIsEnable(BatchCommonConst.BATCH_FLAG_TRUE);
        batchJobVO.setIsDeleted(BatchCommonConst.BATCH_FLAG_FALSE);
        batchJobVO.setIsGroup(BatchCommonConst.BATCH_FLAG_FALSE);
        CurrentPage<BatchJobVO> currentPage = new CurrentPage<BatchJobVO>();
        CurrentPage<BatchJobVO> queryForPage = batchJobUCC.queryForPage(batchJobVO, currentPage );
        System.out.println(queryForPage.getPageItems().size());
    }

    public void setBatchJobUCC(IBatchJobUCC batchJobUCC) {
        this.batchJobUCC = batchJobUCC;
    }
}
