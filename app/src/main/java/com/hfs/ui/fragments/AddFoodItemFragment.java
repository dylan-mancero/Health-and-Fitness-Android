package com.hfs.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hfs.ui.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddFoodItemFragment extends Fragment {

    public AddFoodItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_food_item, container, false);

        Button confirm = (Button) view.findViewById(R.id.BtnConfirmAddFood);
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
