package com.tecnologiasintech.e_planner.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sergiosilva on 12/19/17.
 */

public class PurposedEventFragment extends Fragment{

    public static PurposedEventFragment newInstance() {

        PurposedEventFragment fragment = new PurposedEventFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
