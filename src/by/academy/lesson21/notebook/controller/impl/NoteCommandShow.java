package by.academy.lesson21.notebook.controller.impl;

import by.academy.lesson21.notebook.controller.Command;
import by.academy.lesson21.notebook.entity.Note;
import by.academy.lesson21.notebook.logic.LogicProvider;
import by.academy.lesson21.notebook.logic.NotebookLogic;

import java.util.List;

public class NoteCommandShow implements Command {

    private final LogicProvider logicProvider = LogicProvider.getInstance();
    private final NotebookLogic logic = logicProvider.getNotebookLogic();

    @Override
    public String execute(String[] params) {
        List<Note> notes;

        notes = logic.getAllNotes();

        if (!notes.isEmpty()) {
            return notes.toString().replaceAll(", Note", "\n")
                    .replaceAll("Note", "");
        } else {
            return "Пусто.";
        }
    }
}
