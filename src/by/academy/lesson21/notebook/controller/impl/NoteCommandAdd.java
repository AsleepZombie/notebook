package by.academy.lesson21.notebook.controller.impl;

import by.academy.lesson21.notebook.controller.Command;
import by.academy.lesson21.notebook.controller.CommandException;
import by.academy.lesson21.notebook.logic.LogicProvider;
import by.academy.lesson21.notebook.logic.NotebookLogic;
import by.academy.lesson21.notebook.logic.NotebookLogicException;
import by.academy.lesson21.notebook.util.Validator;

import java.time.LocalDateTime;

public class NoteCommandAdd implements Command {

    private final LogicProvider logicProvider = LogicProvider.getInstance();
    private final NotebookLogic logic = logicProvider.getNotebookLogic();

    @Override
    public String execute(String[] params) throws CommandException {
        if (params.length < 3) {
            return "Неверное количество параметров.";
        }
        String text = Validator.correctText(params[1]);
        String header = Validator.correctHeader(params[2]);
        LocalDateTime date = LocalDateTime.now();

        try {
            return logic.add(header, text, date);
        } catch (NotebookLogicException e) {
            throw new CommandException(e);
        }
    }

}
