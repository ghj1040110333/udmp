

package cn.com.git.udmp.boot.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import cn.com.git.udmp.core.logging.ILog;

/** 
 * @description 
 * @author Spring Cao
 * @date 2016年10月12日 下午2:55:41  
*/
@SpringBootApplication
@ComponentScan(basePackages={"cn.com.git.udmp.cloud.eureka"})
public class EurekaHAConfig implements CommandLineRunner,ILog{
    @Override
    public void run(String... arg0) throws Exception {
        logger.info("Eureka HA Runtime Server Scaning ！");
    }
}

