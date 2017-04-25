package cn.com.git.udmp.boot.camel.service;

import cn.com.git.udmp.common.model.DataObject;

public interface ICamelService {
    public <T extends DataObject>T execRoute(String uId,T obj);
}
