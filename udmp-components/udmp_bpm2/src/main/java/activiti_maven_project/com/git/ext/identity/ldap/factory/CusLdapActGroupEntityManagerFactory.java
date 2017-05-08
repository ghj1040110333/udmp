package activiti_maven_project.com.git.ext.identity.ldap.factory;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.GroupIdentityManager;
import org.springframework.beans.factory.annotation.Autowired;

import activiti_maven_project.com.git.ext.identity.ldap.manager.CusLdapActGroupManager;


public class CusLdapActGroupEntityManagerFactory implements SessionFactory{

	@Autowired(required = true)
	private CusLdapActGroupManager cusActGroupManager;
	
	
	@Override
	public Class<?> getSessionType() {
		return GroupIdentityManager.class;
	}

	@Override
	public Session openSession() {
		return cusActGroupManager;
	}

	
}
