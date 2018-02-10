/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
 
public class FXMLControlePedidosCSController {
    
    
     @FXML
    private TableColumn<?, ?> columnCliente;

    @FXML
    private VBox vBoxPedidosConcluidosCS;

    @FXML
    private TableView<?> tablePedidosConcluidos;

    @FXML
    private TableColumn<?, ?> columnDataEntrega;

    @FXML
    private TableColumn<?, ?> columnStatus;

    @FXML
    private TableColumn<?, ?> columnDataPedido;
    
    
}
