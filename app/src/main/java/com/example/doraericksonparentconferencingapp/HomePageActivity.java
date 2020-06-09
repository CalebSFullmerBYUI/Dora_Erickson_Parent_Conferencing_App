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
     * <h3>RefreshAnnouncements()</h3>
     * Sends a request to the server to get the user's announcements.
     */
    public void RefreshAnnouncements() {

    }

    /**
     * <h3>DisplayAnnouncements()</h3>
     * Displays the user's announcements on the screen.
     */
    public void DisplayAnnouncements() {

    }




    //Start new Activity
    /**
     * <h3>StartHelpActivity(View view)</h3>
     * Creates a new instance of the HelpActivity and directs the user
     * to it.
     * @param view (Type: View, the view object which triggered the method)
     */
    public void StartHelpActivity(View view) {

    }

    /**
     * <h3>StartLinksActivity(View view)</h3>
     * Starts a new instance of the LinksActivity.
     * @param view (Type: View, the view object which triggered the method)
     */
    public void StartLinksActivity(View view) {

    }

    /**
     * <h3>StartClassroomsActivity(View view)</h3>
     * Creates a new instance of the ClassroomActivity. This will only
     * trigger for teacher and admin users.
     * @param view (Type: View, the view object which triggered the method)
     */
    public void StartClassroomsActivity(View view) {

    }

    /**
     * <h3>StartDirectoryActivity(View view)</h3>
     * Creates a new instance of the DirectoryActivity.
     * @param view (Type: View, the view object which triggered the method)
     */
    public void StartDirectoryActivity(View view) {

    }

    /**
     * <h3>StartScheduleActivity(View view)</h3>
     * Starts a new instance of the ScheduleActivity.
     * @param view (Type: View, the view object which triggered the method)
     */
    public void StartScheduleActivity(View view) {

    }

    /**
     * <h3>StartsMessagesActivity(View view)</h3>
     * Starts a new instance of the MessagesActivity.
     * @param view (Type: View, the view object which triggered the method)
     */
    public void StartMessagesActivity(View view) {

    }

    /**
     * <h3>StartAccountActivity(View view)</h3>
     * Starts a new instance of the AccountActivity.
     * @param view (Type: View, the view object which triggered the method)
     */
    public void StartAccountActivity(View view) {

    }

    /**
     * <h3>Logout(View view)</h3>
     * Logs the user out and returns them to the login screen.
     * @param view (Type: View, the view object which triggered the method)
     */
    public void Logout(View view) {

    }
}