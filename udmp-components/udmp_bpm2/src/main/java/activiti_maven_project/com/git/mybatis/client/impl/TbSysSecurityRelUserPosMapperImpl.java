package activiti_maven_project.com.git.mybatis.client.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import activiti_maven_project.com.git.mybatis.client.TbSysSecurityRelUserPosMapper;
import activiti_maven_project.com.git.mybatis.model.TbSysSecurityRelUserPos;
@Repository
public class TbSysSecurityRelUserPosMapperImpl implements TbSysSecurityRelUserPosMapper {
	@Autowired(required=true)
	private SqlSessionTemplate myBatisSession;
	@Override
	public int deleteByPrimaryKey(String relId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(TbSysSecurityRelUserPos record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(TbSysSecurityRelUserPos record) {
		// TODO Auto-generated method stubpublic TbSysSecurityRelUserPos selectPositionForUserId(String userId)
		return 0;
	}

	@Override
	public TbSysSecurityRelUserPos selectByPrimaryKey(String relId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(TbSysSecurityRelUserPos record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(TbSysSecurityRelUserPos record) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	@Override
	public List<TbSysSecurityRelUserPos> selectPositionForUserId(String userId) {
		return myBatisSession.selectList("activiti_maven_project.com.git.mybatis.client.TbSysSecurityRelUserPosMapper.selectPositionForUserId", userId);
	}
	
	@Override
	public List<TbSysSecurityRelUserPos> selectUserForPosition(String positionId) {
		return myBatisSession.selectList("activiti_maven_project.com.git.mybatis.client.TbSysSecurityRelUserPosMapper.selectUserForPosition", positionId);
	}
}
