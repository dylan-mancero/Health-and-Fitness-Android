package com.hfs.ui;

import android.app.Application;
import android.util.Log;

import com.hfs.lib.StandardProfile;
import com.hfs.lib.repo.Activities;
import com.hfs.ui.di.AppComponent;
import com.hfs.ui.di.DaggerAppComponent;
import com.hfs.ui.di.ProfileModule;
import com.hfs.ui.di.RepoModule;

public class HFSApplication extends Application {
    private static final String TAG = "HFSApplication";
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public void initStandardProfile(StandardProfile standardProfile){
        Log.d(TAG, "initStandardProfile called with "  + standardProfile);
        if(appComponent == null){
            appComponent = DaggerAppComponent
                    .builder()
                    .profileModule(ProfileModule.getInstance(standardProfile))
                    .repoModule(RepoModule.getInstance(Activities.getInstance(this)))
                    .build();
        }
    }

    public AppComponent getAppComponent() {
        if(appComponent == null){
            throw new NullPointerException("AppComponent not initialised.\nCall initStandardProfile first.\nStandardProfile not set.");
        }
        return appComponent;
    }

}
