package cn.com.git.udmp.boot.camel.service.impl;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import cn.com.git.udmp.boot.camel.service.ICamelService;
import cn.com.git.udmp.common.exception.FrameworkException;
import cn.com.git.udmp.common.model.DataObject;
import cn.com.git.udmp.core.logging.ILog;

/**
 * 流水线执行器服务类
 * @author liang
 * 2016年8月9日
 */
@Service
public class LineCamelService implements ICamelService,ILog{
    @Autowired
    ProducerTemplate template;
    
    public <T extends DataObject>T execRoute(String planId,T obj){
        Preconditions.checkNotNull(obj);
        logger.info("交易序列号：{}",planId);
        Object result = template.requestBody("direct:"+planId,obj);
        if(obj.getClass().isAssignableFrom(result.getClass())){
            return  (T) result;
        }else{
            throw new FrameworkException("任务链配置存在异常，返回结果不合规");
        }
    }    
    
}
