package com.hfs.ui;

import android.app.Application;
import android.util.Log;

import com.hfs.lib.StandardProfile;
import com.hfs.ui.di.AppComponent;
import com.hfs.ui.di.DaggerAppComponent;
import com.hfs.ui.di.ProfileModule;

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
                    .build();
        }
    }

    public AppComponent getAppComponent() {
        if(appComponent == null){
            throw new NullPointerException("AppComponent not initialised.\nCall initStandardProfile first.\nStandardProfile not set.");
        }
        return appComponent;
    }

    public StandardProfile getStandardProfile(){
        if(appComponent == null){
            throw new NullPointerException("AppComponent not initialised.\nCall initStandardProfile first.\nStandardProfile not set.");
        }

        return appComponent.standardProfile();
    }
}
