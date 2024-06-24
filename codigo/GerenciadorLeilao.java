package codigo;

import codigo.algoritmos.AlgoritmoGuloso1;
import codigo.algoritmos.Backtracking;
import codigo.algoritmos.DivisaoConquista;
import codigo.algoritmos.ProgramacaoDinamica;
import codigo.utilitarios.LeitorDados;
import codigo.entidades.Oferta;
import codigo.entidades.Resultado;

import java.io.IOException;
import java.util.Scanner;

/**
 * Esta classe gerencia a execução de diferentes algoritmos para o problema do leilão.
 */
public class GerenciadorLeilao {

    /**
     * O método principal da classe. Ele lê dados de um arquivo, apresenta um menu ao usuário para selecionar um algoritmo,
     * e executa o algoritmo selecionado.
     */
    public static void main(String[] args) {
        try {
            // Ler os dados do arquivo
            LeitorDados.lerDados("codigo/dados/db.txt");

            Scanner scanner = new Scanner(System.in);
            int opcao;

            do {
                // Menu de seleção do algoritmo
                System.out.println("\nSelecione o algoritmo que deseja usar:");
                System.out.println("1. Programação Dinâmica");
                System.out.println("2. Divisão e Conquista");
                System.out.println("3. Algoritmo Guloso");
                System.out.println("4. Backtracking");
                System.out.println("0. Sair");
                opcao = scanner.nextInt();

                if (opcao >= 1 && opcao <= 4) {
                    executarAlgoritmo(opcao);
                } else if (opcao != 0) {
                    System.out.println("Opção inválida. Por favor, selecione uma opção válida.");
                }
            } while (opcao != 0);

            System.out.println("Encerrando o programa...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Executa o algoritmo selecionado e exibe os resultados.
     *
     * @param opcao O algoritmo selecionado. 1 para Programação Dinâmica, 2 para Divisão e Conquista, 3 para Algoritmo Guloso, e 4 para Backtracking.
     */
    private static void executarAlgoritmo(int opcao) {
        Resultado resultadoFinal;
        String titulo;
        long tempoTotalExecucao = 0;

        for (int i = 0; i < LeitorDados.conjuntosTeste.size(); i++) {
            LeitorDados.ConjuntoTeste conjunto = LeitorDados.conjuntosTeste.get(i);
            titulo = switch (opcao) {
                case 1 -> {
                    resultadoFinal = ProgramacaoDinamica.calcular(conjunto.capacidadeTotal, conjunto.ofertas);
                    yield "Resultados da Programação Dinâmica";
                }
                case 2 -> {
                    resultadoFinal = DivisaoConquista.calcular(conjunto.capacidadeTotal, conjunto.ofertas);
                    yield "Resultados da Divisão e Conquista";
                }
                case 3 -> {
                    resultadoFinal = AlgoritmoGuloso1.calcular(conjunto.capacidadeTotal, conjunto.ofertas);
                    yield "Resultados do Algoritmo Guloso";
                }
                case 4 -> {
                    resultadoFinal = Backtracking.calcular(conjunto.capacidadeTotal, conjunto.ofertas);
                    yield "Resultados do Algoritmo de Backtracking";
                }
                default -> throw new IllegalArgumentException("Opção inválida: " + opcao);
            };

            tempoTotalExecucao += resultadoFinal.getTempoExecucao();
            exibirResultados(titulo, resultadoFinal);
        }

        double mediaTempoExecucaoEmSegundos = (double) tempoTotalExecucao / LeitorDados.conjuntosTeste.size() / 1_000_000_000.0;
        System.out.printf("\nMédia de tempo de execução: %.5f segundos\n", mediaTempoExecucaoEmSegundos);
    }

    /**
     * Exibe os resultados da execução do algoritmo.
     *
     * @param titulo    O título dos resultados.
     * @param resultado Os resultados da execução do algoritmo.
     */
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