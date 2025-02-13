package by.academy.lesson21.notebook.controller.impl;

import by.academy.lesson21.notebook.controller.Command;
import by.academy.lesson21.notebook.controller.CommandException;
import by.academy.lesson21.notebook.logic.LogicProvider;
import by.academy.lesson21.notebook.logic.NotebookLogic;
import by.academy.lesson21.notebook.logic.NotebookLogicException;
import by.academy.lesson21.notebook.util.Validator;

import java.time.LocalDate;

public class NoteCommandFindByDate implements Command {

    private final LogicProvider logicProvider = LogicProvider.getInstance();
    private final NotebookLogic logic = logicProvider.getNotebookLogic();

    @Override
    public String execute(String[] params) throws CommandException {
        if (params.length <= 1) {
            //throw new CommandException("Неверное количество параметров.");
            throw new CommandException("Wrong param length.");
        }

        String date = params[1];
        String result;
        if (!Validator.isValidDate(date)) {
            //throw new CommandException("Неправильно указана дата.");
            throw new CommandException("Wrong date.");
        }
        try{
            result = logic.find(LocalDate.parse(date));
        } catch (NotebookLogicException e) {
            throw new CommandException(e);
        }

        return result.isEmpty()? "Ничего не найдено.": result;
    }
}
