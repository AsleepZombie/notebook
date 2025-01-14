package by.academy.lesson21.notebook.logic.impl;

import by.academy.lesson21.notebook.dao.DaoProvider;
import by.academy.lesson21.notebook.dao.NoteBookDao;
import by.academy.lesson21.notebook.entity.Note;
import by.academy.lesson21.notebook.logic.NotebookLogic;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NotebookLogicImpl implements NotebookLogic {
    private final DaoProvider provider = DaoProvider.getInstance();
    private final NoteBookDao dao = provider.getNoteBookDao();

    @Override
    public void add(Note note) {
        dao.add(note);
    }

    @Override
    public void add(String text) {
        Note note = new Note(text);
        dao.add(note);
    }

    @Override
    public void add(String header, String text) {
        Note note = new Note(header, text);
        dao.add(note);
    }

    @Override
    public void add(String header, String text, LocalDateTime creationDate) {
        Note note = new Note(header, text, creationDate);
        dao.add(note);
    }

    @Override
    public void updateById(String id, String text) {
        List<Note> notes = dao.allNotes();
        for (Note noteToUpdate : notes) {
            if (noteToUpdate.getId().equals(id)) {
                dao.delete(notes.indexOf(noteToUpdate));
                noteToUpdate.setText(text);
                dao.add(noteToUpdate);
            }
        }
    }

    @Override
    public void updateById(String id, String header, String text) {
        List<Note> notes = dao.allNotes();
        for (Note noteToUpdate : notes) {
            if (noteToUpdate.getId().equals(id)) {
                dao.delete(notes.indexOf(noteToUpdate));
                noteToUpdate.setHeader(header);
                noteToUpdate.setText(text);
                dao.add(noteToUpdate);
            }
        }
    }

    @Override
    public void delete(String id) {
        List<Note> notes = dao.allNotes();
        for (Note noteToUpdate : notes) {
            if (noteToUpdate.getId().equals(id)) {
                dao.delete(notes.indexOf(noteToUpdate));
            }
        }
    }

    @Override
    public List<Note> find(String text){
        List<Note> result = new ArrayList<>();
        List<Note> notes = dao.allNotes();

        for(Note note : notes) {
            if(isTextInNote(note, text)) {
                result.add(note);
            }
        }

        return result;
    }

    @Override
    public List<Note> find(LocalDate date){
        List<Note> result = new ArrayList<>();
        List<Note> notes = dao.allNotes();

        for (Note note : notes) {
            if (note.getCreationDate().toLocalDate().isEqual(date)) {
                result.add(note);
            }
        }

        return result;
    }

    @Override
    public List<Note> getAllNotes() {
        return dao.allNotes();
    }

    @Override
    public void save() throws IOException {
        dao.save();
    }

    private boolean isTextInNote(Note note, String text) {
        return note.getHeader().contains(text) || note.getText().contains(text);
    }

}
