Alguns requisitos/detalhes adicionais que ficaram em aberto até então:

Tarefa: uma tarefa tem parâmetros tais como, Nome, descrição, Data de término, nível de prioridade (1--5), categoria e status (ToDo, Doing e Done);


Requisitos Obrigatórios:

A tarefa deve ter pelo menos os parâmetros: 
Nome;
Descrição;
Data de término;
Nível de prioridade de 1 a 5;
Categoria;
Status (todo, doing e done).
CRD de Tarefas;
Disponibilize essas ações em menu simples no terminal.
Deve ser possível listar as atividades por:
Categoria;
Prioridade;
Status.
Quando uma nova atividade for inserida, deve ser feito o rebalanceamento da ordem, verificando a prioridade da mesma, para inseri-la na posição ideal;
Atenção: O projeto deve ser implementado em Java sem uso de frameworks (spring, micronaut, grails etc).


Requisitos opcionais: 

Ser possível consultar o número de atividades que estão concluídas (Done), estão para fazer (ToDo) e estão sendo feitas (Doing);
Ser possível Atualizar tarefa;
Ter uma opção de filtragem por data na listagem;
Seria um diferencial para o seu programa ter uma persistência. Não é interessante perder todas as atividades sempre que o programa é fechado, então pense em salvar os dados em algum arquivo (.csv, .txt, .xml, .json, .docks etc). 