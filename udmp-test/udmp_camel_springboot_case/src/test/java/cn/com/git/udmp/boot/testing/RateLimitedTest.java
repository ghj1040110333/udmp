package cn.com.git.udmp.boot.testing;

import com.google.common.util.concurrent.RateLimiter;

public class RateLimitedTest {
    public static void main(String[] a){
        RateLimiter_2();
    }
    
    /* 
     * 前提：默认limiter.acquire()
     *     默认为1秒，若创建5个令牌的话，那就是1秒除以5=200毫秒，也就是说，每隔200毫秒就执行一次limiter.acquire()方法，
     * 也就是所谓的按照200毫秒平滑限流，若需要改变限流时间，可以通过改变令牌数量来实现
     */ 
    public static void RateLimiter_1(){
        RateLimiter limiter = RateLimiter.create(5);
        System.out.println(limiter.acquire());
        System.out.println(limiter.acquire());
        System.out.println(limiter.acquire());
        System.out.println(limiter.acquire());
        System.out.println(limiter.acquire());
        System.out.println(limiter.acquire());
    }
    
    /**
     * 
     * @title
     * @description
     *  前提：修改limiter.acquire()方法构造的值，来反映限流的效果
     */
    public static void RateLimiter_2(){
        RateLimiter limiter = RateLimiter.create(5);
        System.out.println(limiter.acquire(10));
        System.out.println(limiter.acquire(1));
        System.out.println(limiter.acquire(1));
    }
}
