package com.hfs.lib.activity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.hfs.lib.typeconverters.DurationConverter;
import com.hfs.lib.typeconverters.OffsetDateTimeConverter;

import java.time.Duration;
import java.time.OffsetDateTime;

@Entity
public class FinishedActivityEntry {
    @PrimaryKey(autoGenerate = true)
    private final long finishedActivityId;

    private final long userId;
    private final boolean isSportOrExcercise;
    private final long sportOrExcerciseOccurrenceId;


    @TypeConverters(OffsetDateTimeConverter.class)
    private final OffsetDateTime startTime;
    @TypeConverters(DurationConverter.class)
    private final Duration duration;

    public FinishedActivityEntry(long finishedActivityId, long userId, boolean isSportOrExcercise, long sportOrExcerciseOccurrenceId, OffsetDateTime startTime, Duration duration) {
        this.finishedActivityId = finishedActivityId;
        this.userId = userId;
        this.isSportOrExcercise = isSportOrExcercise;
        this.sportOrExcerciseOccurrenceId = sportOrExcerciseOccurrenceId;
        this.startTime = startTime;
        this.duration = duration;
    }

    public long getFinishedActivityId() {
        return finishedActivityId;
    }

    public long getUserId() {
        return userId;
    }

    public boolean isSportOrExcercise() {
        return isSportOrExcercise;
    }

    public long getSportOrExcerciseOccurrenceId() {
        return sportOrExcerciseOccurrenceId;
    }

    public OffsetDateTime getStartTime() {
        return startTime;
    }

    public Duration getDuration() {
        return duration;
    }
}
