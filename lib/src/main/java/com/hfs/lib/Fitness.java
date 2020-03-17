package com.hfs.lib;


import com.hfs.lib.activity.FinishedActivity;
import com.hfs.lib.activity.Sport;
import com.hfs.lib.activity.SportOccurrence;

import java.util.LinkedList;
import java.util.List;

public class Fitness {

	private double caloriesBurned = 0;
	private List<FinishedActivity> activities;

	public Fitness() {
		this.activities = new LinkedList<>();
	}

	/**
	 * 
	 * @param activity
	 */
	public void addActivitySession(FinishedActivity activity) {
		if(activity == null){
			throw new NullPointerException("Cannot add null FinishedActivity to Fitness.");
		}

		final double caloriesBurned = SportOccurrence.calculateCaloriesBurned(activity.getDuration(), (Sport) activity.getActivity());
		if(caloriesBurned < 0){
			throw new IllegalArgumentException("Cannot burn " + caloriesBurned + " calories.");
		}
		this.caloriesBurned += caloriesBurned;

		this.activities.add(activity);
	}

	public FinishedActivity[] getActivitySessions() {
		return this.activities.toArray(new FinishedActivity[this.activities.size()]);
	}

	public void removeActivitySession(FinishedActivity activity) {
	    this.activities.remove(activity);
	}

}