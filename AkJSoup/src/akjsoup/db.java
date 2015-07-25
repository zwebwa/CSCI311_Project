/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package akjsoup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.time.Clock.system;

/**
 *
 * @author aungkoko
 */
public class db {
    public Connection conn = null;
    
    public db(){
         try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/Crawler";
			conn = DriverManager.getConnection(url, "root", "");
			System.out.println("conn built");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }
    
    public ResultSet runSQL(String sql) throws SQLException{
           Statement sta = conn.createStatement();
           return sta.executeQuery(sql);
    }
    
    public boolean runSql2(String sql) throws SQLException{
           Statement sta = conn.createStatement();
           return sta.execute(sql);
    }
    
    public int runSql3(String sql) throws SQLException{
           Statement sta = conn.createStatement();
           return sta.executeUpdate(sql);
    }
    
    @Override
    protected void finalize() throws Throwable{
     if (conn != null || !conn.isClosed()) {
			conn.close();
		}
    }
    
}
