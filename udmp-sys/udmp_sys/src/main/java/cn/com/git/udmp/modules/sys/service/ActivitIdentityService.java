
package cn.com.git.udmp.modules.sys.service;

import cn.com.git.udmp.modules.sys.entity.Role;
import cn.com.git.udmp.modules.sys.entity.User;

public interface ActivitIdentityService {

	void saveActivitiUser(User user);

	void deleteActivitiUser(User user);

	void saveActivitiGroup(Role role);

	void deleteActivitiGroup(Role role);
	
	boolean hasGroup();

	boolean hasUser();


}
