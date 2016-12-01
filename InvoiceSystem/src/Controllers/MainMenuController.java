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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ahmed Saboor
 */
public class MainMenuController implements Initializable {
    
    /**
     * Add New Customer
     * @param event 
     */
    @FXML
    private void AddCustomerActionButton(ActionEvent event) {
        // Switch To Add New Invoice View
        try {    
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/AddCustomer.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Add New Customer");
            stage.setScene(new Scene(root1));  
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Add New Invoice
     * @param event 
     */
    @FXML
    private void addNewInvoice(ActionEvent event) {
        // Switch To Add New Invoice View
        try {    
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/AddNewInvoice.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Add New Invoice");
            stage.setScene(new Scene(root1));  
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Add New Product
     * @param event 
     */
    @FXML
    private void addNewProduct ( ActionEvent event ) {
        // Switch To Add New Product
        
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/AddNewProduct.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Add New Product");
            stage.setScene(new Scene(root1));  
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
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
    
}
