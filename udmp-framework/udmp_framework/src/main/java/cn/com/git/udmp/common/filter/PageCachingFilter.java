
package cn.com.git.udmp.common.filter;

import org.springframework.cache.ehcache.EhCacheCacheManager;

import com.google.common.base.Optional;

import cn.com.git.udmp.common.utils.CacheUtils;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.constructs.web.filter.SimplePageCachingFilter;

/**
 * 页面高速缓存过滤器
 * 
 * @author Spring Cao
 * @version 2015-8-5
 */
public class PageCachingFilter extends SimplePageCachingFilter {

    @Override
    protected CacheManager getCacheManager() {
        org.springframework.cache.CacheManager cacheManager = CacheUtils.getCacheManager();
        if (cacheManager instanceof EhCacheCacheManager) {
            return ((EhCacheCacheManager) cacheManager).getCacheManager();
        }
        return null;
    }
    

}
