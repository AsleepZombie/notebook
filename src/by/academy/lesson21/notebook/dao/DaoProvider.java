package by.academy.lesson21.notebook.dao;

import by.academy.lesson21.notebook.dao.impl.FileNoteBookDao;

public final class DaoProvider {
    private static final DaoProvider INSTANCE = new DaoProvider();

    private DaoProvider() {}

    private final NoteBookDao noteBookDao = new FileNoteBookDao();

    public NoteBookDao getNoteBookDao() {
        return noteBookDao;
    }

    public static DaoProvider getInstance() {
        return INSTANCE;
    }


}
