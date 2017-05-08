package activiti_maven_project.com.git.mybatis.client;

import java.util.List;

import org.springframework.stereotype.Repository;

import activiti_maven_project.com.git.mybatis.model.TbSysSecurityRelUserPos;

@Repository
public interface TbSysSecurityRelUserPosMapper {
    int deleteByPrimaryKey(String relId);

    int insert(TbSysSecurityRelUserPos record);

    int insertSelective(TbSysSecurityRelUserPos record);

    TbSysSecurityRelUserPos selectByPrimaryKey(String relId);

    int updateByPrimaryKeySelective(TbSysSecurityRelUserPos record);

    int updateByPrimaryKey(TbSysSecurityRelUserPos record);
    
    public  List<TbSysSecurityRelUserPos> selectPositionForUserId(String userId);
    
    public List<TbSysSecurityRelUserPos> selectUserForPosition(String positionId);
}