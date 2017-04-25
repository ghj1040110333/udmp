

package cn.com.git.udmp.modules.sys.service;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.shiro.session.Session;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.git.udmp.common.persistence.Page;
import cn.com.git.udmp.common.persistence.entity.UserEntity;
import cn.com.git.udmp.common.security.Digests;
import cn.com.git.udmp.common.security.shiro.session.SessionDAO;
import cn.com.git.udmp.common.service.BaseService;
import cn.com.git.udmp.common.service.ServiceException;
import cn.com.git.udmp.common.service.UserService;
import cn.com.git.udmp.common.utils.CacheUtils;
import cn.com.git.udmp.common.utils.Encodes;
import cn.com.git.udmp.common.utils.SpringContextHolder;
import cn.com.git.udmp.common.utils.StringUtils;
import cn.com.git.udmp.common.web.Servlets;
import cn.com.git.udmp.core.config.Global;
import cn.com.git.udmp.modules.sys.dao.MenuDao;
import cn.com.git.udmp.modules.sys.dao.RoleDao;
import cn.com.git.udmp.modules.sys.dao.UserDao;
import cn.com.git.udmp.modules.sys.entity.Menu;
import cn.com.git.udmp.modules.sys.entity.Office;
import cn.com.git.udmp.modules.sys.entity.Role;
import cn.com.git.udmp.modules.sys.entity.User;
import cn.com.git.udmp.modules.sys.utils.LogUtils;
import cn.com.git.udmp.modules.sys.utils.UserUtils;


/**
 * 系统管理，安全相关实体的管理类,包括用户、角色、菜单.
 * @description 
 * @author Spring Cao 
 * @date 2016年8月25日 下午4:55:54
 */
@Service
@Transactional(readOnly = true)
public class SystemService extends BaseService implements InitializingBean,UserService {
	
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private MenuDao menuDao;
	@Autowired
	private SessionDAO sessionDao;
	
	private ActivitIdentityService activitIdentityService;
	
	
	public SessionDAO getSessionDao() {
		return sessionDao;
	}

	//-- User Service --//
	
	/**
	 * @title 查询本机构和角色对应的用户列表
	 * @description
	 * 
	 * @param user 
	 * @return
	 */
	public List<User> getOwnUserList(User user){
	    return userDao.findOwnList(user);
	}
	
	/**
	 * @title 获取用户
	 * @description
	 * 
	 * @param id
	 * @return
	 */
	public User getUser(String id) {
		User user = UserUtils.get(id);
		if(user == null){
			user = new User();
		}
		return user;
	}

	/**
	 * @title 根据登录名获取用户
	 * @description
	 * 
	 * @param loginName
	 * @return
	 */
	public User getUserByLoginName(String loginName) {
		return UserUtils.getByLoginName(loginName);
	}
	
