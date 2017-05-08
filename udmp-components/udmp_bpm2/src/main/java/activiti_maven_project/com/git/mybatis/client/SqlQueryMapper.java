package activiti_maven_project.com.git.mybatis.client;

import java.util.List;

import org.springframework.stereotype.Repository;
import activiti_maven_project.com.git.mybatis.BaseMapper;



@Repository("sqlQueryMapper")
public interface SqlQueryMapper extends BaseMapper {

	public List<java.util.Map<String,Object>> countWorkByTemplate(String userId);

	public List<java.util.Map<String,Object>> countCandiWorkByTemplate(String userId,String[] groupIds);
}
