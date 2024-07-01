package codigo.algoritmos;

import codigo.entidades.Oferta;
import codigo.entidades.Resultado;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DivisaoConquista {

    public static Resultado calcular(int capacidadeTotal, List<Oferta> ofertas) {
        long inicio = System.nanoTime();

        ofertas.sort(Comparator.comparingDouble(o -> -1.0 * o.getValor() / o.getMegawatts()));

        // Cria uma cópia da lista de ofertas para usar como lista de ofertas disponíveis
        List<Oferta> ofertasDisponiveis = new ArrayList<>(ofertas);

        Resultado resultado = dEc_leilao(capacidadeTotal, ofertasDisponiveis);

        long fim = System.nanoTime();
        resultado.setTempoExecucao(fim - inicio);

        return resultado;
    }

    public static Resultado dEc_leilao(int capacidadeTotal, List<Oferta> ofertasDisponiveis) {
        if (capacidadeTotal < ofertasDisponiveis.stream().mapToInt(Oferta::getMegawatts).min().orElse(0)) {
            Oferta melhorOferta = calcularForcaBruta(capacidadeTotal, ofertasDisponiveis);
            Resultado resultado = new Resultado();
            if (melhorOferta != null) {
                resultado.adicionarOferta(melhorOferta);
                ofertasDisponiveis.remove(melhorOferta);  // Remove a oferta da lista de ofertas disponíveis
            }
            return resultado;
        }

        int quant = capacidadeTotal / 2;

        Resultado listaEsquerda = dEc_leilao(quant, ofertasDisponiveis);
        quant += capacidadeTotal % 2;
        Resultado listaDireita = dEc_leilao(quant, ofertasDisponiveis);
        return combinar(listaEsquerda, listaDireita, capacidadeTotal, ofertasDisponiveis);
    }

    public static Resultado combinar(Resultado listaEsquerda, Resultado listaDireita, int capacidadeTotal, List<Oferta> ofertas) {
        List<Oferta> ofertasSelecionadas = new ArrayList<>(listaEsquerda.getOfertasSelecionadas());
        for (Oferta oferta : listaDireita.getOfertasSelecionadas()) {
            if (!ofertasSelecionadas.contains(oferta)) {
                ofertasSelecionadas.add(oferta);
            }
        }

        Resultado solucao = new Resultado();
        solucao.setOfertasSelecionadas(ofertasSelecionadas);

        List<Oferta> ofertaNaoSelecionadas = new ArrayList<>(ofertas);
        ofertaNaoSelecionadas.removeAll(ofertasSelecionadas);

        int capacidadeRestante = capacidadeTotal - ofertasSelecionadas.stream().mapToInt(Oferta::getMegawatts).sum();

        for (Oferta oferta : ofertaNaoSelecionadas) {
            if (oferta.getMegawatts() <= capacidadeRestante) {
                solucao.getOfertasSelecionadas().add(oferta);
                capacidadeRestante -= oferta.getMegawatts();
            }
        }

        for (Oferta ofertaNaoSelecionada : ofertaNaoSelecionadas) {
            for (Oferta ofertaSelecionada : new ArrayList<>(solucao.getOfertasSelecionadas())) {
                if (ofertaNaoSelecionada.getMegawatts() <= (capacidadeRestante + ofertaSelecionada.getMegawatts())) {
                    double valorSelecionada = (double) ofertaSelecionada.getValor() / ofertaSelecionada.getMegawatts();
                    double valorNaoSelecionada = (double) ofertaNaoSelecionada.getValor() / ofertaNaoSelecionada.getMegawatts();
                    if (valorNaoSelecionada > valorSelecionada) {
                        solucao.getOfertasSelecionadas().remove(ofertaSelecionada);
                        solucao.getOfertasSelecionadas().add(ofertaNaoSelecionada);
                        capacidadeRestante += ofertaSelecionada.getMegawatts() - ofertaNaoSelecionada.getMegawatts();
                    }
                }
            }
        }

        return solucao;
    }

    public static Oferta calcularForcaBruta(int capacidade, List<Oferta> ofertas) {
        Oferta melhorOferta = null;
        double melhorValorPorMegawatt = 0;

        for (Oferta oferta : ofertas) {
            if (oferta.getMegawatts() <= capacidade) {
                double valorPorMegawatt = (double) oferta.getValor() / oferta.getMegawatts();
                if (melhorOferta == null || valorPorMegawatt > melhorValorPorMegawatt) {
                    melhorOferta = oferta;
                    melhorValorPorMegawatt = valorPorMegawatt;
                }
            }
        }

        return melhorOferta;
    }
}
