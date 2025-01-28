package by.academy.lesson21.notebook.logic;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface NotebookLogic {

    public String add(String header, String text, LocalDateTime creationDate) throws NotebookLogicException;

    public String updateByIndex(int index, String header, String text);

    public String delete(int index);

    public String find(String text);

    public String find(LocalDate date);

    public String getAllNotes();

    public String save() throws NotebookLogicException;
}
