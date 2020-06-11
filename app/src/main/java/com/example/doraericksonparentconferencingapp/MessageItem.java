package com.example.doraericksonparentconferencingapp;

import android.os.Message;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <h3>MessageItem</h3>
 * Holds data for messages between users. An extension of the TextInformation class.
 * @author Caleb Fullmer
 * @since June 10, 2020
 * @version 1.0
 */
public class MessageItem extends TextInformation {
    //Variables
    private String recipient;
    private List<MessageItem> replies;
    private boolean isRead = false;


    //Constructors
    /**
     * <h3>MessageItem</h3>
     * Default Constructor
     */
    public MessageItem() {
        super();
        setNewMessItemObj("", new ArrayList<MessageItem>(), false);
    }

    /**
     * <h3>MessageItem(String sender, ...)</h3>
     * Overload Constructor
     * @param sender (Type: String, the name of the sender)
     * @param message (Type: String, the content of the message)
     * @param sentDate (Type: Date, the date the message was sent)
     * @param recipient (Type: String, the name of the recipient)
     */
    public MessageItem(String sender, String message, Date sentDate, String recipient) {
        super(sender, message, sentDate);
        setNewMessItemObj(recipient, new ArrayList<MessageItem>(), false);
    }

    /**
     * <h3>MessageItem(MessageITem objToCopy)</h3>
     * Copy Constructor
     * @param objToCopy (Type: MessageItem, the message to copy)
     */
    public MessageItem(MessageItem objToCopy) {
        super(objToCopy);

        if (objToCopy != null) {
            setNewMessItemObj(objToCopy.recipient, objToCopy.replies, objToCopy.isRead);
        } else {
            setNewMessItemObj("", new ArrayList<MessageItem>(), false);
        }
    }

    /**
     * <h3>setNewMessItemObj(String recipient, ...)</h3>
     * Used by constructors to safely set variables.
     * @param recipient (Type: String, the recipient of the message)
     * @param replies (Type: List<MessageItem>, list with replies to the message)
     * @param isRead (Type: boolean, indicates if the message has been read)
     */
    private void setNewMessItemObj(String recipient, List<MessageItem> replies, boolean isRead) {
        setRecipient(recipient);
        setIsRead(isRead);

        if (replies != null) {
            this.replies = replies;
        } else {
            this.replies = new ArrayList<MessageItem>();
        }
    }




    //Getters
    /**
     * <h3>getRecipient()</h3>
     * Returns the recipient of the message
     * @return recipient (Type: String, the name of the recipient)
     */
    public String getRecipient() {
        if (recipient == null) {
            setRecipient("");
        }
        return recipient;
    }

    /**
     * <h3>getReplies()</h3>
     * Returns a list containing the replies to the message.
     * @return replies (Type: List, the replies to the message)
     */
    public List<MessageItem> getReplies() {
        if (replies == null) {
            replies = new ArrayList<MessageItem>();
        }
        return replies;
    }

    /**
     * <h3>getIsRead()</h3>
     * Returns a bool indicating if the message has been read.
     * @return isRead (Type: boolean, indicates if the message is read)
     */
    public boolean getIsRead() {
        return isRead;
    }




    //Setters

    /**
     * <h3>setRecipient(String recipient)</h3>
     * Sets the recipient of the message to the given value.
     * @param recipient (Type: String, the new recipient of the message)
     */
    public void setRecipient(String recipient) {
        if (recipient != null) {
            this.recipient = recipient;
        } else {
            this.recipient = "";
        }
    }

    /**
     * <h3>addReply(MessageItem newReply)</h3>
     * Adds a new reply to the reply chain.
     * @param newReply (Type: MessageItem, the new reply to add)
     */
    public void addReply(MessageItem newReply) {
        if (newReply != null) {
            if (replies == null) {
                replies = new ArrayList<MessageItem>();
            }
            replies.add(newReply);
        }
    }

    /**
     * <h3>setIsRead(boolean read)</h3>
     * Sets the isRead value to the given boolean.
     * @param read (Type: boolean, indicates if the message has been read)
     */
    public void setIsRead(boolean read) {
        isRead = read;
    }
}