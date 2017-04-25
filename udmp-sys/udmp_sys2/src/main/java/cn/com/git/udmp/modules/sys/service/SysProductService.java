

package cn.com.git.udmp.modules.sys.service;

import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.git.udmp.common.persistence.Page;
import cn.com.git.udmp.common.service.CrudService;
import cn.com.git.udmp.common.utils.StringUtils;
import cn.com.git.udmp.modules.sys.dao.SysProductDao;
import cn.com.git.udmp.modules.sys.dao.SysProductDetailDao;
import cn.com.git.udmp.modules.sys.entity.SysProduct;
import cn.com.git.udmp.modules.sys.entity.SysProductDetail;

/**
 * 产品信息Service
 * @author 赵明辉
 * @version 2015-11-17
 */
@Service
@Transactional(readOnly = true)
public class SysProductService extends CrudService<SysProductDao, SysProduct> {

	@Autowired
	private SysProductDetailDao sysProductDetailDao;
	
	public SysProduct get(String id) {
		SysProduct sysProduct = super.get(id);
		sysProduct.setSysProductDetailList(sysProductDetailDao.findList(new SysProductDetail(sysProduct)));
		return sysProduct;
	}
	
	public List<SysProduct> findList(SysProduct sysProduct) {
		return super.findList(sysProduct);
	}
	
	public Page<SysProduct> findPage(Page<SysProduct> page, SysProduct sysProduct) {
		return super.findPage(page, sysProduct);
	}
	
	@Transactional(readOnly = false)
	public void save(SysProduct sysProduct) {
		if (sysProduct.getProductContent()!=null){
			sysProduct.setProductContent(StringEscapeUtils.unescapeHtml4(sysProduct.getProductContent()));
		}
		if (sysProduct.getProductAbstract()!=null){
			sysProduct.setProductAbstract(StringEscapeUtils.unescapeHtml4(sysProduct.getProductAbstract()));
		}
		if (sysProduct.getAppProductContent()!=null){
			sysProduct.setAppProductContent(StringEscapeUtils.unescapeHtml4(sysProduct.getAppProductContent()));
		}
		super.save(sysProduct);
		for (SysProductDetail sysProductDetail : sysProduct.getSysProductDetailList()){
			if (sysProductDetail.getId() == null){
				continue;
			}
			if (SysProductDetail.DEL_FLAG_NORMAL.equals(sysProductDetail.getDelFlag())){
				if (StringUtils.isBlank(sysProductDetail.getId())){
					sysProductDetail.setSysProductId(sysProduct);
					sysProductDetail.preInsert();
					sysProductDetailDao.insert(sysProductDetail);
				}else{
					sysProductDetail.preUpdate();
					sysProductDetailDao.update(sysProductDetail);
				}
			}else{
				sysProductDetailDao.delete(sysProductDetail);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(SysProduct sysProduct) {
		super.delete(sysProduct);
		sysProductDetailDao.delete(new SysProductDetail(sysProduct));
	}
	
}