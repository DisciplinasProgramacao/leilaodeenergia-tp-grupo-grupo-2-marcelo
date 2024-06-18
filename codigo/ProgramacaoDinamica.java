package codigo;
import java.util.ArrayList;
import java.util.List;

public class ProgramacaoDinamica {

    public static Resultado calcular(int capacidade, List<Oferta> ofertas) {

        // início tempo de execução
        long inicio = System.currentTimeMillis();

        // numero inicial de colunas
        // As colunas são as possibilidades, que vão de 0 até a capacidade de venda, ou total de megawatts disponíveis
        int[] colunaDinamica = new int[capacidade + 1];
        // tabela dinamica
        List<Oferta>[] combinacoes = gerarListaInicial(capacidade);

        // preenchimento da tabela
        preencherTabela(colunaDinamica, combinacoes, capacidade, ofertas);

        // fim tempo de execução
        long fim = System.currentTimeMillis();
        // calculo tempo de execução
        long tempoExecucao = fim - inicio;

        return new Resultado(colunaDinamica[capacidade], combinacoes[capacidade], tempoExecucao);
    }

    // É gerada a matriz de forma que cada coluna receba uma lista das ofertas
    private static List<Oferta>[] gerarListaInicial(int capacidade) {
        List<Oferta>[] combinacoes = new ArrayList[capacidade + 1];
        for (int i = 0; i <= capacidade; i++) {
            combinacoes[i] = new ArrayList<>();
        }
        return combinacoes;
    }

    private static void preencherTabela(int[] colunaDinamica, List<Oferta>[] combinacoes, int capacidade, List<Oferta> ofertas) {
        // Feito um for para percorre uma linha a cada vez, ou seja, uma oferta por vez
        for (Oferta oferta : ofertas) {
            /*
                Feito um for para percorre uma coluna a cada vez, Ou seja, uma venda por vez
                Para reduzir a quantidade de variáveis, o for é feito invertido, dessa forma, não é necessário possuir
                uma coluna dinâmica que represente a linha anterior e outra para a linha atual
                Como a oferta não pode ser efetuada para menos megawatts do que o disponível para a venda
                Só é efetuado o for para os casos em que a capacidade é superior a oferta
            */
            for (int j = capacidade; j >= oferta.megawatts; j--) {
                // compara linha com a oferta anterior com a substituição pela linha com a oferta atual
                // caso a oferta atual seja melha, entra no if
                if (colunaDinamica[j] < colunaDinamica[j - oferta.megawatts] + oferta.valor) {
                    // substitui o valor da coluna pela nova oferta
                    colunaDinamica[j] = colunaDinamica[j - oferta.megawatts] + oferta.valor;
                    // busca se há ofertas anteriores quando ocorreu a substituição
                    combinacoes[j] = new ArrayList<>(combinacoes[j - oferta.megawatts]);
                    // adiciona a nova oferta na solução
                    combinacoes[j].add(oferta);
                }
            }
        }
    }
}
