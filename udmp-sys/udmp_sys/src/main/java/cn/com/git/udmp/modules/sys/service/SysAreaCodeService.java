

package cn.com.git.udmp.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.git.udmp.common.persistence.Page;
import cn.com.git.udmp.common.service.CrudService;
import cn.com.git.udmp.modules.sys.dao.SysAreaCodeDao;
import cn.com.git.udmp.modules.sys.entity.Area;
import cn.com.git.udmp.modules.sys.entity.SysAreaCode;
import cn.com.git.udmp.modules.sys.utils.UserUtils;

/**
 * 区域代码管理Service
 * @author 赵明辉
 * @version 2015-11-10
 */
@Service
@Transactional(readOnly = true)
public class SysAreaCodeService extends CrudService<SysAreaCodeDao, SysAreaCode> {

	public SysAreaCode get(String id) {
		return super.get(id);
	}
	
	public List<SysAreaCode> findList(SysAreaCode sysAreaCode) {
		return super.findList(sysAreaCode);
	}
	
	public Page<SysAreaCode> findPage(Page<SysAreaCode> page, SysAreaCode sysAreaCode) {
		return super.findPage(page, sysAreaCode);
	}
	
	@Transactional(readOnly = false)
	public void save(SysAreaCode sysAreaCode) {
		super.save(sysAreaCode);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysAreaCode sysAreaCode) {
		super.delete(sysAreaCode);
	}

	public List<Area> getAreaList() {
		return UserUtils.getAreaList();
	}
	
}