package javafx.view.relatorios;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.model.dao.GraoDAO;
import javafx.model.database.Database;
import javafx.model.database.DatabaseMySQL;
import javafx.model.domain.Grao;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * FXML Controller class
 *
 * @author Rodolfo
 */
public class RelatorioGraoEstoqueController implements Initializable {

    
    @FXML
    private TableView<Grao> tableGraoEstoque;
    
    @FXML
    private TableView<Grao> tableBobinas;
    
    @FXML
    private TableColumn<Grao, String> columnTipoGraoEstoque, columnQtdGraoEstoque;
    
    private List<Grao> listaGraos;
    
    private List<Grao> listaBobinas;
     
    private ObservableList<Grao> observableListGraos;
     
    
    @FXML
    private BarChart barChart;
    
    @FXML
    private PieChart pieChartGrao;
    
    @FXML
    private Label labelGrafico;
    
    @FXML
    private CategoryAxis categoriaAxis;
     
    @FXML
    private NumberAxis numberAxis; 
     
    @FXML
    private VBox vBoxRelatorioGrao; 
    
    private final Database databaseMySql = new DatabaseMySQL();
    private final Connection connection = databaseMySql.conectar();
    private GraoDAO graoDao = new GraoDAO();
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
     graoDao.setConnection(connection);
        carregarTableViewGrao();
     
