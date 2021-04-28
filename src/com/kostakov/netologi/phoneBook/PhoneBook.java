package com.kostakov.netologi.phoneBook;

import java.util.*;

public class PhoneBook {

    private Map<String, List<PhoneContact>> contactGroup = new HashMap<>(); //Ключ - название групп
    private Map<String, PhoneContact> contactPhone = new HashMap<>(); // Ключ - номер телефона

    public boolean addGroup(String titleGroup) {

        if (contactGroup.containsKey(titleGroup)) {
            return false;
        } else {
            contactGroup.put(titleGroup, new ArrayList<>());
            return true;
        }
    }

    public boolean addContact(PhoneContact contact, String[] titlesGroup) {
        boolean isAddedToGroup = false;
        for (String s : titlesGroup) {
            if (contactGroup.containsKey(s)) {
                contactGroup.get(s).add(contact);
                isAddedToGroup = true;
                Collections.sort(contactGroup.get(s));
            }
        }
        if (isAddedToGroup) {
            contactPhone.put(contact.getPhoneNumber(), contact);
        }
        return isAddedToGroup;
    }

    public Set getGroupNameList() {
        return contactGroup.keySet();
    }

    public List getGroupContactList(String nameGroup){
        return (List) contactGroup.get(nameGroup);
    }

    public PhoneContact findContact (String phoneNumber) {
        if (contactPhone.containsKey(phoneNumber)) {
            return contactPhone.get(phoneNumber);
        } else {
            return null;
        }
    }
}