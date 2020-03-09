package com.hfs.lib;

public class ExerciseOccurance extends ActivityOccurance {

	private int sets;
	private int reps;

	/**
	 *
	 * @param exercise
	 * @param sets
	 * @param reps
	 */
	public ExerciseOccurance(Exercise exercise, int sets, int reps) {
		super(exercise);
		// TODO - implement ExerciseOccurance.ExerciseOccurance
		throw new UnsupportedOperationException();
	}

	public int getSets() {
		return this.sets;
	}

	public int getReps() {
		return this.reps;
	}

	public ActivityReport getActivityReport() {
		// TODO - implement ExerciseOccurance.getActivityReport
		throw new UnsupportedOperationException();
	}

}