package com.hfs.lib.activity;

public class ExerciseOccurrence extends ActivityOccurrence {

	private final int sets;
	private final int reps;
	private final ActivityReport report;

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

		StringBuffer reportBuffer = new StringBuffer(finishedActivity.getActivity().getName() + " - Sets: " + sets + ", Reps: " + reps+ "\n");
		reportBuffer.append("Duration: " + finishedActivity.getDuration().toString() + "\n");

		int totReps = sets * reps;

		if(totReps > 200){
			reportBuffer.append("Amazing.");
		} else if(totReps > 100) {
			reportBuffer.append("Well done.");
		} else {
			reportBuffer.append("Keep it up.");
		}

		this.report = new ActivityReport(reportBuffer.toString());
	}

	public int getSets() {
		return this.sets;
	}

	public int getReps() {
		return this.reps;
	}

	public ActivityReport getActivityReport() {
	    return this.report;
	}

}