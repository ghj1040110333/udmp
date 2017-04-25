

package cn.com.git.udmp.mq.kafka.test.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.PollableChannel;
import org.springframework.stereotype.Component;

import cn.com.git.udmp.mq.MqReceiveRunnable;

/** 
 * 异步线程实现Kafka的消息接收
 * @description 
 * @author Spring Cao
 * @date 2016年10月27日 上午10:50:25  
*/
@Component
public class KafkaConsumerRunnable implements MqReceiveRunnable{
    public PollableChannel pollableChannel;

    @Autowired
    public KafkaConsumerRunnable(@Qualifier("fromKafka") PollableChannel pollableChannel) {
        this.pollableChannel = pollableChannel;
    }
    
    @Override
    public void run() {
        while(true) {
            receive();
        }
    }

    @Override
    public void receive() {
        Message<?> message = pollableChannel.receive();
//        Optional.of(message).get().getPayload(); //容错，对象是否为空值，
        logger.info(String.format("ConsumeWithRunnable 接收数据 : %s", message.getPayload()));
    }
}
