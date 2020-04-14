package Controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import suaoficina.SuaOficina;
import suaoficina.TelaPrincipal;

public class LoginController implements Initializable, InterfaceController {

    
    @FXML
    private TextField tfLogin;
    @FXML
    private PasswordField tfSenha;
    @FXML
    private Button btnEntrar;
    @FXML
    private Label lbErros;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        btnEntrar.setOnMouseClicked((MouseEvent e)->{
            
            login = tfLogin.getText();
            senha = tfSenha.getText();
                
            try {
                checar(login, senha);
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });   
        
        btnEntrar.setOnKeyPressed((KeyEvent e) -> {
            
            login = tfLogin.getText();
            senha = tfSenha.getText();
                
            try {
                checar(login, senha);
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        
    }    
    
    String login;
    String senha;
    
    
    public void checar(String login, String senha) throws FileNotFoundException, IOException, Exception{
        
        FileReader arq = new FileReader("Usuarios.txt");
        BufferedReader lerArq = new BufferedReader(arq);
        String linha = lerArq.readLine();
        
        do{
            TelaPrincipal p = new TelaPrincipal();

            if(linha.equals(login)){
                linha = lerArq.readLine();
                if(linha.equals(senha)){
                    p.start(new Stage());
                    fechar();
                }
                else{
                lbErros.setText("Usuário e/ou senha inválidos");
                }}
            else{
                lbErros.setText("Usuário e/ou senha inválidos");
            }
            
            linha = lerArq.readLine();
        }while(linha!=null);
        arq.close();
    }
    
    @Override
    public void fechar(){
       SuaOficina.getStage().close(); 
    }
    
}
