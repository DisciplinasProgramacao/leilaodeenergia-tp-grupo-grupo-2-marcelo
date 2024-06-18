package codigo;

public class Oferta {
    private String nome;
    private int megawatts;
    private int valor;

    public Oferta(String nome, int megawatts, int valor) {
        this.nome = nome;
        this.megawatts = megawatts;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getMegawatts() {
        return megawatts;
    }

    public void setMegawatts(int megawatts) {
        this.megawatts = megawatts;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
