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

package cn.com.git.udmp.calc.camel.mappings;

import java.math.BigDecimal;

import cn.com.git.udmp.boot.camel.entity.Message;

/** 
 * @description 
 * @author Spring Cao
 * @date 2016年9月1日 上午10:05:46  
*/
public class Report extends Message{
    private String repid;
    private String id;
    private String name;
    private String address;
    private int age;
    private BigDecimal currency;
    public String getRepid() {
        return repid;
    }
    public void setRepid(String repid) {
        this.repid = repid;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public BigDecimal getCurrency() {
        return currency;
    }
    public void setCurrency(BigDecimal currency) {
        this.currency = currency;
    }
}
