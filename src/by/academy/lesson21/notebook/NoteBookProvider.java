package by.academy.lesson21.notebook;

import by.academy.lesson21.notebook.base.Note;
import by.academy.lesson21.notebook.base.NoteBook;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class NoteBookProvider {
    private static final NoteBookProvider provider = new NoteBookProvider();
    private static final NoteBook notebook = new NoteBook();
    private String filepath = "base.txt";

    public static NoteBookProvider getProvider() {
        return provider;
    }

    public boolean hasFilepath() {
        return !filepath.isEmpty();
    }

    public boolean readFrom(String filepath) throws IOException {
        if (!filepath.isEmpty()) {
            this.filepath = filepath;
        }
        try (RandomAccessFile reader = new RandomAccessFile(this.filepath, "r")) {
            String line = reader.readLine();

            String header;
            String body;
            String date;

            while (line != null) {
                header = line;
                body = reader.readLine();
                date = reader.readLine();
                notebook.addNote(new Note(header, body, LocalDateTime.parse(date)));

                line = reader.readLine();
            }
        };

        return true;
    }

    public boolean save() throws IOException {
        try (RandomAccessFile writer = new RandomAccessFile(filepath, "rw")) {
            writer.setLength(0);
            for (Note note: notebook.getNotes()){
                writer.writeChars(note.getHeader() + "\n");
                writer.writeChars(note.getText() + "\n");
                writer.writeChars(note.getCreationDate() + "\n");
            }
        }

        return true;
    }

    public boolean addNote(Note note) {
        return notebook.addNote(note);
    }

    public boolean isEmpty() {
        return notebook.isEmpty();
    }

    public ArrayList<Note> findByHeader(String header) {
        ArrayList<Note> result = new ArrayList<>();
        for (Note note: notebook.getNotes()) {
            if (note.getHeader().equals(header)) {
                result.add(note);
            }
        }
        return result;
    }

    public ArrayList<Note> findByDate(LocalDateTime date) {
        ArrayList<Note> result = new ArrayList<>();
        for (Note note: notebook.getNotes()) {
            if (note.getCreationDate().toLocalDate().isEqual(date.toLocalDate())) {
                result.add(note);
            }
        }
        return result;
    }

}
