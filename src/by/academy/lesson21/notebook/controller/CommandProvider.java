package by.academy.lesson21.notebook.controller;

import by.academy.lesson21.notebook.controller.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();

    CommandProvider(){
        repository.put(CommandName.ADD, new NoteCommandAdd());
        repository.put(CommandName.DELETE, new NoteCommandDelete());
        repository.put(CommandName.FIND_TEXT, new NoteCommandFindText());
        repository.put(CommandName.FIND_BY_DATE, new NoteCommandFindByDate());
        repository.put(CommandName.SHOW, new NoteCommandShow());
        repository.put(CommandName.UPDATE, new NoteCommandUpdate());
        repository.put(CommandName.SAVE, new NoteCommandSave());

        repository.put(CommandName.NONE, new NoteCommandNone());
    }

    Command getCommand(String name){
        CommandName commandName;
        Command command;


        try{
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        }catch(IllegalArgumentException | NullPointerException e){
            //write log
            command = repository.get(CommandName.NONE);
        }
        return command;
    }

}
