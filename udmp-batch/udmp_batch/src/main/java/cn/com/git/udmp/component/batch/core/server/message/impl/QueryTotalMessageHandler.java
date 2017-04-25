package cn.com.git.udmp.component.batch.core.server.message.impl;

import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message;
import cn.com.git.udmp.component.batch.core.server.message.IMessageHandler;

/**
 * @description 查询总数返回信息的业务处理类
 * @author liuliang liuliang_wb@newchina.live
 * @date 2015年2月9日 上午10:49:14
 */
public class QueryTotalMessageHandler implements IMessageHandler {

    @Override
    public void process(Message message) {
        logger.debug("接受到的任务{}返回的总数是:{}");
    }

}
