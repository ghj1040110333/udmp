package cn.com.git.udmp.trace.dubbo;

import java.net.InetSocketAddress;
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
import com.github.kristofa.brave.ClientRequestAdapter;
import com.github.kristofa.brave.ClientResponseAdapter;
import com.github.kristofa.brave.IdConversion;
import com.github.kristofa.brave.KeyValueAnnotation;
import com.github.kristofa.brave.SpanId;
import com.twitter.zipkin.gen.Endpoint;

import cn.com.git.udmp.trace.BraveFactory;
import cn.com.git.udmp.trace.Constants;
/**
 * dubbo服务提供者的拦截器
 * <p>通过实现Dubbo的{@link Filter}，可以对所有服务调用进行拦截，并进行zipkin的处理。
 * <p>使用是需要在dubbo的配置文件中加入<dubbo:consumer filter="udmpTraceProviderFilter"/>的配置
 * 
 * @author guosg
 *
 */
public class DubboConsumerInterceptor implements Filter{
	static Logger logger = LoggerFactory.getLogger(DubboProviderInterceptor.class);

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		
		if(logger.isDebugEnabled()){
			logger.debug("开始进行调用拦截");
		}
		//请求拦截
		BraveFactory.getBrave().clientRequestInterceptor().handle(new ClientRequestAdapter() {
			@Override
			public Endpoint serverAddress() {
				RpcContext context = RpcContext.getContext();
				InetSocketAddress addr = context.getRemoteAddress();
				int ipv4 = 2130706433;//如果娶不到ip则使用127.0.0.1
				int port = 0;
				if(addr.getAddress()!=null){
					return null;
				}
				
				return Endpoint.create("dubbo client", ipv4,port);
			}
			
			public Collection<KeyValueAnnotation> requestAnnotations() {
				KeyValueAnnotation k = KeyValueAnnotation.create("dubbo url", invoker.getUrl().toFullString());
				return Arrays.asList(k);
			}
			
			public String getSpanName() {
				return "dubbo invoke "+invoker.getInterface().getSimpleName()+"."+RpcContext.getContext().getMethodName();
			}
			@Override
			public void addSpanIdToRequest(SpanId spanId) {
				if(spanId == null){
					invocation.getAttachments().put(Constants.SAMPLED,"0");
				}else{
					invocation.getAttachments().put(Constants.SAMPLED,"1");
					invocation.getAttachments().put(Constants.SPAN_ID,IdConversion.convertToString(spanId.spanId));
					invocation.getAttachments().put(Constants.TRACE_ID, IdConversion.convertToString(spanId.traceId));
		            if (spanId.nullableParentId() != null) {
		            	invocation.getAttachments().put(Constants.PARENT_SPAN_ID,  IdConversion.convertToString(spanId.parentId));
		            }
				}
			}
		});
		Result result =null;
		try{
			result = invoker.invoke(invocation);
		}finally{
			BraveFactory.getBrave().clientResponseInterceptor().handle(new ClientResponseAdapter() {
				public Collection<KeyValueAnnotation> responseAnnotations() {
					return Collections.emptyList();
				}
			});
		}
		if(logger.isDebugEnabled()){
			logger.debug("结束进行调用拦截");
		}
		return result;
	}

}
