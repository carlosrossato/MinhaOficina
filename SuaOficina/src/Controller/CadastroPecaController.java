package Controller;

import Model.Peças;
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
import suaoficina.CadastroPeca;
import suaoficina.Peca;


public class CadastroPecaController implements Initializable, InterfaceController {

    @FXML
    private Button btnVoltar;
    @FXML
    private TextField txfNome;
    @FXML
    private TextField txfValor;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnSalvar;
    @FXML
    private TextField txfFabricante;
    @FXML
    private TextField txfTipoDeVeiculo;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        btnVoltar.setOnMouseClicked((MouseEvent e)->{
            abrirProdutos();
        });
        
        btnVoltar.setOnKeyPressed((KeyEvent e)->{
            if(e.getCode() == KeyCode.ENTER){
                abrirProdutos();
            }
        });
        
        btnSalvar.setOnMouseClicked((MouseEvent e)->{
            try {
                cadastrarCliente();
            } catch (IOException ex) {
                Logger.getLogger(CadastroPecaController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText("Produto cadastrado!");
            a.show();
        });
        
        btnSalvar.setOnKeyPressed((KeyEvent e)->{
            if(e.getCode() == KeyCode.ENTER){
                try {
                    cadastrarCliente();
                } catch (IOException ex) {
                    Logger.getLogger(CadastroPecaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText("Produto cadastrado!");
            a.show();
        });
        
        btnCancelar.setOnMouseClicked((MouseEvent e)->{
            abrirProdutos();
        });
        
        btnCancelar.setOnKeyPressed((KeyEvent e)->{
            if(e.getCode() == KeyCode.ENTER){
                abrirProdutos();
            }
        });
        
    }  
    
    @Override
    public void fechar(){
        CadastroPeca.getStage().close();
    }
    
    public void abrirProdutos(){
        Peca p = new Peca();
        fechar();
        try {
            p.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(PecaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cadastrarCliente() throws IOException{
        
        List<Peças> peça = new ArrayList<>();
        
        FileReader arq = new FileReader("Peças.txt");
        BufferedReader lerArq = new BufferedReader(arq);
        
        String linha = lerArq.readLine();
        
        do{
            Peças p = new Peças();
            p.setNome(linha);
            linha = lerArq.readLine();
            float x = Float.parseFloat(linha);
            p.setPreço(x);
            linha = lerArq.readLine();
            p.setFabricante(linha);
            linha = lerArq.readLine();
            p.setTipoDoAutomovel(linha);
            linha = lerArq.readLine();
                
            peça.add(p);
        }while(linha!=null);
        
        Peças p2 = new Peças();
        
        p2.setNome(txfNome.getText());
        float y = Float.parseFloat(txfValor.getText());
        p2.setPreço(y);
        p2.setFabricante(txfFabricante.getText());
        p2.setTipoDoAutomovel(txfTipoDeVeiculo.getText());
        
        peça.add(p2);
        
        FileWriter arq1 = new FileWriter("Peças.txt");
        PrintWriter gravarArq = new PrintWriter(arq1);
        
        int i, n = peça.size();
        for (i=0; i<n; i++) {
        gravarArq.printf("%s%n", peça.get(i).getNome());
        gravarArq.printf("%s%n", peça.get(i).getPreço());
        gravarArq.printf("%s%n", peça.get(i).getFabricante());
        gravarArq.printf("%s%n", peça.get(i).getTipoDoAutomovel());}
        gravarArq.close();
    }
}

