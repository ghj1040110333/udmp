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

/**
 * User: heyman
 * Date: 4/28/11
 * Time: 11:15 AM
 */
public class TelnetClientHandler implements Replier<Message> {

    public Class<Message> interest() {
        return Message.class;
    }

    public Object reply(ExchangeChannel channel, Message msg) throws RemotingException {
        return msg;
    }
}