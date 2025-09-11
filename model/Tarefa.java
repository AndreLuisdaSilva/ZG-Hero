package com.example.demo.exerciciosjava.K1T3Java.model;

import java.time.LocalDateTime;

public class Tarefa {
    private String nome;
    private String descricao;
    private LocalDateTime dataTermino;
    private int nivelPrioridade;
    private String categoria;
    private Status status;
    private boolean alarme;
    private int antecedenciaAlarmeHoras;

    public enum Status {
        TODO, DOING, DONE;
    }

    public Tarefa(String nome, String descricao, LocalDateTime dataTermino, int nivelPrioridade, String categoria,
            Status status, Boolean alarme, int antecedenciaAlarmeHoras) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataTermino = dataTermino;
        this.nivelPrioridade = nivelPrioridade;
        this.categoria = categoria;
        this.status = status;
        this.alarme = alarme;
        this.antecedenciaAlarmeHoras = antecedenciaAlarmeHoras;
    }

    public int getAntecedenciaAlarmeHoras() {
        return antecedenciaAlarmeHoras;
    }

    public boolean isAlarme() {
        return alarme;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getDataTermino() {
        return dataTermino;
    }

    public int getNivelPrioridade() {
        return nivelPrioridade;
    }

    public String getCategoria() {
        return categoria;
    }

    public Status getStatus() {
        return status;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDataTermino(LocalDateTime dataTermino) {
        this.dataTermino = dataTermino;
    }

    public void setNivelPrioridade(int nivelPrioridade) {
        this.nivelPrioridade = nivelPrioridade;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setAlarme(boolean alarme) {
        this.alarme = alarme;
    }

    public void setAntecedenciaAlarmeHoras(int antecedenciaAlarmeHoras) {
        this.antecedenciaAlarmeHoras = antecedenciaAlarmeHoras;
    }

    @Override
    public String toString() {
        return "nome:" + nome +
                ", descricao:" + descricao +
                ", dataTermino:" + dataTermino +
                ", nivelPrioridade:" + nivelPrioridade +
                ", categoria:" + categoria +
                ", status:" + status +
                ", alarme:" + alarme +
                ", antecedenciaAlarmeHoras:" + antecedenciaAlarmeHoras;
    }
}
