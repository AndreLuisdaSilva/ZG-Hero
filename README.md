# Gerenciador de Tarefas (Java)

Um simples gerenciador de tarefas desenvolvido em Java puro, sem frameworks externos.  
O programa funciona pelo terminal e permite criar, listar e organizar tarefas de forma prática.  

---

## Requisitos Obrigatórios

Cada tarefa possui os seguintes atributos:  
- Nome  
- Descrição  
- Data de término  
- Nível de prioridade (1 a 5)  
- Categoria  
- Status (ToDo, Doing, Done)  

### Funcionalidades obrigatórias
- CRD de tarefas (Criar, Listar, Deletar)  
- Menu simples no terminal para navegação  
- Listagem de tarefas por:
  - Categoria  
  - Prioridade  
  - Status  
- Rebalanceamento automático da ordem:
  - Ao inserir uma nova tarefa, o sistema reorganiza de acordo com sua prioridade.  
- **Alarme de Tarefas:**
  - **Adicionar Alarme:** Agora é possível adicionar um alarme a uma tarefa, definindo com quantas horas de antecedência o aviso deve ser disparado.
  - **Notificação:** Ao iniciar a aplicação, o sistema verifica as tarefas com alarme e exibe uma notificação para aquelas que estão próximas do prazo de término.

Atenção:  
O projeto deve ser implementado apenas em Java, sem uso de frameworks como Spring, Micronaut ou Grails.  

---

## Requisitos Opcionais

Funcionalidades extras que podem ser implementadas:  
- Consultar quantidade de tarefas por status (ToDo, Doing, Done)  
- Atualizar tarefa existente  
- Filtrar tarefas por data de término  
- Persistência em arquivo (ex: .csv, .txt, .xml, .json)  
  - Evitar perda de dados ao fechar o programa.  

---

## Como executar

1. Compile o projeto:
   ```bash
   javac com/example/demo/exerciciosjava/K1T3Java/controller/TarefaController.java
   ```
2. Execute o programa:
   ```bash
   java com.example.demo.exerciciosjava.K1T3Java.controller.TarefaController
   ```

## Estrutura do Projeto
```
/src
└── main
    └── java
        └── com
            └── example
                └── demo
                    └── exerciciosjava
                        └── K1T3Java
                            ├── controller
                            │   └── TarefaController.java
                            ├── model
                            │   └── Tarefa.java
                            └── service
                                └── GerenciadorDeTarefas.java
```
