/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.main;

import java.io.IOException; 
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader; 
import javafx.scene.Scene; 
import javafx.scene.layout.VBox;
import javafx.stage.Stage; 
/**
 *
 * @author Rodolfo
 */
public class Main extends Application {
    
    
@Override
    public void start(Stage stage) throws IOException {
       
       // AnchorPane rootAnchorPane = FXMLLoader.load(getClass().getResource("/javafx/view/fxmlLoginPage.fxml"));
       // VBox rootAnchorPane = FXMLLoader.load(getClass().getResource("/javafx/view/forex.fxml"));
        VBox rootAnchorPane = FXMLLoader.load(getClass().getResource("/javafx/view/fxmlInicio - Copia.fxml"));
        Scene scene = new Scene(rootAnchorPane);
        
        stage.setScene(scene);
        stage.setWidth(1500.0);
        stage.setHeight(900.0);
        // stage.setMaximized(true);
        stage.setResizable(false);
        stage.show();
   
                
    }

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
