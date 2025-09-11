package com.example.demo.exerciciosjava.K1T3Java.service;

import java.util.LinkedList;
import java.util.List;

import com.example.demo.exerciciosjava.K1T3Java.model.Tarefa;
import com.example.demo.exerciciosjava.K1T3Java.model.Tarefa.Status;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;

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

    public void gravarNoArquivo(List<Tarefa> tarefas) {
        try {
            List<String> linhas = new ArrayList<>();
            for (Tarefa tarefa : tarefas) {
                linhas.add(tarefa.toString());
            }
            Files.write(Path.of("saida.txt"), linhas);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void carregarDoArquivo() {
        Path arquivo = Path.of("saida.txt");
        if (!Files.exists(arquivo)) {
            System.out.println("Arquivo de tarefas não encontrado. Começando com lista vazia.");
            this.listaTarefas = new LinkedList<>();
            return;
        }

        try {
            if (Files.size(arquivo) == 0) {
                System.out.println("Arquivo de tarefas está vazio. Começando com lista vazia.");
                this.listaTarefas = new LinkedList<>();
                return;
            }
        } catch (IOException e) {
            System.err.println("Erro ao verificar o tamanho do arquivo: " + e.getMessage());
        }

        try {
            List<String> linhas = Files.readAllLines(arquivo);
            this.listaTarefas = new LinkedList<>();

            for (String linha : linhas) {
                Tarefa tarefa = parseLinhaParaTarefa(linha);
                if (tarefa != null) {
                    this.listaTarefas.add(tarefa);
                }
            }
            System.out.println("Tarefas carregadas do arquivo com sucesso.");

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo de tarefas: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Tarefa parseLinhaParaTarefa(String linha) {
        try {
            String[] paresAtributoValor = linha.split(",");

            if (paresAtributoValor.length >= 8) {
                String nome = null;
                String descricao = null;
                LocalDateTime dataTermino = null;
                int nivelPrioridade = 0;
                String categoria = null;
                Tarefa.Status status = null;
                boolean alarme = false;
                int antecedenciaAlarmeHoras = 0;

                for (String par : paresAtributoValor) {
                    String[] partes = par.split(":");

                    if (partes.length == 2) {
                        String nomeAtributo = partes[0].trim();
                        String valorAtributo = partes[1].trim();

                        switch (nomeAtributo) {
                            case "nome":
                                nome = valorAtributo;
                                break;
                            case "descricao":
                                descricao = valorAtributo;
                                break;
                            case "dataTermino":
                                if (!valorAtributo.equalsIgnoreCase("null")) {
                                    try {
                                        dataTermino = LocalDateTime.parse(valorAtributo);
                                    } catch (DateTimeParseException e) {
                                        System.err.println(
                                                "Erro ao parsear data '" + valorAtributo + "' na linha: " + linha);
                                    }
                                }
                                break;
                            case "nivelPrioridade":
                                try {
                                    nivelPrioridade = Integer.parseInt(valorAtributo);
                                } catch (NumberFormatException e) {
                                    System.err.println(
                                            "Erro ao parsear prioridade '" + valorAtributo + "' na linha: " + linha);
                                }
                                break;
                            case "categoria":
                                categoria = valorAtributo;
                                break;
                            case "status":
                                try {
                                    status = Tarefa.Status.valueOf(valorAtributo);
                                } catch (IllegalArgumentException e) {
                                    System.err.println(
                                            "Erro ao parsear status '" + valorAtributo + "' na linha: " + linha);
                                }
                                break;
                            case "alarme":
                                alarme = Boolean.parseBoolean(valorAtributo);
                                break;
                            case "antecedenciaAlarmeHoras":
                                antecedenciaAlarmeHoras = Integer.parseInt(valorAtributo);
                                break;
                            default:
                                System.err.println("Atributo desconhecido encontrado na linha: " + nomeAtributo);
                        }
                    } else {
                        System.err.println("Formato inválido para par atributo:valor na linha: " + par);
                    }
                }
                if (nome != null && descricao != null && categoria != null && status != null && nivelPrioridade >= 1
                        && nivelPrioridade <= 5) {
                    return new Tarefa(nome, descricao, dataTermino, nivelPrioridade, categoria, status, alarme, antecedenciaAlarmeHoras);
                } else {
                    System.err.println("Não foi possível extrair todos os campos obrigatórios da linha: " + linha);
                    return null;
                }

            } else {
                System.err.println(
                        "Linha com formato inválido ou incompleto (número insuficiente de atributos): " + linha);
                return null;
            }

        } catch (Exception e) {
            System.err.println("Erro geral ao parsear a linha: " + linha + " - " + e.getMessage());
            return null;
        }
    }

    public List<Tarefa> getListaTarefas() {
        return listaTarefas;
    }

    public <T> boolean setListaTarefas(String nomeTarefa, int escolha, T novoValor) {
        for(Tarefa tarefa : listaTarefas){
            if(tarefa.getNome().equalsIgnoreCase(nomeTarefa)){
                System.out.print("Nova categoria: ");

                switch (escolha) {
                    case 1:
                        if(novoValor instanceof String){
                            tarefa.setNome((String)novoValor);
                            break;
                        }else {
                            System.out.println("Valor inválido para Nome.");
                            return false;
                        }
                    case 2:
                        if(novoValor instanceof String){
                            tarefa.setDescricao((String)novoValor);
                            break;}
                        else {
                            System.out.println("Valor inválido para Descricao.");
                            return false;
                        }
                    case 3:
                        if (novoValor instanceof String) {
                            tarefa.setCategoria((String)novoValor);
                            break;}
                        else {
                            System.out.println("Valor inválido para Categoria.");
                            return false;
                        }
                    case 4:
                        if(novoValor instanceof String){
                            tarefa.setStatus(Tarefa.Status.valueOf(((String) novoValor).toUpperCase()));
                            break;
                        }else {
                            System.out.println("Valor inválido para Status.");
                            return false;
                        }
                    case 5:
                        if(novoValor instanceof Integer){
                            tarefa.setNivelPrioridade((Integer)novoValor);
                            break;
                        }else {
                            System.out.println("Valor inválido para Prioridade.");
                            return false;
                        }
                    case 6:
                        if(novoValor instanceof String){
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                            tarefa.setDataTermino(LocalDateTime.parse((String) novoValor, formatter));
                            break;
                        }else {
                            System.out.println("Valor inválido para DataTermino.");
                            return false;
                        }
                    case 7:
                        if(novoValor instanceof Boolean){
                            tarefa.setAlarme((Boolean)novoValor);
                            break;
                        }else {
                            System.out.println("Valor inválido para Alarme.");
                            return false;
                        }
                    case 8:
                        if(novoValor instanceof Integer){
                            tarefa.setAntecedenciaAlarmeHoras((Integer)novoValor);
                            break;
                        }else {
                            System.out.println("Valor inválido para Antecedencia.");
                            return false;
                        }
                    default:
                        System.out.println("Opção inválida.");
                        return false;
                }
                return true;
            }
        }
        return false;
    }

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

    public List<Tarefa> getTarefasPorPrioridade(int prioridade) {
        List<Tarefa> tarefasPorPrioridade = new LinkedList<>();

        for (Tarefa tarefa : listaTarefas) {
            if (tarefa.getNivelPrioridade() == prioridade) {
                tarefasPorPrioridade.add(tarefa);
            }
        }

        return tarefasPorPrioridade;
    }

    public List<Tarefa> getTarefasPorOrdemDecrescente() {
        List<Tarefa> listaTarefasOrdemDecrescente = new LinkedList<>();

        listaTarefasOrdemDecrescente.addAll(listaTarefas);

        Comparator<Tarefa> comparatorDecrescente = new Comparator<Tarefa>() {
            @Override
            public int compare(Tarefa p1, Tarefa p2) {
                int aux = Integer.compare(p2.getNivelPrioridade(), p1.getNivelPrioridade());

                if (aux == 0) {
                    return p2.getNome().compareToIgnoreCase(p1.getNome());
                }
                ;
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

    private List<Tarefa> rebalancearPorPrioridade() {
        Comparator<Tarefa> comparator = new Comparator<Tarefa>() {
            @Override
            public int compare(Tarefa p1, Tarefa p2) {// -1 se p1 < p2, 0 se p1 == p2, 1 se p1 > p2
                int aux = Integer.compare(p1.getNivelPrioridade(), p2.getNivelPrioridade());
                if (aux == 0) {
                    return p1.getNome().compareToIgnoreCase(p2.getNome());
                }
                ;
                return aux;
            }
        };

        listaTarefas.sort(comparator);

        return listaTarefas;
    }

    public void verificarAlarmes() {
        LocalDateTime agora = LocalDateTime.now();
        for (Tarefa tarefa : listaTarefas) {
            if (tarefa.isAlarme()) {
                LocalDateTime dataAlarme = tarefa.getDataTermino().minusHours(tarefa.getAntecedenciaAlarmeHoras());
                if (agora.isAfter(dataAlarme) && agora.isBefore(tarefa.getDataTermino())) {
                    System.out.println("Alarme: A tarefa '" + tarefa.getNome() + "' está próxima do fim!");
                }
            }
        }
    }
}
