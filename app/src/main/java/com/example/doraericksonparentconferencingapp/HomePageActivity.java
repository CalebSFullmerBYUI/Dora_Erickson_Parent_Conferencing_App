package com.example.doraericksonparentconferencingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * <h3></h3>HomePageActivity Activity</h3>
 * The home page of the app. From here, the user can access their messages,
 * schedule, account info, and (if they are a teacher or admin) their classroom.
 * References:
 *      https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
 *          Official JavaDocs on SimpleDateFormat and date formatting.
 * @author Caleb Fullmer
 * @since June 22, 2020
 * @version 1.1
 */
public class HomePageActivity extends AppCompatActivity {
    //Variables
    private User currentUser = null;
    private ArrayList<ScheduleItem> announcements = new ArrayList<ScheduleItem>();
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MMM d, yyyy 'at' hh:mm a");
    public ArrayAdapter<AnnouncementView> announceAdapter = new ArrayAdapter<AnnouncementView>(this,
            android.R.layout.simple_list_item_1, new ArrayList<AnnouncementView>());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //Set up adapter for announcements listview.
        ((ListView)findViewById(R.id.lstView_Announcements)).setAdapter(announceAdapter);

        if (MainActivity.UNIQUE_ID != null) {
            //Get user info
            Thread startUp = new Thread(new CustomRun(this) {
                @Override
                public void run() {
                    //Gets the currentUser as a Json object.
                    //Add file path and variable keys.
                    final String userJson = new ServerRequest().request("", "");

                    currentActivity.get().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if ((userJson != null) && (!userJson.equals(""))) {
                                //Set user and set announcements.

                                //mockResponse used in place of userJson for prototype.
                                String mockResponse = "{'name': 'John Doe', 'email': 'JohnDoe@fakeEmail.com', " +
                                        "'classroom': '', 'classId': 0, 'isAdmin': false, 'uniqueId': 'FakeId'}";

                                currentUser = new Gson().fromJson(mockResponse /*userJson*/, User.class);

                                //This is for testing admin and teacher accounts.
                                //currentUser.setIsAdmin(true);

                                refreshAnnouncements();
                            } else {
                                Toast errorToast = Toast.makeText(currentActivity.get().getApplicationContext(),
                                        "Could not log-in. Please Restart App", Toast.LENGTH_LONG);
                                errorToast.show();
                            }
                        }
                    });
                }
            });
        }
    }



    //Backend
    /**
     * <h3>refreshAnnouncements()</h3>
     * Sends a request to the server to get the user's announcements.
     */
    public void refreshAnnouncements() {
        Thread updateAnnouncements = new Thread(new CustomRun(this) {
            @Override
            public void run() {
                Gson convertJson = new Gson();
                //Insert file path and variable keys.
                String responseJson = new ServerRequest().request("", "");
                final HomePageActivity updatedAnnouncements;
                if ((responseJson != null) && (!responseJson.equals(""))) {
                    //mockResponse used in place of userJson for prototype.
                    String mockResponse = "{'announcements': [" +
                            "{'sender': 'admin1', 'subject': " +
                            "'New School Policy', 'message': 'We will be implementing a new " +
                            "school policy starting October 12, 2020. It requires students to " +
                            "be on time to class.', 'sentDate': '1598652343', 'dueDate': 0, " +
                            "'isHomework': false}," +
                            "{'sender': 'admin1', 'subject': 'Welcome Back Event', 'message': " +
                            "'The school will be hosting a welcome back event for the start of " +
                            "the school year on September 4th.', 'sentDate': '1597356343', 'dueDate': " +
                            "1599689143, 'isHomework': true}," +
                            "{'sender': 'admin3', 'subject': 'PTO Meeting on August 24', 'message': " +
                            "'We will be having a PTO meeting on August 24th to discuss the upcoming " +
                            "school year.', 'sentDate': '1598652343', 'dueDate': 1598306743, " +
                            "'isHomework': true}," +
                            "{'sender': 'admin1', 'subject': 'Gradebook Changes', 'message': " +
                            "'Starting September 4th, there will be changes to the Gradebook app. " +
                            "Check PowerSchool for more details.', 'sentDate': '1598652343', " +
                            "'dueDate': null, 'isHomework': false}" +
                            "]}";
                     updatedAnnouncements = convertJson.fromJson(mockResponse/*responseJson*/,
                            HomePageActivity.class);
                } else {
                    updatedAnnouncements = null;
                }

                //Run displayAnnouncements() on UI thread.
                currentActivity.get().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if ((updatedAnnouncements != null) && (updatedAnnouncements.announcements != null)) {
                            announcements = updatedAnnouncements.announcements;
                            displayAnnouncements();
                        } else {
                            Toast error = Toast.makeText(currentActivity.get().getApplicationContext(),
                                    "Error getting announcements.", Toast.LENGTH_LONG);
                        }
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
        if (announcements == null) {
            announcements = new ArrayList<ScheduleItem>();
        }

        if (announceAdapter == null) {
            announceAdapter = new ArrayAdapter<AnnouncementView>(this,
                    android.R.layout.simple_list_item_1, new ArrayList<AnnouncementView>());
        }

        announceAdapter.clear();

        for (SchedlueItem item: announcements) {
            if (item != null) {
                AnnouncementView newAnnouncement = new AnnouncementView(getApplicationContext(), false);
                newAnnouncement.setSender(item.getSender());
                newAnnouncement.setSubject(item.getSubject());
                newAnnouncement.setSentDate(DATE_FORMAT.format(item.getDate()));
                newAnnouncement.setMessage(item.getMessage());
                announceAdapter.add(newAnnouncement);
            }
        }
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
        //Intent newClassroom = new Intent(this, ClassroomsActivity.class);
        //newClassroom.putExtra("isAdmin", currentUser.getIsAdmin());
        //newClassroom.putExtra("classId", currentUser.getClassId());
        //startActivity(newClassroom);
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
        finish();
    }
}