package cn.com.git.udmp.component.batch.core.server.message;

import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message;
import cn.com.git.udmp.core.logging.ILog;

/**
 * @description 回执信息的处理器
 * @author liuliang 
 * @date 2015年2月9日 上午10:40:57
 */
public interface IMessageHandler extends ILog{

    /**
     * @description 消息处理接口
     * @version 1.0
     * @title 消息处理接口
     * @author bihb bihb_wb@newchinalife.com
     * @param message
     *            protobuf定义的批处理server-agent交互消息协议格式
     */
    public void process(Message message);
}
