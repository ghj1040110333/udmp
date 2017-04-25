

package cn.com.git.udmp.common.annotation;

import org.springframework.web.method.support.HandlerMethodArgumentResolver;

/** 
 * 抽象的自定义WebMVC Controller Annotation的ArgumentsResolver句柄
 * @description 为以后提供统一的扩展
 * @author Spring Cao
 * @date 2016年11月8日 下午3:51:41  
*/
public abstract class AbsHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver,IHandleResolverBuilder{

}
