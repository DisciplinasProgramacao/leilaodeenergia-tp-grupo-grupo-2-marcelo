package codigo;

import java.io.IOException;
import java.util.List;

public class GerenciadorLeilao {

    public static void main(String[] args) {
        try {
            long tempoTotalProgDinamica = 0;
            // Ler os dados do arquivo
            LeitorDados.lerDados("codigo/dados/db.txt");
            for (int i = 0; i < LeitorDados.conjuntosTeste.size(); i++) {
                LeitorDados.ConjuntoTeste conjunto = LeitorDados.conjuntosTeste.get(i);

                // Executar a programação dinâmica
                Resultado programacaoDinamica = ProgramacaoDinamica.calcular(conjunto.capacidadeTotal, conjunto.ofertas);
                // exibirResultados("Resultados da Programação Dinâmica", programacaoDinamica);

                tempoTotalProgDinamica += programacaoDinamica.getTempoExecucao();
            }
            long mediaTempoExecucao = tempoTotalProgDinamica / LeitorDados.conjuntosTeste.size();
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
