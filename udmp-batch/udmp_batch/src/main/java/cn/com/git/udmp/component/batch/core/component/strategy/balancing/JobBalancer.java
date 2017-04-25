package cn.com.git.udmp.component.batch.core.component.strategy.balancing;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import cn.com.git.udmp.common.exception.FrameworkException;
import cn.com.git.udmp.component.batch.common.utils.BalanceUtil;
import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.core.server.manage.AgentManager;
import cn.com.git.udmp.component.batch.core.server.manage.IJobManager;
import cn.com.git.udmp.component.batch.model.AgentConfig;
import cn.com.git.udmp.component.batch.model.AgentWithJob;
import cn.com.git.udmp.component.batch.model.JobConfig;
import cn.com.git.udmp.component.batch.model.JobParam;
import cn.com.git.udmp.component.batch.common.utils.BalanceUtil.Section;
import cn.com.git.udmp.core.logging.ILog;

/**
 * @description 任务的负载均衡
 * @author liuliang
 * @date 2015年1月29日 下午4:17:12
 */
@Component
public class JobBalancer implements ILog {
	
    @Autowired
    @Qualifier("jobManager")
    private  IJobManager jobManager;

    @Autowired
    private  AgentManager agentManager;

	/**
	 * @description 查询agent列表及其对应需要处理的记录集
	 * @param jobId
	 *            任务ID
	 * @param start
	 *            起始值
	 * @param end
	 *            结束值
	 * @param context
	 * @return agent列表及其对应需要处理的记录集
	 */
	public List<AgentWithJob> getAgentsWithJob(String jobId, long start, long end, JobSessionContext context) {
		// TODO 负载均衡+心跳监 听+赋值参数
		JobConfig jobConfig = jobManager.getJob(jobId);
		String system = jobConfig.getTaskSystem();

		// 获取任务的参数
		List<JobParam> jobParams = jobManager.getJobParams(jobId);
		// 获取能使用并且匹配的agent端（心跳监听）
		List<AgentConfig> agents = agentManager.getAgentsBySys(system);
		List<AgentWithJob> resultList = new ArrayList<AgentWithJob>();
		// 负载均衡(简单逻辑)
		int size = agents.size();
		//modified by liang on 20160922 --通过balanceUtil工具类获取分片结果
		if(size==0){
			return resultList;
		}
		List<Section> balanceArray = BalanceUtil.balanceArray(size, start, end);
		//modified by liang on 20160923 -- 对agent做一个排序(根据数据库中agent对应的任务数做排序)
		/*--lambda syntax
		 * agents.sort((x,y)->agentManager.getAgentWithJobNum(x.getAgentId()).compareTo(agentManager.getAgentWithJobNum(y.getAgentId())));*/
		//TODO 这里对结果进行排序,实现负载均衡.
//		List<AgentConfig> sortAgents = sortList(agents);
		
		
		for (int i = 0; i < balanceArray.size(); i++) {
			AgentConfig agent = agents.get(i);
			AgentWithJob agentWithJob = new AgentWithJob();
			agentWithJob.setJobId(jobId);
			agentWithJob.setParams(jobParams);
			agentWithJob.setJobThreadLimit(jobConfig.getJobThreadLimitCnt());
			try {
				BeanUtils.copyProperties(agentWithJob, agent);
				agentWithJob.setJsContext(context);
			} catch (IllegalAccessException e) {
				logger.error("JobBalancer发生异常:{}",e);
				throw new FrameworkException(e.getMessage());
			} catch (InvocationTargetException  e) {
			    throw new FrameworkException(e.getMessage());
            }
			/**
			 * 设置区间起始值和结束值
			 */
			agentWithJob.setStartNum(balanceArray.get(i).getStartNum());
			agentWithJob.setEndNum(balanceArray.get(i).getEndNum());
			logger.debug("代理{}:{}获取到的执行分区是:{}到{}", agentWithJob.getAgentIp(),agentWithJob.getAgentPort(), agentWithJob.getStartNum(),
					agentWithJob.getEndNum());
			resultList.add(agentWithJob);
		}

		return resultList;
	}
	
	
	/**
	 * @title
	 * @description 对agent进行负载均衡排序
	 * 
	 * @param agents
	 * @return 
	*/
//	private List<AgentConfig> sortList(List<AgentConfig> agents) {
//    }


    public void setJobManager(IJobManager jobManager) {
		this.jobManager = jobManager;
	}
	public void setAgentManager(AgentManager agentManager) {
		this.agentManager = agentManager;
	}
	
	
//	public static void main(String[] args) {
//		ArrayList<Integer> list = Lists.newArrayList(11,21,33,14,55,6);
//		System.out.println(list);
//		list.forEach(System.out::println);
//		System.out.println("========================");
//		list.sort((x,y)->x.compareTo(y));
//		System.out.println(list);
//		list.forEach(System.out::println);
//	}
}
