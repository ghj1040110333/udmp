package cn.com.git.udmp.test.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.git.udmp.common.utils.testsupport.AbstractTest;
import cn.com.git.udmp.test.cache.service.CacheService;

/**
 *  使用redis缓存的测试用例
 * @author liang
 * 2016年6月13日
 */
//@ContextConfiguration(locations = { "classpath*:/META-INF/spring/spring-udmp-context*.xml","classpath*:/META-INF/**/spring-context*.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisCacheTest extends AbstractTest{
    
    @Autowired
    CacheService cacheService;
    
    /**
     * 测试
     */
    @Test
    public void test1(){
        System.out.println("123");
    }
    
    
    @Test
    public void testCache(){
        System.out.println(cacheService.cache());//查询到缓存
        System.out.println(cacheService.cache1());//查询缓存
    }
}
