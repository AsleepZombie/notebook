package by.academy.lesson21.notebook.controller;

import by.academy.lesson21.notebook.util.ParamHelper;

public class Controller {

    private final CommandProvider provider = new CommandProvider();

    public String readAndExecute(String request) {
        String commandName;
        Command executionCommand;

        commandName = request.substring(0, request.indexOf(ParamHelper.delimiter));
        executionCommand = provider.getCommand(commandName.toUpperCase());

        String response;
        response = executionCommand.execute(request.split("\n"));

        return response;
    }

}
