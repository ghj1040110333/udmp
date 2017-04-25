package cn.com.git.udmp.boot.camel.entity;

import cn.com.git.udmp.boot.camel.common.UdmpCamelConstants;
import cn.com.git.udmp.common.model.DataObject;
import cn.com.git.udmp.core.exception.FrameworkRuntimeException;

public class Message extends DataObject {
    private boolean terminateFlag = false;
    private Object payload;

    public Object getPayload() {
        return this.get("payload");
    }

    public void setPayload(Object payload) {
        this.set("payload", payload);
    }

    public boolean getTerminateFlag() {
        Object obj = this.get(UdmpCamelConstants.TERMINATE_FLAG);
        if(obj!=null){
            if(obj.equals(true)){
                return true;
            }else if(obj.equals(false)){
                return false;
            }else{
                throw new FrameworkRuntimeException("获取对象的布尔值失败");
            }
        }
        return false;
    }

    public void setTerminateFlag(boolean terminateFlag) {
        this.set(UdmpCamelConstants.TERMINATE_FLAG, terminateFlag);
    }
    

}
