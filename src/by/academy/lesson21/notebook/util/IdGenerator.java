package by.academy.lesson21.notebook.util;

import java.util.UUID;

public class IdGenerator {

    public static String getId() {
        return UUID.randomUUID().toString();
    }
}
