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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Models.LoginModel;

/**
 * FXML Controller class
 *
 * @author Ahmed Saboor
 */
public class LoginController implements Initializable {
    // Declare Text Field
    @FXML
    TextField UserName, Password;
    // Declare Button
    @FXML
    private javafx.scene.control.Button LoginButton;
    /**
     * Switch to Main Menu
     * @param event 
     */
    @FXML
    private void handleButtonAction(ActionEvent event) {
        // Create Login Model Object.       
        LoginModel loginObject = new LoginModel();
        // Login Model Login Functional Is Called. 
        if ((UserName.getText().trim().isEmpty() || Password.getText().trim().isEmpty()) || (UserName.getText().trim().isEmpty() && Password.getText().trim().isEmpty())) {
           
            UserName.getStyleClass().add("error");
            Password.getStyleClass().add("error");
        
        } else {
            // Check If User Is Exist or Not.           
            boolean checkExist = loginObject.loginFunctional(UserName.getText(), Password.getText());

            if (checkExist == true) {
                // Close The Existing Window.
                Stage loginStage = (Stage) LoginButton.getScene().getWindow();
                loginStage.close();

                // Switch to New Window.
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/MainMenu.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setTitle("Main Menu");
                    stage.setScene(new Scene(root1));
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                //  Invalid Username And Password.
                UserName.getStyleClass().add("error");
                Password.getStyleClass().add("error");
            }
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
