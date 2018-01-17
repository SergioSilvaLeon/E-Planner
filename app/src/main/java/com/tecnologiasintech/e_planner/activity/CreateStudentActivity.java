package com.tecnologiasintech.e_planner.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

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

    private String tShirtSizeSelected, semesterSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_student);

        //Know which reference to update


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

         editTextName = (EditText) findViewById(R.id.editText_name);
         editTextEmail = (EditText) findViewById(R.id.editText_email);
         editTextSchool = (EditText) findViewById(R.id.editText_school);

         Spinner spinnerTShirtSize = (Spinner) findViewById(R.id.spinnerTShirtSize);
         ArrayAdapter<CharSequence> adapterTShirtSize = ArrayAdapter.createFromResource(this,
                R.array.tshirt_array, android.R.layout.simple_spinner_dropdown_item);
         adapterTShirtSize.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         spinnerTShirtSize.setAdapter(adapterTShirtSize);
         spinnerTShirtSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 tShirtSizeSelected = parent.getItemAtPosition(position).toString();
             }

             @Override
             public void onNothingSelected(AdapterView<?> parent) {

             }
         });

         editTextTechnology = (EditText) findViewById(R.id.editText_technologies);
         editTextOrginization = (EditText) findViewById(R.id.editText_orginization);

        Spinner spinnerSemester = (Spinner) findViewById(R.id.spinnerSemester);
        ArrayAdapter<CharSequence> adapterSemester = ArrayAdapter.createFromResource(this,
                R.array.semester_array, android.R.layout.simple_spinner_dropdown_item);
        adapterSemester.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSemester.setAdapter(adapterSemester);
        spinnerSemester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                semesterSelected = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
        String technology = editTextTechnology.getText().toString();
        String orginization = editTextOrginization.getText().toString();

        // name
        if (TextUtils.isEmpty(name)){
            editTextName.setError("Enter full name!");
            return;
        }
        if (name.length() >= 50){
            editTextName.setError("Name field too long!");
        }

        if (!name.matches("[a-zA-Z ]+")){
            editTextName.setError("Enter a valid name");
        }
        // email
        if (TextUtils.isEmpty(email)) {
            editTextEmail.setError("Enter email address!");
            return;
        }

        if (!email.contains("@") || !email.contains(".")){
            editTextEmail.setError("Please enter a valid email!");
            return;
        }
        // School



        // Enter Technologies
        if (!technology.matches("[a-zA-Z ]+")){
            editTextTechnology.setError("Enter a valid name");
        }
        // Enter Organization



        // Get Key;
        String key = ref.push().getKey();


        Student student = new Student(name,email,school,tShirtSizeSelected,technology,orginization,semesterSelected,key);
        //Update student to firebase
        ref.child(key).setValue(student);


        finish();


    }
}
