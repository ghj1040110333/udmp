package cn.com.git.udmp.batch.test.nettty;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Test;

import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;

public class FutureTest {
    /**
     * @title 简单测试下netty线程池的使用
     * @description
     * 
     * @throws InterruptedException
     */
    @Test
    public void testFuture() throws InterruptedException {
        EventExecutorGroup group = new DefaultEventExecutorGroup(4);
        Future<String> future = group.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "hello,world";
            }
        });
        future.addListener(new FutureListener<String>() {

            @Override
            public void operationComplete(Future<String> future) throws Exception {
                System.out.println(future.get(5, TimeUnit.SECONDS));
            }
        });
        Thread.sleep(10000);
    }

    /**
     * @title 测试线程异常通过listener控制
     * @description
     * 
     * @throws InterruptedException
     */
    @Test
    public void testFutureException() throws InterruptedException {
        EventExecutorGroup group = new DefaultEventExecutorGroup(4);
        Future<String> future = group.submit(new Callable<String>() {

            @Override
            public String call() throws Exception {
                throw new RuntimeException("这是个异常");
            }
        });
        future.addListener(new FutureListener<String>() {
            @Override
            public void operationComplete(Future<String> future) throws Exception {
                try {
                    String result = future.get(5, TimeUnit.SECONDS);
                    System.out.println(result);
                } catch (Exception e) {
                    System.out.println("异常怎么办" + e.getMessage());
                    System.out.println(e.getClass().getName());
                    System.out.println(e.getCause() instanceof RuntimeException);
                }
            }
        });
        Thread.sleep(10000);
    }

    /**
     * @title 测试直接cancel线程能否停止线程
     * @description
     * 
     * @throws InterruptedException
     */
    @Test
    public void testCancelThread() throws InterruptedException {
        EventExecutorGroup group = new DefaultEventExecutorGroup(4);
        Future<String> future = group.submit(new Callable<String>() {

            @Override
            public String call() throws Exception {
                int i;
                for (i = 0; i < 10000; i++) {
                    Thread.sleep(1000);
                    System.out.println(i);
                }
                return "complete";
            }
        });
        Thread.sleep(2000);
        System.out.println(future.isCancellable());
        while (!future.isCancelled()) {
            future.cancel(true);
            Thread.sleep(100);
            if (future.isCancelled()) {
                future.cancel(false);
            }
            System.out.println("canceled: " + future.isCancelled());
        }
        ;
        Thread.sleep(10000);
    }

    /**
     * @title 通过时间停止线程
     * @description
     * @result 只能通过future抛出异常，线程停止还需要自己控制
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     */
    @Test
    public void testCancelThreadByTime() throws InterruptedException, ExecutionException, TimeoutException {
        EventExecutorGroup group = new DefaultEventExecutorGroup(4);
        Future<String> future = group.submit(new Callable<String>() {

            @Override
            public String call() throws Exception {
                int i;
                boolean flag = false;
                for (i = 0; i < 10000; i++) {
                    if (!flag) {
                        Thread.sleep(1000);
                        System.out.println(i);
                    }
                }
                return "complete";
            }
        });
        try {
            future.get(5, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println("catch the exception");
        }
        Thread.sleep(10000);
    }

    @Test
    public void testCancelThreadBySelf() throws InterruptedException, ExecutionException, TimeoutException {
        EventExecutorGroup group = new DefaultEventExecutorGroup(4);

        MyCallable<String> task = new MyCallable<String>() {
            boolean flag = false;

            @Override
            public String call() throws Exception {
                int i;
                for (i = 0; i < 10000; i++) {
                    if (!flag) {
                        Thread.sleep(100);
                        System.out.println(i);
                    }
                }
                return flag?"stoppped":"complete";
            }

            @Override
            public void setClose() {
                flag = true;
            }
        };
        Future<String> future = group.submit(task);
        System.out.println("==========ending==========");
        Thread.sleep(2000);
        task.setClose();
        System.out.println("==========ended==========");
        System.out.println(future.get());
        Thread.sleep(10000);
    }

    private interface MyCallable<V> extends Callable<V> {
        void setClose();
    }

}
