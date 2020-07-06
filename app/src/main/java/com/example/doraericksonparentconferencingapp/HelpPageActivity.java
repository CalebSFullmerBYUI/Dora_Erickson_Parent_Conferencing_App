package com.example.doraericksonparentconferencingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * <h3>HelpPageActivity Activity</h3>
 * Displays the help page which has information about how to use the
 * app. It also would have the phone number for the District 91 IT
 * department, and a link to any applicable District 91 websites.
 * References:
 *      https://developer.android.com/training/basics/intents/sending
 *      https://www.tutorialspoint.com/android/android_phone_calls.htm
 *          These websites discuss how to activate Activities on other apps
 *          by using implicit Intents.
 *      https://stackoverflow.com/questions/12022646/setting-contentdescription-from-label-for-android-accessibility
 *          This website discusses View objects' contentDescription variable.
 * @author Caleb Fullmer
 * @since June 20, 2020
 * @version 1.1
 */
public class HelpPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_page);
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


    /**
     * <h3>startCall(View view)</h3>
     * Call the phone number indicated byt he View object. Expects contentDescription
     * of View to have a phone number in the string form "tel:0000000000".
     * That's 000-000-0000 when written normally.
     * @param view (Type: View, the object which triggered the method)
     */
    public void startCall(View view) {
        String newPhoneNum = view.getContentDescription().toString();
        Intent newCall = new Intent(Intent.ACTION_DIAL, Uri.parse(newPhoneNum));
        startActivity(newCall);
    }
}