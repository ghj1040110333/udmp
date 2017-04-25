package cn.com.git.udmp.springcloud.hystrix;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
   *	@author  Liang
   *	@since 2016年10月17日
**/
@Component
public class StoreIntegration {
	@HystrixCommand(fallbackMethod = "defaultStores")
    public Object getStores(Map<String, Object> parameters) {
		return parameters;
        //do stuff that might fail
    }

    public Object defaultStores(Map<String, Object> parameters) {
        return "it's a error";
    }
}
