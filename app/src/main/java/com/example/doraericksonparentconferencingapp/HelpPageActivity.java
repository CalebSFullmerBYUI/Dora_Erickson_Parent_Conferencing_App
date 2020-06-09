package com.example.doraericksonparentconferencingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * <h3>HelpPageActivity Activity</h3>
 * Displays the help page which has information about how to use the
 * app. It also would have the phone number for the District 91 IT
 * department, and a link to any applicable District 91 websites.
 * @author Caleb Fullmer
 * @since June 9, 2020
 * @version 0.1
 */
public class HelpPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_page);
    }
}