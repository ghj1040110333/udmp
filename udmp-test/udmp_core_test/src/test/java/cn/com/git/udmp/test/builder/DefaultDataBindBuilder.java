/**
 * GIT Confidential
 * Licensed Materials - Property of GIT
 * Copyright (c) 1998-2016 Global InfoTech Corp. All Rights Reserved.
 * Global InfoTech, Inc. owns the copyright and other intellectual
 * property rights in this software.
 *
 * The source code for this program is not published.
 * Duplication or use of the Software is not permitted
 * except as expressly provided in a written agreement
 * between your company and Global InfoTech, Inc.
 */

package cn.com.git.udmp.test.builder;

/** 
 * @description 
 * @author Spring Cao
 * @date 2016年8月26日 下午2:36:38  
*/
public class DefaultDataBindBuilder implements DataBind{
    
    DefaultDataBindBuilder(Builder builder){
        //可以用作扩展，获取Builder的实例化对象，并应用于其他的内部类中
    }
    
    
    public static Builder builder() {
        return new Builder();
    }
    
    public static final class Builder implements DataBindBuilder.Builder{
        private String name;
        private int age;
        private String addr;
        
        Builder() {
        }
        
        @Override
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        @Override
        public Builder age(int age) {
            this.age = age;
            return this;
        }

        @Override
        public Builder address(String addr) {
            this.addr = addr;
            return this;
        }

        @Override
        public DataBind build() {
            // TODO 自动生成的方法存根
            return new DefaultDataBindBuilder(this);
        }

        @Override
        public String echo() {
            String rtn = this.name + "," + this.age + "," + this.addr;
            System.out.println(rtn);
            return rtn;
        }
    }
}
