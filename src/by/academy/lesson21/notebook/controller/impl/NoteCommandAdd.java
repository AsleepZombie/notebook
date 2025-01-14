package by.academy.lesson21.notebook.controller.impl;

import by.academy.lesson21.notebook.controller.Command;
import by.academy.lesson21.notebook.logic.LogicProvider;
import by.academy.lesson21.notebook.logic.NotebookLogic;

import java.io.IOException;
import java.time.LocalDateTime;

public class NoteCommandAdd implements Command {

    private final LogicProvider logicProvider = LogicProvider.getInstance();
    private final NotebookLogic logic = logicProvider.getNotebookLogic();

    @Override
    public String execute(String[] params) {

        switch (params.length) {
            case 2:
                logic.add(params[1]);
                break;
            case 3:
                logic.add(params[2], params[1]);
                break;
            case 4:
                logic.add(params[2], params[1], LocalDateTime.parse(params[3]));
                break;
            default:
                return "Неверное количество параметров.";
        }
        try {
            logic.save();
        } catch (IOException e) {
            e.printStackTrace();
            return "Запись не добавлена.";
        }

        return "Запись сохранена успешно.";
    }

}
