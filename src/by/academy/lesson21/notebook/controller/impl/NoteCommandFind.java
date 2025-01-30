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
            throw new CommandException("Неверное количество параметров.");
        }

        String text = params[1];
        String date = params[2];
        String result;

        if (text.isEmpty()) {
            if (!Validator.isValidDate(date)) {
                throw new CommandException("Неправильно указана дата.");
            }

            result = logic.find(LocalDate.parse(date));
        }

        result = logic.find(text);

        return result.isEmpty()? "Ничего не найдено.": result;
    }
}
