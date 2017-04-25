package cn.com.git.udmp.component.batch.event.alert.impl;

import cn.com.git.udmp.component.batch.event.alert.AlertMsg;

/**
 * 空操作报警代理对象，报警代理对象响应报警动作，但不做任何操作
 * @description 空操作报警代理对象，报警代理对象响应报警动作，但不做任何操作
 * @author bihb bihb_wb@newchinalife.com
 * @date 2015年4月13日 下午4:12:35  
 */
public class NOPAlertProxy extends BaseAlertProxy implements IAlertProxy {

    /**
     * @description 报警操作
     * @version 1.0
     * @title 报警操作
     * @author bihb bihb_wb@newchinalife.com
     * @see cn.com.git.udmp.component.batch.event.alert.impl.IAlertProxy#alert(cn.com.git.udmp.component.batch.event.alert.AlertMsg)
     * @param msg 报警操作
     */
    @Override
    public void alert(AlertMsg msg) {
        //如果是启用状态，那么进行报警操作
        if (isEnable()) {
            try {
                Thread.sleep(getAlertDelaySecond() * TIMES);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            getLogger().info("默认报警空操作。");
        }

    }

}
