package javafx.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Rodolfo
 */
public class FxmlLoginPageController implements Initializable  {
    
    @FXML
    private JFXButton loginBtn;

    @FXML
    private JFXButton cancelBtn;

    @FXML
    private JFXTextField usuarioText;

    @FXML
    private JFXPasswordField senhaText;

    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    public void handleLogin(ActionEvent event) {

        String usuario = usuarioText.getText();
        String senha = usuarioText.getText();
    }
    
    
     /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
           
        
    }
    
 
  
     

  
    
}
