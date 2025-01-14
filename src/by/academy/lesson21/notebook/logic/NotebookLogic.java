package by.academy.lesson21.notebook.logic;

import by.academy.lesson21.notebook.entity.Note;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface NotebookLogic {

    public void add(Note note);

    public void add(String text);

    public void add(String header, String text);

    public void add(String header, String text, LocalDateTime creationDate);

    public void updateById(String id, String text);

    public void updateById(String id, String header, String text);

    public void delete(String id);

    public List<Note> find(String text);

    public List<Note> find(LocalDate date);

    public List<Note> getAllNotes();

    public void save() throws IOException;
}
