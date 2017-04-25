package cn.com.git.udmp.test.druid;

import java.sql.Connection;  
import java.sql.PreparedStatement;  
import java.sql.SQLException;  
import java.sql.Statement;  
  
import javax.sql.DataSource;  
  
/**
 * DB Table Operator Testing
 * 
 * <P>
 * 	  make a test ddl to db 
 * </P>
 * 
 * @author Spring Cao
 *
 */
public class TableOperator {  
	/** java.sql.DataSource */
    private DataSource dataSource;  
    /** db table cols */
    private static final int COUNT = 5;  
  
    /**
     * Default Constructor
     */
    public TableOperator() {  
    }  
    
    public void setDataSource(DataSource dataSource) {  
        this.dataSource = dataSource;  
    } 
  
    /**
     * Distroy test Table
     * @throws Exception
     */
    public void tearDown() throws Exception {  
        try {  
            dropTable();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  
  
    /**
     * Insert test Table
     * @throws Exception
     */
    public void insert() throws Exception {  
        StringBuffer ddl = new StringBuffer();  
        ddl.append("INSERT INTO test_druid (");  
        for (int i = 0; i < COUNT; ++i) {  
            if (i != 0) {  
                ddl.append(", ");  
            }  
            ddl.append("col_" + i);  
        }  
        ddl.append(") VALUES (");  
        for (int i = 0; i < COUNT; ++i) {  
            if (i != 0) {  
                ddl.append(", ");  
            }  
            ddl.append("?");  
        }  
        ddl.append(")");  
        Connection conn = dataSource.getConnection();  
        System.out.println(ddl.toString());  
        PreparedStatement stmt = conn.prepareStatement(ddl.toString());  
        for (int i = 0; i < COUNT; ++i) {  
            stmt.setInt(i + 1, i);  
        }  
        stmt.execute();  
        stmt.close();  
        conn.close();  
    }  
  
    /**
     * Drop test Table
     * @throws SQLException
     */
    private void dropTable() throws SQLException {  
        Connection conn = dataSource.getConnection();  
        Statement stmt = conn.createStatement();  
        stmt.execute("DROP TABLE test_druid");  
        stmt.close();  
        conn.close();  
    }  
  
    /**
     * Create test Table
     * @throws SQLException
     */
    public void createTable() throws SQLException {  
        StringBuffer ddl = new StringBuffer();  
        ddl.append("CREATE TABLE test_druid (FID INT not null primary key auto_increment");  
        for (int i = 0; i < COUNT; ++i) {  
            ddl.append(", ");  
            ddl.append("col_" + i);  
            ddl.append(" char(10)");  
        }  
        ddl.append(")");  
        Connection conn = dataSource.getConnection();  
        Statement stmt = conn.createStatement();  
        System.out.println(ddl.toString());  
        stmt.execute(ddl.toString());  
        stmt.close();  
        conn.close();  
    }
} 
