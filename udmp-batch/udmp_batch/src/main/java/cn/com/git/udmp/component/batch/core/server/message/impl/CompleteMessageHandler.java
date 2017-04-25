//package cn.com.git.udmp.component.batch.core.server.message.impl;
//
//import cn.com.git.udmp.component.batch.common.constants.StatusEnum;
//import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message;
//import cn.com.git.udmp.component.batch.core.server.manage.JobStatusManager;
//import cn.com.git.udmp.component.batch.core.server.message.IMessageHandler;
//
///**
// * @description 业务完成的业务处理类
// * @author liuliang liuliang_wb@newchina.live
// * @date 2015年2月9日 上午10:48:35
// */
//public class CompleteMessageHandler implements IMessageHandler {
//
//    @Override
//    public void process(Message message) {
//        // TODO Auto-generated method stub
//        logger.debug("准备完成任务{}", message.getJob().getBasicInfo().getId());
//        JobStatusManager.setJobStatus(message.getJob().getBasicInfo().getId(), StatusEnum.SUCCESS);
//    }
//
//}
