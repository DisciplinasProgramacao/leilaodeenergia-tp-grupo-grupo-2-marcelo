package codigo.algoritmos;

import codigo.entidades.Oferta;
import codigo.entidades.Resultado;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AlgoritmoGuloso1 {
    
    public static Resultado calcular(int capacidade, List<Oferta> ofertas) {

        // Iniciando valor total
        int valorTotal = 0;

        // Iniciando arraylist de ofertas
        ArrayList<Oferta> lancesSelecionados = new ArrayList<Oferta>();

        // início tempo de execução
        long inicio = System.nanoTime();

        // Ordenar os lances por valor total (V) em ordem decrescente
        Collections.sort(ofertas, new Comparator<Oferta>() {
            @Override
            public int compare(Oferta o1, Oferta o2) {
                return o2.getValor() - o1.getValor();
            }
        });

        for (Oferta oferta : ofertas){
           if (oferta.getMegawatts() <= capacidade){
               lancesSelecionados.add(oferta);
               capacidade -= oferta.getMegawatts();
               valorTotal += oferta.getValor();
           }
        }

        // fim tempo de execução
        long fim = System.nanoTime();
        // calculo tempo de execução
        long tempoExecucao = fim - inicio;

        // Retorna resultado final com todos os dados
        return new Resultado(valorTotal, lancesSelecionados, tempoExecucao);
    }
}
