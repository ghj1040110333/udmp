package cn.com.git.udmp.batch.test.dubbo;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

import com.alibaba.dubbo.common.serialize.ObjectInput;

import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.Builder;

public class ProtobufObjectInput implements ObjectInput
{
    InputStream protobufInput;
    
    public ProtobufObjectInput(InputStream is)
    {
        this.protobufInput = is;
//        mH2i.setSerializerFactory(Hessian2SerializerFactory.SERIALIZER_FACTORY);
    }

    

    public Object readObject() throws IOException
    {
        Message message = Message.newBuilder().mergeFrom(protobufInput).build();
        return message;
    }



    @Override
    public boolean readBool() throws IOException {
        throw new IOException("protobuf input unsupported ");
    }



    @Override
    public byte readByte() throws IOException {
        throw new IOException("protobuf input unsupported ");
    }



    @Override
    public short readShort() throws IOException {
        throw new IOException("protobuf input unsupported ");
    }



    @Override
    public int readInt() throws IOException {
        throw new IOException("protobuf input unsupported ");
    }



    @Override
    public long readLong() throws IOException {
        throw new IOException("protobuf input unsupported ");
    }



    @Override
    public float readFloat() throws IOException {
        throw new IOException("protobuf input unsupported ");
    }



    @Override
    public double readDouble() throws IOException {
        throw new IOException("protobuf input unsupported ");
    }



    @Override
    public String readUTF() throws IOException {
        throw new IOException("protobuf input unsupported ");
    }



    @Override
    public byte[] readBytes() throws IOException {
        throw new IOException("protobuf input unsupported ");
    }



    @Override
    public <T> T readObject(Class<T> cls) throws IOException, ClassNotFoundException {
        throw new IOException("protobuf input unsupported ");
    }



    @Override
    public <T> T readObject(Class<T> cls, Type type) throws IOException, ClassNotFoundException {
        throw new IOException("protobuf input unsupported ");
    }


}