package by.academy.lesson21.notebook.dao;

import by.academy.lesson21.notebook.dao.impl.FileNoteBookDao;

public final class DaoProvider {
    private static final DaoProvider INSTANCE;

    static {
        try {
            INSTANCE = new DaoProvider();
        } catch (NoteBookException e) {
            throw new RuntimeException(e);
        }
    }

    private DaoProvider() throws NoteBookException {}

    private final NoteBookDao noteBookDao = new FileNoteBookDao();

    public NoteBookDao getNoteBookDao() {
        return noteBookDao;
    }

    public static DaoProvider getInstance() {
        return INSTANCE;
    }


}
