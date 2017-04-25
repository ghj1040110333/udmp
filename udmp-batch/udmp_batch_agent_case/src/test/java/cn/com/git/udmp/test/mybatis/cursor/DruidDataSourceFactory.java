package cn.com.git.udmp.test.mybatis.cursor;

import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

import com.alibaba.druid.pool.DruidDataSource;

/**
   *	@author  liang
   *	@since 2016年9月17日
**/

public class DruidDataSourceFactory extends UnpooledDataSourceFactory {
	public DruidDataSourceFactory() {
	    this.dataSource = new DruidDataSource();
	  }
}
