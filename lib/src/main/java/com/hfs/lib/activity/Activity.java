package com.hfs.lib.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Activity {

	@PrimaryKey
	@NonNull private final String name;
	private final boolean isSportOrExercise;
	public static boolean IS_SPORT = true, IS_EXERCISE = false;

	public Activity(@NonNull String name, boolean isSportOrExercise) {
		this.name = name;
		this.isSportOrExercise = isSportOrExercise;
	}

	public String getName() {
		return this.name;
	}

	public boolean isSportOrExercise() {
		return isSportOrExercise;
	}

	@Override
	public boolean equals(@Nullable Object obj) {
		if(obj == null){
			return false;
		}else if(obj == this){
			return true;
		}else if(obj instanceof Exercise || obj instanceof Sport){
			return this.name.equals(((Activity) obj).getName());
		} else {
			return false;
		}
	}
}