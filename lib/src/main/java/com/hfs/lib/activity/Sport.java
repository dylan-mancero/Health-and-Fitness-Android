package com.hfs.lib.activity;

import androidx.room.Entity;

@Entity(ignoredColumns = "isSportOrExercise")
public class Sport extends Activity {

	private final double calorieMultiplier;

	/**
	 * 
	 * @param calorieMultiplier
	 */
	public Sport(String name, double calorieMultiplier) {
		super(name, IS_SPORT);
		this.calorieMultiplier = calorieMultiplier;
	}

	public double getCalorieMultiplier() {
		return this.calorieMultiplier;
	}

}