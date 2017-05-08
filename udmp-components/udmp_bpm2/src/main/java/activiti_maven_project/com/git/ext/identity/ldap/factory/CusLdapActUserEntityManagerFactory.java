package activiti_maven_project.com.git.ext.identity.ldap.factory;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.UserIdentityManager;
import org.springframework.beans.factory.annotation.Autowired;

import activiti_maven_project.com.git.ext.identity.ldap.manager.CusLdapActUserManager;



public class CusLdapActUserEntityManagerFactory implements SessionFactory{
	
	@Autowired(required = true)
	private CusLdapActUserManager cusActUserManager;

	@Override
	public Class<?> getSessionType() {
		return UserIdentityManager.class;
	}

	@Override
	public Session openSession() {
		return cusActUserManager;
	}

}
