package com.example.doraericksonparentconferencingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

/**
 * <h3>LinksActivity Activity</h3>
 * Displays links to other District 91 resources, such as the District 91
 * website, PowerSchool, and Google Classrooms.
 */
public class LinksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_links);
    }

    /**
     * <h3>goToLink(View view)</h3>
     * Open the link indicated by the View object in the user's default
     * web browser.
     * @param view (Type: View, the object which triggered the method)
     */
    public void goToLink(View view) {

    }
}