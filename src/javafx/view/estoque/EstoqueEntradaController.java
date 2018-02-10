/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.view.estoque;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.controller.FxmlSolicProdEXDialogController;
import javafx.enumeration.TipoOperacaoEnum;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.model.dao.EstoqueDAO;
import javafx.model.dao.PigmentoDAO;
import javafx.model.database.Database;
import javafx.model.database.DatabaseMySQL;
import javafx.model.domain.Pigmento;
import javafx.model.domain.Saco;
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
 * @author PC-CASA
 */
public class EstoqueEntradaController implements Initializable {

    @FXML
    private Label labelTotalRegistro;

    @FXML
    private TableColumn<Saco, String> columnCor;

    @FXML
    private TableView<Saco> tableEntradaEstoque;

    @FXML
    private Stage dialogStage;
    
    @FXML
    private VBox vBoxEntradaEstoque;

    @FXML
    private TableColumn<Saco, String> columnPeso;

    @FXML
    private TableColumn<Saco, String> columnQuantidade;

    @FXML
    private TableColumn<Saco, String> columnProduto;

    @FXML
    private TextField paramProduto;
    
    @FXML
    private TextField paramCor;
    private final Database databaseMySql = new DatabaseMySQL();
    
    private final Connection connection = databaseMySql.conectar();
    
    private EstoqueDAO estoqueDao = new EstoqueDAO();
    
    private List<Saco> listaSacos;
    
    private ObservableList<Saco> observableListSaco;
    
    private Saco saco;
    
     

    @FXML
    void excluir(ActionEvent event) {
        estoqueDao.setConnection(null);
        estoqueDao.remover(this.saco);
        exibirNotificacao();
        carregarTableViewEntradaEstoque();
    }

    
    @FXML
    void atualizar(ActionEvent event) {
       carregarTableViewEntradaEstoque();
       tableEntradaEstoque.refresh();
    }
 
    @FXML
    public void pesquisar(ActionEvent event) {

        Saco sacoConsulta = new Saco();
        sacoConsulta.setEspecificacao(paramProduto.getText());
        sacoConsulta.setCor(paramCor.getText());
         
        carregarTableViewPigmentoPesquisa(sacoConsulta);
        exibirNotificacaoPesquisa(listaSacos.size());
    }
    
    public void carregarTableViewPigmentoPesquisa(Saco saco) {
        columnProduto.setCellValueFactory(new PropertyValueFactory<>("especificacao"));
        columnCor.setCellValueFactory(new PropertyValueFactory<>("cor"));
        columnPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        columnQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        
        listaSacos = null;
        estoqueDao.setConnection(null);
        listaSacos = estoqueDao.pesquisar(saco);
        observableListSaco = FXCollections.observableArrayList(listaSacos);
        tableEntradaEstoque.setItems(observableListSaco);
        labelTotalRegistro.setText(String.valueOf(tableEntradaEstoque.getItems().size()));
        tableEntradaEstoque.refresh();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
        
        carregarTableViewEntradaEstoque();
        
        tableEntradaEstoque.refresh();
        
        // Listener acionado diante de quaisquer alterações na seleção de itens do TableView
        tableEntradaEstoque.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewSaco(newValue));
    
    }

    public void selecionarItemTableViewSaco(Saco saco){
        if (saco != null) {
            this.saco = saco;
            
      } else {
            this.saco = null; 
        }
    }
         
    @FXML
    void atualizarTabela(ActionEvent event) {
    carregarTableViewEntradaEstoque();
    tableEntradaEstoque.refresh();
    }
    
    public void carregarTableViewEntradaEstoque() {
        columnProduto.setCellValueFactory(new PropertyValueFactory<>("especificacao"));
        columnCor.setCellValueFactory(new PropertyValueFactory<>("cor"));
        columnPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        columnQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        
        estoqueDao.setConnection(null);
        listaSacos = estoqueDao.listar();
        observableListSaco= FXCollections.observableArrayList(listaSacos);
        tableEntradaEstoque.setItems(observableListSaco);
        labelTotalRegistro.setText(String.valueOf(tableEntradaEstoque.getItems().size()));
    }
      
    
     @FXML
    public void limpar(ActionEvent event) {
       paramProduto.setText(null);
       paramCor.setText(null);
    }
    
     private void exibirNotificacao() {
        
         Image img = new Image("javafx/resources/rem_ok.png");
         
         Notifications notificationBuilder = 
                 Notifications.create()
                         .title("Exclusão de produto")
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
                         .title("Pesquisa de produtos em estoque! ")
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
    
 public void abrirPopupEditar() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FxmlSolicProdEXDialogController.class.getResource("/javafx/view/estoque/estoqueEntradaDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
       
        dialogStage = new Stage();
        dialogStage.setTitle("CDPI");
        dialogStage.setResizable(false);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        
        EntradaEstoqueDialog controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setTipoOperacao(TipoOperacaoEnum.ALTERACAO.getTipoOperacao());
        controller.getProdutoTxt().setText(this.saco.getEspecificacao());
        controller.getCorTxt().setText(this.saco.getCor());
        controller.getPesoTxt().setText(String.valueOf(this.saco.getPeso()));
        controller.getAltTxt().setText(String.valueOf(this.saco.getAlt()));
        controller.getLargTxt().setText(String.valueOf(this.saco.getLarg()));
        controller.getCompTxt().setText(String.valueOf(this.saco.getComp()));
        controller.getQuantidadeTxt().setText(String.valueOf(this.saco.getQuantidade()));
        controller.getObservacaoTxt().setText(this.saco.getObservacao());
        controller.setIdSaco(this.saco.getIdSaco());
        
        controller.getPesoTxt().setVisible(true);
        controller.getPesoLabel().setVisible(true);
        controller.getPesoTxt().setDisable(true);
        controller.getPesoLabel().setDisable(true);
        
        // Mostra o Dialog e espera até que o usuário o feche 
        dialogStage.show();
 
    }   
    
 
 public void abrirPopupNovo() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FxmlSolicProdEXDialogController.class.getResource("/javafx/view/estoque/estoqueEntradaDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
       
        dialogStage = new Stage();
        dialogStage.setTitle("CDPI");
        dialogStage.setResizable(false);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

       EntradaEstoqueDialog controller = loader.getController();
       controller.setDialogStage(dialogStage);
       controller.setTipoOperacao(TipoOperacaoEnum.CRIACAO.getTipoOperacao());

        // Mostra o Dialog e espera até que o usuário o feche 
        dialogStage.show();
 
    }
 
}
