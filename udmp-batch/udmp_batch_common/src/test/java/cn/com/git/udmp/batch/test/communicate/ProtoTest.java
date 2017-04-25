

package cn.com.git.udmp.batch.test.communicate;

import org.junit.Test;

import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.Extension;

/** 
 * @description protobuf测试
 * @author Liang liuliang1@git.com.cn 
 * @date 2016年9月12日 下午2:18:29  
*/
public class ProtoTest {
    @Test
    public void testProtoOut(){
        Extension value = Extension.newBuilder().setKey("1").setValue("Beijing").build();
        System.out.println(value);
    }
}
