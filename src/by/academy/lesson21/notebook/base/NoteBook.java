package by.academy.lesson21.notebook.base;

import java.util.ArrayList;
import java.util.Objects;

public class NoteBook {
    private final ArrayList<Note> notes;

    public NoteBook() {
        notes = new ArrayList<>();
    }

    public NoteBook(Note note) {
        notes = new ArrayList<>();
        notes.addFirst(note);
    }

    public NoteBook(ArrayList<Note> notes) {
        this.notes = notes;
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public boolean addNote(Note note) {
        return notes.add(note);
    }

    public boolean isEmpty() {
        return notes.isEmpty();
    }

    @Override
    public String toString() {
        return "NoteBook{" +
                "notes=" + notes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        NoteBook noteBook = (NoteBook) o;
        return Objects.equals(notes, noteBook.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(notes);
    }

}
