

package cn.com.git.udmp.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.git.udmp.common.persistence.Page;
import cn.com.git.udmp.common.service.CrudService;
import cn.com.git.udmp.modules.sys.dao.SysInitUserInfoDao;
import cn.com.git.udmp.modules.sys.entity.SysInitUserInfo;

/**
 * 客户初始化信息管理Service
 * @author 孙毅
 * @version 2015-11-09
 */
@Service
@Transactional(readOnly = true)
public class SysInitUserInfoService extends CrudService<SysInitUserInfoDao, SysInitUserInfo> {
	
	public SysInitUserInfo get(String id) {
		return super.get(id);
	}
	
	public List<SysInitUserInfo> findList(SysInitUserInfo sysInitUserInfo) {
		return super.findList(sysInitUserInfo);
	}
	
	public Page<SysInitUserInfo> findPage(Page<SysInitUserInfo> page, SysInitUserInfo sysInitUserInfo) {
		return super.findPage(page, sysInitUserInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(SysInitUserInfo sysInitUserInfo) {
		super.save(sysInitUserInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysInitUserInfo sysInitUserInfo) {
		super.delete(sysInitUserInfo);
	}
	
	public List<SysInitUserInfo> findAllList(SysInitUserInfo sysInitUserInfo) {
		return dao.findAllList(sysInitUserInfo);
	}
	
	@Transactional(readOnly = false)
	public void updateSelFlag(SysInitUserInfo sysInitUserInfo){
		dao.updateNoSel();
		if(sysInitUserInfo.getSelStr() != null && !"".equalsIgnoreCase(sysInitUserInfo.getSelStr().trim())){
			dao.updateSelByDicts(sysInitUserInfo);
		}
		
	}
}