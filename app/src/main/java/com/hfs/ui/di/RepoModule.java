package com.hfs.ui.di;

import com.hfs.lib.activity.Activity;
import com.hfs.lib.repo.Consumables;
import com.hfs.lib.repo.Exercises;
import com.hfs.lib.repo.Sports;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class RepoModule {

    @Provides
    static Exercises provideExercises(){
        return Exercises.getInstance();
    }

    @Provides
    static Sports provideSports(){
        return Sports.getInstance();
    }

    @Provides
    static Consumables provideConsumables(){
        return Consumables.getInstance();
    }

    @Provides @Named("activities")
    static List<Activity> activities(Sports sports, Exercises exercises){
        final List<Activity> activities = new ArrayList<>();
        activities.addAll(sports.getSports());
        activities.addAll(exercises.getExercises());
        return activities;
    }
}
