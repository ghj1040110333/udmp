package cn.com.git.udmp.component.batch.core.server.manage.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;

import cn.com.git.udmp.component.batch.core.server.manage.IJobManager;
import cn.com.git.udmp.component.batch.model.JobConfig;

/**
 * @description 任务及其状态的管理类
 * @author liuliang 
 * @date 2015年1月28日 下午3:30:05
 */
public abstract class AbsJobManager implements IJobManager {
    protected static Map<String, JobConfig> jobConfigs = new HashMap<String, JobConfig>();

}
