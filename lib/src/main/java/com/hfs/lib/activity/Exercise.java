package com.hfs.lib.activity;

import androidx.room.Entity;

@Entity(ignoredColumns = "isSportOrExercise")
public class Exercise extends Activity {

    public Exercise(String name) {
        super(name, IS_EXERCISE);
    }

}