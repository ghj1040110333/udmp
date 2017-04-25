/**
 * GIT Confidential
 * Licensed Materials - Property of GIT
 * Copyright (c) 1998-2016 Global InfoTech Corp. All Rights Reserved.
 * Global InfoTech, Inc. owns the copyright and other intellectual
 * property rights in this software.
 *
 * The source code for this program is not published.
 * Duplication or use of the Software is not permitted
 * except as expressly provided in a written agreement
 * between your company and Global InfoTech, Inc.
 */

package cn.com.git.udmp.test.builder;

/** 
 * @description 
 * @author Spring Cao 
 * @date 2016年8月26日 下午2:43:35  
*/
public class TestMain {
    public static void main(String a[]){
        String str = DefaultDataBindBuilder.builder().name("Spring").age(111).address("Beijing").echo();
        System.out.println(">>>>>>>> "+str);
    }
}
