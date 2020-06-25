package com.example.doraericksonparentconferencingapp;


import java.util.ArrayList;

/**
 * <h3>TeacherItemVector</h3>
 * Wraps an ArrayList of MessageItems to make Json conversion from server
 * responses easier.
 * @author Caleb Fullmer
 * @since June 25, 2020
 * @version 1.0
 */
public class TeacherItemVector {
    private ArrayList<TeacherItem> teachers = null;

    //Constructors
    /**
     * <h3>TeacherItemVector()</h3>
     * Default Constructor
     */
    public TeacherItemVector() {
        setVector(new ArrayList<TeacherItem>());
    }

    /**
     * <h3>TeacherItemVector(TeacherItemVector vectorToCopy)</h3>
     * Copy Constructor
     * @param vectorToCopy (Type: TeacherItemVector, the vector to copy)
     */
    public TeacherItemVector(TeacherItemVector vectorToCopy) {
        this.teachers = vectorToCopy.teachers;
    }




    //Getters and Setters
    /**
     * <h3>getVector()</h3>
     * Gets the vector being wrapped.
     * @return messages (Type: ArrayList<TeacherItem>, the wrapped vector)
     */
    public ArrayList<TeacherItem> getVector() {
        if (teachers == null) {
            teachers = new ArrayList<TeacherItem>();
        }

        return teachers;
    }

    /**
     * <h3>setVector(ArrayList<TeacherItem> newVector)</h3>
     * Sets the wrapped vector to a new vector.
     * @param newVector (ArrayList<TeacherItem>, the new vector to wrap)
     */
    public void setVector(ArrayList<TeacherItem> newVector) {
        if (newVector != null) {
            teachers = newVector;
        } else {
            teachers = new ArrayList<TeacherItem>();
        }
    }
}
