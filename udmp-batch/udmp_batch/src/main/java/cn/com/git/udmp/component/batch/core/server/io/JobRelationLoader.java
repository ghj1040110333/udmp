package cn.com.git.udmp.component.batch.core.server.io;

import java.util.Properties;

import cn.com.git.udmp.component.batch.io.IResourceLoader;

/**
 * @deprecated
 * @description 任务的依赖关系加载器
 * @author liuliang liuliang_wb@newchina.live
 * @date 2015年3月30日
 * @version 1.0
 */
public class JobRelationLoader implements IResourceLoader {

    @Override
    public void load(Properties props) {
        // TODO Auto-generated method stub
        logger.debug("任务的依赖关系加载器loading...");
    }

    @Override
    public void load() {
        // TODO Auto-generated method stub
        load(null);
    }

    @Override
    public void unload() {
        logger.debug("********jobRelationLoader unload********");
    }

}
