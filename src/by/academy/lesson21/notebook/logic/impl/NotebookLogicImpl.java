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
    public void add(String header, String text, LocalDateTime creationDate) throws NotebookLogicException {
        Note note;

        note = new Note(header, text, creationDate);
        try {
            dao.add(note);
        } catch (NoteBookException e) {
            throw new NotebookLogicException(e);
        }
    }

    @Override
    public void updateByIndex(int index, String header, String text) throws NotebookLogicException {
        List<Note> notes;
        Note note;

        try {
            notes = dao.allNotes();
        } catch (NoteBookException e) {
            throw new NotebookLogicException(e);
        }

        if (index + 1 > notes.size()) {
            //throw new NotebookLogicException("Неправильно указан номер.");
            throw new NotebookLogicException("Wrong number.");
        }
        note = notes.get(index);
        note.setHeader(header);
        note.setText(text);
    }

    @Override
    public void delete(int index) throws NotebookLogicException {
        List<Note> notes;

        try {
            notes = dao.allNotes();

            if (index + 1 > notes.size()) {
                //throw new NotebookLogicException("Неправильно указан номер.");
                throw new NotebookLogicException("Wrong number.");
            }

            dao.delete(index);
        } catch (NoteBookException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String find(String text) throws NotebookLogicException {
        List<Note> result = new ArrayList<>();
        List<Note> notes;

        try {
            notes = dao.allNotes();
        } catch (NoteBookException e) {
            throw new NotebookLogicException(e);
        }

        if (notes.isEmpty()) {
            //return "Блокнот не имеет записей";
            return "";
        }

        for(Note note : notes) {
            if(isTextInNote(note, text)) {
                result.add(note);
            }
        }

        //return result.isEmpty()? "Ничего не найдено.": noteToString(result);
        return result.isEmpty()? "": noteToString(result);
    }

    @Override
    public String find(LocalDate date) throws NotebookLogicException {
        List<Note> result = new ArrayList<>();
        List<Note> notes;

        try {
            notes = dao.allNotes();
        } catch (NoteBookException e) {
            throw new NotebookLogicException(e);
        }

        if (notes.isEmpty()) {
            //return "Блокнот не имеет записей";
            return "";
        }

        for (Note note : notes) {
            if (note.getCreationDate().toLocalDate().isEqual(date)) {
                result.add(note);
            }
        }

        //return result.isEmpty()? "Ничего не найдено.": noteToString(result);
        return result.isEmpty()? "": noteToString(result);
    }

    @Override
    public String getAllNotes() throws NotebookLogicException {
        List<Note> notes;

        try {
            notes = dao.allNotes();
        } catch (NoteBookException e) {
            throw new NotebookLogicException(e);
        }

        if (notes.isEmpty()) {
            //throw new NotebookLogicException("Блокнот не имеет записей");
            throw new NotebookLogicException("No records");
        }

        return noteToString(notes);
    }

    @Override
    public void save() throws NotebookLogicException {
        try {
            dao.save();
        } catch (NoteBookException e) {
            throw new NotebookLogicException(e);
        }
    }

    private boolean isTextInNote(Note note, String text) {
        return note.getHeader().contains(text) || note.getText().contains(text);
    }

    private String noteToString(List<Note> notes) {
        return notes.toString().replaceAll(", Note", "\n")
                .replaceAll("Note", "");
    }

}
