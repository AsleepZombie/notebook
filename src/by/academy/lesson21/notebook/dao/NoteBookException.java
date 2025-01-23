package by.academy.lesson21.notebook.dao;

public class NoteBookException extends Exception{

    public NoteBookException() {
    }

    public NoteBookException(String message) {
        super(message);
    }

    public NoteBookException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoteBookException(Throwable cause) {
        super(cause);
    }
}
