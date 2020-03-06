package com.hfs.lib;

import java.util.Date;

public class StandardProfile {

	private boolean reportSharing;
	private int sharablePin;
	private double height;
	private double weight;

	/**
	 * 
	 * @param nutrition
	 * @param schedule
	 * @param fitness
	 * @param sports
	 * @param exercises
	 * @param consumables
	 */
	public StandardProfile(Nutrition nutrition, Schedule schedule, Fitness fitness, Sports sports, Exercises exercises, Consumables consumables) {
		// TODO - implement StandardProfile.StandardProfile
		throw new UnsupportedOperationException();
	}

	public int getSharablePin() {
		return this.sharablePin;
	}

	/**
	 * 
	 * @param activity
	 */
	public UnfinishedActivity startActivitySession(Activity activity) {
		// TODO - implement StandardProfile.startActivitySession
		throw new UnsupportedOperationException();
	}

	public UnfinishedActivity[] getFutureActiviitySessions() {
		// TODO - implement StandardProfile.getFutureActiviitySessions
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param activity
	 * @param startTime
	 */
	public void addFutureActivitySession(Activity activity, Date startTime) {
		// TODO - implement StandardProfile.addFutureActivitySession
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param activity
	 */
	public void removeActivitySession(UnfinishedActivity activity) {
		// TODO - implement StandardProfile.removeActivitySession
		throw new UnsupportedOperationException();
	}

	public FinishedActivity[] getPastActivitySessions() {
		// TODO - implement StandardProfile.getPastActivitySessions
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param activity
	 */
	public void addPastActivitySession(FinishedActivity activity) {
		// TODO - implement StandardProfile.addPastActivitySession
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param activity
	 */
	public void removeActivitySession(FinishedActivity activity) {
		// TODO - implement StandardProfile.removeActivitySession
		throw new UnsupportedOperationException();
	}

	public boolean isSharingReports() {
		// TODO - implement StandardProfile.isSharingReports
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param reportSharing
	 */
	public void setReportSharing(boolean reportSharing) {
		this.reportSharing = reportSharing;
	}

	public Nutrition getNutrition() {
		// TODO - implement StandardProfile.getNutrition
		throw new UnsupportedOperationException();
	}

	public ConsumableOccurance[] getConsumption() {
		// TODO - implement StandardProfile.getConsumption
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param consumable
	 * @param amount
	 */
	public void consume(Consumable consumable, double amount) {
		// TODO - implement StandardProfile.consume
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param consumable
	 */
	public void addNewConsumable(Consumable consumable) {
		// TODO - implement StandardProfile.addNewConsumable
		throw new UnsupportedOperationException();
	}

	public Goal getGoal() {
		// TODO - implement StandardProfile.getGoal
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param goal
	 */
	public void setGoal(Goal goal) {
		// TODO - implement StandardProfile.setGoal
		throw new UnsupportedOperationException();
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

	// TODO - KJ: Fix access to hashCode
	private int hashCode() {
		// TODO - implement StandardProfile.hashCode
		throw new UnsupportedOperationException();
	}

}