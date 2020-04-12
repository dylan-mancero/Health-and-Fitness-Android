package com.hfs.lib.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.hfs.lib.activity.Exercise;
import com.hfs.lib.activity.FinishedActivity;
import com.hfs.lib.activity.Sport;

import java.util.List;

@Dao
public interface ActivitiesDao {

    @Query("SELECT *, sport.calorieMultiplier " +
            "FROM activity INNER JOIN sport " +
            "ON activity.name = sport.name " +
            "WHERE activity.isSportOrExercise = 'TRUE' ")
    List<Sport> loadAllSports();

    @Query("SELECT * FROM activity WHERE isSportOrExercise = 'FALSE'")
    List<Exercise> loadAllExercises();

    @Query("SELECT * FROM finishedActivity WHERE userId = :id")
    List<FinishedActivity> loadFinishedActivities(int id);

    @Insert
    void insertFinishedActivity(FinishedActivity finishedActivity);

    @Update
    void updateFinishedActivity(FinishedActivity finishedActivity);

    @Update
    void delete(FinishedActivity finishedActivity);

    @Query("SELECT * FROM finishedactivity WHERE userId = :userId")
    LiveData<List<FinishedActivity>> loadAllFinishedActivities(int userId);
}
