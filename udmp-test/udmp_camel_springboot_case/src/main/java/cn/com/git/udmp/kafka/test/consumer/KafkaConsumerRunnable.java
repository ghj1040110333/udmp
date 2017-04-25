///**
// * GIT Confidential
// * Licensed Materials - Property of GIT
// * Copyright (c) 1998-2016 Global InfoTech Corp. All Rights Reserved.
// * Global InfoTech, Inc. owns the copyright and other intellectual
// * property rights in this software.
// *
// * The source code for this program is not published.
// * Duplication or use of the Software is not permitted
// * except as expressly provided in a written agreement
// * between your company and Global InfoTech, Inc.
// */
//
//package cn.com.git.udmp.kafka.test.consumer;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.PollableChannel;
//import org.springframework.stereotype.Component;
//
//import cn.com.git.udmp.mq.MqReceiveRunnable;
//
///** 
// * 异步线程实现Kafka的消息接收
// * @description 
// * @author Spring Cao
// * @date 2016年10月27日 上午10:50:25  
//*/
//@Component
//public class KafkaConsumerRunnable implements MqReceiveRunnable{
//    public PollableChannel pollableChannel;
//
//    @Autowired //不要修改此名称，此名称配置在udmp_mq_kafka_boot工程的KafkaConfig类中
//    public KafkaConsumerRunnable(@Qualifier("fromKafka") PollableChannel pollableChannel) {
//        this.pollableChannel = pollableChannel;
//    }
//    
//    @Override
//    public void run() {
//        while(true) {
//            receive();
//        }
//    }
//
//    @Override
//    public void receive() {
//        Message<?> message = pollableChannel.receive();
////        Optional.of(message).get().getPayload(); //容错，对象是否为空值，
//        logger.info(String.format("ConsumeWithRunnable : %s", message.getPayload()));
//    }
//}
