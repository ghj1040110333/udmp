package cn.com.git.udmp.test.batch.common.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ThreadPoolTest {

    ExecutorService executor = Executors.newSingleThreadExecutor();

    /**
     * @title
     * @description 测试正常发布一个线程到线程池并停止
     * 
     * @throws InterruptedException 
    */
    @Test
    public void testCancelThread() throws InterruptedException {
        Thread2 task = new Thread2();
        Future<?> future1 = executor.submit(task);
        TimeUnit.SECONDS.sleep(1);
        boolean result = future1.cancel(true);
        System.out.println("cacel线程是否成功:" + result);
        TimeUnit.SECONDS.sleep(2);
    }

    /**
     * @title
     * @description 测试线程池的shutdownNow方法，该方法会相当于发送一个interrupt方法，而且不再接收新线程，抛出RejectedExecutionException异常
     * 
     * @throws InterruptedException
     */
    @Test
    public void testShutdownNowMethod() throws InterruptedException {
        try {
            Thread2 task = new Thread2();
            Future<?> future1 = executor.submit(task);
            TimeUnit.SECONDS.sleep(1);
            System.out.println(">>>>>>>>>>>>shutdown now");
            executor.shutdownNow();
            TimeUnit.SECONDS.sleep(1);
            System.out.println(">>>>>>>>>>>>add thread again");
            testCancelThread();
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

    /**
     * @title 测试一个个加入到线程池中
     * @description
     * 
     * @throws InterruptedException
     */
    @Test
    public void testAddTwoThreadOneByOne() throws InterruptedException {
        executor = Executors.newFixedThreadPool(1);
        System.out.println(">>>>>>>>>>第一个线程添加");
        testCancelThread();
        System.out.println(">>>>>>>>>>第二个线程添加");
        testCancelThread();
    }

    /**
     * @title
     * @description 通过一次性submit两个线程到一个大小为1的线程池发现如果第一个线程不停止第二个线程会一直出于等待状态，当第一个线程停止后第二个线程才会开始执行
     * 
     * @throws InterruptedException
     */
    @Test
    public void testAddTowThreadInOneTime() throws InterruptedException {
        executor = Executors.newFixedThreadPool(1);
        System.out.println(">>>>>>>>>>第一个线程添加");
        Thread2 task = new Thread2(1);
        Future<?> future1 = executor.submit(task);
        TimeUnit.SECONDS.sleep(1);
        System.out.println(">>>>>>>>>>第二个线程添加");
        Thread2 task2 = new Thread2(2);
        Future<?> future2 = executor.submit(task2);
        TimeUnit.SECONDS.sleep(1);
        System.out.println(">>>>>>>>>>关闭线程一");
        boolean result = future1.cancel(true);
        System.out.println("cacel线程是否成功:" + result);
        TimeUnit.SECONDS.sleep(5);
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        try {
            Thread2 task = new Thread2();
            Future<?> future1 = executor.submit(task);
            TimeUnit.SECONDS.sleep(1);
            boolean result = future1.cancel(true);
            System.out.println("cacel线程是否成功:" + result);
        } finally {
            executor.shutdown();
        }
    }

}

class Thread2 implements Runnable {
    public boolean stopFlag = false;
    public int i;

    public Thread2(int i) {
        this.i = i;
    }

    public Thread2() {
    }

    public void shutdown() {
        stopFlag = true;
    }

    @Override
    public void run() {
        while (!stopFlag) {
            System.out.println(Thread.currentThread().getName() + ">>>>>>>>>>>>>>>thread run:" + i);
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                stopFlag = true;
            }
        }
        System.out.println(">>>>>>>>>>>>>>thread end ");
    }
}