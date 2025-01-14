package by.academy.lesson21.notebook.controller.impl;

import by.academy.lesson21.notebook.controller.Command;

public class NoteCommandNone implements Command {

    @Override
    public String execute(String[] params) {
        return "Ошибка запроса";
    }

}
