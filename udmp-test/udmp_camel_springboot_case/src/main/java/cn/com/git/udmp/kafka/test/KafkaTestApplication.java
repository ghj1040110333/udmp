//package cn.com.git.udmp.kafka.test;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.integration.config.EnableIntegration;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//
//import cn.com.git.udmp.boot.UdmpApplication;
//import cn.com.git.udmp.core.logging.ILog;
//import cn.com.git.udmp.kafka.test.consumer.KafkaConsumer;
//import cn.com.git.udmp.kafka.test.producer.KafkaProducer;
//import cn.com.git.udmp.mq.MqReceive;
//import cn.com.git.udmp.mq.MqReceiveRunnable;
//import cn.com.git.udmp.mq.MqSend;
//
///**
// * 测试UDMP_Kafka包的功能
// * @description 
// * @author Spring Cao
// * @date 2016年10月27日 下午3:56:52
// */
//@EnableIntegration
//@SpringBootApplication
//public class KafkaTestApplication implements ILog,CommandLineRunner {
//    
//    @Autowired
//    ConfigurableApplicationContext context;
//    
//    @Autowired
//    private MqReceiveRunnable kafkaConsumerRunnable;
//    
//    /**
//     * @title
//     *  spring-boot:run所调用的方法
//     * @description
//     *  CommandLineRunner的实现方法
//     * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
//     * @param arg0
//     * @throws Exception
//     */
//    @Override
//    public void run(String... arg0) throws Exception {
//        logger.info("Startup with CommandLineRunner !");
//        execWithContext(context);
//    }
//    
//    /**
//     * @title
//     *  Application直接运行的main函数
//     * @description
//     * 
//     * @param args
//     * @throws Exception
//     */
//    public static void main(String[] args) throws Exception {
//        logger.info("Startup with Application main !");
//        new SpringApplicationBuilder(UdmpApplication.class).web(false).run(args);
//    }
//    
//    /**
//     * @title
//     *  执行封装
//     * @description
//     * 
//     * @param context
//     */
//    private void execWithContext(ConfigurableApplicationContext context){
////        objs(context);
//        objsWithRunnable(context);
////        context.close();
////        System.exit(0);
//    }
//       
//    /**
//     * 
//     * @title 默认开发方式
//     * @description
//     * 
//     * @param context
//     */
//    public static void objs(ConfigurableApplicationContext context){
//        MqSend toKafka = context.getBean("kafkaProducer", KafkaProducer.class);
//        System.out.println("正在发送数据....");
//        for (int i = 0; i < 100; i++) {
//            toKafka.send("test4" + i);
//        }
//        
//        System.out.println("正在接收数据....");
//        MqReceive fromKafka = context.getBean("kafkaConsumer", KafkaConsumer.class);
//        fromKafka.receive();
//    }
//    
//    public void objsWithRunnable(ConfigurableApplicationContext context){
//        System.out.println("正在接收数据....");
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.createThread(kafkaConsumerRunnable).start();
//        
//        MqSend toKafka = context.getBean("kafkaProducer", KafkaProducer.class);
//        System.out.println("正在发送数据....");
//        for (int i = 0; i < 100; i++) {
//            toKafka.send("test4" + i);
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
