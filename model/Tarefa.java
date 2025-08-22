package com.example.demo.exerciciosjava.K1T3Java.model;

import java.time.LocalDate;

public class Tarefa {
    private String nome;
    private String descricao;
    private LocalDate dataTermino;
    private int nivelPrioridade;
    private String categoria;
    private Status status;

    public enum Status {
        TODO, DOING, DONE;
    }

    public Tarefa(String nome, String descricao, LocalDate dataTermino, int nivelPrioridade, String categoria,
            Status status) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataTermino = dataTermino;
        this.nivelPrioridade = nivelPrioridade;
        this.categoria = categoria;
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getDataTermino() {
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

    public void setDataTermino(LocalDate dataTermino) {
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

    @Override
    public String toString() {
        return "nome:" + nome +
                ", descricao:" + descricao +
                ", dataTermino:" + dataTermino +
                ", nivelPrioridade:" + nivelPrioridade +
                ", categoria:" + categoria +
                ", status:" + status;
    }
}
