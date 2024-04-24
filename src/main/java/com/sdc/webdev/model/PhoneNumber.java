package com.sdc.webdev.model;

public class PhoneNumber {
    private String phoneNumber;
    private String surname;

    public PhoneNumber(String phoneNumber, String surname) {
        this.phoneNumber = phoneNumber;
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
