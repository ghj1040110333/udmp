package cn.com.git.udmp.modules.act.service;

import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.git.udmp.common.utils.StringUtils;
import cn.com.git.udmp.core.config.Global;
import cn.com.git.udmp.modules.sys.entity.Role;
import cn.com.git.udmp.modules.sys.entity.User;
import cn.com.git.udmp.modules.sys.service.ActivitIdentityService;
import cn.com.git.udmp.modules.sys.service.SystemService;

/**
 * 
 * @author guosg
 *
 */
public class DefaultActivitIdentityService implements ActivitIdentityService{
	
	@Autowired
	private IdentityService identityService;
	
	@Autowired
	private SystemService systemService;
	
	@Override
	public void saveActivitiGroup(Role role) {
		if (!Global.isSynActivitiIndetity()){
			return;
		}
		String groupId = role.getEnname();
		
		// 如果修改了英文名，则删除原Activiti角色
		if (StringUtils.isNotBlank(role.getOldEnname()) && !role.getOldEnname().equals(role.getEnname())){
			identityService.deleteGroup(role.getOldEnname());
		}
		
		Group group = identityService.createGroupQuery().groupId(groupId).singleResult();
		if (group == null) {
			group = identityService.newGroup(groupId);
		}
		group.setName(role.getName());
		group.setType(role.getRoleType());
		identityService.saveGroup(group);
		
		// 删除用户与用户组关系
		List<org.activiti.engine.identity.User> activitiUserList = identityService.createUserQuery().memberOfGroup(groupId).list();
		for (org.activiti.engine.identity.User activitiUser : activitiUserList){
			identityService.deleteMembership(activitiUser.getId(), groupId);
		}

		// 创建用户与用户组关系
		List<User> userList = systemService.findUser(new User(new Role(role.getId())));
		for (User e : userList){
			String userId = e.getLoginName();//ObjectUtils.toString(user.getId());
			// 如果该用户不存在，则创建一个
			org.activiti.engine.identity.User activitiUser = identityService.createUserQuery().userId(userId).singleResult();
			if (activitiUser == null){
				activitiUser = identityService.newUser(userId);
				activitiUser.setFirstName(e.getName());
				activitiUser.setLastName(StringUtils.EMPTY);
				activitiUser.setEmail(e.getEmail());
				activitiUser.setPassword(StringUtils.EMPTY);
				identityService.saveUser(activitiUser);
			}
			identityService.createMembership(userId, groupId);
		}
	}

	public void deleteActivitiGroup(Role role) {
		if (!Global.isSynActivitiIndetity()){
			return;
		}
		if(role!=null) {
			String groupId = role.getEnname();
			identityService.deleteGroup(groupId);
		}
	}

	@Override
	public void saveActivitiUser(User user) {
		if (!Global.isSynActivitiIndetity()){
			return;
		}
		String userId = user.getLoginName();//ObjectUtils.toString(user.getId());
		org.activiti.engine.identity.User activitiUser = identityService.createUserQuery().userId(userId).singleResult();
		if (activitiUser == null) {
			activitiUser = identityService.newUser(userId);
		}
		activitiUser.setFirstName(user.getName());
		activitiUser.setLastName(StringUtils.EMPTY);
		activitiUser.setEmail(user.getEmail());
		activitiUser.setPassword(StringUtils.EMPTY);
		identityService.saveUser(activitiUser);
		
		// 删除用户与用户组关系
		List<Group> activitiGroups = identityService.createGroupQuery().groupMember(userId).list();
		for (Group group : activitiGroups) {
			identityService.deleteMembership(userId, group.getId());
		}
		// 创建用户与用户组关系
		for (Role role : user.getRoleList()) {
	 		String groupId = role.getEnname();
	 		// 如果该用户组不存在，则创建一个
		 	Group group = identityService.createGroupQuery().groupId(groupId).singleResult();
            if(group == null) {
	            group = identityService.newGroup(groupId);
	            group.setName(role.getName());
	            group.setType(role.getRoleType());
	            identityService.saveGroup(group);
            }
			identityService.createMembership(userId, role.getEnname());
		}
	}

	@Override
	public void deleteActivitiUser(User user) {
		if (!Global.isSynActivitiIndetity()){
			return;
		}
		if(user!=null) {
			String userId = user.getLoginName();//ObjectUtils.toString(user.getId());
			identityService.deleteUser(userId);
		}
	}

	@Override
	public boolean hasGroup() {
		List<Group> groupList = identityService.createGroupQuery().list();
		return (groupList!=null && groupList.size()>0)?true:false;
	}

	@Override
	public boolean hasUser() {
		List<org.activiti.engine.identity.User> userList = identityService.createUserQuery().list();
		return (userList!=null && userList.size()>0)?true:false;
	}
	
	
	
	///////////////// Synchronized to the Activiti end //////////////////
}
