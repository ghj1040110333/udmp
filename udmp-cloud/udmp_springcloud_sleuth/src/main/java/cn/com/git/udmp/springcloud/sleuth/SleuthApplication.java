package cn.com.git.udmp.springcloud.sleuth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.git.udmp.core.logging.ILog;
import de.koizumi.sleuth.annotation.CreateSleuthSpan;
import de.koizumi.sleuth.annotation.SleuthSpanTag;

@SpringBootApplication
@RestController
public class SleuthApplication implements ILog{

//    @RequestMapping("/")
//    public String home() {
//      logger.info("Handling home");
//      test(123L);
//      return "Hello World";
//    }
//    
//    @CreateSleuthSpan(name = "psql/findProductsByTenantAndAmount")
//    public void test(@SleuthSpanTag(value = "tenantId", tagValueExpression = "param + 1")Long tenantId){
//        System.out.println("test");
//    }
    
    
    public static void main(String[] args) {
      SpringApplication.run(SleuthApplication.class, args);
    }
}
