package com.example.demo.exerciciosjava.KTO.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Comparator;
import com.example.demo.exerciciosjava.KTO.model.Tarefa;
import com.example.demo.exerciciosjava.KTO.model.Tarefa.Status;

public class GerenciadorDeTarefas {
    private List<Tarefa> listaTarefas;

    public GerenciadorDeTarefas() {
        this.listaTarefas = new LinkedList<>();
    }

    public void adiciona(Tarefa tarefa) {
        listaTarefas.add(tarefa);
        if (listaTarefas.size() > 1) {
            rebalancearPorPrioridade();
        }
    }

    public List<Tarefa> getListaTarefas() {
        return listaTarefas;
    }

    // Método para excluir uma tarefa (você pode excluir por nome, ID, etc.)
    public boolean excluirTarefa(String nomeTarefa) {
        return listaTarefas.removeIf(tarefa -> tarefa.getNome().equalsIgnoreCase(nomeTarefa));
    }
    public List<Tarefa> getTarefasPorCategoria(String categoria) {

        List<Tarefa> tarefasPorCategoria = new LinkedList<>();

        for (Tarefa tarefa : listaTarefas) {
            if (tarefa.getCategoria().equals(categoria)) {
                tarefasPorCategoria.add(tarefa);
            }
        }

        return tarefasPorCategoria;
    }

    public List<Tarefa> getTarefasPorPrioridade(int prioridade){
        List<Tarefa> tarefasPorPrioridade = new LinkedList<>();

        for (Tarefa tarefa : listaTarefas) {
            if (tarefa.getNivelPrioridade() == prioridade) {
                tarefasPorPrioridade.add(tarefa);
            }
        }

        return tarefasPorPrioridade;
    }



    public List<Tarefa> getTarefasPorOrdemDecrescente(){
        List<Tarefa> listaTarefasOrdemDecrescente = new LinkedList<>();

        listaTarefasOrdemDecrescente.addAll(listaTarefas);
        
        Comparator<Tarefa> comparatorDecrescente = new Comparator<Tarefa>() {
            @Override
            public int compare(Tarefa p1, Tarefa p2) {
                // Inverte a comparação de prioridade
                int aux = Integer.compare(p2.getNivelPrioridade(), p1.getNivelPrioridade()); // p2 vs p1
        
                if(aux == 0) {
                    // Inverte a comparação de nome se as prioridades forem iguais
                    return p2.getNome().compareToIgnoreCase(p1.getNome()); // p2 vs p1
                };
                return aux;
            }
        };
        
        listaTarefasOrdemDecrescente.sort(comparatorDecrescente);

        return listaTarefasOrdemDecrescente;
    }


    public List<Tarefa> getTarefasPorStatus(Status status) {
        List<Tarefa> tarefasPorStatus = new LinkedList<>();

        for (Tarefa tarefa : listaTarefas) {
            if (tarefa.getStatus().equals(status)) {
                tarefasPorStatus.add(tarefa);
            }
        }

        return tarefasPorStatus;
    }

     // Método para rebalancear a lista de tarefas com base na prioridade
     private List<Tarefa> rebalancearPorPrioridade() {
        //definição do critério de comparação, usando um Comparator
        Comparator<Tarefa> comparator = new Comparator<Tarefa>() {
            @Override
            public int compare(Tarefa p1, Tarefa p2) {// -1 se p1 < p2, 0 se p1 == p2, 1 se p1 > p2
                int aux = Integer.compare(p1.getNivelPrioridade(), p2.getNivelPrioridade());
                if(aux == 0) {
                    return p1.getNome().compareToIgnoreCase(p2.getNome());
                };
                return aux;
            }
        };
    
        // uso do método sort - ordena a listaTarefas inplace
        listaTarefas.sort(comparator);

        // Retorna a lista original que agora está ordenada
        return listaTarefas;
    }

    // Métodos para requisitos opcionais podem ser adicionados aqui:
    // public boolean atualizarTarefa(String nomeTarefa, Tarefa novaTarefa) { ... }
    // public Map<Tarefa.Status, Long> contarTarefasPorStatus() { ... }
    // public List<Tarefa> listarTarefasPorDataTermino(LocalDate data) { ... }
    // Métodos para persistência (salvar/carregar)
}
