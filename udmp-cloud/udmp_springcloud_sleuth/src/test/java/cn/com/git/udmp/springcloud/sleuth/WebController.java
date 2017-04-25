package cn.com.git.udmp.springcloud.sleuth;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.DefaultSpanNamer;
import org.springframework.cloud.sleuth.SpanNamer;
import org.springframework.cloud.sleuth.TraceKeys;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.git.udmp.core.logging.ILog;
import de.koizumi.sleuth.annotation.CreateSleuthSpan;
import de.koizumi.sleuth.annotation.SleuthSpanTag;

@SpringBootApplication
@ComponentScan(basePackages={"cn.com.git","de.koizumi"})
@RestController
public class WebController implements ILog{
    @Autowired
    TestService testService;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String MsACtr() throws Exception{
        
        String result = "";
        TraceKeys traceKeys = new TraceKeys();
        SpanNamer spanNamer = new DefaultSpanNamer();
        ExecutorService executor = Executors.newFixedThreadPool(3);

//        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(
//                () -> wshService.toMsB(), 
//                new TraceableExecutorService(executor,this.tracer, traceKeys, spanNamer, "calculateTax-1")).thenCombine(CompletableFuture.supplyAsync(
//                () -> wshService.toMsC(),
//                new TraceableExecutorService(executor,this.tracer, traceKeys, spanNamer, "calculateTax-2")
//       ), (r1, r2) -> "{sb=>"+r1+",sc=>"+r2+"}");
//
//        result = completableFuture.get();

        logger.info(result);
        testService.test(123L);
        return result;
    }
    
//    @CreateSleuthSpan(name = "psql/findProductsByTenantAndAmount")
//    public void test(@SleuthSpanTag(value = "tenantId", tagValueExpression = "param + 1")Long tenantId){
//        System.out.println("test");
//    }
    
    
    public static void main(String[] args) {
        SpringApplication.run(WebController.class, args);
      }
}
