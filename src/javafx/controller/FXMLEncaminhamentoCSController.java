/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author PC-CASA
 */
public class FXMLEncaminhamentoCSController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
   
    @FXML
    private TableColumn<?, ?> columnTipoGraoEstoque;

    @FXML
    private TableColumn<?, ?> columnQtdGraoEstoque;

    @FXML
    private TableView<?> tableBobinas;

    @FXML
    private VBox vBoxRelatorioGrao;

    @FXML
    void abrirPopupNovo(ActionEvent event) {

    }

  
}
