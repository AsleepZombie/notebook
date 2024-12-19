package by.academy.lesson21.notebook.base;

import java.time.LocalDateTime;
import java.util.Objects;

public class Note {
    private String header;
    private String text;
    private final LocalDateTime creationDate;

    public Note() {
        this.header = "";
        this.text = "";
        this.creationDate = LocalDateTime.now();
    }

    public Note(String header) {
        this.header = header;
        this.text = "";
        this.creationDate = LocalDateTime.now();
    }

    public Note(String header, String text) {
        this.header = header;
        this.text = text;
        this.creationDate = LocalDateTime.now();
    }

    public Note(String header, String text, LocalDateTime creationDate) {
        this.header = header;
        this.text = text;
        this.creationDate = creationDate;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    @Override
    public String toString() {
        return "Note{" +
                "header='" + header + '\'' +
                ", text='" + text + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return Objects.equals(header, note.header) && Objects.equals(text, note.text) && Objects.equals(creationDate, note.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, text, creationDate);
    }
}
