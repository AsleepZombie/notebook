package by.academy.lesson21.notebook.controller.impl;

import by.academy.lesson21.notebook.controller.Command;
import by.academy.lesson21.notebook.controller.CommandException;
import by.academy.lesson21.notebook.logic.LogicProvider;
import by.academy.lesson21.notebook.logic.NotebookLogic;
import by.academy.lesson21.notebook.logic.NotebookLogicException;

public class NoteCommandUpdate implements Command {
    private final LogicProvider logicProvider = LogicProvider.getInstance();
    private final NotebookLogic logic = logicProvider.getNotebookLogic();

    @Override
    public String execute(String[] params) throws CommandException {
        if (params.length < 4) {
            return "Неверное количество параметров.";
        }
        String number = params[1];
        String text = params[2];
        String header = params[3];

        try {
            return logic.updateByIndex(number, header, text);
        } catch (NotebookLogicException e) {
            throw new CommandException(e);
        }
    }

}
