package by.academy.lesson21.notebook.util;

import java.util.regex.Pattern;

public final class Validator {
    private static final Pattern INDEX_PATTERN = Pattern.compile("^\\d$");
    private static final Pattern DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
    private static final Pattern HEADER_PATTERN = Pattern.compile("[^\\da-zA-Zа-яёА-ЯЁ ]");
    private static final Pattern TEXT_PATTERN = Pattern.compile("\\R");
    public static final int WRONG_INDEX = -1;

    public static int ValidateIndex(String index) {
        int indexNumber;
        if (!INDEX_PATTERN.matcher(index).matches()) {
            return WRONG_INDEX;
        }
        indexNumber = Integer.parseInt(index);
        if (indexNumber < 0) {
            return WRONG_INDEX;
        }
        return indexNumber;
    }

    public static boolean isValidDate(String date) {
        return DATE_PATTERN.matcher(date).matches();
    }

    public static String correctHeader(String header) {
        return HEADER_PATTERN.matcher(header).replaceAll("");
    }

    public static String correctText(String text) {
        return TEXT_PATTERN.matcher(text).replaceAll("");
    }
}
