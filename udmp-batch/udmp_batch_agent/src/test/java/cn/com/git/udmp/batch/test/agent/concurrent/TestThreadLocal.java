package cn.com.git.udmp.batch.test.agent.concurrent;

import org.junit.Assert;
import org.junit.Test;

import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.core.agent.Work;

public class TestThreadLocal {
    @Test
    public void testThreadLocalOnWork() throws InterruptedException{
        new Thread(new Runnable() {
            @Override
            public void run() {
                Assert.assertEquals(false, Work.isCurrentJobStoped());
            }
        }).start();
        Thread.sleep(100);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Assert.assertEquals(false, Work.isCurrentJobStoped());
            }
        }).start();
        Thread.sleep(100);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Work work = new Work(0, new JobSessionContext(),null);
                work.stopWork();
                Assert.assertEquals(true, Work.isCurrentJobStoped());
            }
        }).start();
        Thread.sleep(100);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Assert.assertEquals(false, Work.isCurrentJobStoped());
            }
        }).start();
        Thread.sleep(100); 
    }
    
    
}
