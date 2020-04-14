package Controller;

import Model.Automoveis;
import Model.Clientes;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import suaoficina.Automovel;
import suaoficina.CadastroAutomovel;

public class CadastroAutomovelController implements Initializable, InterfaceController {

    @FXML
    private Button btnVoltar;
    @FXML
    private ComboBox<Clientes> cbDono;
    @FXML
    private TextField txtCor;
    @FXML
    private TextField txtKilometragem;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnSalvar;
    @FXML
    private TextField txtMarca;
    @FXML
    private TextField txtPlaca;
    @FXML
    private TextField txtAno;
    
    private ObservableList<Clientes> cliente = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        btnVoltar.setOnMouseClicked((MouseEvent e)->{
            abrirAutomovel();
        });
        
        btnVoltar.setOnKeyPressed((KeyEvent e)->{
            if(e.getCode() == KeyCode.ENTER){
                abrirAutomovel();
            }
        });
        
         btnCancelar.setOnMouseClicked((MouseEvent e)->{
            abrirAutomovel();
        });
        
        btnCancelar.setOnKeyPressed((KeyEvent e)->{
            if(e.getCode() == KeyCode.ENTER){
                abrirAutomovel();
            }
        });
        
        btnSalvar.setOnMouseClicked((MouseEvent e)->{
            try {
                cadastrarAutomovel();
            } catch (IOException ex) {
                Logger.getLogger(CadastroAutomovelController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText("Automovel cadastrado!");
            a.show();
        });
        
        btnSalvar.setOnKeyPressed((KeyEvent e)->{
            if(e.getCode() == KeyCode.ENTER){
                try {
                    cadastrarAutomovel();
                } catch (IOException ex) {
                    Logger.getLogger(CadastroAutomovelController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setHeaderText("Automovel cadastrado!");
                a.show();
            }
        });
        
        try {
            carregarClientes();
        } catch (IOException ex) {
            Logger.getLogger(CadastroAutomovelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }        
    
    @Override
    public void fechar(){
        CadastroAutomovel.getStage().close();
    }
    
    public void abrirAutomovel(){
        Automovel a = new Automovel();
        fechar();
        try {
            a.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(CadastroAutomovelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cadastrarAutomovel() throws IOException{
        
        List<Automoveis> auto = new ArrayList<>();
        
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
        
        Automoveis a2 = new Automoveis();
        
        a2.setMarca(txtMarca.getText());
        a2.setCor(txtCor.getText());
        a2.setPlaca(txtPlaca.getText());
        int x = Integer.parseInt(txtAno.getText());
        a2.setAno(x);
        float y = Float.parseFloat(txtKilometragem.getText());
        a2.setKilometragem(y);
        a2.setDono(cbDono.getValue());
        
        auto.add(a2);
        
        FileWriter arq1 = new FileWriter("Automoveis.txt");
        PrintWriter gravarArq = new PrintWriter(arq1);
        
        int i, n = auto.size();
        for (i=0; i<n; i++) {
        gravarArq.printf("%s%n", auto.get(i).getMarca());
        gravarArq.printf("%s%n", auto.get(i).getCor());
        gravarArq.printf("%s%n", auto.get(i).getPlaca());
        gravarArq.printf("%s%n", auto.get(i).getAno());
        gravarArq.printf("%s%n", auto.get(i).getDono());
        gravarArq.printf("%s%n", auto.get(i).getKilometragem());}
        gravarArq.close();
    }
    
    public void carregarClientes() throws FileNotFoundException, IOException{
        
        List<Clientes> clientes = new ArrayList<>();
        
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
                
            clientes.add(c);
                
        }while(linha!=null);
        
        cliente = FXCollections.observableArrayList(clientes);
        
        cbDono.setItems(cliente);
    }
}
