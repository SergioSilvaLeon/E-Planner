package com.tecnologiasintech.e_planner.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.tecnologiasintech.e_planner.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the view of the three layouts
        View viewEvent = findViewById(R.id.activity_main_view_event);
        View viewMentor = findViewById(R.id.activity_main_view_mentor);
        View viewStudent = findViewById(R.id.activity_main_view_student);

        viewEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open Event Activity
                Intent intent = new Intent
                        (MainActivity.this, EventActivity.class);
                startActivity(intent);

            }
        });

        viewMentor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open Mentor Activity
                Intent intent = new Intent(
                        MainActivity.this, MentorActivity.class
                );
                startActivity(intent);
            }
        });

        viewStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open Student Acivity
                Intent intent = new Intent(
                        MainActivity.this, StudentActivity.class
                );
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_sign_out){
            userSignOut();
        }


        if (item.getItemId() == R.id.action_change_password){
            changeUserPassword();
        }

        if (item.getItemId() == R.id.action_manage_stack){
            manageTechnologyStacks();
        }

        return super.onOptionsItemSelected(item);
    }

    private void changeUserPassword() {
        startActivity(new Intent(this, ChangePasswordActivity.class));
    }

    private void userSignOut() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private void manageTechnologyStacks(){
        // Create a section in firebase
        startActivity(new Intent(this, ManageStacksActivity.class));
        // add, delete

        // expose in mentor activity

    }

}
