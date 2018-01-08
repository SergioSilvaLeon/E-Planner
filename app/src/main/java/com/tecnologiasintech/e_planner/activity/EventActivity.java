package com.tecnologiasintech.e_planner.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tecnologiasintech.e_planner.R;
import com.tecnologiasintech.e_planner.adapter.EventAdapter;
import com.tecnologiasintech.e_planner.adapter.StudentAdapter;
import com.tecnologiasintech.e_planner.model.Event;
import com.tecnologiasintech.e_planner.model.Student;

public class EventActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "EXTRA_NAME";
    public static final String EXTRA_DATE = "EXTRA_DATE";
    public static final String EXTRA_DESCRIPTION = "EXTRA_DESCRIPTION";
    public static final String EXTRA_POPULAR = "EXTRA_POPULAR";
    public static final String EXTRA_HOST = "EXTRA_HOST";
    public static final String EXTRA_KEY = "EXTRA_KEY";

    private RecyclerView mRecyclerView;
    private EventAdapter mEventAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mEventAdapter = new EventAdapter(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mEventAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventActivity.this, CreateEventActivity.class);
                startActivity(intent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onStart() {
        super.onStart();

        mEventAdapter.reset();

        // atach firebase listener;
        DatabaseReference reference =
                FirebaseDatabase.getInstance().getReference("EPlanner/Event");

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                try {
                    Event event = dataSnapshot.getValue(Event.class);
                    // Student to a list;
                    mEventAdapter.updateList(event);

                }catch (NullPointerException NPE){

                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                //mStudentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
