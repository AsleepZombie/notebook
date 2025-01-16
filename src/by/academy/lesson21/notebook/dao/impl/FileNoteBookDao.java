package by.academy.lesson21.notebook.dao.impl;

import by.academy.lesson21.notebook.dao.NoteBookDao;
import by.academy.lesson21.notebook.entity.Note;
import by.academy.lesson21.notebook.util.ParamHelper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FileNoteBookDao implements NoteBookDao {
    private final List<Note> notes = new ArrayList<>();
    private String contentWarning;
    private String actionWarning;

    public FileNoteBookDao() {
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
        } catch (FileNotFoundException e) {
            contentWarning = "Файл не найден";
        } catch (IOException e) {
            contentWarning = "Что-то пошло не так";
        }
    }

    @Override
    public boolean add(Note note) {
        return notes.add(note);
    }

    @Override
    public boolean delete(int index) {
        try {
            notes.remove(index);
        } catch (IndexOutOfBoundsException e) {
            actionWarning = "Неправильный номер";
            return false;
        } catch (RuntimeException e) {
            actionWarning = "Что-то пошло не так";
            return false;
        }
        return true;
    }


    public boolean update(int index, Note note) {
        try {
            notes.set(index, note);
        } catch (IndexOutOfBoundsException e) {
            actionWarning = "Неправильный номер";
            return false;
        } catch (RuntimeException e) {
            actionWarning = "Что-то пошло не так";
            return false;
        }
        return true;
    }

    @Override
    public List<Note> allNotes() {
        return notes;
    }

    @Override
    public boolean save() {
        int counter = 0;
        try (RandomAccessFile writer = new RandomAccessFile(ParamHelper.filePath, "rw")) {
            writer.setLength(0);
            for (Note note: notes){
                writer.writeBytes((counter++ == 0 ? "" : "\n") + note.getId());
                writer.writeBytes("\n" + note.getHeader());
                writer.writeBytes("\n" + note.getText());
                writer.writeBytes("\n" + note.getCreationDate());
            }
        }catch (FileNotFoundException e) {
            actionWarning = contentWarning = "Файла больше не существует, создайте заново.";
            return false;
        } catch (IOException e) {
            actionWarning = "Что-то пошло не так";
            return false;
        }
        return true;
    }

    @Override
    public boolean hasContentWarning() {
        return !contentWarning.isEmpty();
    }

    @Override
    public String getContentWarning() {
        return contentWarning;
    }

    @Override
    public String getActionWarning() {
        return actionWarning;
    }
}
