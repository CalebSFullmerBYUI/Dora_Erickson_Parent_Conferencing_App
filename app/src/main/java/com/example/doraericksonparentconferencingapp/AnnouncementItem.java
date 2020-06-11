package com.example.doraericksonparentconferencingapp;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Presently a testing class. Should be useful for displaying message and announcement items.
 */
public class AnnouncementItem extends LinearLayout {
    private TextView sender = null;
    private TextView subject = null;
    private TextView sentDate = null;
    private TextView recipient = null;

    public AnnouncementItem(Context context, boolean isMessage) {
        super(context);
        setOrientation(LinearLayout.HORIZONTAL);

        sender = new TextView(context);
        subject = new TextView(context);
        sentDate = new TextView(context);
        recipient = new TextView(context);

        addView(sender);
        addView(subject);
        addView(sentDate);

        if (isMessage) {
            addView(recipient);
        }
    }


    public void makeSender() {
        sender = new TextView(getContext());
        //Set up the appropreate textview fields.
    }

    public void makeSubject() {
        subject = new TextView(getContext());
        //Set up appropreate textview fields.
    }

    public void makeSentDate() {
        sentDate = new TextView(getContext());
        //Set up appropreate textview fields.
    }

    public void makeRecipient() {
        recipient = new TextView(getContext());
        //Set up appropreate textview fields.
    }



    //Getters and setters for field data.
    public String getSender() {
        if (sender == null) {
            makeSender();
        }
        return (String)sender.getText();
    }

    public void setSender(String sender) {
        if (this.sender == null) {
            makeSender();
        }

        if (sender != null) {
            this.sender.setText(sender);
        } else {
            this.sender.setText("");
        }
    }
}
