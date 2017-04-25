package cn.com.git.udmp.modules.act.service.ext;

import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.git.udmp.modules.act.entity.Act;
import cn.com.git.udmp.modules.sys.dao.RoleDao;
import cn.com.git.udmp.modules.sys.dao.UserDao;
import cn.com.git.udmp.modules.sys.entity.Role;
import cn.com.git.udmp.modules.sys.entity.User;
import cn.com.git.udmp.modules.sys.service.SystemService;

/**
 * 任务随机分配
 * @author guosg
 *
 */
@Service
public class RandomAssignTaskService implements AssignTaskService{
	@Autowired
	RoleDao roleDao;
	@Autowired
	UserDao userDao;
	
	@Override
	public String assign(Act act) {
		Role role = getRoleId(act.getRoleName());
		User user = new User();
		user.setRole(role);
		List<User> users = userDao.findList(user);
		int random = RandomUtils.nextInt(0, users.size()-1);
		User au = users.get(random);
		return au.getLoginName();
	}
	
	private Role getRoleId(String roleName){
		Role r = new Role();
		r.setEnname(roleName);;
		Role role = roleDao.getByEnname(r);
		return role;
	}

}
