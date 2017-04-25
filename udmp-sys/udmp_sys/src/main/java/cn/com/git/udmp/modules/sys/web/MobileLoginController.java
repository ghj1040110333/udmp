
package cn.com.git.udmp.modules.sys.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.git.udmp.common.service.UserService;
import cn.com.git.udmp.common.web.BaseController;
import cn.com.git.udmp.common.web.MobileResultVO;
import cn.com.git.udmp.common.web.ParentResultVO;
import cn.com.git.udmp.modules.sys.entity.LoginRO;
import cn.com.git.udmp.modules.sys.entity.User;
import cn.com.git.udmp.modules.sys.entity.UserDTO;
import cn.com.git.udmp.modules.sys.security.FormAuthenticationFilter;
import cn.com.git.udmp.modules.sys.security.SystemAuthorizingRealm;
import cn.com.git.udmp.modules.sys.security.UsernamePasswordToken;
import cn.com.git.udmp.modules.sys.utils.UserUtils;

/**
 * 手机登录
 * 
 * @author tangyz
 */
@Controller
@RequestMapping(value = "${mobilePath}/login")
public class MobileLoginController extends BaseController {

	@Autowired
	private SystemAuthorizingRealm systemRealm;
//	@Autowired
//	private AppThirdPartyService appThirdPartyService;
	
	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping(value = { "checkLogin" })
	public MobileResultVO<Object> checkLogin() {
		User user = UserUtils.getUser();
		if (user == null) {
			return setResult(ParentResultVO.ERROR, "会话已失效，请重新登录!", null);
		} else {
			MobileResultVO<Object> resultVO = setResult(ParentResultVO.SUCCESS, "login ok", null);
			UserDTO userDTO=getUserDTO();
//			AppThirdParty appThirdParty=appThirdPartyService.checkThirdParty(UserUtils.getUser());
//			if(appThirdParty==null){
//				appThirdParty=new AppThirdParty();
//				appThirdParty.setUser(UserUtils.getUser());
//				appThirdParty.setRegistrationId(IdGen.randomBase62(40));//极光别名
//				appThirdPartyService.save(appThirdParty);
//				userDTO.setAlias(appThirdParty.getRegistrationId());
//			}else{
//				userDTO.setAlias(appThirdParty.getRegistrationId());
//			}
			resultVO.setBizVO(userDTO);
			return resultVO;
		}
	}
	
	/**
	 * 手机登录
	 * 
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "li" })
	public MobileResultVO<Object> login(@RequestBody LoginRO loginRO, HttpServletRequest request) {
		try {
			Subject subject = SecurityUtils.getSubject();
			if (subject.isAuthenticated()) {
				//return setResult(ResultVO.SUCCESS, "already login", null);
				MobileResultVO<Object> resultVO =setResult(ParentResultVO.SUCCESS, "already login", null);
				UserDTO userDTO=getUserDTO();
				resultVO.setBizVO(userDTO);
				return resultVO;
			}

			// TODO 如果用户已登录，先踢出
			// ShiroSecurityHelper.kickOutUser(user.getUsername());

			boolean rememberMe = true; // WebUtils.isTrue(request, FormAuthenticationFilter.DEFAULT_REMEMBER_ME_PARAM);
			String host = request.getRemoteHost();
			String captcha = WebUtils.getCleanParam(request, FormAuthenticationFilter.DEFAULT_CAPTCHA_PARAM);
			String agent=request.getHeader("user-agent").substring(0, request.getHeader("user-agent").indexOf("/"));
//			System.out.println(agent);
			AuthenticationToken token = new UsernamePasswordToken(loginRO.getPhone(), loginRO.getPasswd().toCharArray(), rememberMe, host, captcha, true,agent );
			
			subject.login(token); // 登录
		} catch (UnknownAccountException e) {
			return setResult(ParentResultVO.ERROR, "用户或密码错误", null);
		} catch (IncorrectCredentialsException e) {
			return setResult(ParentResultVO.ERROR, "密码不正确", null);
		} catch (Exception e) {
			e.printStackTrace();
			return setResult(ParentResultVO.ERROR, "登录出错，请稍后再试", null);
		} finally {
			systemRealm.clearCachedAuthorizationInfo(loginRO.getPhone());
		}
		MobileResultVO<Object> resultVO=setResult(ParentResultVO.SUCCESS, "login ok", null);
		UserDTO userDTO=getUserDTO();
//		AppThirdParty appThirdParty=appThirdPartyService.checkThirdParty(UserUtils.getUser());
//		if(appThirdParty==null){
//			appThirdParty=new AppThirdParty();
//			appThirdParty.setUser(UserUtils.getUser());
//			appThirdParty.setRegistrationId(IdGen.randomBase62(40));//极光别名
//			appThirdPartyService.save(appThirdParty);
//			userDTO.setAlias(appThirdParty.getRegistrationId());
//		}else{
//			userDTO.setAlias(appThirdParty.getRegistrationId());
//		}
		resultVO.setBizVO(userDTO);
		return resultVO;
	}
	
	/**
	 * 退出登录
	 * 
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "lo" })
	public MobileResultVO<Object> logout(HttpServletRequest req) {
		SecurityUtils.getSubject().logout();
		return setResult(ParentResultVO.SUCCESS, "logout ok", null);
	}
	
//	@ResponseBody
//	@RequestMapping(value = { "getUserForm" })
//	public  MobileResultVO<Object> getUserForm(HttpServletRequest req){
//		MobileResultVO<Object> resultVO=setResult(ResultVO.SUCCESS, "ok", null);
//		User user = UserUtils.getUser();
//		UserForm userForm = userService.queryUserForm(user.getCrmUser().getUserId());
//		resultVO.setBizVO(userForm);
//		return resultVO;
//	}
	
	private UserDTO getUserDTO(){
		User user = UserUtils.getUser();
		UserDTO userDTO=new UserDTO();
		
		BeanUtils.copyProperties(user, userDTO);
		
//		userDTO.setUserId(user.getId());
//		UserUtils.setUserPhoto(userDTO);
		return userDTO;
	}

}
