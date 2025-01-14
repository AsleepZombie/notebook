package by.academy.lesson21.notebook.entity;

import by.academy.lesson21.notebook.util.IdGenerator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Note {
    private final String id;
    private String header;
    private String text;
    private final LocalDateTime creationDate;

    public Note() {
        id = IdGenerator.getId();
        this.header = "";
        this.text = "";
        this.creationDate = LocalDateTime.now();
    }

    public Note(String text) {
        id = IdGenerator.getId();
        this.header = "";
        this.text = text;
        this.creationDate = LocalDateTime.now();
    }

    public Note(String header, String text) {
        id = IdGenerator.getId();
        this.header = header;
        this.text = text;
        this.creationDate = LocalDateTime.now();
    }

    public Note(String header, String text, LocalDateTime creationDate) {
        id = IdGenerator.getId();
        this.header = header;
        this.text = text;
        this.creationDate = creationDate;
    }

    public Note(String id, String header, String text, LocalDateTime creationDate) {
        this.id = id;
        this.header = header;
        this.text = text;
        this.creationDate = creationDate;
    }

    public String getId() {
        return id;
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
                ", creationDate=" + creationDate.format(DateTimeFormatter.ISO_LOCAL_DATE) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return Objects.equals(id, note.id) && Objects.equals(header, note.header) && Objects.equals(text, note.text) && Objects.equals(creationDate, note.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, header, text, creationDate);
    }
}
