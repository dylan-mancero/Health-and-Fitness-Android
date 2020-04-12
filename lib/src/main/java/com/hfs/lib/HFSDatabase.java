package com.hfs.lib;

import android.app.Application;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.hfs.lib.StandardProfile;
import com.hfs.lib.activity.Activity;
import com.hfs.lib.activity.ExerciseOccurrence;
import com.hfs.lib.activity.FinishedActivity;
import com.hfs.lib.activity.Sport;
import com.hfs.lib.activity.SportOccurrence;
import com.hfs.lib.dao.ActivitiesDao;
import com.hfs.lib.dao.StandardProfileDao;

@Database(entities = {Activity.class, Sport.class, StandardProfile.class, FinishedActivity.class, SportOccurrence.class, ExerciseOccurrence.class}, version = 1)
public abstract class HFSDatabase extends RoomDatabase {
    private static HFSDatabase instance;


    public abstract ActivitiesDao activitiesDao();

    public abstract StandardProfileDao standardProfileDao();

    public static synchronized HFSDatabase getInstance(Context context) {
        // TODO: Run db in a background thread - remove allowMainThredQueries()
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    HFSDatabase.class, "hfs")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }
}
