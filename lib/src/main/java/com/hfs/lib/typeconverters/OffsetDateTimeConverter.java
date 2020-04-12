package com.hfs.lib.typeconverters;

import androidx.room.TypeConverter;

import java.time.OffsetDateTime;

public class OffsetDateTimeConverter {

    @TypeConverter
    public static OffsetDateTime fromStr(String time){
        return OffsetDateTime.parse(time);
    }

    @TypeConverter
    public static String toStr(OffsetDateTime time){
        return time.toString();
    }

}
