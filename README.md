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
   javac Main.java
Execute:


java Main
Estrutura do Projeto (sugestão)
```bash
/src
 ├── Main.java        # Classe principal (menu do programa)
 ├── Task.java        # Classe que representa uma tarefa
 ├── TaskManager.java # Classe responsável por gerenciar tarefas
 └── utils/           # Utilitários (ex: persistência em arquivo)
 ´´´