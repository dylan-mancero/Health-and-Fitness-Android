package com.hfs.lib.activity;

import java.time.DateTimeException;
import java.time.Duration;
import java.time.OffsetDateTime;

public class FinishedActivity implements ReportableStrategy {

	private final Duration duration;
	private final UnfinishedActivity unfinishedActivity;

	/**
	 * 
	 * @param activity
	 */
	public FinishedActivity(UnfinishedActivity activity) {
	    this(activity, OffsetDateTime.now());
	}

	/**
	 * 
	 * @param unfinishedActivity
	 * @param endTime
	 */
	public FinishedActivity(UnfinishedActivity unfinishedActivity, OffsetDateTime endTime) throws DateTimeException{
		final OffsetDateTime start = unfinishedActivity.getStart();
		if(endTime.isAfter(start)){
			throw new DateTimeException("Provided end time is invalid.\nStart time: " + start.toString() + "\tEnd time: " + endTime.toString());
		}
		this.duration = Duration.between(start, endTime);

		this.unfinishedActivity = unfinishedActivity;
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
		// TODO - implement FinishedActivity.getActivityReport
		throw new UnsupportedOperationException();
	}

}