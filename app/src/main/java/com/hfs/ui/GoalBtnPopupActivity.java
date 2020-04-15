package com.hfs.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import androidx.annotation.Nullable;

public class GoalBtnPopupActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Linking 'activity_goalbtn_popup.xml' to this java class.
        setContentView(R.layout.activity_goalbtn_popup);

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
