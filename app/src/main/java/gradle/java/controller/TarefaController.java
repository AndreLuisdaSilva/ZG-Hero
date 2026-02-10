package gradle.java.controller;

import gradle.java.model.Tarefa;
import gradle.java.service.GerenciadorDeTarefas;
import gradle.java.view.TarefaView;

import java.util.List;

public class TarefaController {

    private GerenciadorDeTarefas gerenciador;
    private TarefaView view;

    public TarefaController(GerenciadorDeTarefas gerenciador, TarefaView view) {
        this.gerenciador = gerenciador;
        this.view = view;
    }

    public void iniciar() {
        gerenciador.carregarDoArquivo();
        gerenciador.verificarAlarmes();
        int opcao;
        do {
            view.exibirMenu();
            opcao = view.lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 0);

        view.fecharScanner();
        view.exibirMensagem("Saindo do Gerenciador de Tarefas. Até mais!");
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
                editarTarefa();
                break;
            case 8:
                excluirTarefa();
                break;
            case 0:
                // Sair do programa
                break;
            default:
                view.exibirMensagem("Opção inválida. Tente novamente.");
        }
    }

    private void adicionarTarefa() {
        Tarefa novaTarefa = view.lerDetalhesDaTarefa();
        gerenciador.adiciona(novaTarefa);
        gerenciador.gravarNoArquivo(gerenciador.getListaTarefas());
        view.exibirMensagem("Tarefa adicionada com sucesso!");
    }

    private void listarTodasTarefas() {
        view.exibirMensagem("\n--- Todas as Tarefas ---");
        List<Tarefa> tarefas = gerenciador.getListaTarefas();
        view.exibirTarefas(tarefas);
    }

    private void listarTarefasPorCategoria() {
        String categoria = view.lerCategoria();
        List<Tarefa> tarefas = gerenciador.getTarefasPorCategoria(categoria);
        if (tarefas.isEmpty()) {
            view.exibirMensagem("Nenhuma tarefa encontrada para a categoria '" + categoria + "'.");
        } else {
            view.exibirTarefas(tarefas);
        }
    }

    private void listarTarefasPorPrioridade() {
        int prioridade = view.lerPrioridade();
        List<Tarefa> tarefas = gerenciador.getTarefasPorPrioridade(prioridade);
        if (tarefas.isEmpty()) {
            view.exibirMensagem("Nenhuma tarefa encontrada com prioridade " + prioridade + ".");
        } else {
            view.exibirTarefas(tarefas);
        }
    }

    private void listarTarefasOrdemDecrescente() {
        view.exibirMensagem("\n--- Listar Tarefas em Ordem Decrescente ---");
        List<Tarefa> tarefas = gerenciador.getTarefasPorOrdemDecrescente();
        view.exibirTarefas(tarefas);
    }

    private void listarTarefasPorStatus() {
        Tarefa.Status status = view.lerStatus();
        List<Tarefa> tarefas = gerenciador.getTarefasPorStatus(status);
        if (tarefas.isEmpty()) {
            view.exibirMensagem("Nenhuma tarefa encontrada com status " + status + ".");
        } else {
            view.exibirTarefas(tarefas);
        }
    }

    private void editarTarefa() {
        String nomeTarefa = view.lerNomeDaTarefaParaEditar();
        int escolha = view.lerCampoParaEditar();
        String novoValor = view.lerNovoValor();

        // Lógica para editar a tarefa
        // ...

        view.exibirMensagem("Tarefa editada com sucesso!");
    }

    private void excluirTarefa() {
        String nomeTarefa = view.lerNomeDaTarefaParaExcluir();
        boolean removida = gerenciador.excluirTarefa(nomeTarefa);
        if (removida) {
            view.exibirMensagem("Tarefa '" + nomeTarefa + "' excluída com sucesso!");
        } else {
            view.exibirMensagem("Tarefa '" + nomeTarefa + "' não encontrada.");
        }
    }
}
