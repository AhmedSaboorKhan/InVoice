/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.AddCustomerModel;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ahmed Saboor
 */
public class AddCustomerController implements Initializable {
    
    
    // Declare Model Object
    AddCustomerModel addCustomerModel = new AddCustomerModel();
    
    // Declare Customer TableView
    @FXML
    public TableView<AddCustomer> addCustomerTable = new TableView<AddCustomer>();
    
    // Declare Observabilelist.
    @FXML
    private final ObservableList<AddCustomer> data = FXCollections.observableArrayList();
    
    @FXML
    private TableColumn<AddCustomer, String> customerCodes;
    @FXML
    private TableColumn<AddCustomer, String> customerNames;
    @FXML
    private TableColumn<AddCustomer, String> customerPhones;
    @FXML
    private TableColumn<AddCustomer, String> customerAddresses;
    @FXML
    private TableColumn<AddCustomer, String> customerAreas;
    
    // Declare TextField
    @FXML
    TextField CustomerName, CustomerPhoneNumber, CustomerArea;
    
    //  Declare TextArea
    @FXML
    TextArea  CustomerAddress;
    
    // Declare Button
    @FXML
    Button buttonAddCustomer, buttonEditCustomer, buttonDeleteCustomer;
    
    /**
     * Add Customer Action 
     * @param  event 
     */
    @FXML
    private void AddCustomerAction(ActionEvent event) {
        //System.out.println("Add Customer Action");
        // Generate Random Number.
        Random random = new Random();
        
        // Set Customer Data
        addCustomerModel.setCustomerAddress(CustomerAddress.getText());
        addCustomerModel.setCustomerName(CustomerName.getText());
        addCustomerModel.setCustomerPhoneNumber(CustomerPhoneNumber.getText());
        addCustomerModel.setCustomerArea(CustomerArea.getText());
        addCustomerModel.setCustomerCode(String.format("%04d", random.nextInt(10000)));
        
        // Add Customer Data.
        boolean checkCustomerData = addCustomerModel.addCustomerData();
        
        // Check Customer Data.
        if ( checkCustomerData ) {
            //System.out.println(addCustomerModel.getCustomerCode());
            addItemTableView(addCustomerModel.getCustomerCode());
            
        } else {
            System.out.println("Check" + checkCustomerData);
        }
    }
    
    /**
     * Add New Item To Table View
     *
     * @param customerCode
     */
    private void addItemTableView(String customerCode) {
        // Set Customer Data According To Object.
        AddCustomer addCustomer = new AddCustomer(customerCode, CustomerName.getText(), CustomerPhoneNumber.getText(), CustomerArea.getText(), CustomerAddress.getText());
        // Add Customer Data.
        data.add(0, addCustomer);
    }
    
