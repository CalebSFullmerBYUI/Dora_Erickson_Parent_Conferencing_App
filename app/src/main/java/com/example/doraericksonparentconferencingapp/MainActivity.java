package com.example.doraericksonparentconferencingapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    public static String UNIQUE_ID = null;
    public static final String ID_KEY = "userID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void recover (View view) {
        startActivity(new Intent(MainActivity.this, AccountRecoveryActivity.class));
    }

    public void signUp (View view) {
        startActivity(new Intent(MainActivity.this, AccountCreationActivity.class));
    }

    public void logIn(View view) {
        final EditText e1 = (EditText) findViewById(R.id.editText3);
        final EditText e2 = (EditText) findViewById(R.id.editText3);
        String username = e1.toString();
        String password = e2.toString();

        /*SELECT *
        FROM usernameTable
        INNER JOIN paswordTable ON usernameTableID = passwordTableID
        WHERE usernameTable.username == username && passwordTable.password == password*/
        String strJson = new ServerRequest().request("", "");

        if ((strJson != null) && !strJson.equals("")) {
            User newUser = new Gson().fromJson(strJson, User.class);
            UNIQUE_ID = newUser.getUniqueId();
            SharedPreferences.Editor newEditor = getSharedPreferences(ID_KEY, MODE_PRIVATE).edit();
            newEditor.putString(ID_KEY, UNIQUE_ID);
            newEditor.apply();

            startActivity(new Intent(MainActivity.this, HomePageActivity.class));
        } else {
            Toast errorToast = Toast.makeText(getApplicationContext(), "Error logging in.", Toast.LENGTH_LONG);
            errorToast.show();
            Log.e("MainActivity.logIn()", "Invalid server response.");
        }

    }

}