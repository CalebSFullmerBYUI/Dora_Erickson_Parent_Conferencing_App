package com.example.doraericksonparentconferencingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    public static String UNIQUE_ID = null;
    public static final String ID_KEY = "userID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UNIQUE_ID = getSharedPreferences(ID_KEY, MODE_PRIVATE).getString(ID_KEY, "fail");

        if ((UNIQUE_ID != null) && !UNIQUE_ID.equals("")) {
            //Check that server accepts id. if so, start home page activity.
        }

        Log.d("UNIQUE_ID", UNIQUE_ID);
        System.out.println(UNIQUE_ID);

        TestLoginInfo(new View(getApplicationContext()));
    }

    public void TestLoginInfo(View view) {
        String strJson = new ServerRequest().request("", "");
        //User newUser = new Gson().fromJson(strJson, User.class);
        //UNIQUE_ID = newUser.getUniqueId();
        SharedPreferences.Editor newEditor = getSharedPreferences(ID_KEY, MODE_PRIVATE).edit();
        newEditor.putString(ID_KEY, UNIQUE_ID);
        newEditor.apply();
        //Start home page activity.
    }
}
