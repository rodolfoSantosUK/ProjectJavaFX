package javafx.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.model.domain.Bobina;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
 
public class FXMLBobinasTurnoController implements Initializable {

    @FXML
    private TableColumn<Bobina, String> columnCliente;
     
    @FXML
    private TableColumn<Bobina, String> columnTurno;

    @FXML
    private TableColumn<Bobina, String> columnPeso;
    
    @FXML
    private TableColumn<Bobina, String> columnBobina;
    
    @FXML
    private TableColumn<Bobina, String> columnExtrusor;
     
    @FXML
    private TextField paramExtrusor;
    
    @FXML
    private TextField paramPeso;
    
    @FXML
    private TableView<Bobina> tableBobinas;
    
    @FXML
    private ComboBox<?> paramTurno;
    
    @FXML
    private VBox vBoxBobinaTurno;
    
    
    @FXML
    void abrirPopupNovo(ActionEvent event) {

    }

    @FXML
    void abrirPopupEditar(ActionEvent event) {

    }

    @FXML
    void excluirBonina(ActionEvent event) {

    }
 
    @FXML
    void pesquisar(ActionEvent event) {

    }
    
    @FXML
    void limpar(ActionEvent event) {

    }
    
    @FXML
    void atualizar(ActionEvent event) {

    }
    
    @FXML
    private Label labelTotalRegistro;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
     
    

    

   

    
}
