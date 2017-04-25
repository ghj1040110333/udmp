package cn.com.git.udmp.component.batch.event.alert.impl;

import org.slf4j.Logger;

import cn.com.git.udmp.core.logging.ILog;

/**
 * 报警代理对象基类
 * @description 报警代理对象基类
 * @author bihb 
 * @date 2015年4月13日 下午3:37:10  
 */
public class BaseAlertProxy implements ILog{
    /**
     * @Fields isEnable : 是否启用标记
     */ 
    private boolean enable;

    /**
     * @Fields alertTemplate : 报警模板文件路径，通过配置文件配置  XXXXAlertTemplate.vm
     */ 
    private String alertTemplate = "";
    
    /**
     * @Fields alertDelaySecond : 报警操作延迟时间，单位秒，默认值0，没有延迟
     */ 
    private int alertDelaySecond;
    /**
     * 返回enable的属性值
     * @description 返回enable的属性值
     * @version 1.0
     * @title 返回enable的属性值
     * @author bihb bihb_wb@newchinalife.com
     * @return 返回获取enable的属性值
     */
    public boolean isEnable() {
        return enable;
    }

    /**
     * @description 给enable属性赋值
     * @version 1.0
     * @title 给enable属性赋值
     * @author bihb bihb_wb@newchinalife.com
     * @param enable enable属性 
     */
    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    /**
     * 返回alertTemplate的属性值
     * @description 返回alertTemplate的属性值
     * @version 1.0
     * @title 返回alertTemplate的属性值
     * @author bihb bihb_wb@newchinalife.com
     * @return 返回获取alertTemplate的属性值
     */
    public String getAlertTemplate() {
        return alertTemplate;
    }

    /**
     * @description 给alertTemplate属性赋值
     * @version 1.0
     * @title 给alertTemplate属性赋值
     * @author bihb bihb_wb@newchinalife.com
     * @param alertTemplate alertTemplate属性 
     */
    public void setAlertTemplate(String alertTemplate) {
        this.alertTemplate = alertTemplate;
    }

    /**
     * 返回alertDelaySecond的属性值
     * @description 返回alertDelaySecond的属性值
     * @version 1.0
     * @title 返回alertDelaySecond的属性值
     * @author bihb bihb_wb@newchinalife.com
     * @return 返回获取alertDelaySecond的属性值
     */
    public int getAlertDelaySecond() {
        return alertDelaySecond;
    }

    /**
     * @description 给alertDelaySecond属性赋值
     * @version 1.0
     * @title 给alertDelaySecond属性赋值
     * @author bihb bihb_wb@newchinalife.com
     * @param alertDelaySecond alertDelaySecond属性 
     */
    public void setAlertDelaySecond(int alertDelaySecond) {
        this.alertDelaySecond = alertDelaySecond;
    }

    /**
     * 返回logger的属性值
     * @description 返回logger的属性值
     * @version 1.0
     * @title 返回logger的属性值
     * @author bihb bihb_wb@newchinalife.com
     * @return 返回获取logger的属性值
     */
    public Logger getLogger() {
        return logger;
    }

}
