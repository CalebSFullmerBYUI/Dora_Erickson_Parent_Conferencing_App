package com.example.doraericksonparentconferencingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * <h3></h3>HomePageActivity Activity</h3>
 * The home page of the app. From here, the user can access their messages,
 * schedule, account info, and (if they are a teacher or admin) their classroom.
 * References:
 *      https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
 *          Official JavaDocs on SimpleDateFormat and date formatting.
 * @author Caleb Fullmer
 * @since June 9, 2020
 * @version 0.1
 */
public class HomePageActivity extends AppCompatActivity {
    //Variables
    //Uncomment these once User and ScheduleItem exist.
    //private User currentUser = null;
    //private List<ScheduleItem> announcements = new ArrayList<ScheduleItem>();
    public static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MMM d, yyyy 'at' hh:mm a");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Thread startUp = new Thread(new Runnable() {
            @Override
            public void run() {
                //Get user from server.
                //set announcements equal to the user's main announcements list.
            }
        });
    }



    //Backend
    /**
     * <h3>refreshAnnouncements()</h3>
     * Sends a request to the server to get the user's announcements.
     */
    public void refreshAnnouncements() {
        Thread updateAnnouncements = new Thread(new Runnable() {
            @Override
            public void run() {
                Gson convertJson = new Gson();
                String responseJson = "";
                //User updatedAnnouncements = null;
                //Get user data from server.
                //updatedAnnouncements = convertJson.fromJson(responseJson, User.class);

                //Run displayAnnouncements() on UI thread.
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //currentUser.setAnnouncements(updatedAnnouncements.getAnnouncements);
                        displayAnnouncements();
                    }
                });
            }
        });
    }

    /**
     * <h3>displayAnnouncements()</h3>
     * Displays the user's announcements on the screen.
     */
    public void displayAnnouncements() {
        //List<Integer> test = new ArrayList<>();
        //View newAnnouncement = new AnnouncementView(getApplicationContext(), false);
    }




    //Start new Activity
    /**
     * <h3>startHelpActivity(View view)</h3>
     * Creates a new instance of the HelpActivity and directs the user
     * to it.
     * @param view (Type: View, the view object which triggered the method)
     */
    public void startHelpActivity(View view) {
        Intent newHelpIntent = new Intent(this, HelpPageActivity.class);
        startActivity(newHelpIntent);
    }

    /**
     * <h3>startLinksActivity(View view)</h3>
     * Starts a new instance of the LinksActivity.
     * @param view (Type: View, the view object which triggered the method)
     */
    public void startLinksActivity(View view) {
        Intent newLinksIntent = new Intent(this, LinksActivity.class);
        startActivity(newLinksIntent);
    }

    /**
     * <h3>startClassroomsActivity(View view)</h3>
     * Creates a new instance of the ClassroomActivity. This will only
     * trigger for teacher and admin users.
     * @param view (Type: View, the view object which triggered the method)
     */
    public void startClassroomsActivity(View view) {
        /*if (currentUser.getIsAdmin) {
            Intent newClassroom = new Intent(this, ClassroomsActivity.class);
            newClassroom.putExtra("isAdmin", currentUser.getIsAdmin());
            startActivity(newClassroom)
        }*/
    }

    /**
     * <h3>startDirectoryActivity(View view)</h3>
     * Creates a new instance of the DirectoryActivity.
     * @param view (Type: View, the view object which triggered the method)
     */
    public void startDirectoryActivity(View view) {
        Intent newDirectory = new Intent(this, DirectoryActivity.class);
        startActivity(newDirectory);
    }

    /**
     * <h3>startScheduleActivity(View view)</h3>
     * Starts a new instance of the ScheduleActivity.
     * @param view (Type: View, the view object which triggered the method)
     */
    public void startScheduleActivity(View view) {
        Intent newSchedule = new Intent(this, ScheduleActivity.class);
        startActivity(newSchedule);
    }

    /**
     * <h3>startsMessagesActivity(View view)</h3>
     * Starts a new instance of the MessagesActivity.
     * @param view (Type: View, the view object which triggered the method)
     */
    public void startMessagesActivity(View view) {
        //Intent newMessagesActivity = new Intent(this, MessagingActivity.class);
        //startActivity(newMessagesActivity);
    }

    /**
     * <h3>startAccountActivity(View view)</h3>
     * Starts a new instance of the AccountActivity.
     * @param view (Type: View, the view object which triggered the method)
     */
    public void startAccountActivity(View view) {
        //Intent newViewProfile = new Intent(this, ViewProfile.class);
        //startActivity(newViewProfile);
    }

    /**
     * <h3>logout(View view)</h3>
     * Logs the user out and returns them to the login screen.
     * @param view (Type: View, the view object which triggered the method)
     */
    public void logout(View view) {

    }
}