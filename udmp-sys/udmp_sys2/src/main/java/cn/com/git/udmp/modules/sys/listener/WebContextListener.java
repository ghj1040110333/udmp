
package cn.com.git.udmp.modules.sys.listener;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;

import cn.com.git.udmp.modules.sys.service.SystemService;

/**
 * 
 * @description UDMP 应用上线文加载监听
 * @author Spring Cao
 * @date 2016年9月7日 上午11:47:43
 */
public class WebContextListener extends org.springframework.web.context.ContextLoaderListener {
	
	@Override
	public WebApplicationContext initWebApplicationContext(ServletContext servletContext) {
	    //加载自定义启动时屏幕输出信息
		if (!SystemService.printKeyLoadMessage()){
			return null;
		}
		return super.initWebApplicationContext(servletContext);
	}
}
