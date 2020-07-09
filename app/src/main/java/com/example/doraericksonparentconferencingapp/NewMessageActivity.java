package com.example.doraericksonparentconferencingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class NewMessageActivity extends AppCompatActivity {
    private MessageItem message = null;
    private User currentUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_message);

        if (getIntent().getStringArrayExtra(HomePageActivity.USER_KEY) != null) {
            currentUser = new Gson().fromJson(getIntent().getStringExtra(HomePageActivity.USER_KEY), User.class);
        } else {
            Log.e("DirectoryActivity.onCreate()", "Error: No known User passed in.");
            currentUser = null;

            Toast errorToast = Toast.makeText(getApplicationContext(), "User not recognized.", Toast.LENGTH_LONG);
            errorToast.show();
        }

        message = new MessageItem();

        if (currentUser != null) {
            message.setSender(currentUser.getName());
        } else {
            message.setSender("");
        }
    }


    /**
     * <h3>send(View view)</h3>
     * Sends the message to the server for dispersal.
     * @param view (Type: View, the View which called the function)
     */
    public void send(View view) {
        getMessageData();
        final MessageItem constMessage = message;

        //Notify server that draft should be saved.
        if ((message.getSender() != null) && !message.getSender().equals("")) {
            Thread sendThread = new Thread(new CustomRun(this) {
                @Override
                public void run() {
                    //Send message
                    String serverSendResponse = new ServerRequest().request("", ""/*"?keyName=" + new Gson().toJson(constMessage)*/);
                    //Delete draft from user draft page.
                    String serverDeleteResponse = new ServerRequest().request("", ""/*"?keyName=" + new Gson().toJson(constMessage)*/);
                    final String toastOutput;

                    if ((serverDeleteResponse == null) || serverDeleteResponse.equals("")) {
                        Log.e("NewMessageActivity.Send()", "Error deleting file from server.");
                    }

                    //Determine status of save.
                    if ((serverSendResponse == null) || serverSendResponse.equals("")) {
                        Log.e("NewMessageActivity.Send()", "Error sending message.");
                        toastOutput = "Error sending message.";
                    } else {
                        toastOutput = "Message Sent";
                    }

                    //Report save status to user.
                    currentActivity.get().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast statusToast = Toast.makeText(currentActivity.get().getApplicationContext(),
                                    toastOutput, Toast.LENGTH_LONG);
                            statusToast.show();
                        }
                    });
                }
            });

            sendThread.start();
        } else {
            Toast noSenderToast = Toast.makeText(getApplicationContext(), "Can't Send, No Recipient", Toast.LENGTH_LONG);
            noSenderToast.show();
        }
    }

    /**
     * <h3>delete(View view)</h3>
     * Checks if the user wants to delete the activity and, if so, ends the activity without
     * saving.
     * @param view (Type: View, the View which called the function)
     */
    public void delete(View view) {
        //Send message to server that this draft can be deleted.
        final MessageItem constMessage = message;

        //Notify server that message can be deleted.
        Thread messageCanBeDeletedThread = new Thread(new Runnable() {
            @Override
            public void run() {
                String serverResponse = new ServerRequest().request("", ""/*"?keyName=" + new Gson().toJson(constMessage)*/);

                if ((serverResponse == null) || serverResponse.equals("")) {
                    Log.e("NewMessageActivity.delete()", "Error deleting message from server.");
                }
            }
        });

        messageCanBeDeletedThread.start();

        finish();
    }

    /**
     * <h3>save(View view)</h3>
     * Saves the message to the server as a draft.
     * @param view
     */
    public void save(View view) {
        getMessageData();
        final MessageItem constMessage = message;

        //Notify server that draft should be saved.
        Thread saveDraftThread = new Thread(new CustomRun(this) {
            @Override
            public void run() {
                String serverResponse = new ServerRequest().request("", ""/*"?keyName=" + new Gson().toJson(constMessage)*/);
                final String toastOutput;

                //Determine status of save.
                if ((serverResponse == null) || serverResponse.equals("")) {
                    Log.e("NewMessageActivity.Save()", "Error saving message to server.");
                    toastOutput = "Error saving message.";
                } else {
                    toastOutput = "Message Saved";
                }

                //Report save status to user.
                currentActivity.get().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast statusToast = Toast.makeText(currentActivity.get().getApplicationContext(),
                                    toastOutput, Toast.LENGTH_LONG);
                            statusToast.show();
                        }
                });
            }
        });

        saveDraftThread.start();
    }


    private void getMessageData() {
        String recipient = ((TextView)findViewById(R.id.txt_Recipient)).getText().toString();
        String subject = ((TextView)findViewById(R.id.txt_Subject)).getText().toString();
        String message = ((TextView)findViewById(R.id.txt_MessageContent)).getText().toString();

        if (recipient == null) {
            recipient = "";
        }

        if (subject == null) {
            subject = "";
        }

        if (message == null) {
            message = "";
        }


        this.message.setRecipient(recipient);
        this.message.setSubject(subject);
        this.message.setMessage(message);
    }
}