package cn.com.git.udmp.component.batch.common.base;

import java.util.List;
import java.util.Map;

import cn.com.git.udmp.component.batch.common.base.entity.IBaseBatchBO;
import cn.com.git.udmp.component.model.CurrentPage;
import cn.com.git.udmp.common.model.DataObject;
import cn.com.git.udmp.utils.bean.BeanUtils;

/**
 * 
 * @description 
 * @author Spring Cao
 * @date 2016年8月30日 上午11:28:52 
 * @param <BO>
 * @param <PO>
 */
public abstract class BaseBatchService4Pojo<BO extends IBaseBatchBO,PO extends DataObject> implements IBaseBatchService<BO,PO> {
    private Class<PO> clazzPO;
    private Class<BO> clazzBO;

    /**
     * @description DAO-getter方法
     * @version
     * @title
     * @return Idao dao接口对象
     */
    public abstract IBaseDao4Pojo<PO> getDao();

    /**
     * @description 增加数据
     * @version
     * @title
     * @param batchAgentBO 对象
     * @return BO
     */
    @Override
    public void add(BO bo) {
        PO data = BeanUtils.copyProperties(clazzPO, bo);
        getDao().add(data);
    }

    /**
     * @description 修改数据
     * @version
     * @title
     * @param batchAgentBO 对象
     * @return BO
     */
    @Override
    public BO update(BO batchAgentBO) {
        logger.debug("<======BatchAgentServiceImpl--updateBatchAgent======>");
        PO batchAgentBackPO = getDao().update(BeanUtils.copyProperties(clazzPO, batchAgentBO));
        BO batchAgentBackBO = BeanUtils.copyProperties(clazzBO, batchAgentBackPO);
        return batchAgentBackBO;
    }

    /**
     * @description 删除数据
     * @version
     * @title
     * @param batchAgentBO 对象
     * @return
     */
    @Override
    public BO delete(BO batchAgentBO) {
        logger.debug("<======BatchAgentServiceImpl--deleteBatchAgent======>");
        PO batchAgentBackPO = getDao().delete(BeanUtils.copyProperties(clazzPO, batchAgentBO));
        BO batchAgentBackBO = BeanUtils.copyProperties(clazzBO, batchAgentBackPO);
        return batchAgentBackBO;
    }

    /**
     * @description 查询单条数据
     * @version
     * @title
     * @param batchAgentBO 对象
     * @return BO 查询结果对象
     */
    @Override
    public BO find(BO batchAgentBO) {
        logger.debug("<======BatchAgentServiceImpl--findBatchAgent======>");
        PO batchAgentBackPO = getDao().find(BeanUtils.copyProperties(clazzPO, batchAgentBO));
        BO batchAgentBackBO = BeanUtils.copyProperties(clazzBO, batchAgentBackPO);
        return batchAgentBackBO;
    }

    /**
     * @description 查询所有数据
     * @version
     * @title
     * @param batchAgentBO 对象
     * @return List<BO> 查询结果List
     */
    @Override
    public List<BO> findAll(BO batchAgentBO) {
        logger.debug("<======BatchAgentServiceImpl--findAllBatchAgent======>");
        PO batchAgentPO = BeanUtils.copyProperties(clazzPO, batchAgentBO);
        return BeanUtils.copyList(clazzBO, getDao().findAll(batchAgentPO));
    }

    /**
     * @description 查询数据条数
     * @version
     * @title
     * @param batchAgentBO 对象
     * @return int 查询结果条数
     */
    @Override
    public int findTotal(BO batchAgentBO) {
        logger.debug("<======BatchAgentServiceImpl--findBatchAgentTotal======>");
        PO batchAgentPO = BeanUtils.copyProperties(clazzPO, batchAgentBO);
        return getDao().findBatchAgentTotal(batchAgentPO);
    }

    /**
     * @description 分页查询数据
     * @version
     * @title
     * @param batchAgentBO 对象
     * @param currentPage 当前页对象
     * @return CurrentPage<M> 查询结果的当前页对象
     */
    @Override
    public CurrentPage<BO> queryForPage(BO batchAgentBO, CurrentPage<BO> currentPage) {
        logger.debug("<======BatchAgentServiceImpl--queryBatchAgentForPage======>");
        PO batchAgentPO = BeanUtils.copyProperties(clazzPO, batchAgentBO);
        return BeanUtils.copyCurrentPage(clazzBO,
                getDao().queryBatchAgentForPage(batchAgentPO, BeanUtils.copyCurrentPage(clazzPO, currentPage)));
    }

    /**
     * @description 批量增加数据
     * @version
     * @title
     * @param batchAgentBOList 对象列表
     * @return boolean 批量添加是否成功
     */
    @Override
    public boolean batchSave(List<BO> batchAgentBOList) {
        logger.debug("<======BatchAgentServiceImpl--batchSaveBatchAgent======>");
        List<PO> batchAgentPOList = BeanUtils.copyList(clazzPO, batchAgentBOList);
        return getDao().batchSave(batchAgentPOList);
    }

    /**
     * @description 批量修改数据
     * @version
     * @title
     * @param batchAgentBOList 对象列表
     * @return boolean 批量修改是否成功
     */
    @Override
    public boolean batchUpdate(List<BO> batchAgentBOList) {
        logger.debug("<======BatchAgentServiceImpl--batchUpdateBatchAgent======>");
        List<PO> batchAgentPOList = BeanUtils.copyList(clazzPO, batchAgentBOList);
        return getDao().batchUpdate(batchAgentPOList);
    }

    /**
     * @description 批量删除数据
     * @version
     * @title
     * @param batchAgentBOList 对象列表
     * @return boolean 批量删除是否成功
     */
    @Override
    public boolean batchDelete(List<BO> batchAgentBOList) {
        logger.debug("<======BatchAgentServiceImpl--batchDeleteBatchAgent======>");
        List<PO> batchAgentPOList = BeanUtils.copyList(clazzPO, batchAgentBOList);
        return getDao().batchDelete(batchAgentPOList);
    }

    /**
     * @description 查询所有数据 ，重新组装为map
     * @version
     * @title
     * @param batchAgentBO 对象
     * @return List<Map<String, Object>> 查询结果存放到map中
     */
    @Override
    public List<Map<String, Object>> findAllMap(BO batchAgentBO) {
        logger.debug("<======BatchAgentServiceImpl--findAllMapBatchAgent======>");
        PO batchAgentPO = BeanUtils.copyProperties(clazzPO, batchAgentBO);
        return getDao().findAllMap(batchAgentPO);
    }
}
