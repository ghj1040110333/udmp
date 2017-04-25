package cn.com.git.udmp.component.batch.core.server.manage;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;

import cn.com.git.udmp.common.exception.FrameworkException;
import cn.com.git.udmp.component.batch.common.constants.BatchCommonConst;
import cn.com.git.udmp.component.batch.common.constants.JobRunStatus;
import cn.com.git.udmp.component.batch.model.AgentConfig;
import cn.com.git.udmp.component.batch.model.AgentInfo;
import cn.com.git.udmp.core.exception.FrameworkRuntimeException;
import cn.com.git.udmp.core.logging.ILog;
import cn.com.git.udmp.impl.batch.agent.ucc.IBatchAgentUCC;
import cn.com.git.udmp.impl.batch.agent.vo.BatchAgentVO;
import cn.com.git.udmp.impl.batch.jobRun.ucc.IBatchJobRunUCC;
import cn.com.git.udmp.impl.batch.jobRun.vo.BatchJobRunVO;

/**
 * @description agent管理类
 * @author liuliang 
 * @date 2015年2月3日 上午11:30:59
 */
@Component
public class AgentManager implements ILog{
    private static ConcurrentHashMap<String, AgentInfo> agent = new ConcurrentHashMap<String, AgentInfo>();
    
    @Autowired
    private IBatchAgentUCC batchAgentUCC;
    @Autowired
    private IBatchJobRunUCC batchJobRunUCC;

    /**
     * @description 获取所有启用中的agent
     * @author liuliang liuliang@newchinalife.com
     * @return 所有启用中的agent列表
     */
    public List<AgentConfig> getActivedAgents() {
        return getDBAgentsBySys(null);
    }

    /**
     * @description 返回内存中所有的agent信息
     * @author liuliang liuliang@newchinalife.com
     * @return agent信息列表
     */
    public static ConcurrentHashMap<String, AgentInfo> getAgentInfo() {
        return agent;
    }

    /**
     * @description 返回内存中对应IP的agent信息
     * @author liuliang liuliang@newchinalife.com
     * @param ip Agent的ip地址
     * @return agent的相关信息
     */
    public static AgentInfo getAgentInfoByIP(String ip,int port) {
        return agent.get(ip+":"+port);
    }

    /**
     * @description 根据系统号获取心跳存活的agent列表
     * @author liuliang liuliang@newchinalife.com
     * @param systemId 系统ID
     * @return 心跳存活的agent列表
     */
    public List<AgentConfig> getAgentsBySys(String systemId) {
        List<AgentConfig> dbAgentsBySys = getDBAgentsBySys(systemId);
        List<AgentConfig> results = new ArrayList<AgentConfig>();
        for (AgentConfig key : dbAgentsBySys) {
            AgentInfo agentInfoByIP = AgentManager.getAgentInfoByIP(key.getAgentIp(),key.getAgentPort());
            // 如果心跳监听结果未连通则去除未连通的节点
            if (agentInfoByIP == null || !agentInfoByIP.isActive()) {
                continue;
            }
            results.add(key);
        }
        return results;
    }

    /**
     * @description 根据系统号获取agent列表
     * @author liuliang liuliang@newchinalife.com
     * @param systemId 系统ID
     * @return agent列表
     */
    private List<AgentConfig> getDBAgentsBySys(String systemId) {
        BatchAgentVO batchAgentVO = new BatchAgentVO();
        if (!StringUtils.isEmpty(systemId)) {
            batchAgentVO.setAgentSystem(systemId);
        }
        // TODO batch--常量替换
        batchAgentVO.setIsDeleted(BatchCommonConst.BATCH_FLAG_FALSE);
        batchAgentVO.setIsEnable(BatchCommonConst.BATCH_FLAG_TRUE);
        // 根据系统号返回对应的agent列表
        List<BatchAgentVO> agentResults = batchAgentUCC.findAll(batchAgentVO);
        List<AgentConfig> agentConfigs = new ArrayList<AgentConfig>();
        for (BatchAgentVO key : agentResults) {
            AgentConfig agentConfig = new AgentConfig();
            try {
                BeanUtils.copyProperties(agentConfig, key);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new FrameworkException(e.getMessage());
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                throw new FrameworkException(e.getMessage());
            }
            agentConfig.setAgentId(key.getAgentId().toString());
            agentConfig.setAgentPort(key.getAgentPort().intValue());
            agentConfig.setAgentThreadLimitCnt(key.getAgentThreadLimitCnt().intValue());
            agentConfigs.add(agentConfig);
        }

        // AgentConfig agentConfig = new AgentConfig();
        // agentConfig.setAgentId("33");
        // agentConfig.setAgentIp("127.0.0.1");
        // agentConfig.setAgentPort(9999);
        // return Arrays.asList(new AgentConfig[] { agentConfig });
        return agentConfigs;
    }

    /**
     * @description 更新agent的相关信息
     * @author liuliang liuliang@newchinalife.com
     * @param agentInfo agent的相关信息
     */
    public static void updateAgentInfo(AgentInfo agentInfo) {
        if(StringUtils.isNotEmpty(agentInfo.getAgentIp())&&agentInfo.getAgentPort()!=0){
            AgentManager.agent.put(agentInfo.getAgentIp()+":"+agentInfo.getAgentPort(), agentInfo);
        }else{
            throw new FrameworkRuntimeException("执行代理信息缺失");
        }
    }
    
    
    /**
     * added by liang on 20160923
     * 根据 agent的id获取其正在执行的任务数
     * @param agentIds agentId列表
     * @return 
     */
    public Map<String,Integer> getAgentWithJobs(List<String> agentIds){
    	HashMap<String, Integer> resultMap = Maps.newHashMap();
    	for (String agentId : agentIds) {
    		resultMap.put(agentId, getAgentWithJobNum(agentId));
    	}
		return resultMap;
    }
    
    /**
     * 获取代理agentId上存在的正在Running的任务数
     * @param agentId 代理ID
     * @return 代理上正在Running的任务数
     */
    public Integer getAgentWithJobNum(String agentId){
    	BatchJobRunVO jobRunVO = new BatchJobRunVO();
		jobRunVO.setAgentId(new BigDecimal(agentId));
		jobRunVO.setStatus(JobRunStatus.RUNNING);
		List<BatchJobRunVO> result = batchJobRunUCC.findAll(jobRunVO );
		return result==null?0:result.size();
    }
    

    public void setBatchAgentUCC(IBatchAgentUCC batchAgentUCC) {
        this.batchAgentUCC = batchAgentUCC;
    }

	public static void setAgent(ConcurrentHashMap<String, AgentInfo> agent) {
		AgentManager.agent = agent;
	}

	public IBatchJobRunUCC getBatchJobRunUCC() {
		return batchJobRunUCC;
	}

	public void setBatchJobRunUCC(IBatchJobRunUCC batchJobRunUCC) {
		this.batchJobRunUCC = batchJobRunUCC;
	}


}
