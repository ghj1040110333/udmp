

package cn.com.git.udmp.web.log;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.com.git.udmp.common.exception.FrameworkException;
import cn.com.git.udmp.common.mapper.JsonMapper;
import cn.com.git.udmp.common.utils.biz.BusinessProcessUtil;
import cn.com.git.udmp.core.logging.ILog;
import cn.com.git.udmp.web.ResultVO;

/**
 * SpringMVC 日志处理拦截器
 * @description 
 * @author guosg
 * @date 2016年9月7日 上午10:24:40
 */
public class LogHandlerInterceptor implements HandlerInterceptor,ILog{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//初始化业务流程处理
		BusinessProcessUtil.init(null, request.getRequestURI());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	    /**
	     * TODO updated by liang 迁移中发现还需要依赖于安全相关信息，所以这里先注释上待解决
	     * 建议：日志处理拦截器需要返回用户信息，所以看需不需要做接口封装或者从core工程转移到组件工程中
	     */
		if(ex != null){
			logger.error(ex.getMessage(),ex);
			if(ex instanceof FrameworkException){
				ResultVO vo = new ResultVO(((FrameworkException)ex).getErrCode(),ex.getMessage());
				renderString(response, vo);
			}else {
				ResultVO vo = new ResultVO("rcs00001",ex.getMessage());
				renderString(response, vo);
			}
		}
	}
	
	/**
	 * 
	 * @title 客户端返回JSON字符串
	 * @description
	 * 
	 * @param response
	 * @param object
	 * @return
	 */
	protected String renderString(HttpServletResponse response, Object object) {
		return renderString(response, JsonMapper.toJsonString(object), "application/json");
	}
	
	/**
	 * 
	 * @title 客户端返回字符串
	 * @description
	 * 
	 * @param response
	 * @param string
	 * @param type
	 * @return
	 */
	protected String renderString(HttpServletResponse response, String string, String type) {
		try {
			response.reset();
	        response.setContentType(type);
	        response.setCharacterEncoding("utf-8");
			response.getWriter().print(string);
			return null;
		} catch (IOException e) {
			return null;
		}
	}
}
