

package cn.com.git.udmp.common.annotation;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

/** 
 * 自定义的ArgumentsAnnotationResolver的处理接口
 * @description 自定义的Resolver的具体处理逻辑需要实现这个接口相关方法，并将自身注册到 
 *              @see CustomerResolverBuilder 中。
 * @author Spring Cao
 * @date 2016年11月8日 下午3:57:33  
*/
public interface ICustomerResolver {
    
    /**
     * 
     * @title 获得自定义解析器的名称
     * @description
     * 
     * @return
     */
    public String getRsolverName();
    
    /**
     * 
     * @title 获得自定义注解的类型
     * @description
     * 
     * @return
     */
    public Class getAnnotationClazz();
    
    /**
     * 
     * @title 执行自定义注解解析器的逻辑
     * @description
     * 
     * @param parameter
     * @param mavContainer
     * @param webRequest
     * @param binderFactory
     * @return
     */
    public Object execute(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory);
}
  