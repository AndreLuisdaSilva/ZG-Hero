package com.example.demo.exerciciosjava.K1T3Java.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import com.example.demo.exerciciosjava.K1T3Java.model.Tarefa;

public class TarefaView {

    private Scanner scanner;

    public TarefaView() {
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        System.out.println("\n--- Menu do Gerenciador de Tarefas ---");
        System.out.println("1. Adicionar Tarefa");
        System.out.println("2. Listar Todas as Tarefas");
        System.out.println("3. Listar Tarefas por Categoria");
        System.out.println("4. Listar Tarefas por Prioridade");
        System.out.println("5. Listar Tarefas por Prioridade em Ordem Decrecente");
        System.out.println("6. Listar Tarefas por Status");
        System.out.println("7. Editar Tarefa");
        System.out.println("8. Excluir Tarefa");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    public int lerOpcao() {
        while (!scanner.hasNextInt()) {
            System.out.println("Opção inválida. Digite um número.");
            scanner.next();
            System.out.print("Escolha uma opção: ");
        }
        int opcao = scanner.nextInt();
        scanner.nextLine();
        return opcao;
    }

    public Tarefa lerDetalhesDaTarefa() {
        System.out.println("\n--- Adicionar Nova Tarefa ---");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        LocalDateTime dataTermino = null;
        boolean dataValida = false;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        while (!dataValida) {
            System.out.print("Data de término (DD/MM/YYYY HH:mm): ");
            String dataString = scanner.nextLine();
            try {
                dataTermino = LocalDateTime.parse(dataString, formatter);
                dataValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("Formato de data inválido. Use DD/MM/YYYY HH:mm.");
            }
        }

        int prioridade = 0;
        boolean prioridadeValida = false;
        while (!prioridadeValida) {
            System.out.print("Nível de Prioridade (1-5): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida. Digite um número de 1 a 5.");
                scanner.next();
                System.out.print("Nível de Prioridade (1-5): ");
            }
            prioridade = scanner.nextInt();
            scanner.nextLine();
            if (prioridade >= 1 && prioridade <= 5) {
                prioridadeValida = true;
            } else {
                System.out.println("Prioridade inválida. Digite um número de 1 a 5.");
            }
        }

        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();

        Tarefa.Status status = null;
        boolean statusValido = false;
        while (!statusValido) {
            System.out.print("Status (TODO, DOING, DONE): ");
            String statusString = scanner.nextLine().toUpperCase();
            try {
                status = Tarefa.Status.valueOf(statusString);
                statusValido = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Status inválido. Use TODO, DOING ou DONE.");
            }
        }

        System.out.print("Deseja adicionar um alarme para esta tarefa? (S/N): ");
        String respostaAlarme = scanner.nextLine();
        boolean alarme = respostaAlarme.equalsIgnoreCase("S");
        int antecedenciaAlarmeHoras = 0;
        if (alarme) {
            System.out.print("Com quantas horas de antecedência deseja ser notificado? ");
            antecedenciaAlarmeHoras = scanner.nextInt();
            scanner.nextLine();
        }

        return new Tarefa(nome, descricao, dataTermino, prioridade, categoria, status, alarme, antecedenciaAlarmeHoras);
    }

    public void exibirTarefas(List<Tarefa> tarefas) {
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada.");
        } else {
            for (Tarefa tarefa : tarefas) {
                System.out.println(tarefa);
            }
        }
    }

    public String lerCategoria() {
        System.out.println("\n--- Listar Tarefas por Categoria ---");
        System.out.print("Digite a categoria para filtrar: ");
        return scanner.nextLine();
    }

    public int lerPrioridade() {
        System.out.println("\n--- Listar Tarefas por Prioridade ---");
        int prioridade = 0;
        boolean prioridadeValida = false;
        while (!prioridadeValida) {
            System.out.print("Digite o nível de prioridade (1-5) para filtrar: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida. Digite um número de 1 a 5.");
                scanner.next();
                System.out.print("Digite o nível de prioridade (1-5) para filtrar: ");
            }
            prioridade = scanner.nextInt();
            scanner.nextLine();
            if (prioridade >= 1 && prioridade <= 5) {
                prioridadeValida = true;
            } else {
                System.out.println("Prioridade inválida. Digite um número de 1 a 5.");
            }
        }
        return prioridade;
    }

    public Tarefa.Status lerStatus() {
        System.out.println("\n--- Listar Tarefas por Status ---");
        Tarefa.Status status = null;
        boolean statusValido = false;
        while (!statusValido) {
            System.out.print("Digite o status (TODO, DOING, DONE) para filtrar: ");
            String statusString = scanner.nextLine().toUpperCase();
            try {
                status = Tarefa.Status.valueOf(statusString);
                statusValido = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Status inválido. Use TODO, DOING ou DONE.");
            }
        }
        return status;
    }

    public String lerNomeDaTarefaParaEditar() {
        System.out.println("\n--- Editar Tarefa ---");
        System.out.print("Digite o nome da tarefa a ser editada: ");
        return scanner.nextLine();
    }

    public int lerCampoParaEditar() {
        System.out.println("Escolha o campo que deseja alterar:");
        System.out.println("1 - Nome");
        System.out.println("2 - Descrição");
        System.out.println("3 - Categoria");
        System.out.println("4 - Status");
        System.out.println("5 - Prioridade");
        System.out.println("6 - Data de Término");
        System.out.println("7 - Alarme");
        System.out.println("8 - Antecedência do Alarme");
        int escolha = scanner.nextInt();
        scanner.nextLine();
        return escolha;
    }

    public String lerNovoValor() {
        System.out.println("Digite o novo valor:");
        return scanner.nextLine();
    }

    public String lerNomeDaTarefaParaExcluir() {
        System.out.println("\n--- Excluir Tarefa ---");
        System.out.print("Digite o nome da tarefa a ser excluída: ");
        return scanner.nextLine();
    }

    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    public void fecharScanner() {
        scanner.close();
    }
}
