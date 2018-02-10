package javafx.controller;

import java.io.IOException; 
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader; 
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Rodolfo
 */
public class FxmlInicioController {

    @FXML
    private Button btnMenuBar;

    @FXML
    private Button openFrm2;

    @FXML
    private Button openFrm3;

    @FXML
    private Button acesso1;

    @FXML
    private Button acesso3;

    @FXML
    private Button acesso2;

    @FXML
    private Button acesso4;
    
    @FXML
    private VBox vBoxCentral;

    public void setDataPane(Node node) {
        // update VBox with new form(FXML) depends on which button is clicked
        vBoxCentral.getChildren().setAll(node);
    }

    public VBox fadeAnimate(String url) throws IOException {
        VBox v = (VBox) FXMLLoader.load(getClass().getResource(url));
        FadeTransition ft = new FadeTransition(Duration.millis(100));
        ft.setNode(v);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
        return v;
    }

    public void abrirAdministrativo(ActionEvent event) throws IOException {
        setDataPane(fadeAnimate("/javafx/view/administrativo.fxml"));
    }
    
    public void abrirExtrusao(ActionEvent event) throws IOException {
        setDataPane(fadeAnimate("/javafx/view/extrusao.fxml"));
    }
    
    public void abrirCorteESolda(ActionEvent event) throws IOException {
        setDataPane(fadeAnimate("/javafx/view/corteSolda.fxml"));
    }
    
    public void abrirEstoque(ActionEvent event) throws IOException {
        setDataPane(fadeAnimate("/javafx/view/estoque.fxml"));
    }
    
    public void loadSolicEX(ActionEvent event) throws IOException {
        setDataPane(fadeAnimate("/javafx/view/fxmlSolicProdEX.fxml"));
    }

    public void loadPane2(ActionEvent event) throws IOException {
        setDataPane(fadeAnimate("/javafx/view/fxmlSolicProdEX.fxml"));
    }

    public void loadPane3(ActionEvent event) throws IOException {
        setDataPane(fadeAnimate("/javafx/view/fxmlSolicProdEX.fxml"));
    }  

    public void exibirRelatorioGraoEstoque() throws IOException {
        setDataPane(fadeAnimate("/javafx/view/relatorios/relatorioGraoEstoque.fxml"));
    }
    
    public void exibirRelatorioBobina() throws IOException {
        setDataPane(fadeAnimate("/javafx/view/relatorios/relatorioBobinas.fxml"));
    }
    
    public void exibirRelatorioPerdaExtrusao() throws IOException {
        setDataPane(fadeAnimate("/javafx/view/relatorios/relatorioPerda.fxml"));
    }
    
    public void exibirRelatorioPedidoConcluido() throws IOException {
        setDataPane(fadeAnimate("/javafx/view/relatorios/relatorioPedidoConcluido.fxml"));
    }
    
    //Extrusão
    
    public void abrirEntradaPedidoExtrusao() throws IOException {
        setDataPane(fadeAnimate("/javafx/view/extrusao/manterPedidosExtrusao.fxml"));
    }
    
    public void abrirControleGraoExtrusao() throws IOException {
        setDataPane(fadeAnimate("/javafx/view/extrusao/manterControleGrao.fxml"));
    }
    
    public void abrirControlePigmento() throws IOException {
        setDataPane(fadeAnimate("/javafx/view/extrusao/manterControlePigmento.fxml"));
    }
     
    public void abrirBobinasTurno() throws IOException {
        setDataPane(fadeAnimate("/javafx/view/extrusao/manterBobinasTurno.fxml"));
    } 
     
    public void abrirPedidosConcluidos() throws IOException {
        setDataPane(fadeAnimate("/javafx/view/extrusao/manterPedidosConcluidos.fxml"));
    } 
   
    public void abrirEncaminhamentoCS() throws IOException {
        setDataPane(fadeAnimate("/javafx/view/extrusao/manterEncaminhamentoCS.fxml"));
    }
    
    
    //Corte e solda
    
    public void abrirEntradaPedidoCorteSolda() throws IOException {
        setDataPane(fadeAnimate("/javafx/view/corteSolda/entradaPedidoCorteSolda.fxml"));
    }    
    
    public void abrirControleBobinas() throws IOException {
        setDataPane(fadeAnimate("/javafx/view/corteSolda/controleBobinas.fxml"));
    }
        
    public void abrirControleSacosCS() throws IOException {
        setDataPane(fadeAnimate("/javafx/view/corteSolda/controleSacosProdCS.fxml"));
    }
    
    public void abrirPedidosCS() throws IOException {
        setDataPane(fadeAnimate("/javafx/view/corteSolda/controlePedidosCS.fxml"));
    }
    
    
    //Estoque
    
    public void abrirVisaoGeral() throws IOException {
        setDataPane(fadeAnimate("/javafx/view/corteSolda/controlePedidosCS.fxml"));
    }
    
    public void abrirEntradaEstoque() throws IOException {
        setDataPane(fadeAnimate("/javafx/view/estoque/estoqueEntrada.fxml"));
    }
    
    public void abrirSaidaEstoque() throws IOException {
        setDataPane(fadeAnimate("/javafx/view/estoque/estoqueSaida.fxml"));
    }
    
}
