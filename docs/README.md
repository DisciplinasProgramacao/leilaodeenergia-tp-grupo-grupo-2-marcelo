# Dados e relatórios sobre a implementação e resultados

### Responsabilidades

Aluno                               | Desenvolvimento
----------------------------------- | -------------------------
Emmanuel Viglioni                   | Divisão e Conquista
Lucas Machado de Oliveira Andrade   | Programação Dinâmica
Marcelo Aguilar Araújo D'Almeida    | Algorítmo Guloso
Paulo Victor Pimenta Rubinger       | Backtracking

### Arquitetura utilizada

Com o objetivo de centralizar os dados e calcular de forma coesa os valores obtidos para cada tipo de implementação foram gerados classes genéricas que auxiliam na obtenção dos resultados e são utilizadas para todas as técnicas de solução para o problema.

#### Leitura dos Dados e Dados de Teste

Para centralizar a leitura dos dados e os dados de testes utilizados foi criado a classe LeitorDados.java que possui como objetivo obter todas as ofertas, assim como o limite de vendas em megawatts escritos no arquivo db.txt.

O arquivo db.txt apresenta a seguinte configuração e atua como um banco de dados local:

* O primeiro dado é o limite de megawatts do problema;
* Os dados seguintes são informações do nome da empresa interessada, a quantidade de megawatts e a quantia em dinheiro, respectivamente e separados por ponto e vírgula;
* Caso deseje fazer mais casos de testes basta que na próxima linha seja adicionado o novo limite, seguido pelos dados das empresas interessadas novamente.

No exemplo abaixo do db.txt é gera dois casos de testes para as mesmas empresas, o primeiro com limite de 1000 megawatts para venda e o segundo com 2000 megawatts.

``` txt
1000
I1;500;500
I2;500;510
I3;400;520
I4;300;400
I5;200;220
I6;900;1110
2000
I1;500;500
I2;500;510
I3;400;520
I4;300;400
I5;200;220
I6;900;1110
```

Dentro do leitor de dados é gerado uma lista da classe de Conjunto de Testes, cada conjunto de testes contendo a capacidade total e a lista de ofertas.

#### Ofertas e Resultados

Novamente a fim de padronizar as informações de entrada e de saída, para minimizar a diferença de tempo de execução devido a manipulação de dados, toda entrada é gerada por uma List da classe Oferta.java. Sendo que, nessa Oferta há o nome, o megawatts e o valor.

Além disso, toda implementação deve retornar a classe Resultado.java, que possui os dados de valorMaximo, ofertasSelecionadas e tempoExecucao, sendo eles o valor em dinheiro encontrado pela solução, as ofertas que fazem parte da solução e o tempo decorrido do início da execução da solução até o seu término.

#### Gerenciador de Leilao

Por fim, foi criado o GerenciadorLeilao.java, que possui como objetivo a gestão dos métodos de solução.

O gerenciador inicialmente realiza a leitura dos dados no db.txt e gera uma interação para caso caso de teste existente

![](./imgs/leituraDados.png)

Dentro dessa interação, é chamado o cálculo de cada método de solução para o problema, que irá receber as informações de capacidade total e ofertas, existentes no conjunto. Esses métodos devem retornar a classe Resultado como resposta, que pode ser visualizada de forma detalhada no método exibirResultados.

### Programação Dinâmica

#### Método geral

Para a solução da programação funcionar conforme o padrão foi feito o método calcular que recebe os valores de capacidade total e a lista de ofertas do problema.
Esse código foi dividido, para melhor análise e visualização.

Inicialmente é realizada o início da conta do tempo de execução.

Posteriormente é gerada uma matriz vazia que possue uma linha para cada oferta cadastrada e colunas que se inicia em 0 e finaliza no limite de vendas em megawatts. Formando uma matriz de Ofertas X Capacidade de Venda. Dessa forma, a matriz será preenchida de forma que possua um solução já definida para cada limite de venda até o resultado final desejado.

Após a criação da matriz foi gerada uma função a parte que é responsável por preencher a tabela. Quando finalizado o preenchimento, o último registro, na última linha e última coluna coresponderá ao valor em dinheiros maximizado da solução.

Entretanto, a tabela dinâmica não devolve as ofertas que foram responsáveis por gerar a solução, logo foi feita a parte outra função responsável por buscar a lista de ofertas que fazem parte da solução final.

