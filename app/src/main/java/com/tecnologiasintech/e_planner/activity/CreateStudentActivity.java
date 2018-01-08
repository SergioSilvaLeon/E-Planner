package com.tecnologiasintech.e_planner.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tecnologiasintech.e_planner.R;

public class CreateStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_student);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
