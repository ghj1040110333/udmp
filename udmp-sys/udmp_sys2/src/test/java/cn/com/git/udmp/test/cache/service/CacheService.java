

package cn.com.git.udmp.test.cache.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author liang
 *
 */
@Service
public class CacheService {
    @Cacheable(value="sysCache",key="1")
    public String cache(){
        return "this is mine cache";
        
    }

    /**
     * @return
     */
    @Cacheable(value="sysCache",key="1")
    public String cache1() {
        return "this is cache1";
    }
}
