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

package cn.com.git.udmp.test.modules.sys.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.git.udmp.common.utils.testsupport.AbstractTest;
import cn.com.git.udmp.modules.sys.entity.Office;
import cn.com.git.udmp.modules.sys.entity.User;
import cn.com.git.udmp.modules.sys.service.SystemService;

/**
 * @author liang
 *
 */
public class SystemServiceTest extends AbstractTest{
    
    @Autowired
    SystemService service;
    
    @Test
    public void testFindOwnList(){
        User user = new User();
        Office office = new Office();
        office.setId("3");
        user.setOffice(office);
//        SystemService service = (SystemService) this.getBean("systemService");
        List<User> list = service.getOwnUserList(user);
        System.out.println(list.size());
    }
}
