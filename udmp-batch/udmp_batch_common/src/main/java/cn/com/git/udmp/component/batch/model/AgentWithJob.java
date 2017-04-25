package cn.com.git.udmp.component.batch.model;

import java.util.ArrayList;
import java.util.List;

import cn.com.git.udmp.component.batch.context.JobSessionContext;

/**
 * @description 附带任务信息的任务配置
 * @author liuliang liuliang_wb@newchina.live
 * @date 2015年1月29日 上午10:10:42
 */
public class AgentWithJob extends AgentConfig {
    private String jobId;
    // 执行区间
    private long startNum;
    private long endNum;
    private String isSplit;
    private String jobRunId;
    private String jobChainId;
    private List<JobParam> params = new ArrayList<JobParam>();
    private int jobThreadLimit;
    private JobSessionContext jsContext;

    // Map<String,Object> params = new HashMap<String, Object>();

    /**
     * @return jsContext
     */
    public JobSessionContext getJsContext() {
        return jsContext;
    }

    /**
     * @param jsContext 要设置的 jsContext
     */
    public void setJsContext(JobSessionContext jsContext) {
        this.jsContext = jsContext;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public long getStartNum() {
        return startNum;
    }

    public void setStartNum(long startNum) {
        this.startNum = startNum;
    }

    public long getEndNum() {
        return endNum;
    }

    public void setEndNum(long endNum) {
        this.endNum = endNum;
    }

    // public Map<String, Object> getParams() {
    // return params;
    // }
    //
    // public void setParams(Map<String, Object> params) {
    // this.params = params;
    // }

    public String getIsSplit() {
        return isSplit;
    }

    public void setIsSplit(String isSplit) {
        this.isSplit = isSplit;
    }

    public String getJobRunId() {
        return jobRunId;
    }

    public void setJobRunId(String jobRunId) {
        this.jobRunId = jobRunId;
    }

    public void setParams(List<JobParam> params) {
        this.params = params;
    }

    public List<JobParam> getParams() {
        return params;
    }

    public int getJobThreadLimit() {
        return jobThreadLimit;
    }

    public void setJobThreadLimit(int jobThreadLimit) {
        this.jobThreadLimit = jobThreadLimit;
    }

    public String getJobChainId() {
        return jobChainId;
    }

    public void setJobChainId(String jobChainId) {
        this.jobChainId = jobChainId;
    }
}
