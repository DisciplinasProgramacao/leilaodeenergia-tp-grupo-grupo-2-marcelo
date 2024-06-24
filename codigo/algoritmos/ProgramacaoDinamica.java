package codigo.algoritmos;

import codigo.entidades.Oferta;
import codigo.entidades.Resultado;

import java.util.ArrayList;
import java.util.List;

public class ProgramacaoDinamica {
    
    public static Resultado calcular(int capacidade, List<Oferta> ofertas) {

        // início tempo de execução
        long inicio = System.nanoTime();

        // tabela dinamica inicial do tamanho Ofertas x Capacidade máxima
        int[][] tabela = new int[ofertas.size() + 1][capacidade + 1];

        // Preenche tabela dinamica
        preencherTabela(tabela, capacidade, ofertas);
        // Resultado final é o último dado da tabela dinamica
        int resultadoDinheiros = tabela[ofertas.size()][capacidade];

        // Busca Ofertas que fazem parte da solução
        List<Oferta> ofertasSelecionadas = buscarOfertasSelecionadas(tabela, capacidade, ofertas);

        // fim tempo de execução
        long fim = System.nanoTime();
        // calculo tempo de execução
        long tempoExecucao = fim - inicio;

        // Retorna resultado final com todos os dados
        return new Resultado(resultadoDinheiros, ofertasSelecionadas, tempoExecucao);
    }

    private static void preencherTabela(int[][] tabela, int capacidade, List<Oferta> ofertas) {
        // for para percorrer cada oferta (Linha da tabela)
        for (int i = 1; i <= ofertas.size(); i++) {

            // obtem dados da linha selecionada
            Oferta oferta = ofertas.get(i - 1);
            int mw = oferta.getMegawatts();
            int valor = oferta.getValor();

            // for para percorrer cada megawatt de capacidade máxima (Coluna da tabela)
            for (int j = 0; j <= capacidade; j++) {
                /*
                 * Caso a quantidade de megawatts da tabela seja superior ou igual a quantidade de megawatts da oferta
                 * deve ser selecionado o maior valor considerando:
                 *      O valor já existe da oferta anterior tabela[i - 1][j]
                 *      O valor ao adiciona a nova oferta data a limitação de megawatts tabela[i - 1][j - mw] + valor
                 * Caso a quantidade de megawatts da tabela seja inferior a quantidade de megawatts da oferta não
                 * é possível inserir a nova oferta, ou seja, deve ser copiado o valor anterior
                */
                if (mw <= j) {
                    tabela[i][j] = Math.max(tabela[i - 1][j], tabela[i - 1][j - mw] + valor);
                } else {
                    tabela[i][j] = tabela[i - 1][j];
                }
            }
        }
    }

    private static List<Oferta> buscarOfertasSelecionadas(int[][] tabela, int capacidade, List<Oferta> ofertas) {
        List<Oferta> ofertasSelecionadas = new ArrayList<>();
        int valorTabela = tabela[ofertas.size()][capacidade];
        int colunaTabela = capacidade;

        /*
         * Inicia-se o for da último oferta e irá subindo na tabela dinâmica
         * Caso o valor encontrado na tabela for 0, significa que chegou-se na base e o for pode ser interrompido
         */
        for (int i = ofertas.size(); i > 0 && valorTabela > 0; i--) {
            /*
             * Caso tenha alteração da oferta atual com a oferta anterior da tabela, significa que a oferta faz parte
             * do resultado
            */
            if (valorTabela != tabela[i - 1][colunaTabela]) {
                // obtem a oferta e adiciona na solução
                Oferta oferta = ofertas.get(i - 1);
                ofertasSelecionadas.add(oferta);
                // valor da tabela é redefinido para retirar a influência da oferta atual, que já está na solução
                valorTabela -= oferta.getValor();
                // reduz a coluna da tabela para retirar a influência da oferta atual, que já está na solução
                colunaTabela -= oferta.getMegawatts();
            }
        }
        
        return ofertasSelecionadas;
    }
}
