package by.academy.lesson21.notebook.dao;

import by.academy.lesson21.notebook.entity.Note;

import java.util.List;

public interface NoteBookDao {

    void add(Note note) throws NoteBookException;

    void delete(int index) throws NoteBookException;

    List<Note> allNotes();

    void read() throws NoteBookException;

    void save() throws NoteBookException;

}
