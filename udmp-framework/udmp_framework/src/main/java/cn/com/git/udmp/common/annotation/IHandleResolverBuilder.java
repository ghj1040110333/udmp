

package cn.com.git.udmp.common.annotation;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

/** 
 * 自定义WebMVC Controller Annotation的ArgumentsResolverBuilder接口
 * @description 扩展SpringMVC的Arguments Annotation resolve，实现自定义的处理逻辑 
 * @author Spring Cao
 * @date 2016年11月8日 下午3:53:39  
*/
public interface IHandleResolverBuilder {
    boolean supportsPara(MethodParameter parameter);
    Object resolverPara(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory);
}
