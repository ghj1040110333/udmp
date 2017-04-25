package cn.com.git.udmp.component.batch.common.base;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;

import cn.com.git.udmp.common.utils.ClassUtils;
import cn.com.git.udmp.component.batch.common.base.entity.IBaseBatchBO;
import cn.com.git.udmp.component.batch.common.base.entity.IBaseBatchVO;
import cn.com.git.udmp.component.model.CurrentPage;
import cn.com.git.udmp.common.model.DataObject;
import cn.com.git.udmp.utils.bean.BeanUtils;

/** 
 * @description ucc的抽象父类  
 * @author liuliang 
 * @date 2015年12月25日 下午1:23:02 
 * @param <VO>
 * @param <BO>
 * @param <PO> 
*/
public abstract class BaseBatchUCC<VO extends IBaseBatchVO,BO extends IBaseBatchBO,PO extends DataObject> implements IBaseBatchUCC<VO, BO, PO>{
    private Class<BO> clazzBO;
    private Class<VO> clazzVO;
    
    @SuppressWarnings("unchecked")
    public BaseBatchUCC(){
        this.clazzVO = ClassUtils.getSuperClassGenricType(getClass(), 0);
        this.clazzBO = ClassUtils.getSuperClassGenricType(getClass(), 1);
    }
    
    /**
     * @description SERVICE-getter方法
     * @version
     * @title
     * @return IBatchAgentService service接口对象
     */
    public abstract <Q extends IBaseBatchService<BO,PO>> Q getService() ;
    
    
    /**
     * @description 增加数据
     * @version
     * @title
     * @param batchVO 对象
     * @return 
     */
    @Override
    public void add(VO batchVO) {
        logger.debug("<======BatchUCC-{}--addBatchAgent======>",getClass().getSimpleName());
        BO batchBO = BeanUtils.copyProperties(clazzBO, batchVO);
        getService().add(batchBO);
        BeanUtils.copyProperties(batchVO, batchBO);
    }
    
    /**
     * @description 修改数据
     * @version
     * @title
     * @param batchVO 对象
     * @return 
     */
    @Override
    public void update(VO batchVO)  {
        logger.debug("<======BatchUCC-{}-updateBatchAgent======>",getClass().getSimpleName());
        BO batchBO = BeanUtils.copyProperties(clazzBO, batchVO);
        getService().update(batchBO);
    }

     /**
     * @description 删除数据
     * @version
     * @title
     * @param batchVO 对象
     * @return boolean 删除是否成功
     */
    @Override
    public void delete(VO batchVO)  {
        logger.debug("<======BatchUCC-{}-deleteBatchAgent======>",getClass().getSimpleName());
        getService().delete(BeanUtils.copyProperties(clazzBO, batchVO));
    }
    
    /**
     * @description 查询单条数据
     * @version
     * @title
     * @param batchVO 对象
     * @return VO 查询结果对象
     */
    @Override
    public VO find(VO batchVO) {
        logger.debug("<======BatchUCC-{}-findBatchAgent======>",getClass().getSimpleName());
        BO batchBackBO = getService().find(BeanUtils.copyProperties(clazzBO, batchVO));
        VO batchBackVO = BeanUtils.copyProperties(clazzVO, batchBackBO);
        return batchBackVO;
    }  
    
    /**
     * @description 查询所有数据
     * @version
     * @title
     * @param batchVO 对象
     * @return List<VO> 查询结果List
     */
    @Override
    public List<VO> findAll(VO batchVO) {
        logger.debug("<======BatchUCC-{}-findAllBatchAgent======>",getClass().getSimpleName());
        BO batchBO = BeanUtils.copyProperties(clazzBO, batchVO);
        return BeanUtils.copyList(clazzVO, getService().findAll(batchBO));
    } 
    
     /**
     * @description 查询数据条数
     * @version
     * @title
     * @param batchVO 对象
     * @return int 查询结果条数
     */
    @Override
    public int findBatchAgentTotal(VO batchVO) {
        logger.debug("<======BatchUCC-{}-findBatchAgentTotal======>",getClass().getSimpleName());
        BO batchBO = BeanUtils.copyProperties(clazzBO, batchVO);
        return getService().findTotal(batchBO);
    }   
    
     /**
     * @description 分页查询数据
     * @version
     * @title
     * @param batchVO 对象
     * @param currentPage 当前页对象
     * @return CurrentPage<VO> 查询结果的当前页对象
     */
    @Override
    public CurrentPage<VO> queryForPage(VO batchVO, CurrentPage<VO> currentPage) {
        logger.debug("<======BatchUCC-{}-queryBatchAgentForPage======>",getClass().getSimpleName());
        BO batchBO = BeanUtils.copyProperties(clazzBO, batchVO);
        return BeanUtils.copyCurrentPage(clazzVO, getService().queryForPage(batchBO, BeanUtils.copyCurrentPage(clazzBO, currentPage)));
    }
    
    /**
     * @description 批量增加数据
     * @version
     * @title
     * @author yangfeiit@newchinalife.com
     * @param batchVOList 对象列表
     * @return boolean 批量添加是否成功
     */
    @Override
    public boolean batchSave(List<VO> batchVOList) {
        logger.debug("<======BatchUCC-{}-batchSaveBatchAgent======>",getClass().getSimpleName());
        return getService().batchSave(BeanUtils.copyList(clazzBO, batchVOList));
    }
    
    /**
     * @description 批量修改数据
     * @version
     * @title
     * @author yangfeiit@newchinalife.com
     * @param batchVOList 对象列表
     * @return boolean 批量修改是否成功
     */
    @Override
    public boolean batchUpdate(List<VO> batchVOList) {
        logger.debug("<======BatchUCC-{}-batchUpdateBatchAgent======>",getClass().getSimpleName());
        return getService().batchUpdate(BeanUtils.copyList(clazzBO, batchVOList));
    }
    
    /**
     * @description 批量删除数据
     * @version
     * @title
     * @param batchVOList 对象列表
     * @return boolean 批量删除是否成功
     */
    @Override
    public boolean batchDelete(List<VO> batchVOList) {
        logger.debug("<======BatchUCC-{}-batchDeleteBatchAgent======>",getClass().getSimpleName());
        return getService().batchDelete(BeanUtils.copyList(clazzBO, batchVOList));
    }
    
    /**
     * @description 查询所有数据 ，重新组装为map
     * @version
     * @title
     * @param batchVO 对象
     * @return List<Map<String, Object>> 查询结果存放到map中
     */
    @Override
    public List<Map<String, Object>> findAllMap(VO batchVO) {
        logger.debug("<======BatchUCC-{}-findAllMapBatchAgent======>",getClass().getSimpleName());
        BO batchBO = BeanUtils.copyProperties(clazzBO, batchVO);
        return getService().findAllMap(batchBO);
    }
    
}
