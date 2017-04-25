package cn.com.git.udmp.common.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.UserFilter;
import org.springframework.stereotype.Service;

import cn.com.git.udmp.common.mapper.JsonMapper;
import cn.com.git.udmp.common.web.MobileResultVO;
import cn.com.git.udmp.common.web.ParentResultVO;
import cn.com.git.udmp.core.config.Global;

/**
 * 手机端资源访问过滤器
 * 
 * 使用方式：
 * 在spring-context-shiro.xml的shiroFilter中定义：
 * <entry key="mobileUser" value-ref="mobileUserFilter"/>
 * 在filterChainDefinitions中增加：
 * ${mobilePath}/** = mobileUser
 * 
 * @author tangyz
 */
//@Service
public class MobileUserFilter extends UserFilter {

	/**
	 * 手机端的登录uri，例如 "/m/login/li"
	 */
    public String getLoginUrl() {
        return "/" + Global.getMobilePath() + "/login/li";
    }
    
    /**
     * 当方法{@link #isAccessAllowed(javax.servlet.ServletRequest, javax.servlet.ServletResponse, Object) isAccessAllowed}
     * 拒绝请求时，进一步对rquest进行处理.
     *
     * @param request  进来的 <code>ServletRequest</code>
     * @param response 出去的 <code>ServletResponse</code>
     * @return <code>true</code> 允许继续访问; false 禁止访问并直接返回出错提示.
     * @throws Exception 处理请求出现问题.
     */
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
    	MobileResultVO<Object> mobileResultVO = new MobileResultVO<Object>();
		mobileResultVO.setResult(ParentResultVO.ERROR);
		mobileResultVO.setMessage("not login");
		mobileResultVO.setRedirect("login");
		HttpServletResponse httpServletResponse=(HttpServletResponse) response;
    	httpServletResponse.setStatus(401);  
    	JsonMapper.renderJson(mobileResultVO, response);
    	
        return false;
    }
    
}
