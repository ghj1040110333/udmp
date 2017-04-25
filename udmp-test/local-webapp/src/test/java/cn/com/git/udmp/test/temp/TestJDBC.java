package cn.com.git.udmp.test.temp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class TestJDBC {
    @Test
    public void test1(){
        ResultSet rs = null;  
        Statement stmt = null;  
        Connection conn = null;  
        try {  
         Class.forName("oracle.jdbc.driver.OracleDriver");  
         //new oracle.jdbc.driver.OracleDriver();  
//         conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.1:1521:yuewei", "scott", "tiger");  
         conn = DriverManager.getConnection("jdbc:oracle:thin:@10.200.16.139:1521:orcl", "crms", "crms");  
         stmt = conn.createStatement();  
         rs = stmt.executeQuery("select * from dept");  
         while(rs.next()) {  
          System.out.println(rs.getString("deptno"));  
          //System.out.println(rs.getInt("deptno"));  
         }  
        } catch (ClassNotFoundException e) {  
         e.printStackTrace();  
        } catch (SQLException e) {  
         e.printStackTrace();  
        } finally {  
         try {  
          if(rs != null) {  
           rs.close();  
           rs = null;  
          }  
          if(stmt != null) {  
           stmt.close();  
           stmt = null;  
          }  
          if(conn != null) {  
           conn.close();  
           conn = null;  
          }  
         } catch (SQLException e) {  
          e.printStackTrace();  
         }  
        }  
    }
}
