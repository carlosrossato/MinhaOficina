package Model;

public class Automoveis {
    
    private String marca;
    private String cor;
    private String placa;
    private int ano;
    private Clientes dono;
    private float kilometragem;

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        if(!marca.isEmpty()) this.marca = marca;
        else this.marca = "Não cadastrado";
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        if(!cor.isEmpty()) this.cor = cor;
        else this.cor = "Não cadastrado";
    }
    
    public String getPlaca() {
        return placa;
    }
    
    public void setPlaca(String placa){
        if(!placa.isEmpty()) this.placa = placa;
        else this.placa = "Não cadastrado";
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        if(ano>=1886) this.ano = ano;
        else this.ano = 0000;
    }

    public String getDono() {
        return dono.getNome();
    }

    public void setDono(Clientes dono) {
        this.dono = dono;
    }

    public float getKilometragem() {
        return kilometragem;
    }   

    public void setKilometragem(float kilometragem){
        if(kilometragem > 0.0) this.kilometragem = kilometragem;
        else this.kilometragem = 0;
    }
}

