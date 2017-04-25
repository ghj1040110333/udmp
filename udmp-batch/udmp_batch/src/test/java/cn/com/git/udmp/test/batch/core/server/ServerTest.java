

package cn.com.git.udmp.test.batch.core.server;

import org.junit.Test;

import cn.com.git.udmp.common.utils.testsupport.AbstractTest;
import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.core.ServerInit;
import cn.com.git.udmp.component.batch.core.server.job.IJobController;

/**
 * @description 服务端测试
 * @author liang
 *
 */
public class ServerTest extends AbstractTest{
    /**
     * @throws InterruptedException 
     * @description 启动服务端
     */
    @Test
    public void initServer() throws InterruptedException{
        logger.debug("==========服务端启动==========");
        //这里的服务端实际上是批处理服务的调度中心
        ServerInit server = (ServerInit) this.getBean("serverInit");//获取服务端启动实例
        server.init();//调用服务端初始化方法
        
        IJobController jobController = (IJobController) this.getBean("dispatchJobController");
        JobSessionContext jobSessionContext = new JobSessionContext();
        jobSessionContext.setAgentIp("127.0.0.1");
        jobSessionContext.setAgentPort("9999");
        jobSessionContext.setJobId("238");
        jobController.control(jobSessionContext );
        Thread.sleep(1000000);
    }
}
