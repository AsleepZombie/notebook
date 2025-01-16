package by.academy.lesson21.notebook.logic.impl;

import by.academy.lesson21.notebook.dao.DaoProvider;
import by.academy.lesson21.notebook.dao.NoteBookDao;
import by.academy.lesson21.notebook.entity.Note;
import by.academy.lesson21.notebook.logic.NotebookLogic;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class NotebookLogicImpl implements NotebookLogic {
    private final DaoProvider provider = DaoProvider.getInstance();
    private final NoteBookDao dao = provider.getNoteBookDao();

    @Override
    public String add(String header, String text, String date) {
        LocalDateTime creationDate;
        Note note;

        try {
            creationDate = LocalDateTime.parse(date);
        } catch (DateTimeParseException e) {
            return "Неверно указана дата.";
        }

        note = new Note(header, text, creationDate);
        if (dao.add(note)) {
            return dao.save()? "Запись успешно добавлена": dao.getActionWarning();
        } else {
            return "Не удалось добавить запись.";
        }

    }

    @Override
    public String updateByIndex(String number, String header, String text) {
        int index = 0;
        Note note;

        try {
            index = Integer.parseInt(number) - 1;
            note = dao.allNotes().get(index);
        } catch (NumberFormatException e) {
            return "Указан неправильный номер.";
        } catch (IndexOutOfBoundsException e) {
            return "Неправильно указан номер.";
        }
            note.setHeader(header);
            note.setText(text);
            return "Запись успешно обновлена";
    }

    @Override
    public String delete(String number) {
        int index = 0;
        try {
            index = Integer.parseInt(number) - 1;
        } catch (NumberFormatException e) {
            return "Неправильно указан номер.";
        }
        return dao.delete(index)? "Запись успешно удалена": dao.getActionWarning();
    }

    @Override
    public String find(String text){
        List<Note> result = new ArrayList<>();
        List<Note> notes = dao.allNotes();

        for(Note note : notes) {
            if(isTextInNote(note, text)) {
                result.add(note);
            }
        }

        return result;
    }

    @Override
    public String find(LocalDate date){
        List<Note> result = new ArrayList<>();
        List<Note> notes = dao.allNotes();

        for (Note note : notes) {
            if (note.getCreationDate().toLocalDate().isEqual(date)) {
                result.add(note);
            }
        }

        return result;
    }

    @Override
    public String getAllNotes() {
        return dao.allNotes();
    }

    @Override
    public String save() {
        return dao.save()? "Изменения сохранены": dao.getActionWarning();
    }

    private boolean isTextInNote(Note note, String text) {
        return note.getHeader().contains(text) || note.getText().contains(text);
    }

}
