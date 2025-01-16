package by.academy.lesson21.notebook.dao;

import by.academy.lesson21.notebook.entity.Note;

import java.util.List;

public interface NoteBookDao {

    boolean add(Note note);

    boolean delete(int index);

    List<Note> allNotes();

    boolean save();

    boolean hasContentWarning();

    String getContentWarning();

    String getActionWarning();

}
