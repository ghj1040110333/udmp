/*
 * Copyright 1999-2011 Alibaba Group.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.com.git.udmp.batch.test.dubbo.test;

import com.alibaba.dubbo.remoting.RemotingException;
import com.alibaba.dubbo.remoting.exchange.ExchangeChannel;
import com.alibaba.dubbo.remoting.exchange.support.Replier;

import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.Builder;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.Header;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.Receiver;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.Sender;

/**
 * User: heyman
 * Date: 4/26/11
 * Time: 4:29 PM
 */
public class TelnetServerHandler implements Replier<Message> {

    public Class<Message> interest() {
        return Message.class;
    }

    public Object reply(ExchangeChannel channel, Message msg) throws RemotingException {
        // Generate and write a response.
        System.out.println("get command:"+msg.getHeader().getCommand());
//        String response;
//        if (msg!=null) {
//            response = "Please type something.\r\n";
//        }  else {
//            response = "Did you say '" + msg + "'?\r\n";
//        }
        Builder builder = Message.newBuilder();
        builder.setHeader(Header.newBuilder().setCommand("test").setCommandType("run"));
        
        builder.setSender(Sender.newBuilder().setFromIp("127.0.0.1").setFromPort("8080"));
        builder.setReceiver(Receiver.newBuilder().setToIp("127.0.0.1").setToPort("8080"));
        //System.out.println(response);
        return builder.build();
    }

}