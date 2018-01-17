package com.tecnologiasintech.e_planner.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tecnologiasintech.e_planner.R;
import com.tecnologiasintech.e_planner.model.Event;

import org.w3c.dom.Text;

public class CreateEventActivity extends AppCompatActivity {

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("EPlanner/Event");

    EditText editTextName,editTextDate,editTextDescription,editTextHost;
    CheckBox mCheckBoxPopular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editTextName = (EditText) findViewById(R.id.editText_name);
        editTextDate = (EditText) findViewById(R.id.editText_date);
        editTextDescription = (EditText) findViewById(R.id.editText_description);
        editTextHost = (EditText) findViewById(R.id.editText_host);
        mCheckBoxPopular = (CheckBox) findViewById(R.id.checkBoxPopular  );

        Button buttonCreateEvent = (Button) findViewById(R.id.btn_create_Event);

        buttonCreateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createEvent();
            }
        });
    }

    private void createEvent() {

        // Get data and upload it
        // Get input

        // upload to firebase
        String name = editTextName.getText().toString();
        String date = editTextDate.getText().toString();
        String description = editTextDescription.getText().toString();
        String host = editTextHost.getText().toString();
        boolean isPopular = mCheckBoxPopular.isChecked();


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


        // get key
        String key = ref.push().getKey();

        Event event = new Event(name, date, description, isPopular, host, key);
        ref.child(key).setValue(event);


        finish();
    }
}
