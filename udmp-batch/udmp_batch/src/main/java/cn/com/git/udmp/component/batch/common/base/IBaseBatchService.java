package cn.com.git.udmp.component.batch.common.base;

import java.util.List;
import java.util.Map;

import cn.com.git.udmp.component.batch.common.base.entity.IBaseBatchBO;
import cn.com.git.udmp.component.model.CurrentPage;
import cn.com.git.udmp.common.model.DataObject;
import cn.com.git.udmp.core.logging.ILog;

public interface IBaseBatchService<BO extends IBaseBatchBO, PO extends DataObject> extends ILog{

    void add(BO bo);

    BO update(BO batchAgentBO);

    BO delete(BO batchAgentBO);

    BO find(BO batchAgentBO);

    List<BO> findAll(BO batchAgentBO);

    CurrentPage<BO> queryForPage(BO batchAgentBO, CurrentPage<BO> currentPage);

    boolean batchSave(List<BO> batchAgentBOList);

    boolean batchUpdate(List<BO> batchAgentBOList);

    boolean batchDelete(List<BO> batchAgentBOList);

    List<Map<String, Object>> findAllMap(BO batchAgentBO);

    int findTotal(BO batchAgentBO);

}
