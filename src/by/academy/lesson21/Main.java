package by.academy.lesson21;

import by.academy.lesson21.notebook.NoteBookProvider;
import by.academy.lesson21.notebook.base.Note;

import java.io.IOException;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) throws IOException {

        NoteBookProvider.getProvider().addNote(new Note("TestHeader", "Test Body", LocalDateTime.now()));
        NoteBookProvider.getProvider().save();

    }
}