package com.hfs.lib.activity;

public class ExerciseOccurrence extends ActivityOccurrence {

	private final int sets;
	private final int reps;

	/**
	 *
	 * @param finishedActivity
	 * @param sets
	 * @param reps
	 */
	public ExerciseOccurrence(FinishedActivity finishedActivity, int sets, int reps) throws IllegalArgumentException {
		super(finishedActivity);
		if(!(this.getActivity() instanceof Exercise)){
			throw new IllegalArgumentException(finishedActivity.getActivity().toString() + " is not of type Exercise.\nExerciseOccurance cannot be created.");
		}
		this.sets = sets;
		this.reps = reps;
	}

	public int getSets() {
		return this.sets;
	}

	public int getReps() {
		return this.reps;
	}

	public ActivityReport getActivityReport() {
		// TODO - implement ExerciseOccurrence.getActivityReport
		throw new UnsupportedOperationException();
	}

}