package com.hfs.lib.activity;

import java.time.DateTimeException;
import java.time.Duration;
import java.time.OffsetDateTime;

public class FinishedActivity implements ReportableStrategy {

	private final Duration duration;
	private final UnfinishedActivity unfinishedActivity;
	private final ActivityOccurrence activityOccurrence;

	/**
	 * 
	 * @param activity
	 */
	public FinishedActivity(UnfinishedActivity activity) {
	    this(activity, OffsetDateTime.now());
	}

	public FinishedActivity(UnfinishedActivity activity, int sets, int reps) {
		this(activity, OffsetDateTime.now(), sets, reps);
	}
	/**
	 * 
	 * @param unfinishedActivity
	 * @param endTime
	 */
	public FinishedActivity(UnfinishedActivity unfinishedActivity, OffsetDateTime endTime) throws DateTimeException{
		final OffsetDateTime start = unfinishedActivity.getStart();
		if(start.isAfter(endTime)){
			throw new DateTimeException("Provided end time is invalid.\nStart time: " + start.toString() + "\tEnd time: " + endTime.toString());
		}
		this.duration = Duration.between(start, endTime);
		this.unfinishedActivity = unfinishedActivity;

		if(!(unfinishedActivity.getActivity() instanceof Sport)){
			throw new IllegalArgumentException(unfinishedActivity.getActivity().toString() + " not of type Sport.\nCannot create FinishedActivity.");
		}
		this.activityOccurrence = new SportOccurrence(this);

	}

	public FinishedActivity(UnfinishedActivity unfinishedActivity, OffsetDateTime endTime, int sets, int reps) throws DateTimeException{
		final OffsetDateTime start = unfinishedActivity.getStart();
		if(endTime.isAfter(start)){
			throw new DateTimeException("Provided end time is invalid.\nStart time: " + start.toString() + "\tEnd time: " + endTime.toString());
		}
		this.duration = Duration.between(start, endTime);
		this.unfinishedActivity = unfinishedActivity;

		if(!(unfinishedActivity.getActivity() instanceof Exercise)){
			throw new IllegalArgumentException(unfinishedActivity.getActivity().toString() + " not of type Exercise.\nCannot create FinishedActivity.");
		}
		this.activityOccurrence = new ExerciseOccurrence(this, sets, reps);
	}

	public OffsetDateTime getStart() {
		return this.unfinishedActivity.getStart();
	}

	public OffsetDateTime getEnd() {
		return this.getStart().plus(duration);
	}

	public Duration getDuration(){
		return this.duration;
	}

	public Activity getActivity() {
	    return this.unfinishedActivity.getActivity();
	}

	public ActivityReport getActivityReport() {
	    return this.activityOccurrence.getActivityReport();
	}

}