package cn.com.git.udmp.impl.batch.task.ucc;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import cn.com.git.udmp.component.model.CurrentPage;
import cn.com.git.udmp.core.logging.ILog;
import cn.com.git.udmp.impl.batch.task.vo.BatchTaskParamVO;
import cn.com.git.udmp.impl.batch.task.vo.BatchTaskVO;


/**
 * 
 * @description IBatchTaskUCC接口
 * @author Spring Cao
 * @date 2016年8月30日 上午11:52:23
 */
@Transactional(readOnly=false)
public interface IBatchTaskUCC extends ILog{ 
    
    /**
     * @title
     * @description 逻辑删除作业
     * 
     * @param batchTaskVO 
    */
    public void delete(BatchTaskVO batchTaskVO);
    
    /**
     * @description 增加数据
     * @version
     * @title
     * @param batchTaskVO 对象
     * @return BatchTaskVO 添加结果
     */
	public void add(BatchTaskVO batchTaskVO,List<BatchTaskParamVO> batchTaskParamVO);
	
 	/**
     * @description 修改数据
     * @version
     * @title
     * @param batchTaskVO 对象
     * @return BatchTaskVO 修改结果
     */
 	public BatchTaskVO update(BatchTaskVO batchTaskVO,List<BatchTaskParamVO> batchTaskParamVOList);
 	
 	/**
     * @description 查询单条数据
     * @version
     * @title
     * @param batchTaskVO 对象
     * @return BatchTaskVO 查询结果对象
     */
 	public BatchTaskVO find(BatchTaskVO batchTaskVO);
 	
 	/**
     * @description 查询所有数据
     * @version
     * @title
     * @param batchTaskVO 对象
     * @return List<BatchTaskVO> 查询结果List
     */
 	public List<BatchTaskVO> findAll(BatchTaskVO batchTaskVO);
 	
 	/**
     * @description 查询数据条数
     * @version
     * @title
     * @param batchTaskVO 对象
     * @return int 查询结果条数
     */
 	public int findTotal(BatchTaskVO batchTaskVO);
 	
 	 /**
     * @description 分页查询数据
     * @version
     * @title
     * @param batchTaskVO 对象
     * @param currentPage 当前页对象
     * @return CurrentPage<BatchTaskVO> 查询结果的当前页对象
     */
 	public CurrentPage<BatchTaskVO> queryForPage(BatchTaskVO batchTaskVO, CurrentPage<BatchTaskVO> currentPage);
 	
 	/**
     * @description 批量增加数据
     * @version
     * @title
     * @param batchTaskVOList 对象列表
     * @return boolean 批量添加是否成功
     */
 	public boolean batchSave(List<BatchTaskVO> batchTaskVOList);
 	
 	/**
     * @description 批量修改数据
     * @version
     * @title
     * @param batchTaskVOList 对象列表
     * @return boolean 批量修改是否成功
     */
 	public boolean batchUpdate(List<BatchTaskVO> batchTaskVOList);
 	
 	/**
     * @description 批量删除数据
     * @version
     * @title
     * @param batchTaskVOList 对象列表
     * @return boolean 批量删除是否成功
     */
 	public boolean batchDelete(List<BatchTaskVO> batchTaskVOList,List<BatchTaskParamVO> batchTaskParamVOList);
 	
 	/**
     * @description 查询所有数据 ，重新组装为map
     * @version
     * @title
     * @param batchTaskVO 对象
     * @return List<Map<String, Object>> 查询结果存放到map中
     */
 	public List<Map<String, Object>> findAllMap(BatchTaskVO batchTaskVO);
}
