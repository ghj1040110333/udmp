

package cn.com.git.udmp.test.batch.framework;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.git.udmp.common.utils.testsupport.AbstractTest;
import cn.com.git.udmp.component.batch.common.constants.BatchCommonConst;
import cn.com.git.udmp.component.model.CurrentPage;
import cn.com.git.udmp.impl.batch.job.ucc.IBatchJobUCC;
import cn.com.git.udmp.impl.batch.job.vo.BatchJobVO;

/**
 * @author liang
 *
 */
public class BatchJobUCCTest extends AbstractTest {
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
        BatchJobVO batchJobVO = new BatchJobVO();
        batchJobVO.setIsEnable(BatchCommonConst.BATCH_FLAG_TRUE);
        batchJobVO.setIsDeleted(BatchCommonConst.BATCH_FLAG_FALSE);
        batchJobVO.setIsGroup(BatchCommonConst.BATCH_FLAG_FALSE);
        batchJobVO.setJobId(new BigDecimal(238));
        BatchJobVO result = batchJobUCC.find(batchJobVO);
        System.out.println("结果集是否为null："+result==null);
        System.out.println(result.getJobName());
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
