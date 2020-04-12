package com.hfs.lib.activity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Entity
public class SportOccurrence extends ActivityOccurrence {

	@PrimaryKey(autoGenerate = true)
	public long sportOccurrenceId;

	private final double caloriesBurned;

	@Ignore
	private ActivityReport report;

	/**
	 * Only for Android Room.
	 * @param caloriesBurned
	 */
	@Deprecated
	public SportOccurrence(double caloriesBurned) {
		this.caloriesBurned = caloriesBurned;
	}

	/**
	 * Only for Android Room.
	 * @param finishedActivity
	 */
	@Deprecated
	public void init(FinishedActivity finishedActivity){
		super.setFinishedActivity(finishedActivity);
		this.report = genReport(finishedActivity, this.caloriesBurned);
	}

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
		this.report = genReport(finishedActivity, caloriesBurned);
	}

	private static ActivityReport genReport(FinishedActivity finishedActivity, double caloriesBurned){
		final StringBuffer reportBuffer = new StringBuffer();
		reportBuffer.append(finishedActivity.getActivity().getName() + " - " + caloriesBurned + " kCal burned.\n");
		reportBuffer.append("Duration: " + finishedActivity.getDuration().toString() + "\n");

		if(caloriesBurned > 300.0){
			reportBuffer.append("Amazing.");
		} else if(caloriesBurned > 150.0){
			reportBuffer.append("Well done.");
		} else {
			reportBuffer.append("Keep it up.");
		}

		return new ActivityReport(reportBuffer.toString());
	}

	public static double calculateCaloriesBurned(Duration duration, Sport sport){
		return duration.get(ChronoUnit.SECONDS) * sport.getCalorieMultiplier();
	}

	public double getCaloriesBurned() {
		return this.caloriesBurned;
	}

	public ActivityReport getActivityReport() {
		if(this.report == null){
			throw new NullPointerException("ActivityReport not initialised, you need to call init() first." +
					"\nDo not use deprecated constructor." +
					"\nThis should only occur in case of Android Room.");
		}
		return this.report;
	}

}