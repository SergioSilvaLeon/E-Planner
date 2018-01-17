package com.tecnologiasintech.e_planner.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tecnologiasintech.e_planner.R;
import com.tecnologiasintech.e_planner.model.Student;

public class UpdateStudentActivity extends AppCompatActivity {

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("EPlanner/Student");

    EditText editTextName,editTextEmail,editTextSchool,editTextTechnology,editTextOrginization;

    Spinner spinnerEditTShirtSize;
    Spinner spinnerEditSemester;

    private String tShirtSizeSelected, semesterSelected;

    String[] semesterArray;
    String[] tShirtArray;


    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);

        semesterArray = getResources().getStringArray(R.array.semester_array);
        tShirtArray = getResources().getStringArray(R.array.tshirt_array);



        editTextName = (EditText) findViewById(R.id.editText_name);
        editTextEmail = (EditText) findViewById(R.id.editText_email);
        editTextSchool = (EditText) findViewById(R.id.editText_school);

        spinnerEditTShirtSize = (Spinner) findViewById(R.id.spinnerEditTShirtSize);
        ArrayAdapter<CharSequence> adapterEditTShirtSize = ArrayAdapter.createFromResource(this,
                R.array.tshirt_array, android.R.layout.simple_spinner_dropdown_item);
        adapterEditTShirtSize.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEditTShirtSize.setAdapter(adapterEditTShirtSize);
        spinnerEditTShirtSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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


        spinnerEditSemester = (Spinner) findViewById(R.id.spinnerEditSemester);
        ArrayAdapter<CharSequence> adapterEditSemester = ArrayAdapter.createFromResource(this,
                R.array.semester_array, android.R.layout.simple_spinner_dropdown_item);
        adapterEditSemester.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEditSemester.setAdapter(adapterEditSemester);
        spinnerEditSemester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                semesterSelected = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        Button buttonCreateUser = (Button) findViewById(R.id.btn_update_Student);
        buttonCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser();
            }
        });


        Intent intent  = getIntent();
        String name = intent.getStringExtra(StudentActivity.EXTRA_NAME);
        String email = intent.getStringExtra(StudentActivity.EXTRA_EMAIL);
        String school = intent.getStringExtra(StudentActivity.EXTRA_SCHOOL);
        tshirtSize = intent.getStringExtra(StudentActivity.EXTRA_TSHIRTSIZE);
        String technology = intent.getStringExtra(StudentActivity.EXTRA_TECHNOLOGY);
        String orginiaztion = intent.getStringExtra(StudentActivity.EXTRA_ORGINIAZTION);
        semester = intent.getStringExtra(StudentActivity.EXTRA_SEMESTER);
        key = intent.getStringExtra(StudentActivity.EXTRA_KEY);


        editTextName.setText(name);
        editTextEmail.setText(email);
        editTextSchool.setText(school);

        // Set Current TShirt Size Spinner

        // Get array from R.array.tshirt_array





        editTextTechnology.setText(technology);
        editTextOrginization.setText(orginiaztion);

        // Set Current Semester Spinner
        // Get array from R.array.tshirt_array



    }

    String tshirtSize;
    String semester;


    @Override
    protected void onStart() {
        super.onStart();

        // TODO: Get Both Spinners show preselection

        for (int j = 0;  j < semesterArray.length; j++){
            if (semesterArray[j].equals(semester)){
                spinnerEditSemester.setSelection(j);
                return;
            }
        }

        for (int i = 0;  i < tShirtArray.length; i++){
            if (tShirtArray[i].equals(tshirtSize)){
                spinnerEditTShirtSize.setSelection(i);
                return;
            }
        }

    }

    private void updateUser() {

        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String school = editTextSchool.getText().toString();
        String technology = editTextTechnology.getText().toString();
        String orginiaztion = editTextOrginization.getText().toString();

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


        Student student = new Student(name,email,school,tShirtSizeSelected,technology,orginiaztion,semesterSelected,key);
        //Update student to firebase
        ref.child(key).setValue(student);
        finish();
    }
}
