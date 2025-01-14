package by.academy.lesson21.notebook.logic;

import by.academy.lesson21.notebook.logic.impl.NotebookLogicImpl;

public final class LogicProvider {
    private static final LogicProvider instance = new LogicProvider();

    private LogicProvider() {}

    private final NotebookLogic logic = new NotebookLogicImpl();

    public NotebookLogic getNotebookLogic() {
        return logic;
    }

    public static LogicProvider getInstance() {
        return instance;
    }
}
