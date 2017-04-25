package cn.com.git.udmp.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/** 
 * @description swargger配置类
 * @author Liang liuliang1@git.com.cn 
 * @date 2017年2月24日 下午2:29:06  
*/
@Configuration
@EnableSwagger2
@SpringBootApplication
public class SwaggerConfiguration{
    public static void main(String[] args) {
        SpringApplication.run(SwaggerConfiguration.class, args);
    }

//    @Bean
//    public Docket testApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("test")
//                .genericModelSubstitutes(DeferredResult.class)
//                //.genericModelSubstitutes(ResponseEntity.class)
//                .useDefaultResponseMessages(false)
//                .forCodeGeneration(true)
//                .pathMapping("/test")//api测试请求地址
//                .select()
//                .paths(PathSelectors.regex("/common/.*"))//过滤的接口
//                .build()
//                .apiInfo(testApiInfo());
//    }


    @Bean
    public Docket udmpApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("udmp")
                .genericModelSubstitutes(DeferredResult.class)
                //  .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(false)
                .pathMapping("/")
                .select()
//                .paths(PathSelectors.regex("/comm.*"))//过滤的接口
                .build()
                .apiInfo(udmpApiInfo());
    }

//    private ApiInfo testApiInfo() {
//        Contact contact = new Contact("高伟达公司研发中心", "http://10.210.33.56:8090/display/UDMPDOC/UDMP-DOC", "udmp@git.com.cn");
//        ApiInfo apiInfo = new ApiInfo("测试API接口",//大标题
//                "REST风格API",//小标题
//                "0.1",//版本
//                "www.git.com.cn",
//                contact,//作者
//                "主页",//链接显示文字
//                "http://www.git.com.cn"//网站链接
//        );
//        return apiInfo;
//    }

    private ApiInfo udmpApiInfo() {
        Contact contact = new Contact("高伟达公司研发中心", "http://10.210.33.56:8090/display/UDMPDOC/UDMP-DOC", "udmp@git.com.cn");
        ApiInfo apiInfo = new ApiInfo("全工程API接口",//大标题
                "REST风格API",//小标题
                "0.1",//版本
                "www.git.com.cn",
                contact,//作者
                "主页",//链接显示文字
                "http://www.git.com.cn"//网站链接
        );
        return apiInfo;
    }
}
