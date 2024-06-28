# FPAA  - Leilão de Energia

AVALIAÇÃO INDIVIDUAL
Relatório (Nota de grupo): 		4,8/6
- Pessoal, eu não sei onde eu deixei a entender (e creio que não deixei) que seria necessário um relatório de 41 páginas(!!!!) num trabalho simples de disciplina. Isso não é uma monografia. Trechos como 

      No cenário atual, a gestão eficiente de recursos é crucial para a competitividade e sustentabilidade das empresas, especialmente aquelas envolvidas na produção e distribuição de energia. Empresas produtoras de energia frequentemente enfrentam o desafio de maximizar seus lucros ao vender sua produção a diversas empresas interessadas. Este problema de otimização, conhecido como o problema do leilão de energia, envolve a venda de uma quantidade fixa de energia a diferentes compradores, cada um fazendo ofertas específicas para lotes de tamanhos determinados. 
      
      A complexidade do problema reside na necessidade de escolher quais ofertas aceitar de forma que o valor total obtido seja maximizado, considerando que cada empresa interessada comprará apenas um lote do tamanho exato da oferta. Essa característica confere ao problema uma natureza combinatória intratável, tipicamente pertencente à classe NP. A dificuldade em encontrar a solução ótima aumenta exponencialmente com o número de empresas interessadas e suas respectivas ofertas, exigindo a aplicação de técnicas avançadas de projeto e análise de algoritmos. Abaixo segue um exemplo prático do problema que estamos lidando:

estão ocupando páginas e páginas a troco de nada (vocês estão repetindo, de forma rebuscada, o que é o nosso enunciado) e roubando tempo e esforço que poderiam ser empregados no que interessa: a comparação dos algoritmos. 

Depois, apresentar listas de ofertas para 10 conjuntos de backtracking e coisas assim... não tem utilidade. O foco é no resultado comparativo das estratégias. Mais abaixo vocês até listam os resultados comparativos mas, por exemplo, não tem uma tabela com os resultados lado a lado para vermos qual algoritmo se deu melhor (assim como fizemos na sala de aula). Para a comparação de tempo, vocês fizeram direitinho. As conclusões estão razoáveis.


Apresentação (Nota individual): 4

Implementação (Nota individual): 10
- BT: ok 10/10
- AG: ok 10/10
- DC: como falado na apresentação, não me parece uma DC efetiva. Os lances são testados um a um e descartados em uma suposta "energia disponível". É feita uma "combinação" com e sem o atual, como se percorrendo uma lista. Se assemelha mais a uma maneira recursiva de fazer várias sequências de algoritmos gulosos (ou, neste sentido, um BT). Tem o mérito de guardar os calculados para não precisar recalcular. 6/10 
- PD: não ordena as empresas. 8/10
