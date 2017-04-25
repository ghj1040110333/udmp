

package cn.com.git.udmp.core.context;

import java.util.Map;

import cn.com.git.udmp.core.base.IContext;
import cn.com.git.udmp.core.config.Global;

/**
 * 系统环境上下文
 * @description 
 * @author Spring Cao
 * @date 2016年8月22日 下午4:12:50
 */
public class ENVContext implements IContext{

	@Override
	public String getSysEnv(String key) {
		return System.getenv(key != null ? key : "");
	}

	@Override
	public Map<String, String> getSysEnvAll() {
		return System.getenv();
	}	
	
	/**
	 * @title 获取Global全局配置类
	 * @description 判断Global不为空时返回Global类，否则，返回null
	 * 
	 * @return Global 全局配置类
	 */
	public Global getGlobal(){
	    return Global.getInstance() != null ? Global.getInstance() : null;
	}
}
