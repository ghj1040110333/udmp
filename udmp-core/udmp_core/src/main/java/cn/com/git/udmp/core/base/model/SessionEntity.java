package cn.com.git.udmp.core.base.model;

import java.io.Serializable;
import java.util.Date;

import cn.com.git.udmp.common.exception.FrameworkException;
/**
 * Session对象抽象接口
 * @description UDMP的Session对象接口，需要由抽象实现类来实现一些没别要的方法，例如:stop、touch、set*等
 * @author Spring Cao
 * @date 2016年8月23日 上午10:01:19
 */
public interface SessionEntity extends Serializable{
    /**
     * 
     * @title 获得Session对象ID
     * @description 返回SessionID
     * 
     * @return String SessionID
     */
    String getId();
    
    public void setId(String id);
    
    /**
     * 
     * @title 获得SessionTimestamp
     * @description 获得Session创建时的时间戳
     * 
     * @return util.Date 开始时间戳
     */
    Date getStartTimestamp();
    public void setStartTimestamp(Date startTimestamp);
    /**
     * 
     * @title 获得LastAccessTime
     * @description 获得最后访问时间戳
     * 
     * @return util.Date 最后访问时间戳
     */
    Date getLastAccessTime();
    public void setLastAccessTime(Date lastAccessTime);
    /**
     * 
     * @title 获得Timeout
     * @description 获得超时时间
     * 
     * @return long 超时时间
     * @throws FrameworkException
     */
    long getTimeout() throws FrameworkException;
    /**
     * 
     * @title 获得Host
     * @description 获得Session所在Host信息
     * 
     * @return String Host信息(IP)
     */
    String getHost();
    public void setHost(String host);
    /**
     * 
     * @title 设置Timeout
     * @description 设置超时时间，抽象实现为空实现
     * 
     * @param maxIdleTimeInMillis long型值
     * @throws FrameworkException
     */
    public void setTimeout(long maxIdleTimeInMillis) throws FrameworkException;
  
}
