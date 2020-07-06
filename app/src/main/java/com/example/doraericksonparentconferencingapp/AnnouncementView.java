package com.example.doraericksonparentconferencingapp;

import android.app.AppComponentFactory;
import android.app.Application;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.Shape;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Constraints;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;


/**
 * <h3>AnnouncementView class</h3>
 * Extends LinearLayout. Used to display message items and announcements.
 * References:
 *      https://stackoverflow.com/questions/6798867/android-how-to-programmatically-set-the-size-of-a-layout/25540159
 *      https://stackoverflow.com/questions/15636401/how-to-set-margins-for-textview-programmatically
 *          These stack overflow posts discuss how to set layout params
 *          parameters programmatically.
 *      https://stackoverflow.com/questions/45263159/constraintlayout-change-constraints-programmatically
 *      https://developer.android.com/reference/androidx/constraintlayout/widget/ConstraintSet
 *          These webpages discuss how to set ConstraintLayout constraints programmatically.
 * @author Caleb Fullmer
 * @since June 29, 2020
 * @version 1.0
 */
public class AnnouncementView extends ConstraintLayout {
    private TextView sender = null;
    private TextView subject = null;
    private TextView sentDate = null;
    private TextView recipient = null;
    private TextView message = null;

    //Generate new Id's for the AnnouncementView and child Views.
    private final int senderId = generateViewId();
    private final int subjectId = generateViewId();
    private final int sentDateId = generateViewId();
    private final int recipientId = generateViewId();
    private final int messageId = generateViewId();
    private final int announceViewId = generateViewId();

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

