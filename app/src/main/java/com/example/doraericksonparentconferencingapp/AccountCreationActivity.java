package com.example.doraericksonparentconferencingapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AccountCreationActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_creation_activity);
    }

    public void createUser(View view) {
        final EditText e1 = (EditText) findViewById(R.id.editText6);
        final EditText e2 = (EditText) findViewById(R.id.editText);
        final EditText e3 = (EditText) findViewById(R.id.editText7);
        String username = e1.toString();
        String passwd = e2.toString();
        String email = e3.toString();
        // query db, if email or username already exist, alert error
        // else,
        // db push username, password, and email to db
        // send email to user as well
        startActivity(new Intent(AccountCreationActivity.this, MainActivity.class));
    }
}
