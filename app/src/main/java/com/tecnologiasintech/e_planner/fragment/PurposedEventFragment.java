package com.tecnologiasintech.e_planner.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tecnologiasintech.e_planner.R;
import com.tecnologiasintech.e_planner.adapter.PurposedEventFragmentAdapter;

/**
 * Created by sergiosilva on 12/19/17.
 */

public class PurposedEventFragment extends Fragment{

    private PurposedEventFragmentAdapter mAdapter;

    public static PurposedEventFragment newInstance() {

        PurposedEventFragment fragment = new PurposedEventFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_purposed_event, container, false);

        // Inflate and set recyclerview
        //Create the Adapter
        mAdapter = new PurposedEventFragmentAdapter();

        //Capture the recyclerView
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(PurposedEventFragment.this.getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);

        return view;

    }
}
