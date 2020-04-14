package Model;

public class Clientes extends Designacao{
    
    private String endereço;
    private int cpf;
    
    public Clientes(){}
    
    public Clientes(String nome) {
        this.setNome(nome);
    }
       
    public String getEndereço(){ return endereço; }
    
    public void setEndereço(String endereço){ 
        if(!endereço.isEmpty()) this.endereço = endereço;
    }
    
    public int getCpf(){ return cpf; }
    
    public void setCpf(int cpf){
        if(cpf>0) this.cpf = cpf;
    }  

    @Override
    public String toString() {
        return nome;
    }
}

