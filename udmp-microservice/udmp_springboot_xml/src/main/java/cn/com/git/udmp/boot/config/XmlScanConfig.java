

package cn.com.git.udmp.boot.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import cn.com.git.udmp.core.logging.ILog;

/** 
 * @description spring配置文件扫描规范配置类
 * @author Liang liuliang1@git.com.cn 
 * @date 2016年9月28日 下午4:24:16  
*/
@SpringBootApplication
@ImportResource(locations = { "classpath*:/META-INF/spring/**/spring-context-*.xml" })
public class XmlScanConfig implements CommandLineRunner,ILog{

    /**
     * @title
     * @description
     *
     * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
     * @param arg0
     * @throws Exception 
    */
    @Override
    public void run(String... arg0) throws Exception {
        logger.info("spring-boot 的xml扫描机制记载了");
    }
    
}
