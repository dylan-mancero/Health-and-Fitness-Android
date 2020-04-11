package com.hfs.lib.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public abstract class Activity {

	@PrimaryKey
	@NonNull private final String name;

	// For Room persistence.
	public boolean isSportOrExercise;

	public Activity(@NonNull String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
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