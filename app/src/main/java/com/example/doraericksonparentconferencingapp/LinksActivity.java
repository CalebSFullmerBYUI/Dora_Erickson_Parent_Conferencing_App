package com.example.doraericksonparentconferencingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * <h3>LinksActivity Activity</h3>
 * Displays links to other District 91 resources, such as the District 91
 * website, PowerSchool, and Google Classrooms.
 * References:
 *      https://developer.android.com/training/basics/intents/sending
 *      https://www.tutorialspoint.com/android/android_phone_calls.htm
 *          These websites discuss how to activate Activities on other apps
 *          by using implicit Intents.
 * @author Caleb Fullmer
 * @since June 20, 2020
 * @version 1.0
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
     * web browser. Expects view to have text content which represents a valid URL.
     * @param view (Type: TextView, the object which triggered the method)
     */
    public void goToLink(View view) {
        //Must be a TextView item.
        assert (view instanceof TextView);

        String newURL = ((TextView)view).getText().toString();
        Intent newSearch = new Intent(Intent.ACTION_VIEW, Uri.parse(newURL));
        startActivity(newSearch);
    }
}