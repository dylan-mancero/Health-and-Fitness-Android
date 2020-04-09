package com.hfs.lib;

import com.hfs.lib.activity.Activity;
import com.hfs.lib.activity.UnfinishedActivity;
import com.hfs.lib.repo.Exercises;
import com.hfs.lib.activity.FinishedActivity;
import com.hfs.lib.repo.Sports;
import com.hfs.lib.nutrition.Consumable;
import com.hfs.lib.nutrition.ConsumableOccurance;
import com.hfs.lib.repo.Consumables;
import com.hfs.lib.nutrition.Nutrition;

import java.time.OffsetDateTime;
import java.util.List;

public class StandardProfile {

	private boolean reportSharing;
	private int sharablePin;
	private double height;
	private double weight;
	private Goal goal;
	private Nutrition nutrition;
	private Schedule schedule;
	private Fitness fitness;

	/**
	 * 
	 * @param nutrition
	 * @param schedule
	 * @param fitness
	 */
	public StandardProfile(Nutrition nutrition, Schedule schedule, Fitness fitness) {
	    this.nutrition = nutrition;
	    this.schedule = schedule;
	    this.fitness = fitness;
	}

	public int getSharablePin() {
		return this.sharablePin;
	}

	/**
	 * 
	 * @param activity
	 */
	public UnfinishedActivity startActivitySession(Activity activity) {
		return new UnfinishedActivity(activity);
	}

	public UnfinishedActivity[] getFutureActivitySessions() {
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

	public List<FinishedActivity> getPastActivitySessions() {
	    return this.fitness.getActivitySessions();
	}

	public void addPastActivitySession(FinishedActivity activity) {
		this.fitness.addActivitySession(activity);
	}

	public void removeActivitySession(FinishedActivity activity) {
	    this.fitness.removeActivitySession(activity);
	}

	public boolean isSharingReports() {
	    return this.reportSharing;
	}

	public void setReportSharing(boolean reportSharing) {
		this.reportSharing = reportSharing;
	}

	public Nutrition getNutrition() {
	    return this.nutrition;
	}

	public ConsumableOccurance[] getConsumption() {
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