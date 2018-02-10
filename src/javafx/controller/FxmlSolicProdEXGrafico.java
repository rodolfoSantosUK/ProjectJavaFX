/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



/**
 *
 * @author Giselle
 */
public class FxmlSolicProdEXGrafico implements Initializable {
    
    
    @FXML
    private PieChart pieChart;
    
    @FXML
    private Stage dialogStage;
    
    @FXML
    private AnchorPane anchorPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    carregarPieChart();
        
    
    }
    
    public void carregarPieChart(){
       
        ObservableList<PieChart.Data> datas = 
        FXCollections.observableArrayList(
                new PieChart.Data("Concluídos",8),
                new PieChart.Data("Em andamento",6),
                new PieChart.Data("Cancelados",5),
                new PieChart.Data("Em atraso",6));
       PieChart grafico = new PieChart(datas);
      pieChart.getData().addAll(datas);
      
       final Label caption = new Label("");
        caption.setTextFill(Color.BLACK);
        caption.setStyle("-fx-font: 14 arial;");
 
        for (final PieChart.Data data : pieChart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    caption.setTranslateX(e.getSceneX());
                    caption.setTranslateY(e.getSceneY());
 
                    caption.setText(String.valueOf(data.getPieValue()));
                }
            });
        }
        
        anchorPane.getChildren().addAll(caption);
    }

public void onClickPieChart() {
   
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
