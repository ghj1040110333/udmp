package cn.com.git.udmp.test.batch.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = { "classpath*:/META-INF/spring/spring-udmp-context*.xml","classpath*:/META-INF/**/spring-context*.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class AbsTransactionNGTest extends AbstractTransactionalJUnit4SpringContextTests{
    @Test
    public void test1(){
        System.out.println(1);
    }
}
