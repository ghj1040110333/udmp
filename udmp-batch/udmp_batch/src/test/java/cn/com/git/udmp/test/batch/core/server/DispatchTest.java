package cn.com.git.udmp.test.batch.core.server;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.git.udmp.common.utils.testsupport.AbstractTest;
import cn.com.git.udmp.component.batch.common.constants.StatusEnum;
import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.core.ServerInit;
import cn.com.git.udmp.component.batch.core.server.job.IJobController;
import cn.com.git.udmp.component.batch.core.server.manage.BatchQuartzManager;
import cn.com.git.udmp.component.batch.core.server.manage.IJobDependManager;
import cn.com.git.udmp.component.batch.core.server.manage.JobStatusManager;

/**
 * @description 任务调度测试 
 * @author liuliang liuliang_wb@newchina.live
 * @date 2015年3月30日
 * @version 1.0
 */
public class DispatchTest extends AbstractTest{
    
    @Autowired
    private ServerInit serverInit;
    
	IJobDependManager jobDependManager;

	@Autowired
    private JobStatusManager jobStatusManager;
    @Override
    public void setupAbstractTest() {
        super.setupAbstractTest();
    }
    
	@Test
	public void test(){
	    jobDependManager = (IJobDependManager) getBean("jobDependManager");
		logger.debug("starting..............");
	}
	
	/**
	 * @description 测试激活执行某一任务
	 */
	@Test
	public void testController(){
		IJobController jobController = (IJobController) getBean("runJobController");
		jobController.control(new JobSessionContext());
	}
	
	/**
	 * @throws InterruptedException 
	 * @description 测试初始化quartz的信息
	 */
	@Test
	public void testQuartzInit() throws InterruptedException{
//		JobConfig jobConfig = new JobConfig();
//		jobConfig.setJobId("1");
//		jobConfig.setJobGroupId(10);
//		BatchQuartzManager.setRunJob(jobConfig);
	    BatchQuartzManager.quartzInit();
		Thread.sleep(1000000);
	}
	
	@Test
	public void testStaticInject(){
        jobDependManager.findJobsStop("", StatusEnum.SUCCESS);
	}
	
	@Test
	public void testDependUCC(){
	    jobDependManager.findJobsAfter("2", StatusEnum.SUCCESS);
	}
	
	@Test
	public void testJobDepend(){
	    jobStatusManager.setJobStatus("2","1","2", StatusEnum.SUCCESS);
	}
	
	@Test
	public void testServer(){
	    serverInit.init();
	}

    public void setServerInit(ServerInit serverInit) {
        this.serverInit = serverInit;
    }
}
