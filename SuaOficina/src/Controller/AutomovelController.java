package Controller;

import Model.Automoveis;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import suaoficina.Automovel;
import suaoficina.CadastroAutomovel;
import suaoficina.TelaPrincipal;


public class AutomovelController implements Initializable, InterfaceController {

   @FXML
    private Button btnVoltar;
    @FXML
    private TableView<Automoveis> tvAutomoveis;
    @FXML
    private TextField txfBusca;
    @FXML
    private Button btnAdd;
    @FXML
    private TableColumn<Automoveis, String> cMarca;
    @FXML
    private TableColumn<Automoveis, String> cCor;
    @FXML
    private TableColumn<Automoveis, String> cPlaca;
    @FXML
    private TableColumn<Automoveis, Integer> cAno;
    @FXML
    private TableColumn<Automoveis, Clientes> cCliente;
    @FXML
    private TableColumn<Automoveis, Float> ckilometragem;
    
    private ObservableList<Automoveis> automoveis = FXCollections.observableArrayList();

    
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
            Logger.getLogger(AutomovelController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        btnAdd.setOnMouseClicked((MouseEvent e)->{
            abrirCadastroAutomovel();
        });
        
        btnAdd.setOnKeyPressed((KeyEvent e)->{
            if(e.getCode() == KeyCode.ENTER){
                abrirCadastroAutomovel();
            }
        });
        
        txfBusca.setOnKeyReleased((KeyEvent e)->{
            tvAutomoveis.setItems(busca());
        });
    }   
    
   @Override
    public void fechar(){
        Automovel.getStage().close();
    }
    
    public void abrirPrincipal(){
        TelaPrincipal tp = new TelaPrincipal();
        fechar();
        try {
            tp.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(AutomovelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void abrirCadastroAutomovel(){
        CadastroAutomovel ca = new CadastroAutomovel();
        fechar();
        try {
            ca.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(AutomovelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void initTable() throws IOException{
        cMarca.setCellValueFactory(new PropertyValueFactory("marca"));
        cCor.setCellValueFactory(new PropertyValueFactory("cor"));
        cPlaca.setCellValueFactory(new PropertyValueFactory("placa"));
        cAno.setCellValueFactory(new PropertyValueFactory("ano"));
        cCliente.setCellValueFactory(new PropertyValueFactory("dono"));
        ckilometragem.setCellValueFactory(new PropertyValueFactory("kilometragem"));
                
        tvAutomoveis.setItems(atualizarTabela());
    }
    
    public ObservableList<Automoveis> atualizarTabela() throws FileNotFoundException, IOException{
        
        ArrayList<Automoveis> auto = new ArrayList<>();
        
        FileReader arq = new FileReader("Automoveis.txt");
        BufferedReader lerArq = new BufferedReader(arq);
        String linha = lerArq.readLine();
        
        do{
            Automoveis a = new Automoveis();
            a.setMarca(linha);
            linha = lerArq.readLine();
            a.setCor(linha);
            linha = lerArq.readLine();
            a.setPlaca(linha);
            int y = Integer.parseInt(lerArq.readLine());
            a.setAno(y);
            linha = lerArq.readLine();
            Clientes c = new Clientes(linha);
            a.setDono(c);
            linha = lerArq.readLine();
            float x = Float.parseFloat(linha);
            a.setKilometragem(x);
            linha = lerArq.readLine();
                
            auto.add(a);
                
            }while(linha!=null); 
        
        automoveis = FXCollections.observableArrayList(auto);
        return automoveis;
    }
    
    public ObservableList<Automoveis> busca(){
        ObservableList<Automoveis> veiculosPesquisa = FXCollections.observableArrayList();
        for(int i = 0;i<automoveis.size();i++)
            if((automoveis.get(i).getPlaca().toLowerCase().contains(txfBusca.getText().toLowerCase()))||
              (automoveis.get(i).getDono().toLowerCase().contains(txfBusca.getText().toLowerCase())))
                veiculosPesquisa.add(automoveis.get(i));  
        return veiculosPesquisa;
    }
}