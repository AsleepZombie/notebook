package by.academy.lesson21.notebook.logic.impl;

import by.academy.lesson21.notebook.dao.DaoProvider;
import by.academy.lesson21.notebook.dao.NoteBookDao;
import by.academy.lesson21.notebook.dao.NoteBookException;
import by.academy.lesson21.notebook.entity.Note;
import by.academy.lesson21.notebook.logic.NotebookLogic;
import by.academy.lesson21.notebook.logic.NotebookLogicException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NotebookLogicImpl implements NotebookLogic {
    private final DaoProvider provider = DaoProvider.getInstance();
    private final NoteBookDao dao = provider.getNoteBookDao();

    public NotebookLogicImpl() {
    }

    @Override
    public String add(String header, String text, LocalDateTime creationDate) {
        Note note;

        note = new Note(header, text, creationDate);
        dao.add(note);

        return "Запись успешно добавлена.";
    }

    @Override
    public String updateByIndex(int index, String header, String text) {
        Note note;

        if (index + 1 > dao.allNotes().size()) {
            return "Неправильно указан номер.";
        }
        note = dao.allNotes().get(index);
        note.setHeader(header);
        note.setText(text);

        return "Запись успешно обновлена";
    }

    @Override
    public String delete(int index) {
        if (index + 1 > dao.allNotes().size()) {
            return "Неправильно указан номер.";
        }
            dao.delete(index);
        return "Запись успешно удалена";
    }

    @Override
    public String find(String text) {
        List<Note> result = new ArrayList<>();
        List<Note> notes = dao.allNotes();

        if (notes.isEmpty()) {
            return "Блокнот не имеет записей";
        }

        for(Note note : notes) {
            if(isTextInNote(note, text)) {
                result.add(note);
            }
        }

        return result.isEmpty()? "Ничего не найдено.": noteToString(result);
    }

    @Override
    public String find(LocalDate date) {
        List<Note> result = new ArrayList<>();
        List<Note> notes = dao.allNotes();

        if (notes.isEmpty()) {
            return "Блокнот не имеет записей";
        }

        for (Note note : notes) {
            if (note.getCreationDate().toLocalDate().isEqual(date)) {
                result.add(note);
            }
        }

        return result.isEmpty()? "Ничего не найдено.": noteToString(result);
    }

    @Override
    public String getAllNotes() {
        List<Note> notes = dao.allNotes();

        if (notes.isEmpty()) {
            return "Блокнот не имеет записей";
        }

        return noteToString(notes);
    }

    @Override
    public String save() throws NotebookLogicException {
        try {
            dao.save();
        } catch (NoteBookException e) {
            throw new NotebookLogicException(e);
        }

        return "Изменения сохранены.";
    }

    private boolean isTextInNote(Note note, String text) {
        return note.getHeader().contains(text) || note.getText().contains(text);
    }

    private String noteToString(List<Note> notes) {
        return notes.toString().replaceAll(", Note", "\n")
                .replaceAll("Note", "");
    }

}
