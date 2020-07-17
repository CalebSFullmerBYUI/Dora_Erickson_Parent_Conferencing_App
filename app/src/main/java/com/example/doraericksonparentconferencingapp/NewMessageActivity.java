package com.example.doraericksonparentconferencingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class NewMessageActivity extends AppCompatActivity {
    public static final String IS_REPLY_KEY = "Is_Reply Key";
    public static final String MESSAGE_KEY = "Message key";
    private MessageItem message = null;
    private MessageItem parentMessageItem = null;
    private User currentUser = null;
    private boolean isReply = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_message);

        if (getIntent().getStringExtra(HomePageActivity.USER_KEY) != null) {
            currentUser = new Gson().fromJson(getIntent().getStringExtra(HomePageActivity.USER_KEY), User.class);
        } else {
            Log.e("DirectoryActivity.onCreate()", "Error: No known User passed in.");
            currentUser = null;

            Toast errorToast = Toast.makeText(getApplicationContext(), "User not recognized.", Toast.LENGTH_LONG);
            errorToast.show();
        }


        message = new MessageItem();



        //Check for if it is a new message or a reply.
        if (getIntent().getStringExtra(MESSAGE_KEY) != null) {
            isReply = getIntent().getBooleanExtra(IS_REPLY_KEY, false);

            try {
                if (getIntent().getStringExtra(MESSAGE_KEY) != null) {
                    parentMessageItem = new Gson().fromJson(getIntent().getStringExtra(MESSAGE_KEY), MessageItem.class);

                    if (isReply) {
                        if (!parentMessageItem.getSender().equals("Me") && !parentMessageItem.getSender().equals(currentUser.getName())) {
                            message.setRecipient(parentMessageItem.getSender());
                            message.setSender(parentMessageItem.getRecipient());
                        } else {
                            message.setRecipient(parentMessageItem.getRecipient());
                            message.setSender(parentMessageItem.getSender());
                        }
                        
                        message.setSubject(parentMessageItem.getSubject());
                        if (currentUser != null) {
                            message.setSender(currentUser.getName());
                        }
                    } else {
                        message = parentMessageItem;
                        parentMessageItem = null;
                    }

                    ((EditText)findViewById(R.id.txt_Recipient)).setText(message.getRecipient());
                    ((EditText)findViewById(R.id.txt_Subject)).setText(message.getSubject());
                    ((EditText)findViewById(R.id.txt_MessageContent)).setText(message.getMessage());
                } else {
                    throw new Exception("Intent marked as a reply, but no base MessageItem given.");
                }
            } catch (Exception e) {
                Log.e("NewMessageActivity.onCreate()", e.getMessage());
                Toast errorToast = Toast.makeText(getApplicationContext(), "Issue Replying", Toast.LENGTH_LONG);
                errorToast.show();
                finish();
            }
        } else {
            parentMessageItem = null;
        }




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
        String variableKeys = "?";

        if (isReply && (parentMessageItem != null)) {
            //Set appropreate variable keys.
        } else {
            //Set appropreate variable keys.
        }

        //Notify server that draft should be saved.
        if ((message.getRecipient() != null) && !message.getRecipient().equals("")) {
            Thread sendThread = new Thread(new CustomRun(this) {
                @Override
                public void run() {
                    //Send message
                    final String serverSendResponse = new ServerRequest().request("", ""/*"?keyName=" + new Gson().toJson(constMessage)*/);
                    //Delete draft from user draft page.
                    String serverDeleteResponse = new ServerRequest().request("", ""/*"?keyName=" + new Gson().toJson(constMessage)*/);

                    if ((serverDeleteResponse == null) || serverDeleteResponse.equals("")) {
                        Log.e("NewMessageActivity.Send()", "Error deleting file from server.");
                    }

                    //Report save status to user.
                    currentActivity.get().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Determine status of save.
                            if ((serverSendResponse == null) || serverSendResponse.equals("")) {
                                Log.e("NewMessageActivity.Send()", "Error sending message.");
                                Toast statusToast = Toast.makeText(currentActivity.get().getApplicationContext(),
                                        "Error sending message.", Toast.LENGTH_LONG);
                                statusToast.show();
                            } else {
                                Toast statusToast = Toast.makeText(currentActivity.get().getApplicationContext(),
                                        "Message Sent", Toast.LENGTH_LONG);
                                statusToast.show();
                                finish();
                            }
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
        String variableKeys = "?";

        if (isReply && (parentMessageItem != null)) {
            //Set appropreate variable keys.
        } else {
            //Set appropreate variable keys.
        }

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