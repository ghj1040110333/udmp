package cn.com.git.udmp.impl.batch.agent.bo;

import java.math.BigDecimal;

import cn.com.git.udmp.component.batch.common.base.entity.IBaseBatchBO;
import cn.com.git.udmp.common.model.DataObject;

/**
 * @description BatchAgentBO对象
 * @author anthorName@newchinalife.com
 * @date 2015-02-02 17:00:56
 */
public class BatchAgentBO extends DataObject implements IBaseBatchBO{
    private static final long serialVersionUID = 1L;
    /**
     * @Fields agentName : 执行代理名称
     */
    private String agentName;
    /**
     * @Fields isEnable : 是否启用标记。值域：是-1/否-0 默认值：否
     */
    private String isEnable;
    /**
     * @Fields agentThreadLimitCnt : 线程阀值，默认值1
     */
    private BigDecimal agentThreadLimitCnt;
    /**
     * @Fields agentIp : 执行代理IP
     */
    private String agentIp;
    /**
     * @Fields isDeleted : 是否删除。值域：是-1/否-0 默认值：否
     */
    private String isDeleted;
    /**
     * @Fields agentPort : 执行代理端口
     */
    private BigDecimal agentPort;
    /**
     * @Fields agentSystem : 所属系统
     */
    private String agentSystem;
    /**
     * @Fields ver : 预留版本号。默认值：1.0.0
     */
    private String ver;
    /**
     * @Fields agentId : 代理id
     */
    private BigDecimal agentId;

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setAgentThreadLimitCnt(BigDecimal agentThreadLimitCnt) {
        this.agentThreadLimitCnt = agentThreadLimitCnt;
    }

    public BigDecimal getAgentThreadLimitCnt() {
        return agentThreadLimitCnt;
    }

    public void setAgentIp(String agentIp) {
        this.agentIp = agentIp;
    }

    public String getAgentIp() {
        return agentIp;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setAgentPort(BigDecimal agentPort) {
        this.agentPort = agentPort;
    }

    public BigDecimal getAgentPort() {
        return agentPort;
    }

    public void setAgentSystem(String agentSystem) {
        this.agentSystem = agentSystem;
    }

    public String getAgentSystem() {
        return agentSystem;
    }

    public void setAgentId(BigDecimal agentId) {
        this.agentId = agentId;
    }

    public BigDecimal getAgentId() {
        return agentId;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }


    @Override
    public String toString() {
        return "BatchAgentBO [" + "agentName=" + agentName + "," + "isEnable=" + isEnable + ","
                + "agentThreadLimitCnt=" + agentThreadLimitCnt + "," + "agentIp=" + agentIp + "," + "isDeleted="
                + isDeleted + "," + "agentPort=" + agentPort + "," + "agentSystem=" + agentSystem + "," + "agentId="
                + agentId + "ver=" + ver + "]";
    }

}
