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
public class UpdateProductModel {
    
    private Database databaseObject;
    private String productName;
    private String productCode;
    private String ManufactureDate;
    private String ExpiredDate;
    private int price;
    private int Quanlity;

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the productCode
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * @param productCode the productCode to set
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
     * @return the ManufactureDate
     */
    public String getManufactureDate() {
        return ManufactureDate;
    }

    /**
     * @param ManufactureDate the ManufactureDate to set
     */
    public void setManufactureDate(String ManufactureDate) {
        this.ManufactureDate = ManufactureDate;
    }

    /**
     * @return the ExpiredDate
     */
    public String getExpiredDate() {
        return ExpiredDate;
    }

    /**
     * @param ExpiredDate the ExpiredDate to set
     */
    public void setExpiredDate(String ExpiredDate) {
        this.ExpiredDate = ExpiredDate;
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * @return the Quanlity
     */
    public int getQuanlity() {
        return Quanlity;
    }

    /**
     * @param Quanlity the Quanlity to set
     */
    public void setQuanlity(int Quanlity) {
        this.Quanlity = Quanlity;
    }
    
    public boolean updateProductData(){
        
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
            String updateQuery = "UPDATE ProductDetail SET Product_Name = ?, Retailer_price = ?, Quanlity = ?, Expired_date = ?, Manufactured_date = ?, Update_Date = ? WHERE Product_Code = ?;";

            // Prepare Statement Insert Query.
            preparedStatement = connectionObject.prepareStatement(updateQuery);
            preparedStatement.setString(1, getProductName());
            preparedStatement.setInt(2, getPrice());
            preparedStatement.setInt(3, getQuanlity());
            preparedStatement.setString(4, getExpiredDate());
            preparedStatement.setString(5, getManufactureDate());
            preparedStatement.setString(6, df.format(dateobj));
            preparedStatement.setString(7, getProductCode());
            
            // execute insert SQL stetement
            insertValue = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return insertValue == 1;
    }
}
