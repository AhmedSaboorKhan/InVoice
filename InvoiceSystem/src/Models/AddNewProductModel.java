/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import static java.lang.Math.random;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


/**
 *
 * @author Ahmed Saboor
 */
public class AddNewProductModel {

    // Create Database  
    private  Database databaseObject;
    
    // AddNewProductModel Constructor
    public AddNewProductModel() {
        System.out.println("AddNewProduct");
    }
      
    // Add Product to Database
    public boolean AddProductDataBase( String productName, int Quanlity, String RetailerPrice, String Detail, String manufactureDate, String getExpireDate  ){
        
        // Random Number Generator.
        Random random = new Random();
        String productCode = String.format("%04d", random.nextInt(10000));
        
        // Create Object of Database
        databaseObject = new Database();
        
        // Create PrepareStatement
        PreparedStatement preparedStatement;
        
        // Initialize Database Connection.
        Connection connectionObject  = databaseObject.initializeDatabaseConnection();
        
        // Date.
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date dateobj = new Date();
        int insertValue = 0;
        try {
            // Insert Query
            String insertData = "INSERT INTO ProductDetail"
                + "(Product_Code, Product_Name, Quanlity, Expired_date, Manufactured_date, Update_Date, Insert_Date, Status) VALUES"
                + "(?,?,?,?,?,?,?,?)";
            
            // Prepare Statement Insert Query.
            preparedStatement = connectionObject.prepareStatement(insertData);
            preparedStatement.setString(1, productCode);
            preparedStatement.setString(2, productName);
            preparedStatement.setInt(3, Quanlity);
            preparedStatement.setString(4, getExpireDate);
            preparedStatement.setString(5, manufactureDate);
            preparedStatement.setString(6, df.format(dateobj));
            preparedStatement.setString(7, df.format(dateobj));
            preparedStatement.setString(8, "STATUS");
            
            // execute insert SQL stetement
            insertValue = preparedStatement.executeUpdate();
            
            
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        if ( insertValue == 1 ) {
            return true;
        } else {
            return false;
        }
    }
    
}
