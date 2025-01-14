package by.academy.lesson21.notebook.controller.impl;

import by.academy.lesson21.notebook.controller.Command;
import by.academy.lesson21.notebook.entity.Note;
import by.academy.lesson21.notebook.logic.LogicProvider;
import by.academy.lesson21.notebook.logic.NotebookLogic;

import java.time.LocalDate;
import java.util.List;

public class NoteCommandFind implements Command {

    private final LogicProvider logicProvider = LogicProvider.getInstance();
    private final NotebookLogic logic = logicProvider.getNotebookLogic();

    @Override
    public String execute(String[] params) {
        List<Note> notes;

        if (params.length > 2) {
            if (params[1].isEmpty()) {
                notes = logic.find(params[1]);
            } else {
                notes = logic.find(LocalDate.parse(params[2]));
            }
        } else {
            return "Неверное количество параметров.";
        }
        if (!notes.isEmpty()) {
            return notes.toString();
        } else {
            return "Ничего не найдено.";
        }
    }
}
