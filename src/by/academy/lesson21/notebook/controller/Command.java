package by.academy.lesson21.notebook.controller;

public interface Command {
    String execute(String[] params) throws CommandException;
}
