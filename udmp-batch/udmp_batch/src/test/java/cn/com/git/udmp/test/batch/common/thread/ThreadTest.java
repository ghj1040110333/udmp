package cn.com.git.udmp.test.batch.common.thread;

import java.util.concurrent.TimeUnit;

public class ThreadTest {
    /**
     * @title
     * @description
     * 
     * @param args
     * @throws InterruptedException 
    */
    public static void main(String[] args) throws InterruptedException {
        Thread1 thread1 = new Thread1();
        thread1.start();
        TimeUnit.SECONDS.sleep(1);
        thread1.interrupt();
        TimeUnit.SECONDS.sleep(1);
        thread1.shutdown();
        TimeUnit.SECONDS.sleep(10);
    }
}

class Thread1 extends Thread{
    public static boolean stopFlag = false;
    
    public void shutdown(){
        stopFlag = true;
    }
    
    
    @Override
    public void run() {
        while(!stopFlag){
            System.out.println(">>>>>>>>>>>>>>>thread run");
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(">>>>>>>>>>>>>>thread end ");
    }
}
