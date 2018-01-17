package com.tecnologiasintech.e_planner.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tecnologiasintech.e_planner.R;
import com.tecnologiasintech.e_planner.model.Event;

public class EventUpdateActivity extends AppCompatActivity {

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("EPlanner/Event");

    CheckBox mCheckBox;
    EditText editTextName,editTextDate,editTextDescription,editTextHost;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_update);

        editTextName = (EditText) findViewById(R.id.editText_name);
        editTextDate = (EditText) findViewById(R.id.editText_date);
        editTextDescription = (EditText) findViewById(R.id.editText_description);
        editTextHost = (EditText) findViewById(R.id.editText_host);

        mCheckBox = (CheckBox) findViewById(R.id.checkBoxEditPopular);

        Button button = (Button) findViewById(R.id.btn_update_Event);

        Intent intent  = getIntent();
        String name = intent.getStringExtra(EventActivity.EXTRA_NAME);
        String date = intent.getStringExtra(EventActivity.EXTRA_DATE);
        String description = intent.getStringExtra(EventActivity.EXTRA_DESCRIPTION);
        String host = intent.getStringExtra(EventActivity.EXTRA_HOST);
        String isPopular = intent.getStringExtra(EventActivity.EXTRA_POPULAR);

        key = intent.getStringExtra(EventActivity.EXTRA_KEY);

        editTextName = (EditText) findViewById(R.id.editText_name);
        editTextDate = (EditText) findViewById(R.id.editText_date);
        editTextDescription = (EditText) findViewById(R.id.editText_description);
        editTextHost = (EditText) findViewById(R.id.editText_host);

        // update editTextView
        editTextName.setText(name);
        editTextDate.setText(date);
        editTextDescription.setText(description);
        editTextHost.setText(host);


        if (isPopular.equals("true")){
            mCheckBox.setChecked(true);
        }else {
            mCheckBox.setChecked(false);
        }



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateEvent();
            }
        });


    }

    private void updateEvent() {

        String name = editTextName.getText().toString();
        String date = editTextDate.getText().toString();
        String description = editTextDescription.getText().toString();
        String host = editTextHost.getText().toString();
        boolean ispopular = mCheckBox.isChecked();

        /*
        * Todo: Add Date Logic
        * **/


        // Name of the event
        if (TextUtils.isEmpty(name)){
            editTextName.setError("Enter the name of the Event!");
            return;
        }

        if (name.length() > 30){
            editTextName.setError("Please vertify the length of the name of the event");
            return;
        }

        // Description of the event
        if (description.length() > 120){
            editTextDescription.setError
                    ("Please, vertify the length of the description is less 120 characters");
            return;
        }

        // Host of the event
        if (TextUtils.isEmpty(host)){
            editTextHost.setError("Please enter the name of the host");
            return;
        }

        if (host.length() < 5 || host.length() > 30){
            editTextHost.setError
                    ("Please, vertift the length of the host is between 5 - 30 characters");

            return;
        }

        // Alphabetic
        if (!host.matches("[a-zA-Z ]+")){
            editTextHost.setError("Solo se acceptan datos alphabetics");
            return;
        }

        Event event = new Event(name,date,description,ispopular,host,key);

        ref.child(key).setValue(event);
        finish();

    }
}
