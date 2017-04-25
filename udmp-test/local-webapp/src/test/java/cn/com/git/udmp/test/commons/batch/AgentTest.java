/**
 * GIT Confidential
 * Licensed Materials - Property of GIT
 * Copyright (c) 1998-2015 Global InfoTech Corp. All Rights Reserved.
 * Global InfoTech, Inc. owns the copyright and other intellectual
 * property rights in this software.
 *
 * The source code for this program is not published.
 * Duplication or use of the Software is not permitted
 * except as expressly provided in a written agreement
 * between your company and Global InfoTech, Inc.
 */

package cn.com.git.udmp.test.commons.batch;

import org.junit.Test;

import cn.com.git.udmp.common.utils.testsupport.AbstractTest;
import cn.com.git.udmp.component.batch.core.agent.communicate.AgentSocketServer;

/**
 * @description 执行代理的测试类
 * @author liang
 *
 */
public class AgentTest extends AbstractTest {
    
    @Test
    public void agentInit(){
        try{
            AgentSocketServer agent = (AgentSocketServer) this.getBean("agentSocketServer");
            agent.accept("9999");
            Thread.sleep(1000000);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
