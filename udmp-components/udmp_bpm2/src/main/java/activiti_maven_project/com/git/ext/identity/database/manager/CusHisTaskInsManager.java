package activiti_maven_project.com.git.ext.identity.database.manager;

import javax.servlet.http.HttpSession;

import org.activiti.engine.impl.db.PersistentObject;
import org.activiti.engine.impl.persistence.entity.HistoricTaskInstanceEntity;
import org.activiti.engine.impl.persistence.entity.HistoricTaskInstanceEntityManager;
import org.springframework.stereotype.Service;

import activiti_maven_project.com.git.user.Users;

import org.springframework.beans.factory.annotation.Autowired;
@Service("cusHisTaskInsManager")
public class CusHisTaskInsManager extends HistoricTaskInstanceEntityManager{
	
//	@Autowired  
//	private HttpSession session;  
//
//	  public void insert(PersistentObject persistentObject) {
//		  
//		  Users user=(Users)session.getAttribute("user");
//		  String usercd=user.getUserCd();
//		  String orgcd=user.getOrgCd();
//		  
//		  HistoricTaskInstanceEntity ht=(HistoricTaskInstanceEntity)persistentObject;
//		  ht.setAssignee(orgcd);
//		  ht.setOwner(orgcd);
//		  getDbSqlSession().insert(ht);
//	  }
}
