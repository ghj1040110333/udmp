

package cn.com.git.udmp.boot.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * jedis配置
 * @author liang
 * 2016年8月17日
 */
@ConfigurationProperties(prefix = JedisProperties.JEDIS_PREFIX)
public class JedisProperties {
    public final static String JEDIS_PREFIX = "jedis";
    
    private String maxIdle; //最大能够保持idel状态的对象数 
    private  String maxTotal; //最大分配的对象数
    private  Boolean testOnBorrow; //当调用borrow Object方法时，是否进行有效性检查 
    private  List<String> address1; //当调用borrow Object方法时，是否进行有效性检查 
    /**
     * @return address
     */
    public List<String> getAddress() {
        return address1;
    }
    /**
     * @param address 要设置的 address
     */
    public void setAddress(List<String> address) {
        this.address1 = address;
    }
    /**
     * @return maxIdle
     */
    public String getMaxIdle() {
        return maxIdle;
    }
    /**
     * @param maxIdle 要设置的 maxIdle
     */
    public void setMaxIdle(String maxIdle) {
        this.maxIdle = maxIdle;
    }
    /**
     * @return maxTotal
     */
    public String getMaxTotal() {
        return maxTotal;
    }
    /**
     * @param maxTotal 要设置的 maxTotal
     */
    public void setMaxTotal(String maxTotal) {
        this.maxTotal = maxTotal;
    }
    /**
     * @return testOnBorrow
     */
    public Boolean getTestOnBorrow() {
        return testOnBorrow;
    }
    /**
     * @param testOnBorrow 要设置的 testOnBorrow
     */
    public void setTestOnBorrow(Boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }
    
    
    
    
}
