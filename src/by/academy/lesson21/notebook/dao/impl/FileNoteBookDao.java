package by.academy.lesson21.notebook.dao.impl;

import by.academy.lesson21.notebook.dao.NoteBookDao;
import by.academy.lesson21.notebook.dao.NoteBookException;
import by.academy.lesson21.notebook.entity.Note;
import by.academy.lesson21.notebook.util.FileRequest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FileNoteBookDao implements NoteBookDao {
    private final List<Note> notes;

    public FileNoteBookDao() throws NoteBookException {
        notes = new ArrayList<>();
        read();
    }

    @Override
    public void add(Note note) {
        notes.add(note);
    }

    @Override
    public void delete(int index) {
        notes.remove(index);
    }

    public void update(int index, Note note) {
        notes.set(index, note);
    }

    @Override
    public List<Note> allNotes() {
        return notes;
    }

    public void read() throws NoteBookException {
        try (RandomAccessFile reader = new RandomAccessFile(FileRequest.filePath, "r")) {
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
        } catch (FileNotFoundException e) {
            throw new NoteBookException("Файл не найден", e);
        } catch (IOException e) {
            throw new NoteBookException("Что-то пошло не так", e);
        }
    }

    @Override
    public void save() throws NoteBookException {
        int counter = 0;
        try (RandomAccessFile writer = new RandomAccessFile(FileRequest.filePath, "rw")) {
            writer.setLength(0);
            for (Note note: notes){
                writer.writeBytes((counter++ == 0 ? "" : "\n") + note.getId());
                writer.writeBytes("\n" + note.getHeader());
                writer.writeBytes("\n" + note.getText());
                writer.writeBytes("\n" + note.getCreationDate());
            }
        }catch (FileNotFoundException e) {
            throw new NoteBookException("Файла больше не существует, создайте заново.", e);
        } catch (IOException e) {
            throw new NoteBookException("Что-то пошло не так", e);
        }
    }
}
