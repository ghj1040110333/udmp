package cn.com.git.udmp.trace.collector;

import org.springframework.beans.factory.FactoryBean;

import com.github.kristofa.brave.EmptySpanCollectorMetricsHandler;
import com.github.kristofa.brave.http.HttpSpanCollector;
import com.github.kristofa.brave.http.HttpSpanCollector.Config;
import com.github.kristofa.brave.http.HttpSpanCollector.Config.Builder;

/**
 * HttpSpanCollector工厂类
 * <p>不建议在生产环境中使用，生产环境中建议使用kafka
 * @author guosg
 *
 */
public class HttpSpanCollectorFactory implements FactoryBean<HttpSpanCollector>{
	
	private String url;
	
	@Override
	public HttpSpanCollector getObject() throws Exception {
		EmptySpanCollectorMetricsHandler empty = new EmptySpanCollectorMetricsHandler();
		Builder builder2 =Config.builder();
		Config config = builder2.build();
		HttpSpanCollector httpSpanCollector = HttpSpanCollector.create(url,config, empty);
		return httpSpanCollector;
	}

	@Override
	public Class<?> getObjectType() {
		return HttpSpanCollector.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
