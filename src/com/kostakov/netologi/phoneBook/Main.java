package com.kostakov.netologi.phoneBook;

import java.io.*;

public class Main {

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private static int consoleReadInt() {
        int voice = 0;
        String input = consoleReadString();

        if (isDigit(input)) {
            voice = Integer.parseInt(input);
        } else {
            System.out.println("Вы ввели неверное значение, повторите выбор.");
            voice = consoleReadInt();
        }
        return voice;
    }

    static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String consoleReadString() {
        String line = "";
        try {
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    public static void main(String[] args) {
        int i = 1;
        PhoneBook phoneBook = new PhoneBook();
        UserInterface.printMenu();

        while (true) {
            switch (consoleReadInt()) {
                case 0:
                    i = 0;
                    break;
                case 1:
                    UserInterface.addingGroupsToPhoneBook(phoneBook);
                    UserInterface.printMenu();
                    break;

                case 2:
                    UserInterface.addingContactToPhoneBook(phoneBook);
                    UserInterface.printMenu();
                    break;
                case 3:
                    UserInterface.printPhoneBook(phoneBook);
                    UserInterface.printMenu();
                    break;
                default:
                    System.out.println("Вы ввели неверное значение, повторите выбор:");
                    break;
            }
            if (i == 0) break;
        }

    }
}
