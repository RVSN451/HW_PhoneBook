package com.kostakov.netologi.phoneBook;

import java.util.*;

public class UserInterface {

    public static void printMenu() {
        System.out.println("\nВыберите и введите номер из пункта меню:" +
                "\n   1. Создать новые группы" +
                "\n   2. Внести новый контакт в телефонную книгу" +
                "\n   3. Распечатать телефонную книгу" +
                "\n   0. Завершить работу программы");
    }

    public static void addingGroupsToPhoneBook(PhoneBook phoneBook) {
        List<String> titlesGroup = new ArrayList<>();
        String line;
        System.out.println("Ведите наименование групп для добавления в телефонную книгу." +
                "\nВведите 'Стоп' для выхода.");

        while (true) {
            line = Main.consoleReadString();
            if (line.equalsIgnoreCase("стоп")) {
                break;
            }
            titlesGroup.add(line.toUpperCase());
        }

        for (String s : titlesGroup) {
            phoneBook.addGroup(s);
        }
    }

    public static PhoneContact newContact () {
        PhoneContact contact = new PhoneContact();
        System.out.println("Введите имя:");
        contact.setName(Main.consoleReadString());

        System.out.println("Введите фамилию:");
        contact.setSurname(Main.consoleReadString());

        System.out.println("Введите номер телефона:");
        contact.setPhoneNumber(Main.consoleReadString());

        return contact;
    }

    public static String[] groupsSelection (PhoneBook phoneBook){
        List<String> titlesGroup = new ArrayList<>();
        String line;

        System.out.println("\nВведите наименование групп, в которые необходимо добавить контакт." +
                "\nВведите 'Стоп' для окончания ввода." +
                "\n" +
                "\nСписок групп в Вашей телефонной книге:");
        System.out.println(printTitleGroup(phoneBook));

        while (true) {
            line = Main.consoleReadString().toUpperCase();
            if (line.equalsIgnoreCase("стоп")) {
                break;
            }
            if (!phoneBook.getGroupNameList().contains(line)) {
                System.out.println("Группа '" + line + "' отсутствует в Вашей телефонной книге.");
            } else {
                titlesGroup.add(line);
            }
        }
        return titlesGroup.toArray(new String[titlesGroup.size()]);
    }

    public static void addingContactToPhoneBook(PhoneBook phoneBook) {
        PhoneContact contact = newContact();
        String[] titlesGroup = groupsSelection(phoneBook);
        phoneBook.addContact(contact, titlesGroup);
    }

    public static String printTitleGroup(PhoneBook phoneBook) {
        Set<String> keys = phoneBook.getGroupNameList();
        String[] arrayKeys = keys.toArray(new String[keys.size()]);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < arrayKeys.length; i++) {
            result.append((i + 1) + ". " + arrayKeys[i] + "\n");
        }
        return result.toString();
    }

    public static void printPhoneBook(PhoneBook phoneBook) {

        if (phoneBook.getGroupNameList().isEmpty()) {
            System.out.println("ТЕЛЕФОННАЯ КНИГА ПУСТА.");
        } else {
            Set<String> keys = phoneBook.getGroupNameList();
            StringBuilder result = new StringBuilder();

            result.append("СПИСОК КОНТАКТОВ ТЕЛЕФОННОЙ КНИГИ:\n");
            for (String gr : keys) {
                int itemInGroup = 1;
                result.append("\nКонтакты, включенные в группу '" + gr.toUpperCase() + "':\n");
                List<PhoneContact> listGroupContact = phoneBook.getGroupContactList(gr);
                if (listGroupContact.isEmpty()) {
                    result.append("    Группа '" + gr.toUpperCase() + "' пуста.\n");
                } else {
                    for (PhoneContact printContact : listGroupContact) {
                        result.append("    " + itemInGroup + ". " + printContact.toString() + "\n");
                        itemInGroup++;
                    }
                }
            }
            System.out.println(result);
        }
    }
}