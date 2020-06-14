package com.example.doraericksonparentconferencingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * <h3>DirectoryActivity Activity</h3>
 * Allows the user to access teacher and admin info. From here
 * the user can access individual teacher and admin classrooms.
 * @author Caleb Fullmer
 * @since June 9 2020
 * @version 0.1
 */
public class DirectoryActivity extends AppCompatActivity {
    //Variables
    private ArrayList<TeacherItem> teachers = new ArrayListist<TeacherItem>();
    private TreeMap<String, ArrayList<TeacherItem>> gradeSortTeachers =
            new TreeMap<String, ArrayList<TeacherItem>>();
    public ArrayAdapter<AnnouncementView> directoryAdapter = new ArrayAdapter<AnnouncementView>(this,
            android.R.layout.simple_list_item_1, new ArrayList<AnnouncementView>());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);

        ((ListView)findViewById(R.id.lstView_Announcements)).setAdapter(directoryAdapter);

        getDirectoryInfo();
    }


    /**
     * <h3>getDirectoryInfo()</h3>
     * Sends a request to the server to get the directory info.
     */
    public void getDirectoryInfo() {
        Thread getDirThread = new Thread(new CustomRun(this) {
            @Override
            public void run() {
                String dirJson = new ServerRequest().request("", "");
                final DirectoryActivity newData;

                if ((dirJson != null) && (dirJson != "")) {
                    DirectoryActivity newDirectory = new Gson().fromJson(dirJson, DirectoryActivity.class);
                    alphabetizeTeacherVector(newDirectory.teachers);
                    newDirectory.sortTeachersByGrade();

                    newData = newDirectory;
                } else {
                    newData = null;
                }

                currentActivity.get().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (newData != null) {
                            teachers = newData.teachers;
                            gradeSortTeachers = newData.gradeSortTeachers;
                            displayDirectory();
                        } else {
                            Toast errorToast = Toast.makeText(currentActivity.get().getApplicationContext(),
                                    "Error loading directory.", Toast.LENGTH_LONG);
                            errorToast.show();
                        }
                    }
                });
            }
        });
    }

    /**
     * <h3>displayDirectory()</h3>
     * Displays the teachers and admins by class type (Ex: 1st Grade,
     * counselor, etc.) Teachers are arranged alphabetically within each
     * category.
     */
    public void displayDirectory() {
        if (teachers == null) {
            teachers = new ArrayListist<TeacherItem>();
        }

        if (gradeSortTeachers == null) {
            gradeSortTeachers = new TreeMap<String, ArrayList<TeacherItem>>();
        }

        if (directoryAdapter == null) {
            directoryAdapter = new ArrayAdapter<AnnouncementView>(this,
                    android.R.layout.simple_list_item_1, new ArrayList<AnnouncementView>());
        }

        for (Map.Entry<String, ArrayList<TeacherItem>> it: gradeSortTeachers.entrySet()) {
            //Add data to be displayed as nessisary.
        }
    }

    /**
     * <h3>sortTeachersByGrade()</h3>
     * Sorts the given list of teachers by grade in a TreeMap.
     */
    private void sortTeachersByGrade() {
        if (teachers == null) {
            teachers = new ArrayListist<TeacherItem>();
        }

        if (gradeSortTeachers == null) {
            new TreeMap<String, ArrayList<TeacherItem>>();
        }

        for (TeacherItem teacher: teachers) {
            if (teacher != null) {
                if (gradeSortTeachers.containsKey(teacher.getClassName())) {
                    gradeSortTeachers.get(teacher.getClassName()).add(teacher);
                } else {
                    ArrayList<TeacherItem> newList = new ArrayList<>();
                    newList.add(teacher);
                    gradeSortteachers.put(teacher.getClassName(), newList);
                }
            }
        }

        for (Map.Entry<String, ArrayList<TeacherItem>> it: gradeSortTeachers.entrySet()) {
            if (it.getValue().size() > 1) {
                alphabetizeTeacherVector(it.getValue());
            }
        }
    }

    /**
     * <h3>alphabetizeTeachervector(ArrayList<TeacherItem> teacherVector)</h3>
     * Sorts the teachers in the list by first name alphabetizes.
     * @param teacherVector (Type: ArrayList<TeacherItem>, the teacher list to sort)
     */
    private void alphabetizeTeacherVector(ArrayList<TeacherItem> teacherVector) {
        Collections.sort(teacherVector, new Comparator<TeacherItem>() {
            @Override
            public int compare(TeacherItem teach1, TeacherItem teach2) {
                //Change this to be last name if we decide to have seperate last and first names.
                return teach1.getName() > teach2.getName();
            }
        });
    }

    /**
     * <h3>goToClassroom(view view)</h3>
     * Redirects the user to the classroom page indicated by the view
     * object.
     * @param view (Type: View, the object which triggered the method)
     */
    public void goToClassroom(View view) {
        //Intent newClassroom = new Intent(this, ClassroomsActivity.class);
        //newClassroom.putExtra("isAdmin", currentUser.getIsAdmin());
        //newClassroom.putExtra("classId", currentUser.getClassId());
        //startActivity(newClassroom);
    }



    //This would require a boolean variable to keep track of the desired setting.
    /**
     * <h3>switchDisplay(View view)</h3>
     * Toggles between displaying teachers alphabetically or by class type.
     * @param view (Type: View, the object which triggered the method)
     */
    /*
    public void switchDisplay(View view) {

    }
    */
}