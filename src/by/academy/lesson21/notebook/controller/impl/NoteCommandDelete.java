package by.academy.lesson21.notebook.controller.impl;

import by.academy.lesson21.notebook.controller.Command;
import by.academy.lesson21.notebook.controller.CommandException;
import by.academy.lesson21.notebook.logic.LogicProvider;
import by.academy.lesson21.notebook.logic.NotebookLogic;
import by.academy.lesson21.notebook.util.Validator;

public class NoteCommandDelete implements Command {

    private final LogicProvider logicProvider = LogicProvider.getInstance();
    private final NotebookLogic logic = logicProvider.getNotebookLogic();

    @Override
    public String execute(String[] params) throws CommandException {
        if (params.length < 2) {
            return "Неверное количество параметров.";
        }
        String number = params[1];
        int indexNumber = Validator.ValidateIndex(number);
        if (indexNumber == Validator.WRONG_INDEX) {
            return "Неправильно указан индекс.";
        }
        int index = indexNumber -1;
        return logic.delete(index);
    }
}
