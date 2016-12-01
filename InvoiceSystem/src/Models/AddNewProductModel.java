/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Ahmed Saboor
 */
public class AddNewProductModel {

    // Create Database  
    private Database databaseObject;

    // AddNewProductModel Constructor
    public AddNewProductModel() {
        System.out.println("AddNewProduct");
    }

    // Add Product to Database
    public boolean AddProductDataBase(String productName, int Quanlity, int RetailerPrice, String Detail, String manufactureDate, String getExpireDate, String productCode) {

        // Create Object of Database
        databaseObject = new Database();

        // Create PrepareStatement
        PreparedStatement preparedStatement;

        // Initialize Database Connection.
        Connection connectionObject = databaseObject.initializeDatabaseConnection();

        // Date.
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date dateobj = new Date();
        int insertValue = 0;
        try {
            // Insert Query
            String insertData = "INSERT INTO ProductDetail"
                    + "(Product_Code, Product_Name, Quanlity, Expired_date, Manufactured_date, Update_Date, Insert_Date, Status, Retailer_price) VALUES"
                    + "(?,?,?,?,?,?,?,?,?)";

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
            preparedStatement.setInt(9, RetailerPrice);
            // execute insert SQL stetement
            insertValue = preparedStatement.executeUpdate();

        } catch (SQLException e) {
        }
        return insertValue == 1;
    }

    // Get All Product Function 
    public ResultSet getAllProduct() throws SQLException {

        // Create Object of Database
        databaseObject = new Database();
        // Create PrepareStatement
        PreparedStatement preparedStatement;
        // Connection Object.
        Connection connectionObject = databaseObject.initializeDatabaseConnection();;
        // Get All Product By Order
        String allProductQuery = "SELECT * FROM ProductDetail ORDER BY Update_Date DESC";
        preparedStatement = connectionObject.prepareStatement(allProductQuery);
        // Execute Query.
        ResultSet productResult = preparedStatement.executeQuery();

        return productResult;
    }
    
    // Get Product By Code.
    public ResultSet getProductByCode ( String productCode ) throws SQLException {
        // Create Object of Database
        databaseObject = new Database();
        // Create PrepareStatement
        PreparedStatement preparedStatement;
        // Connection Object.
        Connection connectionObject = databaseObject.initializeDatabaseConnection();;
        
        // Get Product According to Product Code.
        String allProductQuery = "SELECT * FROM ProductDetail WHERE Product_Code =?";    
        preparedStatement = connectionObject.prepareStatement(allProductQuery);
        preparedStatement.setString(1, productCode  );
        // Execute Query.
        ResultSet productData = preparedStatement.executeQuery();

        return productData;
    }
    
    public boolean deleteProduct ( String productCode ) throws SQLException {
        
        // Check Result
        int checkResult = 0;
        // Create Object of Database
        databaseObject = new Database();
        // Create PrepareStatement
        PreparedStatement preparedStatement;
        // Connection Object.
        Connection connectionObject = databaseObject.initializeDatabaseConnection();;
        
        // Delete Product According to Product Code.
        String deleteProduct = "DELETE FROM ProductDetail WHERE Product_Code =?";    
        preparedStatement = connectionObject.prepareStatement(deleteProduct);
        preparedStatement.setString(1, productCode  );
        // Execute Query.
        checkResult = preparedStatement.executeUpdate();
        
        if ( checkResult == 1 ) {
            return true;
        } else {
            return false;
        }
        
    }
}
