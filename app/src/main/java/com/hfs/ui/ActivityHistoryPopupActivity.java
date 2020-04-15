package com.hfs.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import androidx.annotation.Nullable;

import com.hfs.lib.StandardProfile;

import javax.inject.Inject;

public class ActivityHistoryPopupActivity extends Activity {

    @Inject StandardProfile profile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*
        DaggerProfileComponent.builder()
                .appComponent(((HFSApplication) getApplication()).getAppComponent())
                .build()
                .inject(this);
*/

        //Linking 'activity_activity_history_popupactivity_history_popup.xml' to this java class.
        setContentView(R.layout.activity_activity_history_popup);

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
