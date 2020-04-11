package com.hfs.ui.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hfs.ui.DatePickerFragment;
import com.hfs.ui.NumberPickerFragment;
import com.hfs.ui.R;
import com.hfs.ui.TimePickerFragment;



import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddFutureActivityFragment extends Fragment implements NumberPicker.OnValueChangeListener {
    Button dateOfBirthET;
    Button selectTimeBtn; //added
    Button durationBtn;
    Button saveBtn;
    String selectedDate;
    String selectedTime; //added
    String selectedNumber;
    String selectedNumber2;
    String selectedNumber3;

    private static TextView tv;
    static Dialog d;

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
        durationBtn = view.findViewById(R.id.durationBtn);
        saveBtn = view.findViewById(R.id.saveBtn);

        String [] values = {"SELECT AN ACTIVITY","Running", "Swimming", "Cycling", "Walking", "Push-ups","Sit-ups","Pull-ups","Plank"};
        Spinner spinner = (Spinner) view.findViewById(R.id.activitySpinner2);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), R.layout.spinner_item, values){
            @Override
            public boolean isEnabled(int position) {
                if(position == 0)
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    tv.setTextColor(Color.GRAY);
                }
                else{
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        // get fragment manager so we can launch from fragment
        final FragmentManager fm = ((AppCompatActivity)getActivity()).getSupportFragmentManager();
        final FragmentManager fm1 = ((AppCompatActivity)getActivity()).getSupportFragmentManager(); //added
        final FragmentManager fm2 = ((AppCompatActivity)getActivity()).getSupportFragmentManager();

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

        durationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NumberPickerFragment newFragment = new NumberPickerFragment();
                newFragment.setValueChangeListener(AddFutureActivityFragment.this);
                newFragment.show(fm2, "time picker");
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(spinner.getSelectedItemPosition() == 0 | selectedTime == null | selectedDate == null | durationBtn.getText() == "Set a Duration"){
                    Toast.makeText(getActivity(),"Please select all the fields.", Toast.LENGTH_SHORT).show();
                } else
                Toast.makeText(getActivity(),
                        spinner.getSelectedItem() + " activity on "
                        + selectedDate + " at " + selectedTime + " for " + durationBtn.getText() + " saved!", Toast.LENGTH_LONG).show();
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

    @Override
    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
        selectedNumber = Integer.toString(numberPicker.getValue());
        selectedNumber2 = Integer.toString(i);
        selectedNumber3 = Integer.toString(i1);

        if (numberPicker.getValue() != 0 && i != 0 && i1 != 0) {
            durationBtn.setText(selectedNumber + " Hours " + selectedNumber2 + "Minutes" + selectedNumber3+ " Seconds");
        } else if(numberPicker.getValue() == 0 && i == 0 && i1 != 0){
            durationBtn.setText(selectedNumber3+ " Seconds");
        } else if(numberPicker.getValue() == 0 && i != 0 && i1 == 0){
            durationBtn.setText(selectedNumber2+ " Minutes");
        } else if(numberPicker.getValue() != 0 && i == 0 && i1 == 0){
            durationBtn.setText(selectedNumber+ " Hours");
        } else if(numberPicker.getValue() == 0) {
            durationBtn.setText(selectedNumber2 + " Minutes " + selectedNumber3 + " Seconds");
        } else if(i !=0 && i1 == 0){
            durationBtn.setText(selectedNumber+ " Hours " + selectedNumber2 + " Minutes");
        }else if(i ==0 && i1 != 0){
            durationBtn.setText(selectedNumber+ " Hours " + selectedNumber2 + " Seconds");
        } else if((numberPicker.getValue() == 0 && i == 0 && i1 == 0)) {Toast.makeText(this.getActivity(),
                "Set a duration of at least one second ", Toast.LENGTH_SHORT).show();
            durationBtn.setText("Set A Duration");
        } else durationBtn.setText(selectedNumber + " Hours " + selectedNumber2 + " Minutes " + selectedNumber3+ " Seconds");
    }


}
