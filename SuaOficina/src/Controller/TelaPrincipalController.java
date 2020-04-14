package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import suaoficina.Automovel;
import suaoficina.Cliente;
import suaoficina.Orcamento;
import suaoficina.Peca;
import suaoficina.Servico;
import suaoficina.TelaPrincipal;

public class TelaPrincipalController implements Initializable, InterfaceController {

    @FXML
    private Button btnClientes;
    @FXML
    private Button btnVeiculos;
    @FXML
    private Button btnServico;
    @FXML
    private Button btnProdutos;
    @FXML
    private Button btnOrcamento;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnClientes.setOnMouseClicked((MouseEvent e)->{
           abrirClientes();
           fechar();
        });
        
        btnClientes.setOnKeyPressed((KeyEvent e)->{
            if(e.getCode() == KeyCode.ENTER)
                abrirClientes();
                fechar();
        });
        
         btnVeiculos.setOnMouseClicked((MouseEvent e)->{
            abrirVeiculos();
            fechar();
        });
         
         btnVeiculos.setOnKeyPressed((KeyEvent e)->{
            if(e.getCode() == KeyCode.ENTER)
                abrirVeiculos();
                fechar();
        });
         
          btnServico.setOnMouseClicked((MouseEvent e)->{
            abrirServicos();
            fechar();
        });
          
          btnServico.setOnKeyPressed((KeyEvent e)->{
            if(e.getCode() == KeyCode.ENTER)
                abrirServicos();
                fechar();
        });
        
           btnProdutos.setOnMouseClicked((MouseEvent e)->{
            abrirpeças();
            fechar();
        });
           
           btnProdutos.setOnKeyPressed((KeyEvent e)->{
            if(e.getCode() == KeyCode.ENTER)
                abrirpeças();
                fechar();
        });
           
        btnOrcamento.setOnMouseClicked((MouseEvent e)->{
            abrirOrcamento();
            fechar();
        });
           
        btnOrcamento.setOnKeyPressed((KeyEvent e)->{
            if(e.getCode() == KeyCode.ENTER)
                abrirOrcamento();
                fechar();
        });
    } 
    
    public void abrirClientes(){
         Cliente c = new Cliente();
            try {
                c.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void abrirVeiculos(){
        Automovel a = new Automovel();
            try {
                a.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void abrirServicos(){
        Servico s = new Servico();
            try {
                s.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void abrirpeças(){
        Peca p = new Peca();
            try {
                p.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
     public void abrirOrcamento(){
        Orcamento o = new Orcamento();
            try {
                o.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
    
    }
    
    @Override
    public void fechar(){
        TelaPrincipal.getStage().close();
    }
}
