/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.view.estoque;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.enumeration.TipoOperacaoEnum;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Control;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.AnimationValidationField;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 *
 * @author rlsantos
 */
public class EditarSaidaEstoqueDialog  implements Initializable  {
   
    @FXML
    private Stage dialogStage;

    @FXML
    private TextField pesoTxt;

    @FXML
    private TextArea observacaoTxt;

    @FXML
    private TextField corTxt;

    @FXML
    private TextField clienteTxt;
    
    @FXML
    private TextField quantidadeTxt;

    @FXML
    private TextField compTxt;

    @FXML
    private Label labelPeso;
    
    @FXML
    private TextField altTxt;

    @FXML
    private TextField largTxt;
    
    @FXML
    private TextField produtoTxt;
    
    private String tipoOperacao;
 
    private final Database databaseMySql = new DatabaseMySQL();
    
    private final Connection connection = databaseMySql.conectar();
    
    private EstoqueDAO estoqueDao = new EstoqueDAO();
    
    private EntregaDAO entregaDao = new EntregaDAO();
    
    private Long idSaco;
    
    private Long idEntrega;
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        estoqueDao.setConnection(connection);
    }    
        
    public void fecharPopup() {
         getDialogStage().close();
    }

     public void salvarEntrega() {
        
        if(validateView()) {
            
            if (TipoOperacaoEnum.CRIACAO.getTipoOperacao().equals(tipoOperacao)) {
                criarSaco(); 
            } else if (TipoOperacaoEnum.ALTERACAO.getTipoOperacao().equals(tipoOperacao)){
                alterarEntrega();
            }
            
        }
    }
    
     private void criarSaco() {
         
         Saco saco = new Saco();
         
         saco.setEspecificacao(getProdutoTxt().getText());
         saco.setCor(getCorTxt().getText());
         saco.setPeso(new BigDecimal(getPesoTxt().getText()));
         saco.setQuantidade(new Long(getQuantidadeTxt().getText()));
         saco.setObservacao(getObservacaoTxt().getText());
         saco.setCliente(null);
         estoqueDao.inserir(saco);
         
         fecharPopup();
         exibirNotificacao("Entrada de estoque.");
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
      
     private void alterarEntrega(){
         
         System.out.print("Entrou em alterarEntrega" );
         
         Entrega entrega = new Entrega();
         entrega.setEspecificacao(getProdutoTxt().getText());
         entrega.setCor(getCorTxt().getText());
         entrega.setPeso(new BigDecimal(getPesoTxt().getText()));
         entrega.setQuantidade(new Long(getQuantidadeTxt().getText()));
         entrega.setObservacao(getObservacaoTxt().getText());
         entrega.setCliente(getClienteTxt().getText());
         entrega.setAlt(new BigDecimal(getAltTxt().getText()));
         entrega.setLarg(new BigDecimal(getLargTxt().getText()));
         entrega.setComp(new BigDecimal(getCompTxt().getText()));
         entrega.setIdEntrega(getIdEntrega());
         
         System.out.print(" Entrou no alterarEntrega com cliente "  + getClienteTxt().getText());
         
         entregaDao.alterar(entrega);
         
         fecharPopup();
         exibirNotificacao("Alteração de saída de estoque.");
         
     }
     
    private boolean validateView() {
        
        List<Control> controls = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        DropShadow ds = new DropShadow();
        ds.setSpread(0.5);
        ds.setColor(Color.RED);
        
        if (getProdutoTxt().getText().isEmpty()) {
            sb.append("O campo produto é obrigatório. \n");
            controls.add(getProdutoTxt());
            getProdutoTxt().setEffect(ds);
        } else {
            getProdutoTxt().setEffect(null);
        }
        
        if (getCorTxt().getText().isEmpty()) {
            sb.append("O campo cor é obrigatório. \n");
            controls.add(getCorTxt());
            getCorTxt().setEffect(ds);
        } else {
            getCorTxt().setEffect(null);
        }
        
        if (getPesoTxt().getText().isEmpty()) {
            sb.append("O campo peso é obrigatório. \n");
            controls.add(getPesoTxt());
            getPesoTxt().setEffect(ds);
        } else {
            getPesoTxt().setEffect(null);
        }
        
        if (getQuantidadeTxt().getText().isEmpty()) {
            sb.append("O campo quantidade é obrigatório. \n");
            controls.add(getQuantidadeTxt());
            getQuantidadeTxt().setEffect(ds);
        } else {
            getQuantidadeTxt().setEffect(null);
        }
        
        if(!sb.toString().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validação");
            alert.setHeaderText("Erros encontrados");
            alert.setContentText(sb.toString());
            alert.initOwner(getProdutoTxt().getScene().getWindow());
            
            alert.setOnHiding( (DialogEvent event) -> { 
                AnimationValidationField.starRotationAnimation(controls);
            });
            alert.show();
        }
        
        return sb.toString().isEmpty();
        
    }
    
    /**
     * @return the dialogStage
     */
    public Stage getDialogStage() {
        return dialogStage;
    }

    /**
     * @param dialogStage the dialogStage to set
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    
     /**
     * @return the tipoOperacao
     */
    public String getTipoOperacao() {
        return tipoOperacao;
    }

    /**
     * @param tipoOperacao the tipoOperacao to set
     */
    public void setTipoOperacao(String tipoOperacao) {
        this.tipoOperacao = tipoOperacao;
    }

    /**
     * @return the pesoTxt
     */
    public TextField getPesoTxt() {
        return pesoTxt;
    }

    /**
     * @param pesoTxt the pesoTxt to set
     */
    public void setPesoTxt(TextField pesoTxt) {
        this.pesoTxt = pesoTxt;
    }

    /**
     * @return the observacaoTxt
     */
    public TextArea getObservacaoTxt() {
        return observacaoTxt;
    }

    /**
     * @param observacaoTxt the observacaoTxt to set
     */
    public void setObservacaoTxt(TextArea observacaoTxt) {
        this.observacaoTxt = observacaoTxt;
    }

    /**
     * @return the corTxt
     */
    public TextField getCorTxt() {
        return corTxt;
    }

    /**
     * @param corTxt the corTxt to set
     */
    public void setCorTxt(TextField corTxt) {
        this.corTxt = corTxt;
    }

    /**
     * @return the quantidadeTxt
     */
    public TextField getQuantidadeTxt() {
        return quantidadeTxt;
    }

    /**
     * @param quantidadeTxt the quantidadeTxt to set
     */
    public void setQuantidadeTxt(TextField quantidadeTxt) {
        this.quantidadeTxt = quantidadeTxt;
    }

    /**
     * @return the produtoTxt
     */
    public TextField getProdutoTxt() {
        return produtoTxt;
    }

    /**
     * @param produtoTxt the produtoTxt to set
     */
    public void setProdutoTxt(TextField produtoTxt) {
        this.produtoTxt = produtoTxt;
    }

    /**
     * @return the idSaco
     */
    public Long getIdSaco() {
        return idSaco;
    }

    /**
     * @param idSaco the idSaco to set
     */
    public void setIdSaco(Long idSaco) {
        this.idSaco = idSaco;
    }
    
    
     /**
     * @return the clienteTxt
     */
    public TextField getClienteTxt() {
        return clienteTxt;
    }

    /**
     * @param clienteTxt the clienteTxt to set
     */
    public void setClienteTxt(TextField clienteTxt) {
        this.clienteTxt = clienteTxt;
    }

    /**
     * @return the idEntrega
     */
    public Long getIdEntrega() {
        return idEntrega;
    }

    /**
     * @param idEntrega the idSaco to set
     */
    public void setIdEntrega(Long idEntrega) {
        this.idEntrega = idEntrega;
    }

    /**
     * @return the compTxt
     */
    public TextField getCompTxt() {
        return compTxt;
    }

    /**
     * @param compTxt the compTxt to set
     */
    public void setCompTxt(TextField compTxt) {
        this.compTxt = compTxt;
    }

    /**
     * @return the altTxt
     */
    public TextField getAltTxt() {
        return altTxt;
    }

    /**
     * @param altTxt the altTxt to set
     */
    public void setAltTxt(TextField altTxt) {
        this.altTxt = altTxt;
    }

    /**
     * @return the largTxt
     */
    public TextField getLargTxt() {
        return largTxt;
    }

    /**
     * @param largTxt the largTxt to set
     */
    public void setLargTxt(TextField largTxt) {
        this.largTxt = largTxt;
    }

    /**
     * @return the labelPeso
     */
    public Label getLabelPeso() {
        return labelPeso;
    }

    /**
     * @param labelPeso the labelPeso to set
     */
    public void setLabelPeso(Label labelPeso) {
        this.labelPeso = labelPeso;
    }
    
    
    
    
}
