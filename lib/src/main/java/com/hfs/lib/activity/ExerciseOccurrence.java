package com.hfs.lib.activity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class ExerciseOccurrence extends ActivityOccurrence {

	@PrimaryKey(autoGenerate = true)
	public long exerciseOccurrenceId;

	private final int sets;
	private final int reps;
	@Ignore
	private ActivityReport report;

	/**
	 * Only for Android Room.
	 * @param sets
	 * @param reps
	 */
	@Deprecated
	public ExerciseOccurrence(int sets, int reps){
		super();
		this.sets = sets;
		this.reps = reps;
	}

	/**
	 * Only for Android Room.
	 * @param finishedActivity
	 */
	@Deprecated
	 public void init(FinishedActivity finishedActivity){
		super.setFinishedActivity(finishedActivity);
		this.report = genReport(finishedActivity, this.sets, this.reps);
	 }

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

	private static ActivityReport genReport(FinishedActivity finishedActivity, int sets, int reps){
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

		return new ActivityReport(reportBuffer.toString());

	}

	public int getSets() {
		return this.sets;
	}

	public int getReps() {
		return this.reps;
	}

	public ActivityReport getActivityReport() {
		if(this.report == null){
			throw new NullPointerException("ActivityReport not initialised, you need to call init() first." +
					"\nDo not use deprecated constructor." +
					"\nThis should only occur in case of Android Room.");
		}
	    return this.report;
	}

}