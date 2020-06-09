package com.example.doraericksonparentconferencingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

/**
 * <h3>DirectoryActivity Activity</h3>
 * Allows the user to access teacher and admin info. From here
 * the user can access individual teacher and admin classrooms.
 * @author Caleb Fullmer
 * @since June 9 2020
 * @version 0.1
 */
public class DirectoryActivity extends AppCompatActivity {
    //Uncomment this once TeacherItem exists.
    //private List<TeacherItem> teachers = new ArrayListist<TeacherItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);
    }


    /**
     * <h3>getDirectoryInfo()</h3>
     * Sends a request to the server to get the directory info.
     */
    public void getDirectoryInfo() {

    }

    /**
     * <h3>displayDirectory()</h3>
     * Displays the teachers and admins by class type (Ex: 1st Grade,
     * counselor, etc.) Teachers are arranged alphabetically within each
     * category.
     */
    public void displayDirectory() {

    }

    /**
     * <h3>goToClassroom(view view)</h3>
     * Redirects the user to the classroom page indicated by the view
     * object.
     * @param view (Type: View, the object which triggered the method)
     */
    public void goToClassroom(View view) {

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