package com.example.demo.exerciciosjava.K1T3Java;

import com.example.demo.exerciciosjava.K1T3Java.controller.TarefaController;
import com.example.demo.exerciciosjava.K1T3Java.service.GerenciadorDeTarefas;
import com.example.demo.exerciciosjava.K1T3Java.view.TarefaView;

public class Main {

    public static void main(String[] args) {
        GerenciadorDeTarefas model = new GerenciadorDeTarefas();
        TarefaView view = new TarefaView();
        TarefaController controller = new TarefaController(model, view);

        controller.iniciar();
    }
}
