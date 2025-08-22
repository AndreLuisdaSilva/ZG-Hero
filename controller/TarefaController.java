package com.example.demo.exerciciosjava.KTO.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import com.example.demo.exerciciosjava.KTO.model.Tarefa;
import com.example.demo.exerciciosjava.KTO.service.GerenciadorDeTarefas;

public class TarefaController {

    private GerenciadorDeTarefas gerenciador;
    private Scanner scanner;

    public TarefaController() {
        this.gerenciador = new GerenciadorDeTarefas();
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        TarefaController controller = new TarefaController();
        controller.iniciar();
    }

    public void iniciar() {
        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 0);

        scanner.close();
        System.out.println("Saindo do Gerenciador de Tarefas. Até mais!");
    }

    private void exibirMenu() {
        System.out.println("\n--- Menu do Gerenciador de Tarefas ---");
        System.out.println("1. Adicionar Tarefa");
        System.out.println("2. Listar Todas as Tarefas");
        System.out.println("3. Listar Tarefas por Categoria");
        System.out.println("4. Listar Tarefas por Prioridade");
        System.out.println("5. Listar Tarefas por Prioridade em Ordem Decrecente");
        System.out.println("6. Listar Tarefas por Status");
        System.out.println("7. Excluir Tarefa");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private int lerOpcao() {
        while (!scanner.hasNextInt()) {
            System.out.println("Opção inválida. Digite um número.");
            scanner.next(); // Limpa a entrada inválida
            System.out.print("Escolha uma opção: ");
        }
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha após o número
        return opcao;
    }

    private void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                adicionarTarefa();
                break;
            case 2:
                listarTodasTarefas();
                break;
            case 3:
                listarTarefasPorCategoria();
                break;
            case 4:
                listarTarefasPorPrioridade();
                break;
            case 5:
                listarTarefasOrdemDecrescente();
                break;
            case 6:
                listarTarefasPorStatus();
                break;
            case 7:
                excluirTarefa();
                break;
            case 0:
                // Sair do programa
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private void adicionarTarefa() {
        System.out.println("\n--- Adicionar Nova Tarefa ---");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        LocalDate dataTermino = null;
        boolean dataValida = false;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        while (!dataValida) {
            System.out.print("Data de término (DD/MM/YYYY): ");
            String dataString = scanner.nextLine();
            try {
                dataTermino = LocalDate.parse(dataString, formatter);
                dataValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("Formato de data inválido. Use DD-MM-YYYY.");
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
            scanner.nextLine(); // Consumir a quebra de linha
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


        Tarefa novaTarefa = new Tarefa(nome, descricao, dataTermino, prioridade, categoria, status);
        gerenciador.adiciona(novaTarefa);
        System.out.println("Tarefa adicionada com sucesso!");
    }

    private void listarTodasTarefas() {
        System.out.println("\n--- Todas as Tarefas ---");
        List<Tarefa> tarefas = gerenciador.getListaTarefas();
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada.");
        } else {
            for (Tarefa tarefa : tarefas) {
                System.out.println(tarefa); // Usa o método toString da classe Tarefa
            }
        }
    }

    private void listarTarefasPorCategoria() {
        System.out.println("\n--- Listar Tarefas por Categoria ---");
        System.out.print("Digite a categoria para filtrar: ");
        String categoria = scanner.nextLine();
        List<Tarefa> tarefas = gerenciador.getTarefasPorCategoria(categoria);
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa encontrada para a categoria '" + categoria + "'.");
        } else {
            for (Tarefa tarefa : tarefas) {
                System.out.println(tarefa);
            }
        }
    }

    private void listarTarefasPorPrioridade() {
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
            scanner.nextLine(); // Consumir a quebra de linha
            if (prioridade >= 1 && prioridade <= 5) {
                prioridadeValida = true;
            } else {
                System.out.println("Prioridade inválida. Digite um número de 1 a 5.");
            }
        }

        List<Tarefa> tarefas = gerenciador.getTarefasPorPrioridade(prioridade);
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa encontrada com prioridade " + prioridade + ".");
        } else {
            for (Tarefa tarefa : tarefas) {
                System.out.println(tarefa);
            }
        }
    }

    private void listarTarefasOrdemDecrescente() {
        System.out.println("\n--- Listar Tarefas em Ordem Decrescente ---");
        List<Tarefa> tarefas = gerenciador.getTarefasPorOrdemDecrescente();
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada.");
        } else {
                for (Tarefa tarefa : tarefas){
                    System.out.println(tarefa);
                }
            }
    }

    private void listarTarefasPorStatus() {
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

        List<Tarefa> tarefas = gerenciador.getTarefasPorStatus(status);
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa encontrada com status " + status + ".");
        } else {
            for (Tarefa tarefa : tarefas) {
                System.out.println(tarefa);
            }
        }
    }

    private void excluirTarefa() {
        System.out.println("\n--- Excluir Tarefa ---");
        System.out.print("Digite o nome da tarefa a ser excluída: ");
        String nomeTarefa = scanner.nextLine();

        boolean removida = gerenciador.excluirTarefa(nomeTarefa);
        if (removida) {
            System.out.println("Tarefa '" + nomeTarefa + "' excluída com sucesso!");
        } else {
            System.out.println("Tarefa '" + nomeTarefa + "' não encontrada.");
        }
    }
}
