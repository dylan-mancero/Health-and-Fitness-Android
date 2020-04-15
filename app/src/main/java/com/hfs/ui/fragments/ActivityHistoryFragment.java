package com.hfs.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hfs.lib.StandardProfile;
import com.hfs.lib.activity.FinishedActivity;
import com.hfs.ui.HFSApplication;
import com.hfs.ui.R;
import com.hfs.ui.RecyclerViewAdapter;
import com.hfs.ui.di.DaggerProfileComponent;

import java.util.List;

import javax.inject.Inject;


/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityHistoryFragment extends Fragment {

    private static final String TAG = "FoodHistoryFragment";

    @Inject StandardProfile profile;

    public ActivityHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_activity_history, container, false);

        DaggerProfileComponent.builder()
                .appComponent(((HFSApplication) getActivity().getApplication()).getAppComponent())
                .build()
                .inject(this);

        final LiveData<List<FinishedActivity>> activities = profile.getPastActivitySessions();

        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this.getContext(), activities.getValue());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        activities.observe(getViewLifecycleOwner(), adapter::setActivities);

        Log.d(TAG, "onCreateView: started.");

        return view;
    }

    /*
    //Gets images from internet and maps them to relevant Activity name, as they are added to the same position in list as each other.
    private void initImageBitmaps(){
        // TODO: Either create a Map or put this in the db and add a url attribute in Activity.
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");
        mImageURLs.add("https://f1.pngfuel.com/png/797/224/38/running-logo-sports-exercise-rabbit-line-text-hand-symbol-png-clip-art.png");
        mNames.add("Running");

        mImageURLs.add("https://p7.hiclipart.com/preview/682/795/608/swimming-at-the-summer-olympics-olympic-games-olympic-symbols-sport-swimming.jpg");
        mNames.add("Swimming");

        mImageURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSjKyDdlUKL5HT-i75J1FjneMwxBgUh8x359M1n_tW28LVzt3o6");
        mNames.add("Climbing");

        mImageURLs.add("https://webstockreview.net/images/cycling-clipart-symbol.jpg");
        mNames.add("Cycling");
    }    */
}
