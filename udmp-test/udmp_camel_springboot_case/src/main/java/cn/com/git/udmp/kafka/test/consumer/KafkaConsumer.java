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
//import org.springframework.stereotype.Service;
//
//import cn.com.git.udmp.mq.MqReceive;
//
///** 
// * @description 
// * @author Spring Cao
// * @date 2016年10月25日 上午11:13:30  
//*/
//@Service
//public class KafkaConsumer implements MqReceive{
//    
//    @Autowired
//    @Qualifier("fromKafka")//不要修改此名称，此名称配置在udmp_mq_kafka_boot工程的KafkaConfig类中
//    public PollableChannel pollableChannel;
//    
//    Message<?> received = null;
//    
//    @Override
//    public void receive() {
//        Message<?> received = null;
//        int i = 1;
//        while(true){
//            received = pollableChannel.receive(1000);
//            System.out.println("KafkaConsumer receive payload : "+received);
//            if(i++>1000)
//                break;
//        }
//    }
//
////    @Override
////    public void run() {
////        logger.info("开始接收数据.....");
////        int i = 1;
////        while (true) {
////            receive();
////            if(i++>1000)
////                break;
////        }
////    }
//}
