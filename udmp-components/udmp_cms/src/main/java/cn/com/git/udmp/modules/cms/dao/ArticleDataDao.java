/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.com.git.udmp.modules.cms.dao;

import cn.com.git.udmp.common.persistence.CrudDao;
import cn.com.git.udmp.common.persistence.annotation.MyBatisDao;
import cn.com.git.udmp.modules.cms.entity.ArticleData;

/**
 * 文章DAO接口
 * @author ThinkGem
 * @version 2013-8-23
 */
@MyBatisDao
public interface ArticleDataDao extends CrudDao<ArticleData> {
	
}
