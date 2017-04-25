

package cn.com.git.udmp.core.base;

import java.io.Serializable;
import java.util.Map;

import cn.com.git.udmp.core.logging.ILog;

public interface IContext extends ILog,Serializable{
	public String getSysEnv(String key);
	public Map<String,String> getSysEnvAll();
}
