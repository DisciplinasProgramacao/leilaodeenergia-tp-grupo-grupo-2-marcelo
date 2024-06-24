package codigo.algoritmos;

import codigo.entidades.Oferta;
import codigo.entidades.Resultado;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe que implementa o algoritmo de Divisão e Conquista com memoização
 * para resolver o problema de maximização de valor das ofertas dentro de uma capacidade total.
 */
public class DivisaoConquista {

    /**
     * Método principal para calcular o valor máximo das ofertas selecionadas
     * que não excedem a capacidade total. Iremos utilizar memoização para evitar recalculações.
     *
     * @param capacidadeTotal A capacidade total disponível.
     * @param ofertas A lista de ofertas disponíveis.
     * @return Um objeto Resultado contendo o valor máximo, as ofertas selecionadas e o tempo de execução.
     */
    public static Resultado calcular(int capacidadeTotal, List<Oferta> ofertas) {
        // Início do tempo de execução
        long inicio = System.nanoTime();

        // Ordenar ofertas pelo valor por megawatt (decrescente)
        // Está sendo multiplicado por -1 para inverter a ordem
        ofertas.sort(Comparator.comparingDouble(o -> -1.0 * o.getValor() / o.getMegawatts()));

        // Inicializar a lista de ofertas selecionadas
        List<Oferta> ofertasSelecionadas = new ArrayList<>();
        // Inicializar o mapa de memoização
        Map<String, ResultadoParcial> memo = new HashMap<>();
        // Calcular o valor máximo das ofertas utilizando recursão com memoização
        int valorMaximo = calcularRecursivo(capacidadeTotal, ofertas, 0, ofertasSelecionadas, memo);

        // Fim do tempo de execução
        long fim = System.nanoTime();
        long tempoExecucao = fim - inicio;

        // Retornar o resultado final
        return new Resultado(valorMaximo, ofertasSelecionadas, tempoExecucao);
    }

    /**
     * Método recursivo para calcular o valor máximo das ofertas selecionadas.
     * Utiliza memoização para evitar recalculações de subproblemas já resolvidos.
     *
     * @param capacidadeRestante A capacidade restante disponível.
     * @param ofertas A lista de ofertas disponíveis.
     * @param indice O índice atual na lista de ofertas.
     * @param ofertasSelecionadas A lista de ofertas selecionadas até o momento.
     * @param memo O mapa de memoização.
     * @return O valor máximo das ofertas selecionadas.
     */
    private static int calcularRecursivo(int capacidadeRestante, List<Oferta> ofertas, int indice, List<Oferta> ofertasSelecionadas, Map<String, ResultadoParcial> memo) {
        // Caso base: sem capacidade restante ou sem ofertas
        if (capacidadeRestante <= 0 || indice >= ofertas.size()) {
            return 0;
        }

        // Chave para memoização
        String chave = capacidadeRestante + "-" + indice;

        // Verificar se já temos o resultado memoizado
        if (memo.containsKey(chave)) {
            ResultadoParcial resultadoParcial = memo.get(chave);
            ofertasSelecionadas.clear();
            ofertasSelecionadas.addAll(resultadoParcial.ofertasSelecionadas);
            return resultadoParcial.valor;
        }

        // Obter a oferta atual
        Oferta ofertaAtual = ofertas.get(indice);

        // Listas para armazenar as ofertas selecionadas em ambos os cenários
        List<Oferta> ofertasComAtual = new ArrayList<>(ofertasSelecionadas);
        int valorComAtual = 0;
        // Caso a oferta atual possa ser incluída (não ultrapassa a capacidade restante)
        if (ofertaAtual.getMegawatts() <= capacidadeRestante) {
            ofertasComAtual.add(ofertaAtual);
            valorComAtual = ofertaAtual.getValor() + calcularRecursivo(capacidadeRestante - ofertaAtual.getMegawatts(), ofertas, indice + 1, ofertasComAtual, memo);
        }

        // Valor sem incluir a oferta atual
        List<Oferta> ofertasSemAtual = new ArrayList<>(ofertasSelecionadas);
        int valorSemAtual = calcularRecursivo(capacidadeRestante, ofertas, indice + 1, ofertasSemAtual, memo);

        // Selecionar a opção com maior valor
        int valorMaximo;
        List<Oferta> melhoresOfertas;
        if (valorComAtual > valorSemAtual) {
            ofertasSelecionadas.clear();
            ofertasSelecionadas.addAll(ofertasComAtual);
            valorMaximo = valorComAtual;
            melhoresOfertas = ofertasComAtual;
        } else {
            ofertasSelecionadas.clear();
            ofertasSelecionadas.addAll(ofertasSemAtual);
            valorMaximo = valorSemAtual;
            melhoresOfertas = ofertasSemAtual;
        }

        // Armazenar o resultado memoizado
        memo.put(chave, new ResultadoParcial(valorMaximo, melhoresOfertas));

        return valorMaximo;
    }

    /**
     * Classe auxiliar para armazenar o resultado parcial de um subproblema.
     */
    private static class ResultadoParcial {
        int valor;
        List<Oferta> ofertasSelecionadas;

        ResultadoParcial(int valor, List<Oferta> ofertasSelecionadas) {
            this.valor = valor;
            this.ofertasSelecionadas = new ArrayList<>(ofertasSelecionadas);
        }
    }
}
