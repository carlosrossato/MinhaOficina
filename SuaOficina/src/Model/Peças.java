package Model;

public class Peças extends Designacao{
    
    private float preço;
    private String fabricante;
    private String tipoDoAutomovel;
    
    public float getPreço() {
        return preço;
    }

    public void setPreço(float preço) {
        if(preço>0) this.preço = preço;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        if(!fabricante.isEmpty()) this.fabricante = fabricante;
    }

    public String getTipoDoAutomovel() {
        return tipoDoAutomovel;
    }

    public void setTipoDoAutomovel(String tipoDoAutomovel) {
        if(!tipoDoAutomovel.isEmpty()) this.tipoDoAutomovel = tipoDoAutomovel;
    }
    
    @Override
    public String toString() {
        return nome+"-"+"R$"+preço;
    }
}
