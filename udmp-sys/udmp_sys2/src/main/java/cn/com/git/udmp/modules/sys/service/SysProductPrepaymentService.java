

package cn.com.git.udmp.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.git.udmp.common.persistence.Page;
import cn.com.git.udmp.common.service.CrudService;
import cn.com.git.udmp.modules.sys.dao.SysProductPrepaymentDao;
import cn.com.git.udmp.modules.sys.entity.SysProductPrepayment;

/**
 * 提前还款信息Service
 * @author 赵明辉
 * @version 2015-11-18
 */
@Service
@Transactional(readOnly = true)
public class SysProductPrepaymentService extends CrudService<SysProductPrepaymentDao, SysProductPrepayment> {

	public SysProductPrepayment get(String id) {
		return super.get(id);
	}
	
	public List<SysProductPrepayment> findList(SysProductPrepayment sysProductPrepayment) {
		return super.findList(sysProductPrepayment);
	}
	
	public Page<SysProductPrepayment> findPage(Page<SysProductPrepayment> page, SysProductPrepayment sysProductPrepayment) {
		return super.findPage(page, sysProductPrepayment);
	}
	
	@Transactional(readOnly = false)
	public void save(SysProductPrepayment sysProductPrepayment) {
		super.save(sysProductPrepayment);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysProductPrepayment sysProductPrepayment) {
		super.delete(sysProductPrepayment);
	}
	
}