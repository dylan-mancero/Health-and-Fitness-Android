package com.hfs.lib;

import androidx.lifecycle.LiveData;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import com.hfs.lib.activity.Activity;
import com.hfs.lib.activity.FinishedActivity;
import com.hfs.lib.activity.UnfinishedActivity;
import com.hfs.lib.dao.ActivitiesDao;

import java.time.OffsetDateTime;
import java.util.List;

public class Schedule {

	private final ActivitiesDao activitiesDao;
	private LiveData<List<UnfinishedActivity>> activities;
	private final StandardProfile profile;

	public Schedule(ActivitiesDao activitiesDao, StandardProfile profile) {
		this.profile = profile;
		this.activitiesDao = activitiesDao;
		this.activities = activitiesDao.loadAllUnfinishedActivities(profile.getStandardProfileId());
	}

	public void addFutureActivitySession(Activity activity, OffsetDateTime startTime) {
		final UnfinishedActivity unfinishedActivity = new UnfinishedActivity(activity, startTime, this.profile);

		this.activitiesDao.insert(unfinishedActivity);
	}

	/**
	 * 
	 * @param activity
	 */
	public FinishedActivity endActivitySession(UnfinishedActivity activity) {
	    if(this.activities.getValue().contains(activity)){
	    	final FinishedActivity finishedActivity = activity.end();
	    	this.activitiesDao.insert(finishedActivity);
	    	return finishedActivity;
		} else {
	        throw new NullPointerException("UnfinishedActivity " + activity.toString() + " cannot be ended - null.");
		}
	}

	public FinishedActivity endActivitySession(UnfinishedActivity activity, int sets, int reps) {
		if(this.activities.getValue().contains(activity)){
			final FinishedActivity finishedActivity = activity.end(sets, reps);
			this.activitiesDao.insert(finishedActivity);
			return finishedActivity;
		} else {
			throw new NullPointerException("UnfinishedActivity " + activity.toString() + " cannot be ended - null.");
		}
	}

	public void removeActivitySession(UnfinishedActivity activity){
		this.activitiesDao.delete(activity);
	}

	public LiveData<List<UnfinishedActivity>> getFutureActivitySessions() {
	    return this.activities;
	}

}