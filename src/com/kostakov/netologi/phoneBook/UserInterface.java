package com.kostakov.netologi.phoneBook;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class UserInterface {

    public static void printMenu() {
        System.out.println("\nВыберите и введите номер из пункта меню:" +
                "\n   1. Создать новые группы" +
                "\n   2. Внести новый контакт в телефонную книгу" +
                "\n   3. Распечатать телефонную книгу\n" +
                "\n   4. Добавить пропущенные вызовы" +
                "\n   5. Очистить список пропущенных вызовов" +
                "\n   6. Распечатать список пропущенных вызовов\n" +
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
        if (!phoneBook.addContact(contact, titlesGroup)) {
            System.out.println("Контакт [" + contact + "] НЕ ДОБАВЛЕН в телефонную книгу." +
                    "\nНе указано/не верно указано название группы, в которую необходимо добавить контакт.");
        };
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

            result.append("\nСПИСОК КОНТАКТОВ ТЕЛЕФОННОЙ КНИГИ:");
            for (String gr : keys) {
                //Collections.sort(phoneBook.getContactGroup().get(gr));
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

    public static void addingMissedCall (MissedPhoneCall missedCall) {
        String line;
        System.out.println("Ведите номера пропущенных взовов." +
                "\nВведите 'Стоп' для выхода.");

        while (true) {
            line = Main.consoleReadString();
            if (line.equalsIgnoreCase("стоп")) {
                break;
            } else {
                missedCall.addMissedCall(line);
            }
        }
    }

    public static void printMissedCall (PhoneBook phoneBook, MissedPhoneCall missedCall) {
        StringBuilder result = new StringBuilder();

        if (missedCall.isEmpty()) {
            System.out.println("\nПРОПУЩЕННЫЕ ВЫЗОВЫ ОТСУТСТВУЮТ.");
        } else {
            int i = 1; // счетчик пропущенных вызовов
            result.append("\nСПИСОК ПРОПУЩЕННЫХ ВЫЗОВОВ:");
            for (Map.Entry<LocalDateTime, String> missedCallUnit: missedCall.entrySet()) {
                LocalDateTime time = missedCallUnit.getKey();
                String phoneNumber = missedCallUnit.getValue();
                PhoneContact contact = phoneBook.findContact(phoneNumber);
                DateTimeFormatter data = DateTimeFormatter.ofPattern("MM-dd-yyyy");
                DateTimeFormatter hour = DateTimeFormatter.ofPattern("HH-mm");

                if (contact == null) {
                    result.append("\n\t" + i + ". " + data.format(time) + "г. " + hour.format(time) + ". " + phoneNumber);
                    i++;
                } else {
                    result.append("\n\t" + i + ". " + data.format(time) + "г. " + hour.format(time) + ". " + contact);
                    i++;
                }
            }
        }
        System.out.println(result);
    }
}