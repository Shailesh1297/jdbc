/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author UCHIHA
 */
public class MyJdbc {
    
    static Connection connect=null;
    public static Connection connection(String database,String user,String password)
    {
        
        String url="jdbc:mariadb://localhost:3306/"+database;
        
        try {
            //register the driver
            DriverManager.registerDriver(new org.mariadb.jdbc.Driver());
            
            //connecting to database
            connect=DriverManager.getConnection(url,user,password);
            
           
            
        } catch (SQLException ex) {
            Logger.getLogger(MyJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return connect;
    }
    
    
    public int insertData(Statement stmt,String table,String data) throws SQLException
    {
        int flag=0;
        String []st=data.split(" ");
       String sql="INSERT INTO "+table+" VALUES ("+st[0]+","+st[1]+")";
       
        flag=stmt.executeUpdate(sql);
        return flag;
    }
    
    public int updateData(Statement stmt,String table, String column, String newData,int id) throws SQLException
    {
         int flag=0;
         String sql="UPDATE "+table+" SET "+column+"="+newData+" WHERE id="+id;
         flag=stmt.executeUpdate(sql);
         
         return flag;
    }
    
    public int deleteRow(Statement stmt,String table,int id) throws SQLException
    {
        int flag=0;
        String sql="DELETE FROM "+table+" WHERE id="+id;
        flag=stmt.executeUpdate(sql);
        return flag;
    }
    
    public void showData(Statement stmt,String table) throws SQLException
    {
        String sql="SELECT * FROM "+table;
         ResultSet rs=stmt.executeQuery(sql);
         while(rs.next())  
        System.out.println(rs.getInt(0)+"  "+rs.getString(1)+"  "+rs.getString(2));  
    }
    
}
