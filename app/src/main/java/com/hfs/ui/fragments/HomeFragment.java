package com.hfs.ui.fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.hfs.lib.StandardProfile;
import com.hfs.lib.activity.Activity;
import com.hfs.lib.activity.UnfinishedActivity;
import com.hfs.lib.repo.Activities;
import com.hfs.ui.GoalBtnPopupActivity;
import com.hfs.ui.HFSApplication;
import com.hfs.ui.LoginActivity;
import com.hfs.ui.R;
import com.hfs.ui.di.DaggerProfileComponent;

import java.lang.ref.WeakReference;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.inject.Inject;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    Button selectDateBtn;
    private static final String TAG = "HomeFragment";
    
    @Inject StandardProfile profile;
    @Inject Activities activities;

    private ProgressBar progressBar;
    private ActivityButton activityBtn;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_home, container, false);

        final HFSApplication app = (HFSApplication) getActivity().getApplication();
        DaggerProfileComponent.builder().appComponent(app.getAppComponent()).build().inject(this);

        final List<Activity> activityList = activities.getActivities();

        // TODO: Set goal button text.
        final Button goalBtn = fragmentView.findViewById(R.id.goalBtn);
        //goalBtn.setText(profile.getGoal().name());

        //Adding popup window to goalBtn
        goalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), GoalBtnPopupActivity.class));
            }
        });

        final Button logoutBtn = fragmentView.findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Intent intToLogin = new Intent(getActivity(), LoginActivity.class);
            startActivity(intToLogin);
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
        final List<String> activityNames = activityList.stream().map(Activity::getName).collect(Collectors.toList());

        ArrayAdapter<String> activitiesAdapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_dropdown_item, activityNames);
        activitiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activitySpinner.setAdapter(activitiesAdapter);

        this.progressBar = fragmentView.findViewById(R.id.progressBar);

        final Button activityNormalBtn = fragmentView.findViewById(R.id.activityBtn);
        activityBtn = new ActivityButton(this, activityNormalBtn, activitySpinner, hhPicker, mmPicker, ssPicker);

        return fragmentView;
    }

    private static class ActivityButton implements View.OnClickListener{

        private final HomeFragment homeFragment;
        private final Button button;
        private final NumberPicker hhPicker, mmPicker, ssPicker;
        private enum State{
            START, STOP
        }
        private State state;
        private String activityName;

        ActivityTask activityTask;

        private ActivityButton(HomeFragment homeFragment, Button button, Spinner activitySpinner, NumberPicker hhPicker, NumberPicker mmPicker, NumberPicker ssPicker) {
            this.homeFragment = homeFragment;

            this.button = button;
            button.setOnClickListener(this);

            activitySpinner = activitySpinner;
            activitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    activityName = (String) parent.getItemAtPosition(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    activityName = (String) parent.getItemAtPosition(0);
                }
            });

            this.hhPicker = hhPicker;
            this.mmPicker = mmPicker;
            this.ssPicker = ssPicker;

            this.setState(State.START);
        }

        private void setState(State state){
            this.state = state;
            this.button.setText(state.name());
        }

        @Override
        public void onClick(View v) {
            if(state == State.START){
                this.setState(State.STOP);

                final Duration duration = Duration.ZERO
                        .plus(hhPicker.getValue(), ChronoUnit.HOURS)
                        .plus(mmPicker.getValue(), ChronoUnit.MINUTES)
                        .plus(ssPicker.getValue(), ChronoUnit.SECONDS);

                activityTask = new ActivityTask(this.homeFragment, this, duration, homeFragment.activities.getActivity(activityName));
                activityTask.execute();

            } else {
                activityTask.cancel(true);
                // Wait for activity to be cancelled.
                while(!activityTask.isCancelled());
                activityTask = null;

                button.setText(State.START.name());
            }
        }
    }

    public static class ActivityTask extends AsyncTask<Void, Integer, Void> {
        private final WeakReference<HomeFragment> homeFragmentWeakReference;
        private final ActivityButton button;
        private final Duration activityDuration;
        private final int progressMax;

        private final Activity activity;
        private UnfinishedActivity unfinishedActivity;

        @Inject StandardProfile profile;

        final static Function<WeakReference<HomeFragment>, HomeFragment> getHardReference = (weakReferance) -> {
            final HomeFragment homeFragment = weakReferance.get();
            if(homeFragment == null || homeFragment.getActivity() == null|| homeFragment.getActivity().isFinishing()) {
                return null;
            }
            return homeFragment;
        };

        private ActivityTask(HomeFragment homeFragment, ActivityButton button, Duration activityDuration, Activity activity) {
            this.homeFragmentWeakReference = new WeakReference<>(homeFragment);
            this.activityDuration = activityDuration;
            this.button = button;
            this.progressMax = Math.toIntExact(activityDuration.toMillis());
            this.activity = activity;

            DaggerProfileComponent.builder()
                    .appComponent(((HFSApplication) homeFragment.getActivity().getApplication()).getAppComponent())
                    .build().inject(this);

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            final HomeFragment homeFragment = getHardReference.apply(homeFragmentWeakReference);
            if(homeFragment == null){
                return;
            }
            final ProgressBar progressBar = homeFragment.progressBar;

            progressBar.setMax(progressMax);
            progressBar.setVisibility(View.VISIBLE);

            this.unfinishedActivity = profile.startActivitySession(this.activity);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            final int updateRate = 1000;
            for(int currentProgress = 0; currentProgress < progressMax; currentProgress += updateRate){
                if(this.isCancelled()){
                    return null;
                }

                publishProgress(currentProgress);
                try {
                    Thread.sleep(updateRate);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            /* Necessary as loop ends when currentProgress == progressMax
               so progressMax is never published and shown on the progress bar.
             */
            publishProgress(progressMax);
            try {
                // Enough time to see the progress bar full.
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            final HomeFragment homeFragment = getHardReference.apply(homeFragmentWeakReference);
            if(homeFragment == null){
                return;
            }
            final ProgressBar progressBar = homeFragment.progressBar;

            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            this.onTermination();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();

            this.onTermination();
        }

        private void onTermination(){
            final HomeFragment homeFragment = getHardReference.apply(homeFragmentWeakReference);
            if(homeFragment == null){
                return;
            }

            this.profile.addPastActivitySession(this.unfinishedActivity.end());

            final ProgressBar progressBar = homeFragment.progressBar;

            progressBar.setVisibility(View.INVISIBLE);
            progressBar.setProgress(0);

            final ActivityButton activityBtn = getHardReference.apply(homeFragmentWeakReference).activityBtn;
            if(activityBtn == null){
                return;
            }

            activityBtn.setState(ActivityButton.State.START);
        }
    }
}
