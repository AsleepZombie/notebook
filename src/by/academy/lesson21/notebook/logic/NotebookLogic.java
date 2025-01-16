package by.academy.lesson21.notebook.logic;

import java.time.LocalDate;

public interface NotebookLogic {

    public String add(String header, String text, String date);

    public String updateByIndex(String number, String header, String text);

    public String delete(String number);

    public String find(String text);

    public String find(LocalDate date);

    public String getAllNotes();

    public String save();
}
