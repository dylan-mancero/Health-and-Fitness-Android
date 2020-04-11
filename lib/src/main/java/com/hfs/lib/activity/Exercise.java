package com.hfs.lib.activity;

import androidx.room.Entity;

@Entity
public class Exercise extends Activity {

    public Exercise(String name) {
        super(name);
    }

}