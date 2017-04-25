

package com.liang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author liang 2016年8月24日
 */
@SpringBootApplication
@EnableWebMvc
public class SampleWebFreeMarkerApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SampleWebFreeMarkerApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleWebFreeMarkerApplication.class, args);
    }

}