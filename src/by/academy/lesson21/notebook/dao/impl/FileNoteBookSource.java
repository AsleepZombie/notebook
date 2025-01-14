package by.academy.lesson21.notebook.dao.impl;

import by.academy.lesson21.notebook.entity.Note;
import by.academy.lesson21.notebook.util.ParamHelper;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FileNoteBookSource {
    private static final List<Note> notes = new ArrayList<>();

    static {
        try (RandomAccessFile reader = new RandomAccessFile(ParamHelper.filePath, "r")) {
            String line = reader.readLine();

            String id;
            String header;
            String body;
            String date;

            while (line != null) {
                id = line;
                header = reader.readLine();
                body = reader.readLine();
                date = reader.readLine();
                notes.add(new Note(id, header, body, LocalDateTime.parse(date)));

                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void add(Note note) {
        notes.add(note);
    }

    public static void delete(int index) {
        notes.remove(index);
    }

    public static void update(int index, Note note) {
        notes.set(index, note);
    }

    public static List<Note> getAll(){
        return notes;
    }

    public static int count() {
        return notes.size();
    }

    public static void save() throws IOException {
        int counter = 0;
        try (RandomAccessFile writer = new RandomAccessFile(ParamHelper.filePath, "rw")) {
            writer.setLength(0);
            for (Note note: notes){
                writer.writeBytes((counter++ == 0 ? "" : "\n") + note.getId());
                writer.writeBytes("\n" + note.getHeader());
                writer.writeBytes("\n" + note.getText());
                writer.writeBytes("\n" + note.getCreationDate());
            }
        }
    }
}
