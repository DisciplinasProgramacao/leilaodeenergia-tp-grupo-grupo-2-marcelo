package codigo;

import java.io.IOException;

public class GerenciadorLeilao {

    public static void main(String[] args) {
        try {
            long tempoTotalProgDinamica = 0;
            long tempoTotalAlgGuloso1   = 0;
            long tempoTotalBacktracking = 0;

            long tempoTotalAlgGuloso2   = 0;
            // Ler os dados do arquivo
            // LeitorDados.lerDados("codigo/dados/db.txt");
            LeitorDados.lerDados("codigo/dados/testBacktracking.txt");

            for (int i = 0; i < LeitorDados.conjuntosTeste.size(); i++) {
                LeitorDados.ConjuntoTeste conjunto = LeitorDados.conjuntosTeste.get(i);

                // Executar a programação dinâmica
//                // Resultado programacaoDinamica = ProgramacaoDinamica.calcular(conjunto.capacidadeTotal, conjunto.ofertas);
//                // exibirResultados("Resultados da Programação Dinâmica", programacaoDinamica);
//                // tempoTotalProgDinamica += programacaoDinamica.getTempoExecucao();

                // Executar o Algoritmo Guloso - Estratégia 1 - Ordenando por valores das ofertas de forma decrescente
//                Resultado algoritmoGuloso1 = AlgoritmoGuloso1.calcular(conjunto.capacidadeTotal, conjunto.ofertas);
//                exibirResultados("Resultados do Algoritmo Guloso - Estratégia 1", algoritmoGuloso1);
//                tempoTotalAlgGuloso1 += algoritmoGuloso1.getTempoExecucao();

                // Executar o backtracking
                Resultado backtracking = Backtracking.calcular(conjunto.capacidadeTotal, conjunto.ofertas);
                exibirResultados("Resultados do Algoritmo de Backtracking", backtracking);
                tempoTotalBacktracking += backtracking.getTempoExecucao();

//                 Executar o Algoritmo Guloso - Estratégia 2 - Ordenando pelo valor do megawatt de forma decrescente
                Resultado algoritmoGuloso2 = AlgoritmoGuloso2.calcular(conjunto.capacidadeTotal, conjunto.ofertas);
                exibirResultados("Resultados do Algoritmo Guloso - Estratégia 2", algoritmoGuloso2);
                tempoTotalAlgGuloso2 += algoritmoGuloso2.getTempoExecucao();

            }
            long mediaTempoExecucao = tempoTotalBacktracking / LeitorDados.conjuntosTeste.size();
            System.out.println("\nMédia de tempo de execução: " + mediaTempoExecucao + " nanosegundos");

        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    private static void exibirResultados(String titulo, Resultado resultado) {
        System.out.println(titulo);
        System.out.println("Valor máximo que pode ser obtido: " + resultado.getValorMaximo());
        System.out.println("Tempo de execução: " + resultado.getTempoExecucao() + " nanosegundos");
        System.out.println("Ofertas selecionadas:");
        for (Oferta oferta : resultado.getOfertasSelecionadas()) {
            System.out.println(oferta.getNome() + " comprou " + oferta.getMegawatts() + " MW por " + oferta.getValor() + " dinheiros");
        }
    }
}
