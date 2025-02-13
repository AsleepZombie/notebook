package by.academy.lesson21.notebook.controller.impl;

import by.academy.lesson21.notebook.controller.Command;
import by.academy.lesson21.notebook.controller.CommandException;
import by.academy.lesson21.notebook.logic.LogicProvider;
import by.academy.lesson21.notebook.logic.NotebookLogic;
import by.academy.lesson21.notebook.logic.NotebookLogicException;

public class NoteCommandFindText implements Command {

    private final LogicProvider logicProvider = LogicProvider.getInstance();
    private final NotebookLogic logic = logicProvider.getNotebookLogic();

    @Override
    public String execute(String[] params) throws CommandException {
        if (params.length <= 1) {
            //throw new CommandException("Неверное количество параметров.");
            throw new CommandException("Wrong param length.");
        }

        String text = params[1];
        String result;

        if (text.isEmpty()) {
            throw new CommandException("Wrong search text.");
        }

        try {
            result = logic.find(text);
        } catch (NotebookLogicException e) {
            throw new CommandException(e);
        }

        return result.isEmpty()? "Ничего не найдено.": result;
    }
}
