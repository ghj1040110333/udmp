

package cn.com.git.udmp.common.utils;


import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;

import com.google.common.base.Optional;


/**
 * Cache工具类
 * @author Spring Cao
 * @updatedBy L.liang on 206/11/3 使用spring cache抽象来操作缓存
 * @version 2013-5-29
 */
public class CacheUtils {
	
	/** 
	* @Fields cacheManager : 获取具体的缓存Manager，依赖于spring cache的第三方实现 
	*/ 
	private static CacheManager cacheManager =  SpringContextHolder.getBean("cacheManager");
	private static final String SYS_CACHE = "sysCache";

	/**
	 * 获取SYS_CACHE缓存
	 * @param key
	 * @return
	 */
	public static Object get(String key) {
		return get(SYS_CACHE, key);
	}
	
	/**
	 * 写入SYS_CACHE缓存
	 * @param key
	 * @return
	 */
	public static void put(String key, Object value) {
		put(SYS_CACHE, key, value);
	}
	
	/**
	 * 从SYS_CACHE缓存中移除
	 * @param key
	 * @return
	 */
	public static void remove(String key) {
		remove(SYS_CACHE, key);
	}
	
	/**
	 * 获取缓存
	 * @param cacheName
	 * @param key
	 * @return
	 */
	public static Object get(String cacheName, String key) {
	    ValueWrapper valueWrapper = getCache(cacheName).get(key);
	    if(valueWrapper!=null){
	        return valueWrapper.get();
	    }
	    return null;
	}

	/**
	 * 写入缓存
	 * @param cacheName
	 * @param key
	 * @param value
	 */
	public static void put(String cacheName, String key, Object value) {
		getCache(cacheName).put(key, value);
	}

	/**
	 * 从缓存中移除
	 * @param cacheName
	 * @param key
	 */
	public static void remove(String cacheName, String key) {
		getCache(cacheName).evict(key);
	}
	
	/**
	 * 获得一个Cache，没有则创建一个。
	 * @param cacheName
	 * @return
	 */
	private static Cache getCache(String cacheName){
	    return cacheManager.getCache(cacheName);
	}

	public static CacheManager getCacheManager() {
		return cacheManager;
	}
	
	
	
}
