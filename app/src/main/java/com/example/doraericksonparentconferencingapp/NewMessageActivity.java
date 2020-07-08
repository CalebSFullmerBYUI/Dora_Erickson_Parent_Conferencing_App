package com.example.doraericksonparentconferencingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class NewMessageActivity extends AppCompatActivity {
    private MessageItem message = null;
    private User currentUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_message);
    }


    /**
     * <h3>send(View view)</h3>
     * Sends the message to the server for dispersal.
     * @param view (Type: View, the View which called the function)
     */
    public void send(View view) {

    }

    /**
     * <h3>delete(View view)</h3>
     * Checks if the user wants to delete the activity and, if so, ends the activity without
     * saving.
     * @param view (Type: View, the View which called the function)
     */
    public void delete(View view) {

    }

    /**
     * <h3>save(View view)</h3>
     * Saves the message to the server as a draft.
     * @param view
     */
    public void Save(View view) {

    }
}