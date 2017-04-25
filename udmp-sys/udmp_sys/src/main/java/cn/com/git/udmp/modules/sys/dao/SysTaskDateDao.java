

package cn.com.git.udmp.modules.sys.dao;

import java.util.Date;

import cn.com.git.udmp.common.persistence.annotation.MyBatisDao;

@MyBatisDao
public interface SysTaskDateDao {
	public Date getSysDate();
}
