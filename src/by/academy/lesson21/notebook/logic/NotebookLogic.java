package by.academy.lesson21.notebook.logic;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface NotebookLogic {

    public void add(String header, String text, LocalDateTime creationDate) throws NotebookLogicException;

    public void updateByIndex(int index, String header, String text) throws NotebookLogicException;

    public void delete(int index) throws NotebookLogicException;

    public String find(String text);

    public String find(LocalDate date);

    public String getAllNotes() throws NotebookLogicException;

    public void save() throws NotebookLogicException;
}
