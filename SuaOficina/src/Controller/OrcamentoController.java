package Controller;

import Model.Clientes;
import Model.Peças;
import Model.Serviço;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import suaoficina.Orcamento;
import suaoficina.TelaPrincipal;

public class OrcamentoController implements Initializable, InterfaceController {

    @FXML
    private Button btnVoltar;
    @FXML
    private ComboBox<Clientes> cbCliente;
    @FXML
    private Button btCliente;
    @FXML
    private ComboBox<Peças> cbProdutos;
    @FXML
    private Button btProduto;
    @FXML
    private ComboBox<Serviço> cbServiços;
    @FXML
    private Button btServico;
    @FXML
    private ListView<String> lvOrcamentos;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btGerar;
    @FXML
    private Button btnFinalizar;
    @FXML
    private Button btMostrar;
    
    private List<String>orcamentos = new ArrayList<>();
    private ArrayList<Float>total = new ArrayList<>();

    
    private ObservableList<String> orcamento = FXCollections.observableArrayList();
    private ObservableList<Clientes> clientes = FXCollections.observableArrayList();
    private ObservableList<Serviço> serviços = FXCollections.observableArrayList();
    private ObservableList<Peças> produtos = FXCollections.observableArrayList();

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            carregarClientes();
        } catch (IOException ex) {
            Logger.getLogger(OrcamentoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         try {
            carregarProdutos();
        } catch (IOException ex) {
            Logger.getLogger(OrcamentoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            carregarServicos();
        } catch (IOException ex) {
            Logger.getLogger(OrcamentoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        btCliente.setOnMouseClicked((MouseEvent e)->{
            try {
                orcamentoCliente();
            } catch (IOException ex) {
                Logger.getLogger(OrcamentoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        btProduto.setOnMouseClicked((MouseEvent e)->{
            try {
                orcamentoProdutos();
            } catch (IOException ex) {
                Logger.getLogger(OrcamentoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        btServico.setOnMouseClicked((MouseEvent e)->{
            try {
                orcamentoServico();
            } catch (IOException ex) {
                Logger.getLogger(OrcamentoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        btnCancelar.setOnMouseClicked((MouseEvent e)->{
            abrirPrincipal();
        });
        
        btnVoltar.setOnMouseClicked((MouseEvent e)->{
            abrirPrincipal();
        });
       
        btnFinalizar.setOnMouseClicked((MouseEvent e)->{
            try {
                finalizar();
            } catch (IOException ex) {
                Logger.getLogger(OrcamentoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
       });
        
       btGerar.setOnMouseClicked((MouseEvent e)->{
           gerarPdf();
        });
       
        try {
            mostrarOrcamentos();
        } catch (IOException ex) {
            Logger.getLogger(OrcamentoController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       btMostrar.setOnMouseClicked((MouseEvent e)->{
            try {
                mostrarOrcamentos();
                //abrirLV();
            } catch (IOException ex) {
                Logger.getLogger(OrcamentoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }  
    
    @Override
     public void fechar(){
        Orcamento.getStage().close();
    }
    
    public void abrirPrincipal(){
        TelaPrincipal tp = new TelaPrincipal();
        fechar();
        try {
            tp.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(OrcamentoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void carregarClientes() throws FileNotFoundException, IOException{
        
        ArrayList<Clientes> cliente = new ArrayList<>();
        
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
        
        clientes = FXCollections.observableArrayList(cliente);
        
        cbCliente.setItems(clientes);
    }
    
    public void carregarProdutos() throws FileNotFoundException, IOException{
        
        ArrayList<Peças> peca = new ArrayList<>();
        
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
                
            peca.add(p);
                
        }while(linha!=null); 
        
        produtos = FXCollections.observableArrayList(peca);
        cbProdutos.setItems(produtos);

    }
    
    public void carregarServicos() throws FileNotFoundException, IOException{
        
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
        
        serviços = FXCollections.observableArrayList(serve);
        
        cbServiços.setItems(serviços);

    }
    
    public void orcamentoCliente() throws IOException{
        
        ArrayList<Clientes> orcamento = new ArrayList<>();
        orcamento.add(cbCliente.getSelectionModel().selectedItemProperty().getValue());
        
        int i, n = orcamento.size();
        for (i=0; i<n; i++){
           orcamentos.add("Cliente: "+orcamento.get(i).getNome());}
    }
    
    public void orcamentoServico() throws IOException{
        
        ArrayList<Serviço> orcamento = new ArrayList<>();
        orcamento.add(cbServiços.getSelectionModel().selectedItemProperty().getValue());
        
        int i, n = orcamento.size();
        for (i=0; i<n; i++){
        orcamentos.add("Serviço: "+orcamento.get(i).getNome()+" Preço: "
                +(Float.toString(orcamento.get(i).getValor())));
        total.add(orcamento.get(i).getValor());
        }
    }
    
    public void orcamentoProdutos() throws IOException{
        
        ArrayList<Peças> orcamento = new ArrayList<>();
        
        orcamento.add(cbProdutos.getSelectionModel().selectedItemProperty().getValue());
        
        int i, n = orcamento.size();
        for (i=0; i<n; i++){
        orcamentos.add("Produto: "+orcamento.get(i).getNome()+" Preço: "
                +(Float.toString(orcamento.get(i).getPreço())));
        total.add(orcamento.get(i).getPreço());}    
    }
    
    public void gerarPdf(){
        Document doc = new Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\Carlos Rossato\\Documents\\NetBeansProjects\\SuaOficina\\Orcamentos\\Orcamento.pdf"));
            doc.open();
            int j, m = orcamentos.size();
            doc.add(new Paragraph("ORÇAMENTO"));
            doc.add(new Paragraph("------------------"));
            for (j=0; j<m; j++){
                doc.add(new Paragraph(orcamentos.get(j)));}
            doc.close();
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText("PDF gerado com sucesso!");
            a.show();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OrcamentoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(OrcamentoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OrcamentoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void finalizar() throws IOException{
       
        FileWriter arq =  new FileWriter("Orcamento.txt", true);
        PrintWriter gravarArq = new PrintWriter(arq);
        
        int i, n = orcamentos.size();
        for (i=0; i<n; i++){
        gravarArq.println(orcamentos.get(i));}
        
        float T = (float) 0.0;
        int j, m = total.size();
        for (j=0; j<m; j++){
            T += total.get(j);
        }
        Float.toString(T);
        gravarArq.println("Total: R$"+T);
        gravarArq.println("----------------------------------------------------------------");
        gravarArq.close();
        
        orcamentos.clear();
    }
    
   
    public void mostrarOrcamentos() throws FileNotFoundException, IOException{
        
        FileReader arq = new FileReader("Orcamento.txt");
        BufferedReader lerArq = new BufferedReader(arq);
        String linha = lerArq.readLine();
        ArrayList<String>o = new ArrayList<>();
        do{
            o.add(linha);
            linha = lerArq.readLine();
         }while(linha!=null);
        
        orcamento = FXCollections.observableArrayList(o);
        lvOrcamentos.setItems(orcamento);
        
    }
    
    
    
}
