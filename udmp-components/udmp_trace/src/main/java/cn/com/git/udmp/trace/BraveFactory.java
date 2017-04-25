package cn.com.git.udmp.trace;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import com.github.kristofa.brave.Brave;
import com.github.kristofa.brave.LoggingSpanCollector;
import com.github.kristofa.brave.Sampler;
import com.github.kristofa.brave.SpanCollector;
import com.github.kristofa.brave.http.HttpSpanCollector;

/**
 * Brave工厂
 * @author guosg
 *
 */
public class BraveFactory implements InitializingBean,FactoryBean<Brave>{
	public static final Logger logger = LoggerFactory.getLogger(BraveFactory.class);
	private static Brave brave;
	
	/**
	 * 采样率
	 * <p>性能相关的指标，它表示通过请求多少次服务需要记录一次性能监控。例如1＝全采样 0.01＝一百笔采样一次
	 * <p>如果为空使用1作为默认值
	 */
	private float simpler = 1F;
	
	
	/**
	 * span收集器
	 * <p>通过这个日志收集器发送span信息到zipkin服务器
	 * <p>支持多种日志收集器 {@link HttpSpanCollector},{@link KafkaSpanCollector},{@link LoggingSpanCollector}等等
	 * <p>默认使用{@link LoggingSpanCollector}收集器
	 */
	private SpanCollector spanCollector;
	
	/**
	 * 服务的名称
	 * <p>
	 */
	private String serviceName;
	
	
	@Override
	public Brave getObject() throws Exception {
		if(brave == null){
			instanceBrave();
		}
		return BraveFactory.brave;
	}

	@Override
	public Class<?> getObjectType() {
		return Brave.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		if(brave == null){
			instanceBrave();
		}
		
	}
	/**
	 * 实例化一个Brave
	 */
	private synchronized void instanceBrave(){
		if(brave !=null){
			return;
		}
		if(spanCollector == null){
			spanCollector = new LoggingSpanCollector();
		}
		if(simpler == 0){
			simpler = 1f;
		}
		
		if(serviceName == null){
			serviceName = "unknown";
		}
		
		Brave.Builder builder = new Brave.Builder(serviceName);
		BraveFactory.brave = builder
		  .spanCollector(spanCollector)
		  .traceSampler(Sampler.create(simpler))
		  .build();
	}
	
	public static Brave getBrave(){
		return brave;
	}
	

	/**
	 * 获得采样率
	 * <p>性能相关的指标，它表示通过请求多少次服务需要记录一次性能监控。例如1＝全采样 0.01＝一百笔采样一次
	 * <p>如果为空使用1作为默认值
	 */
	public float getSimpler() {
		return simpler;
	}

	/**
	 * 设置采样率
	 * <p>性能相关的指标，它表示通过请求多少次服务需要记录一次性能监控。例如1＝全采样 0.01＝一百笔采样一次
	 * <p>如果为空使用1作为默认值
	 */
	public void setSimpler(float simpler) {
		this.simpler = simpler;
	}



	/**
	 * 获取span收集器
	 * <p>通过这个日志收集器发送span信息到zipkin服务器
	 * <p>支持多种日志收集器 {@link HttpSpanCollector},{@link KafkaSpanCollector},{@link LoggingSpanCollector}等等
	 * <p>默认使用{@link LoggingSpanCollector}收集器
	 */
	public SpanCollector getSpanCollector() {
		return spanCollector;
	}

	/**
	 * 设置span收集器
	 * <p>通过这个日志收集器发送span信息到zipkin服务器
	 * <p>支持多种日志收集器 {@link HttpSpanCollector},{@link KafkaSpanCollector},{@link LoggingSpanCollector}等等
	 * <p>默认使用{@link LoggingSpanCollector}收集器
	 */
	public void setSpanCollector(SpanCollector spanCollector) {
		this.spanCollector = spanCollector;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	
}
