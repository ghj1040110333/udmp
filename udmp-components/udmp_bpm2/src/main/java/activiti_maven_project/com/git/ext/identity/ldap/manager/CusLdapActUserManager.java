package activiti_maven_project.com.git.ext.identity.ldap.manager;

import org.activiti.engine.identity.User;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import activiti_maven_project.com.git.mybatis.client.TbSysSecurityUsersMapper;
import activiti_maven_project.com.git.mybatis.model.TbSysSecurityUsers;

@Service("cusLdapActUserManager")
public class CusLdapActUserManager extends UserEntityManager {
	@Autowired
	private TbSysSecurityUsersMapper tbSysSecurityUsersMapperImpl;
	
	static Logger logger = LogManager.getLogger();
	public User findUserById(String userId) {
		UserEntity ue = new UserEntity();
		TbSysSecurityUsers tu=tbSysSecurityUsersMapperImpl.selectByPrimaryKey(userId);
		ue.setFirstName(tu.getUserName());
		ue.setId(tu.getUserId());
		ue.setLastName(StringUtils.EMPTY);
		
		return ue;
	}

}
