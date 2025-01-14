package by.academy.lesson21.notebook.dao;

import by.academy.lesson21.notebook.entity.Note;

import java.io.IOException;
import java.util.List;

public interface NoteBookDao {

    void add(Note note);

    void delete(int index);

    List<Note> allNotes();

    void save() throws IOException;

}
