package com.example.doraericksonparentconferencingapp;

import java.util.Date;

/**
 * <h3>TextInformation</h3>
 * Stores data for text information such as messages and announcements.
 * @author Caleb Fullmer
 * @since June 8, 2020
 * @version 0.1
 */
public class TextInformation {
    //Variables
    private String sender;
    private String subject;
    private String message;
    private Date sentDate;


    //Constructors
    /**
     * <h3>TestInformation()</h3>
     * Default Constructor
     */
    public TextInformation() {
        sender = "";
        subject = "";
        message = "";
        sentDate = null;
    }

    /**
     * <h3>TextInformation(String sender, ...)</h3>
     * Overload Constructor
     * @param sender (Type: String, the name of the sender)
     * @param message (Type: String, the message to be sent)
     * @param sentDate (Type: Date, The date the message was sent)
     */
    public TextInformation(String sender, String message, Date sentDate) {
        this.sender = sender;
        this.message = message;
        this.sentDate = sentDate;
        subject = "";
    }

    /**
     * <h3>TextInformation(TextInformation objToCopy)</h3>
     * Copy Constructor
     * @param objToCopy (Type: TextInformation, The object to copy)
     */
    public TextInformation(TextInformation objToCopy) {
        sender = objToCopy.getSender();
        subject = objToCopy.getSubject();
        message = objToCopy.getMessage();
        sentDate = objToCopy.getDate();
    }



    //Getters

    /**
     * <h3>getSender()</h3>
     * Returns the name of the sender.
     * @return sender (Type: String, the name of the sender)
     */
    public String getSender() {
        return sender;
    }

    /**
     * <h3>getSubject()</h3>
     * Returns the subject of the information item.
     * @return subject (Type: String, the subject)
     */
    public String getSubject() {
        return subject;
    }

    /**
     * <h3>getMessage()</h3>
     * Returns the message content of the item.
     * @return message (Type: String, the message of the item)
     */
    public String getMessage() {
        return message;
    }

    /**
     * <h3>getDate()</h3>
     * Returns the date the message was sent.
     * @return sentDate (Type: Date, the date the message was sent)
     */
    public Date getDate() {
        return sentDate;
    }



    //Setters

    /**
     * <h3>setSender(String sender)</h3>
     * Sets the sender to the given sender value.
     * @param newSender (Type: String, the new sender)
     */
    public void setSender(String newSender) {

    }

    /**
     * <h3>setSubject(String newSubject)</h3>
     * Sets the subject to the given subject.
     * @param newSubject (Type: String, the new subject)
     */
    public void setSubject(String newSubject) {

    }

    /**
     * <h3>setMessage(String newMessage)</h3>
     * Sets the message to the given message.
     * @param newMessage (Type: String, the new message)
     */
    public void setMessage(String newMessage) {

    }

    /**
     * <h3>setDate(Date newDate)</h3>
     * Sets the date the message was sent.
     * @param newDate (Type: Date, the date the message was sent)
     */
    public void setDate(Date newDate) {

    }
}