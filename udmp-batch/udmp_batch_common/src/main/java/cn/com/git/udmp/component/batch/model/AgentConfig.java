package cn.com.git.udmp.component.batch.model;

/**
 * @description agent端的配置类
 * @author liuliang liuliang_wb@newchina.live
 * @date 2015年1月29日 上午10:06:51
 */
public class AgentConfig {
    private String agentId;
    private String agentName;
    private String agentSystem;
    private String agentIp;
    private int agentPort;
    private String isEnable;
    private int agentThreadLimitCnt;

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentSystem() {
        return agentSystem;
    }

    public void setAgentSystem(String agentSystem) {
        this.agentSystem = agentSystem;
    }

    public String getAgentIp() {
        return agentIp;
    }

    public void setAgentIp(String agentIp) {
        this.agentIp = agentIp;
    }

    public int getAgentPort() {
        return agentPort;
    }

    public void setAgentPort(int agentPort) {
        this.agentPort = agentPort;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    public int getAgentThreadLimitCnt() {
        return agentThreadLimitCnt;
    }

    public void setAgentThreadLimitCnt(int agentThreadLimitCnt) {
        this.agentThreadLimitCnt = agentThreadLimitCnt;
    }
}
