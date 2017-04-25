package cn.com.git.udmp.trace.druid;


import com.alibaba.druid.filter.AutoLoad;
import com.alibaba.druid.filter.FilterEventAdapter;
import com.alibaba.druid.proxy.jdbc.ResultSetProxy;
import com.alibaba.druid.proxy.jdbc.StatementProxy;

import cn.com.git.udmp.trace.BraveFactory;

@AutoLoad
public class BraveDruidFilter extends FilterEventAdapter{
		
		protected void statementExecuteUpdateBefore(StatementProxy statement, String sql) {
			if(BraveFactory.getBrave()!=null)
				BraveFactory.getBrave().clientRequestInterceptor().handle(new DruidClientRequestAdapter("sql update", sql,statement));
	    }

	    protected void statementExecuteUpdateAfter(StatementProxy statement, String sql, int updateCount) {
	    	if(BraveFactory.getBrave()!=null)
	    		BraveFactory.getBrave().clientResponseInterceptor().handle(new DruidClientResponseAdapter(updateCount,null));
	    }

	    protected void statementExecuteQueryBefore(StatementProxy statement, String sql) {
	    	if(BraveFactory.getBrave()!=null)	
	    		BraveFactory.getBrave().clientRequestInterceptor().handle(new DruidClientRequestAdapter("sql query", sql,statement));
	    }

	    protected void statementExecuteQueryAfter(StatementProxy statement, String sql, ResultSetProxy resultSet) {
	    	if(BraveFactory.getBrave()!=null)
	    		BraveFactory.getBrave().clientResponseInterceptor().handle(new DruidClientResponseAdapter(0,null));
	    }

	    protected void statementExecuteBefore(StatementProxy statement, String sql) {
	    	if(BraveFactory.getBrave()!=null)
	    		BraveFactory.getBrave().clientRequestInterceptor().handle(new DruidClientRequestAdapter("sql execute", sql,statement));
	    }

	    protected void statementExecuteAfter(StatementProxy statement, String sql, boolean result) {
	    	if(BraveFactory.getBrave()!=null)
	    		BraveFactory.getBrave().clientResponseInterceptor().handle(new DruidClientResponseAdapter(0,null));
	    }
	    
	    protected void statement_executeErrorAfter(StatementProxy statement, String sql, Throwable error) {
	    	if(BraveFactory.getBrave()!=null)
	    		BraveFactory.getBrave().clientResponseInterceptor().handle(new DruidClientResponseAdapter(0,error.getMessage()));
	    }
	    
}
