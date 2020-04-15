package com.hfs.lib;

import androidx.lifecycle.LiveData;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.hfs.lib.activity.Activity;
import com.hfs.lib.activity.UnfinishedActivity;
import com.hfs.lib.activity.FinishedActivity;
import com.hfs.lib.nutrition.Consumable;
import com.hfs.lib.nutrition.ConsumableOccurrence;
import com.hfs.lib.nutrition.Nutrition;
import com.hfs.lib.typeconverters.GoalConverter;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
public class StandardProfile {

    @PrimaryKey(autoGenerate = true)
	private long standardProfileId;

	private boolean reportSharing;
	public static boolean SHARING = true, NOT_SHARING = false;
	@Ignore
	private int sharablePin;
	private double height;
	private double weight;
	@TypeConverters(GoalConverter.class)
	private Goal goal;

	@Ignore
	private Nutrition nutrition;
	@Ignore
	private Schedule schedule;
	@Ignore
	private Fitness fitness;

	/**
	 * Only for Room.
	 * @param standardProfileId
	 * @param reportSharing
	 * @param height
	 * @param weight
	 * @param goal
	 */
	@Deprecated
	public StandardProfile(long standardProfileId, boolean reportSharing, double height, double weight, Goal goal) {
		this.standardProfileId = standardProfileId;
		this.reportSharing = reportSharing;
		this.height = height;
		this.weight = weight;
		this.goal = goal;
	}

	protected void setStandardProfileId(long standardProfileId) {
		this.standardProfileId = standardProfileId;
	}

	/**
	 * Only for Room.
	 * @param nutrition
	 * @return
	 */
	@Deprecated
	public boolean setNutrition(Nutrition nutrition) {
		if(this.nutrition == null) {
			this.nutrition = nutrition;
			return true;
		} else{
			return false;
		}
	}

	/**
	 * Only for Room.
	 * @param schedule
	 * @return
	 */
	@Deprecated
	public boolean setSchedule(Schedule schedule) {
		if(this.schedule == null) {
			this.schedule = schedule;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Only for Room.
	 * @param fitness
	 * @return
	 */
	@Deprecated
	public boolean setFitness(Fitness fitness) {
		if(this.fitness == null) {
			this.fitness = fitness;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Only for Room.
	 * @return
	 */
	public long getStandardProfileId() {
		return standardProfileId;
	}

	public int getSharablePin() {
		// TODO: Implement sharable pin.
		throw new UnsupportedOperationException("Sharable Pin not implemented.");
	}

	/**
	 * 
	 * @param activity
	 */
	public UnfinishedActivity startActivitySession(Activity activity) {
		return new UnfinishedActivity(activity,this);
	}

	public LiveData<List<UnfinishedActivity>> getFutureActivitySessions() {
	    return this.schedule.getFutureActivitySessions();
	}

	/**
	 * 
	 * @param activity
	 * @param startTime
	 */
	public void addFutureActivitySession(Activity activity, OffsetDateTime startTime) {
	    this.schedule.addFutureActivitySession(activity,startTime);
	}

	/**
	 * 
	 * @param activity
	 */
	public void removeActivitySession(UnfinishedActivity activity) {
		this.schedule.removeActivitySession(activity);
	}

	public LiveData<List<FinishedActivity>> getPastActivitySessions() {
	    return this.fitness.getActivitySessions();
	}

	public void addPastActivitySession(FinishedActivity activity) {
		this.fitness.addActivitySession(activity);
	}

	public void addPastActivitySession(Activity activity, OffsetDateTime startTime, OffsetDateTime endTime){
		this.fitness.addActivitySession(activity, startTime, endTime);
	}

	public void removeActivitySession(FinishedActivity activity) {
	    this.fitness.removeActivitySession(activity);
	}

	public boolean getReportSharing() {
	    return this.reportSharing;
	}

	public void setReportSharing(boolean reportSharing) {
		this.reportSharing = reportSharing;
	}

	public Nutrition getNutrition() {
	    return this.nutrition;
	}

	public LiveData<List<ConsumableOccurrence>> getConsumption() {
	    return this.nutrition.getConsumables();
	}

	public void consume(Consumable consumable, double amount) {
	    this.nutrition.addConsumable(consumable, amount);
	}

	public Goal getGoal() {
	    return this.goal;
	}

	/**
	 * 
	 * @param goal
	 */
	public void setGoal(Goal goal) {
		this.goal = goal;
	}

	public double getHeight() {
		return this.height;
	}

	/**
	 * 
	 * @param height
	 */
	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return this.weight;
	}

	/**
	 * 
	 * @param weight
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

}