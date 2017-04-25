package cn.com.git.udmp.test.batch.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.git.udmp.common.utils.SpringContextHolder;
import cn.com.git.udmp.component.batch.common.constants.StatusEnum;
import cn.com.git.udmp.component.batch.core.server.manage.impl.JobDependManagerInDB;
import cn.com.git.udmp.core.logging.ILog;

/** 
 * @description JobDependManger测试类
 * @author Liang liuliang1@git.com.cn 
 * @date 2016年12月13日 下午5:26:03  
*/
@ContextConfiguration(locations = { "classpath*:/META-INF/spring/spring-udmp-context.xml","classpath*:/META-INF/**/spring-context-batch*.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class JobDependTest implements ILog{
    
    @Autowired
    JobDependManagerInDB jobDependManager;
    
    
    @Test
    public void testFindStopConditions(){
        String jobId = "708";
        String jobChainRunId = "123321";
        logger.debug("{}的前置条件是{}",jobId,jobDependManager.findPreCondition(jobId));
        logger.debug("{}的启动校验结果是{}",jobId,jobDependManager.checkPreCondtion(jobId, jobChainRunId));
    }
    
    @Test
    public void testActiveJob(){
        String jobId = "670";
        String jobChainRunId = "123321";
        jobDependManager.activateAfter(jobId, jobChainRunId, StatusEnum.SUCCESS);
    }
}
