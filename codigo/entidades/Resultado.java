package codigo.entidades;

import java.util.ArrayList;
import java.util.List;

public class Resultado {
    private int valorMaximo;
    private int energiaUsada;
    private List<Oferta> ofertasSelecionadas;
    private long tempoExecucao;

    public Resultado(int valorMaximo, List<Oferta> ofertasSelecionadas, long tempoExecucao) {
        this.valorMaximo = valorMaximo;
        this.ofertasSelecionadas = ofertasSelecionadas;
        this.tempoExecucao = tempoExecucao;
    }

    public Resultado() {
        this.ofertasSelecionadas = new ArrayList<>();
    }

    public int getEnergiaUsada() {
        return energiaUsada;
    }

    public void setEnergiaUsada(int energiaUsada) {
        this.energiaUsada = energiaUsada;
    }

    public int getValorMaximo() {
        return valorMaximo;
    }

    public void setValorMaximo(int valorMaximo) {
        this.valorMaximo = valorMaximo;
    }

    public List<Oferta> getOfertasSelecionadas() {
        return ofertasSelecionadas;
    }

    public void setOfertasSelecionadas(List<Oferta> ofertasSelecionadas) {
        this.ofertasSelecionadas = ofertasSelecionadas;
    }

    public long getTempoExecucao() {
        return tempoExecucao;
    }

    public void setTempoExecucao(long tempoExecucao) {
        this.tempoExecucao = tempoExecucao;
    }

    public void adicionarOferta(Oferta oferta) {
        this.ofertasSelecionadas.add(oferta);
        this.energiaUsada += oferta.getMegawatts();
        this.valorMaximo += oferta.getValor();
    }

    public void removerOferta(Oferta oferta) {
        this.ofertasSelecionadas.remove(oferta);
        this.energiaUsada -= oferta.getMegawatts();
        this.valorMaximo -= oferta.getValor();
    }

    @Override
    public String toString() {
        return "Resultado{" +
                "valorMaximo=" + valorMaximo +
                ", energiaUsada=" + energiaUsada +
                ", ofertasSelecionadas=" + ofertasSelecionadas +
                ", tempoExecucao=" + tempoExecucao +
                '}';
    }
}