	/**
	 * 
	 * @title 查询用户对象并分页
	 * @description
	 * 
	 * @param page
	 * @param user
	 * @return
	 */
	public Page<User> findUser(Page<User> page, User user) {
		// 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
		user.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "o", "a"));
		// 设置分页参数
		user.setPage(page);
		// 执行分页查询
		page.setList(userDao.findList(user));
		return page;
	}
	
	/**
	 * @title 无分页查询人员列表
	 * @description
	 * 
	 * @param user
	 * @return
	 */
	public List<User> findUser(User user){
		// 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
		user.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "o", "a"));
		List<User> list = userDao.findList(user);
		return list;
	}

	/**
	 * @title 通过部门ID获取用户列表，仅返回用户id和name（树查询用户时用）
	 * @description
	 * 
	 * @param user
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<User> findUserByOfficeId(String officeId) {
		List<User> list = (List<User>)CacheUtils.get(UserUtils.USER_CACHE, UserUtils.USER_CACHE_LIST_BY_OFFICE_ID_ + officeId);
		if (list == null){
			User user = new User();
			user.setOffice(new Office(officeId));
			list = userDao.findUserByOfficeId(user);
			CacheUtils.put(UserUtils.USER_CACHE, UserUtils.USER_CACHE_LIST_BY_OFFICE_ID_ + officeId, list);
		}
		return list;
	}
	
	/**
	 * 
	 * @title 保存或更新一个用户
	 * @description
	 * 
	 * @param user
	 */
	@Transactional(readOnly = false)
	public void saveUser(User user) {
		if (StringUtils.isBlank(user.getId())){
			user.preInsert();
			userDao.insert(user);
		}else{
			// 清除原用户机构用户缓存
			User oldUser = userDao.get(user.getId());
			if (oldUser.getOffice() != null && oldUser.getOffice().getId() != null){
				CacheUtils.remove(UserUtils.USER_CACHE, UserUtils.USER_CACHE_LIST_BY_OFFICE_ID_ + oldUser.getOffice().getId());
			}
			// 更新用户数据
			user.preUpdate();
			userDao.update(user);
		}
		if (StringUtils.isNotBlank(user.getId())){
			// 更新用户与角色关联
			userDao.deleteUserRole(user);
			if (user.getRoleList() != null && user.getRoleList().size() > 0){
				userDao.insertUserRole(user);
			}else{
				throw new ServiceException(user.getLoginName() + "没有设置角色！");
			}
			// 将当前用户同步到Activiti
			if(activitIdentityService!=null)
				activitIdentityService.saveActivitiUser(user);
			// 清除用户缓存
			UserUtils.clearCache(user);
//			// 清除权限缓存
//			systemRealm.clearAllCachedAuthorizationInfo();
		}
	}
	
	/**
	 * 
	 * @title 更新一个用户
	 * @description 并清理缓存
	 * 
	 * @param user
	 */
	@Transactional(readOnly = false)
	public void updateUserInfo(User user) {
		user.preUpdate();
		userDao.updateUserInfo(user);
		// 清除用户缓存
		UserUtils.clearCache(user);
//		// 清除权限缓存
//		systemRealm.clearAllCachedAuthorizationInfo();
	}
	
	/**
	 * 
	 * @title 删除一个用户
	 * @description
	 * 
	 * @param user
	 */
	@Transactional(readOnly = false)
	public void deleteUser(User user) {
		userDao.delete(user);
		// 同步到Activiti
		if(activitIdentityService!=null)
			activitIdentityService.deleteActivitiUser(user);
		// 清除用户缓存
		UserUtils.clearCache(user);
//		// 清除权限缓存
//		systemRealm.clearAllCachedAuthorizationInfo();
	}
	
	/**
	 * 
	 * @title 根据用户编号更新其密码
	 * @description
	 * 
	 * @param id
	 * @param loginName
	 * @param newPassword
	 */
	@Transactional(readOnly = false)
	public void updatePasswordById(String id, String loginName, String newPassword) {
		User user = new User(id);
		user.setPassword(entryptPassword(newPassword));
		userDao.updatePasswordById(user);
		// 清除用户缓存
		user.setLoginName(loginName);
		UserUtils.clearCache(user);
//		// 清除权限缓存
//		systemRealm.clearAllCachedAuthorizationInfo();
	}
	
	/**
	 * 
	 * @title 更新用户登录信息
	 * @description
	 * 
	 * @param user
	 */
	@Transactional(readOnly = false)
	public void updateUserLoginInfo(User user) {
		// 保存上次登录信息
		user.setOldLoginIp(user.getLoginIp());
		user.setOldLoginDate(user.getLoginDate());
		// 更新本次登录信息
		user.setLoginIp(StringUtils.getRemoteAddr(Servlets.getRequest()));
		user.setLoginDate(new Date());
		userDao.updateLoginInfo(user);
	}
	
	/**
	 * 
	 * @title 生成安全的密码
	 * @description 生成随机的16位salt并经过1024次 sha-1 hash
	 * 
	 * @param plainPassword
	 * @return
	 */
	public static String entryptPassword(String plainPassword) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
		return Encodes.encodeHex(salt)+Encodes.encodeHex(hashPassword);
	}
	
	/**
	 * 
	 * @title 验证密码
     * @description
     * 
	 * @param plainPassword 明文密码
	 * @param password 密文密码
	 * @return 验证成功返回true
	 */
	public static boolean validatePassword(String plainPassword, String password) {
		byte[] salt = Encodes.decodeHex(password.substring(0,16));
		byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
		return password.equals(Encodes.encodeHex(salt)+Encodes.encodeHex(hashPassword));
	}
	
	/**
	 * 
	 * @title 获得活动会话
	 * @description
	 * 
	 * @return
	 */
	public Collection<Session> getActiveSessions(){
		return sessionDao.getActiveSessions(false);
	}
	
	//-- Role Service --//
	
	/**
	 * 
	 * @title 根据角色编号获取一个角色对象
	 * @description
	 * 
	 * @param id
	 * @return
	 */
	public Role getRole(String id) {
		return roleDao.get(id);
	}

	/**
	 * 
	 * @title 根据角色名称查询一个角色对象
	 * @description
	 * 
	 * @param name
	 * @return
	 */
	public Role getRoleByName(String name) {
		Role r = new Role();
		r.setName(name);
		return roleDao.getByName(r);
	}
	
	/**
	 * 
	 * @title 根据角色英文名查询一个角色对象
	 * @description
	 * 
	 * @param enname
	 * @return
	 */
	public Role getRoleByEnname(String enname) {
		Role r = new Role();
		r.setEnname(enname);
		return roleDao.getByEnname(r);
	}
	
	/**
	 * 
	 * @title 根据角色编号查询角色对象集合
	 * @description
	 * 
	 * @param role
	 * @return
	 */
	public List<Role> findRole(Role role){
		return roleDao.findList(role);
	}
	
	/**
	 * 
	 * @title 查询此用户所有角色
	 * @description
	 * 
	 * @return
	 */
	public List<Role> findAllRole(){
		return UserUtils.getRoleList();
	}
	
	/**
	 * 
	 * @title 保存一个角色
	 * @description
	 * 
	 * @param role
	 */
	@Transactional(readOnly = false)
	public void saveRole(Role role) {
		if (StringUtils.isBlank(role.getId())){
			role.preInsert();
			roleDao.insert(role);
			// 同步到Activiti
			if(activitIdentityService!=null)
				activitIdentityService.saveActivitiGroup(role);
		}else{
			role.preUpdate();
			roleDao.update(role);
		}
		// 更新角色与菜单关联
		roleDao.deleteRoleMenu(role);
		if (role.getMenuList().size() > 0){
			roleDao.insertRoleMenu(role);
		}
		// 更新角色与部门关联
		roleDao.deleteRoleOffice(role);
		if (role.getOfficeList().size() > 0){
			roleDao.insertRoleOffice(role);
		}
		// 同步到Activiti
		if(activitIdentityService!=null)
			activitIdentityService.saveActivitiGroup(role);
		// 清除用户角色缓存
		UserUtils.removeCache(UserUtils.CACHE_ROLE_LIST);
//		// 清除权限缓存
//		systemRealm.clearAllCachedAuthorizationInfo();
	}

	/**
	 * 
	 * @title 删除一个角色
	 * @description
	 * 
	 * @param role
	 */
	@Transactional(readOnly = false)
	public void deleteRole(Role role) {
		roleDao.delete(role);
		// 同步到Activiti
		if(activitIdentityService!=null)
			activitIdentityService.deleteActivitiGroup(role);
		// 清除用户角色缓存
		UserUtils.removeCache(UserUtils.CACHE_ROLE_LIST);
//		// 清除权限缓存
//		systemRealm.clearAllCachedAuthorizationInfo();
	}
	
	/**
	 * 
	 * @title 移除用户角色
	 * @description
	 * 
	 * @param role
	 * @param user
	 * @return
	 */
	@Transactional(readOnly = false)
	public Boolean outUserInRole(Role role, User user) {
		List<Role> roles = user.getRoleList();
		for (Role e : roles){
			if (e.getId().equals(role.getId())){
				roles.remove(e);
				saveUser(user);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @title 分配用户角色
	 * @description
	 * 
	 * @param role
	 * @param user
	 * @return
	 */
	@Transactional(readOnly = false)
	public User assignUserToRole(Role role, User user) {
		if (user == null){
			return null;
		}
		List<String> roleIds = user.getRoleIdList();
		if (roleIds.contains(role.getId())) {
			return null;
		}
		user.getRoleList().add(role);
		saveUser(user);
		return user;
	}

	//-- Menu Service --//
	
	/**
	 * 
	 * @title 根据菜单编号获取对应的菜单对象
	 * @description
	 * 
	 * @param id
	 * @return
	 */
	public Menu getMenu(String id) {
		return menuDao.get(id);
	}

	/**
	 * 
	 * @title 查询当前用户所有菜单对象集合
	 * @description
	 * 
	 * @return
	 */
	public List<Menu> findAllMenu(){
		return UserUtils.getMenuList();
	}
	
	/**
	 * 
	 * @title 保存新的菜单对象
	 * @description 写入或更新数据库数据并删除缓存数据
	 * 
	 * @param menu
	 */
	@Transactional(readOnly = false)
	public void saveMenu(Menu menu) {
		
		// 获取父节点实体
		menu.setParent(this.getMenu(menu.getParent().getId()));
		
		// 获取修改前的parentIds，用于更新子节点的parentIds
		String oldParentIds = menu.getParentIds(); 
		
		// 设置新的父节点串
		menu.setParentIds(menu.getParent().getParentIds()+menu.getParent().getId()+",");

		// 保存或更新实体
		if (StringUtils.isBlank(menu.getId())){
			menu.preInsert();
			menuDao.insert(menu);
		}else{
			menu.preUpdate();
			menuDao.update(menu);
		}
		
		// 更新子节点 parentIds
		Menu m = new Menu();
		m.setParentIds("%,"+menu.getId()+",%");
		List<Menu> list = menuDao.findByParentIdsLike(m);
		for (Menu e : list){
			e.setParentIds(e.getParentIds().replace(oldParentIds, menu.getParentIds()));
			menuDao.updateParentIds(e);
		}
		// 清除用户菜单缓存
		UserUtils.removeCache(UserUtils.CACHE_MENU_LIST);
//		// 清除权限缓存
//		systemRealm.clearAllCachedAuthorizationInfo();
		// 清除日志相关缓存
		CacheUtils.remove(LogUtils.CACHE_MENU_NAME_PATH_MAP);
	}

	/**
	 * 
	 * @title 更新菜单对象顺序
	 * @description 删除数据库数据并删除缓存数据
	 * 
	 * @param menu
	 */
	@Transactional(readOnly = false)
	public void updateMenuSort(Menu menu) {
		menuDao.updateSort(menu);
		// 清除用户菜单缓存
		UserUtils.removeCache(UserUtils.CACHE_MENU_LIST);
//		// 清除权限缓存
//		systemRealm.clearAllCachedAuthorizationInfo();
		// 清除日志相关缓存
		CacheUtils.remove(LogUtils.CACHE_MENU_NAME_PATH_MAP);
	}

	/**
	 * 
	 * @title 删除菜单对象
	 * @description 删除数据库数据并删除缓存数据
	 * 
	 * @param menu
	 */
	@Transactional(readOnly = false)
	public void deleteMenu(Menu menu) {
		menuDao.delete(menu);
		// 清除用户菜单缓存
		UserUtils.removeCache(UserUtils.CACHE_MENU_LIST);
//		// 清除权限缓存
//		systemRealm.clearAllCachedAuthorizationInfo();
		// 清除日志相关缓存
		CacheUtils.remove(LogUtils.CACHE_MENU_NAME_PATH_MAP);
	}
	
	/**
	 * 
	 * @title 打印版权所述信息
	 * @description
	 * 
	 * @return
	 */
	public static boolean printKeyLoadMessage(){
		StringBuilder sb = new StringBuilder();
		sb.append("\r\n======================================================================\r\n");
		sb.append("\r\n    欢迎使用 "+Global.getConfig("productName")+"  - Powered By http://www.git.com.cn\r\n");
		sb.append("\r\n \r\n");
		sb.append("\r\n GIT Confidential \r\n");
		sb.append("\r\n Licensed Materials - Property of GIT \r\n");
		sb.append("\r\n Copyright (c) 1998-2016 Global InfoTech Corp. All Rights Reserved. \r\n");
		sb.append("\r\n Global InfoTech, Inc. owns the copyright and other intellectual \r\n");
		sb.append("\r\n property rights in this software. \r\n");
		sb.append("\r\n \r\n");
		sb.append("\r\n The source code for this program is not published. \r\n");
		sb.append("\r\n Duplication or use of the Software is not permitted \r\n");
		sb.append("\r\n except as expressly provided in a written agreement \r\n");
		sb.append("\r\n between your company and Global InfoTech, Inc. \r\n");
		sb.append("\r\n======================================================================\r\n");
		System.out.println(sb.toString());
		return true;
	}
	
	///////////////// Synchronized to the Activiti //////////////////
	
	// 已废弃，同步见：ActGroupEntityServiceFactory.java、ActUserEntityServiceFactory.java

	/** 是需要同步Activiti数据，如果从未同步过，则同步数据 */
	private static boolean isSynActivitiIndetity = true;
	
	/**
	 * 
	 * @title Spring的InitializingBean的后置属性设置方法
	 * @description
	 *
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 * @throws Exception
	 */
	public void afterPropertiesSet() throws Exception {
		try{
			activitIdentityService=SpringContextHolder.getBean(ActivitIdentityService.class);
		}catch(Throwable ex){
			activitIdentityService = null;
		}
		
		if (!Global.isSynActivitiIndetity()){
			return;
		}
		if (isSynActivitiIndetity && activitIdentityService !=null){
			isSynActivitiIndetity = false;
	        // 同步角色数据
			if (activitIdentityService.hasGroup()){
			 	Iterator<Role> roles = roleDao.findAllList(new Role()).iterator();
			 	while(roles.hasNext()) {
			 		Role role = roles.next();
			 		activitIdentityService.saveActivitiGroup(role);
			 	}
			}
		 	// 同步用户数据
			
			if (activitIdentityService.hasUser()){
			 	Iterator<User> users = userDao.findAllList(new User()).iterator();
			 	while(users.hasNext()) {
			 		activitIdentityService.saveActivitiUser(users.next());
			 	}
			}
		}
	}

	/**
	 * 
	 * @title 返回一个缓存的UserEntity
	 * @description
	 *
	 * @see cn.com.git.udmp.common.service.UserService#getUserById(java.lang.String)
	 * @param id
	 * @return
	 */
	@Override
	public UserEntity getUserById(String id) {
		return UserUtils.get(id);
	}

    @Transactional(readOnly = false)
	public List<Menu> getAllById(String id){
	    Menu menu = new Menu();
        menu.setId(id);
        List<Menu> findByIdAll = menuDao.findByIdAll(menu);
        List<Menu> list = menuDao.findByParentIdAll(menu);
        for (Menu menu1 : findByIdAll) {
            menu1.setChildCount(list.size());
        }
        for (Menu me : list) {
            String pid = me.getId();
            List<Menu> allById = getAllById(pid);
            if (null == allById) {
                continue;
            }
            findByIdAll.addAll(allById);
        }
        return findByIdAll;
	}
}
