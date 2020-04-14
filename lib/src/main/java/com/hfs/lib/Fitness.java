package com.hfs.lib;


import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hfs.lib.activity.Activity;
import com.hfs.lib.activity.FinishedActivity;
import com.hfs.lib.activity.Sport;
import com.hfs.lib.activity.SportOccurrence;
import com.hfs.lib.activity.UnfinishedActivity;
import com.hfs.lib.dao.ActivitiesDao;

import java.time.OffsetDateTime;
import java.util.List;

public class Fitness{
	private static Fitness instance;

	private final ActivitiesDao activitiesDao;

	private double caloriesBurned = 0;

	private final List<FinishedActivity> activities;
	private MutableLiveData<List<FinishedActivity>> activitiesLiveData;

	private final StandardProfile standardProfile;

	/**
	 * Only for Room.
	 * @param activitiesDao
	 */
	@Deprecated
	public Fitness(ActivitiesDao activitiesDao, StandardProfile profile){
		this.activitiesDao = activitiesDao;
		this.activities = activitiesDao.loadFinishedActivities(profile.getStandardProfileId());
		this.activities.forEach(activity -> activity.init(activitiesDao));
		this.standardProfile = profile;
		// TODO: Update calories on load.
	}

	public Fitness(Application application, StandardProfile profile) {
		final HFSDatabase db = HFSDatabase.getInstance(application);
		this.activitiesDao = db.activitiesDao();
		this.activities = this.activitiesDao.loadFinishedActivities(profile.getStandardProfileId());
		this.activities.forEach(activity -> activity.init(activitiesDao));
		this.activitiesLiveData = new MutableLiveData<>(this.activities);
		this.standardProfile = profile;
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

		this.activitiesDao.insert(activity);
	}

	public void addActivitySession(Activity activity, OffsetDateTime startTime, OffsetDateTime endTime){
		final UnfinishedActivity unfinishedActivity = new UnfinishedActivity(activity, startTime, this.standardProfile);
		Log.d("ADDING UNFINISHED ACTIVITY:", unfinishedActivity.getActivityName());
		final long unfinishedActivityId = this.activitiesDao.insert(unfinishedActivity)[0];
		unfinishedActivity.id = unfinishedActivityId;

		final FinishedActivity finishedActivity = new FinishedActivity(unfinishedActivity, endTime);
		this.addActivitySession(finishedActivity);
		this.activities.add(finishedActivity);
	}

	public LiveData<List<FinishedActivity>> getActivitySessions() {
	    return this.activitiesLiveData;
	}

	public void removeActivitySession(FinishedActivity activity) {
		// TODO: Implement removal of finished activity.
		if(activity != null) {
			this.activitiesDao.delete(activity);
		}
	}

}