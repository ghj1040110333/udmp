package cn.com.git.udmp.test.batch.framework.service;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.git.udmp.common.utils.testsupport.AbstractTest;
import cn.com.git.udmp.impl.batch.agent.bo.BatchAgentBO;
import cn.com.git.udmp.impl.batch.agent.po.BatchAgentPO;
import cn.com.git.udmp.impl.batch.agent.service.IBatchAgentService;

public class BatchAgentServiceTest extends AbstractTest{
    @Autowired
    private IBatchAgentService service;

    @Test
    public void testAdd(){
        BatchAgentBO bo = new BatchAgentBO();
        bo.setAgentName("123");
        bo.setAgentSystem("udmp");
        bo.setAgentPort(new BigDecimal(8888));
        bo.setAgentThreadLimitCnt(new BigDecimal(1000));
        bo.setAgentIp("192.168.1.1");
        bo.setIsEnable("1");
        service.add(bo);
        service.delete(bo);
    }
    
    public static void main(String[] args) {
        BatchAgentPO po = new BatchAgentPO();
        po.setAgentId(new BigDecimal(123));
        BatchAgentBO bo = new BatchAgentBO();
        System.out.println(po.getData());
        BeanUtils.copyProperties(po, bo);
        System.out.println(bo.getAgentId());
    }
    
    

    public IBatchAgentService getService() {
        return service;
    }

    public void setService(IBatchAgentService service) {
        this.service = service;
    }
}
