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
//package cn.com.git.udmp.kafka.test.producer;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.messaging.MessageChannel;
//import org.springframework.messaging.support.GenericMessage;
//import org.springframework.stereotype.Service;
//
//import cn.com.git.udmp.mq.MqSend;
///** 
// * @description 
// * @author Spring Cao
// * @date 2016年10月25日 上午11:15:27  
//*/
//@Service
//public class KafkaProducer implements MqSend{
//
//    @Autowired
//    @Qualifier("toKafka")//不要修改此名称，此名称配置在udmp_mq_kafka_boot工程的KafkaConfig类中
//    public MessageChannel toKafka;
//    
//    @Override
//    public void send(String message) {
//        logger.info("KafkaProducer发送消息 {}",message);
//        toKafka.send(new GenericMessage<>(message));
//    }
//    
////    @Override
////    public void send(String message) {
////        logger.info("发送消息 {}",message);
////        org.springframework.messaging.Message<String> mess = MessageBuilder.withPayload(message).build();
////        toKafka.send(mess);
////    }
//}
