

package cn.com.git.udmp.modules.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.git.udmp.common.persistence.Page;
import cn.com.git.udmp.common.service.CrudService;
import cn.com.git.udmp.common.utils.StringUtils;
import cn.com.git.udmp.modules.sys.dao.SysBreachFineSubsectionDao;
import cn.com.git.udmp.modules.sys.dao.SysProductFineDao;
import cn.com.git.udmp.modules.sys.entity.SysBreachFineSubsection;
import cn.com.git.udmp.modules.sys.entity.SysProductFine;

/**
 * 罚息管理Service
 * @author 赵明辉
 * @version 2015-11-18
 */
@Service
@Transactional(readOnly = true)
public class SysProductFineService extends CrudService<SysProductFineDao, SysProductFine> {

	@Autowired
	private SysBreachFineSubsectionDao sysBreachFineSubsectionDao;
	
	public SysProductFine get(String id) {
		SysProductFine sysProductFine = super.get(id);
		sysProductFine.setSysBreachFineSubsectionList(sysBreachFineSubsectionDao.findList(new SysBreachFineSubsection(sysProductFine)));
		return sysProductFine;
	}

	public List<SysProductFine> findList(SysProductFine sysProductFine) {
		return super.findList(sysProductFine);
	}
	
	public Page<SysProductFine> findPage(Page<SysProductFine> page, SysProductFine sysProductFine) {
		return super.findPage(page, sysProductFine);
	}
	
	@Transactional(readOnly = false)
	public void save(SysProductFine sysProductFine) {
		super.save(sysProductFine);
		for (SysBreachFineSubsection sysBreachFineSubsection : sysProductFine.getSysBreachFineSubsectionList()){
			if (sysBreachFineSubsection.getId() == null){
				continue;
			}
			if (SysBreachFineSubsection.DEL_FLAG_NORMAL.equals(sysBreachFineSubsection.getDelFlag())){
				if (StringUtils.isBlank(sysBreachFineSubsection.getId())){
					sysBreachFineSubsection.setSysProductFineId(sysProductFine);
					sysBreachFineSubsection.preInsert();
					sysBreachFineSubsectionDao.insert(sysBreachFineSubsection);
				}else{
					sysBreachFineSubsection.preUpdate();
					sysBreachFineSubsectionDao.update(sysBreachFineSubsection);
				}
			}else{
				sysBreachFineSubsectionDao.delete(sysBreachFineSubsection);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(SysProductFine sysProductFine) {
		super.delete(sysProductFine);
		sysBreachFineSubsectionDao.delete(new SysBreachFineSubsection(sysProductFine));
	}
	
}