

package cn.com.git.udmp.modules.sys.dao;
/**
 * 
 * @author guosg
 *
 */

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import cn.com.git.udmp.common.enums.GenNumberEnum;

//@Component
public class GenNumDao  implements InitializingBean{
	@Resource(name="seqDataSource")
	private DataSource seqDataSource;
	
	private JdbcTemplate seqJdbcTemplate;
	
	public Integer seq(GenNumberEnum genNumber){
		seqJdbcTemplate.execute("REPLACE INTO "+genNumber.getName()+"(SEQ_NAME) VALUES ('custid');");
		return seqJdbcTemplate.queryForObject("SELECT last_insert_id();", Integer.class);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		seqJdbcTemplate = new JdbcTemplate(seqDataSource);
	}
}
