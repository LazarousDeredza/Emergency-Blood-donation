package com.example.emergencyblooddonation;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ForgotPassword extends AppCompatActivity {
    private ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar=getSupportActionBar();
        assert actionBar != null;

        actionBar.setTitle("Password Recovery");
        setContentView(R.layout.activity_forgot_password);
    }
}