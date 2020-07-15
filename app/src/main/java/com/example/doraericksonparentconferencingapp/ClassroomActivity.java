package com.example.doraericksonparentconferencingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
                            String mockResponseTeach = "";
                            teacher = new Gson().fromJson(mockResponseTeach/*jsonResponse*/, TeacherItem.class);

                            if ((currentUser != null) && (teacher.getClassId() == currentUser.getClassId()) &&
                                    currentUser.isAdmin()) {
                                displayAsAdmin = true;
                            }

                            //Read in announcements
                            if ((jsonResponseAnnouncements != null) && !jsonResponseAnnouncements.equals("")) {
                                String mockResponseAnnouncements = "";
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

    public void displayInfo() {
        canEdit();
    }

    public void canEdit() {
    }

    public void uploadEdits() {

    }

    public void createNewAnnouncement(View view) {

    }
}