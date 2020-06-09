package com.example.doraericksonparentconferencingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

/**
 * <h3>ScheduleActivity Activity</h3>
 * Displays the user's schedule in a weekly schedule format.
 * @author Caleb Fullmer
 * @since June 9, 2020
 * @version 0.1
 */
public class ScheduleActivity extends AppCompatActivity {
    //Uncomment this once ScheduleItem exists.
    //private List<ScheduleItem> scheduledItems = new ArrayList<ScheduleItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
    }


    /**
     * <h3>getScheduleInfo()</h3>
     * Sends a request to the server to get the user's schedule.
     */
    public void getScheduleInfo() {

    }

    /**
     * <h3>displayInfo()</h3>
     * Displays the user's schedule info.
     */
    public void displayInfo() {

    }

    /**
     * <h3>refreshInfo(View view)</h3>
     * Allows user to update schedule info.
     * @param view (Type: View, the object which triggered the method)
     */
    public void refreshInfo(View view) {

    }

    /**
     * <h3>loadNextWeek(View view)</h3>
     * Loads the next week of the user's schedule.
     * @param view (Type: View, the object which triggered the method)
     */
    public void loadNextWeek(View view) {

    }

    /**
     * <h3>loadPrevWeek(View view)</h3>
     * Loads the previous week of the user's schedule.
     * @param view (Type: View, the object which triggered the method)
     */
    public void loadPrevWeek(View view) {

    }
}