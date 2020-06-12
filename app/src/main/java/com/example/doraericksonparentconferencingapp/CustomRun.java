package com.example.doraericksonparentconferencingapp;

import android.app.Activity;

import java.lang.ref.WeakReference;

/**
 * <h3>CustomRun class</h3>
 * A custom version of the runnable interface. The run method is blank and must be
 * overloaded. Takes a weak reference to the activity which called it which allows for
 * the UI thread to be accessed.
 * @author Caleb Fullmer
 * @since June 12, 2020
 * @version 0.1
 */
public class CustomRun implements Runnable {
    WeakReference<Activity> currentActivity = null;

    /**
     * <h3>CustomRun(Activity currentActivity)</h3>
     * Creates a new CustomRun object with a weak reference to the activity
     * which called it.
     * @param currentActivity (Type: Activity, the activity which called the
     *                        CustomRun object)
     */
    public CustomRun(Activity currentActivity) {
        this.currentActivity = new WeakReference<Activity>(currentActivity);
    }

    @Override
    public void run() {

    }
}