        //Set attributes for AnnouncementView
        makeAnnouncementView();
    }

    /**
     * <h3>makeAnnouncementView</h3>
     * Defines constraints, attributes, and child views for the AnnouncementView.
     */
    private void makeAnnouncementView() {
        setBackgroundResource(R.drawable.background_announc_view);
        setId(announceViewId);
        LayoutParams newLayout = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(newLayout);

        makeRecipient();
        makeSender();
        makeSubject();
        makeSentDate();
        makeMessage();

        addView(recipient);
        addView(sender);
        addView(subject);
        addView(sentDate);
        addView(message);


        //Paranoia (Ensures make functions correctly assigned Id's)
        assert(recipient.getId() == recipientId);
        assert(sender.getId() == senderId);
        assert(subject.getId() == subjectId);
        assert(sentDate.getId() == sentDateId);
        assert(message.getId() == messageId);
        assert(getId() == announceViewId);


        //Set constraints.
        ConstraintSet newConstraints = new ConstraintSet();
        newConstraints.clone(this);
        //Constraints for recipient.
        newConstraints.connect(recipientId, ConstraintSet.TOP, announceViewId, ConstraintSet.TOP);
        newConstraints.connect(recipientId, ConstraintSet.BOTTOM, senderId, ConstraintSet.TOP);
        newConstraints.connect(recipientId, ConstraintSet.START, announceViewId, ConstraintSet.START);
        newConstraints.connect(recipientId, ConstraintSet.END, announceViewId, ConstraintSet.END);

        //Constraints for sender
        newConstraints.connect(senderId, ConstraintSet.TOP, recipientId, ConstraintSet.BOTTOM);
        newConstraints.connect(senderId, ConstraintSet.BOTTOM, subjectId, ConstraintSet.TOP);
        newConstraints.connect(senderId, ConstraintSet.START, announceViewId, ConstraintSet.START);
        newConstraints.connect(senderId, ConstraintSet.END, announceViewId, ConstraintSet.END);

        //Constraints for subject
        newConstraints.connect(subjectId, ConstraintSet.TOP, senderId, ConstraintSet.BOTTOM);
        newConstraints.connect(subjectId, ConstraintSet.BOTTOM, sentDateId, ConstraintSet.TOP);
        newConstraints.connect(subjectId, ConstraintSet.START, announceViewId, ConstraintSet.START);
        newConstraints.connect(subjectId, ConstraintSet.END, announceViewId, ConstraintSet.END);

        //Constraints for sentDate
        newConstraints.connect(sentDateId, ConstraintSet.TOP, subjectId, ConstraintSet.BOTTOM);
        newConstraints.connect(sentDateId, ConstraintSet.BOTTOM, messageId, ConstraintSet.TOP);
        newConstraints.connect(sentDateId, ConstraintSet.START, announceViewId, ConstraintSet.START);
        newConstraints.connect(sentDateId, ConstraintSet.END, announceViewId, ConstraintSet.END);

        //Constraints for message
        newConstraints.connect(messageId, ConstraintSet.TOP, sentDateId, ConstraintSet.BOTTOM);
        newConstraints.connect(messageId, ConstraintSet.BOTTOM, announceViewId, ConstraintSet.BOTTOM);
        newConstraints.connect(messageId, ConstraintSet.START, announceViewId, ConstraintSet.START);
        newConstraints.connect(messageId, ConstraintSet.END, announceViewId, ConstraintSet.END);

        //Add constraints to layout.
        newConstraints.applyTo(this);
    }

    /**
     * <h3>makeSender()</h3>
     * Creates the sender textview and defines its attributes.
     */
    private void makeSender() {
        sender = new TextView(getContext());
        //Set up the appropreate textview fields.
        sender.setText("From:");
        sender.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        sender.setId(senderId);

        sender.setTextColor(Color.BLACK);

        ConstraintLayout.LayoutParams newLayout = new Constraints.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        newLayout.setMargins(14, 5, 15, 0);
        sender.setLayoutParams(newLayout);
    }

    /**
     * <h3>makeSubject()</h3>
     * Create the subject textview and define its attributes.
     */
    private void makeSubject() {
        subject = new TextView(getContext());
        //Set up appropreate textview fields.
        subject.setText("Subject:");
        subject.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        subject.setId(subjectId);

        subject.setTextColor(Color.BLACK);

        ConstraintLayout.LayoutParams newLayout = new Constraints.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        newLayout.setMargins(14, 5, 15, 0);
        subject.setLayoutParams(newLayout);
    }

    /**
     * <h3>makeSentDate()</h3>
     * Create the sentDate textview and define its attributes.
     */
    private void makeSentDate() {
        sentDate = new TextView(getContext());
        //Set up appropreate textview fields.
        sentDate.setText("Date:");
        sentDate.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        sentDate.setId(sentDateId);

        sentDate.setTextColor(Color.BLACK);

        ConstraintLayout.LayoutParams newLayout = new Constraints.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        newLayout.setMargins(14, 5, 15, 0);
        sentDate.setLayoutParams(newLayout);
    }

    /**
     * <h3>makeRecipient()</h3>
     * Create the recipient textview and define its attributes.
     */
    private void makeRecipient() {
        recipient = new TextView(getContext());
        //Set up appropreate textview fields.
        recipient.setText("Recipient:");
        recipient.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        recipient.setId(recipientId);

        recipient.setTextColor(Color.BLACK);

        ConstraintLayout.LayoutParams newLayout = new Constraints.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        newLayout.setMargins(14, 5, 15, 0);
        recipient.setLayoutParams(newLayout);
    }

    /**
     * <h3>makeMessage()</h3>
     * Create the message textview and define its attributes.
     */
    private void makeMessage() {
        message = new TextView(getContext());
        //Set up appropreate textview fields.
        message.setText("From:");
        message.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        message.setId(messageId);

        message.setPadding(3, 2, 3, 2);
        message.setBackgroundColor(Color.argb(255, 250, 250, 250));
        message.setTextColor(Color.BLACK);

        ConstraintLayout.LayoutParams newLayout = new Constraints.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        newLayout.setMargins(14, 5, 15, 10);
        message.setLayoutParams(newLayout);
    }




    //Getters and setters for field data.
    /**
     * <h3>getSender()</h3>
     * Gets the text displayed in the sender textview
     * @return strSender (Type: String, the text displayed in the sender textview)
     */
    public String getSender() {
        if (sender == null) {
            makeAnnouncementView();
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
            makeAnnouncementView();
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
            makeAnnouncementView();
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
            makeAnnouncementView();
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
            makeAnnouncementView();
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
            makeAnnouncementView();
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
            makeAnnouncementView();
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
            makeAnnouncementView();
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
            makeAnnouncementView();
        }

        if (message != null) {
            this.message.setText(message);
        } else {
            this.message.setText("");
        }
    }
}
