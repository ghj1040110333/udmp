package cn.com.git.udmp.core.logging;

import org.slf4j.Logger;

/** 
 * @description Logger工厂类  copy from slf4j
 * @author tanzl
 * @date 2014年10月22日 下午3:38:40  
*/
public class LoggerFactory {
    /**
     * @description 不需要传类名获取slf4j的Logger实例
     * @version
     * @title
     * @return  Logger实例
    */
    public static Logger getLogger() {
        String name = new Exception().getStackTrace()[1].getClassName();
        org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(name);
        return logger;
    }
}
