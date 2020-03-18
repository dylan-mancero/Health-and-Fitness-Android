package com.hfs.lib.activity;

public class Sport extends Activity {

	private final double calorieMultiplier;

	/**
	 * 
	 * @param calorieMultiplier
	 */
	public Sport(String name, double calorieMultiplier) {
		super(name);
		this.calorieMultiplier = calorieMultiplier;
	}

	public double getCalorieMultiplier() {
		return this.calorieMultiplier;
	}

}