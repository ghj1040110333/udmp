

package cn.com.git.udmp.common.persistence.entity;

import java.util.Date;

import cn.com.git.udmp.core.base.model.SessionEntity;

/** 
 * 抽象只读会话对象
 * @description 默认的只读型抽象Session实现 
 * @author Spring Cao
 * @date 2016年8月23日 上午10:12:24  
*/
public class DefaultSessionEntity implements SessionEntity{
    
    private String id;
    private Date startTimestamp;
    private Date lastAccessTime;
    private long timeout;
    private String host;
    
    private static SessionEntity session = null;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Date getStartTimestamp() {
        return startTimestamp;
    }
    public void setStartTimestamp(Date startTimestamp) {
        this.startTimestamp = startTimestamp;
    }
    public Date getLastAccessTime() {
        return lastAccessTime;
    }
    public void setLastAccessTime(Date lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }
    public long getTimeout() {
        return timeout;
    }
    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }
    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }
}
