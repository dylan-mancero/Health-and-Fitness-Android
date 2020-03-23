package com.hfs.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hfs.ui.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddFutureActivityFragment extends Fragment {

    public AddFutureActivityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_future_activity, container, false);
    }
}
