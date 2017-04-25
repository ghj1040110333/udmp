
package cn.com.git.udmp.modules.sys.dao;

import java.util.List;

import cn.com.git.udmp.common.persistence.CrudDao;
import cn.com.git.udmp.common.persistence.annotation.MyBatisDao;
import cn.com.git.udmp.modules.sys.entity.OaNotify;

/**
 * 通知通告DAO接口
 * 
 * @author 叶清平
 * @version 2016-02-24
 */
@MyBatisDao
public interface OaNotifyDao extends CrudDao<OaNotify> {

    /**
     * 获取我的未读通知
     * 
     * @param oaNotify
     * @return
     */
    public List<OaNotify> findUnread(OaNotify oaNotify);

}