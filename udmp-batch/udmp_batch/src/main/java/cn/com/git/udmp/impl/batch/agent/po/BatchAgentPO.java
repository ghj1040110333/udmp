package cn.com.git.udmp.impl.batch.agent.po;


import java.math.BigDecimal;

import cn.com.git.udmp.common.model.DataObject;

/**
 * @description BatchAgentPO对象
 * @author yangfeiit@newchinalife.com
 * @date 2015-02-02 17:07:20
 */
public class BatchAgentPO extends DataObject {
    private static final long serialVersionUID = 1L;

    /** 属性 --- java类型 --- oracle类型_数据长度_小数位长度_注释信息 */
    // agentId --- BigDecimal --- NUMBER_16_0_执行代理ID;
    // agentName --- String --- VARCHAR2_200_0_执行代理名称;
    // agentSystem --- String --- VARCHAR2_100_0_所属系统;
    // agentIp --- String --- VARCHAR2_15_0_执行代理IP;    
    // agentPort --- BigDecimal --- NUMBER_6_0_执行代理端口;
    // isEnable --- String --- CHAR_1_0_是否启用标记。值域：是-1/否-0默认值：否;
    // agentThreadLimitCnt --- BigDecimal --- NUMBER_4_0_线程阀值，默认值1;
    // isDeleted --- String --- CHAR_1_0_是否删除。值域：是-1/否-0默认值：否;
    // ver --- String --- VARCHAR2_11_0_预留版本号。默认值：1.0.0;
    
    public void setAgentName(String agentName) {
        setString("agent_name", agentName);
    }

    public String getAgentName() {
        return getString("agent_name");
    }

    public void setIsEnable(String isEnable) {
        setString("is_enable", isEnable);
    }

    public String getIsEnable() {
        return getString("is_enable");
    }

    public void setAgentThreadLimitCnt(BigDecimal agentThreadLimitCnt) {
        setBigDecimal("agent_thread_limit_cnt", agentThreadLimitCnt);
    }

    public BigDecimal getAgentThreadLimitCnt() {
        return getBigDecimal("agent_thread_limit_cnt");
    }

    public void setAgentIp(String agentIp) {
        setString("agent_ip", agentIp);
    }

    public String getAgentIp() {
        return getString("agent_ip");
    }

    public void setIsDeleted(String isDeleted) {
        setString("is_deleted", isDeleted);
    }

    public String getIsDeleted() {
        return getString("is_deleted");
    }

    public void setAgentPort(BigDecimal agentPort) {
        setBigDecimal("agent_port", agentPort);
    }

    public BigDecimal getAgentPort() {
        return getBigDecimal("agent_port");
    }

    public void setAgentSystem(String agentSystem) {
        setString("agent_system", agentSystem);
    }

    public String getAgentSystem() {
        return getString("agent_system");
    }

    public void setVer(String ver) {
        setString("ver", ver);
    }

    public String getVer() {
        return getString("ver");
    }

    public void setAgentId(BigDecimal agentId) {
        setBigDecimal("agent_id", agentId);
    }

    public BigDecimal getAgentId() {
        return getBigDecimal("agent_id");
    }

    @Override
    public String toString() {
        return "BatchAgentPO [" + "agentName=" + getAgentName() + "," + "isEnable=" + getIsEnable() + ","
                + "agentThreadLimitCnt=" + getAgentThreadLimitCnt() + "," + "agentIp=" + getAgentIp() + ","
                + "isDeleted=" + getIsDeleted() + "," + "agentPort=" + getAgentPort() + "," + "agentSystem="
                + getAgentSystem() + "," + "ver=" + getVer() + "," + "agentId=" + getAgentId() + "]";
    }
}
