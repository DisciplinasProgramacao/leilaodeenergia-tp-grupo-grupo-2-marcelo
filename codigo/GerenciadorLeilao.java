package codigo;

import java.io.IOException;
import java.util.List;

public class GerenciadorLeilao {

    public static void main(String[] args) {
        try {
            // Ler os dados do arquivo
            LeitorDados.lerDados("codigo/dados/db.txt");
            int capacidade = LeitorDados.capacidadeTotal;
            List<Oferta> ofertas = LeitorDados.ofertas;

            // Executar a programação dinâmica
            Resultado programacaoDinamica = ProgramacaoDinamica.calcular(capacidade, ofertas);
            exibirResultados("Resultados da Programação Dinâmica", programacaoDinamica);

        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    private static void exibirResultados(String titulo, Resultado resultado) {
        System.out.println(titulo);
        System.out.println("Valor máximo que pode ser obtido: " + resultado.getValorMaximo());
        System.out.println("Tempo de execução: " + resultado.getTempoExecucao() + " milissegundos");
        System.out.println("Ofertas selecionadas:");
        for (Oferta oferta : resultado.getOfertasSelecionadas()) {
            System.out.println(oferta.nome + " comprou " + oferta.megawatts + " MW por " + oferta.valor + " dinheiros");
        }
    }
}
