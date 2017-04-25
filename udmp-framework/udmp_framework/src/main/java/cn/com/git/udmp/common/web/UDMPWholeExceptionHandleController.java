

package cn.com.git.udmp.common.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * UDMP捕获Java异常父类
 * @description 当有未捕获的异常类型时，统一由此类来捕获，并且使用了@ControllerAdvice来保证全局可捕获
 * @see BaseDefaultExceptionHandleController
 * @author Spring Cao
 * @date 2016年8月25日 下午4:20:12
 */
@ControllerAdvice
public class UDMPWholeExceptionHandleController extends BaseDefaultExceptionHandleController{

    /**
     * 
     * @title 全局异常捕获
     * @description 针对没有明确类型的异常进行统一捕获，并跳转至500页面
     * 
     * @return
     */
    @Override
    @ExceptionHandler({ Exception.class })
    public String wholeException() {
        logger.error(">>>>>>>>>>>>>>>>>>>UDMPWholeExceptionHandleController:已经捕获error/500，并跳转异常页面");
        return CTL_REDIRECT_ERR_500;
    }
}
