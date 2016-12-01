/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Ahmed Saboor
 */
public class AddCustomerModel {
    
    // Create Database  
    private Database databaseObject;
    
    // Generate Variable.
    private String customerName, customerAddress, customerArea, customerPhoneNumber, CustomerCode;
    private int outputValue;

    /**
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the customerAddress
     */
    public String getCustomerAddress() {
        return customerAddress;
    }

    /**
     * @param customerAddress the customerAddress to set
     */
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    /**
     * @return the customerArea
     */
    public String getCustomerArea() {
        return customerArea;
    }

    /**
     * @param customerArea the customerArea to set
     */
    public void setCustomerArea(String customerArea) {
        this.customerArea = customerArea;
    }

    /**
     * @return the customerPhoneNumber
     */
    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    /**
     * @param customerPhoneNumber the customerPhoneNumber to set
     */
    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }
    
     // Get Product By Code.
    public ResultSet getCustomerByCode ( String customerCode ) throws SQLException {
        // Create Object of Database
        databaseObject = new Database();
        // Create PrepareStatement
        PreparedStatement preparedStatement;
        // Connection Object.
        Connection connectionObject = databaseObject.initializeDatabaseConnection();;
        
        // Get Product According to Product Code.
        String allProductQuery = "SELECT * FROM customerdetail WHERE Customer_Code =?";    
        preparedStatement = connectionObject.prepareStatement(allProductQuery);
        preparedStatement.setString(1, customerCode  );
        // Execute Query.
        ResultSet customerData = preparedStatement.executeQuery();

        return customerData;
    }
    
    public boolean deleteCustomer ( String customerCode ) throws SQLException {
        
        // Check Result
        int checkResult = 0;
        // Create Object of Database
        databaseObject = new Database();
        // Create PrepareStatement
        PreparedStatement preparedStatement;
        // Connection Object.
        Connection connectionObject = databaseObject.initializeDatabaseConnection();;
        
        // Delete Product According to Customer Code.
        String deleteCustomer = "DELETE FROM customerdetail WHERE Customer_Code =?";    
        preparedStatement = connectionObject.prepareStatement(deleteCustomer);
        preparedStatement.setString(1, customerCode  );
        // Execute Query.
        checkResult = preparedStatement.executeUpdate();
        
        if ( checkResult == 1 ) {
            return true;
        } else {
            return false;
        }
        
    }
    
    // Get All Customer Function 
    public ResultSet getAllCustomer() throws SQLException {

        // Create Object of Database
        databaseObject = new Database();
        // Create PrepareStatement
        PreparedStatement preparedStatement;
        // Connection Object.
        Connection connectionObject = databaseObject.initializeDatabaseConnection();;
        // Get All Product By Order
        String allCustomerAuery = "SELECT * FROM customerdetail ORDER BY Update_Date DESC";
        preparedStatement = connectionObject.prepareStatement(allCustomerAuery);
        // Execute Query.
        ResultSet customerResult = preparedStatement.executeQuery();

        return customerResult;
    }
    
    /**
     * Add Customer Data
     */
    public boolean addCustomerData() {
        System.out.println("Models.AddCustomerModel.addCustomerData()");
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
            String insertData = "INSERT INTO CustomerDetail"
                + "(Customer_Code, Customer_Name, Phone_No, Address, Area, Update_Date, Status) VALUES"
                + "(?,?,?,?,?,?,?)";

            // Prepare Statement Insert Query.
            preparedStatement = connectionObject.prepareStatement(insertData);
            preparedStatement.setString(1, getCustomerCode());
            preparedStatement.setString(2, getCustomerName());
            preparedStatement.setString(3, getCustomerPhoneNumber());
            preparedStatement.setString(4, getCustomerAddress());
            preparedStatement.setString(5, getCustomerArea());
            preparedStatement.setString(6, df.format(dateobj));
            preparedStatement.setString(7, "NORMAL");
            // execute insert SQL stetement
            insertValue = preparedStatement.executeUpdate();
            
            
        } catch (SQLException e) {
            System.out.println(e);
        }
        return insertValue == 1; 
    }
    
    
    public void setAddCustomerOutput (  int outputResult  ) {
        this.outputValue = outputResult;
    }
    
    
    public int getAddCustomerOutput () {
        return outputValue;
    }
    

    /**
     * @return the CustomerCode
     */
    public String getCustomerCode() {
        return CustomerCode;
    }

    /**
     * @param CustomerCode the CustomerCode to set
     */
    public void setCustomerCode(String CustomerCode) {
        this.CustomerCode = CustomerCode;
    }
    
}
