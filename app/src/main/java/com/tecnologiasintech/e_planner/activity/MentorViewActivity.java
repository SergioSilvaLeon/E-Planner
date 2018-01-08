package com.tecnologiasintech.e_planner.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.tecnologiasintech.e_planner.R;

public class MentorViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_view);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView nameTxt = (TextView) findViewById(R.id.nameTxt);
        TextView emailTxt = (TextView) findViewById(R.id.emailTxt);
        TextView adminTxt = (TextView) findViewById(R.id.adminTxt);
        TextView stackTxt = (TextView) findViewById(R.id.stackTxt);

        Intent intent = getIntent();
        String name = intent.getStringExtra(MentorActivity.EXTRA_NAME);
        String email = intent.getStringExtra(MentorActivity.EXTRA_EMAIL);
        String admin = intent.getStringExtra(MentorActivity.EXTRA_ADMIN);
        String stack = intent.getStringExtra(MentorActivity.EXTRA_STACK);

        nameTxt.setText(name);
        emailTxt.setText(email);
        adminTxt.setText(admin);
        stackTxt.setText(stack);
    }
}