Por fim é finalizado o tempo de execução, calculado e enviado a resposta em formato de Classe com os dados do resultado final em dinheiro, a lista de ofertas que fazem parte do resultado final e o tempo total de execução.

![](./imgs/calcularProgramacaoDinamica.png)

#### Preenchimento da tabela dinâmica

No preenchimento da tabela dinâmica foi feito dois fors, sendo que o for externo corresponde as ofertas (linhas da tabela) e o interno corresponde aos limites de venda (colunas da tabela)

Para cada linha existente no for interno é obtido os dados da oferta para serem utilizados nas interações da coluna sem precisar utilizar de getters e setters.

Para cada coluna, dado uma oferta, é feita a comparação para avaliar se a quantidade de megawatts da oferta pode ser inserida na célula, ou seja, se não supera o limite de capacidade de venda. Caso supere, significa que a oferta não pode efetuar a compra de energia, pois não há energia disponível para se vender. Nesse caso é repetido o valor da linha anterior.

Entretanto, caso seja possível considerar a oferta deve prevalecer o maior valor entre duas possibilidades:

* As ofertas consideradas anteriormente, existentes na linha anterior tabela[i - 1][j]
* A inserção da nova oferta, com a retirada das ofertas anteriores forem necessárias para adição da nova oferta tabela[i - 1][j - mw] + valor

![](./imgs/preencherProgramacaoDinamica.png)

#### Encontrando a lista de ofertas

Após o preenchimento completo da tabela é necessário realizar a busca dos dados que foram utilizados para obter a solução. Para isso é inicializado os dados que serão analisados a cada interação, assim como uma lista vazia dos resultados, que serão incrementados a cada dado utilizado.

Para descobrir quais dados foram utilizados é necessário analisar cada linha da tabela, iniciando da última oferta gerada pela tabela.
Para reduzir a quantidade de interações, o loop também é interrompido caso chegue na coluna 0, pois isso significa que chegou-se na base e já se tem o resultado final.

Para se obter a resposta basta validar se o valor analisado, que é iniciado como o valor da última célula da tabela, é diferente do seu valor anterior (mesma coluna, linha acima). Caso esses valores sejam diferentes, a oferta analisada foi inserida na resposta, logo ela é adicionada na solução.

Além disso, o valor analisado, que está presente na célula, deve ser atualizado para validar a interação da oferta anterior, além de retornar a coluna analisada também, retirando o valor de megawatts utilizado da solução.

Nesse caso, o novo valorTabela obtido pela retirada da oferta também poderia ser obtido através da célula existe na tabela[ofertaAnalisada - 1][colunaAnalisada - megawatts].

![](./imgs/encontrarOfertasProgramacaoDinamica.png)

#### Resultados Problema Exemplo

A solução encontrada Para o problema exemplo foi de 1140 dinheiros com as ofertas de I5, I4 e I3, totalizando a compra de 900 megawatts.
Para esse caso, a solução foi obtida em 37 microsegundos

O problema quando verificado a solução, é solucionado pela programação dinâmica em duas etapas.
A primeira etapa está relacionada com o preenchimento da tabela que ocorre n * m interações, sendo n a quantidade de ofertas e m o valor de limite de venda de megawatts, ou seja, nesse caso em que há 6 ofertas e 1000 de limite, há uma performance pior do que seria para n^2 interações. Mas com o aumento da quantiade de ofertas e a manutenção do limite, o comportamente se assemelha a uma interação linear de n interações.

A segunda etapa ocorre na busca pelos dados, que no pior caso, realiza n operações, sendo uma para cada oferta.

#### Aumentando o problema

Com o aumento do problema foi feito validações do tempo médio de execução, considerando sempre a média de 10 conjuntos de testes de mesmo tamanho.

Manutenção do Limite em 1000 Megawatts e aumento da quantidade de ofertas:

Quantidade de Ofertas   | Tempo execução (microsegundos)
----------------------- | -------------------------------
100                     | 396
200                     | 810
300                     | 1412
700                     | 3365
800                     | 4033
900                     | 9997
1000                    | 17471


### Algoritmo Guloso - Estratégia 1 - Ordenação por maior valor

O algoritmo inicialmente faz o inicio da conta do tempo de execução.

Depois disso, ofertas são ordenadas em ordem decrescente de valor. Essa ordenação garante que as ofertas de maior valor sejam consideradas primeiro pelo algoritmo guloso. Após a ordenação, o algoritmo percorre a lista de ofertas ordenadas, verificando para cada oferta se a capacidade restante é suficiente para aceitar a oferta.

