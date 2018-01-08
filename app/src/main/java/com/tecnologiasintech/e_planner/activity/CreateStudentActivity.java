package com.tecnologiasintech.e_planner.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tecnologiasintech.e_planner.R;
import com.tecnologiasintech.e_planner.model.Student;

public class CreateStudentActivity extends AppCompatActivity {

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("EPlanner/Student");

    EditText editTextName,editTextEmail,editTextSchool,editTextTShirtSize,editTextTechnology,
            editTextOrginization,editTextSemester;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_student);

        //Know which reference to update


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

         editTextName = (EditText) findViewById(R.id.editText_name);
         editTextEmail = (EditText) findViewById(R.id.editText_email);
         editTextSchool = (EditText) findViewById(R.id.editText_school);
         editTextTShirtSize = (EditText) findViewById(R.id.editText_tshirtsize);
         editTextTechnology = (EditText) findViewById(R.id.editText_technologies);
         editTextOrginization = (EditText) findViewById(R.id.editText_orginization);
         editTextSemester = (EditText) findViewById(R.id.editText_semester);

        Button buttonCreateUser = (Button) findViewById(R.id.btn_create_Student);

        buttonCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });


    }

    private void createUser() {
        // get input and save to firebase
        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String school = editTextSchool.getText().toString();
        String tShirtSize = editTextTShirtSize.getText().toString();
        String technology = editTextTechnology.getText().toString();
        String orginization = editTextOrginization.getText().toString();
        String semester = editTextSemester.getText().toString();

        // Get Key;
        String key = ref.push().getKey();


        Student student = new Student(name,email,school,tShirtSize,technology,orginization,semester,key);
        //Update student to firebase
        ref.child(key).setValue(student);


        finish();


    }
}
