package cn.com.git.udmp.boot.camel.processor;

import cn.com.git.udmp.common.model.DataObject;
import cn.com.git.udmp.core.logging.ILog;

/**
 * 执行任务接口
 * @author liang
 * 2016年8月3日
 */
public interface IProcessor extends ILog{
    public <T extends DataObject> T doProcess(T sourceObj);
}
