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

package cn.com.git.udmp.test.modules.sys.utils;

import org.junit.Test;

import cn.com.git.udmp.common.utils.testsupport.AbstractTest;
import cn.com.git.udmp.modules.sys.entity.User;
import cn.com.git.udmp.modules.sys.utils.UserUtils;

/**
 * @description 用户工具测试类
 * @author liang
 *
 */
public class UserUtilsTest extends AbstractTest{
    @Test
    public void getUser(){
        User user = UserUtils.get("11");
        System.out.println(user.getOffice().getCode());
    }
}