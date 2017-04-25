

package cn.com.git.udmp.common.annotation;

import java.util.List;

/** 
 * 自定义Resolver的组合类
 * @description 用于集合ICustomerResolver的多个实现类 
 * @author Spring Cao
 * @date 2016年11月8日 下午5:17:24  
*/
public class CustomeResolverBuilder {
    /** 自定义注解解析器集合 */
    private List<ICustomerResolver> customerResolvers;

    public List<ICustomerResolver> getCustomerResolvers() {
        return customerResolvers;
    }

    public void setCustomerResolvers(List<ICustomerResolver> customerResolvers) {
        this.customerResolvers = customerResolvers;
    }
}
