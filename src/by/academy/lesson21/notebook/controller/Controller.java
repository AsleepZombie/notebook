package by.academy.lesson21.notebook.controller;

import by.academy.lesson21.notebook.util.CommandUtil;

public class Controller {

    private final CommandProvider provider = new CommandProvider();

    public String readAndExecute(String request) {
        String commandName;
        Command executionCommand;

        commandName = request.substring(0, request.indexOf(CommandUtil.COMMAND_DELIMITER));
        executionCommand = provider.getCommand(commandName.toUpperCase());

        String response;
        try {
            response = executionCommand.execute(request.split(String.valueOf(CommandUtil.COMMAND_DELIMITER)));
        } catch (CommandException e) {
            response = e.getMessage().split(": ")[1];
        }

        return response;
    }

}
