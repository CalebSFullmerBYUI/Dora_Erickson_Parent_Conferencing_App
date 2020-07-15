package com.example.doraericksonparentconferencingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

public class ClassroomActivity extends AppCompatActivity {
    public static final String CLASSROOM_ID_KEY = "ClassRoomId KEY";
    private ArrayList<String> hours = null;
    private TeacherItem teacher = null;
    private ArrayList<ScheduleItem> announcements = new ArrayList<ScheduleItem>();
    private boolean displayAsAdmin = false;
    User currentUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classroom);

        if (getIntent().getStringArrayExtra(HomePageActivity.USER_KEY) != null) {
            currentUser = new Gson().fromJson(getIntent().getStringExtra(HomePageActivity.USER_KEY), User.class);
        } else {
            Log.e("DirectoryActivity.onCreate()", "Error: No known User passed in.");
            currentUser = null;

            Toast errorToast = Toast.makeText(getApplicationContext(), "User not recognized.", Toast.LENGTH_LONG);
            errorToast.show();
        }



        //Get classroomId
        int classId = getIntent().getIntExtra(CLASSROOM_ID_KEY, -444);

        if (classId != -444) {
            getClassroomInfo(classId);
        } else {
            Toast errorToast = Toast.makeText(getApplicationContext(), "Issue getting teacher info.", Toast.LENGTH_LONG);
        }
    }


    /**
     * <h3>getClassroomInfo(int classId)</h3>
     * Retrieves teacher info and teacher announcements from the server.
     * @param classId (Type: int, the id of the class)
     */
    public void getClassroomInfo(int classId) {
        Thread getClassInfoThread = new Thread(new CustomRun(this) {
            @Override
            public void run() {
                final String jsonResponseTeach = new ServerRequest().request("",/*"?classId=" + classId*/"");
                final String jsonResponseAnnouncements = new ServerRequest().request("", /*"?classId=" + classId*/"");

                currentActivity.get().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if ((jsonResponseTeach != null) && !jsonResponseTeach.equals("")) {
                            String mockResponseTeach = "{'name': 'Mr. Test', 'className': '2nd Grade', 'classId': 0, 'email': 'test@blabla.com', 'phoneNum': 2083333333}";
                            teacher = new Gson().fromJson(mockResponseTeach/*jsonResponse*/, TeacherItem.class);

                            if ((currentUser != null) && (teacher.getClassId() == currentUser.getClassId()) &&
                                    currentUser.isAdmin()) {
                                displayAsAdmin = true;
                            } else {
                                displayAsAdmin = false;
                            }

                            //Read in announcements
                            if ((jsonResponseAnnouncements != null) && !jsonResponseAnnouncements.equals("")) {
                                String mockResponseAnnouncements = "{'announcements': [" +
                                        "{'sender': 'Mr. Test', 'subject': 'test', 'message': 'test', 'sentDate': 0, 'dueDate': 0, 'isHomework': false}" +
                                        "]}";
                                announcements = new Gson().fromJson(mockResponseAnnouncements, ScheduleItemVector.class).getVector();
                            } else {
                                announcements = new ArrayList<ScheduleItem>();
                                Log.e("ClassroomActivity.getClassroomInfo()", "Invalid ScheduleItemVector response from server.");
                                Toast errorToast = Toast.makeText(currentActivity.get().getApplicationContext(),
                                        "Issue getting announcements.", Toast.LENGTH_LONG);
                                errorToast.show();
                            }


                            displayInfo();
                        } else {
                            Log.e("ClassroomActivity.getClassroomInfo()", "Invalid TeacherItem response from server.");
                            Toast errorToast = Toast.makeText(currentActivity.get().getApplicationContext(),
                                    "Issue getting teacher info.", Toast.LENGTH_LONG);
                            errorToast.show();
                        }
                    }
                });
            }
        });

        getClassInfoThread.start();
    }


    /**
     * <h3>displayInfo()</h3>
     * Displays the teacher information and announcements.
     */
    public void displayInfo() {
        canEdit();

        if (announcements == null) {
            announcements = new ArrayList<ScheduleItem>();
        }

        if (teacher != null) {
            ((TextView) findViewById(R.id.txtView_TeacherName)).setText(teacher.getName());
            ((TextView)findViewById(R.id.txtView_ClassType)).setText(teacher.getClassName());
            ((TextView)findViewById(R.id.txtView_Email)).setText(teacher.getEmail());
        } else {
            teacher = new TeacherItem("","",-1);
            ((TextView) findViewById(R.id.txtView_TeacherName)).setText("Name");
            ((TextView)findViewById(R.id.txtView_ClassType)).setText("Class Type");
            ((TextView)findViewById(R.id.txtView_Email)).setText("Email");
        }



        //Display announcements
        ((LinearLayout)findViewById(R.id.linLay_Announcements)).removeAllViews();

        for (ScheduleItem item: announcements) {
            AnnouncementView newView = new AnnouncementView(getApplicationContext(), false);

            newView.setSender(teacher.getName());
            newView.setSubject(item.getSubject());
            if (item.getIsHomework()) {
                newView.setSentDate(item.getDueDate());
            } else {
                newView.setSentDate(item.getDate());
            }
            newView.setMessage(item.getMessage());
            newView.setTag(item);

            if (displayAsAdmin) {
                newView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        createNewAnnouncement(v);
                    }
                });
            }

            ((LinearLayout)findViewById(R.id.linLay_Announcements)).addView(newView);
        }
    }


    /**
     * <h3>canEdit()</h3>
     * Displays the AddHomework button if the user is the correct teacher.
     */
    public void canEdit() {
        if (displayAsAdmin) {
            ((Button)findViewById(R.id.btn_AddHomework)).setVisibility(View.VISIBLE);
        } else {
            ((Button)findViewById(R.id.btn_AddHomework)).setVisibility(View.GONE);
        }
    }

    /*
    public void uploadEdits() {

    }
    */


    /**
     * <h3>createNewAnnoucement(View view)</h3>
     * Allows the teacher to add or change announcements and homework assignments.
     * @param view (Type: View, the View which called the method)
     */
    public void createNewAnnouncement(View view) {
        Intent newIntent = new Intent(this, NewScheduleItemActivity.class);
        if (currentUser != null) {
            newIntent.putExtra(HomePageActivity.USER_KEY, new Gson().toJson(currentUser));
        }
        newIntent.putExtra(NewScheduleItemActivity.IS_TEACHER_ANNOUNCE_KEY, true);

        if ((view.getTag() != null) && (view.getTag() instanceof ScheduleItem)) {
            newIntent.putExtra(NewScheduleItemActivity.SCHEDULE_ITEM_NAME, new Gson().toJson((ScheduleItem)view.getTag()));
        }

        startActivity(newIntent);
    }
}