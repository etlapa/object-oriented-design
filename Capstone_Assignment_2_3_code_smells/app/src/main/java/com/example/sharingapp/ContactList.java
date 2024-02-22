package com.example.sharingapp;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * ContactList class
 */
public class ContactList extends Observable {

    private List<Contact> contacts;
    private static final String FILENAME = "contacts.sav";

    private FileProcessor<Contact> fileProcessor = new FileProcessor<>(FILENAME);

    public ContactList() {
        contacts = new ArrayList<Contact>();
    }

    public void setContacts(List<Contact> contact_list) {
        contacts = contact_list;
        notifyObservers();
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public List<String> getAllUsernames(){
        List<String> username_list = new ArrayList<String>();
        for (Contact c : contacts){
            username_list.add(c.getUsername());
        }
        return username_list;
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
        notifyObservers();
    }

    public void deleteContact(Contact contact) {
        contacts.remove(contact);
        notifyObservers();
    }

    public Contact getContact(int index) {
        return contacts.get(index);
    }

    public int getSize() {
        return contacts.size();
    }

    public Contact getContactByUsername(String username){
        for (Contact c : contacts){
            if (c.getUsername().equals(username)){
                return c;
            }
        }
        return null;
    }

    public boolean hasContact(Contact contact) {
        for (Contact c : contacts) {
            if (contact.getId().equals(c.getId())) {
                return true;
            }
        }
        return false;
    }

    public int getIndex(Contact contact) {
        int pos = 0;
        for (Contact c : contacts) {
            if (contact.getId().equals(c.getId())) {
                return pos;
            }
            pos = pos+1;
        }
        return -1;
    }

    public void loadContacts(Context context) {
        contacts = fileProcessor.loadContacts(context);
        notifyObservers();
    }

    /**
     * @param context
     * @return true: if save is successful, false: if save is unsuccessful
     */
    public boolean saveContacts(Context context) {
        return fileProcessor.saveContacts(context);
    }

    public boolean isUsernameAvailable(String username){
        for (Contact u : contacts) {
            if (u.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }
}
