/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.AnimationValidationField;

/**
 * FXML Controller class
 *
 * @author Rodolfo
 */
public class FxmlSolicProdEXDialogController implements Initializable {

   
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
    


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    public void fecharPopup() {
         getDialogStage().close();
    }
    
   public void salvarPedidoEX() {
        System.out.println("Entrou no salvar pedido");
        if(validateView()) {
            
        }
    }
    
      private boolean validateView() {
        
        List<Control> controls = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        DropShadow ds = new DropShadow();
        ds.setSpread(0.5);
        ds.setColor(Color.RED);
        
        if (qtdGrao.getText().isEmpty()) {
            System.out.println("qtdGrao está nulo ");
            sb.append("O campo Quantidade de grão é obrigatório. \n");
            controls.add(qtdGrao);
            qtdGrao.setEffect(ds);
        }
        if (qtdPigmento.getText().isEmpty()) {
            System.out.println("qtdPigmento está nulo ");
            sb.append("O campo Quantidade de pigmento é obrigatório. \n");
            controls.add(qtdPigmento);
            qtdPigmento.setEffect(ds);
        }
        
        if (comboBoxStatus.getValue()== null) {
            System.out.println("datePrazo está nulo ");
            sb.append("O campo Status é obrigatório. \n");
            controls.add(comboBoxStatus);
            comboBoxStatus.setEffect(ds);
        }
        if (datePrazo.getValue() == null) {
            System.out.println("datePrazo está nulo ");
            sb.append("O campo Prazo de conclusão é obrigatório. \n");
            controls.add(datePrazo);
            datePrazo.setEffect(ds);
        }
        
        if (nmSolicitante.getText().isEmpty()) {
            System.out.println("nmSolicitante está nulo ");
            sb.append("O campo Solicitante é obrigatório. \n");
            controls.add(nmSolicitante);
            nmSolicitante.setEffect(ds);
        }
        
        if(!sb.toString().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validação");
            alert.setHeaderText("Erros encontrados");
            alert.setContentText(sb.toString());
            alert.initOwner(qtdGrao.getScene().getWindow());
            
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
  

    
}
