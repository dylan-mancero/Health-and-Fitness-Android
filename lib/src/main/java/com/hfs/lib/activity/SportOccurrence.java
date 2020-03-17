package com.hfs.lib.activity;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class SportOccurrence extends ActivityOccurrence {

	private final double caloriesBurned;
	private final ActivityReport report;

	/**
	 * 
	 * @param finishedActivity
	 */
	public SportOccurrence(FinishedActivity finishedActivity) throws IllegalArgumentException {
		super(finishedActivity);

		// If the activity is not a Sport, no SportOccurrence can be created.
		final Sport sport;
		try{
			sport = (Sport) this.getActivity();
		} catch (ClassCastException notASport){
			throw new IllegalArgumentException(finishedActivity.getActivity() + " is not of type Sport.\nCannot create a SportOccurance.");
		}

		this.caloriesBurned = calculateCaloriesBurned(finishedActivity.getDuration(), sport);

		final StringBuffer reportBuffer = new StringBuffer();
		reportBuffer.append(finishedActivity.getActivity().getName() + " - " + this.caloriesBurned + " kCal burned.\n");
		reportBuffer.append("Duration: " + finishedActivity.getDuration().toString() + "\n");

		if(this.caloriesBurned > 300.0){
			reportBuffer.append("Amazing.");
		} else if(this.caloriesBurned > 150.0){
			reportBuffer.append("Well done.");
		} else {
			reportBuffer.append("Keep it up.");
		}

		this.report = new ActivityReport(reportBuffer.toString());
	}

	private static double calculateCaloriesBurned(Duration duration, Sport sport){
		return duration.get(ChronoUnit.SECONDS) * sport.getCalorieMultiplier();
	}

	public double getCaloriesBurned() {
		return this.caloriesBurned;
	}

	public ActivityReport getActivityReport() {
		return this.report;
	}

}