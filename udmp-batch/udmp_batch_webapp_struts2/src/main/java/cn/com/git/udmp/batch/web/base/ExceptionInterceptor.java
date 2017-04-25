package cn.com.git.udmp.batch.web.base;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.ExceptionMappingInterceptor;

import cn.com.git.udmp.batch.web.base.entity.CommonExceptionConst;
import cn.com.git.udmp.batch.web.base.entity.ExceptionResCode;
import cn.com.git.udmp.common.mapper.JsonMapper;
import cn.com.git.udmp.component.model.Constants;
import cn.com.git.udmp.core.logging.ILog;

/**
 * 
 * @description 公共异常处理拦截器
 * @author Spring Cao
 * @date 2016年8月30日 下午1:44:31
 */
@SuppressWarnings("serial")
public class ExceptionInterceptor extends ExceptionMappingInterceptor implements ILog{

    /**
     * @Fields EARLY_WARNING : 是否预警标识
     */
    private static String EARLY_WARNING = "1";

    /**
     * @Fields ORG_CODE : 业务机构代码
     */
    private static String ORG_CODE = "86";

    /**
     * @description 异常捕获公共处理方法
     * @version
     * @title
     * @see com.opensymphony.xwork2.interceptor.ExceptionMappingInterceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
     * @param invocation 动态拦截对象
     * @return 跳转页面
     * @throws Exception
     */
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        String result = "none";
        // 由ActionInvocation获取request和response
        HttpServletRequest request = (HttpServletRequest) invocation.getInvocationContext().get(
                StrutsStatics.HTTP_REQUEST);
        HttpServletResponse response = (HttpServletResponse) invocation.getInvocationContext().get(
                StrutsStatics.HTTP_RESPONSE);
        try {
            result = invocation.invoke();
        } catch (Exception e) {
            ActionContext act = invocation.getInvocationContext();
            // 得到请求参数
            final Map<String, Object> parameters = act.getParameters();
            // System.out.println("nciHeadVo.TransSerialno====>"+parameters.get("nciHeadVo.TransSerialno"));
            // 发送异常JMS消息队列给监控平台
//            System.out.println(e.getClass().getName());
//            System.out.println(e.getClass().getSimpleName());
//            alertJMS(parameters, e);

            // 公共日志处理
            logger.error(e.getMessage(), e);
            // 非Ajax方式请求，返回错误页面
            if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request
                    .getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").indexOf(
                    "XMLHttpRequest") > -1))) {
                throw e;
            } else { // Ajax方式请求，页面弹出错误提示窗口
                Map<String, String> map = new HashMap<String, String>();
                map.put("statusCode", Constants.DWZ_STATUSCODE_300);
                map.put("message", e.getMessage());
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                String str = JsonMapper.toJsonString(map);
                out.write(str);
            }
        }
        return result;
    }

  
    
    /**
     * @description 对常见异常信息进行分类，并返回相应的状态码和状态描述信息
     * @version 1.0
     * @title 
     * @param e 抛出的异常
     * @return 异常状态码对象
    */
    private ExceptionResCode exceptionHandler(Exception e) {
        //异常的简称
        String exceptionName = e.getClass().getSimpleName();
        //异常信息的全称
        String exceptionFullName = e.getClass().getName();
        if(exceptionName.equalsIgnoreCase(CommonExceptionConst.NULLPOINTEREXCEPTION)) {
            return ExceptionResCode.NULLPOINT_EXCEPTION;
        } else if(exceptionName.equalsIgnoreCase(CommonExceptionConst.ARITHMETICEXCEPTION)) {
            return ExceptionResCode.ARITHMETICEXCEPTION;
        } else if(exceptionName.equalsIgnoreCase(CommonExceptionConst.ARRAYINDEXOUTOFBOUNDSEXCEPTION) 
                || exceptionName.equalsIgnoreCase(CommonExceptionConst.INDEXOUTOFBOUNDSEXCEPTION)) {
            return ExceptionResCode.OUTOFBOUND_EXCEPTION;
        } else if(exceptionName.equalsIgnoreCase(CommonExceptionConst.CLASSCASTEXCEPTION)) {
            return ExceptionResCode.CLASSCAST_EXCEPTION;
        } else if(exceptionName.equalsIgnoreCase(CommonExceptionConst.NOSUCHBEANDEFINITIONEXCEPTION)
                || exceptionName.equalsIgnoreCase(CommonExceptionConst.NOUNIQUEBEANDEFINITIONEXCEPTION)
                || exceptionFullName.indexOf("springframework") != -1) {
            return ExceptionResCode.PROPERTY_EXCEPTION;
        } else if(exceptionName.equalsIgnoreCase(CommonExceptionConst.SECURITYEXCEPTION)) {
            return ExceptionResCode.SECURITY_EXCEPTION;
        } else if(exceptionName.equalsIgnoreCase(CommonExceptionConst.BINDEXCEPTION)
                || exceptionName.equalsIgnoreCase(CommonExceptionConst.CONNECTIONEXCEPTION)
                || exceptionName.equalsIgnoreCase(CommonExceptionConst.SOCKETEXCEPTION) 
                || exceptionName.equalsIgnoreCase(CommonExceptionConst.SOCKETTIMEOUTEXCEPTION) 
                || exceptionFullName.indexOf("net") != -1) {
            return ExceptionResCode.NET_EXCEPTION;
        } else if(exceptionName.equalsIgnoreCase(CommonExceptionConst.ARRAYSTOREEXCEPTION)) {
            return ExceptionResCode.STORE_EXCEPTION;
        } else if(exceptionName.equalsIgnoreCase(CommonExceptionConst.SERVLETEXCEPTION)) {
            return ExceptionResCode.CONTAINER_EXCEPTION;
        } else {
            return ExceptionResCode.UNKNOWN_EXCEPTION;
        }
    }
}
