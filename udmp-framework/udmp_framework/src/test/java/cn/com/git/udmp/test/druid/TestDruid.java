package cn.com.git.udmp.test.druid;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;

public class TestDruid {
	
	private DataSource dataSource;
	
	public static void getDataSource() throws Exception{
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUsername("");
		dataSource.setPassword("");
		dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/test");
		dataSource.setInitialSize(5);
		dataSource.setMinIdle(1);
		dataSource.setMaxActive(10);
		// 启用监控统计功能
		// dataSource.setFilters("stat");// for mysql
		 dataSource.setPoolPreparedStatements(false);
//		 DruidPooledConnection conn = new DruidPooledConnection(new DruidConnectionHolder(dataSource, conn));
		
	}
}
