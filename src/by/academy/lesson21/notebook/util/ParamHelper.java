package by.academy.lesson21.notebook.util;

import java.io.File;
import java.util.regex.Pattern;

public class ParamHelper {
    public static String filePath = "src" + File.separator + "by" + File.separator + "academy" + File.separator + "lesson21" + File.separator + "notebook" + File.separator + "file" + File.separator + "base.txt";
    public static final char delimiter = '\n';
    public static final Pattern DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
    public static final Pattern HEADER_PATTERN = Pattern.compile("[^\\da-zA-Zа-яёА-ЯЁ ]");
}
