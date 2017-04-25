package cn.com.git.udmp.component.batch.communication.protobuf.protobuf;

import java.util.List;

import com.google.common.collect.Lists;

import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message;

/** 
 * @description 
 * @author Liang liuliang1@git.com.cn 
 * @date 2017年4月10日 上午11:53:54  
*/
public class MessageCache {
    private static List<Message> list = Lists.newCopyOnWriteArrayList();
    
    public static void addMsg(Message msg){
        list.add(msg);
    }
    
    public static List<Message> getMsgCache(){
        return list;
    }
    
    
}
