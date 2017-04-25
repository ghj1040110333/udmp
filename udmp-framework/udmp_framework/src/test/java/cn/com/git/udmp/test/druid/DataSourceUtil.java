package cn.com.git.udmp.test.druid;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;
  

/**
 * DataSource Utils
 * 
 * <p>
 * 		DataSource Factory
 * </p>
 * 
 * @author Spring Cao
 *
 */
public class DataSourceUtil {  
  
    /** Druid DataSource configuration 1*/  
    public static final int DRUID_MYSQL_SOURCE = 0;  
  
    /** Druid DataSource configuration 2 */  
    public static final int DRUID_MYSQL_SOURCE2 = 1;  
  
    /** DBCP DataSource configuration */  
    public static final int DBCP_SOURCE = 4;  
    public static String fileName = "testDruid.properties";  
    public static Properties prop = null;  
  
    static {  
        prop = new Properties();  
        InputStream inputStream = null;  
        try {  
        	inputStream = DataSourceUtil.class.getResourceAsStream(fileName);
        	prop.load(inputStream);  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if (inputStream != null) {  
                    inputStream.close();  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
    
    /** 
     * DataSource Factory to Gen Druid or DBCP
     *  
     * @param sourceType 
     * @return druid or dbcp DataSource
     * @throws Exception 
     */  
    public static final DataSource getDataSource(int sourceType) throws Exception {  
        DataSource dataSource = null;  
        switch (sourceType) {  
        case DRUID_MYSQL_SOURCE:  
            dataSource = DruidDataSourceFactory.createDataSource(prop);  
            break;  
        case DRUID_MYSQL_SOURCE2:  
            dataSource = DruidDataSourceFactory.createDataSource(prop);  
            break;  
        case DBCP_SOURCE:  
        	//TODO
            break;  
        }  
        return dataSource;  
    }  
    
    public static void main(String a[]){
    	try {
    		DataSource ds = DataSourceUtil.getDataSource(DRUID_MYSQL_SOURCE);
    		System.out.println(ds == null?"DS is null":ds.getConnection().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}  
