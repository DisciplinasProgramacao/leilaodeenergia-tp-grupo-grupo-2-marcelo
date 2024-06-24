package codigo.entidades;

import java.util.List;

public class Resultado {
    private int valorMaximo;
    private List<Oferta> ofertasSelecionadas;
    private long tempoExecucao;

    public Resultado(int valorMaximo, List<Oferta> ofertasSelecionadas, long tempoExecucao) {
        this.valorMaximo = valorMaximo;
        this.ofertasSelecionadas = ofertasSelecionadas;
        this.tempoExecucao = tempoExecucao;
    }

    public Resultado(int valorMaximo, List<Oferta> ofertasSelecionadas) {
        this.valorMaximo = valorMaximo;
        this.ofertasSelecionadas = ofertasSelecionadas;
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
}
