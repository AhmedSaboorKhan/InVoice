/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.UpdateProductModel;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ahmed Saboor
 */
public class ProductEditController implements Initializable {

    // Declare addNewProduct Controller Variable.
    @FXML
    private AddNewProductController addNewProductController = new AddNewProductController();
    // Declare String for Product Detail
    private String productCode, productName, productDetail, manufacturedDate, expiredDate;
    private int productPrice, prodcutQuanlity;
    // Declare TextField.
    @FXML
    private TextField ProductName, RetailPrice, Quanlity;
    // Declare DatePicker.
    @FXML
    private DatePicker ManuDate, ExpDate;
    // Declare Button.
    @FXML
    private Button btnUpdate, BtnClose;
    // Declare Update Product Model.
    UpdateProductModel productModelObject = new UpdateProductModel();

    // Declare Product TableView
    @FXML
    public TableView<AddNewProductController.AddProduct> addProductTable = new TableView<AddNewProductController.AddProduct>();

    // Declare Observabilelist.
    @FXML
    public ObservableList<AddNewProductController.AddProduct> primaryData = FXCollections.observableArrayList();
    
    // Constructor.    
    public ProductEditController() {
        this.productCode = null;
        this.productName = null;
        this.productDetail = null;
        this.manufacturedDate = null;
        this.expiredDate = null;
        this.productPrice = 0;
        this.prodcutQuanlity = 0;
    }

    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    // Set Edit Product
    /**
     * Set Edit Product
     * @param resultObject
     */
    public void SetEditProduct(ResultSet resultObject) throws SQLException {
        while (resultObject.next()) {
            this.productCode = resultObject.getString("Product_Code");
            this.productName = resultObject.getString("Product_Name");
            this.productDetail = "Empty";
            this.manufacturedDate = resultObject.getString("Manufactured_date");
            this.expiredDate = resultObject.getString("Expired_date");
            this.productPrice = resultObject.getInt("Retailer_price");
            this.prodcutQuanlity = resultObject.getInt("Quanlity");
        }
    }

    /**
     * Set Product Data on TextField
     * @param actionEvent 
     */
    @FXML
    public void SetProductData() {
        //System.out.println("SetProductData");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate manuDate = LocalDate.parse(getManufacturedDate(), formatter);
        LocalDate expDate = LocalDate.parse(getExpiredDate(), formatter);
        
        ProductName.setText(getProductName());
        RetailPrice.setText(Integer.toString(getProductPrice()));
        Quanlity.setText(Integer.toString(getProdcutQuanlity()));
        ManuDate.setValue(manuDate);
        ExpDate.setValue(expDate);

    }

    /**
     * Update Product Data
     * @param actionEvent 
     */
    @FXML
    public void UpdateProductAction(ActionEvent actionEvent) {
        //System.out.println("UpdateProductAction");
        productModelObject.setProductName(ProductName.getText());
        productModelObject.setProductCode(getProductCode());
        productModelObject.setPrice(Integer.parseInt(RetailPrice.getText()));
        productModelObject.setQuanlity(Integer.parseInt(Quanlity.getText()));
        productModelObject.setManufactureDate(ManuDate.getValue().toString());
        productModelObject.setExpiredDate(ExpDate.getValue().toString());

        boolean checkUpdate = productModelObject.updateProductData();

        if (checkUpdate) {
            
            primaryData.clear();
            addNewProductController.addProductDatabaseFromTableView(primaryData);
            Stage stage = (Stage) BtnClose.getScene().getWindow();
            stage.close();
        }
    }
    
    /**
     * Close Button
     * @param actionEvent 
     */
    @FXML
    public void CloseProductAction(ActionEvent actionEvent) {
        //System.out.println("CloseProductAction");
        Stage stage = (Stage) BtnClose.getScene().getWindow();
        stage.close();
    }

    /**
     * @return the productCode
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @return the productDetail
     */
    public String getProductDetail() {
        return productDetail;
    }

    /**
     * @return the manufacturedDate
     */
    public String getManufacturedDate() {
        return manufacturedDate;
    }

    /**
     * @return the expiredDate
     */
    public String getExpiredDate() {
        return expiredDate;
    }

    /**
     * @return the productPrice
     */
    public int getProductPrice() {
        return productPrice;
    }

    /**
     * @return the prodcutQuanlity
     */
    public int getProdcutQuanlity() {
        return prodcutQuanlity;
    }

}
