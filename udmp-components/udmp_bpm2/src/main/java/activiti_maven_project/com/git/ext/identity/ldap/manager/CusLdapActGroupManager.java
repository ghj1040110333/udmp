package activiti_maven_project.com.git.ext.identity.ldap.manager;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.GroupQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import activiti_maven_project.com.git.mybatis.client.TbSysSecurityRelUserPosMapper;
import activiti_maven_project.com.git.mybatis.model.TbSysSecurityRelUserPos;

@Service("cusLdapActGroupManager")
public class CusLdapActGroupManager extends  GroupEntityManager{
	@Autowired
	private TbSysSecurityRelUserPosMapper tbSysSecurityRelUserPosMapperImpl;
	
	static Logger logger = LogManager.getLogger();
	
	  public List<Group> findGroupsByUser(String userId) {
		  List<Group> list=new ArrayList<Group>();
		  List<TbSysSecurityRelUserPos>  ps= tbSysSecurityRelUserPosMapperImpl.selectPositionForUserId(userId);
		  for(TbSysSecurityRelUserPos post:ps){
			  GroupEntity group =new GroupEntity();
			  group.setId(post.getPositionCd());
			  group.setName(post.getPositionName());
			  list.add(group);
		  }
		  return list;
	  }

	  public List<Group> findGroupByQueryCriteria(GroupQueryImpl query, Page page) {
		  logger.debug("------------findGroupByQueryCriteria--------query:{}-----",query);
		  List<Group> list=new ArrayList<Group>();
		  List<TbSysSecurityRelUserPos>  ps= tbSysSecurityRelUserPosMapperImpl.selectPositionForUserId(query.getUserId());
		  for(TbSysSecurityRelUserPos post:ps){
			  GroupEntity group =new GroupEntity();
			  group.setId(post.getPositionCd());
			  group.setName(post.getPositionName());
			  list.add(group);
		  }
		  return list;
	  }
}
