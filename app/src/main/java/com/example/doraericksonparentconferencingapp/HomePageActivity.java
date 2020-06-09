package com.example.doraericksonparentconferencingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

/**
 * <h3></h3>HomePageActivity Activity</h3>
 * The home page of the app. From here, the user can access their messages,
 * schedule, account info, and (if they are a teacher or admin) their classroom.
 * @author Caleb Fullmer
 * @since June 9, 2020
 * @version 0.1
 */
public class HomePageActivity extends AppCompatActivity {
    //Variables
    private User currentUser = null;
    private List<ScheduleItem> announcements = new ArrayList<ScheduleItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
    }



    //Backend
    /**
     * <h3>refreshAnnouncements()</h3>
     * Sends a request to the server to get the user's announcements.
     */
    public void refreshAnnouncements() {

    }

    /**
     * <h3>displayAnnouncements()</h3>
     * Displays the user's announcements on the screen.
     */
    public void displayAnnouncements() {

    }




    //Start new Activity
    /**
     * <h3>startHelpActivity(View view)</h3>
     * Creates a new instance of the HelpActivity and directs the user
     * to it.
     * @param view (Type: View, the view object which triggered the method)
     */
    public void startHelpActivity(View view) {

    }

    /**
     * <h3>startLinksActivity(View view)</h3>
     * Starts a new instance of the LinksActivity.
     * @param view (Type: View, the view object which triggered the method)
     */
    public void startLinksActivity(View view) {

    }

    /**
     * <h3>startClassroomsActivity(View view)</h3>
     * Creates a new instance of the ClassroomActivity. This will only
     * trigger for teacher and admin users.
     * @param view (Type: View, the view object which triggered the method)
     */
    public void startClassroomsActivity(View view) {

    }

    /**
     * <h3>startDirectoryActivity(View view)</h3>
     * Creates a new instance of the DirectoryActivity.
     * @param view (Type: View, the view object which triggered the method)
     */
    public void startDirectoryActivity(View view) {

    }

    /**
     * <h3>startScheduleActivity(View view)</h3>
     * Starts a new instance of the ScheduleActivity.
     * @param view (Type: View, the view object which triggered the method)
     */
    public void startScheduleActivity(View view) {

    }

    /**
     * <h3>startsMessagesActivity(View view)</h3>
     * Starts a new instance of the MessagesActivity.
     * @param view (Type: View, the view object which triggered the method)
     */
    public void startMessagesActivity(View view) {

    }

    /**
     * <h3>startAccountActivity(View view)</h3>
     * Starts a new instance of the AccountActivity.
     * @param view (Type: View, the view object which triggered the method)
     */
    public void startAccountActivity(View view) {

    }

    /**
     * <h3>logout(View view)</h3>
     * Logs the user out and returns them to the login screen.
     * @param view (Type: View, the view object which triggered the method)
     */
    public void logout(View view) {

    }
}