package com.hfs.lib;

import com.hfs.lib.activity.Activity;
import com.hfs.lib.activity.FinishedActivity;
import com.hfs.lib.activity.UnfinishedActivity;

import java.time.OffsetDateTime;
import java.util.LinkedList;
import java.util.List;

public class Schedule {

	private List<UnfinishedActivity> activities;

	public Schedule() {
		this.activities = new LinkedList<>();
	}

	public void addFutureActivitySession(Activity activity, OffsetDateTime startTime) {
		final UnfinishedActivity unfinishedActivity = new UnfinishedActivity(activity, startTime);

		this.activities.add(unfinishedActivity);
	}

	/**
	 * 
	 * @param activity
	 */
	public FinishedActivity endActivitySession(UnfinishedActivity activity) {
	    if(this.activities.contains(activity)){
	    	return activity.end();
		} else {
	    	// TODO: Maybe throw an exception.
	    	return null;
		}
	}

	public FinishedActivity endActivitySession(UnfinishedActivity activity, int sets, int reps) {
		if(this.activities.contains(activity)){
			return activity.end(sets, reps);
		} else {
			// TODO: Maybe throw an exception.
			return null;
		}
	}

	public void removeActivitySession(UnfinishedActivity activity){
		this.activities.remove(activity);
	}

	public UnfinishedActivity[] getFutureActivitySessions() {
	    return this.activities.toArray(new UnfinishedActivity[this.activities.size()]);
	}

}