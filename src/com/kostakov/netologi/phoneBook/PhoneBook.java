package com.kostakov.netologi.phoneBook;

import java.util.*;

public class PhoneBook {

    private Map<String, List<PhoneContact>> contactGroup = new HashMap<>();

    public boolean addGroup(String titleGroup) {

        if (contactGroup.containsKey(titleGroup)) {
            return false;
        } else {
            contactGroup.put(titleGroup, new ArrayList<>());
            return true;
        }
    }

    public void addContact(PhoneContact contact, String[] titlesGroup) {
        for (String s : titlesGroup) {
            if (contactGroup.containsKey(s)) {
                contactGroup.get(s).add(contact);
                Collections.sort(contactGroup.get(s)); //TODO Проверить работоспособность
            }
        }
    }

    public Set getGroupNameList() {
        return contactGroup.keySet();
    }

    public List getGroupContactList(String nameGroup){
        return (List) contactGroup.get(nameGroup);
    }
}