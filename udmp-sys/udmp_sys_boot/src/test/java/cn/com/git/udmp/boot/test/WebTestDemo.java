///**
// * GIT Confidential
// * Licensed Materials - Property of GIT
// * Copyright (c) 1998-2015 Global InfoTech Corp. All Rights Reserved.
// * Global InfoTech, Inc. owns the copyright and other intellectual
// * property rights in this software.
// *
// * The source code for this program is not published.
// * Duplication or use of the Software is not permitted
// * except as expressly provided in a written agreement
// * between your company and Global InfoTech, Inc.
// */
//
//package cn.com.git.udmp.boot.test;
//
//import static org.assertj.core.api.Assertions.*;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.test.context.junit4.SpringRunner;
//
///**
// * spring-boot测试demo
// * @author liang 2016年8月23日
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//public class WebTestDemo {
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Test
//    public void exampleTest() {
//        String body = this.restTemplate.getForObject("/", String.class);
//        assertThat(body).isEqualTo("Hello World");
//    }
//}
