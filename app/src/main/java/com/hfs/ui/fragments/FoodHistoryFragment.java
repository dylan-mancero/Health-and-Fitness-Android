package com.hfs.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hfs.ui.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FoodHistoryFragment extends Fragment {

    public FoodHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_food_history, container, false);

        return view;
    }
}
