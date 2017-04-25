

package cn.com.git.udmp.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;

/** 
 * @description 
 * @author Liang liuliang1@git.com.cn 
 * @date 2016年9月8日 上午10:13:48  
*/
@ConfigurationProperties(prefix = AgentServerProperties.BATCH_AGENT_PREFIX)
public class AgentServerProperties {
    public final static String BATCH_AGENT_PREFIX="batch.agent";
    
    public String serverPort="10000";

    /**
     * @return serverPort
     */
    public String getServerPort() {
        return serverPort;
    }

    /**
     * @param serverPort 要设置的 serverPort
     */
    public void setServerPort(String serverPort) {
        this.serverPort = serverPort;
    }
    
    
}
