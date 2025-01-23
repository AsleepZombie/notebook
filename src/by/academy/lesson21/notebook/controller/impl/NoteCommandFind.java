package by.academy.lesson21.notebook.controller.impl;

import by.academy.lesson21.notebook.controller.Command;
import by.academy.lesson21.notebook.controller.CommandException;
import by.academy.lesson21.notebook.logic.LogicProvider;
import by.academy.lesson21.notebook.logic.NotebookLogic;
import by.academy.lesson21.notebook.util.ParamHelper;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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
            if (!ParamHelper.DATE_PATTERN.matcher(date).matches()) {
                return "Неверный формат.";
            }
            try {
                return logic.find(LocalDate.parse(date));
            }
            catch (DateTimeParseException e) {
                throw new CommandException("Неправильно указана дата.", e);
            }
        }
        return logic.find(text);
    }
}
