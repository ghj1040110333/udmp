package cn.com.git.udmp.test.jms.service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.stereotype.Component;

/**
 * 注解版消费者
 * @author liang
 * 2016年6月22日
 */
@Component
public class AnnotationConsumerListener extends MessageListenerAdapter{

    @Autowired
    JmsTemplate template;


    @JmsListener(destination = "queue")
    public void processOrder(Message message,Session session) {
        try {
            String bean = (String) getMessageConverter().fromMessage(message);
            System.out.println(bean);
            System.out.println(session);
            message.acknowledge();
            message.acknowledge();
          } catch (MessageConversionException e) {
            e.printStackTrace();
          }catch (JMSException  e) {
              e.printStackTrace();
        }
    }
}
