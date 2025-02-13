package by.academy.lesson21.notebook.logic;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface NotebookLogic {

    void add(String header, String text, LocalDateTime creationDate) throws NotebookLogicException;

    void updateByIndex(int index, String header, String text) throws NotebookLogicException;

    void delete(int index) throws NotebookLogicException;

    String find(String text) throws NotebookLogicException;

    String find(LocalDate date) throws NotebookLogicException;

    String getAllNotes() throws NotebookLogicException;

    void save() throws NotebookLogicException;
}
