package Controller;

import Model.Serviço;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import suaoficina.CadastroServico;
import suaoficina.Servico;
import suaoficina.TelaPrincipal;

public class ServicoController implements Initializable, InterfaceController{

    @FXML
    private Button btnVoltar;
    @FXML
    private TableView<Serviço> tvservicos;
    @FXML
    private TableColumn<Serviço, String> cNome;
    @FXML
    private TableColumn<Serviço, Float> cValor;
    @FXML
    private TextField txfBusca;
    @FXML
    private Button btnAdd;
    
    private ObservableList<Serviço> serviço = FXCollections.observableArrayList();
    
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
            Logger.getLogger(ServicoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        txfBusca.setOnKeyReleased((KeyEvent e)->{
            tvservicos.setItems(busca());
        });
        
        btnAdd.setOnMouseClicked((MouseEvent e)->{
            abrirCadastroServico();
        });
        
        btnAdd.setOnKeyPressed((KeyEvent e)->{
            if(e.getCode() == KeyCode.ENTER){
                abrirCadastroServico();
            }
        });
    }   
    
    @Override
    public void fechar(){
        Servico.getStage().close();
    }
    
    public void abrirPrincipal(){
        TelaPrincipal tp = new TelaPrincipal();
        fechar();
        try {
            tp.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(ServicoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void initTable() throws IOException{
        cNome.setCellValueFactory(new PropertyValueFactory("nome"));
        cValor.setCellValueFactory(new PropertyValueFactory("valor"));

        
        tvservicos.setItems(atualizarTabela());
    }
    
    public ObservableList<Serviço> atualizarTabela() throws FileNotFoundException, IOException{
       
        ArrayList<Serviço> serve = new ArrayList<>();
        
        
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
                
                serve.add(s);
                
            }while(linha!=null); 
        
        serviço = FXCollections.observableArrayList(serve);
        
        return serviço;
    }
    
    public ObservableList<Serviço> busca(){
        ObservableList<Serviço> clientePesquisa = FXCollections.observableArrayList();
        for(int i = 0;i<serviço.size();i++)
            if(serviço.get(i).getNome().toLowerCase().contains(txfBusca.getText().toLowerCase()))
                clientePesquisa.add(serviço.get(i));  
        return clientePesquisa;
    }
    
    public void abrirCadastroServico(){
        CadastroServico cs = new CadastroServico();
        fechar();
        try {
            cs.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(ServicoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
