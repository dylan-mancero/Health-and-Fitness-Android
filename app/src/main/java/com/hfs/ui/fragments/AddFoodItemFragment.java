package com.hfs.ui.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.hfs.lib.StandardProfile;
import com.hfs.lib.nutrition.Consumable;
import com.hfs.lib.repo.Consumables;
import com.hfs.ui.HFSApplication;
import com.hfs.ui.R;
import com.hfs.ui.di.DaggerProfileComponent;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddFoodItemFragment extends Fragment {
    private static final String TAG = "AddFoodItemFragment";

    @Inject StandardProfile profile;
    @Inject Consumables consumables;

    public AddFoodItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_food_item, container, false);

        DaggerProfileComponent.builder()
                .appComponent(((HFSApplication) this.getActivity().getApplication()).getAppComponent())
                .build()
                .inject(this);
        Log.d(TAG, "CHECKING................");

        String [] values = new String [6];

        int count = 0;
        for( Consumable i : consumables.getConsumables()){
            values[count]=i.getName();
            count++;

        }
        for (String s:values){
            Log.d(TAG, "food "+ s);
        }


        Button confirm = (Button) view.findViewById(R.id.BtnConfirmAddFood);
        EditText amount = (EditText) view.findViewById(R.id.editTextAmount);
        TextView food = (TextView)view.findViewById(R.id.FoodTextView);

        Spinner spinner = (Spinner) view.findViewById(R.id.foodSpinneroo);
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

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoodHistoryFragment frag = new FoodHistoryFragment();











                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_frame, frag);
                fragmentTransaction.commit();
            }
        });

        return view;
    }
}
