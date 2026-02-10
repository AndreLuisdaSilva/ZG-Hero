package gradle.java;

import gradle.java.controller.TarefaController;
import gradle.java.service.GerenciadorDeTarefas;
import gradle.java.view.TarefaView;

public class Main {

    public static void main(String[] args) {
        GerenciadorDeTarefas model = new GerenciadorDeTarefas();
        TarefaView view = new TarefaView();
        TarefaController controller = new TarefaController(model, view);

        controller.iniciar();
    }
}
