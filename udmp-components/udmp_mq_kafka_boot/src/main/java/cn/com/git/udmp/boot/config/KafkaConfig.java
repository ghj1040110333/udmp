

package cn.com.git.udmp.boot.config;

import java.util.Collections;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.kafka.core.BrokerAddress;
import org.springframework.integration.kafka.core.BrokerAddressListConfiguration;
import org.springframework.integration.kafka.core.Configuration;
import org.springframework.integration.kafka.core.ConnectionFactory;
import org.springframework.integration.kafka.core.DefaultConnectionFactory;
import org.springframework.integration.kafka.core.Partition;
import org.springframework.integration.kafka.inbound.KafkaMessageDrivenChannelAdapter;
import org.springframework.integration.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.integration.kafka.listener.KafkaTopicOffsetManager;
import org.springframework.integration.kafka.listener.OffsetManager;
import org.springframework.integration.kafka.outbound.KafkaProducerMessageHandler;
import org.springframework.integration.kafka.serializer.common.StringDecoder;
import org.springframework.integration.kafka.support.KafkaProducerContext;
import org.springframework.integration.kafka.support.ProducerConfiguration;
import org.springframework.integration.kafka.support.ProducerFactoryBean;
import org.springframework.integration.kafka.support.ProducerMetadata;
import org.springframework.integration.kafka.support.ZookeeperConnect;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.PollableChannel;

import cn.com.git.udmp.core.logging.ILog;

/** 
 * 集成Kafka配置
 * @description 
 *  读取resources/application.yml配置内容
 *  使用"@Bean"来注册Spring的Bean
 *  配置Kafka的Factory及ProduceContext等Properties
 * @author Spring Cao
 * @date 2016年10月25日 上午9:50:44  
*/
@SpringBootApplication
@ComponentScan(basePackages={"cn.com.git.udmp.mq.kafka"})
public class KafkaConfig  implements CommandLineRunner,ILog{
    
    /** Topic */
    @Value("${kafka.topic}")
    private String topic;

    /** 消息主键 */
    @Value("${kafka.messageKey}")
    private String messageKey;

    /** Kafka代理地址 */
    @Value("${kafka.broker.address}")
    private String brokerAddress;

    /** Zookeeper的地址 */
    @Value("${kafka.zookeeper.connect}")
    private String zookeeperConnect;
    
    @Override
    public void run(String... arg0) throws Exception {
        logger.info("kafka Runtime Scaning ！");
    }
    
    @ServiceActivator(inputChannel = "toKafka")
    @Bean
    public MessageHandler handler() throws Exception {
        KafkaProducerMessageHandler handler = new KafkaProducerMessageHandler(producerContext());
        handler.setTopicExpression(new LiteralExpression(this.topic));
        handler.setMessageKeyExpression(new LiteralExpression(this.messageKey));
        return handler;
    }
    
    @Bean
    public ConnectionFactory kafkaBrokerConnectionFactory() throws Exception {
        return new DefaultConnectionFactory(kafkaConfiguration());
    }

    @Bean
    public Configuration kafkaConfiguration() {
        String[] split = this.brokerAddress.split(",");
        
        int i = 0;
        BrokerAddress[] brokerAddressList = new BrokerAddress[split.length];
        while(i < split.length){
            brokerAddressList[i] = BrokerAddress.fromAddress(split[i]);
            i++;
        }
        
        BrokerAddressListConfiguration configuration = new BrokerAddressListConfiguration(brokerAddressList);
        configuration.setSocketTimeout(500);
        return configuration;
    }

    @Bean
    public KafkaProducerContext producerContext() throws Exception {
        KafkaProducerContext kafkaProducerContext = new KafkaProducerContext();
        ProducerMetadata<String, String> producerMetadata = 
                new ProducerMetadata<>(this.topic, String.class,String.class, 
                        new StringSerializer(),
                        new StringSerializer());
        Properties props = new Properties();
        props.put(ProducerConfig.LINGER_MS_CONFIG, "1000");
        ProducerFactoryBean<String, String> producer =
                new ProducerFactoryBean<>(producerMetadata, this.brokerAddress, props);
        ProducerConfiguration<String, String> config =
                new ProducerConfiguration<>(producerMetadata, producer.getObject());
        Map<String, ProducerConfiguration<?, ?>> producerConfigurationMap =
                Collections.<String, ProducerConfiguration<?, ?>>singletonMap(this.topic, config);
        kafkaProducerContext.setProducerConfigurations(producerConfigurationMap);
        return kafkaProducerContext;
    }

    @Bean
    public OffsetManager offsetManager() {
        return new KafkaTopicOffsetManager(new ZookeeperConnect(this.zookeeperConnect), "si-offsets");
    }

    @Bean
    public KafkaMessageListenerContainer container(OffsetManager offsetManager) throws Exception {
        final KafkaMessageListenerContainer kafkaMessageListenerContainer = 
                new KafkaMessageListenerContainer(kafkaBrokerConnectionFactory(), 
                        new Partition(this.topic, 0));
        kafkaMessageListenerContainer.setOffsetManager(offsetManager);
        kafkaMessageListenerContainer.setMaxFetch(100);
        kafkaMessageListenerContainer.setConcurrency(1);
        return kafkaMessageListenerContainer;
    }

    @Bean
    public KafkaMessageDrivenChannelAdapter adapter(KafkaMessageListenerContainer container) {
        KafkaMessageDrivenChannelAdapter kafkaMessageDrivenChannelAdapter =
                new KafkaMessageDrivenChannelAdapter(container);
        StringDecoder decoder = new StringDecoder();
        kafkaMessageDrivenChannelAdapter.setKeyDecoder(decoder);
        kafkaMessageDrivenChannelAdapter.setPayloadDecoder(decoder);
        kafkaMessageDrivenChannelAdapter.setOutputChannel(fromKafka());
        return kafkaMessageDrivenChannelAdapter;
    }

    @Bean
    public PollableChannel fromKafka() {
        return new QueueChannel();
    }
    
    @Bean
    public TopicCreator topicCreator() {
        return new TopicCreator(this.topic, this.zookeeperConnect);
    }
}
