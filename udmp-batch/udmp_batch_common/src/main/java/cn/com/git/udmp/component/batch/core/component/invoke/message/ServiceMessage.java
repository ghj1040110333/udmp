package cn.com.git.udmp.component.batch.core.component.invoke.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/** 
 * @description 服务的传递信息 
 * @author liuliang liuliang_wb@newchina.live 
 * @date 2015年1月27日 上午10:48:56  
*/
@XmlAccessorType(XmlAccessType.FIELD)
public class ServiceMessage {
    String name;
    String[] address;
    int age;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String[] getAddress() {
        return address;
    }
    public void setAddress(String[] address) {
        this.address = address;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
