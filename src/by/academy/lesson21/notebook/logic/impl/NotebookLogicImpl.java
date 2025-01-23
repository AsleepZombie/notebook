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
    private boolean isDataRead = false;

    @Override
    public String add(String header, String text, LocalDateTime creationDate) throws NotebookLogicException {
        Note note;

        try {
            note = new Note(header, text, creationDate);
            dao.add(note);
            //dao.save();
        } catch (NoteBookException e) {
            throw new NotebookLogicException(e);
        }

        //return "Не удалось добавить запись.";
        return "Запись успешно добавлена.";
    }

    @Override
    public String updateByIndex(String number, String header, String text) throws NotebookLogicException {
        int index = 0;
        Note note;

        try {
            index = Integer.parseInt(number) - 1;
            note = dao.allNotes().get(index);
        } catch (NumberFormatException e) {
            throw new NotebookLogicException("Указан неправильный номер.", e);
        } catch (IndexOutOfBoundsException e) {
            throw new NotebookLogicException("Неправильно указан номер.", e);
        }
        note.setHeader(header);
        note.setText(text);
        //dao.save();
        return "Запись успешно обновлена";
    }

    @Override
    public String delete(String number) throws NotebookLogicException {
        int index = 0;
        try {
            index = Integer.parseInt(number) - 1;
            dao.delete(index);
        } catch (NumberFormatException e) {
            throw new NotebookLogicException("Неправильно указан номер.", e);
        } catch (NoteBookException e) {
            throw new NotebookLogicException(e);
        }
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
    public String read() throws NotebookLogicException {
        if (!isDataRead) {
            try {
                dao.read();
                isDataRead = true;
            } catch (NoteBookException e) {
                throw new NotebookLogicException(e);
            }
        }

        return noteToString(dao.allNotes());
    }

    @Override
    public String save() throws NotebookLogicException {
        if (!isDataRead){
            return "Сохранять нечего.";
        }

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
