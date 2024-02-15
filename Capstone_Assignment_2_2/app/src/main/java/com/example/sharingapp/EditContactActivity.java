package com.example.sharingapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Observable;
import java.util.Observer;

/**
 * Editing a pre-existing contact consists of deleting the old contact and adding a new contact with the old
 * contact's id.
 * Note: You will not be able contacts which are "active" borrowers
 */
public class EditContactActivity extends AppCompatActivity implements Observer {

    private ContactController contactController;
    private ContactListController contactListController = new ContactListController(new ContactList());
    private EditText email;
    private EditText username;
    private Context context;

    private Spinner spinner;
    private ArrayAdapter<String> adapter;
    private boolean on_create_update = false;
    private int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        context = getApplicationContext();

        on_create_update = true;

        contactListController.loadContacts(context);
        contactListController.addObserver(this);

        on_create_update = false;

        Intent intent = getIntent();
        pos = intent.getIntExtra("position", 0);

        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);

        username.setText(contactController.getUsername());
        email.setText(contactController.getEmail());
    }

    public void saveContact(View view) {

        String email_str = email.getText().toString();

        if (email_str.equals("")) {
            email.setError("Empty field!");
            return;
        }

        if (!email_str.contains("@")) {
            email.setError("Must be an email address!");
            return;
        }

        String username_str = username.getText().toString();
        String id = contactController.getId(); // Reuse the contact id


        // Check that username is unique AND username is changed (Note: if username was not changed
        // then this should be fine, because it was already unique.)
        if (!contactListController.isUsernameAvailable(username_str) && !(contactController.getUsername().equals(username_str))) {
            username.setError("Username already taken!");
            return;
        }

        Contact updated_contact = new Contact(username_str, email_str, id);

        // Edit contact
        boolean success = contactListController.editContact(contactController.getContact(), updated_contact, context);
        if (!success) {
            return;
        }

        // End EditContactActivity
        finish();
    }

    public void deleteContact(View view) {

        // Delete contact
        boolean success = contactListController.deleteContact(contactController.getContact(), context);
        if (!success) {
            return;
        }

        contactListController.deleteObserver(this);

        // End EditContactActivity
        finish();
    }

    @Override
    public void update(Observable observable, Object o) {
        if (on_create_update) {
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
                    contactListController.getAllUsernames());
            spinner.setAdapter(adapter);

            Contact contact = contactListController.getContact(pos);
            contactController = new ContactController(contact);

            if (contact != null) {
                int contact_pos = contactListController.getIndex(contact);
                spinner.setSelection(contact_pos);
            }

            username.setText(contactController.getUsername());
            email.setText(contactController.getEmail());

            spinner.setVisibility(View.GONE);
        }
    }
}
