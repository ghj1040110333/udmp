package cn.com.git.udmp.component.batch.common.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.git.udmp.common.model.DataObject;
import cn.com.git.udmp.common.persistence.Page;
import cn.com.git.udmp.common.utils.ClassUtils;
import cn.com.git.udmp.common.utils.StringUtils;
import cn.com.git.udmp.component.batch.common.base.entity.IBaseBatchBO;
import cn.com.git.udmp.component.model.CurrentPage;
import cn.com.git.udmp.core.exception.FrameworkRuntimeException;
import cn.com.git.udmp.utils.bean.BeanUtils;

public abstract class BaseBatchService<BO extends IBaseBatchBO, PO extends DataObject>
		implements IBaseBatchService<BO, PO> {
	private Class<PO> clazzPO;
	private Class<BO> clazzBO;

	@SuppressWarnings("unchecked")
	public BaseBatchService() {
		this.clazzBO = ClassUtils.getSuperClassGenricType(getClass(), 0);
		this.clazzPO = ClassUtils.getSuperClassGenricType(getClass(), 1);
	}

	/**
	 * @description DAO-getter方法
	 * @version
	 * @title
	 * @author anthorName@newchinalife.com
	 * @return Idao dao接口对象
	 */
	public abstract <Q extends IBaseDao<PO>> Q getDao();

	/**
	 * @description 增加数据
	 * @version
	 * @title
	 * @author anthorName@newchinalife.com
	 * @param batchAgentBO
	 *            对象
	 * @return BO
	 */
	@Override
	public void add(BO bo) {
		PO data = BeanUtils.copyProperties(clazzPO, bo);
		setPO(data);
		getDao().add(data.getData());
		bo = cpPoToBo(data.getData(), bo);
	}

	/**
	 * @description 修改数据
	 * @version
	 * @title
	 * @author anthorName@newchinalife.com
	 * @param batchAgentBO
	 *            对象
	 * @return BO
	 */
	@Override
	public BO update(BO batchAgentBO) {
		logger.debug("<======{}--update======>",getClass().getSimpleName());
		PO po = BeanUtils.copyProperties(clazzPO, batchAgentBO);
		setPO(po);
		getDao().update(po.getData());
		return batchAgentBO;
	}

	/**
	 * @description 删除数据
	 * @version
	 * @title
	 * @author anthorName@newchinalife.com
	 * @param batchAgentBO
	 *            对象
	 * @return
	 */
	@Override
	public BO delete(BO batchAgentBO) {
		logger.debug("<======{}--delete======>",getClass().getSimpleName());
		PO po = BeanUtils.copyProperties(clazzPO, batchAgentBO);
		setPO(po);
		getDao().delete(po.getData());
		return batchAgentBO;
	}

	/**
	 * @description 查询单条数据
	 * @version
	 * @title
	 * @author anthorName@newchinalife.com
	 * @param batchAgentBO
	 *            对象
	 * @return BO 查询结果对象
	 */
	@Override
	public BO find(BO batchAgentBO) {
		logger.debug("<======{}--find======>",getClass().getSimpleName());
		PO po = BeanUtils.copyProperties(clazzPO, batchAgentBO);
		setPO(po);
		Map result = getDao().find(po.getData());
		Map tempMap = new HashMap<Object, Object>();
		if (result != null) {
/*	    --lambda syntax
 * 		result.forEach((x, y) -> {
				tempMap.put(StringUtils.toCamelCase(x.toString()), y);
			});
*/			
			for(Object key:result.keySet()){
			    tempMap.put(StringUtils.toCamelCase(key.toString()), result.get(key));
			}
		}
		BO bo;
		try {
			bo = clazzBO.newInstance();
			BeanUtils.copyMap(bo, tempMap);
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new FrameworkRuntimeException(clazzBO.toString() + "初始化失败");
		}
		return bo;
	}

	protected BO cpPoToBo(Map result, BO bo) {
		Map tempMap = new HashMap<Object, Object>();
		if (result != null) {
			/* --lambda syntax
			 * result.forEach((x, y) -> {
				tempMap.put(StringUtils.toCamelCase(x.toString()), y);
			});*/
			
			for(Object key:result.keySet()){
                tempMap.put(StringUtils.toCamelCase(key.toString()), result.get(key));
            }
		}
		try {
			BeanUtils.copyMap(bo, tempMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bo;
	}

	/**
	 * @description 查询所有数据
	 * @version
	 * @title
	 * @author anthorName@newchinalife.com
	 * @param batchAgentBO
	 *            对象
	 * @return List<BO> 查询结果List
	 */
	@Override
	public List<BO> findAll(BO batchAgentBO) {
		logger.debug("<======{}--findAll======>",getClass().getSimpleName());
		PO po = BeanUtils.copyProperties(clazzPO, batchAgentBO);
		setPO(po);
		// 这里可能需要转下驼峰。。
		List<Map> resultMap = getDao().findAll(po.getData());
		// lambda写法一(forEache写法)
		// List<Map> newList = new ArrayList<>();
		// resultMap.forEach(o -> {
		// Map tempMap = new HashMap<>();
		// o.forEach((x, y) -> {
		// tempMap.put(StringUtils.toCamelCase(x.toString()), y);
		// });
		// newList.add(tempMap);
		// });
		// return BeanUtils.copyList(clazzBO, newList);
		// lambda写法二（流式写法）
		@SuppressWarnings("unchecked")
		List<BO> list = changeListMap2Bo(resultMap);
		return list;
	}

	protected List<BO> changeListMap2Bo(List<Map> resultMap) {
	    
	    List<BO> list = new ArrayList<BO>();
	    for(Map e:resultMap){

            Map tempMap = new HashMap();
            for(Object x:e.keySet()){
                tempMap.put(StringUtils.toCamelCase(x.toString()), e.get(x));
            }
            BO bo = null;
            try {
                bo = clazzBO.newInstance();
                BeanUtils.copyMap(bo, tempMap);
            } catch (Exception e1) {
                e1.printStackTrace();
                throw new FrameworkRuntimeException(clazzBO.toString() + "初始化失败");
            }
            list.add(bo);
	    }
	    return list;
	}
	
/*	--LAMBDA写法
 * protected List<BO> changeListMap2Bo(List<Map> resultMap) {
	    return resultMap.stream().map(e -> {
	        @SuppressWarnings("rawtypes")
	        Map tempMap = new HashMap();
	        e.forEach((x, y) -> {
	            tempMap.put(StringUtils.toCamelCase(x.toString()), y);
	        });
	        BO bo = null;
	        try {
	            bo = clazzBO.newInstance();
	            BeanUtils.copyMap(bo, tempMap);
	        } catch (Exception e1) {
	            e1.printStackTrace();
	            throw new FrameworkRuntimeException(clazzBO.toString() + "初始化失败");
	        }
	        return bo;
	    }).collect(Collectors.toList());
	}*/

	/**
	 * @description 查询数据条数
	 * @version
	 * @title
	 * @author anthorName@newchinalife.com
	 * @param batchAgentBO
	 *            对象
	 * @return int 查询结果条数
	 */
	@Override
	public int findTotal(BO batchAgentBO) {
		logger.debug("<======{}--findTotal======>",getClass().getSimpleName());
		PO po = BeanUtils.copyProperties(clazzPO, batchAgentBO);
		setPO(po);
		return getDao().findTotal(po.getData());
	}

	/**
	 * @description 分页查询数据
	 * @version
	 * @title
	 * @author anthorName@newchinalife.com
	 * @param bo
	 *            对象
	 * @param currentPage
	 *            当前页对象
	 * @return CurrentPage<M> 查询结果的当前页对象
	 */
	@Override
	public CurrentPage<BO> queryForPage(BO bo, CurrentPage<BO> currentPage) {
		logger.debug("<======{}--queryForPage======>",getClass().getSimpleName());
		PO batchJobPO = BeanUtils.copyProperties(clazzPO, bo);
		setPO(batchJobPO);
		//查询总数
		int total = getDao().findTotal(batchJobPO.getData());
		// TODO 添加currentPage的转换工作
		Map<String, Object> data = batchJobPO.getData();
//		System.out.println(data);
		int pageNo;
		int pageSize = currentPage.getPageSize();

//		if (currentPage.getPageNo() == 1) { // 如果当前页码是第一页
//			pageNo = 0;
//		} else {
			pageNo = currentPage.getPageNo();
//		}
		int greaterNum = pageNo * currentPage.getPageSize();
		int lessNum = (pageNo + 1) * currentPage.getPageSize();

		data.put("GREATER_NUM", greaterNum);
		data.put("LESS_NUM", lessNum);
		// GREATER_NUM,LESS_NUM
		// Updated by liang -- 为集成jeesite的方言翻页做配置 
		Page<Object> page = new Page<Object>(pageNo, pageSize, currentPage.getTotal());
		data.put("page", page);
		List<Map> results = getDao().queryForPage(data);
		currentPage.setPageItems(changeListMap2Bo(results));
		currentPage.setTotal(total);
		return currentPage;
	}

	/**
	 * @description 批量增加数据
	 * @version
	 * @titles
	 * @author anthorName@newchinalife.com
	 * @param batchAgentBOList
	 *            对象列表
	 * @return boolean 批量添加是否成功
	 */
	@Override
	public boolean batchSave(List<BO> batchAgentBOList) {
		logger.debug("<======{}--batchSave======>",getClass().getSimpleName());
		List<Map> batchAgentPOList = new ArrayList<Map>(batchAgentBOList.size());
		for(int i=0; i<batchAgentBOList.size(); i++){
			PO po = BeanUtils.copyProperties(clazzPO, batchAgentBOList.get(i));
			setPO(po);
			batchAgentPOList.add(po.getData());
		}
		return getDao().batchSave(batchAgentPOList);
	}

	/**
	 * @description 批量修改数据
	 * @version
	 * @title
	 * @author anthorName@newchinalife.com
	 * @param batchAgentBOList
	 *            对象列表
	 * @return boolean 批量修改是否成功
	 */
	@Override
	public boolean batchUpdate(List<BO> batchAgentBOList) {
		logger.debug("<======{}--batchUpdate======>",getClass().getSimpleName());
		List<Map> batchAgentPOList = new ArrayList<Map>(batchAgentBOList.size());
		for(int i=0; i<batchAgentBOList.size(); i++){
			PO po = BeanUtils.copyProperties(clazzPO, batchAgentBOList.get(i));
			setPO(po);
			batchAgentPOList.add(po.getData());
		}
		return getDao().batchUpdate(batchAgentPOList);
	}

	/**
	 * @description 批量删除数据
	 * @version
	 * @title
	 * @author anthorName@newchinalife.com
	 * @param batchAgentBOList
	 *            对象列表
	 * @return boolean 批量删除是否成功
	 */
	@Override
	public boolean batchDelete(List<BO> batchAgentBOList) {
		logger.debug("<======{}--batchDelete======>",getClass().getSimpleName());
		List<Map> batchAgentPOList = new ArrayList<Map>(batchAgentBOList.size());
		for(int i=0; i<batchAgentBOList.size(); i++){
			PO po = BeanUtils.copyProperties(clazzPO, batchAgentBOList.get(i));
			setPO(po);
			batchAgentPOList.add(po.getData());
		}
		return getDao().batchDelete(batchAgentPOList);
	}

	/**
	 * @description 查询所有数据 ，重新组装为map
	 * @version
	 * @title
	 * @author anthorName@newchinalife.com
	 * @param batchAgentBO
	 *            对象
	 * @return List<Map<String, Object>> 查询结果存放到map中
	 */
	@Override
	public List<Map<String, Object>> findAllMap(BO batchAgentBO) {
		logger.debug("<======{}--findAllMap======>",getClass().getSimpleName());
		return getDao().findAllMap(BeanUtils.copyProperties(Map.class, batchAgentBO));
	}

	/**
	 * TODO
	 * 
	 * @description 设置PO的公共属性
	 * @author liuliang liuliang1@git.com.cn
	 * @param po
	 */
	protected void setPO(PO po) {
		po.set("insert_by", "123");
		po.set("update_by", "123");
		po.set("", "");
		po.set("", "");
	}

}
