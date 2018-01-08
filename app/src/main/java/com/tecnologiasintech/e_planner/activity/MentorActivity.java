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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tecnologiasintech.e_planner.R;
import com.tecnologiasintech.e_planner.adapter.MentorAdapter;
import com.tecnologiasintech.e_planner.model.User;

public class MentorActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "EXTRA_NAME";
    public static final String EXTRA_EMAIL = "EXTRA_EMAIL";
    public static final String EXTRA_ADMIN = "EXTRA_ADMIN";
    public static final String EXTRA_STACK = "EXTRA_STACK";
    public static final String EXTRA_KEY = "EXTRA_KEY";

    private RecyclerView mRecyclerView;
    private MentorAdapter mMentorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mMentorAdapter = new MentorAdapter(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mMentorAdapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MentorActivity.this, CreateMentorActivity.class);
                startActivity(intent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onStart() {
        super.onStart();

        mMentorAdapter.reset();

        DatabaseReference reference =
                FirebaseDatabase.getInstance().getReference("EPlanner/User");

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                try {
                    User user = dataSnapshot.getValue(User.class);
                    mMentorAdapter.update(user);

                }catch (NullPointerException NPE){

                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

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
