package com.project.avtomoy;

import java.util.ArrayList;

public class ResponseContactClass {
    private ArrayList<ContactsClass> contacts;
    private String photo;
    private String address;

    public ResponseContactClass(ArrayList<ContactsClass> contacts, String photo, String address) {
        this.contacts = contacts;
        this.photo = photo;
        this.address = address;
    }

    public ArrayList<ContactsClass> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<ContactsClass> contacts) {
        this.contacts = contacts;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
