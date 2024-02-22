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
import java.util.List;

/**
 * ItemList class
 */
public class ItemList extends Observable{

    private List<Item> items;
    private String FILENAME = "items.sav";

    private FileProcessor<Item> fileProcessor = new FileProcessor<>(FILENAME);

    public ItemList() {
        items = new ArrayList<>();
    }

    public void setItems(List<Item> item_list) {
        items = item_list;
        notifyObservers();
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
        notifyObservers();
    }

    public void deleteItem(Item item) {
        items.remove(item);
        notifyObservers();
    }

    public Item getItem(int index) {
        return items.get(index);
    }

    public int getIndex(Item item) {
        int pos = 0;
        for (Item i : items) {
            if (item.getId().equals(i.getId())) {
                return pos;
            }
            pos = pos + 1;
        }
        return -1;
    }

    public int getSize() {
        return items.size();
    }

    public void loadItems(Context context) {
        fileProcessor.loadContacts(context);
        notifyObservers();
    }

    public boolean saveItems(Context context) {
        return fileProcessor.saveContacts(context);
    }

    public List<Contact> getActiveBorrowers() {
        List<Contact> active_borrowers = new ArrayList<Contact>();
        for (Item i : items) {
            Contact borrower = i.getBorrower();
            if (borrower != null) {
                active_borrowers.add(borrower);
            }
        }
        return active_borrowers;
    }

    public List<Item> filterItemsByStatus(String status){
        List<Item> selected_items = new ArrayList<>();
        for (Item i: items) {
            if (i.getStatus().equals(status)) {
                selected_items.add(i);
            }
        }
        return selected_items;
    }
}



