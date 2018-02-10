package javafx.view.estoque;

import java.sql.Connection;
import javafx.fxml.FXML;
import javafx.model.dao.EstoqueDAO;
import javafx.model.database.Database;
import javafx.model.database.DatabaseMySQL;
import javafx.stage.Stage;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos; 
import javafx.model.dao.EntregaDAO;
import javafx.model.dao.EstoqueDAO; 
import javafx.model.database.Database;
import javafx.model.database.DatabaseMySQL; 
import javafx.model.domain.Entrega;
import javafx.model.domain.Saco; 
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView; 
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.AnimationValidationField;
import javafx.util.Duration; 
import org.controlsfx.control.Notifications;


public class SaidaEstoqueDialogController implements Initializable {
  
    @FXML
    private TableColumn<Saco,String> columnCor;

    @FXML
    private TextField paramProduto;

    @FXML
    private AnchorPane anchorSaidaEstoque;

    @FXML
    private TableColumn<Saco, String> columnQuantidade;
 
    @FXML
    private TextField produtoRow;

    @FXML
    private TableView<Saco> tableEstoqueDisponivel;

    private List<Saco> listaSacos;
    
    private ObservableList<Saco> observableListSaco;
    
    private Saco saco;
    
    @FXML
    private TableColumn<Saco,String> columnProduto;

    @FXML
    private TextField paramCor;

    @FXML
    private TextField qtdSaida;

    @FXML
    private TextField pesoRow;

    @FXML
    private Label labelTotalRegistro;

    @FXML
    private TextField corRow;

    @FXML
    private TextField qtdDisponivelRow;

    @FXML
    private TableColumn<Saco, String> columnPeso;

    @FXML
    private TextField clienteRow;
    
    private final Database databaseMySql = new DatabaseMySQL();
    
    private final Connection connection = databaseMySql.conectar();
    
    private EstoqueDAO estoqueDao = new EstoqueDAO();
    
    private EntregaDAO entregaDao = new EntregaDAO();
    
    private String tipoOperacao;
     
 
    @FXML
    void limpar(ActionEvent event) {

    }

    @FXML
    void pesquisar(ActionEvent event) {

    }

    @FXML
    void salvarSaidaEstoque(ActionEvent event) {

       //Se a quantidade disponivel for maior que a quantidade de saída tudo ok! 
      if(new BigDecimal(qtdDisponivelRow.getText()).compareTo(new BigDecimal(qtdSaida.getText())) >= 0 ) {
            
       Saco sacoEncontrado = new Saco();     
            
       estoqueDao.setConnection(null);
    //Consulta Saco específico
       sacoEncontrado =   estoqueDao.pesquisarSacoById(this.saco);
       
       System.out.println("SACO ENCONTRADO"  +  sacoEncontrado.getEspecificacao());
       
    //Atualiza quantidade do saco no estoque
    BigDecimal novaQuantidade = new BigDecimal(BigInteger.ZERO);
    novaQuantidade = new BigDecimal(qtdDisponivelRow.getText()).subtract(new BigDecimal(qtdSaida.getText()));
    sacoEncontrado.setQuantidade(Long.valueOf(novaQuantidade.toString()));
       estoqueDao.atualizarQuantidadeSaco(sacoEncontrado);
       entregaDao.setConnection(null);
    //Cria saída de estoque (Entrega)   
       Entrega entrega = new Entrega();
       entrega.setEspecificacao(produtoRow.getText());
       entrega.setCor(corRow.getText());
       entrega.setPeso(new BigDecimal(pesoRow.getText())); 
       entrega.setObservacao(null);
       entrega.setQuantidade(Long.valueOf(qtdSaida.getText()));
       entrega.setCliente(clienteRow.getText());
       entrega.setAlt(saco.getAlt());
       entrega.setLarg(saco.getLarg());
       entrega.setComp(saco.getComp());
       
       entregaDao.inserir(entrega);
            
       fecharPopup();
       exibirNotificacao("Lançar saída");
       
       } else {
      // mensagem de erro
            
       }
    
    }
 
    private void exibirNotificacao(String titulo) {
        
         Image img = new Image("javafx/resources/check_ok.png");
         
         Notifications notificationBuilder = 
                 Notifications.create()
                         .title(titulo)
                         .text("Dados salvos com sucesso, clique no botão atualizar!")
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
    
    
    @FXML
    private Stage dialogStage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        estoqueDao.setConnection(connection);
        carregarTableViewEntradaEstoque();
        
        tableEstoqueDisponivel.refresh();
        
        // Listener acionado diante de quaisquer alterações na seleção de itens do TableView
        tableEstoqueDisponivel.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewSaco(newValue));
        
    }    
        
    public void selecionarItemTableViewSaco(Saco saco){
        if (saco != null) {
            this.saco = saco;
            this.pesoRow.setText(String.valueOf(this.saco.getPeso()));
            this.corRow.setText(String.valueOf(this.saco.getCor()));
            this.qtdDisponivelRow.setText(String.valueOf(this.saco.getQuantidade()));
            this.produtoRow.setText(String.valueOf(this.saco.getEspecificacao()));
            
            System.out.println("Saco selecionado" + this.saco.getEspecificacao());
            
            
      } else {
            this.saco = null; 
        }
    }
    
    
    public void carregarTableViewEntradaEstoque() {
        columnProduto.setCellValueFactory(new PropertyValueFactory<>("especificacao"));
        columnCor.setCellValueFactory(new PropertyValueFactory<>("cor"));
        columnPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        columnQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        
        estoqueDao.setConnection(null);
        listaSacos = estoqueDao.listar();
        observableListSaco= FXCollections.observableArrayList(listaSacos);
        tableEstoqueDisponivel.setItems(observableListSaco);
        labelTotalRegistro.setText(String.valueOf(tableEstoqueDisponivel.getItems().size()));
    }
    
    public void fecharPopup() {
         getDialogStage().close();
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
     
    public String getTipoOperacao() {
        return tipoOperacao;
    }
 
    public void setTipoOperacao(String tipoOperacao) {
        this.tipoOperacao = tipoOperacao;
    }
    
    
    
    
}