Se a capacidade permitir, a oferta é adicionada à lista ofertasSelecionadas, a capacidade disponível é reduzida pelo valor de megawatts da oferta, e o valor da oferta é adicionado ao valorTotal.

Após percorrer todas as ofertas, o algoritmo registra o tempo final de execução e calcula a diferença para determinar o tempo total de execução. Finalmente, o algoritmo retorna um objeto Resultado que contém o valorTotal, a lista de ofertas selecionadas e o tempo de execução.


#### Vantagens - Ordenação por maior valor
- Ao ordenar as ofertas pelo maior valor por megawatt, o algoritmo garante que cada unidade de capacidade seja utilizada da forma mais eficiente possível em termos de valor monetário. Isso significa que as ofertas que proporcionam o maior retorno financeiro para cada megawatt de capacidade são consideradas primeiro.


- A abordagem gulosa baseia-se na premissa de fazer a melhor escolha local em cada etapa do processo. Ordenar pelo maior valor por megawatt permite ao algoritmo fazer essas escolhas locais ótimas de maneira direta e imediata.

#### Desvantagens - Ordenação por maior valor

 - Se a distribuição dos valores por megawatt não seguir uma tendência clara, ordenar apenas por esse critério pode não resultar na melhor solução global.

 
- Em certos cenários, especialmente quando a relação entre valor e capacidade não é linear, a abordagem gulosa pode resultar em soluções subótimas. Isso ocorre porque a estratégia gulosa não revisa decisões anteriores para garantir a otimalidade global.

#### Tempo de Ordenação 
- Foi utilizado na ordenação dos dados a estrutura Colelctions.sort, a qual possui, como referência, o algoritmo TimSort. Esse algoritmo  possui estratégia baseada em divisão e conquista, combinado com inserções em busca binária, devido à isso, ele possui complexidade O(n log n).

![](./imgs/algoritmoGulosoE1.png)

### Algoritmo Guloso - Estratégia 2

Para a segunda estratégia, foi optada a ordenação das ofertas de energia com base no valor por megawatt (V/MW) em ordem decrescente. Isso significa que as ofertas mais valiosas por unidade de energia são consideradas primeiro.

Para cada oferta na lista ordenada, o algoritmo verifica se a capacidade restante é suficiente para incluir a oferta. Se for, a oferta é adicionada à lista de lances selecionados, a capacidade disponível é reduzida pela quantidade de megawatts da oferta, e o valor total é aumentado pelo valor da oferta. Esse processo continua até que todas as ofertas tenham sido consideradas ou a capacidade esteja esgotada.

Após percorrer todas as ofertas, o algoritmo registra o tempo de fim e calcula o tempo de execução. Finalmente, retorna um objeto Resultado, contendo o valor total acumulado, a lista de ofertas selecionadas e o tempo de execução.


#### Vantagens - Ordenação por valor do megawatt, de forma decrescente

- Ordenar as ofertas pelo valor do megawatt em ordem decrescente garante que as ofertas mais valiosas por unidade de capacidade sejam consideradas primeiro. Isso leva à maximização do valor total dentro da capacidade disponível.


- A ordenação decrescente permite que o algoritmo guloso faça escolhas imediatas das melhores ofertas disponíveis, resultando em decisões locais ótimas a cada passo.


- A ordenação prévia das ofertas por valor do megawatt em ordem decrescente permite que o algoritmo realize a seleção de forma mais eficiente, evitando a necessidade de revisitar ofertas menos valiosas durante a execução.


#### Desvantagens - Ordenação por valor do megawatt, de forma decrescente

- Se a distribuição dos valores do megawatt não seguir uma tendência clara de decaimento, a ordenação decrescente pode não garantir a seleção das melhores ofertas em termos de valor total.

- Em certos cenários, a abordagem de ordenação decrescente pode levar a soluções subótimas, especialmente quando a relação entre valor e capacidade não é linear e a ordem de seleção das ofertas é crítica.

#### Tempo de Ordenação
- Da mesma forma que na estratégia 1, foi utilizada a estrutura Collections Sort, a qual possui como base o algoritmo Tim Sort, que utiliza a estratégia de divisão e conquista e busca binária para realizar a ordenação dos dados. Possui uma complexidade O(n log n).

![](./imgs/algoritmoGulosoE2.png)