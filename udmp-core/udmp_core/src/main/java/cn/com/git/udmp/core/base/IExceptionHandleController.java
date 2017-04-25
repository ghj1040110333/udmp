

package cn.com.git.udmp.core.base;

import javax.servlet.http.HttpServletResponse;

/**
 * 
 * UDMP默认异常句柄Controller接口
 * @description 用于定义UDMP Controller层的异常捕获
 * @author Spring Cao
 * @date 2016年8月25日 下午3:59:54
 */
public interface IExceptionHandleController extends IController{
    /** 500页面 */
    public static final String CTL_REDIRECT_ERR_500 = "error/500";
    /** 403页面 */
    public static final String CTL_REDIRECT_ERR_403 = "error/403";
    /** 400页面 */
    public static final String CTL_REDIRECT_ERR_400 = "error/400";
    
    //Catch controllers throws Specific Exceptions
    public String bizException(HttpServletResponse response, Exception ex);
    public String bindException();
    //Catch shiro global
    public String authenticationException();
    public String authorizationException();
    //Catch other Exceptions global of the end
    public String wholeException();
    
}
