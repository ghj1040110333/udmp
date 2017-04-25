package cn.com.git.udmp.test.batch.common;

import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

import cn.com.git.udmp.common.utils.SpringContextHolder;
import cn.com.git.udmp.component.batch.common.constants.StatusEnum;
import cn.com.git.udmp.component.batch.core.server.manage.JobStatusManager;

/** 
 * @description 任务状态操作测试类
 * @author Liang liuliang1@git.com.cn 
 * @date 2016年12月12日 上午10:12:31  
*/
public class JobStatusTest {
    
    private JobStatusManager jobStatusManager = new JobStatusManager();

    /**
     * @title
     * @description 测试多线程下状态收集是否会出现异常
     * 
     * @throws InterruptedException 
    */
    @Test
    public void testStatusMap() throws InterruptedException{
        for(int i=0;i<20;i++){
            final int m = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    jobStatusManager .setJobStatus(Integer.toString(m), m+"a", "group", StatusEnum.RUNNING);
                }
            }).start();
        }
        Thread.sleep(1000L);
        ConcurrentHashMap<String, ConcurrentHashMap<String, StatusEnum>> map = JobStatusManager.getJobChainRunStatus();
        System.out.println(map);
        System.out.println(map.get("group").size());
    }
}
