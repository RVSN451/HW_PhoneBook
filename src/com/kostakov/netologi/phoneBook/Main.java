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
        MissedPhoneCall missedCall = new MissedPhoneCall();

        phoneBook.addGroup("РАБОТА");
        phoneBook.addGroup("ДРУЗЬЯ");
        String job [] = {"РАБОТА"};
        String friends [] = {"ДРУЗЬЯ"};
        String friedJob [] = {"ДРУЗЬЯ", "РАБОТА"};



        PhoneContact contact1 = new PhoneContact("Савелий", "Иванов", "+7(926)111-11-11");
        phoneBook.addContact(contact1, job);
        PhoneContact contact2 = new PhoneContact("Иван", "Петров", "+7(926)222-22-22");
        phoneBook.addContact(contact2, friedJob);
        PhoneContact contact3 = new PhoneContact("Николай", "Букин", "+7(926)333-33-33");
        phoneBook.addContact(contact3,friends);
        PhoneContact contact4 = new PhoneContact("Сергей", "Шпак", "+7(926)444-44-44");
        phoneBook.addContact(contact4,friedJob);
        PhoneContact contact5 = new PhoneContact("Виктор", "Цой", "+7(926)555-55-55");
        phoneBook.addContact(contact5,job);
        PhoneContact contact6 = new PhoneContact("Виктор", "Кузнецов", "+7(926)666-66-66");
        phoneBook.addContact(contact6,friedJob);
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
                case 4:
                    UserInterface.addingMissedCall(missedCall);
                    UserInterface.printMenu();
                    break;
                case 5:
                    missedCall.clearMissedCall();
                    System.out.println("Список пропущенных вызовов очищен.");
                    UserInterface.printMenu();
                    break;
                case 6:
                    UserInterface.printMissedCall(phoneBook,missedCall);
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