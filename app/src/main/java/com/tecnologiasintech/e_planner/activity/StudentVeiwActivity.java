package com.tecnologiasintech.e_planner.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.tecnologiasintech.e_planner.R;

public class StudentVeiwActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_veiw);

        // set back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // set text views
        TextView nameTxt = (TextView) findViewById(R.id.nameTxt);
        TextView emailTxt = (TextView) findViewById(R.id.emailTxt);
        TextView schoolTxt = (TextView) findViewById(R.id.schoolTxt);
        TextView tshirtSizeTxt = (TextView) findViewById(R.id.tshirtSizeTxt);
        TextView technologyTxt = (TextView) findViewById(R.id.technologyTxt);
        TextView orginizationTxt = (TextView) findViewById(R.id.orginizationTxt);
        TextView semesterTxt = (TextView) findViewById(R.id.semesterTxt);


        // Unload intent
        Intent intent  = getIntent();
        String name = intent.getStringExtra(StudentActivity.EXTRA_NAME);
        String email = intent.getStringExtra(StudentActivity.EXTRA_EMAIL);
        String school = intent.getStringExtra(StudentActivity.EXTRA_SCHOOL);
        String tshirtSize = intent.getStringExtra(StudentActivity.EXTRA_TSHIRTSIZE);
        String technology = intent.getStringExtra(StudentActivity.EXTRA_TECHNOLOGY);
        String orginiaztion = intent.getStringExtra(StudentActivity.EXTRA_ORGINIAZTION);
        String semester = intent.getStringExtra(StudentActivity.EXTRA_SEMESTER);

        nameTxt.setText(name);
        emailTxt.setText(email);
        schoolTxt.setText(school);
        tshirtSizeTxt.setText(tshirtSize);
        technologyTxt.setText(technology);
        orginizationTxt.setText(orginiaztion);
        semesterTxt.setText(semester);

    }
}
