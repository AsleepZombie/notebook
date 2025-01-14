package by.academy.lesson21.notebook.controller.impl;

import by.academy.lesson21.notebook.controller.Command;
import by.academy.lesson21.notebook.logic.LogicProvider;
import by.academy.lesson21.notebook.logic.NotebookLogic;

import java.io.IOException;

public class NoteCommandUpdate implements Command {
    private final LogicProvider logicProvider = LogicProvider.getInstance();
    private final NotebookLogic logic = logicProvider.getNotebookLogic();

    @Override
    public String execute(String[] params) {

        switch (params.length) {
            case 3:
                logic.updateByIndex(Integer.parseInt(params[1]), params[2]);
                break;
            case 4:
                logic.updateByIndex(Integer.parseInt(params[1]), params[3], params[2]);
                break;
            default:
                return "Неверное количество параметров.";
        }
        try {
            logic.save();
        } catch (IOException e) {
            e.printStackTrace();
            return "Запись не обновлена.";
        }

        return "Запись обновлена успешно.";
    }

}
