package com.example.sharingapp;

import android.content.Context;

import java.util.ArrayList;
import java.util.Observer;

public class ContactListController {
    private ContactList contactList;

    public ContactListController(ContactList contactList) {
        this.contactList = contactList;
    }

    public void setContactList(ContactList contactList) {
        this.contactList = contactList;
    }

    public ContactList getContactList() {
        return contactList;
    }

    public boolean addContact(Contact contact, Context context) {
        AddContactCommand addContactCommand = new AddContactCommand(contactList, contact, context);
        addContactCommand.execute();
        return addContactCommand.isExecuted();
    }

    public boolean deleteContact(Contact contact, Context context) {
        DeleteContactCommand deleteContactCommand = new DeleteContactCommand(contactList, contact, context);
        deleteContactCommand.execute();
        return deleteContactCommand.isExecuted();
    }

    public boolean editContact(Contact oldContact, Contact newContact, Context context) {
        EditContactCommand editContactCommand = new EditContactCommand(contactList, oldContact, newContact, context);
        editContactCommand.execute();
        return editContactCommand.isExecuted();
    }

    public void setContact(ArrayList<Contact> contacts) {
        contactList.setContacts(contacts);
    }

    public ArrayList<Contact> getContacts() {
        return contactList.getContacts();
    }

    public ArrayList<String> getAllUsernames() {
        return contactList.getAllUsernames();
    }

    public void addContact(Contact contact) {
        contactList.addContact(contact);
    }

    public void deleteContact(Contact contact) {
        contactList.deleteContact(contact);
    }

    public Contact getContact(int index) {
        return contactList.getContact(index);
    }

    public int getSize() {
        return contactList.getSize();
    }

    public Contact getContactByUsername(String username) {
        return contactList.getContactByUsername(username);
    }

    public boolean hasContact(Contact contact) {
        return contactList.hasContact(contact);
    }

    public int getIndex(Contact contact) {
        return contactList.getIndex(contact);
    }

    public boolean isUsernameAvailable(String username) {
        return contactList.isUsernameAvailable(username);
    }

    public void addObserver(Observer observer) {
        contactList.addObserver(observer);
    }

    public void deleteObserver(Observer observer) {
        contactList.deleteObserver(observer);
    }

    public void loadContacts(Context context) {
        contactList.loadContacts(context);
    }

    public boolean saveContacts(Context context) {
        return contactList.saveContacts(context);
    }

}