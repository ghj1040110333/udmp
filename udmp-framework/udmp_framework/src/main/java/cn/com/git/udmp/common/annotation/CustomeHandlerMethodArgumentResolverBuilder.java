

package cn.com.git.udmp.common.annotation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

/** 
 * 自定义的SpringMVC方法参数注解解析器构建类
 * @description 
 * @author Spring Cao
 * @date 2016年11月8日 下午3:56:57  
*/
public class CustomeHandlerMethodArgumentResolverBuilder extends AbsHandlerMethodArgumentResolver{
    
    /** 自定义注解解析器包装类 */
    @Autowired
    private CustomeResolverBuilder customeResolverBuilder;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        
        return supportsPara(parameter);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        return resolverPara(parameter,mavContainer,webRequest,binderFactory);
    }

    @Override
    public boolean supportsPara(MethodParameter parameter) {
        boolean flag = false;
        //获取自定义的Resolver集合
        List<ICustomerResolver> customerResolvers = customeResolverBuilder.getCustomerResolvers();
        //判断自定义的注解是否存在于传入参数集合中
        for(int i = 0 ; customerResolvers != null && i < customerResolvers.size();i++){
            if(parameter.hasParameterAnnotation(customerResolvers.get(i).getAnnotationClazz())){
                flag = true;
                break;
            }
        }
        
        return flag;
    }

    @Override
    public Object resolverPara(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        ICustomerResolver resolver = null;
        //获取自定义的Resolver集合
        List<ICustomerResolver> customerResolvers = customeResolverBuilder.getCustomerResolvers();
        //获得自定义注解的解析器对象
        for(int i = 0 ; customerResolvers != null && i < customerResolvers.size();i++){
            if(parameter.hasParameterAnnotation(customerResolvers.get(i).getAnnotationClazz())){
                resolver = customerResolvers.get(i);
                break;
            }
        }
        //执行注解解析器的方法
        return resolver.execute(parameter, mavContainer, webRequest, binderFactory);
    }

    public CustomeResolverBuilder getCustomeResolverBuilder() {
        return customeResolverBuilder;
    }

    public void setCustomeResolverBuilder(CustomeResolverBuilder customeResolverBuilder) {
        this.customeResolverBuilder = customeResolverBuilder;
    }
}
