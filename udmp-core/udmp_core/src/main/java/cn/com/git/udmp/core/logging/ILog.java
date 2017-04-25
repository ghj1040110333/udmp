package cn.com.git.udmp.core.logging;

import org.slf4j.Logger;

import cn.com.git.udmp.core.logging.LoggerFactory;

/** 
 * @description 日志接口
 * @author liuliang 
 * @date 2015年7月7日 下午5:07:37  
*/
public interface ILog {
    /** 
    * @Fields logger : 日志logger对象 
    */ 
    public Logger logger = LoggerFactory.getLogger();
}
