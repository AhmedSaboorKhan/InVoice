/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;


/**
 *
 * @author Ahmed Saboor
 */
public class LoginModel {
    
    /** 
    * Login Model constructor.
    */
    public LoginModel() {
        System.out.println("Login Model"); 
    }
    
    /**
    * Login Function 
    */    
    public boolean loginFunctional ( String userName , String Password ) {
        
        boolean userExist = false;
        Database databaseObject = new Database();
        Connection conObject= databaseObject.initializeDatabaseConnection();
        
        try {
            
            //String Query = "SELECT * FROM people WHERE Username = '" + userName + "' AND Password = '" + Password + "'";
            String Query = "SELECT * FROM people WHERE Username = ? AND Password = ?";
            
            PreparedStatement  preparedStatementObject  = conObject.prepareStatement(Query);
            preparedStatementObject.setString(1, "nisar");
            preparedStatementObject.setString(2, "1234");
            ResultSet resultValue = preparedStatementObject.executeQuery();
            //System.out.println('value'+resultValue.next());
            userExist = resultValue.next();
            
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return userExist;
    }
    
}
