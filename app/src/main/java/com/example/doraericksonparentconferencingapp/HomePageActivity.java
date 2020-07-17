package com.example.doraericksonparentconferencingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/**
 * <h3></h3>HomePageActivity Activity</h3>
 * The home page of the app. From here, the user can access their messages,
 * schedule, account info, and (if they are a teacher or admin) their classroom.
 * References:
 *      https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
 *          Official JavaDocs on SimpleDateFormat and date formatting.
 * @author Caleb Fullmer
 * @since June 25, 2020
 * @version 1.2
 */
public class HomePageActivity extends AppCompatActivity {
    //Variables
    private User currentUser = null;
    private ArrayList<ScheduleItem> announcements = new ArrayList<ScheduleItem>();
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MMM d, yyyy 'at' hh:mm a");
    public static final String USER_KEY = "currentUser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

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
                                String mockResponse = MockResponses.UserResponse(MainActivity.UNIQUE_ID);

                                currentUser = new Gson().fromJson(mockResponse /*userJson*/, User.class);

                                if (!currentUser.isAdmin()) {
                                    ((Button)findViewById(R.id.btn_Classroom)).setVisibility(View.GONE);
                                } else {
                                    ((Button)findViewById(R.id.btn_Classroom)).setVisibility(View.VISIBLE);
                                }

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

            startUp.start();
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
                final ArrayList<ScheduleItem> updatedAnnouncements;
                if ((responseJson != null) && (!responseJson.equals(""))) {
                    //mockResponse used in place of userJson for prototype.
                    String mockResponse = MockResponses.GetAllAnnouncements();

                    ArrayList<ScheduleItem> temp = convertJson.fromJson(mockResponse/*responseJson*/,
                            ScheduleItemVector.class).getVector();
                    sortScheduleItems(temp);

                     updatedAnnouncements = temp;
                     sortScheduleItems(updatedAnnouncements);
                } else {
                    updatedAnnouncements = null;
                }

                //Run displayAnnouncements() on UI thread.
                currentActivity.get().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if ((updatedAnnouncements != null) && (!updatedAnnouncements.isEmpty())) {
                            announcements = updatedAnnouncements;
                            displayAnnouncements();
                        } else {
                            announcements = new ArrayList<ScheduleItem>();
                            Toast error = Toast.makeText(currentActivity.get().getApplicationContext(),
                                    "Error getting announcements.", Toast.LENGTH_LONG);
                        }
                    }
                });
            }
        });

        updateAnnouncements.start();
    }

    /**
     * <h3>displayAnnouncements()</h3>
     * Displays the user's announcements on the screen.
     */
    public void displayAnnouncements() {
        if (announcements == null) {
            announcements = new ArrayList<ScheduleItem>();
        }

        ((LinearLayout)findViewById(R.id.linLay_Announcements)).removeAllViews();

        for (ScheduleItem item: announcements) {
            if (item != null) {
                Date itemDate = item.getDate();

                if (item.getIsHomework()) {
                    itemDate = item.getDueDate();
                }

                AnnouncementView newAnnouncement = new AnnouncementView(getApplicationContext(), false);
                newAnnouncement.setSender(item.getSender());
                newAnnouncement.setSubject(item.getSubject());
                newAnnouncement.setSentDate(itemDate);
                newAnnouncement.setMessage(item.getMessage());
                ((LinearLayout)findViewById(R.id.linLay_Announcements)).addView(newAnnouncement);
            }
        }
    }


    /**
     * <h3>sortScheduleItems(ArrayList)<ScheduleItem> listToSort</h3>
     * Sorts an ArrayList of ScheduleItems by date.
     * @param listToSort (Type: ArrayList<ScheduleItem>, the list to sort)
     */
    public void sortScheduleItems(ArrayList<ScheduleItem> listToSort) {
        Collections.sort(listToSort, new Comparator<ScheduleItem>() {
            @Override
            public int compare(ScheduleItem item1, ScheduleItem item2) {
                Date item1Date = item1.getDate();
                Date item2Date = item2.getDate();

                if (item1.getIsHomework() == true) {
                    item1Date = item1.getDueDate();
                }

                if (item2.getIsHomework() == true) {
                    item2Date = item2.getDueDate();
                }


                if (item1Date.before(item2Date)) {
                    return 1;
                } else if (item2Date.before(item1Date)) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
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
        Intent newClassroom = new Intent(this, ClassroomActivity.class);
        if (currentUser != null) {
            newClassroom.putExtra(USER_KEY, new Gson().toJson(currentUser));

            if (currentUser.isAdmin()) {
                newClassroom.putExtra(ClassroomActivity.CLASSROOM_ID_KEY, currentUser.getClassId());
            }
        }
        startActivity(newClassroom);
    }

    /**
     * <h3>startDirectoryActivity(View view)</h3>
     * Creates a new instance of the DirectoryActivity.
     * @param view (Type: View, the view object which triggered the method)
     */
    public void startDirectoryActivity(View view) {
        Intent newDirectory = new Intent(this, DirectoryActivity.class);
        if (currentUser != null) {
            newDirectory.putExtra(USER_KEY, new Gson().toJson(currentUser));
        }
        startActivity(newDirectory);
    }

    /**
     * <h3>startScheduleActivity(View view)</h3>
     * Starts a new instance of the ScheduleActivity.
     * @param view (Type: View, the view object which triggered the method)
     */
    public void startScheduleActivity(View view) {
        Intent newSchedule = new Intent(this, ScheduleActivity.class);
        if (currentUser != null) {
            newSchedule.putExtra(USER_KEY, new Gson().toJson(currentUser));
        }
        startActivity(newSchedule);
    }

    /**
     * <h3>startsMessagesActivity(View view)</h3>
     * Starts a new instance of the MessagesActivity.
     * @param view (Type: View, the view object which triggered the method)
     */
    public void startMessagesActivity(View view) {
        Intent newMessagesActivity = new Intent(this, MessagingActivity.class);
        if (currentUser != null) {
            newMessagesActivity.putExtra(USER_KEY, new Gson().toJson(currentUser));
        }
        startActivity(newMessagesActivity);
    }

    /**
     * <h3>startAccountActivity(View view)</h3>
     * Starts a new instance of the AccountActivity.
     * @param view (Type: View, the view object which triggered the method)
     */
    public void startAccountActivity(View view) {
        Intent newViewProfile = new Intent(this, viewProfile.class);
        if (currentUser != null) {
            newViewProfile.putExtra(USER_KEY, new Gson().toJson(currentUser));
        }
        startActivity(newViewProfile);
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