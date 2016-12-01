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
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
    public TableView<AddProduct> addProductTable = new TableView<AddProduct>();
    // Declare Observabilelist.
    @FXML
    private final ObservableList<AddProduct> data = FXCollections.observableArrayList();
    // Product Table View Columns Data    
    @FXML
    private TableColumn<AddProduct, String> productCode;
    @FXML
    private TableColumn<AddProduct, String> productName;
    @FXML
    private TableColumn<AddProduct, Integer> productQuanlity;
    @FXML
    private TableColumn<AddProduct, String> manufacturedData;
    @FXML
    private TableColumn<AddProduct, String> expireDate;
    // Declare Add Product Button
    @FXML
    Button BtnView, BtnDelete;
    // Declare AddNewProduct Object.
    AddNewProductModel addNewProductModelObject = new AddNewProductModel();

    /**
     * Add New Product
     *
     * @param event .
     */
    @FXML
    private void AddProductName(ActionEvent event) {
        // Declare Manufacture and ExpireDate 
        String getManufacture, getExpireDate;
        // Check Manufacture Value Is Null.
        if (ManufacturedDate.getValue() != null) {
            // Get Manufacture Date
            getManufacture = ManufacturedDate.getValue().toString();
        } else {
            getManufacture = "";
            ManufacturedDate.setStyle("-fx-border-color:red;");
        }
        // Check Expired Value Is Null.
        if (ExpiredDate.getValue() != null) {
            getExpireDate = ExpiredDate.getValue().toString();
        } else {
            getExpireDate = "";
            ExpiredDate.setStyle("-fx-border-color:red;");
        }
        // If Text Field Is Not Empty.
        if (!(ProductName.getText().trim().isEmpty() && Quanlity.getText().trim().isEmpty() && RetailerPrice.getText().trim().isEmpty() && Detail.getText().trim().isEmpty() && getManufacture.isEmpty() && getExpireDate.isEmpty())) {
            // Random Number Generator.
            Random random = new Random();
            String productRandomCode = String.format("%04d", random.nextInt(10000));
            // Add Product Function Call.
            boolean checkInsert;
            checkInsert = addNewProductModelObject.AddProductDataBase(ProductName.getText().trim(), Integer.parseInt(Quanlity.getText()), Integer.parseInt(RetailerPrice.getText()), Detail.getText().trim(), getManufacture, getExpireDate, productRandomCode);
            // Check Product Data Is Insert Or Not.
            if (checkInsert) {
                addItemTableView(productRandomCode);
            } else {
                System.out.println("Data Insert" + checkInsert);
            }

        } else {
            // Check Text Field Color.
            ProductName.getStyleClass().add("error");
            Quanlity.getStyleClass().add("error");
            RetailerPrice.getStyleClass().add("error");
            Detail.getStyleClass().add("error");

        }
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Disable The Button Until Table Item Is Not Click.
        BtnView.disableProperty().bind(Bindings.isEmpty(addProductTable.getSelectionModel().getSelectedItems()));
        BtnDelete.disableProperty().bind(Bindings.isEmpty(addProductTable.getSelectionModel().getSelectedItems()));

        // Set CellValueFactory.
        productCode.setCellValueFactory(new PropertyValueFactory<AddProduct, String>("ProductCode"));
        productName.setCellValueFactory(new PropertyValueFactory<AddProduct, String>("ProductName"));
        productQuanlity.setCellValueFactory(new PropertyValueFactory<AddProduct, Integer>("Quanlity"));
        manufacturedData.setCellValueFactory(new PropertyValueFactory<AddProduct, String>("ManufacturedDate"));
        expireDate.setCellValueFactory(new PropertyValueFactory<AddProduct, String>("ExpiredDate"));
        addProductTable.setItems(data);

        // Add Product Data To Table View Function Call
        addProductDatabaseFromTableView(data);

    }

    /**
     * Add New Item To Table View
     *
     * @param productCode
     */
    private void addItemTableView(String productCode) {
        // Set Product Data According To Object.
        AddProduct addProduct = new AddProduct(productCode, ProductName.getText(), Integer.parseInt(Quanlity.getText()), ManufacturedDate.getValue().toString(), ExpiredDate.getValue().toString());
        // Add Product Data.
        data.add(0, addProduct);
    }

    /**
     * Add Product Data To Table View From Database
     *
     * @param data
     */
    public void addProductDatabaseFromTableView(ObservableList<AddProduct> data) {
        try {
            //addProductTable.setItems(data);
            ResultSet resultSet = addNewProductModelObject.getAllProduct();
            // Get All Product
            while (resultSet.next()) {

                AddProduct addProduct = new AddProduct(resultSet.getString("Product_Code"), resultSet.getString("Product_Name"), resultSet.getInt("Quanlity"), resultSet.getString("Manufactured_date"), resultSet.getString("Expired_date"));
                // Add New Product.
                data.add(addProduct);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddNewProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * View Product Item Button
     *
     * @param event
     */
    @FXML
    private void ButtonViewAction(ActionEvent event) throws SQLException {
        // Get Select Item Data.
        AddProduct addProduct = addProductTable.getSelectionModel().getSelectedItem();
        // Get Product Code
        ResultSet productData = addNewProductModelObject.getProductByCode(addProduct.getProductCode());

        try {
            // Declare Product Edit Controller Object.
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/ProductEdit.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            ProductEditController editProduct = fxmlLoader.<ProductEditController>getController();
            // Set Edit Product.
            editProduct.SetEditProduct(productData);
            editProduct.primaryData = data;
            editProduct.SetProductData();
            // Set Title.
            stage.setTitle("Edit Product");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    /**
     * Clear Table Data
     *
     * @param data
     */
    public void ClearTableData(ObservableList<AddProduct> data) {
        data.removeAll(data);
    }

    /**
     * Add Delete Button
     *
     * @param event
     */
    @FXML
    private void ButtonDeleteAction(ActionEvent event) throws SQLException {
        // Alert Dialog Code.
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Delete Product");
        alert.setContentText("Are you sure you want to delete this Product?");
        Optional<ButtonType> result = alert.showAndWait();

        // If Button is Yes.
        if (result.get() == ButtonType.OK) {
            //System.out.println("OK");
            AddProduct addProduct = addProductTable.getSelectionModel().getSelectedItem();
            // Function Call For Delete Product By Product Code.
            boolean checkDelete = addNewProductModelObject.deleteProduct(addProduct.getProductCode());
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
     * Add Product Class
     */
    public static class AddProduct {

        // Declare Private Variable.
        private SimpleStringProperty ProductCode;
        // Declare Private Variable.
        private SimpleStringProperty ProductName;
        // Declare Private Variable.
        private SimpleIntegerProperty Quanlity;
        // Declare Private Variable.
        private SimpleStringProperty ManufacturedDate;
        // Declare Private Variable.
        private SimpleStringProperty ExpiredDate;

        // Constructor.
        private AddProduct(String productCode, String productName, int quanlity, String manufacturedDate, String expiredDate) {
            this.ProductCode = new SimpleStringProperty(productCode);
            this.ProductName = new SimpleStringProperty(productName);
            this.Quanlity = new SimpleIntegerProperty(quanlity);
            this.ManufacturedDate = new SimpleStringProperty(manufacturedDate);
            this.ExpiredDate = new SimpleStringProperty(expiredDate);
        }

        // Set or Get Function.
        public void setProductCode(String ProductCodes) {
            ProductCode.set(ProductCodes);
        }

        public void setProductName(String ProductNames) {
            ProductName.set(ProductNames);
        }

        public void setQuanlity(Integer Quanlitys) {
            Quanlity.set(Quanlitys);
        }

        public void setManufacturedDate(String ManufacturedDates) {
            ManufacturedDate.set(ManufacturedDates);
        }

        public void setExpiredDate(String ExpiredDates) {
            ExpiredDate.set(ExpiredDates);
        }

        public String getProductCode() {
            return ProductCode.get();
        }

        public String getProductName() {
            return ProductName.get();
        }

        public Integer getQuanlity() {
            return Quanlity.get();
        }

        public String getManufacturedDate() {
            return ManufacturedDate.get();
        }

        public String getExpiredDate() {
            return ExpiredDate.get();
        }

    }

}
