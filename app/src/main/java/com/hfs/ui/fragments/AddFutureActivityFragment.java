package com.hfs.ui.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.hfs.ui.DatePickerFragment;
import com.hfs.ui.R;
import com.hfs.ui.TimePickerFragment;

import java.text.DateFormat;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddFutureActivityFragment extends Fragment{
    Button dateOfBirthET;
    Button selectTimeBtn; //added
    String selectedDate;
    String selectedTime; //added
    public static final int REQUEST_CODE = 11; // Used to identify the result
    public static final int REQUEST_CODE1 = 12; //added

    private OnFragmentInteractionListener mListener, mListener1;

    public AddFutureActivityFragment() {
        // Required empty public constructor
    }

    public static AddFutureActivityFragment newInstance() {
        AddFutureActivityFragment fragment = new AddFutureActivityFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_future_activity, container, false);
        dateOfBirthET = view.findViewById(R.id.dateOfBirthET);
        selectTimeBtn = view.findViewById(R.id.selectTimeBtn);

        // get fragment manager so we can launch from fragment
        final FragmentManager fm = ((AppCompatActivity)getActivity()).getSupportFragmentManager();
        final FragmentManager fm1 = ((AppCompatActivity)getActivity()).getSupportFragmentManager(); //added
        // Using an onclick listener on the button to show the datePicker
        dateOfBirthET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create the datePickerFragment
                AppCompatDialogFragment newFragment = new DatePickerFragment();
                // set the targetFragment to receive the results, specifying the request code
                newFragment.setTargetFragment(AddFutureActivityFragment.this, REQUEST_CODE);
                // show the datePicker
                newFragment.show(fm, "datePicker");
            }
        });

        //selectTime added
        selectTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create the datePickerFragment
                AppCompatDialogFragment newFragment = new TimePickerFragment();
                // set the targetFragment to receive the results, specifying the request code
                newFragment.setTargetFragment(AddFutureActivityFragment.this, REQUEST_CODE1);
                // show the datePicker
                newFragment.show(fm1, " timePicker");
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // check for the results
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // get date from string
            selectedDate = data.getStringExtra("selectedDate");
            // set the value of the editText
            dateOfBirthET.setText(selectedDate);
        }

        if (requestCode == REQUEST_CODE1 && resultCode == Activity.RESULT_OK) {
            // get Time from string
            selectedTime = data.getStringExtra("selectedTime");
            // set the value of the editText
            selectTimeBtn.setText(selectedTime);
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        mListener1 = null; //added
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
