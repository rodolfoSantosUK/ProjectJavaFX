 
package javafx.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle; 
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.enumeration.TipoOperacaoEnum;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.model.dao.PigmentoDAO;
import javafx.model.database.Database;
import javafx.model.database.DatabaseMySQL; 
import javafx.model.domain.Pigmento;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.view.extrusao.ControlePigmentoDialog;
import org.controlsfx.control.Notifications;
/**
 * FXML Controller class
 *
 * @author Rodolfo Santos
 */
public class FXMLControlePigmentoController implements Initializable {

   
    @FXML
    private Stage dialogStage;
    
    @FXML
    private TableColumn<Pigmento, String> columTipoPigmento;

    @FXML
    private TableView<Pigmento> tablePigmento;

    @FXML
    private TableColumn<Pigmento, String> columnEstoque;

    @FXML
    private TableColumn<Pigmento, String> columnFornecedor;
    
    @FXML
    private VBox vBoxPigmento;

    private List<Pigmento> listaPigmentos;
    
    private final PigmentoDAO pigmentoDao = new PigmentoDAO();
    
    private ObservableList<Pigmento> observableListPigmento;
    
    private Pigmento pigmento;
    
    @FXML
    private TextField paramTipoPigmento;
	
    @FXML
    private TextField paramFornecedor;
	
    @FXML
    private Label totalRegistro;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        carregarTableViewPigmento();
        tablePigmento.refresh();
        
        // Listener acionado diante de quaisquer alterações na seleção de itens do TableView
        tablePigmento.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewClientes(newValue));
    
    } 
         
    public void selecionarItemTableViewClientes(Pigmento pigmento){
        if (pigmento != null) {
            this.pigmento = pigmento;
        } else {
            this.pigmento = null; 
        }
    }
    
    @FXML
    void atualizarTabela(ActionEvent event) {
        carregarTableViewPigmento();
        tablePigmento.refresh();
    }
    
    public void carregarTableViewPigmento() {
        columnFornecedor.setCellValueFactory(new PropertyValueFactory<>("fornecedor"));
        columnEstoque.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        columTipoPigmento.setCellValueFactory(new PropertyValueFactory<>("tipoPigmento"));
        
        pigmentoDao.setConnection(null);  
        listaPigmentos = pigmentoDao.listar();
         
        observableListPigmento = FXCollections.observableArrayList(listaPigmentos);
        tablePigmento.setItems(observableListPigmento);
        totalRegistro.setText(String.valueOf(tablePigmento.getItems().size()));
    }
    
  
     public void abrirPopupEditar() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FxmlSolicProdEXDialogController.class.getResource("/javafx/view/extrusao/manterControlePigmentoDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
       
        dialogStage = new Stage();
        dialogStage.setTitle("CDPI");
        dialogStage.setResizable(false);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        
        ControlePigmentoDialog controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setTipoOperacao(TipoOperacaoEnum.ALTERACAO.getTipoOperacao());
        controller.getFornecedorTxt().setText(this.pigmento.getFornecedor());
        controller.getTipoPigmentoTxt().setText(this.pigmento.getTipoPigmento());
        controller.getEmEstoqueTxt().setText(String.valueOf(this.pigmento.getQuantidade()));
        controller.getObservacaoPigmentoTxt().setText(this.pigmento.getObservacao());
        controller.setIdPigmento(this.pigmento.getIdPigmento());
        
        // Mostra o Dialog e espera até que o usuário o feche 
        dialogStage.show();
 
    }
            
    
    public void abrirPopupNovo() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FxmlSolicProdEXDialogController.class.getResource("/javafx/view/extrusao/manterControlePigmentoDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
       
        dialogStage = new Stage();
        dialogStage.setTitle("CDPI");
        dialogStage.setResizable(false);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

       ControlePigmentoDialog controller = loader.getController();
       controller.setDialogStage(dialogStage);
       controller.setTipoOperacao(TipoOperacaoEnum.CRIACAO.getTipoOperacao());

        // Mostra o Dialog e espera até que o usuário o feche 
        dialogStage.show();
 
    }
    
    
    private void exibirNotificacao() {
        
        Image img = new Image("javafx/resources/rem_ok.png");
         
        Notifications notificationBuilder = 
                 Notifications.create()
                         .title("Exclusão de pigmento")
                         .text("1 registro da tabela foi removido.")
                         .graphic(new ImageView(img))
                         .hideAfter(Duration.seconds(5))
                         .position(Pos.BOTTOM_RIGHT).onAction(
                          new EventHandler<ActionEvent>(){
                          @Override  
                          public void handle(ActionEvent event) {
                          }   
                         }
                       );
         notificationBuilder.darkStyle();
         notificationBuilder.show();
     }
    
    private void exibirNotificacaoPesquisa(int quantidade) {
        
         Image img = new Image("javafx/resources/check_list.png");
         
         String texto = String.valueOf(quantidade);
         texto += " registro(s) encontrado(s).";
         
         
         Notifications notificationBuilder = 
                 Notifications.create()
                         .title("Pesquisa de pigmentos! ")
                         .text(texto)
                         .graphic(new ImageView(img))
                         .hideAfter(Duration.seconds(5))
                         .position(Pos.CENTER).onAction(
                          new EventHandler<ActionEvent>(){
                          @Override  
                          public void handle(ActionEvent event) {
                          }   
                         }
                       );
         notificationBuilder.darkStyle();
         notificationBuilder.show();
     }
    
    public void carregarTableViewPigmentoPesquisa(Pigmento pigmento) {
        columnFornecedor.setCellValueFactory(new PropertyValueFactory<>("fornecedor"));
        columnEstoque.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        columTipoPigmento.setCellValueFactory(new PropertyValueFactory<>("tipoPigmento"));
        listaPigmentos = null;
        pigmentoDao.setConnection(null);
           
        listaPigmentos = pigmentoDao.pesquisar(pigmento);
            
        observableListPigmento = FXCollections.observableArrayList(listaPigmentos);
        tablePigmento.setItems(observableListPigmento);
        totalRegistro.setText(String.valueOf(tablePigmento.getItems().size()));
        tablePigmento.refresh();
    }
    
    public void removerPigmento() {
        pigmentoDao.setConnection(null);
        pigmentoDao.remover(this.pigmento);
        exibirNotificacao();
        carregarTableViewPigmento();
    }
    
    /**
     * @return the pigmento
     */
    public Pigmento getPigmento() {
        return pigmento;
    }

    /**
     * @param pigmento the pigmento to set
     */
    public void setPigmento(Pigmento pigmento) {
        this.pigmento = pigmento;
    }

    @FXML
    public void pesquisar(ActionEvent event) {

        Pigmento pigmentoConsulta = new Pigmento();
        pigmentoConsulta.setFornecedor(paramFornecedor.getText());
        pigmentoConsulta.setTipoPigmento(paramTipoPigmento.getText());
         
        carregarTableViewPigmentoPesquisa(pigmentoConsulta);
        exibirNotificacaoPesquisa(listaPigmentos.size());
    }

    @FXML
    public void limpar(ActionEvent event) {
       paramFornecedor.setText(null);
       paramTipoPigmento.setText(null);
    }
    
}
