

package cn.com.git.udmp.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.git.udmp.common.persistence.Page;
import cn.com.git.udmp.common.service.CrudService;
import cn.com.git.udmp.modules.sys.dao.SysScoreDao;
import cn.com.git.udmp.modules.sys.entity.SysScore;

/**
 * 积分参数管理Service
 * @author 孙毅
 * @version 2015-11-06
 */
@Service
@Transactional(readOnly = true)
public class SysScoreService extends CrudService<SysScoreDao, SysScore> {

	public SysScore get(String id) {
		return super.get(id);
	}
	
	public List<SysScore> findList(SysScore sysScore) {
		return super.findList(sysScore);
	}
	
	public Page<SysScore> findPage(Page<SysScore> page, SysScore sysScore) {
		return super.findPage(page, sysScore);
	}
	
	@Transactional(readOnly = false)
	public void save(SysScore sysScore) {
		super.save(sysScore);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysScore sysScore) {
		super.delete(sysScore);
	}
	
}