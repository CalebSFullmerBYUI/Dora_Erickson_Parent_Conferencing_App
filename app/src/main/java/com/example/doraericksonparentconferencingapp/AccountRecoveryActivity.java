package com.example.doraericksonparentconferencingapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AccountRecoveryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_recovery);
    }
    public void recoverAccount(View view) {
        final EditText e1 = (EditText) findViewById(R.id.editText5);
        String email = e1.toString();
        // send the dumb email and some other stuff. I don't know, I haven't researched it yet.
        startActivity(new Intent(AccountRecoveryActivity.this, AccountCreationActivity.class));
    }
}
