package cn.com.git.udmp.component.batch.communication.protobuf.protobuf;

import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message;

public interface IRemoteClient {

    void sendMsg(Message msg);

    void connect();

}
