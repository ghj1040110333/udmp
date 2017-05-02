package cn.com.git.udmp.cloud.gateway.zuul.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;

public class AccessFilter extends ZuulFilter{
    private static Logger log = LoggerFactory.getLogger(AccessFilter.class);
    
    @Override
    public Object run() {
        log.info("filter the request");
        return null;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public String filterType() {
        return "pre";
    }

}
