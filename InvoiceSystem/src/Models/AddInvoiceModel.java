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

/**
 *
 * @author Ahmed Saboor
 */
public class AddInvoiceModel {
    
    // Create Database  
    private Database databaseObject;
    
    // Declare Variable;
    private String customerCode, productName, producrQuanlity, productDiscount;

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
     * @return the producrQuanlity
     */
    public String getProducrQuanlity() {
        return producrQuanlity;
    }

    /**
     * @param producrQuanlity the producrQuanlity to set
     */
    public void setProducrQuanlity(String producrQuanlity) {
        this.producrQuanlity = producrQuanlity;
    }

    /**
     * @return the productDiscount
     */
    public String getProductDiscount() {
        return productDiscount;
    }

    /**
     * @param productDiscount the productDiscount to set
     */
    public void setProductDiscount(String productDiscount) {
        this.productDiscount = productDiscount;
    }
    
    // Calculate Product Price.
    public int calculateProductPrice() throws SQLException{
        // Get Product Related Data.
        String retailerPrice = null;
        String productName = getProductName();
        String productQuanlity = getProducrQuanlity();
        String productDiscount = getProductDiscount();
        //System.out.println(productName + productQuanlity + productDiscount);
        ResultSet productData = findProductDataByName(productName);
        // Get Retailer Price
        if ( productData.next() ) {
            retailerPrice = productData.getString("Retailer_price");
            System.out.println(retailerPrice+ productData.getString("Product_Name"));
        }
        // Get The Product Price, Discount And Quanlity.
        int productPrice = Integer.parseInt(retailerPrice);
        int productDiscounts =  Integer.parseInt(productDiscount);
        int productQuanlitys = Integer.parseInt(productQuanlity);
        int productSinglePrice = 0;
        // If Price And Discount Is Not Null And Great than 0. 
        if ( productPrice != 0 && productPrice > 0 && productDiscounts != 0 && productDiscounts > 0 && productQuanlitys != 0 && productQuanlitys > 0 ){
            productDiscounts  = productDiscounts / 100; 
            productSinglePrice = productDiscounts * (  productPrice * productQuanlitys );
            productSinglePrice = ( productPrice * productQuanlitys ) - productSinglePrice;
        } else {
            productSinglePrice  = 0;
        }
        //System.out.println(productSinglePrice);
        return productSinglePrice;
    } 
    
    
    public ResultSet findProductDataByName( String productName ) throws SQLException {
        
        // Create Object of Database
        databaseObject = new Database();
        // Create PrepareStatement
        PreparedStatement preparedStatement;
        // Connection Object.
        Connection connectionObject = databaseObject.initializeDatabaseConnection();;
        // Get All Product By Name
        String GetProduct = "SELECT * FROM productdetail where Product_Name =?";
        preparedStatement = connectionObject.prepareStatement(GetProduct);
        preparedStatement.setString(1, productName  );
        // Execute Query.
        ResultSet productResult = preparedStatement.executeQuery();
        
        return productResult;
    }
    
    
}
