package cn.com.git.udmp.trace.dubbo;


import static com.github.kristofa.brave.IdConversion.convertToLong;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;
import com.github.kristofa.brave.KeyValueAnnotation;
import com.github.kristofa.brave.ServerRequestAdapter;
import com.github.kristofa.brave.ServerResponseAdapter;
import com.github.kristofa.brave.SpanId;
import com.github.kristofa.brave.TraceData;
import cn.com.git.udmp.trace.BraveFactory;
import cn.com.git.udmp.trace.Constants;
/**
 * dubbo服务提供者的拦截器
 * <p>通过实现Dubbo的{@link Filter}，可以对所有暴露的服务进行拦截，并进行zipkin的处理。
 * <p>使用是需要在dubbo的配置文件中加入<dubbo:provider filter="udmpTraceProviderFilter"/>的配置
 * 
 * @author guosg
 *
 */
public class DubboProviderInterceptor implements Filter{
	static Logger logger = LoggerFactory.getLogger(DubboProviderInterceptor.class);
	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		if(logger.isDebugEnabled()){
			logger.debug("开始拦截服务方信息");
		}
		
		BraveFactory.getBrave().serverRequestInterceptor().handle(new ServerRequestAdapter() {
			@Override
			public Collection<KeyValueAnnotation> requestAnnotations() {
				KeyValueAnnotation k = KeyValueAnnotation.create("dubbo url", invoker.getUrl().toFullString());
				return Arrays.asList(k);
			}
			@Override
			public TraceData getTraceData() {
				String sampled = invocation.getAttachment(Constants.SAMPLED);
				if("0".equals(sampled)){
					 return TraceData.builder().sample(false).build();
				}else{
					final String parentSpanId = invocation.getAttachment(Constants.PARENT_SPAN_ID);
	                final String traceId = invocation.getAttachment(Constants.TRACE_ID);
	                final String spanId = invocation.getAttachment(Constants.SPAN_ID);

	                if (traceId != null && spanId != null) {
	                    SpanId span = getSpanId(traceId, spanId, parentSpanId);
	                    return TraceData.builder().sample(true).spanId(span).build();
	                }
				}
				
				return TraceData.builder().build();
			}
			@Override
			public String getSpanName() {
				return "dubbo invoke "+invoker.getInterface().getSimpleName()+"."+RpcContext.getContext().getMethodName();
			}
		});
		Result result = null;
		try{
			result = invoker.invoke(invocation);
		}finally{
			BraveFactory.getBrave().serverResponseInterceptor().handle(new ServerResponseAdapter(){
				@Override
				public Collection<KeyValueAnnotation> responseAnnotations() {
					return Collections.emptyList();
				}
			});
		}
		
		if(logger.isDebugEnabled()){
			logger.debug("结束拦截服务方信息");
		}
		return result;
	}
	
	 private SpanId getSpanId(String traceId, String spanId, String parentSpanId) {
	        return SpanId.builder()
	            .traceId(convertToLong(traceId))
	            .spanId(convertToLong(spanId))
	            .parentId(parentSpanId == null ? null : convertToLong(parentSpanId)).build();
	   }

}
