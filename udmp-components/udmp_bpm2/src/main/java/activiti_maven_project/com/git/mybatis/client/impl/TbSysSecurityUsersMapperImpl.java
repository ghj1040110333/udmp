package activiti_maven_project.com.git.mybatis.client.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import activiti_maven_project.com.git.mybatis.client.TbSysSecurityUsersMapper;
import activiti_maven_project.com.git.mybatis.model.TbSysSecurityUsers;
@Repository
public class TbSysSecurityUsersMapperImpl implements TbSysSecurityUsersMapper {
	@Autowired(required=true)
	private SqlSessionTemplate myBatisSession;
	@Override
	public int deleteByPrimaryKey(String userId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(TbSysSecurityUsers record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(TbSysSecurityUsers record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public TbSysSecurityUsers selectByPrimaryKey(String userId) {
		return myBatisSession.selectOne("activiti_maven_project.com.git.mybatis.client.TbSysSecurityUsersMapper.selectByPrimaryKey", userId);
	}
	
	@Override
	public int updateByPrimaryKeySelective(TbSysSecurityUsers record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(TbSysSecurityUsers record) {
		// TODO Auto-generated method stub
		return 0;
	}

}
