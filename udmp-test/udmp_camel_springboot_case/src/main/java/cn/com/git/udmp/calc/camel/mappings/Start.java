package cn.com.git.udmp.calc.camel.mappings;

import cn.com.git.udmp.boot.camel.entity.Message;

public class Start extends Message{
    private String custId;
    private String name;
    private int age;
    
    public String getCustId() {
        return custId;
    }
    public void setCustId(String custId) {
        this.custId = custId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
