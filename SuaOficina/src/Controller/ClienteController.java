package Controller;

import Model.Clientes;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import suaoficina.CadastroCliente;
import suaoficina.Cliente;
import suaoficina.TelaPrincipal;

public class ClienteController implements Initializable, InterfaceController{

    @FXML
    private Button btnVoltar;
    @FXML
    private TableView<Clientes> tvClientes;
    @FXML
    private TableColumn<Clientes, String> cNome;
    @FXML
    private TableColumn<Clientes, Integer> cCpf;
    @FXML
    private TableColumn<Clientes, String> cEndereco;
    @FXML
    private TextField txfBusca;
    @FXML
    private Button btnAdd;

    private ObservableList<Clientes> clientes = FXCollections.observableArrayList();

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        btnVoltar.setOnMouseClicked((MouseEvent e)->{
            abrirPrincipal();
        });
        
        btnVoltar.setOnKeyPressed((KeyEvent e)->{
            if(e.getCode() == KeyCode.ENTER){
                abrirPrincipal();
            }
        });
        
        try {
            initTable();
        } catch (IOException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        txfBusca.setOnKeyReleased((KeyEvent e)->{
            tvClientes.setItems(busca());
        });
        
        btnAdd.setOnMouseClicked((MouseEvent e)->{
            abrirCadastroCliente();
        });
        
        btnAdd.setOnKeyPressed((KeyEvent e)->{
            if(e.getCode() == KeyCode.ENTER){
                abrirCadastroCliente();
            }
        });
    }   
    
    @Override
    public void fechar(){
        Cliente.getStage().close();
    }
    
    public void abrirPrincipal(){
        TelaPrincipal tp = new TelaPrincipal();
        fechar();
        try {
            tp.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void abrirCadastroCliente(){
        CadastroCliente cc = new CadastroCliente();
        fechar();
        try {
            cc.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void initTable() throws IOException{
        cNome.setCellValueFactory(new PropertyValueFactory("nome"));
        cEndereco.setCellValueFactory(new PropertyValueFactory("Endereço"));
        cCpf.setCellValueFactory(new PropertyValueFactory("cpf"));
        
        tvClientes.setItems(atualizarTabela());
    }
    
    public ObservableList<Clientes> atualizarTabela() throws FileNotFoundException, IOException{
        
        ArrayList<Clientes> c = new ArrayList<>();
        
        FileReader arq = new FileReader("Clientes.txt");
        BufferedReader lerArq = new BufferedReader(arq);
        String linha = lerArq.readLine();
        
        do{
            Clientes c1 = new Clientes();
            c1.setNome(linha);
            linha = lerArq.readLine();
            c1.setEndereço(linha);
            linha = lerArq.readLine();
            int x = Integer.parseInt(linha);
            c1.setCpf(x);
            linha = lerArq.readLine();
                
            c.add(c1);
                
            }while(linha!=null); 
        clientes = FXCollections.observableArrayList(c);
        return clientes;
    }
    
    public ObservableList<Clientes> busca(){
        ObservableList<Clientes> clientePesquisa = FXCollections.observableArrayList();
        for(int i = 0;i<clientes.size();i++)
            if(clientes.get(i).getNome().toLowerCase().contains(txfBusca.getText().toLowerCase()))
                clientePesquisa.add(clientes.get(i));  
        return clientePesquisa;
    }
}
