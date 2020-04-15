package com.hfs.lib.typeconverters;

import androidx.room.TypeConverter;

import com.hfs.lib.nutrition.ConsumableType;

public class ConsumableTypeConverter {

    @TypeConverter
    public static ConsumableType fromStr(String typeStr){
        final ConsumableType[] types = ConsumableType.values();
        for(ConsumableType type : types){
            if(type.toString().equals(typeStr)){
                return type;
            }
        }

        throw new IllegalArgumentException(typeStr + " is not a ConsumableType, thus it cannot be converted.");
    }

    @TypeConverter
    public static String toStr(ConsumableType type){
        return type.toString();
    }
}
