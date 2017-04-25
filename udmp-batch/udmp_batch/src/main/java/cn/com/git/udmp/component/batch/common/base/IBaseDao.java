package cn.com.git.udmp.component.batch.common.base;

import java.util.List;
import java.util.Map;

import cn.com.git.udmp.common.model.DataObject;

public interface  IBaseDao<T extends DataObject> {
    /**
     * @description 增加数据
     * @version
     * @title
     * @author anthorName@newchinalife.com
     * @param batchAgentPO 对象
     * @return BatchAgentPO
     */
     public int add(Map t);
     
     /**
     * @description 删除数据
     * @version
     * @title
     * @author anthorName@newchinalife.com
     * @param batchAgentPO 对象
     * @return BatchAgentPO
     */
     public void delete(Map t);
     
     /**
     * @description 修改数据
     * @version
     * @title
     * @author anthorName@newchinalife.com
     * @param batchAgentPO 对象
     * @return BatchAgentPO
     */
     public int update(Map t);
     
     /**
     * @description 查询单条数据
     * @version
     * @title
     * @author anthorName@newchinalife.com
     * @param batchAgentPO 对象
     * @return BatchAgentPO 查询结果对象
     */
     public Map find(Map t);
     
     /**
     * @description 查询所有数据
     * @version
     * @title
     * @author anthorName@newchinalife.com
     * @param batchAgentPO 对象
     * @return List<BatchAgentPO> 查询结果List
     */
     public List<Map> findAll(Map t);
     
     /**
     * @description 查询数据条数
     * @version
     * @title
     * @author anthorName@newchinalife.com
     * @param batchAgentPO 对象
     * @return int 查询结果条数
     */
     public int findTotal(Map t);

     /**
     * @description 分页查询数据
     * @version
     * @title
     * @author anthorName@newchinalife.com
     * @return CurrentPage<BatchAgentPO> 查询结果的当前页对象
     */
     public List<Map> queryForPage(Map t);

     /**
     * @description 批量增加数据
     * @version
     * @title
     * @author anthorName@newchinalife.com
     * @param <PO>
     * @param batchAgentPOList 对象列表
     * @return boolean 批量添加是否成功
     */
     public <PO> boolean batchSave(List<PO> batchAgentPOList);

     /**
     * @description 批量修改数据
     * @version
     * @title
     * @author anthorName@newchinalife.com
     * @param <PO>
     * @param batchAgentPOList 对象列表
     * @return boolean 批量修改是否成功
     */
     public <PO> boolean batchUpdate(List<PO> list);

     /**
     * @description 批量删除数据
     * @version
     * @title
     * @author anthorName@newchinalife.com
     * @param <PO>
     * @param batchAgentPOList 对象列表
     * @return boolean 批量删除是否成功
     */
     public <PO> boolean batchDelete(List<PO> list);

     /**
     * @description 查询所有数据 ，重新组装为map
     * @version
     * @title
     * @author anthorName@newchinalife.com
     * @param batchAgentPO 对象
     * @return List<Map<String, Object>> 查询结果存放到map中
     */
     public List<Map<String, Object>> findAllMap(Map t);
     
}
