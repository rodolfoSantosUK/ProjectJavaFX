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
import javafx.model.dao.EntregaDAO;
import javafx.model.dao.EstoqueDAO;
import javafx.model.database.Database;
import javafx.model.database.DatabaseMySQL; 
import javafx.model.domain.Entrega;
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
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Rodolfo
 */
public class EstoqueSaidaController implements Initializable {

    @FXML
    private Label labelTotalRegistro;

    @FXML
    private TableColumn<Entrega, String> columnCor;

    @FXML
    private TableView<Entrega> tableSaidaEstoque;

    @FXML
    private Stage dialogStage;
    
    @FXML
    private VBox vBoxSaidaEstoque;

    @FXML
    private TableColumn<Entrega, String> columnPeso;

    @FXML
    private TableColumn<Entrega, String> columnQuantidade;

    @FXML
    private TableColumn<Entrega, String> columnProduto;

    @FXML
    private TableColumn<Saco, String> columnCliente;
    
    @FXML
    private TextField paramProduto;
    
    @FXML
    private TextField paramCor;
    
    private final Database databaseMySql = new DatabaseMySQL();
    
    private final Connection connection = databaseMySql.conectar();
    
    private EntregaDAO entregaDao = new EntregaDAO();
    
    private EstoqueDAO estoqueDao = new EstoqueDAO();
    
    private List<Entrega> listaEntregas;
    
    private ObservableList<Entrega> observableListEntrega;
    
    private Entrega entrega;
    
    @FXML
    void excluir(ActionEvent event) {
        entregaDao.setConnection(null);
        estoqueDao.setConnection(null);
        
        Saco sacoEncontrado = new Saco();
        Saco sacoPesquisa = new Saco();
        sacoPesquisa.setEspecificacao(entrega.getEspecificacao());
        sacoPesquisa.setCor(entrega.getCor());
       
        sacoPesquisa.setAlt(entrega.getAlt());
        sacoPesquisa.setLarg(entrega.getLarg());
        sacoPesquisa.setComp(entrega.getComp());
        
        sacoEncontrado = estoqueDao.pesquisarSacoEspecifico(sacoPesquisa);
        System.out.println("sacoEncontrado.getEpec" + sacoEncontrado.getEspecificacao());
        sacoEncontrado.setQuantidade(sacoEncontrado.getQuantidade() + entrega.getQuantidade());
        
        estoqueDao.atualizarQuantidadeSaco(sacoEncontrado);
        
        entregaDao.remover(this.entrega);
        exibirNotificacao();
        carregarTableViewSaidaEstoque();
    }

    
    @FXML
    void atualizar(ActionEvent event) {
       carregarTableViewSaidaEstoque();
       tableSaidaEstoque.refresh();
    }
 
    @FXML
    public void pesquisar(ActionEvent event) {

        Entrega entregaConsulta = new Entrega();
        entregaConsulta.setEspecificacao(paramProduto.getText());
        entregaConsulta.setCor(paramCor.getText());
         
        carregarTableViewEntregaPesquisa(entregaConsulta);
        exibirNotificacaoPesquisa(listaEntregas.size());
    }
    
    public void carregarTableViewEntregaPesquisa(Entrega entrega) {
        columnProduto.setCellValueFactory(new PropertyValueFactory<>("especificacao"));
        columnCor.setCellValueFactory(new PropertyValueFactory<>("cor"));
        columnPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        columnQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        columnCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
            
        listaEntregas = null;
        entregaDao.setConnection(null);
        listaEntregas = entregaDao.pesquisar(entrega);
        observableListEntrega = FXCollections.observableArrayList(listaEntregas);
        tableSaidaEstoque.setItems(observableListEntrega);
        labelTotalRegistro.setText(String.valueOf(tableSaidaEstoque.getItems().size()));
        tableSaidaEstoque.refresh();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
        
        carregarTableViewSaidaEstoque();
        
        tableSaidaEstoque.refresh();
        
        // Listener acionado diante de quaisquer alterações na seleção de itens do TableView
        tableSaidaEstoque.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewSaco(newValue));
    
    }

    public void selecionarItemTableViewSaco(Entrega entrega){
        if (entrega != null) {
            this.entrega = entrega;
            
      } else {
            this.entrega = null; 
        }
    }
         
    @FXML
    void atualizarTabela(ActionEvent event) {
    carregarTableViewSaidaEstoque();
    tableSaidaEstoque.refresh();
    }
    
    public void carregarTableViewSaidaEstoque() {
        columnProduto.setCellValueFactory(new PropertyValueFactory<>("especificacao"));
        columnCor.setCellValueFactory(new PropertyValueFactory<>("cor"));
        columnPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        columnQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        columnCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        
        entregaDao.setConnection(null);
        listaEntregas = entregaDao.listar();
        observableListEntrega = FXCollections.observableArrayList(listaEntregas);
        tableSaidaEstoque.setItems(observableListEntrega);
        labelTotalRegistro.setText(String.valueOf(tableSaidaEstoque.getItems().size()));
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
        loader.setLocation(FxmlSolicProdEXDialogController.class.getResource("/javafx/view/estoque/estoqueSaidaDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
       
        dialogStage = new Stage();
        dialogStage.setTitle("CDPI");
        dialogStage.setResizable(false);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        
        EditarSaidaEstoqueDialog controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setTipoOperacao(TipoOperacaoEnum.ALTERACAO.getTipoOperacao());
        controller.getProdutoTxt().setText(this.entrega.getEspecificacao());
        controller.getCorTxt().setText(this.entrega.getCor());
        controller.getPesoTxt().setText(String.valueOf(this.entrega.getPeso()));
        controller.getQuantidadeTxt().setText(String.valueOf(this.entrega.getQuantidade()));
        controller.getObservacaoTxt().setText(this.entrega.getObservacao());
        controller.setIdEntrega(this.entrega.getIdEntrega());
        controller.getClienteTxt().setText(this.entrega.getCliente());
        controller.getAltTxt().setText(String.valueOf(this.entrega.getAlt()));
        controller.getLargTxt().setText(String.valueOf(this.entrega.getLarg()));
        controller.getCompTxt().setText(String.valueOf(this.entrega.getComp()));
        
        controller.getPesoTxt().setVisible(true);
        controller.getLabelPeso().setVisible(true);
        controller.getPesoTxt().setDisable(true);
        controller.getLabelPeso().setDisable(true);
        
        // Mostra o Dialog e espera até que o usuário o feche 
        dialogStage.show();
 
    }   
    
 
 public void abrirPopupNovo() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(SaidaEstoqueDialogController.class.getResource("/javafx/view/estoque/lancarSaidaDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
       
        dialogStage = new Stage();
        dialogStage.setTitle("CDPI");
        dialogStage.setResizable(false);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

       SaidaEstoqueDialogController controller = loader.getController();
       controller.setDialogStage(dialogStage);
       controller.setTipoOperacao(TipoOperacaoEnum.CRIACAO.getTipoOperacao());

        // Mostra o Dialog e espera até que o usuário o feche 
        dialogStage.show();
 
    }
 
 
}
