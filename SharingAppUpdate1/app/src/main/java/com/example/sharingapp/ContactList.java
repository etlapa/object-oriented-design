package com.example.sharingapp;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ContactList {
    private ArrayList<Contact> contacts;
    private static final String FILENAME = "contacts.sav";

    public ContactList() {
        contacts = new ArrayList<>();
    }

    public void setContacts(ArrayList<Contact> contact_list) {
        contacts = contact_list;
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public List<String> getAllUsernames() {
//        return contacts.stream().map(contact -> contact.getUsername()).collect(Collectors.toList());
        List<String> names = new ArrayList<>();

        for (Contact contact : contacts) {
            names.add(contact.getUsername());
        }

        return names;
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void deleteContact(Contact contact) {
        contacts.remove(contact);
    }

    public Contact getContact(int index) {
        return contacts.get(index);
    }

    public int getSize() {
        return contacts.size();
    }

    public int getIndex(Contact contact) {
        int index = -1;
        for (int i = 0; i < getSize(); i++) {
            if (contact.getId().equals(contacts.get(i).getId())) {
                index = i;
                break;
            }
        }
        return index;
    }

    public boolean hasContact(Contact contact) {
        return contacts.contains(contact);
    }

    public Contact getContactByUsername(String username) {
        Contact contactFound = null;

        for (Contact contact : contacts) {
            if (contact.getUsername().equals(username)) {
                contactFound = contact;
                break;
            }
        }

        return contactFound;
    }

    public void loadContacts(Context context) {
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Contact>>() {
            }.getType();
            contacts = gson.fromJson(isr, listType); // temporary
            fis.close();
        } catch (IOException e) {
            contacts = new ArrayList<>();
        }
    }

    public void saveContacts(Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, 0);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(contacts, osw);
            osw.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isUsernameAvailable(String username) {
        return getContactByUsername(username) == null;
    }
}
