package activiti_maven_project.com.git.service;

import java.net.UnknownHostException;

import org.springframework.stereotype.Service;

import activiti_maven_project.com.git.common.vo.OSInfoVO;
@Service("sysInfoService")
public interface SysInfoService {
	
	public OSInfoVO getOSInfo() throws Exception;

}
