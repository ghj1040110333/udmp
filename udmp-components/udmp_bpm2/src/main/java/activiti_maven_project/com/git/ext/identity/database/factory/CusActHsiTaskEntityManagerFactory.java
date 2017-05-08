package activiti_maven_project.com.git.ext.identity.database.factory;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.HistoricTaskInstanceEntityManager;
import org.springframework.beans.factory.annotation.Autowired;

import activiti_maven_project.com.git.ext.identity.database.manager.CusHisTaskInsManager;

public class CusActHsiTaskEntityManagerFactory implements SessionFactory{

	@Autowired(required = true)
	private CusHisTaskInsManager cusHisTaskInsManager;
	
	@Override
	public Class<?> getSessionType() {
		return HistoricTaskInstanceEntityManager.class;
	}

	@Override
	public Session openSession() {
		return cusHisTaskInsManager;
	}

}
