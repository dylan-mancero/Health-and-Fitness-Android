package com.hfs.lib.typeconverters;

import androidx.room.TypeConverter;

import com.hfs.lib.Goal;

public class GoalConverter {

    @TypeConverter
    public static Goal fromId(int id){
        final Goal[] goals = Goal.values();
        for(Goal goal : goals){
            if(id == goal.getId()){
                return goal;
            }
        }

        throw new IllegalArgumentException("id: " + id + " cannot be mapped to Goal.");
    }

    @TypeConverter
    public static int toInt(Goal goal){
        return goal.getId();
    }
}
