package by.academy.lesson21.notebook.logic;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface NotebookLogic {

    public String add(String header, String text, LocalDateTime creationDate) throws NotebookLogicException;

    public String updateByIndex(String number, String header, String text) throws NotebookLogicException;

    public String delete(String number) throws NotebookLogicException;

    public String find(String text);

    public String find(LocalDate date);

    public String getAllNotes();

    public String read() throws NotebookLogicException;

    public String save() throws NotebookLogicException;
}
