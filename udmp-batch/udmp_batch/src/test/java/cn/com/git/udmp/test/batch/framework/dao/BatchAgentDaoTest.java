package cn.com.git.udmp.test.batch.framework.dao;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.git.udmp.common.utils.testsupport.AbstractTest;
import cn.com.git.udmp.component.batch.common.base.IBaseDao;
import cn.com.git.udmp.impl.batch.agent.po.BatchAgentPO;

public class BatchAgentDaoTest extends AbstractTest {
    @Autowired
    private IBaseDao<BatchAgentPO> dao;

    public void setDao(IBaseDao<BatchAgentPO> dao) {
        this.dao = dao;
    }

    /**
     * @title
     * @description 测试新增（测试自增主键）
     * 
     */
    @Test
    public void testAdd() {
        System.out.println(dao == null);
        BatchAgentPO po = new BatchAgentPO();
        po.setAgentName("123");
        po.setAgentSystem("udmp");
        po.setAgentPort(new BigDecimal(8888));
        po.setAgentThreadLimitCnt(new BigDecimal(1000));
        po.setAgentIp("192.168.1.1");
        po.setIsEnable("1");
        dao.add(po.getData());
    }

    /**
     * @title
     * @description 测试查询分页（通过查询分页插件自动根据配置自适应分页语句）
     *  
    */
    @Test
    public void testQueryPage() {
        
    }

}
