/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Ahmed Saboor
 */
public class UpdateCustomerModel {
    
    private String customerName, customerPhone, customerAddress, customerArea, customerCode;
    private  Database databaseObject;

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
     * @return the customerPhone
     */
    public String getCustomerPhone() {
        return customerPhone;
    }

    /**
     * @param customerPhone the customerPhone to set
     */
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
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
    
    public boolean updateCustomerData(){
        
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
            String updateQuery = "UPDATE customerdetail SET Customer_Name = ?, Phone_No = ?, Address = ?, Area = ?, Update_Date = ?  WHERE Customer_Code = ?;";

            // Prepare Statement Insert Query.
            preparedStatement = connectionObject.prepareStatement(updateQuery);
            preparedStatement.setString(1, getCustomerName());
            
            preparedStatement.setString(2, getCustomerPhone());
            preparedStatement.setString(3, getCustomerAddress());
            preparedStatement.setString(4, getCustomerArea());
            preparedStatement.setString(5, df.format(dateobj));
            preparedStatement.setString(6, getCustomerCode());
            
            // execute insert SQL stetement
            insertValue = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return insertValue == 1;
    }

    /**
     * @return the customerCode
     */
    public String getCustomerCode() {
        return customerCode;
    }

    /**
     * @param customerCode the customerCode to set
     */
    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }
    
}
