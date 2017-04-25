package cn.com.git.udmp.component.batch.common.base;

import java.util.List;
import java.util.Map;

import cn.com.git.udmp.component.model.CurrentPage;
import cn.com.git.udmp.common.model.DataObject;

public interface  IBaseDao4Pojo<T extends DataObject> {
    /**
     * @description 增加数据
     * @version
     * @title
     * @author anthorName@newchinalife.com
     * @param batchAgentPO 对象
     * @return BatchAgentPO
     */
     public int add(T t);
     
     /**
     * @description 删除数据
     * @version
     * @title
     * @author anthorName@newchinalife.com
     * @param batchAgentPO 对象
     * @return BatchAgentPO
     */
     public T delete(T t);
     
     /**
     * @description 修改数据
     * @version
     * @title
     * @author anthorName@newchinalife.com
     * @param batchAgentPO 对象
     * @return BatchAgentPO
     */
     public T update(T t);
     
     /**
     * @description 查询单条数据
     * @version
     * @title
     * @author anthorName@newchinalife.com
     * @param batchAgentPO 对象
     * @return BatchAgentPO 查询结果对象
     */
     public T find(T t);
     
     /**
     * @description 查询所有数据
     * @version
     * @title
     * @author anthorName@newchinalife.com
     * @param batchAgentPO 对象
     * @return List<BatchAgentPO> 查询结果List
     */
     public List<T> findAll(T t);
     
     /**
     * @description 查询数据条数
     * @version
     * @title
     * @author anthorName@newchinalife.com
     * @param batchAgentPO 对象
     * @return int 查询结果条数
     */
     public int findBatchAgentTotal(T t);

     /**
     * @description 分页查询数据
     * @version
     * @title
     * @author anthorName@newchinalife.com
     * @param currentPage 当前页对象
     * @return CurrentPage<BatchAgentPO> 查询结果的当前页对象
     */
     public CurrentPage<T> queryBatchAgentForPage(T t, CurrentPage<T> currentPage);

     /**
     * @description 批量增加数据
     * @version
     * @title
     * @author anthorName@newchinalife.com
     * @param batchAgentPOList 对象列表
     * @return boolean 批量添加是否成功
     */
     public boolean batchSave(List<T> list);

     /**
     * @description 批量修改数据
     * @version
     * @title
     * @author anthorName@newchinalife.com
     * @param batchAgentPOList 对象列表
     * @return boolean 批量修改是否成功
     */
     public boolean batchUpdate(List<T> list);

     /**
     * @description 批量删除数据
     * @version
     * @title
     * @author anthorName@newchinalife.com
     * @param batchAgentPOList 对象列表
     * @return boolean 批量删除是否成功
     */
     public boolean batchDelete(List<T> list);

     /**
     * @description 查询所有数据 ，重新组装为map
     * @version
     * @title
     * @author anthorName@newchinalife.com
     * @param batchAgentPO 对象
     * @return List<Map<String, Object>> 查询结果存放到map中
     */
     public List<Map<String, Object>> findAllMap(T t);
     
}
