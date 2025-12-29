
package com.example.demo.exerciciosjava.K1T3Java.test.java.com.example.demo.exerciciosjava.K1T3Java.service;

import com.example.demo.exerciciosjava.K1T3Java.model.Tarefa;
import com.example.demo.exerciciosjava.K1T3Java.service.GerenciadorDeTarefas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GerenciadorDeTarefasTest {

    private GerenciadorDeTarefas gerenciador;
    private Tarefa tarefa1;
    private Tarefa tarefa2;

    @BeforeEach
    public void setup() {
        gerenciador = new GerenciadorDeTarefas();
        tarefa1 = new Tarefa("Tarefa 1", "Descrição 1", LocalDateTime.now().plusDays(1), 3, "Categoria 1", Tarefa.Status.TODO, false, 0);
        tarefa2 = new Tarefa("Tarefa 2", "Descrição 2", LocalDateTime.now().plusDays(2), 1, "Categoria 2", Tarefa.Status.DOING, true, 2);
    }

    @Test
    public void testeAdicionarTarefa() {
        // Given
        System.out.println("Teste de criação de tarefa: Criando >> Tarefa 1: " + tarefa1.getNome() + ", " + tarefa1.getDataTermino() + ", " + tarefa1.getStatus());

        // When
        gerenciador.adiciona(tarefa1);

        // Then
        List<Tarefa> tarefas = gerenciador.getListaTarefas();
        assertEquals(1, tarefas.size());
        assertEquals(tarefa1, tarefas.get(0));
        System.out.println(">> criada com sucesso!");
    }

    @Test
    public void testeExcluirTarefa() {
        // Given
        gerenciador.adiciona(tarefa1);
        gerenciador.adiciona(tarefa2);
        System.out.println("Teste de exclusão de tarefa: Excluindo >> " + tarefa1.getNome());

        // When
        boolean excluido = gerenciador.excluirTarefa(tarefa1.getNome());

        // Then
        assertTrue(excluido);
        List<Tarefa> tarefas = gerenciador.getListaTarefas();
        assertEquals(1, tarefas.size());
        assertEquals(tarefa2, tarefas.get(0));
        System.out.println(">> excluída com sucesso!");
    }

    @Test
    public void testeListarTarefas() {
        // Given
        gerenciador.adiciona(tarefa1);
        gerenciador.adiciona(tarefa2);
        System.out.println("Teste de listagem de tarefas: Listando tarefas...");

        // When
        List<Tarefa> tarefas = gerenciador.getListaTarefas();

        // Then
        assertEquals(2, tarefas.size());
        assertTrue(tarefas.contains(tarefa1));
        assertTrue(tarefas.contains(tarefa2));
        System.out.println(">> listadas com sucesso!");
    }
}
