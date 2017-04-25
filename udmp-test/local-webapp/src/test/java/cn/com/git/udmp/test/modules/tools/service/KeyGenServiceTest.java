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

package cn.com.git.udmp.test.modules.tools.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.git.udmp.common.utils.KeyGenUtils;
import cn.com.git.udmp.common.utils.testsupport.AbstractTest;
import cn.com.git.udmp.modules.tools.dao.KeyGenRuleDao;
import cn.com.git.udmp.modules.tools.dao.KeyGenSeqDao;
import cn.com.git.udmp.modules.tools.entity.KeyGenRule;
import cn.com.git.udmp.modules.tools.entity.KeyGenSeq;
import cn.com.git.udmp.modules.tools.service.KeyGenService;

/**
 * @description 测试业务主键生成规则
 * @author liang
 *
 */
public class KeyGenServiceTest extends AbstractTest {
    @Autowired
    KeyGenService service;
    @Autowired
    private  KeyGenSeqDao dao;
    @Autowired
    private  KeyGenRuleDao ruleDao;
    
    @Test
    public void testGen(){
        KeyGenRule rule = new KeyGenRule();
        rule.setGenId("1");
        rule.setBizId("BJZ");
        rule.setDatePattern("yyyyMMdd");
        rule.setGenRule("#bizId+#date+#seq");
        System.out.println(service.genKeyByRule(rule));
        System.out.println(service.genKeyByRule(rule));
    }

    @Test
    public void test1(){
        KeyGenSeq result = getDao().selectByPrimaryKey("3");
        System.out.println(result==null);
        System.out.println(ruleDao.selectByPrimaryKey("1")==null);
    }
    
    @Test
    public void testGenUtil(){
        System.out.println(KeyGenUtils.getKey("1"));
        System.out.println(KeyGenUtils.getKey("1"));
        System.out.println(KeyGenUtils.getKey("1","123","321"));
        
    }
    
    public KeyGenService getService() {
        return service;
    }

    public void setService(KeyGenService service) {
        this.service = service;
    }

    /**
     * @return dao
     */
    public KeyGenSeqDao getDao() {
        return dao;
    }

    /**
     * @param dao 要设置的 dao
     */
    public void setDao(KeyGenSeqDao dao) {
        this.dao = dao;
    }

    public KeyGenRuleDao getRuleDao() {
        return ruleDao;
    }

    public void setRuleDao(KeyGenRuleDao ruleDao) {
        this.ruleDao = ruleDao;
    }
}