    /**
     * Add Product Data To Table View From Database
     *
     * @param data
     */
    public void addProductDatabaseFromTableView(ObservableList<AddCustomer> data) {
        try {
            //addProductTable.setItems(data);
            ResultSet resultSet = addCustomerModel.getAllCustomer();
            // Get All Product
            while (resultSet.next()) {

                AddCustomer addCustomer = new AddCustomer(resultSet.getString("Customer_Code"), resultSet.getString("Customer_Name"), resultSet.getString("Phone_No"), resultSet.getString("Area"), resultSet.getString("Address"));
                // Add New Product.
                data.add(addCustomer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddNewProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Edit Customer Action 
     * @param  event 
     */
    @FXML
    private void EditCustomerAction(ActionEvent event) throws SQLException {
        //System.out.println("Edit Customer Action");
        // Get Select Item Data.
        AddCustomer addCustomer = addCustomerTable.getSelectionModel().getSelectedItem();
        // Get Product Code
        ResultSet productData = addCustomerModel.getCustomerByCode(addCustomer.getCustomerCode());

        try {
            // Declare Product Edit Controller Object.
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/CustomerEdit.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            CustomerEditController editCustomer = fxmlLoader.<CustomerEditController>getController();
            // Set Edit Product.
            editCustomer.SetEditCustomer(productData);
            editCustomer.setCustomerData();
            editCustomer.primaryData = data;
            // Set Title.
            stage.setTitle("Edit Product");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    /**
     * Delete Customer Action 
     * @param  event 
     */
    @FXML
    private void DeleteCustomerAction(ActionEvent event) throws SQLException {
        //System.out.println("Delete Customer Action");
        // Alert Dialog Code.
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Customer");
        alert.setContentText("Are you sure you want to delete this Customer?");
        Optional<ButtonType> result = alert.showAndWait();

        // If Button is Yes.
        if (result.get() == ButtonType.OK) {
            //System.out.println("OK");
            AddCustomer addCustomer = addCustomerTable.getSelectionModel().getSelectedItem();
            // Function Call For Delete Product By Product Code.
            boolean checkDelete = addCustomerModel.deleteCustomer(addCustomer.getCustomerCode());
            // if checkDelete is true.
            if (checkDelete) {
                // clear data from table
                data.clear();
                // update product to table.
                addProductDatabaseFromTableView(data);
            } else {
                System.out.println("No Delete");
            }
        } else {
            System.out.println("Delete");
        }
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // Disable The Button Until Table Item Is Not Click.
        buttonDeleteCustomer.disableProperty().bind(Bindings.isEmpty(addCustomerTable.getSelectionModel().getSelectedItems()));
        buttonEditCustomer.disableProperty().bind(Bindings.isEmpty(addCustomerTable.getSelectionModel().getSelectedItems()));

        // Set CellValueFactory.
        customerCodes.setCellValueFactory(new PropertyValueFactory<AddCustomer, String>("customerCode"));
        customerNames.setCellValueFactory(new PropertyValueFactory<AddCustomer, String>("customerName"));
        customerAddresses.setCellValueFactory(new PropertyValueFactory<AddCustomer, String>("customerAddress"));
        customerPhones.setCellValueFactory(new PropertyValueFactory<AddCustomer, String>("customerPhone"));
        customerAreas.setCellValueFactory(new PropertyValueFactory<AddCustomer, String>("customerArea"));
        addCustomerTable.setItems(data);

        // Add Product Data To Table View Function Call
        addProductDatabaseFromTableView(data);

    }        
    
    /**
     * Add Product Class
     */
    public static class AddCustomer {

        // Declare Private Variable.
        private final SimpleStringProperty customerCode;
        private final SimpleStringProperty customerName;
        private final SimpleStringProperty customerPhone;
        private final SimpleStringProperty customerAddress;
        private final SimpleStringProperty customerArea;
        
        // Constructor.
        private AddCustomer(String customerCode, String customerName, String customerPhone, String customerAddress, String customerArea) {
            this.customerCode = new SimpleStringProperty(customerCode);
            this.customerName = new SimpleStringProperty(customerName);
            this.customerPhone = new SimpleStringProperty(customerPhone);
            this.customerAddress = new SimpleStringProperty(customerAddress);
            this.customerArea = new SimpleStringProperty(customerArea);
        }

        /**
         * @return the customerCode
         */
        public String getCustomerCode() {
            return customerCode.get();
        }

        /**
         * @param customerCodes
         */
        public void setCustomerCode(String customerCodes) {
            customerCode.set(customerCodes);
        }

        /**
         * @return the customerName
         */
        public String getCustomerName() {
            return customerName.get();
        }

        /**
         * @param customerNames
         */
        public void setCustomerName(String customerNames) {
            customerName.set(customerNames);
        }

        /**
         * @return the customerPhone
         */
        public String getCustomerPhone() {
            return customerPhone.get();
        }

        /**
         * @param customerPhones
         */
        public void setCustomerPhone(String customerPhones) {
            customerPhone.set(customerPhones);
        }

        /**
         * @return the customerAddress
         */
        public String getCustomerAddress() {
            return customerAddress.get();
        }

        /**
         * @param customerAddresses
         */
        public void setCustomerAddress(String customerAddresses) {
            customerAddress.set(customerAddresses);
        }

        /**
         * @return the customerArea
         */
        public String getCustomerArea() {
            return customerArea.get();
        }

        /**
         * @param customerAreas
         */
        public void setCustomerArea(String customerAreas) {
            customerArea.set(customerAreas);
        }
    }
}
