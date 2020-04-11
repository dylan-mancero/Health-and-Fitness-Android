package com.hfs.lib.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.hfs.lib.activity.Activity;
import com.hfs.lib.activity.Sport;
import com.hfs.lib.dao.ActivitiesDao;

@Database(entities = {Activity.class, Sport.class}, version = 1)
public abstract class ActivitiesDatabase extends RoomDatabase {

    private static ActivitiesDatabase instance;

    public abstract ActivitiesDao activitiesDao();

    public static synchronized ActivitiesDatabase getInstance(Context context) {
        // TODO: Run db in a background thread - remove allowMainThredQueries()
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ActivitiesDatabase.class, "activities")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }

}