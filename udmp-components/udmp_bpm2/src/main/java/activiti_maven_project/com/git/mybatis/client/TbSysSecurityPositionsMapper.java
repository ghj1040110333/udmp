package activiti_maven_project.com.git.mybatis.client;

import activiti_maven_project.com.git.mybatis.model.TbSysSecurityPositions;

public interface TbSysSecurityPositionsMapper {
    int deleteByPrimaryKey(String positionId);

    int insert(TbSysSecurityPositions record);

    int insertSelective(TbSysSecurityPositions record);

    TbSysSecurityPositions selectByPrimaryKey(String positionId);

    int updateByPrimaryKeySelective(TbSysSecurityPositions record);

    int updateByPrimaryKey(TbSysSecurityPositions record);
}