package com.example.doraericksonparentconferencingapp;

import java.util.ArrayList;

/**
 * <h3>MessageItemVector</h3>
 * Wraps an ArrayList of MessageItems to make Json conversion from server
 * responses easier.
 * @author Caleb Fullmer
 * @since June 25, 2020
 * @version 1.0
 */
public class MessageItemVector {
    private ArrayList<MessageItem> messages = null;

    //Constructors
    /**
     * <h3>MessageItemVector()</h3>
     * Default Constructor
     */
    public MessageItemVector() {
        setVector(new ArrayList<MessageItem>());
    }

    /**
     * <h3>MessageItemVector(MessageItemVector vectorToCopy)</h3>
     * Copy Constructor
     * @param vectorToCopy (Type: MessageItemVector, the vector to copy)
     */
    public MessageItemVector(MessageItemVector vectorToCopy) {
        this.messages = vectorToCopy.messages;
    }




    //Getters and Setters
    /**
     * <h3>getVector()</h3>
     * Gets the vector being wrapped.
     * @return messages (Type: ArrayList<MessageItem>, the wrapped vector)
     */
    public ArrayList<MessageItem> getVector() {
        if (messages == null) {
            messages = new ArrayList<MessageItem>();
        }

        return messages;
    }

    /**
     * <h3>setVector(ArrayList<MessageItem> newVector)</h3>
     * Sets the wrapped vector to a new vector.
     * @param newVector (ArrayList<MessageItem>, the new vector to wrap)
     */
    public void setVector(ArrayList<MessageItem> newVector) {
        if (newVector != null) {
            messages = newVector;
        } else {
            messages = new ArrayList<MessageItem>();
        }
    }
}
