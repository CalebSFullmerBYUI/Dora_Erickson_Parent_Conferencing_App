package com.example.doraericksonparentconferencingapp;

import java.util.Date;

/**
 * <h3>TextInformation</h3>
 * Stores data for text information such as messages and announcements.
 * @author Caleb Fullmer
 * @since June 22, 2020
 * @version 1.1
 */
public class TextInformation {
    //Variables
    private String sender = null;
    private String subject = null;
    private String message = null;
    private long sentDate = 0;


    //Constructors

    /**
     * <h3>TestInformation()</h3>
     * Default Constructor
     */
    public TextInformation() {
        setNewTextInfoObj("", "", "", new Date());
    }

    /**
     * <h3>TextInformation(String sender, ...)</h3>
     * Overload Constructor
     *
     * @param sender   (Type: String, the name of the sender)
     * @param message  (Type: String, the message to be sent)
     * @param sentDate (Type: Date, The date the message was sent)
     */
    public TextInformation(String sender, String message, Date sentDate) {
        setNewTextInfoObj(sender, "", message, sentDate);
    }

    /**
     * <h3>TextInformation(TextInformation objToCopy)</h3>
     * Copy Constructor
     *
     * @param objToCopy (Type: TextInformation, The object to copy)
     */
    public TextInformation(TextInformation objToCopy) {
        if (objToCopy != null) {
            setNewTextInfoObj(objToCopy.getSender(), objToCopy.getSubject(),
                    objToCopy.getMessage(), objToCopy.getDate());
        } else {
            setNewTextInfoObj("", "", "", new Date());
        }
    }

    /**
     * <h3>setNewTextInfoObj(String sender, ...)</h3>
     * Used in constructors to set TextInformation variables.
     *
     * @param sender   (Type: String, the sender of the message)
     * @param subject  (Type: String, the subject of the text)
     * @param message  (Type: String, the message of the text)
     * @param sentDate (Type: Date, the date the text was sent)
     */
    private void setNewTextInfoObj(String sender, String subject, String message, Date sentDate) {
        setSender(sender);
        setSubject(subject);
        setMessage(message);
        setDate(sentDate);
    }


    //Getters

    /**
     * <h3>getSender()</h3>
     * Returns the name of the sender.
     *
     * @return sender (Type: String, the name of the sender)
     */
    public String getSender() {
        if (sender == null) {
            setSender("");
        }
        return sender;
    }

    /**
     * <h3>getSubject()</h3>
     * Returns the subject of the information item.
     *
     * @return subject (Type: String, the subject)
     */
    public String getSubject() {
        if (subject == null) {
            setSubject("");
        }
        return subject;
    }

    /**
     * <h3>getMessage()</h3>
     * Returns the message content of the item.
     *
     * @return message (Type: String, the message of the item)
     */
    public String getMessage() {
        if (message == null) {
            setMessage("");
        }
        return message;
    }

    /**
     * <h3>getDate()</h3>
     * Returns the date the message was sent.
     * @return sentDate (Type: Date, the date the message was sent)
     */
    public Date getDate() {
        /*if (sentDate == null) {
            //This should never be the case. If it is, the obvious 0 milliseconds since
            //  January 1, 1970 should hopefully be a hint that something is wrong.
            setDate(new Date(0));
        }*/
        return new Date(sentDate * 1000);
    }



    //Setters
    /**
     * <h3>setSender(String sender)</h3>
     * Sets the sender to the given sender value.
     * @param newSender (Type: String, the new sender)
     */
    public void setSender(String newSender) {
        if (newSender != null) {
            sender = newSender;
        } else {
            sender = "";
        }
    }

    /**
     * <h3>setSubject(String newSubject)</h3>
     * Sets the subject to the given subject.
     * @param newSubject (Type: String, the new subject)
     */
    public void setSubject(String newSubject) {
        if (newSubject != null) {
            subject = newSubject;
        } else {
            subject = "";
        }
    }

    /**
     * <h3>setMessage(String newMessage)</h3>
     * Sets the message to the given message.
     * @param newMessage (Type: String, the new message)
     */
    public void setMessage(String newMessage) {
        if (newMessage != null) {
            message = newMessage;
        } else {
            message = "";
        }
    }

    /**
     * <h3>setDate(Date newDate)</h3>
     * Sets the date the message was sent.
     * @param newDate (Type: Date, the date the message was sent)
     */
    public void setDate(Date newDate) {
        if (newDate != null) {
            sentDate = newDate.getTime() / 1000;
        } else {
            sentDate = 0;
        }
    }
}