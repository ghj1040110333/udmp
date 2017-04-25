package cn.com.git.udmp.component.batch.common.base;

import java.util.List;
import java.util.Map;

import cn.com.git.udmp.component.batch.common.base.entity.IBaseBatchBO;
import cn.com.git.udmp.component.batch.common.base.entity.IBaseBatchVO;
import cn.com.git.udmp.component.model.CurrentPage;
import cn.com.git.udmp.common.model.DataObject;
import cn.com.git.udmp.core.logging.ILog;

public interface IBaseBatchUCC<VO extends IBaseBatchVO,BO extends IBaseBatchBO,PO extends DataObject> extends ILog{

    void add(VO batchAgentVO);

    void update(VO batchAgentVO);

    void delete(VO batchAgentVO);

    VO find(VO batchAgentVO);

    List<VO> findAll(VO batchAgentVO);

    int findBatchAgentTotal(VO batchAgentVO);

    CurrentPage<VO> queryForPage(VO batchAgentVO, CurrentPage<VO> currentPage);

    boolean batchSave(List<VO> batchAgentVOList);

    boolean batchUpdate(List<VO> batchAgentVOList);

    boolean batchDelete(List<VO> batchAgentVOList);

    List<Map<String, Object>> findAllMap(VO batchAgentVO);


}
