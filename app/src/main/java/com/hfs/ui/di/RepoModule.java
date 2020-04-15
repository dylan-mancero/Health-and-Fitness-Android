package com.hfs.ui.di;

import com.hfs.lib.activity.Activity;
import com.hfs.lib.nutrition.Consumable;
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
    private final Consumables consumables;

    private RepoModule(Activities activities, Consumables consumables) {
        this.activities = activities;
        this.consumables = consumables;
    }

    public static RepoModule getInstance(Activities activities, Consumables consumables) {
        if(instance == null) {
            instance = new RepoModule(activities, consumables);
        }

        return instance;
    }

    @Singleton
    @Provides
    Activities provideActivities(){
        return instance.activities;
    }

    @Singleton
    @Provides
    @Named("activities")
    List<Activity> getActivities(Activities activities){
        return activities.getActivities();
    }

    @Singleton
    @Provides
    Consumables provideConsumables(){
        return instance.consumables;
    }

    @Singleton
    @Provides
    @Named("consumables")
    List<Consumable> provideConsumablesList(){
        return instance.consumables.getConsumables();
    }
}

