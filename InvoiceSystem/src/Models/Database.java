/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Ahmed Saboor
 */
public class Database extends LoginModel {
    
    /**
    * 3306 is the default port for MySQL in XAMPP. Note both the 
    * MySQL server and Apache must be running. 
    */
    private String url = "jdbc:mysql://localhost/test";

   /**
    * The MySQL user.
    */
    private String user = "root";

   /**
    * Password for the above MySQL user. If no password has been 
    * set (as is the default for the root user in XAMPP's MySQL),
    * an empty string can be used.
    */
    private String password = "";
    
    public  Connection initializeDatabaseConnection() {
        
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url, user, password);
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        } 
        
        return con;
   }
}
