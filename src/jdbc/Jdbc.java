/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import java.sql.Connection;
import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author UCHIHA
 */
public class Jdbc {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        Scanner scan=new Scanner(System.in);
        // TODO code application logic here
       String url ="jdbc:mariadb://localhost:3306/test"; //update connection string
        
        String user = "root";//add your db user id here
        String password = "";//add your db password here
        
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println("Successfully connected");
        
        //insert employee record into database
        Statement stmt = conn.createStatement();
        int rows = stmt.executeUpdate("insert into employee (age,name) values(23,'James'),(24,'Julie')");
        System.out.println("Rows inserted = "+ rows);
        
        scan.next();
        //update employee record
        rows= stmt.executeUpdate("Update employee set age=31 where name='James'");
        System.out.println("Rows updated = "+ rows);
        scan.next();
        //read employee records
        ResultSet rs = stmt.executeQuery("Select * from employee where age>20");
        while(rs.next()){
            System.out.println("Emp Id : " + rs.getInt("id") + ", Name : " + rs.getString("name") + ", Age : " + rs.getInt("age"));
        }
        scan.next();
        //delete employee record
        rows = stmt.executeUpdate("delete from employee where name = 'Julie'");
        System.out.println("Rows deleted = "+ rows);
    }
    
}
