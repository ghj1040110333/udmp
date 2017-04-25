

package cn.com.git.udmp.common.utils.bizdate.support;

import java.util.HashMap;
import java.util.Map;

import cn.com.git.udmp.common.utils.bizdate.support.handler.DisableTimeHandler;
import cn.com.git.udmp.common.utils.bizdate.support.handler.MinusTimeHandler;
import cn.com.git.udmp.common.utils.bizdate.support.handler.OrderTimeHandler;
import cn.com.git.udmp.common.utils.bizdate.support.handler.PlusTimeHandler;
import cn.com.git.udmp.common.utils.bizdate.support.handler.SetTimeHandler;

/**
 * @author liang
 *
 */
public class BizTimeHandlerFactory {
    private static Map<String, IBizTimeHandler> map = new HashMap<String, IBizTimeHandler>();
    
    static{
        map.put(HandlerTypeCnst.DISABLE, new DisableTimeHandler());
        map.put(HandlerTypeCnst.MINUS, new MinusTimeHandler());
        map.put(HandlerTypeCnst.ORDER, new OrderTimeHandler());
        map.put(HandlerTypeCnst.PLUS, new PlusTimeHandler());
        map.put(HandlerTypeCnst.SET, new SetTimeHandler());
    }
    
    public static IBizTimeHandler getBizTimeHandler(String type){
       if(map.get(type)==null){
           return null;
       }else{
           return map.get(type);
       }
    }
}
