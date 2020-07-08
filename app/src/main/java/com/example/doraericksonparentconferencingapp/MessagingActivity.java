package com.example.doraericksonparentconferencingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MessagingActivity extends AppCompatActivity {
    private ArrayList<MessageItem> messages = new ArrayList<MessageItem>();
    private User currentUser = null;
    private boolean unreadOnly = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);
    }


    /**
     * <h3>getMessages()</h3>
     * Retrieves messages from server.
     */
    public void getMessages() {

    }

    /**
     * <h3>displayMessages</h3>
     * Displays messages.
     */
    public void displayMessages() {

    }




    /**
     * <h3>refreshMessages(View view)</h3>
     * Manually cales getMessages.
     * @param view (Type: View, the View which called the function)
     */
    public void refreshMessages(View view) {
        getMessages();
    }

    /**
     * <h3>newMessage(View view)</h3>
     * Starts NewMessageActivity to create a new message.
     * @param view (Type: View, the View which called the funcion)
     */
    public void newMessage(View view) {

    }

    /**
     * <h3>replyToMessage(View view)</h3>
     * Starts NewMessageActivity to reply to an existing message.
     * @param view
     */
    public void replyToMessage(View view) {

    }
}