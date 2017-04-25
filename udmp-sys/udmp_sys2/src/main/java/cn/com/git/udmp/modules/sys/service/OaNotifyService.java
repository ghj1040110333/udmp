
package cn.com.git.udmp.modules.sys.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.git.udmp.common.persistence.Page;
import cn.com.git.udmp.common.service.CrudService;
import cn.com.git.udmp.modules.sys.dao.OaNotifyDao;
import cn.com.git.udmp.modules.sys.dao.OaNotifyRecordDao;
import cn.com.git.udmp.modules.sys.entity.OaNotify;
import cn.com.git.udmp.modules.sys.entity.OaNotifyRecord;
import cn.com.git.udmp.modules.sys.entity.User;

/**
 * 通知通告Service
 * 
 * @author 叶清平
 * @version 2016-02-24
 */
@Service
@Transactional(readOnly = true)
public class OaNotifyService extends CrudService<OaNotifyDao, OaNotify> {

    @Autowired
    private OaNotifyRecordDao oaNotifyRecordDao;

    public OaNotify get(String id) {
        OaNotify entity = dao.get(id);
        return entity;
    }

    /**
     * 获取通知发送记录
     * 
     * @param oaNotify
     * @return
     */
    public OaNotify getRecordList(OaNotify oaNotify) {
        List<OaNotifyRecord> list = oaNotifyRecordDao.findList(new OaNotifyRecord(oaNotify));
        oaNotify.setOaNotifyRecordList(list);
        return oaNotify;
    }

    public Page<OaNotify> find(Page<OaNotify> page, OaNotify oaNotify) {
        oaNotify.setPage(page);
        page.setList(dao.findList(oaNotify));
        return page;
    }

    @Transactional(readOnly = false)
    public void save(OaNotify oaNotify) {
        super.save(oaNotify);

        // 更新发送接受人记录
        oaNotifyRecordDao.deleteByOaNotifyId(oaNotify.getId());
        if (oaNotify.getOaNotifyRecordList().size() > 0) {
            oaNotifyRecordDao.insertAll(oaNotify.getOaNotifyRecordList());
        }
    }

    /**
     * 更新阅读状态
     */
    @Transactional(readOnly = false)
    public void updateReadFlag(OaNotify oaNotify) {
        OaNotifyRecord oaNotifyRecord = new OaNotifyRecord(oaNotify);
        oaNotifyRecord.setUser((User) oaNotifyRecord.getCurrentUser());
        oaNotifyRecord.setReadDate(new Date());
        oaNotifyRecord.setReadFlag("1");
        oaNotifyRecordDao.update(oaNotifyRecord);
    }

    /**
     * 获取我的未读通知
     * 
     * @param oaNotify
     * @return
     */
    public List<OaNotify> findUnread(OaNotify oaNotify) {
        return dao.findUnread(oaNotify);
    }
}