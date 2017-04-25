package cn.com.git.udmp.component.batch.core.component.invoke;

import cn.com.git.udmp.common.model.DataObject;
import cn.com.git.udmp.core.logging.ILog;

public interface IService extends ILog{
    public void start();
    public void stop();
    public <T extends DataObject> void run(T t);
}
