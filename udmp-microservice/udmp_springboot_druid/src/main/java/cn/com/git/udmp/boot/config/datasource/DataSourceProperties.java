

package cn.com.git.udmp.boot.config.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description
 * @author Liang liuliang1@git.com.cn
 * @date 2016年10月12日 上午9:57:18
 */
@Component
@ConfigurationProperties(prefix = DataSourceProperties.DATASOURCE_PREFIX)
public class DataSourceProperties {
    public final static String DATASOURCE_PREFIX = "spring.datasource";
    
    private String url;

    private String username;

    private String password;
    
    private String driverClassName;

    private int initialSize = 5;

    private int maxActive = 20;

    private int minIdle = 5;

    private String validationQuery = "SELECT 1 FROM DUAL";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(int initialSize) {
        this.initialSize = initialSize;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public String getValidationQuery() {
        return validationQuery;
    }

    public void setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }
}
