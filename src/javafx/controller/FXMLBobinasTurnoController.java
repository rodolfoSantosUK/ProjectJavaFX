package javafx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.enumeration.TipoOperacaoEnum;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.model.domain.Bobina;
import javafx.model.domain.Pigmento;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
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
 
public class FXMLBobinasTurnoController implements Initializable {

    @FXML
    private Stage dialogStage;
    
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
    
    private Bobina bobina;
    
    private List<Bobina> listaBobinas;
    
    private ObservableList<Bobina> observableListBobina;
    
    @FXML
    private Label labelTotalRegistro;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
//        carregarTableViewBobina();
  //      tableBobinas.refresh();
        
        // Listener acionado diante de quaisquer alterações na seleção de itens do TableView
    //    tableBobinas.getSelectionModel().selectedItemProperty().addListener(
      //          (observable, oldValue, newValue) -> selecionarItemTableViewBobinasTurno(newValue));
    
    }
    
    public void selecionarItemTableViewBobinasTurno(Bobina bobina){
        if (bobina != null) {
            this.bobina = bobina;
        } else {
            this.bobina = null; 
        }
    }
    
    @FXML
    void atualizarTabela(ActionEvent event) {
        carregarTableViewBobina();
        tableBobinas.refresh();
    }
    
    public void carregarTableViewBobina() {
        columnCliente.setCellValueFactory(new PropertyValueFactory<>("fornecedor"));
        columnTurno.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        columnPeso.setCellValueFactory(new PropertyValueFactory<>("tipoPigmento"));
        columnBobina.setCellValueFactory(new PropertyValueFactory<>("tipoPigmento"));;
        columnExtrusor.setCellValueFactory(new PropertyValueFactory<>("tipoPigmento"));;
        
       // pigmentoDao.setConnection(null);  
        listaBobinas = null; // pigmentoDao.listar();
         
        observableListBobina = FXCollections.observableArrayList(listaBobinas);
        tableBobinas.setItems(observableListBobina);
        labelTotalRegistro.setText(String.valueOf(tableBobinas.getItems().size()));
    }
    
    @FXML
    void abrirPopupNovo(ActionEvent event) throws IOException  {

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
    //  controller.getFornecedorTxt().setText(this.pigmento.getFornecedor());
    //  controller.getTipoPigmentoTxt().setText(this.pigmento.getTipoPigmento());
    //  controller.getEmEstoqueTxt().setText(String.valueOf(this.pigmento.getQuantidade()));
    //  controller.getObservacaoPigmentoTxt().setText(this.pigmento.getObservacao());
    //  controller.setIdPigmento(this.pigmento.getIdPigmento());
        
    //  Mostra o Dialog e espera até que o usuário o feche 
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
                         .title("Pesquisa de bobinas produzidas por turno! ")
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
    
    

    @FXML
    void excluirBonina(ActionEvent event) {
       // pigmentoDao.setConnection(null);
       // pigmentoDao.remover(this.pigmento);
       exibirNotificacao();
       carregarTableViewBobina();
    }
 
     @FXML
    public void pesquisar(ActionEvent event) {

        Bobina bobinaConsulta = new Bobina();
      //  bobinaConsulta.setFornecedor(paramFornecedor.getText());
      //  bobinaConsulta.setTipoPigmento(paramTipoPigmento.getText());
        
        carregarTableViewPesquisaBobina(bobinaConsulta);
        exibirNotificacaoPesquisa(listaBobinas.size());
    }

      public void carregarTableViewPesquisaBobina(Bobina bobina) {
    //    columnFornecedor.setCellValueFactory(new PropertyValueFactory<>("fornecedor"));
    //    columnEstoque.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
    //    columTipoPigmento.setCellValueFactory(new PropertyValueFactory<>("tipoPigmento"));
    //    listaPigmentos = null;
    //    pigmentoDao.setConnection(null);
           
    //    listaPigmentos = pigmentoDao.pesquisar(pigmento);
            
    //    observableListPigmento = FXCollections.observableArrayList(listaPigmentos);
    //    tablePigmento.setItems(observableListPigmento);
    //    totalRegistro.setText(String.valueOf(tablePigmento.getItems().size()));
    //    tablePigmento.refresh();
    }
    
    @FXML
    public void limpar(ActionEvent event) {
    //   paramFornecedor.setText(null);
    //   paramTipoPigmento.setText(null);
    }
    
    @FXML
    void atualizar(ActionEvent event) {
        carregarTableViewBobina();
        tableBobinas.refresh();
    }
   
}
