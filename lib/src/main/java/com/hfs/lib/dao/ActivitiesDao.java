package com.hfs.lib.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.hfs.lib.activity.Activity;
import com.hfs.lib.activity.Exercise;
import com.hfs.lib.activity.FinishedActivity;
import com.hfs.lib.activity.Sport;
import com.hfs.lib.activity.UnfinishedActivity;

import java.util.List;

@Dao
public abstract class ActivitiesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void _insert_activities(Activity... activities);

    @Query("SELECT * FROM activity")
    public abstract List<Activity> _load_activities();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void _insert(Sport... sports);

    @Transaction
    public void insert(Sport... sports){
        _insert(sports);
        _insert_activities(sports);
    }

    @Query("SELECT * FROM sport")
    public abstract List<Sport> loadAllSports();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void _insert(Exercise... exercises);

    @Transaction
    public void insert(Exercise... exercises){
        _insert(exercises);
        _insert_activities(exercises);
    }

    @Query("SELECT * FROM exercise")
    public abstract List<Exercise> loadAllExercises();

    @Query("SELECT finishedActivity.* FROM finishedActivity " +
            "INNER JOIN unfinishedactivity " +
            "ON unfinishedActivityId = id " +
            "WHERE userId = :userId")
    public abstract List<FinishedActivity> loadFinishedActivities(long userId);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract void insert(FinishedActivity... finishedActivity);

    @Update
    public abstract void updateFinishedActivity(FinishedActivity finishedActivity);

    @Delete
    public abstract void delete(FinishedActivity finishedActivity);

    @Query("SELECT finishedActivity.* FROM finishedActivity " +
            "INNER JOIN unfinishedactivity " +
            "ON unfinishedActivityId = id " +
            "WHERE userId = :userId")
    public abstract LiveData<List<FinishedActivity>> loadAllFinishedActivities(long userId);

    @Query("SELECT * FROM unfinishedactivity WHERE id = :id")
    public abstract UnfinishedActivity getUnfinishedActivity(long id);

    @Query("SELECT * FROM unfinishedactivity WHERE userId = :id")
    public abstract List<UnfinishedActivity> loadUnfinishedActivities(long id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract long[] insert(UnfinishedActivity... unfinishedActivity);

    @Update
    public abstract void update(UnfinishedActivity unfinishedActivity);

    @Delete
    public abstract void delete(UnfinishedActivity... unfinishedActivity);

    @Query("SELECT * FROM unfinishedactivity WHERE userId = :userId")
    public abstract LiveData<List<UnfinishedActivity>> loadAllUnfinishedActivities(long userId);
}
