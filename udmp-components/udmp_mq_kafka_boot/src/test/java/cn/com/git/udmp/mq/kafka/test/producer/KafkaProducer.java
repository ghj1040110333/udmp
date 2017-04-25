

package cn.com.git.udmp.mq.kafka.test.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

import cn.com.git.udmp.mq.MqSend;
/** 
 * @description 
 * @author Spring Cao
 * @date 2016年10月25日 上午11:15:27  
*/
@Service
public class KafkaProducer implements MqSend{

    @Autowired
    @Qualifier("toKafka")
    public MessageChannel toKafka;
    
    @Override
    public void send(String message) {
        logger.info("KafkaProducer发送消息 {}",message);
        toKafka.send(new GenericMessage<>(message));
    }
    
//    @Override
//    public void send(String message) {
//        logger.info("发送消息 {}",message);
//        org.springframework.messaging.Message<String> mess = MessageBuilder.withPayload(message).build();
//        toKafka.send(mess);
//    }
}
