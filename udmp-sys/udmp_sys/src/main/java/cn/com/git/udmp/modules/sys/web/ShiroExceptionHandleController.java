

package cn.com.git.udmp.modules.sys.web;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import cn.com.git.udmp.common.web.BaseDefaultExceptionHandleController;

/** 
 * Shiro的异常句柄Controller
 * @description 实现Shiro定义的异常抛出的捕获处理
 * @author Spring Cao
 * @date 2016年8月25日 下午3:13:33  
*/
@ControllerAdvice
public class ShiroExceptionHandleController extends BaseDefaultExceptionHandleController{
   
    /**
     * @title 授权异常
     * @description 针对无权限用户的盗链行为，将跳转至403页面
     * 
     * @return
     */
    @Override
    @ExceptionHandler({AuthorizationException.class,UnauthorizedException.class})
    public String authorizationException(){
        logger.error(">>>>>>>>>>>>>>>>>>>ShiroExceptionHandleController:已经捕获error/403，并跳转授权异常页面");
        return CTL_REDIRECT_ERR_403;
    }
    
    /**
     * 
     * @title 认证异常
     * @description 针对用户名或密码异常、验证码异常 等登陆异常行为进行跳转，但当前会被LoginController给捕获并在登陆页面上进行JS的提示，而非跳转
     * 
     * @return
     */
    @Override
    @ExceptionHandler({ AuthenticationException.class })
    public String authenticationException() {
        logger.error(">>>>>>>>>>>>>>>>>>>已经捕获error/403，并跳转认证异常页面");
        return CTL_REDIRECT_ERR_403;
    }
}
