package cn.com.git.udmp.test.batch.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.core.server.job.impl.RunJobController;
import cn.com.git.udmp.core.logging.ILog;

/** 
 * @description 任务控制器测试类
 * @author Liang liuliang1@git.com.cn 
 * @date 2016年12月15日 上午10:24:13  
*/
@ContextConfiguration(locations = { "classpath*:/META-INF/spring/spring-udmp-context.xml","classpath*:/META-INF/**/spring-context-batch*.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class JobControllerTest implements ILog{
    @Autowired
    RunJobController runCtl;
    
    @Test
    public void testRunJob(){
        JobSessionContext jsContext = new JobSessionContext();
        jsContext.setJobId("708");
        runCtl.control(jsContext );
    }
    
    
    /**
     * @title 测试失败重跑任务
     * @description
     *  
    */
    @Test
    public void testReRunJob(){
        JobSessionContext jsContext = new JobSessionContext();
        jsContext.setJobId("708");
        jsContext.setJobChainRunId("2a7b50521f084dfb89925f1913122c98");
        runCtl.control(jsContext);
    }
}
