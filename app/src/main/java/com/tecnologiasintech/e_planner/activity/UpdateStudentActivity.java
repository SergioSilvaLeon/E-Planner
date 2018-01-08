package com.tecnologiasintech.e_planner.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tecnologiasintech.e_planner.R;
import com.tecnologiasintech.e_planner.model.Student;

public class UpdateStudentActivity extends AppCompatActivity {

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("EPlanner/Student");

    EditText editTextName,editTextEmail,editTextSchool,editTextTShirtSize,editTextTechnology,
            editTextOrginization,editTextSemester;

    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);

        editTextName = (EditText) findViewById(R.id.editText_name);
        editTextEmail = (EditText) findViewById(R.id.editText_email);
        editTextSchool = (EditText) findViewById(R.id.editText_school);
        editTextTShirtSize = (EditText) findViewById(R.id.editText_tshirtsize);
        editTextTechnology = (EditText) findViewById(R.id.editText_technologies);
        editTextOrginization = (EditText) findViewById(R.id.editText_orginization);
        editTextSemester = (EditText) findViewById(R.id.editText_semester);

        Button buttonCreateUser = (Button) findViewById(R.id.btn_update_Student);

        Intent intent  = getIntent();
        String name = intent.getStringExtra(StudentActivity.EXTRA_NAME);
        String email = intent.getStringExtra(StudentActivity.EXTRA_EMAIL);
        String school = intent.getStringExtra(StudentActivity.EXTRA_SCHOOL);
        String tshirtSize = intent.getStringExtra(StudentActivity.EXTRA_TSHIRTSIZE);
        String technology = intent.getStringExtra(StudentActivity.EXTRA_TECHNOLOGY);
        String orginiaztion = intent.getStringExtra(StudentActivity.EXTRA_ORGINIAZTION);
        String semester = intent.getStringExtra(StudentActivity.EXTRA_SEMESTER);
        key = intent.getStringExtra(StudentActivity.EXTRA_KEY);


        editTextName.setText(name);
        editTextEmail.setText(email);
        editTextSchool.setText(school);
        editTextTShirtSize.setText(tshirtSize);
        editTextTechnology.setText(technology);
        editTextOrginization.setText(orginiaztion);
        editTextSemester.setText(semester);

        buttonCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser();
            }
        });
    }

    private void updateUser() {

        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String school = editTextSchool.getText().toString();
        String tshirtSize = editTextTShirtSize.getText().toString();
        String technology = editTextTechnology.getText().toString();
        String orginiaztion = editTextOrginization.getText().toString();
        String semester = editTextSemester.getText().toString();


        Student student = new Student(name,email,school,tshirtSize,technology,orginiaztion,semester,key);
        //Update student to firebase
        ref.child(key).setValue(student);
        finish();
    }
}
