package Model;


public class Designacao{

    String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if(!nome.isEmpty()) this.nome = nome;
        else this.nome = null;
    }
    
}
