/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.model.domain.Pedido;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rodolfo
 */
public class FxmlSolicProdEXController implements Initializable {

    @FXML
    private Stage dialogStage;
    
    @FXML
    private TextField qtdPigmento;

    @FXML
    private DatePicker datePrazo;

    @FXML
    private TextField nmSolicitante;

    @FXML
    private ComboBox<?> comboBoxStatus;

    @FXML
    private TextField qtdGrao;
    
    
    
    @FXML
    private VBox vboxGrafico;
    
    
     @FXML
    private TableColumn<Pedido, String> tableColumnCodigo, tableColumnTipo,
            tableColumnDataPedido, tableColumnStatus, tableColumnSolicitante,
            tableColumnPrazo, tableColumnQtdGrao,tableColumnQtdPigmento;
    
    private List<Pedido> listaPedidos;
    
    private ObservableList<Pedido> observableListPedidos;
    
    @FXML
    private TableView<Pedido> tableViewPedido;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       carregarTableViewPedido();
        tableViewPedido.getSelectionModel().selectedItemProperty().addListener(
        (observable,oldvalue,newValue) -> selecionarItemTableViewPedidos(newValue));
    }    
    
    
    public void abrirPopupGrafico() throws IOException {
       FXMLLoader loader = new FXMLLoader();
         loader.setLocation(FxmlSolicProdEXGrafico.class.getResource("/javafx/view/fxmlSolicProdEXGrafico.fxml"));
        VBox page = (VBox) loader.load();
       
        dialogStage = new Stage();
        dialogStage.setTitle("CDPI");
        dialogStage.setResizable(false);
        
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

       // Setando o cliente no Controller.
        FxmlSolicProdEXGrafico controller = loader.getController();
        controller.setDialogStage(dialogStage);
         
        dialogStage.show();
        
        
    }
    
    public void selecionarItemTableViewPedidos(Pedido pedido){
    
       // if(cliente != null) {
//         labelClienteCodigo.setText(String.valueOf(cliente.getCdCliente()));
//         labelClienteNome.setText(String.valueOf(cliente.getNome()));
//         labelClienteCPF.setText(String.valueOf(cliente.getCpf()));
//         labelClienteTelefone.setText(String.valueOf(cliente.getTelefone()));
//        }else {
//         labelClienteCodigo.setText("");
//         labelClienteNome.setText("");
//         labelClienteCPF.setText("");
//         labelClienteTelefone.setText(""); 
//        }
        
     }
    
    public void fecharPopup () {
        dialogStage.close();
    }
    
    public void abrirPopupNovo() throws IOException {
        FXMLLoader loader = new FXMLLoader();
         loader.setLocation(FxmlSolicProdEXDialogController.class.getResource("/javafx/view/fxmlSolicProdEXDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        dialogStage = new Stage();
        dialogStage.setTitle("CDPI");
        //dialogStage.initStyle(StageStyle.UNDECORATED);
        dialogStage.setResizable(false);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

       // Setando o cliente no Controller.
        FxmlSolicProdEXDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
         

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.show();
 
    }
    
    
    
    public void carregarTableViewPedido(){
     
        tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
//      tableColumnTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
//      tableColumnDataPedido.setCellValueFactory(new PropertyValueFactory<>("dataPedido"));
        tableColumnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tableColumnSolicitante.setCellValueFactory(new PropertyValueFactory<>("solicitante"));
        tableColumnPrazo.setCellValueFactory(new PropertyValueFactory<>("prazo"));
        tableColumnQtdGrao.setCellValueFactory(new PropertyValueFactory<>("qtdGrao"));
        tableColumnQtdPigmento.setCellValueFactory(new PropertyValueFactory<>("qtdPigmento"));
        
        listaPedidos = new ArrayList<Pedido>();
        mockPedidos(listaPedidos);
        
       
        observableListPedidos = FXCollections.observableArrayList(listaPedidos);
        tableViewPedido.setItems(observableListPedidos);
        
    }
    
    
    
     public void mockPedidos(List<Pedido> pedidos) {
        
        Pedido pedido1 = new Pedido();
        pedido1.setCodigo("EX001");
        pedido1.setTipo("Corte e Solda");
        pedido1.setDataPedido("09/02/2017");
        pedido1.setStatus("Concluído");
        pedido1.setSolicitante("Arnaldo da Silva");
        pedido1.setPrazo("03/04/2017");
        pedido1.setTelSolicitante("(21) 9876-9832");
        pedido1.setEmailSolicitante("arnaldo@contato.com");
        pedido1.setEndSolicitante("Rua Barbosa de Freitas, 98 casa 05");
        pedido1.setQtdGrao(new BigDecimal(1000));
        pedido1.setQtdPigmento(new BigDecimal(250));
        
        Pedido pedido2 = new Pedido();
        pedido2.setCodigo("EX002");
        pedido2.setTipo("Extrusão");
        pedido2.setDataPedido("09/02/2017");
        pedido2.setStatus("Concluído");
        pedido2.setSolicitante("Mário Ribeiro");
        pedido2.setPrazo("03/04/2017");
        pedido2.setTelSolicitante("(21) 9876-9832");
        pedido2.setEmailSolicitante("mario@contato.com");
        pedido2.setEndSolicitante("Rua Barbosa de Freitas, 98 casa 05");
        pedido2.setQtdGrao(new BigDecimal(130));
        pedido2.setQtdPigmento(new BigDecimal(290));
        
        
        Pedido pedido3 = new Pedido();
        pedido3.setCodigo("EX003");
        pedido3.setTipo("Corte e Solda");
        pedido3.setDataPedido("09/02/2017");
        pedido3.setStatus("Concluído");
        pedido3.setSolicitante("Juliano da Silva");
        pedido3.setPrazo("03/04/2017");
        pedido3.setTelSolicitante("(21) 9876-9832");
        pedido3.setEmailSolicitante("juliano@contato.com");
        pedido3.setEndSolicitante("Rua Barbosa de Freitas, 98 casa 05");
        pedido3.setQtdGrao(new BigDecimal(700));
        pedido3.setQtdPigmento(new BigDecimal(300));
        
        Pedido pedido4 = new Pedido();
        pedido4.setCodigo("EX004");
        pedido4.setTipo("Corte e Solda");
        pedido4.setDataPedido("09/02/2017");
        pedido4.setStatus("Concluído");
        pedido4.setSolicitante("Fatima da Silva");
        pedido4.setPrazo("03/04/2017");
        pedido4.setTelSolicitante("(21) 9876-9832");
        pedido4.setEmailSolicitante("juliano@contato.com");
        pedido4.setEndSolicitante("Rua Barbosa de Freitas, 98 casa 05");
        pedido4.setQtdGrao(new BigDecimal(890));
        pedido4.setQtdPigmento(new BigDecimal(700));
        
        Pedido pedido5 = new Pedido();
        pedido5.setCodigo("EX005");
        pedido5.setTipo("Corte e Solda");
        pedido5.setDataPedido("09/02/2017");
        pedido5.setStatus("Em atraso");
        pedido5.setSolicitante("Roberto da Silva");
        pedido5.setPrazo("03/04/2017");
        pedido5.setTelSolicitante("(21) 9876-9832");
        pedido5.setEmailSolicitante("juliano@contato.com");
        pedido5.setEndSolicitante("Rua Barbosa de Freitas, 98 casa 05");
        pedido5.setQtdGrao(new BigDecimal(1100));
        pedido5.setQtdPigmento(new BigDecimal(2100));
        
        Pedido pedido6 = new Pedido();
        pedido6.setCodigo("EX006");
        pedido6.setTipo("Corte e Solda");
        pedido6.setDataPedido("09/02/2017");
        pedido6.setStatus("Concluído");
        pedido6.setSolicitante("Guilhermina da Silva");
        pedido6.setPrazo("03/04/2017");
        pedido6.setTelSolicitante("(21) 9876-9832");
        pedido6.setEmailSolicitante("juliano@contato.com");
        pedido6.setEndSolicitante("Rua Barbosa de Freitas, 98 casa 05");
        pedido6.setQtdGrao(new BigDecimal(5500));
        pedido6.setQtdPigmento(new BigDecimal(3000));
        
        Pedido pedido7 = new Pedido();
        pedido7.setCodigo("EX003");
        pedido7.setTipo("Corte e Solda");
        pedido7.setDataPedido("09/02/2017");
        pedido7.setStatus("Em atraso");
        pedido7.setSolicitante("Juliano da Silva");
        pedido7.setPrazo("03/04/2017");
        pedido7.setTelSolicitante("(21) 9876-9832");
        pedido7.setEmailSolicitante("juliano@contato.com");
        pedido7.setEndSolicitante("Rua Barbosa de Freitas, 98 casa 05");
        pedido7.setQtdGrao(new BigDecimal(1700));
        pedido7.setQtdPigmento(new BigDecimal(2700));
        
        Pedido pedido8 = new Pedido();
        pedido8.setCodigo("EX008");
        pedido8.setTipo("Corte e Solda");
        pedido8.setDataPedido("09/02/2017");
        pedido8.setStatus("Em atraso");
        pedido8.setSolicitante("Adriano da Silva");
        pedido8.setPrazo("03/04/2017");
        pedido8.setTelSolicitante("(21) 9876-9832");
        pedido8.setEmailSolicitante("juliano@contato.com");
        pedido8.setEndSolicitante("Rua Barbosa de Freitas, 98 casa 05");
        pedido8.setQtdGrao(new BigDecimal(1080));
        pedido8.setQtdPigmento(new BigDecimal(2700));
        
        Pedido pedido9 = new Pedido();
        pedido9.setCodigo("EX009");
        pedido9.setTipo("Corte e Solda");
        pedido9.setDataPedido("09/02/2017");
        pedido9.setStatus("Em atraso");
        pedido9.setSolicitante("José da Silva");
        pedido9.setPrazo("03/04/2017");
        pedido9.setTelSolicitante("(21) 9876-9832");
        pedido9.setEmailSolicitante("juliano@contato.com");
        pedido9.setEndSolicitante("Rua Barbosa de Freitas, 98 casa 05");
        pedido9.setQtdGrao(new BigDecimal(300));
        pedido9.setQtdPigmento(new BigDecimal(2400));
        
        Pedido pedido10 = new Pedido();
        pedido10.setCodigo("EX0010");
        pedido10.setTipo("Corte e Solda");
        pedido10.setDataPedido("09/02/2017");
        pedido10.setStatus("Em atraso");
        pedido10.setSolicitante("Fernando da Silva");
        pedido10.setPrazo("03/04/2017");
        pedido10.setTelSolicitante("(21) 9876-9832");
        pedido10.setEmailSolicitante("juliano@contato.com");
        pedido10.setEndSolicitante("Rua Barbosa de Freitas, 98 casa 05");
        pedido10.setQtdGrao(new BigDecimal(1800));
        pedido10.setQtdPigmento(new BigDecimal(2300));
        
        Pedido pedido11 = new Pedido();
        pedido11.setCodigo("EX0011");
        pedido11.setTipo("Corte e Solda");
        pedido11.setDataPedido("09/02/2017");
        pedido11.setStatus("Em atraso");
        pedido11.setSolicitante("Gustavo da Silva");
        pedido11.setPrazo("03/04/2017");
        pedido11.setTelSolicitante("(21) 9876-9832");
        pedido11.setEmailSolicitante("juliano@contato.com");
        pedido11.setEndSolicitante("Rua Barbosa de Freitas, 98 casa 05");
        pedido11.setQtdGrao(new BigDecimal(1500));
        pedido11.setQtdPigmento(new BigDecimal(2300));
        
        Pedido pedido12 = new Pedido();
        pedido12.setCodigo("EX0012");
        pedido12.setTipo("Corte e Solda");
        pedido12.setDataPedido("09/02/2017");
        pedido12.setStatus("Concluído");
        pedido12.setSolicitante("Lucas da Silva");
        pedido12.setPrazo("03/04/2017");
        pedido12.setTelSolicitante("(21) 9876-9832");
        pedido12.setEmailSolicitante("juliano@contato.com");
        pedido12.setEndSolicitante("Rua Barbosa de Freitas, 98 casa 05");
        pedido12.setQtdGrao(new BigDecimal(1200));
        pedido12.setQtdPigmento(new BigDecimal(2200));
        
        Pedido pedido13 = new Pedido();
        pedido13.setCodigo("EX0013");
        pedido13.setTipo("Corte e Solda");
        pedido13.setDataPedido("09/02/2017");
        pedido13.setStatus("Concluído");
        pedido13.setSolicitante("Ednéia da Silva");
        pedido13.setPrazo("03/04/2017");
        pedido13.setTelSolicitante("(21) 9876-9832");
        pedido13.setEmailSolicitante("juliano@contato.com");
        pedido13.setEndSolicitante("Rua Barbosa de Freitas, 98 casa 05");
        pedido13.setQtdGrao(new BigDecimal(900));
        pedido13.setQtdPigmento(new BigDecimal(700));
        
        Pedido pedido14 = new Pedido();
        pedido14.setCodigo("EX0014");
        pedido14.setTipo("Corte e Solda");
        pedido14.setDataPedido("09/02/2017");
        pedido14.setStatus("Concluído");
        pedido14.setSolicitante("Giselle da Silva");
        pedido14.setPrazo("03/04/2017");
        pedido14.setTelSolicitante("(21) 9876-9832");
        pedido14.setEmailSolicitante("juliano@contato.com");
        pedido14.setEndSolicitante("Rua Barbosa de Freitas, 98 casa 05");
        pedido14.setQtdGrao(new BigDecimal(6500));
        pedido14.setQtdPigmento(new BigDecimal(2300));
        
        Pedido pedido15 = new Pedido();
        pedido15.setCodigo("EX0015");
        pedido15.setTipo("Corte e Solda");
        pedido15.setDataPedido("09/02/2017");
        pedido15.setStatus("Em andamento");
        pedido15.setSolicitante("Rômulo da Silva");
        pedido15.setPrazo("03/04/2017");
        pedido15.setTelSolicitante("(21) 9876-9832");
        pedido15.setEmailSolicitante("juliano@contato.com");
        pedido15.setEndSolicitante("Rua Barbosa de Freitas, 98 casa 05");
        pedido15.setQtdGrao(new BigDecimal(1800));
        pedido15.setQtdPigmento(new BigDecimal(9800));
        
        Pedido pedido16 = new Pedido();
        pedido16.setCodigo("EX0016");
        pedido16.setTipo("Corte e Solda");
        pedido16.setDataPedido("09/02/2017");
        pedido16.setStatus("Em andamento");
        pedido16.setSolicitante("Júlia da Silva");
        pedido16.setPrazo("03/04/2017");
        pedido16.setTelSolicitante("(21) 9876-9832");
        pedido16.setEmailSolicitante("juliano@contato.com");
        pedido16.setEndSolicitante("Rua Barbosa de Freitas, 98 casa 05");
        pedido16.setQtdGrao(new BigDecimal(18900));
        pedido16.setQtdPigmento(new BigDecimal(6200));
        
        Pedido pedido17 = new Pedido();
        pedido17.setCodigo("EX0017");
        pedido17.setTipo("Corte e Solda");
        pedido17.setDataPedido("09/02/2017");
        pedido17.setStatus("Em andamento");
        pedido17.setSolicitante("Josefa da Silva");
        pedido17.setPrazo("03/04/2017");
        pedido17.setTelSolicitante("(21) 9876-9832");
        pedido17.setEmailSolicitante("juliano@contato.com");
        pedido17.setEndSolicitante("Rua Barbosa de Freitas, 98 casa 05");
        pedido17.setQtdGrao(new BigDecimal(1780));
        pedido17.setQtdPigmento(new BigDecimal(2900));
        
        Pedido pedido18 = new Pedido();
        pedido18.setCodigo("EX0018");
        pedido18.setTipo("Corte e Solda");
        pedido18.setDataPedido("09/02/2017");
        pedido18.setStatus("Em andamento");
        pedido18.setSolicitante("Henrique da Silva");
        pedido18.setPrazo("03/04/2017");
        pedido18.setTelSolicitante("(21) 9876-9832");
        pedido18.setEmailSolicitante("juliano@contato.com");
        pedido18.setEndSolicitante("Rua Barbosa de Freitas, 98 casa 05");
        pedido18.setQtdGrao(new BigDecimal(1100));
        pedido18.setQtdPigmento(new BigDecimal(2600));
        
        Pedido pedido19 = new Pedido();
        pedido19.setCodigo("EX0019");
        pedido19.setQtdGrao(new BigDecimal(100));
        pedido19.setQtdPigmento(new BigDecimal(200));
        pedido19.setTipo("Corte e Solda");
        pedido19.setDataPedido("09/02/2017");
        pedido19.setStatus("Em andamento");
        pedido19.setSolicitante("Hélio da Silva");
        pedido19.setPrazo("03/04/2017");
        pedido19.setTelSolicitante("(21) 9876-9832");
        pedido19.setEmailSolicitante("juliano@contato.com");
        pedido19.setEndSolicitante("Rua Barbosa de Freitas, 98 casa 05");
        pedido19.setQtdGrao(new BigDecimal(1500));
        pedido19.setQtdPigmento(new BigDecimal(2500));
        
        Pedido pedido20 = new Pedido();
        pedido20.setCodigo("EX0020");
        pedido20.setTipo("Corte e Solda");
        pedido20.setDataPedido("09/02/2017");
        pedido20.setStatus("Em andamento");
        pedido20.setSolicitante("Francisco da Silva");
        pedido20.setPrazo("03/04/2017");
        pedido20.setTelSolicitante("(21) 9876-9832");
        pedido20.setEmailSolicitante("juliano@contato.com");
        pedido20.setEndSolicitante("Rua Barbosa de Freitas, 98 casa 05");
        pedido20.setQtdGrao(new BigDecimal(1300));
        pedido20.setQtdPigmento(new BigDecimal(2300));
        
        Pedido pedido21 = new Pedido();
        pedido21.setCodigo("EX0021");
        pedido21.setTipo("Corte e Solda");
        pedido21.setDataPedido("09/02/2017");
        pedido21.setStatus("Cancelado");
        pedido21.setSolicitante("Genilson da Silva");
        pedido21.setPrazo("03/04/2017");
        pedido21.setTelSolicitante("(21) 9876-9832");
        pedido21.setEmailSolicitante("juliano@contato.com");
        pedido21.setEndSolicitante("Rua Barbosa de Freitas, 98 casa 05");
        pedido21.setQtdGrao(new BigDecimal(100));
        pedido21.setQtdPigmento(new BigDecimal(200));
        
        Pedido pedido22 = new Pedido();
        pedido22.setCodigo("EX0022");
        pedido22.setTipo("Corte e Solda");
        pedido22.setDataPedido("09/02/2017");
        pedido22.setStatus("Cancelado");
        pedido22.setSolicitante("Cinthia da Silva");
        pedido22.setPrazo("03/04/2017");
        pedido22.setTelSolicitante("(21) 9876-9832");
        pedido22.setEmailSolicitante("juliano@contato.com");
        pedido22.setEndSolicitante("Rua Barbosa de Freitas, 98 casa 05");
        pedido22.setQtdGrao(new BigDecimal(700));
        pedido22.setQtdPigmento(new BigDecimal(800));
        
        Pedido pedido23 = new Pedido();
        pedido23.setCodigo("EX0023");
        pedido23.setTipo("Corte e Solda");
        pedido23.setDataPedido("09/02/2017");
        pedido23.setStatus("Cancelado");
        pedido23.setSolicitante("Daniel da Silva");
        pedido23.setPrazo("03/04/2017");
        pedido23.setTelSolicitante("(21) 9876-9832");
        pedido23.setEmailSolicitante("juliano@contato.com");
        pedido23.setEndSolicitante("Rua Barbosa de Freitas, 98 casa 05");
        pedido23.setQtdGrao(new BigDecimal(7700));
        pedido23.setQtdPigmento(new BigDecimal(700));
        
        Pedido pedido24 = new Pedido();
        pedido24.setCodigo("EX0024");
        pedido24.setTipo("Corte e Solda");
        pedido24.setDataPedido("09/02/2017");
        pedido24.setStatus("Cancelado");
        pedido24.setSolicitante("Joao da Silva");
        pedido24.setPrazo("03/04/2017");
        pedido24.setTelSolicitante("(21) 9876-9832");
        pedido24.setEmailSolicitante("juliano@contato.com");
        pedido24.setEndSolicitante("Rua Barbosa de Freitas, 98 casa 05");
        pedido24.setQtdGrao(new BigDecimal(1800));
        pedido24.setQtdPigmento(new BigDecimal(2700));
        
        Pedido pedido25 = new Pedido();
        pedido25.setCodigo("EX0025");
        pedido25.setTipo("Corte e Solda");
        pedido25.setDataPedido("09/02/2017");
        pedido25.setStatus("Cancelado");
        pedido25.setSolicitante("Rosana da Silva");
        pedido25.setPrazo("03/04/2017");
        pedido25.setTelSolicitante("(21) 9876-9832");
        pedido25.setEmailSolicitante("juliano@contato.com");
        pedido25.setEndSolicitante("Rua Barbosa de Freitas, 98 casa 05");
        pedido25.setQtdGrao(new BigDecimal(1500));
        pedido25.setQtdPigmento(new BigDecimal(2500));
        
        pedidos.add(pedido1);
        pedidos.add(pedido2);
        pedidos.add(pedido3);
    pedidos.add(pedido4);
    pedidos.add(pedido5);
    pedidos.add(pedido6);
    pedidos.add(pedido7);
    pedidos.add(pedido8);
    pedidos.add(pedido9);
    pedidos.add(pedido10);
    pedidos.add(pedido11);
    pedidos.add(pedido12);
    pedidos.add(pedido13);
    pedidos.add(pedido14);
    pedidos.add(pedido15);
    pedidos.add(pedido16);
    pedidos.add(pedido17);
    pedidos.add(pedido18);
    pedidos.add(pedido19);
    pedidos.add(pedido20);
    pedidos.add(pedido21);
    pedidos.add(pedido22);
    pedidos.add(pedido23);
    pedidos.add(pedido24);
    pedidos.add(pedido25);
    
    
     }
    
}

    
    