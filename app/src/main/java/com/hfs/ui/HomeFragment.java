package com.hfs.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_home, container, false);

        final Button logoutBtn = fragmentView.findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intToLogin = new Intent(getActivity(), LoginActivity.class);
                startActivity(intToLogin);
            }
        });

        final Button startBtn = fragmentView.findViewById(R.id.startBtn);
        final Button stopBtn = fragmentView.findViewById(R.id.stopBtn);

        startBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(startBtn.getVisibility() == Button.VISIBLE) {
                    startBtn.setVisibility(Button.INVISIBLE);
                    startBtn.setEnabled(false);

                    stopBtn.setVisibility(Button.VISIBLE);
                    stopBtn.setEnabled(true);
                }
            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                stopBtn.setVisibility(Button.INVISIBLE);
                stopBtn.setEnabled(false);

                startBtn.setVisibility(Button.VISIBLE);
                startBtn.setEnabled(true);
            }
        });


        final NumberPicker hhPicker = fragmentView.findViewById(R.id.hhPicker);
        final NumberPicker mmPicker = fragmentView.findViewById(R.id.mmPicker);
        final NumberPicker ssPicker = fragmentView.findViewById(R.id.ssPicker);
        final NumberPicker[] pickers = {hhPicker, mmPicker, ssPicker};

        for(NumberPicker picker : pickers){
            picker.setMinValue(0);
            picker.setMaxValue(59);
        }
        hhPicker.setMaxValue(5);

        final Spinner activitySpinner = fragmentView.findViewById(R.id.activitySpinner);

        final List<String> activities = new ArrayList<>();
        activities.add("Running");
        activities.add("Swimming");
        activities.add("Cycling");
        activities.add("Walking");

        ArrayAdapter<String> activitiesAdapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_dropdown_item, activities);
        activitiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activitySpinner.setAdapter(activitiesAdapter);

        return fragmentView;
    }
}
