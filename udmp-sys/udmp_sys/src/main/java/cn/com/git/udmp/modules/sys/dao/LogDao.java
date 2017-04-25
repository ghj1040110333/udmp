

package cn.com.git.udmp.modules.sys.dao;

import cn.com.git.udmp.common.persistence.CrudDao;
import cn.com.git.udmp.common.persistence.annotation.MyBatisDao;
import cn.com.git.udmp.modules.sys.entity.Log;

/**
 * 日志DAO接口
 * @author Spring Cao
 * @version 2014-05-16
 */
@MyBatisDao
public interface LogDao extends CrudDao<Log> {

}
