package activiti_maven_project.com.git.mybatis.client.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import activiti_maven_project.com.git.mybatis.client.SqlQueryMapper;


@Repository("sqlQueryMapperImpl")
public class SqlQueryMapperImpl implements SqlQueryMapper {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired(required = true)
	private SqlSessionTemplate myBatisSession;

	@Override
	public List<Map<String,Object>> countWorkByTemplate(String userId) {
		return myBatisSession.selectList(
				"com.git.baidu.mybatis.client.SqlQueryMapper.countWorkByTemplate",
				userId);
	}

	@Override
	public List<Map<String,Object>> countCandiWorkByTemplate(String userId,String[] groupIds) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("groupIds", groupIds);
		
		
		return myBatisSession.selectList(
				"com.git.baidu.mybatis.client.SqlQueryMapper.countCandiWorkByTemplate",
				map);
	}

	
	

}
