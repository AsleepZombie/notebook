package by.academy.lesson21.notebook.dao.impl;

import by.academy.lesson21.notebook.dao.NoteBookDao;
import by.academy.lesson21.notebook.entity.Note;

import java.io.IOException;
import java.util.List;

public class FileNoteBookDao implements NoteBookDao {

    @Override
    public void add(Note note) {
        FileNoteBookSource.add(note);
    }

    @Override
    public void delete(int index) {
        FileNoteBookSource.delete(index);
    }

    @Override
    public List<Note> allNotes() {
        return FileNoteBookSource.getAll();
    }

    @Override
    public void save() throws IOException {
        FileNoteBookSource.save();
    }
}
