package activiti_maven_project.com.git.mybatis.client;

import activiti_maven_project.com.git.mybatis.model.TbSysSecurityUsers;

public interface TbSysSecurityUsersMapper {
    int deleteByPrimaryKey(String userId);

    int insert(TbSysSecurityUsers record);

    int insertSelective(TbSysSecurityUsers record);

    TbSysSecurityUsers selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(TbSysSecurityUsers record);

    int updateByPrimaryKey(TbSysSecurityUsers record);
}