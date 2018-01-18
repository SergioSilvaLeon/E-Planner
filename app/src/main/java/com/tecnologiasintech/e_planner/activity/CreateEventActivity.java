package com.tecnologiasintech.e_planner.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tecnologiasintech.e_planner.R;
import com.tecnologiasintech.e_planner.model.Event;


import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CreateEventActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("EPlanner/Event");

    EditText editTextName,editTextDescription,editTextHost;
    CheckBox mCheckBoxPopular;
    String eventDateSelected;
    LinearLayout viewDate;
    Spinner spinner;
    boolean specific = true;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editTextName = (EditText) findViewById(R.id.editText_name);
        editTextDescription = (EditText) findViewById(R.id.editText_description);
        editTextHost = (EditText) findViewById(R.id.editText_host);
        mCheckBoxPopular = (CheckBox) findViewById(R.id.checkBoxPopular);

        // SetUp Views
        viewDate  = (LinearLayout) findViewById(R.id.view_date);




        spinner  = (Spinner) findViewById(R.id.spinnerFechaEvento);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.evento_array, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                eventDateSelected = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner.setVisibility(View.GONE);

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
        String description = editTextDescription.getText().toString();
        String host = editTextHost.getText().toString();
        boolean isPopular = mCheckBoxPopular.isChecked();


        /*
        * Todo: Add Date Logic
        * **/

        if (!specific){
            date = eventDateSelected;
        }

        if (date == null){
            Toast.makeText(this, "Please, select a Date", Toast.LENGTH_SHORT).show();
            return;
        }


        // Name of the event
        if (TextUtils.isEmpty(name)){
            editTextName.setError("Enter the name of the Event!");
            return;
        }

        if (name.length() > 30 && name.length() < 5){
            editTextName.setError("Please, vertift the length of the Event is between 5 - 30 characters");
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
                    ("Please, vertify the length of the host is between 5 - 30 characters");

            return;
        }

        // Alphabetic
        if (!host.matches("[a-zA-Z ]+")){
            editTextHost.setError("Solo se acceptan datos alphabetics");
            return;
        }


        // get key
        String key = ref.push().getKey();


        Event event = new Event(name, date, description, isPopular, host, key,specific);
        ref.child(key).setValue(event);


        finish();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar cal = new GregorianCalendar(year,month, dayOfMonth);
        setDate(cal);
    }

    private void setDate(final Calendar calendar){
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);

        Date pickedDate = calendar.getTime();
        Date currentDate = new Date();

        // If the given date is before the current date, create Toast
        if (pickedDate.after(currentDate)) {
            ((TextView) findViewById(R.id.showDate)).setText(dateFormat.format(calendar.getTime()));
            date = dateFormat.format(calendar.getTime());
        }else {
            Toast.makeText(this, "The date you have selected is not permited", Toast.LENGTH_SHORT).show();
        }
    }

    public  void datePicker(View view){
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(), "date");
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_date:
                if (checked)
                    // Hide View
                    spinner.setVisibility(View.GONE);
                    viewDate.setVisibility(View.VISIBLE);
                    specific = true;
                    break;
            case R.id.radio_general:
                if (checked)
                    // Ninjas rule
                    spinner.setVisibility(View.VISIBLE);
                    viewDate.setVisibility(View.GONE);
                    specific = false;
                    break;
        }
    }

    public static class DatePickerFragment extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(),
                    (DatePickerDialog.OnDateSetListener)
            getActivity(), year,month,day);
        }
    }


}
