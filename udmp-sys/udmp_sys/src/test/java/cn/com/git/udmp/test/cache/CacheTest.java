

package cn.com.git.udmp.test.cache;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import cn.com.git.udmp.common.web.IUDMPContext;
import cn.com.git.udmp.test.base.AbstractTest;
import cn.com.git.udmp.test.cache.service.CacheService;

/**
 * @author liang
 *
 */
public class CacheTest extends AbstractTest{
    
    @Autowired
    CacheService service;
    @Autowired
    IUDMPContext udmpContext;
    
    @Test
    public void testCache(){
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>      "+udmpContext.getUser().getName());
        System.out.println(service.cache());//查询到缓存
        System.out.println(service.cache1());//查询缓存
    }

    public CacheService getService() {
        return service;
    }

    public void setService(CacheService service) {
        this.service = service;
    }
}
