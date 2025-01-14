package by.academy.lesson21;

import by.academy.lesson21.notebook.controller.Controller;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
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
            result = scanner.next().toLowerCase().charAt(0);
            switch (result) {
                case '1' -> {
                    request = "show\n ";
                    System.out.println(controller.readAndExecute(request));
                }
                case '2' -> {
                    request = "add";
                    System.out.print("Введите текст: ");
                    if (scanner.hasNext()) {
                        request += "\n" + scanner.nextLine();
                    }
                    System.out.print("Введите заголовок: ");
                    if (scanner.hasNext()) {
                        request += "\n" + scanner.nextLine();
                    }
                    System.out.println(controller.readAndExecute(request));
                }
                case '3' -> {
                    request = "delete\n";
                    System.out.println(controller.readAndExecute(request));
                }
                case '4' -> {
                    request = "update\n";
                    System.out.println(controller.readAndExecute(request));
                }
                case '5' -> {
                    request = "find\n";
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