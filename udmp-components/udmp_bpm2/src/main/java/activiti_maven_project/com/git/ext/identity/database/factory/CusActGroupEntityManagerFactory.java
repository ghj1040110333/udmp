package activiti_maven_project.com.git.ext.identity.database.factory;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.GroupIdentityManager;
import org.springframework.beans.factory.annotation.Autowired;

import activiti_maven_project.com.git.ext.identity.database.manager.CusActGroupManager;


public class CusActGroupEntityManagerFactory implements SessionFactory{

	@Autowired(required = true)
	private CusActGroupManager cusActGroupManager;
	
	
	@Override
	public Class<?> getSessionType() {
		return GroupIdentityManager.class;
	}

	@Override
	public Session openSession() {
		return cusActGroupManager;
	}

	
}
