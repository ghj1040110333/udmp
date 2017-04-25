package cn.com.git.udmp.trace.druid;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.util.StringUtils;

import com.github.kristofa.brave.ClientResponseAdapter;
import com.github.kristofa.brave.KeyValueAnnotation;

public class DruidClientResponseAdapter implements ClientResponseAdapter{
	private Integer executeCount=0;
	private String exMessage;
	public DruidClientResponseAdapter() {
	}
	
	public DruidClientResponseAdapter(Integer executeCount,String exMessage) {
		this.executeCount = executeCount;
		this.exMessage = exMessage;
	}
	
	@Override
	public Collection<KeyValueAnnotation> responseAnnotations() {
		Collection<KeyValueAnnotation> rs = new ArrayList<>();
		
		if(executeCount!=null && executeCount >0){
			KeyValueAnnotation an = KeyValueAnnotation.create("executeCount",String.valueOf(executeCount));
			rs.add(an);
		}
		
		if(StringUtils.hasLength(exMessage)){
			KeyValueAnnotation an = KeyValueAnnotation.create("exception",exMessage);
			rs.add(an);
		}
		return rs;
	}

	public Integer getExecuteCount() {
		return executeCount;
	}

	public void setExecuteCount(Integer executeCount) {
		this.executeCount = executeCount;
	}

	public String getExMessage() {
		return exMessage;
	}

	public void setExMessage(String exMessage) {
		this.exMessage = exMessage;
	}

}
