package codigo.algoritmos;

import codigo.entidades.Oferta;
import codigo.entidades.Resultado;

import java.util.ArrayList;
import java.util.List;

public class Backtracking {
    
    public static Resultado calcular(int capacidade, List<Oferta> ofertas) {
        // início tempo de execução
        long inicio = System.nanoTime();
        
        // Inicializa variáveis para armazenar o melhor resultado
        Resultado melhorResultado = new Resultado(0, new ArrayList<>(), 0);
        
        // Inicia o backtracking
        backtrack(capacidade, ofertas, 0, new ArrayList<>(), 0, melhorResultado);
        
        // fim tempo de execução
        long fim = System.nanoTime();
        // calculo tempo de execução
        long tempoExecucao = fim - inicio;
        
        // Atualiza o tempo de execução no melhor resultado
        melhorResultado.setTempoExecucao(tempoExecucao);
        
        return melhorResultado;
    }
    
    private static void backtrack(int capacidade, List<Oferta> ofertas, int indice, List<Oferta> selecionadas, int valorAtual, Resultado melhorResultado) {
        // Atualiza o melhor resultado se o valor atual for maior
        if (valorAtual > melhorResultado.getValorMaximo()) {
            melhorResultado.setValorMaximo(valorAtual);
            melhorResultado.setOfertasSelecionadas(new ArrayList<>(selecionadas));
        }
        
        // Loop para considerar cada oferta a partir do índice atual
        for (int i = indice; i < ofertas.size(); i++) {
            Oferta oferta = ofertas.get(i);
            // Verifica se a oferta atual pode ser considerada (poda)
            if (oferta.getMegawatts() <= capacidade) {
                // Adiciona a oferta atual à lista de selecionadas e atualiza a capacidade e o valor atual
                selecionadas.add(oferta);
                capacidade -= oferta.getMegawatts();
                valorAtual += oferta.getValor();
                
                // Continua a busca recursivamente
                backtrack(capacidade, ofertas, i + 1, selecionadas, valorAtual, melhorResultado);
                
                // Remove a oferta atual para explorar outras combinações
                selecionadas.remove(selecionadas.size() - 1);
                capacidade += oferta.getMegawatts();
                valorAtual -= oferta.getValor();
            }
        }
    }
}
