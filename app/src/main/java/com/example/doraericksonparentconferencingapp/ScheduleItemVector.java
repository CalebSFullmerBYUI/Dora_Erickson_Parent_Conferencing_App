package com.example.doraericksonparentconferencingapp;

import java.util.ArrayList;

/**
 * <h3>ScheduleItemVector</h3>
 * Wraps an ArrayList of ScheduleItems to make Json conversion from server
 * responses easier.
 * @author Caleb Fullmer
 * @since June 25, 2020
 * @version 1.0
 */
public class ScheduleItemVector {
    private ArrayList<ScheduleItem> announcements = null;

    //Constructors
    /**
     * <h3>ScheduleItemVector()</h3>
     * Default Constructor
     */
    public ScheduleItemVector() {
        setVector(new ArrayList<ScheduleItem>());
    }

    /**
     * <h3>ScheduleItemVector(ScheduleItemVector vectorToCopy)</h3>
     * Copy Constructor
     * @param vectorToCopy (Type: ScheduleItemVector, the vector to copy)
     */
    public ScheduleItemVector(ScheduleItemVector vectorToCopy) {
        this.announcements = vectorToCopy.announcements;
    }




    //Getters and Setters
    /**
     * <h3>getVector()</h3>
     * Gets the vector being wrapped.
     * @return messages (Type: ArrayList<ScheduleItem>, the wrapped vector)
     */
    public ArrayList<ScheduleItem> getVector() {
        if (announcements == null) {
            announcements = new ArrayList<ScheduleItem>();
        }

        return announcements;
    }

    /**
     * <h3>setVector(ArrayList<ScheduleItem> newVector)</h3>
     * Sets the wrapped vector to a new vector.
     * @param newVector (ArrayList<ScheduleItem>, the new vector to wrap)
     */
    public void setVector(ArrayList<ScheduleItem> newVector) {
        if (newVector != null) {
            announcements = newVector;
        } else {
            announcements = new ArrayList<ScheduleItem>();
        }
    }
}
