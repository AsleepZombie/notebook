package by.academy.lesson21.notebook.controller.impl;

import by.academy.lesson21.notebook.controller.Command;
import by.academy.lesson21.notebook.logic.LogicProvider;
import by.academy.lesson21.notebook.logic.NotebookLogic;

public class NoteCommandUpdate implements Command {
    private final LogicProvider logicProvider = LogicProvider.getInstance();
    private final NotebookLogic logic = logicProvider.getNotebookLogic();

    @Override
    public String execute(String[] params) {
        if (params.length < 4) {
            return "Неверное количество параметров.";
        }
        String number = params[1];
        String text = params[2];
        String header = params[3];
        logic.updateByIndex(Integer.parseInt(params[1]), params[3], params[2]);
    }

}
