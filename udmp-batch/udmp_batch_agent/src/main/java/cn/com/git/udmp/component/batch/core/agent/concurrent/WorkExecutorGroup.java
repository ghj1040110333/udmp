package cn.com.git.udmp.component.batch.core.agent.concurrent;

import java.util.List;
import java.util.concurrent.ThreadFactory;

import com.google.common.collect.Lists;

import cn.com.git.udmp.component.batch.core.agent.IWork;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.RejectedExecutionHandler;

/** 
 * @description 批处理作业线程池
 * @author Liang liuliang1@git.com.cn 
 * @date 2017年4月14日 上午11:14:51  
*/
public class WorkExecutorGroup extends DefaultEventExecutorGroup {

    List<IWork> list = Lists.newCopyOnWriteArrayList();

    public WorkExecutorGroup(int nThreads, ThreadFactory threadFactory, int maxPendingTasks,
            RejectedExecutionHandler rejectedHandler) {
        super(nThreads, threadFactory, maxPendingTasks, rejectedHandler);
    }

    public WorkExecutorGroup(int nThreads, ThreadFactory threadFactory) {
        super(nThreads, threadFactory);
    }

    public WorkExecutorGroup(int nThreads) {
        super(nThreads);
    }

    public <T> Future<T> submit(IWork<T> work) {
        recycleThread();
        Future<T> future = super.submit(work);
        list.add(work);
        return future;
    }

    /**
     * @title
     * @description 去除线程对象引用,释放资源引用
     * 
     */
    private void recycleThread() {
        for (IWork key : list) {
            if (key.isStoped() || key.isFinished()) {
                list.remove(key);
            }
        }
    }

    @Override
    public Future<?> shutdownGracefully() {
        for (IWork key : list) {
            key.stopWork();
            list.remove(key);
        }
        return super.shutdownGracefully();
    }

}
