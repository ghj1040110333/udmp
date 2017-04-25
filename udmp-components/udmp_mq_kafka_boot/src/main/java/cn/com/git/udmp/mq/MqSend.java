

package cn.com.git.udmp.mq;

import cn.com.git.udmp.core.logging.ILog;

/** 
 * @description 
 * @author Spring Cao
 * @date 2016年10月25日 上午11:07:28  
*/
public interface MqSend extends ILog{
    public void send(String message);
}
