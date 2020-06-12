package com.example.doraericksonparentconferencingapp;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;


/**
 * <h3>AnnouncementView class</h3>
 * Extends LinearLayout. Used to display message items and announcements.
 * @author Caleb Fullmer
 * @since June 12, 2020
 * @version 0.1
 */
public class AnnouncementView extends LinearLayout {
    private TextView sender = null;
    private TextView subject = null;
    private TextView sentDate = null;
    private TextView recipient = null;
    private TextView message = null;

    //Constructors
    /**
     * <h3>AnnouncementView(Context context, bool showRecipient)</h3>
     * Create a new AnnouncementView item.
     * @param context (Type: Context, the application context)
     * @param showRecipient (Type: boolean, indicates if the recipient textview should be
     *                      shown)
     */
    public AnnouncementView(Context context, boolean showRecipient) {
        super(context);
        setOrientation(LinearLayout.VERTICAL);

        makeSender();
        makeSubject();
        makeSentDate();
        makeRecipient();
        makeMessage();

        addView(sender);
        addView(subject);
        addView(sentDate);

        if (showRecipient) {
            addView(recipient);
        }

        addView(message);
    }

    /**
     * <h3>makeSender()</h3>
     * Creates the sender textview and defines its attributes.
     */
    private void makeSender() {
        sender = new TextView(getContext());
        //Set up the appropreate textview fields.
    }

    /**
     * <h3>makeSubject()</h3>
     * Create the subject textview and define its attributes.
     */
    private void makeSubject() {
        subject = new TextView(getContext());
        //Set up appropreate textview fields.
    }

    /**
     * <h3>makeSentDate()</h3>
     * Create the sentDate textview and define its attributes.
     */
    private void makeSentDate() {
        sentDate = new TextView(getContext());
        //Set up appropreate textview fields.
    }

    /**
     * <h3>makeRecipient()</h3>
     * Create the recipient textview and define its attributes.
     */
    private void makeRecipient() {
        recipient = new TextView(getContext());
        //Set up appropreate textview fields.
    }

    /**
     * <h3>makeMessage()</h3>
     * Create the message textview and define its attributes.
     */
    private void makeMessage() {
        message = new TextView(getContext());
        //Set up appropreate textview fields.
    }




    //Getters and setters for field data.
    /**
     * <h3>getSender()</h3>
     * Gets the text displayed in the sender textview
     * @return strSender (Type: String, the text displayed in the sender textview)
     */
    public String getSender() {
        if (sender == null) {
            makeSender();
            sender.setText("");
        }
        return (String)sender.getText();
    }

    /**
     * <h3>setSender(String sender)</h3>
     * Sets the text displayed in the sender textview
     * @param sender (Type: String, the text to display)
     */
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

    /**
     * <h3>getSubject()</h3>
     * Gets the text displayed in the subject textview.
     * @return strSubject (Type: String, the displayed text)
     */
    public String getSubject() {
        if (subject == null) {
            makeSubject();
            subject.setText("");
        }
        return (String)subject.getText();
    }

    /**
     * <h3>setSubject(String subject)</h3>
     * Sets the text displayed in the subject textview.
     * @param subject (Type: String, the text to display)
     */
    public void setSubject(String subject) {
        if (this.subject == null) {
            makeSender();
        }

        if (subject != null) {
            this.sender.setText(subject);
        } else {
            this.sender.setText("");
        }
    }

    /**
     * <h3>getSentDate()</h3>
     * Gets the date
     * @return
     */
    /*
    public Date getSentDate() {
        //Still need to fix this one.
    }*/

    /**
     * <h3>setSentDate(Date date)</h3>
     * Sets the date displayed in the sentDate textview.
     * @param date (Type: Date, the date to display)
     */
    public void setSentDate(Date date) {
        if (this.sentDate == null) {
            makeSentDate();
        }

        if (date != null) {
            this.sentDate.setText(HomePageActivity.DATE_FORMAT.format(date));
        } else {
            this.sentDate.setText("");
        }
    }

    /**
     * <h3>getRecipient()</h3>
     * Gets the text displayed in the recipient textview.
     * @return strRecipient (Type: String, the displayed text)
     */
    public String getRecipient() {
        if (recipient == null) {
            makeRecipient();
            recipient.setText("");
        }
        return (String)recipient.getText();
    }

    /**
     * <h3>setRecipient(String recipient)</h3>
     * Sets the text displayed in the recipient textview.
     * @param recipient (Type: String, the text to display)
     */
    public void setRecipient(String recipient) {
        if (this.recipient == null) {
            makeRecipient();
        }

        if (recipient != null) {
            this.recipient.setText(recipient);
        } else {
            this.recipient.setText("");
        }
    }

    /**
     * <h3>getMessage()</h3>
     * Gets the text displayed in the message textview.
     * @return strMessage (Type: String, the displayed text)
     */
    public String getMessage() {
        if (message == null) {
            makeMessage();
            message.setText("");
        }
        return (String)message.getText();
    }

    /**
     * <h3>setMessage(String message)</h3>
     * Sets the text displayed in the message textview.
     * @param message (Type: String, the text to display)
     */
    public void setMessage(String message) {
        if (this.message == null) {
            makeMessage();
        }

        if (message != null) {
            this.message.setText(message);
        } else {
            this.message.setText("");
        }
    }
}
