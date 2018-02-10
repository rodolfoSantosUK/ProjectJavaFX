package javafx.view.extrusao;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.controller.FXMLControlePigmentoController;
import javafx.enumeration.TipoOperacaoEnum;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos; 
import javafx.model.dao.PigmentoDAO;
import javafx.model.database.Database;
import javafx.model.database.DatabaseMySQL;
import javafx.model.domain.Pigmento;
import javafx.scene.control.Alert;
import javafx.scene.control.Control;
import javafx.scene.control.DialogEvent;
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
 * FXML Controller class
 *
 * @author Rodolfo
 */
public class ControlePigmentoDialog implements Initializable {

    
    @FXML
    private Stage dialogStage;

    @FXML
    private TextArea observacaoPigmentoTxt;

    @FXML
    private TextField tipoPigmentoTxt;

    @FXML
    private TextField fornecedorTxt;

    @FXML
    private TextField emEstoqueTxt;
    
    private String tipoOperacao;
  
    
    private PigmentoDAO pigmentoDao = new PigmentoDAO();
    
    private Long idPigmento;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
    }    
        
    public void fecharPopup() {
         getDialogStage().close();
    }

     public void salvarPigmento() {
        
        if(validateView()) {
            
            if (TipoOperacaoEnum.CRIACAO.getTipoOperacao().equals(tipoOperacao)) {
                criarPigmento(); 
            } else if (TipoOperacaoEnum.ALTERACAO.getTipoOperacao().equals(tipoOperacao)){
                alterarPigmento();
            }
            
        }
    }
    
     private void criarPigmento() {
         
         Pigmento pigmento = new Pigmento();
         
         pigmento.setFornecedor(getFornecedorTxt().getText());
         pigmento.setTipoPigmento(getTipoPigmentoTxt().getText());
         pigmento.setQuantidade(new BigDecimal(getEmEstoqueTxt().getText()));
         pigmento.setObservacao(getObservacaoPigmentoTxt().getText());
         
            pigmentoDao.setConnection(null);
            pigmentoDao.inserir(pigmento); 
        
         
         
         fecharPopup();
         exibirNotificacao("Criação de pigmento.");
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
      
     private void alterarPigmento(){
         
         Pigmento pigmento = new Pigmento();
         
         pigmento.setFornecedor(getFornecedorTxt().getText());
         pigmento.setTipoPigmento(getTipoPigmentoTxt().getText());
         pigmento.setQuantidade(new BigDecimal(getEmEstoqueTxt().getText()));
         pigmento.setObservacao(getObservacaoPigmentoTxt().getText());
         pigmento.setIdPigmento(idPigmento);
         
          pigmentoDao.setConnection(null);
          pigmentoDao.alterar(pigmento);
         
          fecharPopup();
          exibirNotificacao("Alteração de pigmento.");
         
     }
     
    private boolean validateView() {
        
        List<Control> controls = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        DropShadow ds = new DropShadow();
        ds.setSpread(0.5);
        ds.setColor(Color.RED);
        
        if (getFornecedorTxt().getText().isEmpty()) {
            sb.append("O campo fornecedor é obrigatório. \n");
            controls.add(getFornecedorTxt());
            getFornecedorTxt().setEffect(ds);
        } else {
            getFornecedorTxt().setEffect(null);
        }
        
        if (getTipoPigmentoTxt().getText().isEmpty()) {
            sb.append("O campo tipo de pigmento é obrigatório. \n");
            controls.add(getTipoPigmentoTxt());
            getTipoPigmentoTxt().setEffect(ds);
        } else {
            getTipoPigmentoTxt().setEffect(null);
        }
        
        if (getEmEstoqueTxt().getText().isEmpty()) {
            sb.append("O campo estoque é obrigatório. \n");
            controls.add(getEmEstoqueTxt());
            getEmEstoqueTxt().setEffect(ds);
        } else {
            getEmEstoqueTxt().setEffect(null);
        }
        
        if(!sb.toString().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validação");
            alert.setHeaderText("Erros encontrados");
            alert.setContentText(sb.toString());
            alert.initOwner(getFornecedorTxt().getScene().getWindow());
            
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
     * @return the observacaoPigmentoTxt
     */
    public TextArea getObservacaoPigmentoTxt() {
        return observacaoPigmentoTxt;
    }

    /**
     * @param observacaoPigmentoTxt the observacaoPigmentoTxt to set
     */
    public void setObservacaoPigmentoTxt(TextArea observacaoPigmentoTxt) {
        this.observacaoPigmentoTxt = observacaoPigmentoTxt;
    }

    /**
     * @return the tipoPigmentoTxt
     */
    public TextField getTipoPigmentoTxt() {
        return tipoPigmentoTxt;
    }

    /**
     * @param tipoPigmentoTxt the tipoPigmentoTxt to set
     */
    public void setTipoPigmentoTxt(TextField tipoPigmentoTxt) {
        this.tipoPigmentoTxt = tipoPigmentoTxt;
    }

    /**
     * @return the fornecedorTxt
     */
    public TextField getFornecedorTxt() {
        return fornecedorTxt;
    }

    /**
     * @param fornecedorTxt the fornecedorTxt to set
     */
    public void setFornecedorTxt(TextField fornecedorTxt) {
        this.fornecedorTxt = fornecedorTxt;
    }

    /**
     * @return the emEstoqueTxt
     */
    public TextField getEmEstoqueTxt() {
        return emEstoqueTxt;
    }

    /**
     * @param emEstoqueTxt the emEstoqueTxt to set
     */
    public void setEmEstoqueTxt(TextField emEstoqueTxt) {
        this.emEstoqueTxt = emEstoqueTxt;
    }

    /**
     * @return the idPigmento
     */
    public Long getIdPigmento() {
        return idPigmento;
    }

    /**
     * @param idPigmento the idPigmento to set
     */
    public void setIdPigmento(Long idPigmento) {
        this.idPigmento = idPigmento;
    }
    
     
}