

package cn.com.git.udmp.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.git.udmp.common.persistence.Page;
import cn.com.git.udmp.common.service.CrudService;
import cn.com.git.udmp.modules.sys.dao.SysBusParamDao;
import cn.com.git.udmp.modules.sys.entity.SysBusParam;

/**
 * 业务参数管理Service
 * @author xwj
 * @version 2015-11-10
 */
@Service
@Transactional(readOnly = true)
public class SysBusParamService extends CrudService<SysBusParamDao, SysBusParam> {

	public SysBusParam get(String id) {
		return super.get(id);
	}
	
	public List<SysBusParam> findList(SysBusParam sysBusParam) {
		return super.findList(sysBusParam);
	}
	
	public Page<SysBusParam> findPage(Page<SysBusParam> page, SysBusParam sysBusParam) {
		return super.findPage(page, sysBusParam);
	}
	
	@Transactional(readOnly = false)
	public void save(SysBusParam sysBusParam) {
		super.save(sysBusParam);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysBusParam sysBusParam) {
		super.delete(sysBusParam);
	}

	@Transactional(readOnly = false)
	public List<SysBusParam> findRegAgreementList(SysBusParam sysBusParam) {
		return dao.findRegAgreementList(sysBusParam);
	}
	
}