package Controller;

import Model.Clientes;
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
import suaoficina.CadastroCliente;
import suaoficina.Cliente;


public class CadastroClienteController implements Initializable, InterfaceController {

    @FXML
    private TextField txfNome;
    @FXML
    private TextField txfCpf;
    @FXML
    private TextField txfEndereco;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnVoltar;
    @FXML
    private Button btnSalvar;
   

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        btnVoltar.setOnMouseClicked((MouseEvent e)->{
            abrirClientes();
        });
        
        btnVoltar.setOnKeyPressed((KeyEvent e)->{
            if(e.getCode() == KeyCode.ENTER){
                abrirClientes();
            }
        });
        
        btnSalvar.setOnMouseClicked((MouseEvent e)->{
            try {
                cadastrarCliente();
            } catch (IOException ex) {
                Logger.getLogger(CadastroClienteController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText("Cliente cadastrado!");
            a.show();
        });
        
        btnSalvar.setOnKeyPressed((KeyEvent e)->{
            if(e.getCode() == KeyCode.ENTER){
                try {
                    cadastrarCliente();
                } catch (IOException ex) {
                    Logger.getLogger(CadastroClienteController.class.getName()).log(Level.SEVERE, null, ex);
                }
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText("Cliente cadastrado!");
            a.show();
            }
        });
        
        btnCancelar.setOnMouseClicked((MouseEvent e)->{
            abrirClientes();
        });
        
        btnCancelar.setOnKeyPressed((KeyEvent e)->{
            if(e.getCode() == KeyCode.ENTER){
                abrirClientes();
            }
        });
        
    }  
    
    @Override
    public void fechar(){
        CadastroCliente.getStage().close();
    }
    
    public void abrirClientes(){
        Cliente c = new Cliente();
        fechar();
        try {
            c.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cadastrarCliente() throws IOException{
        
        List<Clientes> cliente = new ArrayList<>();
        
        FileReader arq = new FileReader("Clientes.txt");
        BufferedReader lerArq = new BufferedReader(arq);
        String linha = lerArq.readLine();
        
        do{
            Clientes c = new Clientes();
            c.setNome(linha);
            linha = lerArq.readLine();
            c.setEndereço(linha);
            linha = lerArq.readLine();
            int x = Integer.parseInt(linha);
            c.setCpf(x);
            linha = lerArq.readLine();
                
            cliente.add(c);
                
        }while(linha!=null);
        
        Clientes c2 = new Clientes();
        
        c2.setNome(txfNome.getText());
        c2.setEndereço(txfEndereco.getText());
        int y = Integer.parseInt(txfCpf.getText());
        c2.setCpf(y);
        
        cliente.add(c2);
        
        FileWriter arq1 = new FileWriter("Clientes.txt");
        PrintWriter gravarArq = new PrintWriter(arq1);
        
        int i, n = cliente.size();
        for (i=0; i<n; i++) {
        gravarArq.printf("%s%n", cliente.get(i).getNome());
        gravarArq.printf("%s%n", cliente.get(i).getEndereço());
        gravarArq.printf("%s%n", cliente.get(i).getCpf());}
        gravarArq.close();
    }
}
