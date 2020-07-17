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

        UNIQUE_ID = getSharedPreferences(ID_KEY, MODE_PRIVATE).getString(ID_KEY, "fail");

        if ((UNIQUE_ID != null) && !UNIQUE_ID.equals("")) {
            //Check that server accepts id. if so, start home page activity.
        }


        //For testing purposes
        Toast testInstructionsToast = Toast.makeText(getApplicationContext(), "Enter admin to test as admin.", Toast.LENGTH_LONG);
        testInstructionsToast.show();
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
        final String username = e1.getText().toString();
        String password = e2.getText().toString();

        /*SELECT *
        FROM usernameTable
        INNER JOIN paswordTable ON usernameTableID = passwordTableID
        WHERE usernameTable.username == username && passwordTable.password == password*/
        Thread getServerDataThread = new Thread(new CustomRun(this) {
            @Override
            public void run() {
                final String strJson = new ServerRequest().request("", "");

                currentActivity.get().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if ((strJson != null) && !strJson.equals("")) {
                            //Gets the appropriate mock response.
                            String mockUser = MockResponses.UserResponse(username);

                            User newUser = new Gson().fromJson(mockUser/*strJson*/, User.class);
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
                });
            }
        });

        getServerDataThread.start();
    }

}