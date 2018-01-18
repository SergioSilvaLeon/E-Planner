package com.tecnologiasintech.e_planner.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tecnologiasintech.e_planner.R;
import com.tecnologiasintech.e_planner.adapter.EventAdapter;
import com.tecnologiasintech.e_planner.adapter.StackAdapter;

public class ManageStacksActivity extends AppCompatActivity {

    protected DatabaseReference firebaseDatabase = FirebaseDatabase.getInstance().getReference("EPlanner/Stacks");
    private StackAdapter mAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_stacks);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mAdapter = new StackAdapter(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // add, delete

        // expose in mentor activity

    }

    @Override
    protected void onStart() {
        super.onStart();

        mAdapter.clear();



        firebaseDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                try{
                    String string = dataSnapshot.getValue(String.class);
                    mAdapter.update(string);
                }catch (Exception e){}
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
