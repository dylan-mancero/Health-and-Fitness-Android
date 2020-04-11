package com.hfs.lib.repo;

import android.app.Application;

import com.hfs.lib.activity.Activity;
import com.hfs.lib.dao.ActivitiesDao;
import com.hfs.lib.db.ActivitiesDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class Activities {
    private static Activities instance;

    private ActivitiesDao activitiesDao;
    // Acts as a cache
    private final List<Activity> activities;

    private Activities(Application application){
        ActivitiesDatabase db = ActivitiesDatabase.getInstance(application);
        this.activitiesDao = db.activitiesDao();

        activities = new ArrayList<>(this.activitiesDao.loadAllExercises());
        activities.addAll(this.activitiesDao.loadAllSports());
    }

    public static synchronized Activities getInstance(Application application){
        if(instance == null){
            instance = new Activities(application);
        }
        return instance;
    }

    public List<Activity> getActivities(){
        return Collections.unmodifiableList(this.activities);
    }

    public Activity getActivity(String name) throws NoSuchElementException{
        return this.activities.parallelStream()
                .filter(a -> a.getName().equals(name))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException(name + " not found."));
    }

}
