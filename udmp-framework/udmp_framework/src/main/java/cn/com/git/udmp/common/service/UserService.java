package cn.com.git.udmp.common.service;

import cn.com.git.udmp.common.persistence.entity.UserEntity;
import cn.com.git.udmp.core.base.IService;

public interface UserService extends IService{
	public UserEntity getUserById(String id);
	/**
     * 
     * @title 获得用户Session
     * @description 获取用户Session对象，需要使用已经封装好的ISession,其默认实现类ReadOnlySession
     * 
     * @return ISession 
     */
//    public ISession getUserSession();
}
