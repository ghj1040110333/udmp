package cn.com.git.udmp.impl.batch.task.dao;

import cn.com.git.udmp.common.persistence.annotation.MyBatisDao;
import cn.com.git.udmp.component.batch.common.base.IBaseDao;
import cn.com.git.udmp.impl.batch.task.po.BatchTaskPO;


/** 
 * @description IBatchTaskDao接口
 * @author anthorName@newchinalife.com 
 * @date 2015-02-10 10:45:10  
 */
@MyBatisDao
 public interface IBatchTaskDAO extends IBaseDao<BatchTaskPO>{}