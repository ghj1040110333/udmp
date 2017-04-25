package cn.com.git.udmp.component.batch.context;

import cn.com.git.udmp.common.model.DataObject;

/**
 * @description 全局环境变量
 * @author liuliang liuliang_wb@newchina.live
 * @date 2015年1月21日 下午2:13:00
 */
public class Context extends DataObject {
    protected String serverIp;
    protected String serverPort;
    protected String AgentIp;
    protected String AgentPort;

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public String getServerPort() {
        return serverPort;
    }

    public void setServerPort(String serverPort) {
        this.serverPort = serverPort;
    }

    public String getAgentIp() {
        return AgentIp;
    }

    public void setAgentIp(String agentIp) {
        AgentIp = agentIp;
    }

    public String getAgentPort() {
        return AgentPort;
    }

    public void setAgentPort(String agentPort) {
        AgentPort = agentPort;
    }
}