        if(pieChartGrao != null){
        carregarPieChart();
        }
    }    
    
    public void carregarTableViewGrao(){
        
        
        columnTipoGraoEstoque.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        columnQtdGraoEstoque.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        
       // listaGraos = new ArrayList<Grao>();
       // mockGrao(listaGraos);
        
        listaGraos = graoDao.listar();
       
        observableListGraos = FXCollections.observableArrayList(listaGraos);
       
        tableGraoEstoque.getSelectionModel().selectedItemProperty().addListener(
        (observable,oldvalue,newValue) -> selecionarItemTableViewGraos(newValue));
        tableGraoEstoque.setItems(observableListGraos);
       
    }
    
    public void selecionarItemTableViewGraos(Grao grao){
    
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
    
    public void mockGrao(List<Grao> graos) {
        Grao grao1 = new Grao();
        grao1.setTipo("Granulado");
        grao1.setQuantidade(new BigDecimal(1000));
        
        Grao grao2 = new Grao();
        grao2.setTipo("Fino");
        grao2.setQuantidade(new BigDecimal(1000));
        
        Grao grao3 = new Grao();
        grao3.setTipo("Tipo A");
        grao3.setQuantidade(new BigDecimal(1000));
        
        Grao grao4 = new Grao();
        grao4.setTipo("Tipo B");
        grao4.setQuantidade(new BigDecimal(1000));
        
        Grao grao5 = new Grao();
        grao5.setTipo("Tipo C");
        grao5.setQuantidade(new BigDecimal(1000));
        
        graos.add(grao1);
        graos.add(grao2);
        graos.add(grao3);
        graos.add(grao4);
        graos.add(grao5);
        
    }
    
    
    public void carregarPieChart(){
       
        ObservableList<PieChart.Data> datas = 
        FXCollections.observableArrayList(
                new PieChart.Data("Granulado",800),
                new PieChart.Data("Fino",1000),
                new PieChart.Data("Tipo A",500),
                new PieChart.Data("Tipo B",600),
                new PieChart.Data("Tipo C",900));
       PieChart grafico = new PieChart(datas);
       pieChartGrao.setData(datas);
       pieChartGrao.setLegendSide(Side.LEFT);
       
       labelGrafico = new Label () ;
       labelGrafico.setFont(Font.font("SansSerif", FontWeight.BOLD, 15));
       
       pieChartGrao.getData()
                   .stream() 
                   .forEach(data -> {
                       data.getNode().addEventHandler(MouseEvent.ANY, e -> {
                           labelGrafico.setText("   Tipo de grão: " + data.getName() + "  Quantidade: " + data.getPieValue());
                       });
                   });
       
       labelGrafico.setLayoutX(557.0);  
       labelGrafico.setLayoutX(321.0);
       vBoxRelatorioGrao.getChildren().add(labelGrafico);  
               
    }
    
    
    
//    public void carregarBarChart () {
//        
//         categoriaAxis = new CategoryAxis();
//         numberAxis = new NumberAxis(0,1000,100);
//         
//         categoriaAxis.setLabel("Grãos");       
//         numberAxis.setLabel("Quantidade");
//        
//           barChart = new BarChart(categoriaAxis,numberAxis);
//        barChart.setTitle("Resumo de grãos");
//        
//        
//        XYChart.Series serie1 = new XYChart.Series();
//        serie1.setName("2003");       
//        serie1.getData().add(new XYChart.Data("Granulado", 1000));
//        serie1.getData().add(new XYChart.Data("Tipo A", 70));
//        serie1.getData().add(new XYChart.Data("Tipo B", 30));
//        serie1.getData().add(new XYChart.Data("Tipo C", 28));
//        serie1.getData().add(new XYChart.Data("Fino", 65));
//       barChart.getData().addAll(serie1);
//        
//      // vBoxRelatorioGrao.getChildren().add(barChart);
//        
//       // https://www.youtube.com/watch?v=ugo4yM2agnY
//       
//    }
    
    
     public void mockBobina(List<Grao> graos) {
        Grao grao1 = new Grao();
        grao1.setTipo("1.50x70x0.5");
        grao1.setQuantidade(new BigDecimal(700));
        
        Grao grao2 = new Grao();
        grao2.setTipo("1.30x50x0.2");
        grao2.setQuantidade(new BigDecimal(1000));
        
        Grao grao3 = new Grao();
        grao3.setTipo("1.30x50x0.3");
        grao3.setQuantidade(new BigDecimal(1000));
        
        Grao grao4 = new Grao();
        grao4.setTipo("1.30x50x0.4");
        grao4.setQuantidade(new BigDecimal(1000));
        
        Grao grao5 = new Grao();
        grao5.setTipo("1.30x50x0.5");
        grao5.setQuantidade(new BigDecimal(1000));
        
        Grao grao6 = new Grao();
        grao6.setTipo("1.30x50x0.6");
        grao6.setQuantidade(new BigDecimal(1000));
        
        Grao grao7 = new Grao();
        grao7.setTipo("1.30x50x0.7");
        grao7.setQuantidade(new BigDecimal(1000));
        
        Grao grao8 = new Grao();
        grao8.setTipo("1.30x50x0.8");
        grao8.setQuantidade(new BigDecimal(1000));
        
        Grao grao9 = new Grao();
        grao9.setTipo("1.30x50x0.9");
        grao9.setQuantidade(new BigDecimal(1000));
        
        Grao grao10 = new Grao();
        grao10.setTipo("1.30x50x0.10");
        grao10.setQuantidade(new BigDecimal(1000));
        
        Grao grao11 = new Grao();
        grao11.setTipo("1.30x50x0.11");
        grao11.setQuantidade(new BigDecimal(1000));
        
        Grao grao12 = new Grao();
        grao12.setTipo("1.30x50x0.12");
        grao12.setQuantidade(new BigDecimal(1000));
        
        Grao grao13 = new Grao();
        grao13.setTipo("1.30x50x0.13");
        grao13.setQuantidade(new BigDecimal(1000));
        
        
        Grao grao14 = new Grao();
        grao14.setTipo("1.30x50x0.14");
        grao14.setQuantidade(new BigDecimal(1000));
        
        Grao grao15 = new Grao();
        grao15.setTipo("1.30x50x0.15");
        grao15.setQuantidade(new BigDecimal(1000));
        
        Grao grao16 = new Grao();
        grao16.setTipo("1.30x50x0.16");
        grao16.setQuantidade(new BigDecimal(1000));
        
        Grao grao17 = new Grao();
        grao17.setTipo("1.30x50x0.17");
        grao17.setQuantidade(new BigDecimal(1000));
        
        Grao grao18 = new Grao();
        grao18.setTipo("1.30x50x0.18");
        grao18.setQuantidade(new BigDecimal(1000));
        
        Grao grao19 = new Grao();
        grao19.setTipo("1.30x50x0.19");
        grao19.setQuantidade(new BigDecimal(1000));
        
        Grao grao20 = new Grao();
        grao20.setTipo("1.30x50x0.20");
        grao20.setQuantidade(new BigDecimal(1000));
        
        graos.add(grao1);
        graos.add(grao2);
        graos.add(grao3);
        graos.add(grao4);
        graos.add(grao5);
        graos.add(grao6);
        graos.add(grao7);
        graos.add(grao8);
        graos.add(grao9);
        graos.add(grao10);
        graos.add(grao11);
        graos.add(grao12);
        graos.add(grao13);
        graos.add(grao14);
        graos.add(grao15);
        graos.add(grao16);
        graos.add(grao17);
        graos.add(grao18);
        graos.add(grao19);
        graos.add(grao20);
        
    }
    
    
}
