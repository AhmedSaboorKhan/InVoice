/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.UpdateCustomerModel;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ahmed Saboor
 */
public class CustomerEditController implements Initializable {
    
    UpdateCustomerModel customerModel = new UpdateCustomerModel();
    
    AddCustomerController addCustomerController = new AddCustomerController();
    
    // Declare String
    private String customerName, customerPhone, customerAddress, customerArea, customerCode;
    
    // Declare TextField
    @FXML
    private TextField CustomerName, CustomerArea, CustomerPhone;
    // Declare TextArea
    @FXML
    private TextArea CustomerAddress;
    // Declare Button
    @FXML
    private Button btnUpdate, BtnClose; 
    // Declare Observabilelist.
    @FXML
    public ObservableList<AddCustomerController.AddCustomer> primaryData = FXCollections.observableArrayList();
    // Constructor.
    public CustomerEditController() {
        this.customerName = null;
        this.customerAddress = null;
        this.customerPhone = null;
        this.customerArea = null;
    
    }
    
    
    /**
     * Close Customer Data
     * @param actionEvent 
     */
    @FXML
    public void CloseCustomerAction(ActionEvent actionEvent) {
        //System.out.println("CloseCustomerAction");
       Stage stage = (Stage) BtnClose.getScene().getWindow();
       stage.close();
    }
    /**
     * Update Product Data
     * @param actionEvent 
     */
    @FXML
    public void UpdateProductAction(ActionEvent actionEvent) {
        //System.out.println("UpdateProductAction");
        customerModel.setCustomerAddress(CustomerAddress.getText());
        customerModel.setCustomerName(CustomerName.getText());
        customerModel.setCustomerPhone(CustomerPhone.getText());
        customerModel.setCustomerArea(CustomerArea.getText());
        customerModel.setCustomerCode(getCustomerCode());
        
        boolean checkUpdate = customerModel.updateCustomerData();

        if (checkUpdate) {
            System.out.println("Controllers.checkUpdate");
            primaryData.clear();
            addCustomerController.addProductDatabaseFromTableView(primaryData);
            Stage stage = (Stage) BtnClose.getScene().getWindow();
            stage.close();
        }
    }
    
    /**
     * Set Edit Customer
     * @param resultObject
     */
    public void SetEditCustomer(ResultSet resultObject) throws SQLException {
        while (resultObject.next()) {
            this.customerName = resultObject.getString("Customer_Name");
            this.customerAddress = resultObject.getString("Address");
            this.customerPhone = resultObject.getString("Phone_No");
            this.customerArea = resultObject.getString("Area");  
            this.setCustomerCode(resultObject.getString("Customer_Code"));
        }
    }
    
    /**
     * Set Edit Customer
     */
    public void setCustomerData(){
        CustomerName.setText(getCustomerName());
        CustomerPhone.setText(getCustomerPhone());
        CustomerAddress.setText(getCustomerAddress());
        CustomerArea.setText(getCustomerArea());
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

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
