package com.hfs.lib.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.hfs.lib.activity.Exercise;
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
}
