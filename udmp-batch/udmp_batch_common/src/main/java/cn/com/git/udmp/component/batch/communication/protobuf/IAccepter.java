package cn.com.git.udmp.component.batch.communication.protobuf;

import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message;
import cn.com.git.udmp.core.logging.ILog;

/**
 * @description 通信响应接口
 * @author liuliang 
 * @date 2015年4月13日 上午9:19:16
 */
public interface IAccepter extends ILog{

    /**
     * @description 接收信息
     * @param port 端口号
     * @return 接收到的信息
     */
    public Message accept(String port);

    /**
     * @description 关闭接收
     */
    public void stopAccepter();
}
