package by.academy.lesson21.notebook.util;

import java.io.File;
import java.util.StringJoiner;

public class FileRequest {
    public static String filePath = new StringJoiner(File.separator).add("resource").add("base.txt").toString();

}
