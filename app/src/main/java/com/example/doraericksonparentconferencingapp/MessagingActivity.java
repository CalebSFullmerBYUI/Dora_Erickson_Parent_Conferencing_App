package com.example.doraericksonparentconferencingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;

public class MessagingActivity extends AppCompatActivity {
    private ArrayList<MessageItem> messages = new ArrayList<MessageItem>();
    private ArrayList<MessageItem> unreadMessages = new ArrayList<MessageItem>();
    private ArrayList<MessageItem> draftMessages = new ArrayList<MessageItem>();
    private User currentUser = null;
    private boolean unreadOnly = false;
    private boolean onDrafts = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);

        if (getIntent().getStringExtra(HomePageActivity.USER_KEY) != null) {
            currentUser = new Gson().fromJson(getIntent().getStringExtra(HomePageActivity.USER_KEY), User.class);
            getMessages();
        } else {
            Log.e("DirectoryActivity.onCreate()", "Error: No known User passed in.");
            currentUser = new User("John Doe", false);

            Toast errorToast = Toast.makeText(getApplicationContext(), "User not recognized.", Toast.LENGTH_LONG);
            errorToast.show();
        }
    }


    /**
     * <h3>getMessages()</h3>
     * Retrieves messages from server.
     */
    public void getMessages() {
        Thread getMessages = new Thread(new CustomRun(this) {
            @Override
            public void run() {
                final ArrayList<MessageItem> newMessages;
                final ArrayList<MessageItem> newUnreadMessages;
                final ArrayList<MessageItem> newDraftMessages;
                String messagesJSON = new ServerRequest().request("","");
                String draftsJSON = new ServerRequest().request("","");

                if ((messagesJSON != null) && !messagesJSON.equals("")) {
                    String mockResponse = MockResponses.GetMessages();
                    newMessages = new Gson().fromJson(mockResponse/*messagesJSON*/,
                            MessageItemVector.class).getVector();
                    newUnreadMessages = getUnreadMessages(newMessages);
                } else {
                    newMessages = null;
                    newUnreadMessages = null;
                }

                if ((draftsJSON != null) && !draftsJSON.equals("")) {
                    String mockResponse = MockResponses.GetDrafts();
                    newDraftMessages = new Gson().fromJson(mockResponse/*draftsJSON*/,
                            MessageItemVector.class).getVector();
                } else {
                    newDraftMessages = null;
                }


                //Transfer data to current activity and display messages.
                currentActivity.get().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        boolean error = false;

                        if (newMessages != null) {
                            messages = newMessages;
                        } else {
                            Log.e("MessagingActivity.getMessages()", "Unable to get messages.");
                            error = true;
                            messages = new ArrayList<MessageItem>();
                        }

                        if (newDraftMessages != null) {
                            draftMessages = newDraftMessages;
                        } else {
                            Log.e("MessagingActivity.getMessages()", "Unable to get draft messages.");
                            error = true;
                            draftMessages = new ArrayList<MessageItem>();
                        }

                        if (newUnreadMessages != null) {
                            unreadMessages = newUnreadMessages;
                        } else {
                            Log.e("MessagingActivity.getMessages()", "Unable to get unread messages.");
                            error = true;
                            unreadMessages = new ArrayList<MessageItem>();
                        }


                        if (error) {
                            Toast errorToast = Toast.makeText(currentActivity.get().getApplicationContext(),
                                    "Issue getting messages.", Toast.LENGTH_LONG);
                            errorToast.show();
                        }

                        displayMessages();
                    }
                });
            }
        });

        getMessages.start();
    }

    /**
     * <h3>displayMessages</h3>
     * Displays messages.
     */
    public void displayMessages() {
        //Verify all data is valid.
        if (messages == null) {
            messages = new ArrayList<MessageItem>();
        }

        if (unreadMessages == null) {
            unreadMessages = new ArrayList<MessageItem>();
        }

        if (draftMessages == null) {
            draftMessages = new ArrayList<MessageItem>();
        }



        ArrayList<MessageItem> listToDisplay = new ArrayList<MessageItem>();
        ((LinearLayout)findViewById(R.id.linLay_Messages)).removeAllViews();

        if (unreadOnly) {
            //Show unread messages
            listToDisplay = unreadMessages;
        } else if (onDrafts) {
            //Show draft messages
            listToDisplay = draftMessages;
        } else {
            listToDisplay = messages;
        }


        for (MessageItem item: listToDisplay) {
            if (item != null) {
                //Sets messages and adds them to the LinearLayout.
                AnnouncementView newView = new AnnouncementView(getApplicationContext(), true);
                newView.setSender(item.getSender());
                newView.setRecipient(item.getRecipient());
                newView.setSubject(item.getSubject());
                newView.setSentDate(item.getDate());
                newView.setMessage(item.getMessage());
                newView.setTag(new Gson().toJson(item));

                if (listToDisplay == draftMessages) {
                    newView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            newMessage(v);
                        }
                    });
                } else {
                    newView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            newReply(v);
                        }
                    });
                }

                //Set replies
                if (item.getReplies().size() > 0) {
                    for (MessageItem reply : item.getReplies()) {
                        AnnouncementView newReplyView = new AnnouncementView(getApplicationContext(), true);
                        newReplyView.setSender(reply.getSender());
                        newReplyView.setRecipient(reply.getRecipient());
                        newReplyView.setSubject(reply.getSubject());
                        newReplyView.setSentDate(reply.getDate());
                        newReplyView.setMessage(reply.getMessage());
                        newView.addReply(newReplyView);
                    }
                }

                ((LinearLayout) findViewById(R.id.linLay_Messages)).addView(newView);
            }
        }
    }


    /**
     * <h3>getUnreadMessages</h3>
     * Gets the unread messages from the rest of the messages.
     * @param allMessages (Type: ArrayList<MessageItem>, all messages)
     * @return unread (Type: ArrayList<MessageItem>, the unread messages)
     */
    private ArrayList<MessageItem> getUnreadMessages(ArrayList<MessageItem> allMessages) {
        ArrayList<MessageItem> unreadItems = new ArrayList<>();

        if ((allMessages == null) || allMessages.isEmpty()) {
            return unreadItems;
        }

        for (MessageItem item: allMessages) {
            if (!item.getIsRead()) {
                unreadItems.add(item);
            }
        }

        return unreadItems;
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
        Intent newMessageIntent = new Intent(this, NewMessageActivity.class);
        newMessageIntent.putExtra(HomePageActivity.USER_KEY, new Gson().toJson(currentUser));

        if ((view.getTag() != null) && (view.getTag() instanceof String) && !view.getTag().equals("")) {
            newMessageIntent.putExtra(NewMessageActivity.MESSAGE_KEY, (String)view.getTag());
        }
        newMessageIntent.putExtra(NewMessageActivity.IS_REPLY_KEY, false);

        startActivity(newMessageIntent);
    }


    /**
     * <h3>newReply(View view)</h3>
     * Basically the same as newMessage, but will read as a reply.
     * @param view (Type: View, the View which called the method)
     */
    public void newReply(View view) {
        Intent newMessageIntent = new Intent(this, NewMessageActivity.class);
        newMessageIntent.putExtra(HomePageActivity.USER_KEY, new Gson().toJson(currentUser));

        if ((view.getTag() != null) && (view.getTag() instanceof String) && !view.getTag().equals("")) {
            newMessageIntent.putExtra(NewMessageActivity.MESSAGE_KEY, (String)view.getTag());
        }
        newMessageIntent.putExtra(NewMessageActivity.IS_REPLY_KEY, true);

        startActivity(newMessageIntent);
    }






    //Change messages to view.
    /**
     * <h3>viewAllMessages(View view)</h3>
     * Will display all messages.
     * @param view (Type: View, the View that called the function)
     */
    public void viewAllMessages(View view) {
        unreadOnly = false;
        onDrafts = false;

        displayMessages();
    }

    /**
     * <h3>viewUnreadMessages(View veiw)</h3>
     * Will display only messages that have not been read.
     * @param view (Type: View, the View that called the function)
     */
    public void viewUnreadMessages(View view) {
        unreadOnly = true;
        onDrafts = false;

        displayMessages();


        //Update the status of the unread messages from unread to read.
        if (!unreadMessages.isEmpty()) {
            final ArrayList<MessageItem> unread = unreadMessages;
            Thread changeMessageStatusThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    String jsonResponse = new ServerRequest().request("", ""
                            /*"?newlyReadMessages=" + new Gson().toJson(unread)*/);

                    if ((jsonResponse == null) || jsonResponse.equals("")) {
                        Log.e("MessagingActivity.viewUndreadMessages(View view)", "Issue updating message status.");
                    }
                }
            });
            changeMessageStatusThread.start();

            //This isn't particularly necessary for the prototype, but something similar may help
            // reduce the change of server spam.
            //unreadMessages = new ArrayList<MessageItem>();
        }
    }

    /**
     * <h3>viewDrafts()</h3>
     * Will display the user's saved drafts.
     * @param view (Type: View, the View that called the function)
     */
    public void viewDrafts(View view) {
        unreadOnly = false;
        onDrafts = true;

        displayMessages();
    }
}