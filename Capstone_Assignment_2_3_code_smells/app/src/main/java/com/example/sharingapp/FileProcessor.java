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

public class FileProcessor<T> {
    private String filename = "contacts.sav";
    public FileProcessor(String filename){
        this.filename = filename;
    }
    private List<T> elements = new ArrayList<>();
    public List<T> loadContacts(Context context) {
        try {
            FileInputStream fis = context.openFileInput(filename);
            InputStreamReader isr = new InputStreamReader(fis);
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Contact>>() {}.getType();
            elements = gson.fromJson(isr, listType); // temporary
            fis.close();
        } catch (FileNotFoundException e) {
            elements = new ArrayList<T>();
        } catch (IOException e) {
            elements = new ArrayList<T>();
        }
        return elements;
    }

    /**
     * @param context
     * @return true: if save is successful, false: if save is unsuccessful
     */
    public boolean saveContacts(Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(filename, 0);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(elements, osw);
            osw.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
