package com.example.cs246project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class viewProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
    }
    //private List subscriptions
    private User currentUser;

    public void changeUsername(View view) {
        startActivity(new Intent(viewProfile.this, ResetAccountActivity.class));
        }
    public void changePassword(View view) {
        startActivity(new Intent(viewProfile.this, ResetAccountActivity.class));
    }
    public void removeSubscription(View view) {
        // find subscription in list and remove it
        // copy to new list?
    }
}
