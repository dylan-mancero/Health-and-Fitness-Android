package com.hfs.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hfs.lib.activity.FinishedActivity;
import com.hfs.lib.activity.Sport;
import com.hfs.lib.activity.SportOccurrence;

import de.hdodenhof.circleimageview.CircleImageView;

public class ActivityHistoryPopupActivity extends Activity {

    CircleImageView image;
    TextView name;
    TextView date;
    TextView caloriesBurnt;
    TextView duration;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Linking 'activity_activity_history_popupactivity_history_popup.xml' to this java class.
        setContentView(R.layout.activity_activity_history_popup);

        final FinishedActivity finishedActivityReceived = ((ObjectWrapperForBinder)getIntent().getExtras().getBinder("object_value")).getData();

        image = findViewById(R.id.image);
        name = findViewById(R.id.label_Report);
        date = findViewById(R.id.fill_Time);
        caloriesBurnt = findViewById(R.id.fill_CaloriesBurnt);
        duration = findViewById(R.id.fill_Duration);

        //getallpastactivities().getValues().getPosition().getDuration etc...
        //getallpastactivities().getValues().getPosition().getCalories etc... linking to xml box.
        name.setText(finishedActivityReceived.getActivity().getName());
        date.setText(finishedActivityReceived.getStart().toString());

        //checks if activity selected is Exercise or Gym activity to determine the Calories burnt.
        if(finishedActivityReceived.getActivity().isSportOrExercise() == com.hfs.lib.activity.Activity.IS_SPORT){
            caloriesBurnt.setText(String.valueOf(new SportOccurrence(finishedActivityReceived).getCaloriesBurned()));
        }
        else{
            caloriesBurnt.setText("Not calculated");
        }

        duration.setText(finishedActivityReceived.getDuration().toString());

        //Setting resolution of pop-up window, in relation to screen size.
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        //Initialising width and height of popup
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        //Set width and height for popup window in ratio of screen size using multipliers.
        getWindow().setLayout((int) (width * 0.7), (int) (height * 0.7));

    }
}
