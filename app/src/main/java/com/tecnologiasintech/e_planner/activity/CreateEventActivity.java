package com.tecnologiasintech.e_planner.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tecnologiasintech.e_planner.R;
import com.tecnologiasintech.e_planner.model.Event;

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

        // get key
        String key = ref.push().getKey();

        Event event = new Event(name, date, description, isPopular, host, key);
        ref.child(key).setValue(event);


        finish();
    }
}
