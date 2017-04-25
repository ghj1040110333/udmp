
package cn.com.git.udmp.modules.sys.dao;

import java.util.List;

import cn.com.git.udmp.common.persistence.CrudDao;
import cn.com.git.udmp.common.persistence.annotation.MyBatisDao;
import cn.com.git.udmp.modules.sys.entity.OaNotifyRecord;

/**
 * 通知通告记录DAO接口
 * 
 * @author 叶清平
 * @version 2016-02-24
 */
@MyBatisDao
public interface OaNotifyRecordDao extends CrudDao<OaNotifyRecord> {

    /**
     * 插入通知记录
     * 
     * @param oaNotifyRecordList
     * @return
     */
    public int insertAll(List<OaNotifyRecord> oaNotifyRecordList);

    /**
     * 根据通知ID删除通知记录
     * 
     * @param oaNotifyId
     *            通知ID
     * @return
     */
    public int deleteByOaNotifyId(String oaNotifyId);

}