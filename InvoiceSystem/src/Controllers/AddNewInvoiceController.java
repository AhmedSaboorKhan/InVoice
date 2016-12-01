/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.AddInvoiceModel;
import Models.AddNewProductModel;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

/**
 * FXML Controller class
 *
 * @author Ahmed Saboor
 */
public class AddNewInvoiceController implements Initializable {

    // Declare Model Object;
    AddNewProductModel addNewProductModel = new AddNewProductModel();
    AddInvoiceModel addInvoiceModel = new AddInvoiceModel();
    // Declare ObservableList and Combox Variable.
    @FXML
    ObservableList<String> data = FXCollections.observableArrayList();
    @FXML
    ComboBox ProductName = new ComboBox();
    // Declare TextField.
    @FXML
    TextField CustomerCode, Discount, Quanlity;
    // Declare Submit Button.
    @FXML
    Button submitButton;

    //Declare Variables
    private String SelectCustomerCode, SelectProductName, SelectQuanlity, SelectDiscount;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // Get All Product Name.
            ResultSet resultSet = addNewProductModel.getAllProduct();
            // Get All Product With While Loop.
            while (resultSet.next()) {
                // Add All Product To ObservableList
                data.add(resultSet.getString("Product_Name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddNewInvoiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Add All Product To The Select Item.
        ProductName.setItems(data);
    }

    /**
     * Add Customer Action
     *
     * @param event
     */
    @FXML
    private void SubmitButtonAction(ActionEvent event) {
        //System.out.println("Controllers.AddNewInvoiceController.SubmitButtonAction()");
        setSelectProductName(ProductName.getValue().toString());
        setSelectDiscount(Discount.getText());
        setSelectCustomerCode(CustomerCode.getText());
        setSelectQuanlity(Quanlity.getText());
        // Set Data In Invoce Model.
        addInvoiceModel.setCustomerCode(CustomerCode.getText());
        addInvoiceModel.setProducrQuanlity(Quanlity.getText());
        addInvoiceModel.setProductName(ProductName.getValue().toString());
        addInvoiceModel.setProductDiscount(Discount.getText());
        
        addInvoiceModel.calculateProductPrice();
    }

    /**
     * @return the SelectCustomerCode
     */
    public String getSelectCustomerCode() {
        return SelectCustomerCode;
    }

    /**
     * @param SelectCustomerCode the SelectCustomerCode to set
     */
    public void setSelectCustomerCode(String SelectCustomerCode) {
        this.SelectCustomerCode = SelectCustomerCode;
    }

    /**
     * @return the SelectProductName
     */
    public String getSelectProductName() {
        return SelectProductName;
    }

    /**
     * @param SelectProductName the SelectProductName to set
     */
    public void setSelectProductName(String SelectProductName) {
        this.SelectProductName = SelectProductName;
    }

    /**
     * @return the SelectQuanlity
     */
    public String getSelectQuanlity() {
        return SelectQuanlity;
    }

    /**
     * @param SelectQuanlity the SelectQuanlity to set
     */
    public void setSelectQuanlity(String SelectQuanlity) {
        this.SelectQuanlity = SelectQuanlity;
    }

    /**
     * @return the SelectDiscount
     */
    public String getSelectDiscount() {
        return SelectDiscount;
    }

    /**
     * @param SelectDiscount the SelectDiscount to set
     */
    public void setSelectDiscount(String SelectDiscount) {
        this.SelectDiscount = SelectDiscount;
    }

}
