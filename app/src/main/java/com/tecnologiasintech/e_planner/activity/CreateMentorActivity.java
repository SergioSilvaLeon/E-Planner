package com.tecnologiasintech.e_planner.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tecnologiasintech.e_planner.R;
import com.tecnologiasintech.e_planner.model.User;

public class CreateMentorActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText inputEmail, inputfullName;
    private Button btnSignUp;
    private CheckBox mCheckBox;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private static final String PASSWORD = "nearsoft";
    private String stack_selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_mentor);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        inputEmail = (EditText) findViewById(R.id.email);
        inputfullName = (EditText) findViewById(R.id.fullName);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        // Create an ArrayAdapter using th estring array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.stack_array, android.R.layout.simple_spinner_dropdown_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);



        mCheckBox = (CheckBox) findViewById(R.id.checkBoxAdmin);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String fullName = inputfullName.getText().toString();
                final String email = inputEmail.getText().toString().trim();


                // Full Name
                if (TextUtils.isEmpty(fullName)) {
                    inputfullName.setError("Enter full name !");
                    return;
                }

                if (fullName.length() < 5) {
                    inputfullName.setError("Name too short, enter minimum 5 characters!");
                    return;
                }

                // alphabetic
                if (!fullName.matches("[a-zA-Z ]+")){
                    inputfullName.setError("please check characters entered");
                    return;
                }


                // Email
                if (TextUtils.isEmpty(email)) {
                    inputEmail.setError("Enter email address!");
                    return;
                }

                if (!email.contains("@") || !email.contains(".")){
                    inputEmail.setError("Please enter a valid email!");
                    return;
                }



                progressBar.setVisibility(View.VISIBLE);
                //create user
                auth.createUserWithEmailAndPassword(email, PASSWORD)
                        .addOnCompleteListener(CreateMentorActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(CreateMentorActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(CreateMentorActivity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    // TODO: Create new User and Register in database

                                    // Get Key


                                    DatabaseReference databaseReference =
                                            FirebaseDatabase.getInstance().getReference("EPlanner/User");

                                    String key = databaseReference.push().getKey();

                                    User user = new User(email, fullName, mCheckBox.isChecked(),stack_selected, key);


                                    databaseReference.child(key).setValue(user);

                                    finish();

                                }
                            }
                        });

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        stack_selected  = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
