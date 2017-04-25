package cn.com.git.udmp.test.batch.common.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import ch.qos.logback.core.util.TimeUtil;
import cn.com.git.udmp.core.logging.ILog;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;

public class NettyPoolTest implements ILog{
    @Test
    public void testNetty() throws InterruptedException {
        EventExecutorGroup group = new DefaultEventExecutorGroup(4); // 4  threads
        final CountDownLatch latch = new CountDownLatch(1);
        Future<?> f = group.submit(new Runnable() {
            @Override
            public void run() {
//                throw new RuntimeException("test exception");
                try {
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println("success");
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        f.addListener(new FutureListener<Object>() {
            @Override
            public void operationComplete(Future<Object> future) throws Exception {
//                latch.countDown();
                if (future.cause() != null) {
                    logger.debug("error:{}",future.cause().getMessage());
                } else {
                    logger.debug("success");
                }
            }
        });
        latch.await();
        System.out.println("complete");
    }
}
