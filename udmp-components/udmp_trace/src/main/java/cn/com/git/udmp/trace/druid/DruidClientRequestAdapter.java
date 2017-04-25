package cn.com.git.udmp.trace.druid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.util.StringUtils;

import com.alibaba.druid.proxy.jdbc.JdbcParameter;
import com.alibaba.druid.proxy.jdbc.StatementProxy;
import com.github.kristofa.brave.ClientRequestAdapter;
import com.github.kristofa.brave.KeyValueAnnotation;
import com.github.kristofa.brave.SpanId;
import com.twitter.zipkin.gen.Endpoint;

public class DruidClientRequestAdapter implements ClientRequestAdapter{
	private String spanName;
	private String sql;
	private StatementProxy statement;
	
	public DruidClientRequestAdapter() {
	
	}
	
	public DruidClientRequestAdapter(String spanName,String sql,StatementProxy statement) {
		this.spanName = spanName;
		this.sql = sql;
		this.statement = statement;
	}
	
	@Override
	public String getSpanName() {
		return spanName;
	}

	@Override
	public void addSpanIdToRequest(SpanId spanId) {
		
	}

	@Override
	public Collection<KeyValueAnnotation> requestAnnotations() {
		List<KeyValueAnnotation> rs = new ArrayList<>();
		if(StringUtils.hasLength(sql)){
			KeyValueAnnotation an = KeyValueAnnotation.create("sql", sql);
			rs.add(an);
		}
		if(statement!=null && statement.getParameters()!=null && statement.getParametersSize()>0){
			StringBuilder sb = new StringBuilder();
			for(JdbcParameter pa  : statement.getParameters().values()){
				sb.append(pa.getValue()).append(";");
			}
			KeyValueAnnotation pan = KeyValueAnnotation.create("params", sb.toString());
			rs.add(pan);
		}
		return rs;
	}

	@Override
	public Endpoint serverAddress() {
		return null;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public void setSpanName(String spanName) {
		this.spanName = spanName;
	}
	
	

}
