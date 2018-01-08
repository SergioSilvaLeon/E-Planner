package com.tecnologiasintech.e_planner.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.tecnologiasintech.e_planner.R;

public class EventViewctivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_viewctivity);

        // Support Action bar

        // setviews
        TextView nameTxt = (TextView) findViewById(R.id.nameTxt);
        TextView dateTxt = (TextView) findViewById(R.id.dateTxt);
        TextView descriptionTxt = (TextView) findViewById(R.id.descriptionTxt);
        TextView hostTxt = (TextView) findViewById(R.id.hostTxt);
        TextView popularTxt = (TextView) findViewById(R.id.popularTxt);

        Intent intent = getIntent();
        String name = intent.getStringExtra(EventActivity.EXTRA_NAME);
        String date = intent.getStringExtra(EventActivity.EXTRA_DATE);
        String description = intent.getStringExtra(EventActivity.EXTRA_DESCRIPTION);
        String host = intent.getStringExtra(EventActivity.EXTRA_HOST);
        String popular = intent.getStringExtra(EventActivity.EXTRA_POPULAR);

        nameTxt.setText(name);
        dateTxt.setText(date);
        descriptionTxt.setText(description);
        hostTxt.setText(host);
        popularTxt.setText(popular);


    }
}
