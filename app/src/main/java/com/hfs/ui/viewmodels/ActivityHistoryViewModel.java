package com.hfs.ui.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.annotations.NotNull;
import com.hfs.lib.StandardProfile;
import com.hfs.lib.activity.FinishedActivity;
import com.hfs.ui.HFSApplication;
import com.hfs.ui.di.DaggerProfileComponent;

import java.util.List;

import javax.inject.Inject;


public class ActivityHistoryViewModel extends AndroidViewModel {

    final private LiveData<List<FinishedActivity>> activitiesLiveData;
    @Inject StandardProfile profile;

    public ActivityHistoryViewModel(@NotNull Application application) {
        super(application);

        DaggerProfileComponent.builder()
                .appComponent(((HFSApplication) application).getAppComponent())
                .build()
                .inject(this);

        final List<FinishedActivity> activities = profile.getPastActivitySessions();
        this.activitiesLiveData = new MutableLiveData<>(activities);
    }

    public void addActivity(FinishedActivity activity){
        this.profile.addPastActivitySession(activity);
    }

    public void removeActivity(FinishedActivity activity){
        this.profile.removeActivitySession(activity);
    }

    public LiveData<List<FinishedActivity>> getActivities() {
        return activitiesLiveData;
    }
}
