package cn.com.git.udmp.component.batch.core.server.io;

import java.util.Properties;

import cn.com.git.udmp.component.batch.core.server.manage.IJobManager;
import cn.com.git.udmp.component.batch.io.IResourceLoader;

/**
 * @description 任务信息加载器
 * @author liuliang 
 * @date 2015年3月30日
 * @version 1.0
 */
public class JobLoader implements IResourceLoader {
    private IJobManager jobManager;

    @Override
    public void load(Properties props) {
        // TODO Auto-generated method stub
        logger.debug("任务信息加载器loading...");
        jobManager.boot();
    }

    @Override
    public void load() {
        // TODO Auto-generated method stub
        load(null);
    }

    public void setJobManager(IJobManager jobManager) {
        this.jobManager = jobManager;
    }

    @Override
    public void unload() {
        logger.debug("********jobloader unload********");
    }

}
