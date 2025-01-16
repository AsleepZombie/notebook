package by.academy.lesson21.notebook.controller.impl;

import by.academy.lesson21.notebook.controller.Command;
import by.academy.lesson21.notebook.logic.LogicProvider;
import by.academy.lesson21.notebook.logic.NotebookLogic;

public class NoteCommandAdd implements Command {

    private final LogicProvider logicProvider = LogicProvider.getInstance();
    private final NotebookLogic logic = logicProvider.getNotebookLogic();

    @Override
    public String execute(String[] params) {
        if (params.length < 4) {
            return "Неверное количество параметров.";
        }
        String text = params[1];
        String header = params[2];
        String date = params[3];

        return logic.add(header, text, date);
    }

}
