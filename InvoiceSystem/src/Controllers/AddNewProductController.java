/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import Models.AddNewProductModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Ahmed Saboor
 */
public class AddNewProductController implements Initializable {
    
    // Declare TextField Variable.
    @FXML 
    private TextField ProductName, Quanlity, RetailerPrice, Detail; 
    
    // Declare DatePicker Variable.
    @FXML
    private DatePicker ManufacturedDate, ExpiredDate;
    
    // Declare Product TableView
    @FXML
    private TableView addProductTable;
    
    // Prodcut Table View Columns Data
    @FXML private TableColumn productCode;
    @FXML private TableColumn productName;
    @FXML private TableColumn productQuanlity;
    @FXML private TableColumn manufacturedData, ExpireDate;
    
    
    
    // AddProductName Button
    @FXML
    private void AddProductName(ActionEvent event) {
        
        // Declare Add New Product Object
        AddNewProductModel addNewProductModelObject = new AddNewProductModel();

        // Declare Manufacture and ExpireDate 
        String getManufacture, getExpireDate;
        
        // Check Manufacture Value Is Null.
        if ( ManufacturedDate.getValue() != null ){
            getManufacture = ManufacturedDate.getValue().toString();
        } else {
            getManufacture = "";
            ManufacturedDate.setStyle("-fx-border-color:red;");
        }
        
        // Check Expired Value Is Null.
        if ( ExpiredDate.getValue() != null ){
            getExpireDate = ExpiredDate.getValue().toString();
        } else {
            getExpireDate = "";
            ExpiredDate.setStyle("-fx-border-color:red;");
            
        }
        
        // If Text Field Is Not Empty.
        if ( ! ( ProductName.getText().trim().isEmpty() && Quanlity.getText().trim().isEmpty() && RetailerPrice.getText().trim().isEmpty() && Detail.getText().trim().isEmpty() && getManufacture.isEmpty() && getExpireDate.isEmpty()  ) ) {
            addNewProductModelObject.AddProductDataBase(ProductName.getText().trim() , Integer.parseInt(Quanlity.getText()), RetailerPrice.getText().trim(), Detail.getText().trim(), getManufacture, getExpireDate);
            
        } else {
            ProductName.getStyleClass().add("error");
            Quanlity.getStyleClass().add("error");
            RetailerPrice.getStyleClass().add("error");
            Detail.getStyleClass().add("error");
            
        }
    }
        /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("Initialize");
    }    
    
}
