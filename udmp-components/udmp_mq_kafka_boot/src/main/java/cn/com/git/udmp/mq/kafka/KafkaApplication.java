package cn.com.git.udmp.mq.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import cn.com.git.udmp.boot.UdmpApplication;
import cn.com.git.udmp.core.logging.ILog;
import cn.com.git.udmp.mq.MqReceive;
import cn.com.git.udmp.mq.MqReceiveRunnable;
import cn.com.git.udmp.mq.MqSend;
import cn.com.git.udmp.mq.kafka.test.consumer.KafkaConsumer;
import cn.com.git.udmp.mq.kafka.test.producer.KafkaProducer;


/** 
 * Kafka的Spring Boot启动类
 * @description 
 * @author Spring Cao
 * @date 2016年10月12日 下午2:23:00  
*/
@EnableIntegration
@SpringBootApplication
public class KafkaApplication implements ILog{
    /**
     * @title
     *  Application直接运行的main函数
     * @description
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        logger.info("Startup with Application main !");
        SpringApplication.run(UdmpApplication.class, args);
    }
}
