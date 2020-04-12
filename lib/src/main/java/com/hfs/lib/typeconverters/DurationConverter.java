package com.hfs.lib.typeconverters;

import androidx.room.TypeConverter;

import java.time.Duration;

public class DurationConverter {

    @TypeConverter
    public static Duration fromStr(String time){
        return Duration.parse(time);
    }

    @TypeConverter
    public static String toStr(Duration duration){
        return duration.toString();
    }
}
