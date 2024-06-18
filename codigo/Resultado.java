package codigo;

import java.util.List;

public class Resultado {
    int valorMaximo;
    List<Oferta> ofertasSelecionadas;
    long tempoExecucao;

    public Resultado(int valorMaximo, List<Oferta> ofertasSelecionadas, long tempoExecucao) {
        this.valorMaximo = valorMaximo;
        this.ofertasSelecionadas = ofertasSelecionadas;
        this.tempoExecucao = tempoExecucao;
    }

    public int getValorMaximo() {
        return valorMaximo;
    }

    public List<Oferta> getOfertasSelecionadas() {
        return ofertasSelecionadas;
    }

    public long getTempoExecucao() {
        return tempoExecucao;
    }
}
