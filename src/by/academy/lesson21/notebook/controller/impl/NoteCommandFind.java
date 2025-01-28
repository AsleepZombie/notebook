package by.academy.lesson21.notebook.controller.impl;

import by.academy.lesson21.notebook.controller.Command;
import by.academy.lesson21.notebook.controller.CommandException;
import by.academy.lesson21.notebook.logic.LogicProvider;
import by.academy.lesson21.notebook.logic.NotebookLogic;
import by.academy.lesson21.notebook.util.Validator;

import java.time.LocalDate;

public class NoteCommandFind implements Command {

    private final LogicProvider logicProvider = LogicProvider.getInstance();
    private final NotebookLogic logic = logicProvider.getNotebookLogic();

    @Override
    public String execute(String[] params) throws CommandException {
        if (params.length <= 2) {
            return "Неверное количество параметров.";
        }

        String text = params[1];
        String date = params[2];

        if (text.isEmpty()) {
            if (!Validator.isValidDate(date)) {
                return "Неправильно указана дата.";
            }

            return logic.find(LocalDate.parse(date));
        }

        return logic.find(text);
    }
}
