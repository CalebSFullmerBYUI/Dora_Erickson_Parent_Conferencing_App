package com.example.doraericksonparentconferencingapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
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
        /*
        SELECT *
        FROM usernameTable
        INNER JOIN paswordTable ON usernameTableID = passwordTableID
        WHERE usernameTable.username == username && passwordTable.password == password

        if query returns any results, take to homepage via next line
            startActivity(new Intent(MainActivity.this, HomepageActivity.class));
         */
    }

}