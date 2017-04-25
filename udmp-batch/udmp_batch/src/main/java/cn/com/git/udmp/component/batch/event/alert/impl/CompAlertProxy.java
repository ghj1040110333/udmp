package cn.com.git.udmp.component.batch.event.alert.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.com.git.udmp.component.batch.event.alert.AlertMsg;

/**
 * 聚合类型报警代理对象，报警代理对象响应报警动作，但不做任何操作
 * 
 * @description 聚合类型报警代理对象，报警代理对象响应报警动作，但不做任何操作
 * @author bihb bihb_wb@newchinalife.com
 * @date 2015年4月13日 下午4:12:35
 */
public class CompAlertProxy extends BaseAlertProxy implements IAlertProxy {
    /**
     * @Fields alertProxyList : 聚合对象列表，通过配置文件组合注入IAlertProxy的子类都可以聚合进来
     */
    private List<IAlertProxy> alertProxyList = new ArrayList<IAlertProxy>();

    /**
     * @description 报警操作
     * @version 1.0
     * @title 报警操作
     * @author bihb bihb_wb@newchinalife.com
     * @see cn.com.git.udmp.component.batch.event.alert.impl.IAlertProxy#alert(cn.com.git.udmp.component.batch.event.alert.AlertMsg)
     * @param msg
     *            报警操作
     */
    @Override
    public void alert(AlertMsg msg) {
        // 如果是启用状态，那么进行报警操作
        if (isEnable()) {
            // 与模板的merge操作，然后进行报警操作
            // String alertMsg =
            // VelocityUtils.getMergeResultFromTemplate(velocityConfig,
            // contextkey, msg);
            try {
                Thread.sleep(getAlertDelaySecond() * TIMES);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (null != alertProxyList) {
                for (Iterator<IAlertProxy> iterator = this.alertProxyList.iterator();
                        iterator.hasNext();) {
                    IAlertProxy alertProxy = iterator.next();
                    getLogger().info("聚合对象id:" + alertProxy.hashCode());
                    alertProxy.alert(msg);
                }
            }
            getLogger().info("聚合类型报警调用完毕。");
        }

    }

    /**
     * 返回alertProxyList的属性值
     * 
     * @description 返回alertProxyList的属性值
     * @version 1.0
     * @title 返回alertProxyList的属性值
     * @author bihb bihb_wb@newchinalife.com
     * @return 返回获取alertProxyList的属性值
     */
    public List<IAlertProxy> getAlertProxyList() {
        return alertProxyList;
    }

    /**
     * @description 给alertProxyList属性赋值
     * @version 1.0
     * @title 给alertProxyList属性赋值
     * @author bihb bihb_wb@newchinalife.com
     * @param alertProxyList
     *            alertProxyList属性
     */
    public void setAlertProxyList(List<IAlertProxy> alertProxyList) {
        this.alertProxyList = alertProxyList;
    }

}
