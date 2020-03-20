package com.hfs.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.hfs.ui.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityHistoryFragment extends Fragment {

    private static final String TAG = "ActivityHistory";

    public ActivityHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_activity_history, container, false);

        ListView list = (ListView) view.findViewById(R.id.activity_history_list);
        Log.d(TAG, "onCreateView: Started.");

        final ArrayList<String> activities = new ArrayList<>();
        activities.add("Cycling");
        activities.add("Running");
        activities.add("Jogging");
        activities.add("Hiking");
        activities.add("Walking");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_list_item_1, activities);
        list.setAdapter(adapter);

        //Below is a method to allow you to perform desired action once a list item is clicked on...
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: name: " + activities.get(position));
                //Toast.makeText(ActivityHistoryFragment.this, "You clicked on: " + activities.get(position), Toast.LENGTH_SHORT).show(); <--'xxxx.this' parameter does not work.
            }
        });
        return view;
    }
}
