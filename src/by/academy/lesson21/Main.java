package by.academy.lesson21;

import by.academy.lesson21.notebook.controller.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String request;
        char result;
        Controller controller = new Controller();

        while (true) {
            System.out.print("1 - показать все записи, ");
            System.out.print("2 - добавить запись, ");
            System.out.print("3 - удалить запись, ");
            System.out.print("4 - изменить запись, ");
            System.out.print("5 - найти записи, ");
            System.out.print("0 - выход: ");
            result = reader.readLine().toLowerCase().charAt(0);
            switch (result) {
                case '1' -> {
                    request = "show\n ";
                    System.out.println(controller.readAndExecute(request));
                }
                case '2' -> {
                    request = "add";
                    System.out.print("Введите текст: ");
                    request += "\n" + reader.readLine();
                    System.out.print("Введите заголовок: ");
                    request += "\n" + reader.readLine();
                    System.out.println(controller.readAndExecute(request));
                }
                case '3' -> {
                    request = "delete";
                    System.out.print("Введите номер записи для удаления: ");
                    request += "\n" + reader.readLine();
                    System.out.println(controller.readAndExecute(request));
                }
                case '4' -> {
                    request = "update";
                    System.out.print("Введите номер записи для изменения: ");
                    request += "\n" + reader.readLine();
                    System.out.print("Введите текст: ");
                    request += "\n" + reader.readLine();
                    System.out.print("Введите заголовок: ");
                    request += "\n" + reader.readLine();
                    System.out.println(controller.readAndExecute(request));
                }
                case '5' -> {
                    request = "find";
                    System.out.print("1 - поиск по тексту, ");
                    System.out.print("2 - поиск по дате");
                    result = reader.readLine().toLowerCase().charAt(0);
                    if (result == '1') {
                        System.out.print("Введите текст: ");
                        request += "\n" + reader.readLine() + "\n ";
                    } else if (result == '2') {
                        System.out.print("Введите дату в формате (yyyy-mm-dd): ");
                        request += "\n\n" + reader.readLine();
                    }
                    System.out.println(controller.readAndExecute(request));
                }
                case '0' -> {
                    return;
                }
                default -> {
                    System.out.println(("Unexpected value: " + result));
                }
            }
        }
    }
}