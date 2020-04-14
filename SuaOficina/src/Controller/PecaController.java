package Controller;

import Model.Peças;
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
import suaoficina.CadastroPeca;
import suaoficina.Peca;
import suaoficina.TelaPrincipal;

public class PecaController implements Initializable, InterfaceController {

    @FXML
    private Button btnVoltar;
    @FXML
    private TableView<Peças> tvProdutos;
    @FXML
    private TableColumn<Peças, String> cNome;
    @FXML
    private TableColumn<Peças, Float> cPreco;
    @FXML
    private TableColumn<Peças, String> cFabricante;
    @FXML
    private TableColumn<Peças, String> cTipoDeAutomovel;
    @FXML
    private TextField txfBusca;
    @FXML
    private Button btnAdd;
    
    private ObservableList<Peças> peças = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
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
            Logger.getLogger(PecaController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        txfBusca.setOnKeyReleased((KeyEvent e)->{
            tvProdutos.setItems(busca());
        });
        
        btnAdd.setOnMouseClicked((MouseEvent e)->{
            abrirCadastroPeca();
        });
        
        btnAdd.setOnKeyPressed((KeyEvent e)->{
            if(e.getCode() == KeyCode.ENTER){
                abrirCadastroPeca();
            }
        });
    }   
    
    @Override
     public void fechar(){
        Peca.getStage().close();
    }
    
    public void abrirPrincipal(){
        TelaPrincipal p = new TelaPrincipal();
        fechar();
        try {
            p.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(PecaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void initTable() throws IOException{
        cNome.setCellValueFactory(new PropertyValueFactory("nome"));
        cPreco.setCellValueFactory(new PropertyValueFactory("preço"));
        cFabricante.setCellValueFactory(new PropertyValueFactory("fabricante"));
        cTipoDeAutomovel.setCellValueFactory(new PropertyValueFactory("TipoDoAutomovel"));
        
        tvProdutos.setItems(atualizarTabela());
    }
    
    public void abrirCadastroPeca(){
        CadastroPeca cp = new CadastroPeca();
        fechar();
        try {
            cp.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(CadastroPecaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ObservableList<Peças> atualizarTabela() throws FileNotFoundException, IOException{
        ArrayList<Peças> p = new ArrayList<>();
        
        FileReader arq = new FileReader("Peças.txt");
        BufferedReader lerArq = new BufferedReader(arq);
        String linha = lerArq.readLine();
        
        do{
            Peças peça = new Peças();
            peça.setNome(linha);
            linha = lerArq.readLine();
            float x = Float.parseFloat(linha);
            peça.setPreço(x);
            linha = lerArq.readLine();
            peça.setFabricante(linha);
            linha = lerArq.readLine();
            peça.setTipoDoAutomovel(linha);
            linha = lerArq.readLine();
                
            p.add(peça);
                
        }while(linha!=null);
        
        peças = FXCollections.observableArrayList(p);
        return peças;
    }
    
    public ObservableList<Peças> busca(){
        ObservableList<Peças> clientePesquisa = FXCollections.observableArrayList();
        for(int i = 0;i<peças.size();i++)
            if((peças.get(i).getNome().toLowerCase().contains(txfBusca.getText().toLowerCase()))||
               (peças.get(i).getFabricante().toLowerCase().contains(txfBusca.getText().toLowerCase()))||
               (peças.get(i).getTipoDoAutomovel().toLowerCase().contains(txfBusca.getText().toLowerCase())))
                clientePesquisa.add(peças.get(i));  
        return clientePesquisa;
    }
}
