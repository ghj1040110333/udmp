package cn.com.git.udmp.springcloud.sleuth;

import org.springframework.stereotype.Service;

import de.koizumi.sleuth.annotation.CreateSleuthSpan;
import de.koizumi.sleuth.annotation.SleuthSpanTag;

@Service    
public class TestService {
    @CreateSleuthSpan(name = "psql/findProductsByTenantAndAmount")
    public void test(@SleuthSpanTag(value = "tenantId", tagValueExpression = "param + 1")Long tenantId){
        System.out.println("test");
    }
}
