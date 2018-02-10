package javafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

/**
 *
 * @author Rodolfo
 */
  
public class FxmlEntradaPedidoExtrusaoController {
    
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
