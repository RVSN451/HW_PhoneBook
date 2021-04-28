package com.kostakov.netologi.phoneBook;

import java.util.Objects;

public class PhoneContact implements Comparable<PhoneContact> {
    private String name;
    private String surname;
    private String phoneNumber;

    public PhoneContact() {
    }

    public PhoneContact(String setName, String setSurname, String setPhoneNumber) {
        this.name = setName;
        this.surname = setSurname;
        this.phoneNumber = setPhoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Имя: " + getSurname() + " " + getName() + ", телефонный номер:  " + getPhoneNumber());
        return result.toString();
    }

    @Override
    public int compareTo(PhoneContact o) {
        return surname.toLowerCase().compareTo(o.surname.toLowerCase());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhoneContact)) return false;
        PhoneContact that = (PhoneContact) o;
        return Objects.equals(getName(), that.getName());
    }
}