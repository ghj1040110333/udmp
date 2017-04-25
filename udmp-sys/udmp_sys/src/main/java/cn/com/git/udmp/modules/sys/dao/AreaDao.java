

package cn.com.git.udmp.modules.sys.dao;

import java.util.List;

import cn.com.git.udmp.common.persistence.TreeDao;
import cn.com.git.udmp.common.persistence.annotation.MyBatisDao;
import cn.com.git.udmp.modules.sys.entity.Area;

/**
 * 区域DAO接口
 * @author Spring Cao
 * @version 2014-05-16
 */
@MyBatisDao
public interface AreaDao extends TreeDao<Area> {
	//找到所有目标节点的所有父节点
		public List<Area> findparentsList(Area area);
}
