package cn.com.git.udmp.test.batch.framework.ucc;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.git.udmp.impl.batch.job.ucc.IBatchJobUCC;
import cn.com.git.udmp.impl.batch.job.vo.BatchJobVO;

/** 
 * @description 批处理任务UCC测试用例
 * @author Liang liuliang1@git.com.cn 
 * @date 2016年12月27日 上午10:34:12  
*/
@ContextConfiguration(locations = { "classpath*:/META-INF/spring/spring-udmp-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class BatchJobUCCTest {
   
    @Autowired
    private IBatchJobUCC batchJobUCC;
    
    /**
     * @title
     * @description 测试新增任务
     *   
    */
    @Test
    public void testAdd(){
        BatchJobVO batchJobVO = new BatchJobVO();
        batchJobVO.setJobName("demoJob1");
        batchJobVO.setJobGroupId(new BigDecimal(0));
        batchJobVO.setIsAllowManu("1");
        batchJobVO.setIsEnable("1");
        batchJobVO.setJobBatchSize(new BigDecimal(10));
        batchJobVO.setJobExpectDuration(new BigDecimal(10));
        batchJobVO.setIsAllowSplit("0");
        batchJobVO.setJobThreadLimitCnt(new BigDecimal(10));
        batchJobVO.setTaskId(new BigDecimal(1));
        batchJobVO.setJobAlertDuration(new BigDecimal(10));
        batchJobVO.setJobType("1");
        batchJobVO.setIsGroup("0");
//        batchJobVO.setJobAlertDuration(new big);
        
        batchJobUCC.add(batchJobVO );
        printBanner("新增后获取到的ID是");
        System.out.println(batchJobVO.getJobId());
    }
    
    
    

    private void printBanner(String topic) {
        System.out.println("============"+topic+"=============");
    }
}
