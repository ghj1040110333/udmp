package cn.com.git.udmp.component.batch.model;

/**
 * @description agent端的配置类
 * @author liuliang liuliang_wb@newchina.live
 * @date 2015年1月29日 上午10:06:51
 */
public class AgentInfo extends AgentConfig {
    private double cpuLoad;
    private double memoryLoad;
    private int queueLength;
    private int estWaitTime;
    private boolean isActive;

    public double getCpuLoad() {
        return cpuLoad;
    }

    public void setCpuLoad(double cpuLoad) {
        this.cpuLoad = cpuLoad;
    }

    public double getMemoryLoad() {
        return memoryLoad;
    }

    public void setMemoryLoad(double memoryLoad) {
        this.memoryLoad = memoryLoad;
    }

    public int getQueueLength() {
        return queueLength;
    }

    public void setQueueLength(int queueLength) {
        this.queueLength = queueLength;
    }

    public int getEstWaitTime() {
        return estWaitTime;
    }

    public void setEstWaitTime(int estWaitTime) {
        this.estWaitTime = estWaitTime;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
}
