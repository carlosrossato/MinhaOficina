package Model;

public class Serviço extends Designacao{
    
    private float valor;
    
    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        if(valor>0) this.valor = valor;
    }
    
    @Override
    public String toString() {
        return nome+"-"+"R$"+valor;
    }
}
