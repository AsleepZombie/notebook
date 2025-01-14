package by.academy.lesson21.notebook.controller.impl;

import by.academy.lesson21.notebook.controller.Command;
import by.academy.lesson21.notebook.logic.LogicProvider;
import by.academy.lesson21.notebook.logic.NotebookLogic;

import java.io.IOException;
import java.time.LocalDateTime;

public class NoteCommandDelete implements Command {

    private final LogicProvider logicProvider = LogicProvider.getInstance();
    private final NotebookLogic logic = logicProvider.getNotebookLogic();

    @Override
    public String execute(String[] params) {

        if (params.length > 1) {
            logic.delete(Integer.parseInt(params[1]));
        } else {
            return "Неверное количество параметров.";
        }

        try {
            logic.save();
        } catch (IOException e) {
            e.printStackTrace();
            return "Запись не удалена.";
        }

        return "Запись удалена успешно.";
    }
}
