package com.hfs.ui.di;

import com.hfs.lib.activity.Activity;
import com.hfs.lib.repo.Activities;
import com.hfs.lib.repo.Consumables;

import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepoModule {

    private static RepoModule instance;

    private final Activities activities;

    private RepoModule(Activities activities) {
        this.activities = activities;
    }

    public static RepoModule getInstance(Activities activities) {
        if(instance == null) {
            instance = new RepoModule(activities);
        }

        return instance;
    }

    @Singleton
    @Provides
    Activities provideActivities(){
        return this.activities;
    }

    @Singleton
    @Provides
    @Named("activities")
    List<Activity> getActivities(Activities activities){
        return activities.getActivities();
    }

    @Singleton
    @Provides
    static Consumables provideConsumables(){
        return Consumables.getInstance();
    }
}

