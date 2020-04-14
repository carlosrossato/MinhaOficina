package Controller;

import Model.Serviço;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import suaoficina.CadastroServico;
import suaoficina.Servico;

public class CadastroServicoController implements Initializable, InterfaceController {

    @FXML
    private Button btnVoltar;
    @FXML
    private TextField txfNome;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField txfValor;
    @FXML
    private Button btnSalvar;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        btnVoltar.setOnMouseClicked((MouseEvent e)->{
            abrirServicos();
        });
        
        btnVoltar.setOnKeyPressed((KeyEvent e)->{
            if(e.getCode() == KeyCode.ENTER){
                abrirServicos();
            }
        });
        
        btnCancelar.setOnMouseClicked((MouseEvent e)->{
            abrirServicos();
        });
        
        btnCancelar.setOnKeyPressed((KeyEvent e)->{
            if(e.getCode() == KeyCode.ENTER){
                abrirServicos();
            }
        });
        
        btnSalvar.setOnMouseClicked((MouseEvent e)->{
            try {
                cadastrarServicos();
            } catch (IOException ex) {
                Logger.getLogger(CadastroServicoController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText("Serviço cadastrado!");
            a.show();
        });
        
        btnSalvar.setOnKeyPressed((KeyEvent e)->{
            if(e.getCode() == KeyCode.ENTER){
                try {
                    cadastrarServicos();
                } catch (IOException ex) {
                    Logger.getLogger(CadastroServicoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText("Serviço cadastrado!");
            a.show();
        });
        
    } 
    
    @Override
    public void fechar(){
        CadastroServico.getStage().close();
    }
    
    public void abrirServicos(){
        Servico s = new Servico();
        fechar();
        try {
            s.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(CadastroServicoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cadastrarServicos() throws IOException{
        
        List<Serviço> servico = new ArrayList<>();
        
        FileReader arq = new FileReader("Serviços.txt");
        BufferedReader lerArq = new BufferedReader(arq);
        
        String linha = lerArq.readLine();
        
        do{
            Serviço s = new Serviço();
            s.setNome(linha);
            linha = lerArq.readLine();
            float x = Float.parseFloat(linha);
            s.setValor(x);
            linha = lerArq.readLine();
                
            servico.add(s);
                
        }while(linha!=null);
        
        Serviço s2 = new Serviço();
        
        s2.setNome(txfNome.getText());
        float y = Float.parseFloat(txfValor.getText());
        s2.setValor(y);
        
        servico.add(s2);
        
        FileWriter arq1 = new FileWriter("Serviços.txt");
        PrintWriter gravarArq = new PrintWriter(arq1);
        
        int i, n = servico.size();
        for (i=0; i<n; i++) {
        gravarArq.printf("%s%n", servico.get(i).getNome());
        gravarArq.printf("%s%n", servico.get(i).getValor());}
        gravarArq.close();
    }
}
